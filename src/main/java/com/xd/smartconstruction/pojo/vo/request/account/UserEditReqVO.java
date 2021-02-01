package com.xd.smartconstruction.pojo.vo.request.account;

import com.xd.smartconstruction.common.constant.SexEnum;
import com.xd.smartconstruction.common.validate.Insert;
import com.xd.smartconstruction.common.validate.Update;
import com.xd.smartconstruction.pojo.dto.BaseDTO;
import com.xd.smartconstruction.pojo.dto.account.RoleDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @author mm
 * @Date 2021-01-29 14:32
 */
@ApiModel("用户信息编辑模型")
@Data
public class UserEditReqVO extends BaseDTO {

    @NotBlank(message = "用户id不能为空", groups = {Update.class})
    @ApiModelProperty("用户id")
    private String id;

    @NotBlank(message = "用户名 不能为空", groups = {Insert.class})
    @ApiModelProperty(value = "用户名", required = true)
    private String name;

    @NotBlank(message = "用户登录名称 不能为空", groups = {Insert.class})
    @ApiModelProperty(value = "用户登录名称", required = true)
    private String loginName;

    @NotBlank(message = "用户性别 不能为空", groups = {Insert.class})
    @ApiModelProperty(value = "用户性别", required = true)
    private SexEnum sex;

    @NotBlank(message = "用户年龄 不能为空", groups = {Insert.class})
    @ApiModelProperty(value = "用户年龄", required = true)
    private Integer age;

    @NotBlank(message = "用户生日 不能为空", groups = {Insert.class})
    @ApiModelProperty(value = "用户生日", required = true)
    private Date birth;

    @NotBlank(message = "用户手机号 不能为空", groups = {Insert.class})
    @ApiModelProperty(value = "用户手机号", required = true)
    private String phone;

    @ApiModelProperty("用户头像")
    private String avatar;


    @ApiModelProperty("用户密码")
    private String password;


}
