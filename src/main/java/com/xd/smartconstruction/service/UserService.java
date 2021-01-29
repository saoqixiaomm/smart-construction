package com.xd.smartconstruction.service;

import com.xd.smartconstruction.pojo.dto.account.UserDTO;
import com.xd.smartconstruction.pojo.vo.UserLoginReqVO;

/**
 * @author mm
 * @Date 2021-01-29 14:16
 */
public interface UserService {
    /**
     * 验证登录
     * @param userLoginReqVO
     * @return
     */
    UserDTO login(UserLoginReqVO userLoginReqVO);
}
