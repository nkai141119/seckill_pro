package com.kai.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5
 * @author 张凯
 * 实现两次MD5的具体操作
 */
public class MD5Util {
	public static String md5(String src) {
		//计算md5返回即可
		return DigestUtils.md5Hex(src);
	}
	//客户端固定的salt，跟用户的密码做一个拼装
	private static final String salt="1a2b3c4d";
	
	public static String inputPassToFormPass(String inputPass) {
		//增加拼装的严谨性
		String str=""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
		System.out.println(md5(str));
		return md5(str);
		//但是网站源码被拿到的前提下，仍然可能知道str，所以不是绝对安全。
	}
	//二次MD5，在服务端把接收到的md5存入到数据库中
	public static String formPassToDBPass(String formPass,String salt) {//随机的salt
		String str=""+salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(4);
		return md5(str); 			 
	}
	//数据库md5,使用数据库随机salt
	public static String inputPassToDbPass(String input,String saltDB) {
		String formPass=inputPassToFormPass(input);
		System.out.println(formPass);
		String dbPass=formPassToDBPass(formPass,saltDB);
		return dbPass;
	}
	
	public static void main(String[] args) {
		//测试两次md5和一下执行两次md5是否相同，结果是相同
		System.out.println(inputPassToDbPass("111111",salt));
		
		System.out.println(formPassToDBPass("9af1e2a66df9fe8cbd7ce7bdc9a18dcf",salt));
		//9af1e2a66df9fe8cbd7ce7bdc9a18dcf
	}
}
