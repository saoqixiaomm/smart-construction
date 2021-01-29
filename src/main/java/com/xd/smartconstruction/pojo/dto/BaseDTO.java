package com.xd.smartconstruction.pojo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author mm
 * @Date 2021-01-29 15:30
 */
@Data
public class BaseDTO {

    private Date createTime;
    private Date updateTime;
    private String createBy;
    private String updateBy;
}
