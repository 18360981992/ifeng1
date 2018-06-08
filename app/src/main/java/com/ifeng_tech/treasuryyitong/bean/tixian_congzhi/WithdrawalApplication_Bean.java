package com.ifeng_tech.treasuryyitong.bean.tixian_congzhi;

import java.util.List;

/**
 * Created by zzt on 2018/6/7.
 */

public class WithdrawalApplication_Bean {

    /**
     * code : 2000
     * message : 请求成功
     * data : {"list":[{"arrearageInfo":null,"addTime":1526297784000,"balance":1494522.17,"frozenFunds":106944.63,"deleteStatus":false,"tradeFunds":0,"desirableFunds":1387257.54,"arrearage":0,"updateTime":1528355260000,"id":7,"userId":123,"userCode":null}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * arrearageInfo : null
             * addTime : 1526297784000
             * balance : 1494522.17
             * frozenFunds : 106944.63
             * deleteStatus : false
             * tradeFunds : 0
             * desirableFunds : 1387257.54
             * arrearage : 0
             * updateTime : 1528355260000
             * id : 7
             * userId : 123
             * userCode : null
             */

            private Object arrearageInfo;
            private long addTime;
            private double balance;
            private double frozenFunds;
            private boolean deleteStatus;
            private int tradeFunds;
            private double desirableFunds;
            private int arrearage;
            private long updateTime;
            private int id;
            private int userId;
            private Object userCode;

            public Object getArrearageInfo() {
                return arrearageInfo;
            }

            public void setArrearageInfo(Object arrearageInfo) {
                this.arrearageInfo = arrearageInfo;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public double getBalance() {
                return balance;
            }

            public void setBalance(double balance) {
                this.balance = balance;
            }

            public double getFrozenFunds() {
                return frozenFunds;
            }

            public void setFrozenFunds(double frozenFunds) {
                this.frozenFunds = frozenFunds;
            }

            public boolean isDeleteStatus() {
                return deleteStatus;
            }

            public void setDeleteStatus(boolean deleteStatus) {
                this.deleteStatus = deleteStatus;
            }

            public int getTradeFunds() {
                return tradeFunds;
            }

            public void setTradeFunds(int tradeFunds) {
                this.tradeFunds = tradeFunds;
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

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public Object getUserCode() {
                return userCode;
            }

            public void setUserCode(Object userCode) {
                this.userCode = userCode;
            }
        }
    }
}
