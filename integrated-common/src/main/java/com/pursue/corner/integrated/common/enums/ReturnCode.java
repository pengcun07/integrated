package com.pursue.corner.integrated.common.enums;
/**
 * <p>基础异常代码定义</p>
 */
public enum ReturnCode {

    SUCCESS(1, "成功",""),
    ERROR(2, "失败",""),

    SYS_EXP(9998, "系统异常",""),
    DB_EXCEPTION(9999, "数据库异常","");

    /** 返回编码 */
    private int code;
    /** 返回消息内容 */
    private String msg;
    /** 返回编码名称 */
    private String name;

    private ReturnCode(int code, String name, String msg) {
        this.code = code;
        this.name = name;
        this.msg = "返回编码名称:" + name + "\n返回编码详细信息:" + msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = "返回编码名称:" + name + "\n返回编码详细信息:" + msg;
    }

    public static ReturnCode hasType(Enum<?> type) {
        for (ReturnCode metaype : ReturnCode.values()) {
            if (metaype.equals(type)) {
                return metaype;
            }
        }
        return null;
    }

    public static ReturnCode getByCode(int code) {
        for (ReturnCode value : ReturnCode.values()) {
            if (value.getCode()==code) {
                return value;
            }
        }
        return null;
    }

}
