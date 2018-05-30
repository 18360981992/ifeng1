package com.ifeng_tech.treasuryyitong.bean.login;

/**
 * Created by zzt on 2018/5/30.
 */

public class LogoutBean {

    /**
     * code : 2000
     * message : 操作成功
     * data : {}
     */

    private String code;
    private String message;
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

}
