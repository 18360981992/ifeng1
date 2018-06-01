package com.ifeng_tech.treasuryyitong.bean;

/**
 * Created by zzt on 2018/6/1.
 *
 *  转赠手续费
 */

public class TransferFee_Bean {

    /**
     * code : 2000
     * message : 操作成功
     * data : {"commonFeeRate":0.55,"TransferFee":6.4,"availableQty":"102"}
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
         * commonFeeRate : 0.55
         * TransferFee : 6.4
         * availableQty : 102
         */

        private double commonFeeRate;
        private double TransferFee;
        private String availableQty;

        public double getCommonFeeRate() {
            return commonFeeRate;
        }

        public void setCommonFeeRate(double commonFeeRate) {
            this.commonFeeRate = commonFeeRate;
        }

        public double getTransferFee() {
            return TransferFee;
        }

        public void setTransferFee(double TransferFee) {
            this.TransferFee = TransferFee;
        }

        public String getAvailableQty() {
            return availableQty;
        }

        public void setAvailableQty(String availableQty) {
            this.availableQty = availableQty;
        }
    }
}
