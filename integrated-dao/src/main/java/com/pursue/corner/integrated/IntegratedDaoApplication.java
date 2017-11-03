package com.pursue.corner.integrated;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import com.pursue.corner.integrated.rw.config.DynamicDataSourceRegister;

@SpringBootApplication
//注册动态多数据源
@Import({DynamicDataSourceRegister.class})
public class IntegratedDaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegratedDaoApplication.class,args);
	}
}
