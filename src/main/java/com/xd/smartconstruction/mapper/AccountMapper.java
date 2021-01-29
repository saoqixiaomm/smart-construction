package com.xd.smartconstruction.mapper;

import com.xd.smartconstruction.pojo.dto.account.RoleDTO;
import com.xd.smartconstruction.pojo.dto.account.UserDTO;
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
}
