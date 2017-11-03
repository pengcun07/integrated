package com.pursue.corner.integrated.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pursue.corner.integrated.api.requests.LoginRequest;
import com.pursue.corner.integrated.api.responses.LoginResponse;
import com.pursue.corner.integrated.common.domain.OperationEnvironment;
/**
 * <p>RestfulService 接口类</p>
 */
@RestController
@RequestMapping("/integrated/restful")
public interface IntegratedRestfulService {
	
	@RequestMapping("/login")
	LoginResponse login(LoginRequest loginRequest, OperationEnvironment environment);
}
