package com.xd.smartconstruction.controller.account;

import com.alibaba.fastjson.JSON;
import com.xd.smartconstruction.common.constant.ErrorCodeEnum;
import com.xd.smartconstruction.common.exception.BaseException;
import com.xd.smartconstruction.common.utils.OrikaUtils;
import com.xd.smartconstruction.common.utils.RedisUtils;
import com.xd.smartconstruction.common.utils.ResultBuilderUtils;
import com.xd.smartconstruction.common.utils.SessionGetter;
import com.xd.smartconstruction.common.validate.EntityValidator;
import com.xd.smartconstruction.common.validate.Insert;
import com.xd.smartconstruction.common.validate.Update;
import com.xd.smartconstruction.pojo.dto.ResultVO;
import com.xd.smartconstruction.pojo.dto.account.RoleDTO;
import com.xd.smartconstruction.pojo.dto.account.UserDTO;
import com.xd.smartconstruction.pojo.vo.request.GridPageDTO;
import com.xd.smartconstruction.pojo.vo.request.account.UserEditReqVO;
import com.xd.smartconstruction.pojo.vo.request.account.UserListReqVO;
import com.xd.smartconstruction.pojo.vo.request.account.UserLoginReqVO;
import com.xd.smartconstruction.pojo.vo.request.account.UserQueryReqVO;
import com.xd.smartconstruction.pojo.vo.response.GridPageVO;
import com.xd.smartconstruction.pojo.vo.response.account.UserEditRespVO;
import com.xd.smartconstruction.pojo.vo.response.account.UserRespVO;
import com.xd.smartconstruction.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author mm
 * @Date 2021-01-29 14:31
 */
@Api("用户模块")
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

    @Autowired
    private UserService userService;

    @ApiOperation("用户登录")
    @PostMapping(value = "/login")
    public ResultVO<UserRespVO> login(@RequestBody UserLoginReqVO userLoginReqVO) {
        //检验请求参数
        EntityValidator.validateAndThrow(userLoginReqVO);

        UserDTO userDTO = userService.login(userLoginReqVO);
        if (Objects.isNull(userDTO)) {
            return ResultBuilderUtils.buildError(ErrorCodeEnum.USER_NOT_EXIST);
        }
        //如果用户角色为空, 代表没有登录权限 直接返回null, 有必要可以通过id 来确定哪一些角色有登录权限, 目前简单处理
        if (CollectionUtils.isEmpty(userDTO.getRoleList())) {
            return ResultBuilderUtils.buildError(ErrorCodeEnum.USER_NO_POWER);
        }
        //登录成功
        List<String> roleNameList = userDTO.getRoleList().stream().map(RoleDTO::getRoleName).collect(Collectors.toList());
        log.info("用户: {} 登录系统成功, 角色为: {}", userDTO.getName(), StringUtils.join(roleNameList, ","));
        //生成一个token(不重复当做登录验证)
        String token = UUID.randomUUID().toString() + "-" + userDTO.getId();
        userDTO.setToken(token);
        //将用户信息放进缓存(前后端分离放进redis, 不分离可以放进session) 30分钟
        RedisUtils.set(token, JSON.toJSONString(token), 60 * 30);
        return ResultBuilderUtils.buildSuccess(OrikaUtils.convert(userDTO, UserRespVO.class));
    }

    @ApiOperation("添加用户")
    @PostMapping(value = "/addUser")
    public ResultVO<UserEditRespVO> add(@RequestBody UserEditReqVO requestVO) {
        //根据需要 在插入的这个group下面进行校验
        EntityValidator.validateAndThrow(requestVO, Insert.class);
        String id = userService.addUser(requestVO);
        return ResultBuilderUtils.buildSuccess(UserEditRespVO.builder().id(id).build());
    }


    @ApiOperation("修改用户信息")
    @PostMapping(value = "/updateUser")
    public ResultVO<UserEditRespVO> updateUser(@RequestBody UserEditReqVO requestVO) {
        //根据需要 在插入的这个group下面进行校验
        EntityValidator.validateAndThrow(requestVO, Update.class);
        String id = userService.updateUser(requestVO);
        return ResultBuilderUtils.buildSuccess(UserEditRespVO.builder().id(id).build());
    }

    @ApiOperation("获取用户信息")
    @PostMapping(value = "/getUser")
    public ResultVO<UserRespVO> getUser(@RequestBody UserQueryReqVO requestVO) {
        EntityValidator.validateAndThrow(requestVO);
        UserDTO userDTO = userService.getUserById(requestVO.getId());
        return ResultBuilderUtils.buildSuccess(OrikaUtils.convert(userDTO, UserRespVO.class));
    }

    @ApiOperation("重置密码")
    @PostMapping(value = "/resetPassword")
    public ResultVO<Object> resetPassword(@RequestBody UserQueryReqVO requestVO) {
        //如果用户的角色是超级管理员才能进行密码重置
        UserDTO userInfo = SessionGetter.getUserInfo();
        List<Integer> ids = userInfo.getRoleList().stream().map(RoleDTO::getId).collect(Collectors.toList());
        if (!ids.contains(1)) {
            throw new BaseException(ErrorCodeEnum.USER_NO_POWER, "用户权限不足");
        }
        EntityValidator.validateAndThrow(requestVO);
        boolean isSuccess = userService.resetPassword(requestVO.getId());
        return ResultBuilderUtils.buildSuccess(isSuccess);
    }

    @ApiOperation("修改用户信息")
    @PostMapping(value = "/listUser")
    public ResultVO<GridPageVO<UserRespVO>> listUser(@RequestBody UserListReqVO requestVO) {
        EntityValidator.validateAndThrow(requestVO, Update.class);
        GridPageDTO<UserDTO> userPage = userService.listUser(requestVO);
        return ResultBuilderUtils.buildSuccess(OrikaUtils.convert(userPage, GridPageVO.class));
    }
}
