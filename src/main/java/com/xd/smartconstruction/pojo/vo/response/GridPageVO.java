package com.xd.smartconstruction.pojo.vo.response;

import com.xd.smartconstruction.pojo.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author mm
 * @Date 2021-02-01 11:18
 */
@ApiModel("分页返回模型")
@Data
public class GridPageVO<T> extends BaseVO {

    @ApiModelProperty("总条数")
    private Integer total;

    @ApiModelProperty("内容")
    private List<T> content;

}
