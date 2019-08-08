package com.kai.miaosha.config;

import com.kai.miaosha.domain.MiaoshaUser;
import com.kai.miaosha.service.MiaoshaUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 自定义解析器，都要实现HandlerMethodArgumentResolver接口
 * 用于优化/goods/to_list的逻辑，不再直接传入token，而是直接传入MiaoshaUser，不必在tolist内部通过token查user。
 * 思路：要让MiaoshaUser的实例对象可以像SpringMVC中的controller 方法中的HttpServletRequest的实例对象request一样可以直接使用
 * 解析前端传来的cookie里面的token或者请求参数里面的token的业务逻辑就在这里完成
 * */
//将UserArgumentResolver注册到config里面去（放入容器），使得后续可以直接在WebConfig中autowire注入
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver{

	@Autowired					//既然能注入service，那么可以用来容器来管理，将其放在容器中
			MiaoshaUserService miaoshaUserService;
	
	
	public Object resolveArgument(MethodParameter arg0, ModelAndViewContainer arg1, NativeWebRequest webRequest,
			WebDataBinderFactory arg3) throws Exception {
		HttpServletRequest request=webRequest.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse response=webRequest.getNativeResponse(HttpServletResponse.class);
		String paramToken=request.getParameter(MiaoshaUserService.COOKIE1_NAME_TOKEN);
		System.out.println("@UserArgumentResolver-resolveArgument  paramToken:"+paramToken);
		//获取cookie
		String cookieToken=getCookieValue(request,MiaoshaUserService.COOKIE1_NAME_TOKEN);
		System.out.println("@UserArgumentResolver-resolveArgument  cookieToken:"+cookieToken);
		if(StringUtils.isEmpty(cookieToken)&&StringUtils.isEmpty(paramToken))
		{
			return null;
		}
		String token=StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
		//System.out.println("goods-token:"+token);
		//System.out.println("goods-cookieToken:"+cookieToken);		
		MiaoshaUser user=miaoshaUserService.getByToken(token,response);
		System.out.println("@UserArgumentResolver--------user:"+user);
		
		//去取得已经保存的user，因为在用户登录的时候,user已经保存到threadLocal里面了，因为拦截器首先执行，然后才是取得参数
		//MiaoshaUser user=UserContext.getUser();
		return user;
	}

	public String getCookieValue(HttpServletRequest request, String cookie1NameToken) {//COOKIE1_NAME_TOKEN-->"token"
		//遍历request里面所有的cookie
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for(Cookie cookie :cookies) {
				if(cookie.getName().equals(cookie1NameToken)) {
					System.out.println("getCookieValue:"+cookie.getValue());
					return cookie.getValue();
				}
			}
		}
		System.out.println("No getCookieValue!");
		return null;
	}

	public boolean supportsParameter(MethodParameter parameter) {
		//返回参数的类型
		Class<?> clazz=parameter.getParameterType();
		return clazz==MiaoshaUser.class;
	}
	
}
