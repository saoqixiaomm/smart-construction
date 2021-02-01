package com.xd.smartconstruction.common.utils;


import com.xd.smartconstruction.pojo.dto.account.UserDTO;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mm
 * @Date 2021-02-01 14:17
 */
public class SessionGetter {
    private SessionGetter() {
    }

    /**
     * 获取当前登录人的信息
     * @return
     */
    public static UserDTO getUserInfo(){
        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes()).getRequest();
        String value = (String) RedisUtils.get(servletRequest.getHeader("token"));
        return FastJsonUtils.toBean(value, UserDTO.class);
    }

}
