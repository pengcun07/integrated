package com.pursue.corner.integrated.api.responses;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.pursue.corner.integrated.api.enums.SecurityReturn;
import com.pursue.corner.integrated.api.models.UserEntry;

public class LoginResponse extends BaseResponse{
	
	/** 版本 **/
	private String version;
	/** 用户 **/
	private UserEntry userInfo;
	
	public LoginResponse() {
		super();
	}
	
	public LoginResponse(int code, String name ) {
		super(code, name);
	}
	/**
	 * this和super在构造函数中只能有一个，且都必须是构造函数当中的第一行。
	 * */
	public LoginResponse(SecurityReturn security) {
		 this(security.getCode(), security.getName());
	}
	
    public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public UserEntry getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserEntry userInfo) {
		this.userInfo = userInfo;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
