package com.pursue.corner.integrated.api.requests;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.pursue.corner.integrated.api.models.UserEntry;

public class LoginRequest implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6025506059798299868L;
	/** 版本 **/
    private String version;
    /** 用户 **/
    private UserEntry loginInfo;
    
    public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public UserEntry getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(UserEntry loginInfo) {
		this.loginInfo = loginInfo;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
