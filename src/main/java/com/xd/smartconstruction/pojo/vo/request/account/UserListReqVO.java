package com.xd.smartconstruction.pojo.vo.request.account;

import com.xd.smartconstruction.common.constant.SexEnum;
import com.xd.smartconstruction.common.validate.Insert;
import com.xd.smartconstruction.common.validate.Update;
import com.xd.smartconstruction.pojo.dto.BaseDTO;
import com.xd.smartconstruction.pojo.vo.request.BasePageVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author mm
 * @Date 2021-01-29 14:32
 */
@ApiModel("用户列表筛选模型")
@Data
public class UserListReqVO extends BasePageVO {

    @ApiModelProperty("用户id")
    private String id;

    @ApiModelProperty("用户名, 可模糊搜索")
    private String name;

    @ApiModelProperty("用户登录名称, 可模糊搜索")
    private String loginName;

    @ApiModelProperty("用户性别")
    private SexEnum sex;

    @ApiModelProperty("用户手机号, 可模糊搜索")
    private String phone;


}
