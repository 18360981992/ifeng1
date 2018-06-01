package com.ifeng_tech.treasuryyitong.bean.my;

/**
 * Created by zzt on 2018/5/31.
 */

public class UserBean {


    /**
     * code : 2000
     * message : 操作成功
     * data : {"imgUrl":"","Verified":1,"frozenFunds":5697.8,"balance":2999764.05,"holdQty":310,"desirableFunds":2994066.25,"arrearage":0,"userCode":"000000000214"}
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
         * imgUrl :
         * Verified : 1     1 表示已实名认证 0 表示未实名认证
         * frozenFunds : 5697.8
         * balance : 2999764.05
         * holdQty : 310
         * desirableFunds : 2994066.25
         * arrearage : 0
         * userCode : 000000000214
         */

        private String imgUrl;
        private int Verified;
        private double frozenFunds;
        private double balance;
        private int holdQty;
        private double desirableFunds;
        private int arrearage;
        private String userCode;

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

        public int getArrearage() {
            return arrearage;
        }

        public void setArrearage(int arrearage) {
            this.arrearage = arrearage;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }
    }
}
