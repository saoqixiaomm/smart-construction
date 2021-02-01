package com.xd.smartconstruction.pojo.vo.response.account;

import com.xd.smartconstruction.pojo.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mm
 * @Date 2021-02-01 10:50
 */
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ApiModel("用户编辑返回模型")
@Data
public class UserEditRespVO extends BaseVO {

    @ApiModelProperty(value = "用户id")
    private String id;

}
