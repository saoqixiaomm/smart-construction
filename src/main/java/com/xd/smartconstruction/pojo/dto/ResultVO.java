package com.xd.smartconstruction.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mm
 * @Date 2021-01-29 15:39
 */
@Data
public class ResultVO<T> implements Serializable {

    private String code;

    private String errMsg;

    private T data;
}
