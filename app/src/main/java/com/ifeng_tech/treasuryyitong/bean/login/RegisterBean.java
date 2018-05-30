package com.ifeng_tech.treasuryyitong.bean.login;

/**
 * Created by zzt on 2018/5/30.
 */

public class RegisterBean {

    /**
     * code : 2000
     * message : 操作成功
     * data : {}
     */

    private String code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
    }
}
