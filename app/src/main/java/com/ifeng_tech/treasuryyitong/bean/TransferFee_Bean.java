package com.ifeng_tech.treasuryyitong.bean;

/**
 * Created by zzt on 2018/6/1.
 *
 *  转赠/提货 手续费
 */

public class TransferFee_Bean {


    /**
     * code : 2000
     * message : 操作成功
     * data : {"deliveryFee":4,"commonFeeRate":0.55,"TransferFee":6,"availableQty":"112"}
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
         * deliveryFee : 4
         * commonFeeRate : 0.55
         * TransferFee : 6
         * availableQty : 112
         */

        private double deliveryFee;   // 提货
        private double commonFeeRate;
        private double TransferFee;   // 转赠
        private String availableQty;

        public double getDeliveryFee() {
            return deliveryFee;
        }

        public void setDeliveryFee(double deliveryFee) {
            this.deliveryFee = deliveryFee;
        }

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
