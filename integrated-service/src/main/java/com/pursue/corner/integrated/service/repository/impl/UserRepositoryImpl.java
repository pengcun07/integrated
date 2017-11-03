package com.pursue.corner.integrated.service.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pursue.corner.integrated.dao.UserDao;
import com.pursue.corner.integrated.model.User;
import com.pursue.corner.integrated.service.repository.UserRepository;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository{

	@Autowired
	private UserDao userDao;
	
	//用于表示获取数据源@DataSource("read")
	@Override
	public List<User> listAll() {
		// TODO Auto-generated method stub
		return userDao.listAll();
	}

	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		return userDao.findByName(name);
	}

	@Override
	public int insert(String name, Integer age) {
		// TODO Auto-generated method stub
		return userDao.insert(name, age);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(Long id) {
		userDao.delete(id);
	}

	@Override
	public User findById(Long id) {
		return userDao.findById(id);
	}
}
