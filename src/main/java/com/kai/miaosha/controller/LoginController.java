package com.kai.miaosha.controller;

import ch.qos.logback.classic.Logger;
import com.kai.miaosha.redis.RedisService;
import com.kai.miaosha.result.CodeMsg;
import com.kai.miaosha.result.Result;
import com.kai.miaosha.service.MiaoshaUserService;
import com.kai.miaosha.service.UserService;
import com.kai.miaosha.vo.LoginVo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
@RequestMapping("/login")
@Controller
public class LoginController {
	@Autowired
	UserService userService;
	@Autowired
	RedisService redisService;

	@Autowired
	MiaoshaUserService miaoshaUserService;
	
	//slf4j
	private static Logger log=(Logger) LoggerFactory.getLogger(Logger.class);
	
	@RequestMapping("/to_login")
	public String toLogin() {
		return "login";//返回页面login
	}
	//SpringBoot在使用controller注解时返回字符串实质上返回的是templates下的该名称的页面文件；使用ResponseBody注解时，则返回实际的数据，并没有其他意义。
	
	//测试路径，已经废弃
	@RequestMapping("/do_login_test")//作为异步操作
	@ResponseBody
	public Result<String> doLogintest(HttpServletResponse response, @Valid LoginVo loginVo) {//0代表成功
		String token=miaoshaUserService.loginString(response,loginVo);
		return Result.success(token);
	}
	
	
	
	  //使用JSR303校验
		@RequestMapping("/do_login")//作为异步操作
		@ResponseBody
		public Result<Boolean> doLogin(HttpServletResponse response,@Valid LoginVo loginVo) {//0代表成功
			//JSR303校验不成功会抛出BindException异常，由GlobalExceptionHandler进行处理
			//参数检验成功之后，登录
			CodeMsg cm=miaoshaUserService.login(response,loginVo);
			if(cm.getCode()==0) {
				return Result.success(true);
			}else {
				return Result.error(cm);
			}
		}
}
