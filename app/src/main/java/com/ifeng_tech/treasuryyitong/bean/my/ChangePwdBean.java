package com.ifeng_tech.treasuryyitong.bean.my;

/**
 * Created by zzt on 2018/5/30.
 */

public class ChangePwdBean {

    /**
     * code : 2000
     * message : 请求成功
     * data : {"user":{"password":"6f836341613d70b0f4b388066b1e7c0c5f63a1a9bb3bfbf3a712053960aced68f39576a783aa82ee","addTime":1527645237000,"userState":1,"level":0,"mobile":"18360981992","businessPwd":null,"id":274,"type":0,"account":"18360981992","userCode":"000000000274","sId":null}}
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
         * user : {"password":"6f836341613d70b0f4b388066b1e7c0c5f63a1a9bb3bfbf3a712053960aced68f39576a783aa82ee","addTime":1527645237000,"userState":1,"level":0,"mobile":"18360981992","businessPwd":null,"id":274,"type":0,"account":"18360981992","userCode":"000000000274","sId":null}
         */

        private UserBean user;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * password : 6f836341613d70b0f4b388066b1e7c0c5f63a1a9bb3bfbf3a712053960aced68f39576a783aa82ee
             * addTime : 1527645237000
             * userState : 1
             * level : 0
             * mobile : 18360981992
             * businessPwd : null
             * id : 274
             * type : 0
             * account : 18360981992
             * userCode : 000000000274
             * sId : null
             */

            private String password;
            private long addTime;
            private int userState;
            private int level;
            private String mobile;
            private Object businessPwd;
            private int id;
            private int type;
            private String account;
            private String userCode;
            private Object sId;

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public int getUserState() {
                return userState;
            }

            public void setUserState(int userState) {
                this.userState = userState;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public Object getBusinessPwd() {
                return businessPwd;
            }

            public void setBusinessPwd(Object businessPwd) {
                this.businessPwd = businessPwd;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public Object getSId() {
                return sId;
            }

            public void setSId(Object sId) {
                this.sId = sId;
            }
        }
    }
}
