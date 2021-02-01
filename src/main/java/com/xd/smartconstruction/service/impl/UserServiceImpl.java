package com.xd.smartconstruction.service.impl;

import com.xd.smartconstruction.common.utils.MD5Util;
import com.xd.smartconstruction.mapper.AccountMapper;
import com.xd.smartconstruction.pojo.dto.account.RoleDTO;
import com.xd.smartconstruction.pojo.dto.account.UserDTO;
import com.xd.smartconstruction.pojo.vo.request.GridPageDTO;
import com.xd.smartconstruction.pojo.vo.request.account.UserEditReqVO;
import com.xd.smartconstruction.pojo.vo.request.account.UserListReqVO;
import com.xd.smartconstruction.pojo.vo.request.account.UserLoginReqVO;
import com.xd.smartconstruction.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author mm
 * @Date 2021-01-29 14:16
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public UserDTO login(UserLoginReqVO userLoginReqVO) {
        UserDTO userDTO = accountMapper.login(userLoginReqVO.getLoginName(), MD5Util.MD5(userLoginReqVO.getPassword()));
        if (Objects.isNull(userDTO)) {
            return null;
        }
        List<RoleDTO> roleList = accountMapper.listRole(userDTO.getId());
        userDTO.setRoleList(roleList);
        return userDTO;
    }

    @Override
    public String addUser(UserEditReqVO requestVO) {
        //如果密码为空, 默认给用户添加123456的初始密码
        if (StringUtils.isBlank(requestVO.getPassword())) {
            requestVO.setPassword("123456");
        }
        requestVO.setPassword(MD5Util.MD5(requestVO.getPassword()));
        int effectRows = accountMapper.insert(requestVO);
        return requestVO.getId();
    }

    @Override
    public String updateUser(UserEditReqVO requestVO) {
        //当前端给的登录密码跟表里不一样的时候进行MD5加密--前端可能将表里面原本的md5加密后的直接传回来, 如果前端修改了密码会是明文的所以需要加密
        UserDTO oldUserInfo = getUserById(requestVO.getId());
        if (!oldUserInfo.getPassword().equals(requestVO.getPassword())) {
            requestVO.setPassword(MD5Util.MD5(requestVO.getPassword()));
        }
        int effectRows = accountMapper.updateUser(requestVO);
        return requestVO.getId();
    }

    @Override
    public UserDTO getUserById(String id) {
        return accountMapper.getUserById(id);
    }

    @Override
    public GridPageDTO<UserDTO> listUser(UserListReqVO requestVO) {
        Integer count = accountMapper.countUser(requestVO);
        if (Objects.isNull(count) || 0 == count) {
            return GridPageDTO.emptyInstance();
        }
        List<UserDTO> list = accountMapper.listUser(requestVO);
        return new GridPageDTO<>(count, list);
    }

    @Override
    public boolean resetPassword(String id) {
        UserEditReqVO requestVO = new UserEditReqVO();
        requestVO.setId(id);
        requestVO.setPassword(MD5Util.MD5("123456"));
        int effectRows = accountMapper.updateUser(requestVO);
        return effectRows > 0;
    }
}
