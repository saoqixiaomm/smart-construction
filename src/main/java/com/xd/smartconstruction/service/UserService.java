package com.xd.smartconstruction.service;

import com.xd.smartconstruction.pojo.dto.account.UserDTO;
import com.xd.smartconstruction.pojo.vo.request.GridPageDTO;
import com.xd.smartconstruction.pojo.vo.request.account.UserEditReqVO;
import com.xd.smartconstruction.pojo.vo.request.account.UserListReqVO;
import com.xd.smartconstruction.pojo.vo.request.account.UserLoginReqVO;
import com.xd.smartconstruction.pojo.vo.request.account.UserQueryReqVO;
import com.xd.smartconstruction.pojo.vo.response.account.UserRespVO;

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

    /**
     * 添加用户, 返回id
     * @param requestVO
     * @return
     */
    String addUser(UserEditReqVO requestVO);

    /**
     * 修改用户信息, 返回id
     * @param requestVO
     * @return
     */
    String updateUser(UserEditReqVO requestVO);

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    UserDTO getUserById(String id);

    /**
     * 获取用户列表
     * @param requestVO
     * @return
     */
    GridPageDTO<UserDTO> listUser(UserListReqVO requestVO);

    /**
     * 充值用户密码
     * @param id
     * @return
     */
    boolean resetPassword(String id);
}
