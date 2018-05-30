package com.ifeng_tech.treasuryyitong.bean.login;

/**
 * Created by zzt on 2018/5/30.
 */

public class LoginBean {

    /**
     * code : 2000
     * message : 操作成功
     * data : {"IsNoBusinessPwd":0,"user":{"id":212,"addTime":1525918369000,"mobile":"15847209769","userName":null,"account":"15847209769","userCode":"000000000212","userState":"1","agencyInfo":null}}
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
         * IsNoBusinessPwd : 0
         * user : {"id":212,"addTime":1525918369000,"mobile":"15847209769","userName":null,"account":"15847209769","userCode":"000000000212","userState":"1","agencyInfo":null}
         */

        private int IsNoBusinessPwd;
        private UserBean user;

        public int getIsNoBusinessPwd() {
            return IsNoBusinessPwd;
        }

        public void setIsNoBusinessPwd(int IsNoBusinessPwd) {
            this.IsNoBusinessPwd = IsNoBusinessPwd;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * id : 212
             * addTime : 1525918369000
             * mobile : 15847209769
             * userName : null
             * account : 15847209769
             * userCode : 000000000212
             * userState : 1
             * agencyInfo : null
             */

            private int id;
            private long addTime;
            private String mobile;
            private Object userName;
            private String account;
            private String userCode;
            private String userState;
            private Object agencyInfo;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public Object getUserName() {
                return userName;
            }

            public void setUserName(Object userName) {
                this.userName = userName;
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

            public String getUserState() {
                return userState;
            }

            public void setUserState(String userState) {
                this.userState = userState;
            }

            public Object getAgencyInfo() {
                return agencyInfo;
            }

            public void setAgencyInfo(Object agencyInfo) {
                this.agencyInfo = agencyInfo;
            }
        }
    }
}
