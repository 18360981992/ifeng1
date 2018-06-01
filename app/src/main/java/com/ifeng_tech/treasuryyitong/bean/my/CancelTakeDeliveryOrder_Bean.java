package com.ifeng_tech.treasuryyitong.bean.my;

/**
 * Created by zzt on 2018/6/1.
 *
 *  提货单注销
 */

public class CancelTakeDeliveryOrder_Bean {

    /**
     * code : 2000
     * message : 操作成功
     * data : {"entity":{"depotName":null,"quantity":11,"deliveryQty":11,"addTime":1527063113000,"deliveryTime":1527436800000,"outQuantity":null,"accountType":null,"depotId":26,"updateTime":null,"commodityId":1374,"accountId":123,"outBillId":null,"deliveryFee":0,"delPassword":"c4ca4238a0b923820dcc509a6f75849b","billStage":2,"deleteStatus":false,"billId":"od00001231611532001111q30p2","commodityCode":"1070020030","id":173,"endTime":null}}
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
         * entity : {"depotName":null,"quantity":11,"deliveryQty":11,"addTime":1527063113000,"deliveryTime":1527436800000,"outQuantity":null,"accountType":null,"depotId":26,"updateTime":null,"commodityId":1374,"accountId":123,"outBillId":null,"deliveryFee":0,"delPassword":"c4ca4238a0b923820dcc509a6f75849b","billStage":2,"deleteStatus":false,"billId":"od00001231611532001111q30p2","commodityCode":"1070020030","id":173,"endTime":null}
         */

        private EntityBean entity;

        public EntityBean getEntity() {
            return entity;
        }

        public void setEntity(EntityBean entity) {
            this.entity = entity;
        }

        public static class EntityBean {
            /**
             * depotName : null
             * quantity : 11
             * deliveryQty : 11
             * addTime : 1527063113000
             * deliveryTime : 1527436800000
             * outQuantity : null
             * accountType : null
             * depotId : 26
             * updateTime : null
             * commodityId : 1374
             * accountId : 123
             * outBillId : null
             * deliveryFee : 0
             * delPassword : c4ca4238a0b923820dcc509a6f75849b
             * billStage : 2
             * deleteStatus : false
             * billId : od00001231611532001111q30p2
             * commodityCode : 1070020030
             * id : 173
             * endTime : null
             */

            private Object depotName;
            private int quantity;
            private int deliveryQty;
            private long addTime;
            private long deliveryTime;
            private Object outQuantity;
            private Object accountType;
            private int depotId;
            private Object updateTime;
            private int commodityId;
            private int accountId;
            private Object outBillId;
            private int deliveryFee;
            private String delPassword;
            private int billStage;
            private boolean deleteStatus;
            private String billId;
            private String commodityCode;
            private int id;
            private Object endTime;

            public Object getDepotName() {
                return depotName;
            }

            public void setDepotName(Object depotName) {
                this.depotName = depotName;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public int getDeliveryQty() {
                return deliveryQty;
            }

            public void setDeliveryQty(int deliveryQty) {
                this.deliveryQty = deliveryQty;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public long getDeliveryTime() {
                return deliveryTime;
            }

            public void setDeliveryTime(long deliveryTime) {
                this.deliveryTime = deliveryTime;
            }

            public Object getOutQuantity() {
                return outQuantity;
            }

            public void setOutQuantity(Object outQuantity) {
                this.outQuantity = outQuantity;
            }

            public Object getAccountType() {
                return accountType;
            }

            public void setAccountType(Object accountType) {
                this.accountType = accountType;
            }

            public int getDepotId() {
                return depotId;
            }

            public void setDepotId(int depotId) {
                this.depotId = depotId;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public int getAccountId() {
                return accountId;
            }

            public void setAccountId(int accountId) {
                this.accountId = accountId;
            }

            public Object getOutBillId() {
                return outBillId;
            }

            public void setOutBillId(Object outBillId) {
                this.outBillId = outBillId;
            }

            public int getDeliveryFee() {
                return deliveryFee;
            }

            public void setDeliveryFee(int deliveryFee) {
                this.deliveryFee = deliveryFee;
            }

            public String getDelPassword() {
                return delPassword;
            }

            public void setDelPassword(String delPassword) {
                this.delPassword = delPassword;
            }

            public int getBillStage() {
                return billStage;
            }

            public void setBillStage(int billStage) {
                this.billStage = billStage;
            }

            public boolean isDeleteStatus() {
                return deleteStatus;
            }

            public void setDeleteStatus(boolean deleteStatus) {
                this.deleteStatus = deleteStatus;
            }

            public String getBillId() {
                return billId;
            }

            public void setBillId(String billId) {
                this.billId = billId;
            }

            public String getCommodityCode() {
                return commodityCode;
            }

            public void setCommodityCode(String commodityCode) {
                this.commodityCode = commodityCode;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }
        }
    }
}
