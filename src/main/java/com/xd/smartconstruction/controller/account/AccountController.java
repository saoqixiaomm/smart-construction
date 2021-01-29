package com.xd.smartconstruction.controller.account;

import com.alibaba.fastjson.JSON;
import com.xd.smartconstruction.common.constant.ErrorCodeEnum;
import com.xd.smartconstruction.common.utils.OrikaUtils;
import com.xd.smartconstruction.common.utils.RedisUtils;
import com.xd.smartconstruction.common.utils.ResultBuilderUtils;
import com.xd.smartconstruction.pojo.dto.ResultVO;
import com.xd.smartconstruction.pojo.dto.account.RoleDTO;
import com.xd.smartconstruction.pojo.dto.account.UserDTO;
import com.xd.smartconstruction.pojo.vo.UserLoginReqVO;
import com.xd.smartconstruction.pojo.vo.UserVO;
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
    public ResultVO<UserVO> login(@RequestBody UserLoginReqVO userLoginReqVO) {
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
        return ResultBuilderUtils.buildSuccess(OrikaUtils.convert(userDTO, UserVO.class));
    }

    @ApiOperation("添加用户")
    @PostMapping(value = "/addUser")
    public ResultVO<UserLoginReqVO> add(@RequestBody UserLoginReqVO userLoginReqVO) {
        return ResultBuilderUtils.buildSuccess(userLoginReqVO);
    }

}
