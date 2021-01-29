package com.xd.smartconstruction.service.impl;

import com.xd.smartconstruction.common.utils.MD5Util;
import com.xd.smartconstruction.mapper.AccountMapper;
import com.xd.smartconstruction.pojo.dto.account.RoleDTO;
import com.xd.smartconstruction.pojo.dto.account.UserDTO;
import com.xd.smartconstruction.pojo.vo.UserLoginReqVO;
import com.xd.smartconstruction.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
}
