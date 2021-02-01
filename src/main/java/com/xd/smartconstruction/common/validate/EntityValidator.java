package com.xd.smartconstruction.common.validate;


import com.xd.smartconstruction.common.constant.ErrorCodeEnum;
import com.xd.smartconstruction.common.exception.BaseException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public class EntityValidator {

    public static <T> ValidateResult validateEntity(T domain){
        return validate(domain);
    }
    
    public static ValidateResult validateEntityWithList(Object obj, Class<?>... groups) throws IllegalAccessException {
        ValidateResult validateResult = new ValidateResult();
        if (obj instanceof Collection) {
            Collection objList = (Collection) obj;
            if (!CollectionUtils.isEmpty(objList)) {
                for (Object e : objList) {
                    validateResult = validateEntityWithList(e, groups);
                    if (validateResult.hasError())
                        break;
                }
            }
        }
        else if (!ObjectUtils.isEmpty(obj)) {
            validateResult = validate(obj, groups);
            if (validateResult.hasError())
                return validateResult;

            Field[] fields = obj.getClass().getDeclaredFields();
            if (fields.length != 0) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (Collection.class.isAssignableFrom(field.getType())) {
                        validateResult = validateEntityWithList((Collection) field.get(obj), groups);
                    }
                    if (validateResult.hasError())
                        break;
                }
            }
        }
        return validateResult;
    }


    public static <T> ValidateResult validate(T domain, Class<?>... groups) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return convert(validator.validate(domain, groups), new ValidateResult());
    }

    public static <T> void validateAndThrow(T domain, Class<?>... groups) {
        if (Objects.isNull(domain)) {
            throw new BaseException(ErrorCodeEnum.PARAM_VALID_ERRPR.getCode(), "请求参数不能为空");
        }
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        ValidateResult validateResult = convert(validator.validate(domain, groups), new ValidateResult());
        if (validateResult.hasError()) {
            throw new BaseException(ErrorCodeEnum.PARAM_VALID_ERRPR.getCode(), validateResult.getErrorMessages());
        }
    }

    public static <T> ValidateResult validate(T domain, String property, Class<?>... groups) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return convert(validator.validateProperty(domain, property, groups), new ValidateResult());
    }

    private static <T> ValidateResult convert(Set<ConstraintViolation<Object>> vr, ValidateResult r) {
        vr.forEach((cv) -> r.addErrorMessage(cv.getMessage()));
        return r;
    }

}
