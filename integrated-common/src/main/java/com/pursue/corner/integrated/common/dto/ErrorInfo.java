package com.pursue.corner.integrated.common.dto;

import com.pursue.corner.integrated.common.enums.ReturnCode;

public class ErrorInfo<T> {

    private Integer code;
    private String msg;
    private String url;
    private T data;
    
    public ErrorInfo(ReturnCode returnCode) {
		this.code = returnCode.getCode();
		this.msg = returnCode.getMsg();
	}
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
}