package com.kai.miaosha.exception;

import com.kai.miaosha.result.CodeMsg;
import com.kai.miaosha.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 相当于一个controller
 * @author 张凯
 *
 * 额外补充：
 * 这里@ControllerAdvice并不是使用AOP的方式来织入业务逻辑的，而是Spring内置对其各个逻辑的织入方式进行了内置支持。
 * 可以理解为对Controller进行切面环绕。
 * @ControllerAdvice
 * @ControllerAdvice是一个@Component，用于定义@ExceptionHandler，@InitBinder和@ModelAttribute方法，
 * 适用于所有使用@RequestMapping方法，会对所有@RequestMapping方法进行检查，拦截。并进行异常处理。
 *
 *
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	//拦截什么异常
	@ExceptionHandler(value=Exception.class)//拦截所有的异常
	public Result<String> exceptionHandler(HttpServletRequest request, Exception e){
		e.printStackTrace();
		//一般的异常，包含CodeMsg可以查看信息
		if(e instanceof GlobalException) {
			GlobalException ex=(GlobalException) e;
			CodeMsg cm=ex.getCm();
			return Result.error(cm);
		}
		if(e instanceof BindException) {//是绑定异常的情况，承接JSR303校验不通过抛出的异常
			//强转
			BindException ex=(BindException) e;
			//获取错误信息
			List<ObjectError> errors=ex.getAllErrors();
			ObjectError error=errors.get(0);
			String msg=error.getDefaultMessage();
			return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
		}else {//不是绑定异常的情况
			return Result.error(CodeMsg.SERVER_ERROR);
		}
	}
}
