package com.kai.miaosha.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class IsMobileValidator implements ConstraintValidator<IsMobile,String>{

	private boolean required=false;
	public void initialize(IsMobile constraintAnnotation) {
		constraintAnnotation.required();
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(required) {//查看值是否是必须的
			//IsMobile中定义为true，所以是必须的。
			//调用自己实现的ValidatorUtil
			return ValidatorUtil.isMobile(value);
		}else {
			if(StringUtils.isEmpty(value)) {//required
				return true;
			}else {
				return ValidatorUtil.isMobile(value);
			}
		}
	}

}