package com.pursue.corner.integrated.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pursue.corner.integrated.IntegratedServiceApplication;
import com.pursue.corner.integrated.service.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IntegratedServiceApplication.class)
public class IntegratedTestServiceApplication {

	@Resource(name = "userRepository")
	private UserRepository userRepository;

	// 测试默认数据源
	@Test
	public void testDefaultDao() {
		System.out.println(userRepository.insert("Lily", 18));
	}

	// 测试读数据源 @DataSource("read")标识在Service层的方法上
	@Test
	public void testReadDao() {
		System.out.println(userRepository.listAll());
	}

}
