package com.xd.smartconstruction.pojo.dto.account;

import com.alibaba.fastjson.annotation.JSONField;
import com.xd.smartconstruction.common.constant.SexEnum;
import com.xd.smartconstruction.pojo.dto.BaseDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author mm
 * @Date 2021-01-29 14:32
 */
@Data
public class UserDTO extends BaseDTO {

    private String id;

    private String name;

    private String loginName;

    private SexEnum sex;
    private Integer age;
    private Date birth;
    private String phone;
    private String avatar;
    private String password;

    private List<RoleDTO> roleList;

    private String token;

}
