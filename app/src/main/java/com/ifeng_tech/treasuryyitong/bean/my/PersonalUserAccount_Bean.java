package com.ifeng_tech.treasuryyitong.bean.my;

/**
 * Created by zzt on 2018/5/31.
 */

public class PersonalUserAccount_Bean {


    /**
     * code : 2000
     * message : 操作成功
     * data : {"accountInfo":{"imgUrl":"","Verified":3,"IsNoBusinessPwd":0,"frozenFunds":0,"balance":0,"holdQty":0,"withdrawMin":50,"desirableFunds":0,"arrearage":0,"withdrawMax":0,"email":""},"user":{"id":274,"addTime":1527645237000,"mobile":"18360981992","userName":null,"account":"18360981992","userCode":"000000000274","userState":"1","agencyInfo":null}}
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
         * accountInfo : {"imgUrl":"","Verified":3,"IsNoBusinessPwd":0,"frozenFunds":0,"balance":0,"holdQty":0,"withdrawMin":50,"desirableFunds":0,"arrearage":0,"withdrawMax":0,"email":""}
         * user : {"id":274,"addTime":1527645237000,"mobile":"18360981992","userName":null,"account":"18360981992","userCode":"000000000274","userState":"1","agencyInfo":null}
         */

        private AccountInfoBean accountInfo;
        private UserBean user;

        public AccountInfoBean getAccountInfo() {
            return accountInfo;
        }

        public void setAccountInfo(AccountInfoBean accountInfo) {
            this.accountInfo = accountInfo;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class AccountInfoBean {
            /**
             * imgUrl :
             * Verified : 3
             * IsNoBusinessPwd : 0
             * frozenFunds : 0
             * balance : 0
             * holdQty : 0
             * withdrawMin : 50
             * desirableFunds : 0
             * arrearage : 0
             * withdrawMax : 0
             * email :
             */

            private String imgUrl;
            private int Verified;
            private int IsNoBusinessPwd;
            private double frozenFunds;
            private double balance;
            private double holdQty;
            private double withdrawMin;
            private double desirableFunds;
            private double arrearage;
            private double withdrawMax;
            private String email;

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public int getVerified() {
                return Verified;
            }

            public void setVerified(int Verified) {
                this.Verified = Verified;
            }

            public int getIsNoBusinessPwd() {
                return IsNoBusinessPwd;
            }

            public void setIsNoBusinessPwd(int IsNoBusinessPwd) {
                this.IsNoBusinessPwd = IsNoBusinessPwd;
            }

            public double getFrozenFunds() {
                return frozenFunds;
            }

            public void setFrozenFunds(double frozenFunds) {
                this.frozenFunds = frozenFunds;
            }

            public double getBalance() {
                return balance;
            }

            public void setBalance(double balance) {
                this.balance = balance;
            }

            public double getHoldQty() {
                return holdQty;
            }

            public void setHoldQty(double holdQty) {
                this.holdQty = holdQty;
            }

            public double getWithdrawMin() {
                return withdrawMin;
            }

            public void setWithdrawMin(double withdrawMin) {
                this.withdrawMin = withdrawMin;
            }

            public double getDesirableFunds() {
                return desirableFunds;
            }

            public void setDesirableFunds(double desirableFunds) {
                this.desirableFunds = desirableFunds;
            }

            public double getArrearage() {
                return arrearage;
            }

            public void setArrearage(double arrearage) {
                this.arrearage = arrearage;
            }

            public double getWithdrawMax() {
                return withdrawMax;
            }

            public void setWithdrawMax(double withdrawMax) {
                this.withdrawMax = withdrawMax;
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
             * id : 274
             * addTime : 1527645237000
             * mobile : 18360981992
             * userName : null
             * account : 18360981992
             * userCode : 000000000274
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
