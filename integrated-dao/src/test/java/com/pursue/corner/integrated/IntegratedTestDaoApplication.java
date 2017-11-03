package com.pursue.corner.integrated;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pursue.corner.integrated.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IntegratedDaoApplication.class)
public class IntegratedTestDaoApplication {
	
	@Autowired
	private UserDao userMapper;

	@Test
	public void testDefaultDao() {
		System.out.println(userMapper.listAll());
	}
	
}
