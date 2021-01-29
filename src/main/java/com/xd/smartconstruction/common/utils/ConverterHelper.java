/*
 * Miya.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xd.smartconstruction.common.utils;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Objects;

/**
 * @author ${jiangjiangbo}
 * @version $Id: ConverterHelper.java, v 0.1 2019年06月10日 20:35 ${jiangjiangbo} Exp $
 */
public class ConverterHelper {
    /**
     * 订单取消类型枚举转换器
     */
    public static class DateConverter extends BidirectionalConverter<Date, String> {

        @Override
        public String convertTo(final Date source, final Type<String> destinationType) {
            if(Objects.isNull(source)){
                return null;
            }else{
                return DateUtil.format(source);
            }
        }

        @Override
        public Date convertFrom(final String source, final Type<Date> destinationType) {
            if(StringUtils.isNotBlank(source)){
                if(source.contains("-")){
                    if(source.length() == 19){
                        return DateUtil.parseDate(source, DateUtil.YYYY_MM_DD_24HH_MM_SS);
                    }else if(source.length()== 10){
                        return DateUtil.parseDate(source, DateUtil.YYYY_MM_DD);
                    }
                }else{
                    return DateUtil.parseDate(source,DateUtil.YYYYMMDD);
                }
            }
            return null;
        }
    }
}