package com.xd.smartconstruction.pojo.vo.request;

import com.xd.smartconstruction.pojo.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author mm
 * @Date 2021-02-01 11:23
 */
@ApiModel("分页模型")
@Data
public class BasePageVO extends BaseVO {

    @ApiModelProperty("页码")
    public Integer pageNo;

    @ApiModelProperty("分页大小")
    public Integer pageSize;

    @ApiModelProperty("排序字段")
    public String sortKey;

    @ApiModelProperty("排序规则 asc/desc")
    public String sortOrder;

}
