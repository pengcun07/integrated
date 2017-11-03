package com.pursue.corner.integrated.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pursue.corner.integrated.model.User;

@Mapper
public interface UserDao {
	
	@Select("SELECT * FROM user")
	List<User> listAll();
	
	@Select("SELECT * FROM user WHERE name = #{name}")
	User findByName(@Param("name") String name);

	@Insert("INSERT INTO user(name, age) VALUES(#{name}, #{age})")
	int insert(@Param("name") String name, @Param("age") Integer age);

	@Update("UPDATE user SET age=#{age} WHERE name=#{name}")
	void update(User user);

	@Delete("DELETE FROM user WHERE id =#{id}")
	void delete(Long id);
	
	@Select("SELECT * FROM user WHERE id =#{id}")
	User findById(@Param("id") Long id);
}
