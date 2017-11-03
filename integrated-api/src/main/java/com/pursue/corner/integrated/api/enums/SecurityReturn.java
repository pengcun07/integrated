/**
 * 
 */
package com.pursue.corner.integrated.api.enums;

public enum SecurityReturn {

	LOGIN_SUCCESS(0, "登入成功"),
	
	LOGIN_FAIL(10, "登入失败"),
	LOGIN_FAIL_USERNAME(11, "用户名不存在"),
	LOGIN_FAIL_PASSWORD(12, "密码错误"),
	
	AUTHORIZ_FAIL(20, "无权限"),
	
	LOGOUT_SUCCESS(1000, "登出成功");
	private Integer code;
	private String name;

	SecurityReturn(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
