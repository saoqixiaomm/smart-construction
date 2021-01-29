/*
 * Miya.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.xd.smartconstruction.common.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum YesOrNoEnum {

    /**
     * 是
     */
    YES(1, "是"),
    /**
     * 否
     */
    NO(0, "否");

    private Integer code;

    private String description;

    /**
     * 根据code获取枚举
     *
     * @param code code
     * @return ENUM
     */
    public static YesOrNoEnum getByCode(Integer code) {
        Optional<YesOrNoEnum> opt = Arrays.stream(YesOrNoEnum.values()).filter(e -> e.code.intValue() == code).findAny();
        return opt.orElse(null);
    }
}