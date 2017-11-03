package com.pursue.corner.integrated.service.repository;

import java.util.List;
import com.pursue.corner.integrated.model.User;

public interface UserRepository {
	
	List<User> listAll();
	
	User findByName(String name);

	int insert(String name, Integer age);

	void update(User user);

	void delete(Long id);

	User findById(Long id);
}
