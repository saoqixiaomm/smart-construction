package com.xd.smartconstruction.pojo.vo.response.account;

import com.xd.smartconstruction.common.constant.SexEnum;
import com.xd.smartconstruction.pojo.dto.BaseDTO;
import com.xd.smartconstruction.pojo.dto.account.RoleDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author mm
 * @Date 2021-01-29 14:32
 */
@ApiModel("用户信息模型")
@Data
public class UserRespVO extends BaseDTO {

    @ApiModelProperty("用户id")
    private String id;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("用户登录名称")
    private String loginName;

    @ApiModelProperty("用户性别")
    private SexEnum sex;
    @ApiModelProperty("用户年龄")
    private Integer age;
    @ApiModelProperty("用户生日")
    private Date birth;
    @ApiModelProperty("用户手机号")
    private String phone;
    @ApiModelProperty("用户头像")
    private String avatar;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("用户角色列表")
    private List<RoleDTO> roleList;

    @ApiModelProperty("接口认证令牌")
    private String token;

}
