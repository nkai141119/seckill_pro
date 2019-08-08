package com.kai.miaosha.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.kai.miaosha.util.IsMobile;
//用于接收前端传来的login的信息-手机号和密码（已经md5）
public class LoginVo {
	private String mobile;
	private String password;
	
	@NotNull
	@IsMobile
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@NotNull
	@Length(min=32)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
