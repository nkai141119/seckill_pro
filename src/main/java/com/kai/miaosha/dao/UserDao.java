package com.kai.miaosha.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.kai.miaosha.domain.User;

@Mapper
public interface UserDao {
	@Select("select * from t_user where id=#{id}")//@Param("id")引用domain中User的id.
	public User getById(@Param("id") int id);
	@Insert("insert into t_user(id,name) values(#{id},#{name})")  //id为自增的，所以可以不用设置id
	public void insert(User user);
}
