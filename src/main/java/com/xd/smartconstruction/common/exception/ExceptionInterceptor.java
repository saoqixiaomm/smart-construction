package com.xd.smartconstruction.common.exception;

import com.xd.smartconstruction.common.constant.ErrorCodeEnum;
import com.xd.smartconstruction.common.utils.ResultBuilderUtils;
import com.xd.smartconstruction.pojo.dto.ResultVO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 全局异常的抓取
 */
@Component
@Aspect
public class ExceptionInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);
    /**
     * 切面点
     */
    private final String POINT_CUT = "execution(* com.xd.smartconstruction.controller.*.*.*(..))";

    @Around(value = POINT_CUT)
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        logger.debug("环绕通知的目标方法名：" + proceedingJoinPoint.getSignature().getName());
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            return errorHandler(throwable);
        }
    }

    private <T> ResultVO<T> errorHandler(Throwable error) {
        if (error instanceof BaseException) {
            BaseException exception = (BaseException) error;
            logger.error("全局异常捕获: {}-{}", exception.getCode(), exception.getMessage());
            return ResultBuilderUtils.buildError(exception.getCode(), exception.getMessage());
        } else {
            logger.error("全局异常捕获: ", error);
            return ResultBuilderUtils.buildError(ErrorCodeEnum.RUN_TIME_ERROR.getCode(), error.getMessage());
        }
    }

}
