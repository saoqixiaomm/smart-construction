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
@ApiModel("用户信息查询模型")
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryReqVO extends BaseVO {

    @NotBlank(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id", required = true)
    private String id;

}
