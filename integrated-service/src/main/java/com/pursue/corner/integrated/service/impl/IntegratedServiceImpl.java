package com.pursue.corner.integrated.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pursue.corner.integrated.api.IntegratedRestfulService;
import com.pursue.corner.integrated.api.IntegratedWebService;
import com.pursue.corner.integrated.api.enums.SecurityReturn;
import com.pursue.corner.integrated.api.requests.LoginRequest;
import com.pursue.corner.integrated.api.responses.LoginResponse;
import com.pursue.corner.integrated.common.constant.Constant;
import com.pursue.corner.integrated.common.domain.OperationEnvironment;

public class IntegratedServiceImpl implements IntegratedWebService, IntegratedRestfulService {

	private final Logger logger = LoggerFactory.getLogger(IntegratedServiceImpl.class);

	@Override
	public LoginResponse login(LoginRequest loginRequest, OperationEnvironment environment) {
		if (logger.isInfoEnabled()) {
			logger.info("[IntegratedWebService]登入请求:" + loginRequest);
		}
		LoginResponse response = null;
		
		try {

			if (logger.isInfoEnabled()) {
				logger.info("[IntegratedWebService]登入请求完成：" + response);
			}
			response = new LoginResponse(SecurityReturn.LOGIN_SUCCESS);
		} catch (Exception e) {
			logger.error("未知异常异常", e);
			response = new LoginResponse(SecurityReturn.LOGIN_FAIL);
		}
		
		response.setVersion(Constant.DEFAULT_VERSION);
		return response;
	}

}
