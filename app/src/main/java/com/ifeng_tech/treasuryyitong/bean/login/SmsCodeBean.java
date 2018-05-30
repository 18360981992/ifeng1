package com.ifeng_tech.treasuryyitong.bean.login;

/**
 * Created by zzt on 2018/5/30.
 */

public class SmsCodeBean {

    /**
     * code : 2000
     * message : 操作成功
     * data : {"time":60}
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
        /**
         * time : 60
         */

        private int time;

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
}
