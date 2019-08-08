package com.kai.miaosha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kai.miaosha.dao.UserDao;
import com.kai.miaosha.domain.User;
//扫描会识别出service
@Service
public class UserService {
	@Autowired
	UserDao userDao;
	public User getById(int id) {
		return userDao.getById(id);
	}
	
	//使用事务
	//这里测试了一下，插入重复的id是否会造成数据库回滚；答案yes，并且页面会返回错误码500100
	@Transactional
	public boolean tx() {
		User user=new User();
		user.setId(3);
		user.setName("kai");
		userDao.insert(user);
		
		User user1=new User();
		user1.setId(1);
		user1.setName("kaikai");
		userDao.insert(user1);			//这里出问题则回滚
		
		return true;
	}
}
