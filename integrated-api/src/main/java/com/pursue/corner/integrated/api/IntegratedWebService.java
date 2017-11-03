package com.pursue.corner.integrated.api;

import javax.jws.WebService;

import com.pursue.corner.integrated.api.requests.LoginRequest;
import com.pursue.corner.integrated.api.responses.LoginResponse;
import com.pursue.corner.integrated.common.domain.OperationEnvironment;

/**
 * <p>@WebService 接口类</p>
 * /**
* 接口默认是abstract的，
* 接口里的方法默认都是public的、abstract的
*/
@WebService
public interface IntegratedWebService {

	LoginResponse login(LoginRequest loginRequest, OperationEnvironment environment);
}
