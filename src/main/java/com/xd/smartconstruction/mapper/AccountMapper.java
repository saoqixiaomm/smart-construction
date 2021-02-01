package com.xd.smartconstruction.mapper;

import com.xd.smartconstruction.pojo.dto.account.RoleDTO;
import com.xd.smartconstruction.pojo.dto.account.UserDTO;
import com.xd.smartconstruction.pojo.vo.request.account.UserEditReqVO;
import com.xd.smartconstruction.pojo.vo.request.account.UserListReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mm
 * @Date 2021-01-29 14:46
 */
@Mapper
public interface AccountMapper {

    UserDTO login(@Param("loginName") String loginName, @Param("password") String password);

    List<RoleDTO> listRole(@Param("userId") String userId);

    int insert(UserEditReqVO requestVO);

    int updateUser(UserEditReqVO requestVO);

    UserDTO getUserById(@Param("userId") String userId);

    Integer countUser(UserListReqVO requestVO);

    List<UserDTO> listUser(UserListReqVO requestVO);
}
