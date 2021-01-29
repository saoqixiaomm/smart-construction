package com.xd.smartconstruction.common.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author mm
 * @Date 2021-01-29 15:28
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SexEnum {


    /**
     * 是
     */
    M("男"),
    /**
     * 否
     */
    F("女");

    private String desc;
}
