package com.ifeng_tech.treasuryyitong.bean.login;

/**
 * Created by zzt on 2018/7/16.
 */

public class LoginNewBean {

    /**
     * code : 2000
     * message : 操作成功
     * data : {"userExtendedInfo":{"IsNoBusinessPwd":0,"email":"854479711@qq.com"},"user":{"id":367,"addTime":1530583035000,"mobile":"901ab7b0386ab198555d3d7d6bab02fccf1b3d0da85a1787d5d56bcf80f4ef1c327dc9049b3fb992","userName":"","account":"18360981992","userCode":"000000000367","userState":"1","agencyInfo":null}}
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
         * userExtendedInfo : {"IsNoBusinessPwd":0,"email":"854479711@qq.com"}
         * user : {"id":367,"addTime":1530583035000,"mobile":"901ab7b0386ab198555d3d7d6bab02fccf1b3d0da85a1787d5d56bcf80f4ef1c327dc9049b3fb992","userName":"","account":"18360981992","userCode":"000000000367","userState":"1","agencyInfo":null}
         */

        private UserExtendedInfoBean userExtendedInfo;
        private UserBean user;

        public UserExtendedInfoBean getUserExtendedInfo() {
            return userExtendedInfo;
        }

        public void setUserExtendedInfo(UserExtendedInfoBean userExtendedInfo) {
            this.userExtendedInfo = userExtendedInfo;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserExtendedInfoBean {
            /**
             * IsNoBusinessPwd : 0
             * email : 854479711@qq.com
             */

            private int IsNoBusinessPwd;
            private String email;

            public int getIsNoBusinessPwd() {
                return IsNoBusinessPwd;
            }

            public void setIsNoBusinessPwd(int IsNoBusinessPwd) {
                this.IsNoBusinessPwd = IsNoBusinessPwd;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }
        }

        public static class UserBean {
            /**
             * id : 367
             * addTime : 1530583035000
             * mobile : 901ab7b0386ab198555d3d7d6bab02fccf1b3d0da85a1787d5d56bcf80f4ef1c327dc9049b3fb992
             * userName :
             * account : 18360981992
             * userCode : 000000000367
             * userState : 1
             * agencyInfo : null
             */

            private int id;
            private long addTime;
            private String mobile;
            private String userName;
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

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
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
