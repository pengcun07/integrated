package com.pursue.corner.integrated.api.responses;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BaseResponse {

    protected Integer code;
    protected String name;
    protected String msg;

    public BaseResponse() {

    }

    public BaseResponse(int code, String name) {
        this.code = code;
        this.name = name;
        this.msg = "返回编码名称:" + name + "\n返回编码详细信息:" + msg;
    }
   
    public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
