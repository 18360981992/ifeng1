package com.ifeng_tech.treasuryyitong.bean.my;

/**
 * Created by zzt on 2018/5/31.
 */

public class PersonalUserAccount_Bean {


    /**
     * code : 2000
     * message : 操作成功
     * data : {"accountInfo":{"infoState":3,"IsNoBusinessPwd":0,"frozenFunds":396,"addTime":1529376657000,"holdQty":0,"desirableFunds":99999604,"arrearage":0,"withdrawMax":99999604,"userName":"王亚男","userId":3,"userCode":"000000000003","imgUrl":"","balance":100000000,"url1":"","url2":"","certificateNumber":"","withdrawMin":50,"id":199,"email":"","certificateType":"1","deleteState":1},"user":{"id":3,"addTime":1529376657000,"mobile":"18810209061","userName":null,"account":"王亚男","userCode":"000000000003","userState":"1","agencyInfo":null}}
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
         * accountInfo : {"infoState":3,"IsNoBusinessPwd":0,"frozenFunds":396,"addTime":1529376657000,"holdQty":0,"desirableFunds":99999604,"arrearage":0,"withdrawMax":99999604,"userName":"王亚男","userId":3,"userCode":"000000000003","imgUrl":"","balance":100000000,"url1":"","url2":"","certificateNumber":"","withdrawMin":50,"id":199,"email":"","certificateType":"1","deleteState":1}
         * user : {"id":3,"addTime":1529376657000,"mobile":"18810209061","userName":null,"account":"王亚男","userCode":"000000000003","userState":"1","agencyInfo":null}
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
             * infoState : 3   实名认证
             * IsNoBusinessPwd : 0   业务密码
             * frozenFunds : 396   冻结资金
             * addTime : 1529376657000
             * holdQty : 0    持仓总数
             * desirableFunds : 99999604
             * arrearage : 0
             * withdrawMax : 99999604
             * userName : 王亚男
             * userId : 3
             * userCode : 000000000003
             * imgUrl :
             * balance : 100000000
             * url1 :
             * url2 :
             * certificateNumber :
             * withdrawMin : 50
             * id : 199
             * email :
             * certificateType : 1
             * deleteState : 1
             */

            private int infoState;
            private int IsNoBusinessPwd;
            private double frozenFunds;
            private long addTime;
            private int holdQty;
            private double desirableFunds;
            private double arrearage;
            private double withdrawMax;
            private String userName;
            private int userId;
            private String userCode;
            private String imgUrl;
            private double balance;
            private String url1;
            private String url2;
            private String certificateNumber;
            private double withdrawMin;
            private int id;
            private String email;
            private String certificateType;
            private int deleteState;

            public int getInfoState() {
                return infoState;
            }

            public void setInfoState(int infoState) {
                this.infoState = infoState;
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

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public int getHoldQty() {
                return holdQty;
            }

            public void setHoldQty(int holdQty) {
                this.holdQty = holdQty;
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

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public double getBalance() {
                return balance;
            }

            public void setBalance(double balance) {
                this.balance = balance;
            }

            public String getUrl1() {
                return url1;
            }

            public void setUrl1(String url1) {
                this.url1 = url1;
            }

            public String getUrl2() {
                return url2;
            }

            public void setUrl2(String url2) {
                this.url2 = url2;
            }

            public String getCertificateNumber() {
                return certificateNumber;
            }

            public void setCertificateNumber(String certificateNumber) {
                this.certificateNumber = certificateNumber;
            }

            public double getWithdrawMin() {
                return withdrawMin;
            }

            public void setWithdrawMin(double withdrawMin) {
                this.withdrawMin = withdrawMin;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getCertificateType() {
                return certificateType;
            }

            public void setCertificateType(String certificateType) {
                this.certificateType = certificateType;
            }

            public int getDeleteState() {
                return deleteState;
            }

            public void setDeleteState(int deleteState) {
                this.deleteState = deleteState;
            }
        }

        public static class UserBean {
            /**
             * id : 3
             * addTime : 1529376657000
             * mobile : 18810209061
             * userName : null
             * account : 王亚男
             * userCode : 000000000003
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
