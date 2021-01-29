/*
 * Miya.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.xd.smartconstruction.common.utils;

import com.xd.smartconstruction.common.constant.YesOrNoEnum;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;

import java.util.List;

/**S
 * @author ${liushuangzeng}
 * @version $Id: OrikaUtil.java, v 0.1 2018年08月08日 下午8:09 ${liushuangzeng} Exp $
 */
public class OrikaUtils {

    private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    private static ConverterFactory converterFactory = mapperFactory.getConverterFactory();

    static {
        converterFactory.registerConverter(new ConverterHelper.DateConverter());
    }

    public static <A, B> ClassMapBuilder<A, B> classMap(Class<A> source, Class<B> target) {
        return mapperFactory.classMap(source, target);
    }

    public static <T> T convert(Object source, Class<T> target) {
        return mapperFactory.getMapperFacade().map(source, target);
    }

    public static <S, D> List<D> convertList(Iterable<S> source, Class<D> target) {
        return mapperFactory.getMapperFacade().mapAsList(source, target);
    }


    /*****************************************exside****************************/

    public static Long string2Long(String id) {
        return Long.parseLong(id.trim());
    }
    public static String statusName(Integer status){
        if(status.equals(YesOrNoEnum.NO.getCode())){
            return "(已删除)";
        }
        return "";
    }
}