package com.xd.smartconstruction.pojo.vo.request.account;

import com.xd.smartconstruction.pojo.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author mm
 * @Date 2021-01-29 15:51
 */
@ApiModel("用户信息模型")
@EqualsAndHashCode(callSuper = true)
@Data
public class UserLoginReqVO extends BaseVO {

    @NotBlank(message = "登录名/手机号不能为空")
    @ApiModelProperty(value = "登录名/手机号", required = true)
    private String loginName;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "用户登录密码", required = true)
    private String password;

}
