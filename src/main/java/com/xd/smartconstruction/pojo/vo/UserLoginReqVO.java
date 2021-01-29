package com.xd.smartconstruction.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author mm
 * @Date 2021-01-29 15:51
 */
@ApiModel("用户信息模型")
@EqualsAndHashCode(callSuper = true)
@Data
public class UserLoginReqVO extends BaseVO{

    @ApiModelProperty("登录名/手机号")
    private String loginName;

    @ApiModelProperty("用户登录密码")
    private String password;

}
