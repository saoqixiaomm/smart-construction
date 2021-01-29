package com.xd.smartconstruction.common.utils;

import com.xd.smartconstruction.common.constant.ErrorCodeEnum;
import com.xd.smartconstruction.pojo.dto.ResultVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mm
 * @Date 2020-11-27 13:49
 */
@Slf4j
public class ResultBuilderUtils {

    /**
     * 组装失败返回参数
     * @param errorCode
     * @return
     */
    public static <T> ResultVO<T> buildError(ErrorCodeEnum errorCode){
        return buildError(errorCode.getCode(), errorCode.getMessage());
    }

    /**
     * 组装失败返回参数
     * @param errorCode
     * @param errMsg
     * @return
     */
    public static <T> ResultVO<T> buildError(String errorCode, String errMsg){
        return transReturn(new Object(), errorCode, errMsg);
    }

    /**
     * 组装成功返回参数
     * @param data
     * @return
     */
    public static <T> ResultVO<T> buildSuccess(Object data){
        return transReturn(data, "200", "");
    }


    public static <T> ResultVO<T> transReturn(Object data, String code, String errMsg){
        ResultVO result = new ResultVO();
        result.setData(data);
        result.setCode(code);
        result.setErrMsg(errMsg);
        return result;
    }
}
