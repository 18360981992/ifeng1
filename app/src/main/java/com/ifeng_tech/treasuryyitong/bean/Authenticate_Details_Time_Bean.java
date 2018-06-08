package com.ifeng_tech.treasuryyitong.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzt on 2018/6/5.
 */

public class Authenticate_Details_Time_Bean implements Serializable{

    /**
     * code : 2000
     * message : 操作成功
     * data : {"count":1,"list":[{"weeks":"星期五","restNumber_pm":null,"addTime":null,"trusteeship_id":null,"pmoram_pm":null,"ids_pm":null,"pmoram_am":"am","curNumber":null,"pmORam":"am","ids_am":"110","number":null,"restNumber_am":"111","deleteStatus":null,"restNumber":"111","ids":"110","id":null,"orderDate":1530806400000}],"trusteeInfo":{"insurance":null,"applyEndTime":1530288000000,"addTime":1528103792000,"totalTrusteeFee":null,"applyBeginTime":1528041600000,"orderinfo":null,"number":1234,"minNumber":11,"inhouseFee":0.02,"deleteStatus":false,"freeTime":null,"commodityCode":null,"orderEndTime":1530806400000,"id":88,"passNumber":0,"appraisalFee":0.5,"publishTime":1528041600000,"address":"福利特","fakePassrate":0,"applyState":false,"articleId":null,"count":null,"commodityId":836,"collectionGuidePrice":50,"warehousingFee":null,"curNum":null,"leaseFate":20,"orderBeginTime":1530806400000}}
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

    public static class DataBean implements Serializable{
        /**
         * count : 1
         * list : [{"weeks":"星期五","restNumber_pm":null,"addTime":null,"trusteeship_id":null,"pmoram_pm":null,"ids_pm":null,"pmoram_am":"am","curNumber":null,"pmORam":"am","ids_am":"110","number":null,"restNumber_am":"111","deleteStatus":null,"restNumber":"111","ids":"110","id":null,"orderDate":1530806400000}]
         * trusteeInfo : {"insurance":null,"applyEndTime":1530288000000,"addTime":1528103792000,"totalTrusteeFee":null,"applyBeginTime":1528041600000,"orderinfo":null,"number":1234,"minNumber":11,"inhouseFee":0.02,"deleteStatus":false,"freeTime":null,"commodityCode":null,"orderEndTime":1530806400000,"id":88,"passNumber":0,"appraisalFee":0.5,"publishTime":1528041600000,"address":"福利特","fakePassrate":0,"applyState":false,"articleId":null,"count":null,"commodityId":836,"collectionGuidePrice":50,"warehousingFee":null,"curNum":null,"leaseFate":20,"orderBeginTime":1530806400000}
         */

        private int count;
        private TrusteeInfoBean trusteeInfo;
        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public TrusteeInfoBean getTrusteeInfo() {
            return trusteeInfo;
        }

        public void setTrusteeInfo(TrusteeInfoBean trusteeInfo) {
            this.trusteeInfo = trusteeInfo;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class TrusteeInfoBean implements Serializable{
            /**
             * insurance : null
             * applyEndTime : 1530288000000
             * addTime : 1528103792000
             * totalTrusteeFee : null
             * applyBeginTime : 1528041600000
             * orderinfo : null
             * number : 1234
             * minNumber : 11
             * inhouseFee : 0.02
             * deleteStatus : false
             * freeTime : null
             * commodityCode : null
             * orderEndTime : 1530806400000
             * id : 88
             * passNumber : 0
             * appraisalFee : 0.5
             * publishTime : 1528041600000
             * address : 福利特
             * fakePassrate : 0
             * applyState : false
             * articleId : null
             * count : null
             * commodityId : 836
             * collectionGuidePrice : 50
             * warehousingFee : null
             * curNum : null
             * leaseFate : 20
             * orderBeginTime : 1530806400000
             */

            private Object insurance;
            private long applyEndTime;
            private long addTime;
            private Object totalTrusteeFee;
            private long applyBeginTime;
            private Object orderinfo;
            private int number;
            private int minNumber;
            private double inhouseFee;
            private boolean deleteStatus;
            private Object freeTime;
            private Object commodityCode;
            private long orderEndTime;
            private int id;
            private int passNumber;
            private double appraisalFee;
            private long publishTime;
            private String address;
            private int fakePassrate;
            private boolean applyState;
            private Object articleId;
            private Object count;
            private int commodityId;
            private double collectionGuidePrice;
            private Object warehousingFee;
            private Object curNum;
            private int leaseFate;
            private long orderBeginTime;

            public Object getInsurance() {
                return insurance;
            }

            public void setInsurance(Object insurance) {
                this.insurance = insurance;
            }

            public long getApplyEndTime() {
                return applyEndTime;
            }

            public void setApplyEndTime(long applyEndTime) {
                this.applyEndTime = applyEndTime;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public Object getTotalTrusteeFee() {
                return totalTrusteeFee;
            }

            public void setTotalTrusteeFee(Object totalTrusteeFee) {
                this.totalTrusteeFee = totalTrusteeFee;
            }

            public long getApplyBeginTime() {
                return applyBeginTime;
            }

            public void setApplyBeginTime(long applyBeginTime) {
                this.applyBeginTime = applyBeginTime;
            }

            public Object getOrderinfo() {
                return orderinfo;
            }

            public void setOrderinfo(Object orderinfo) {
                this.orderinfo = orderinfo;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getMinNumber() {
                return minNumber;
            }

            public void setMinNumber(int minNumber) {
                this.minNumber = minNumber;
            }

            public double getInhouseFee() {
                return inhouseFee;
            }

            public void setInhouseFee(double inhouseFee) {
                this.inhouseFee = inhouseFee;
            }

            public boolean isDeleteStatus() {
                return deleteStatus;
            }

            public void setDeleteStatus(boolean deleteStatus) {
                this.deleteStatus = deleteStatus;
            }

            public Object getFreeTime() {
                return freeTime;
            }

            public void setFreeTime(Object freeTime) {
                this.freeTime = freeTime;
            }

            public Object getCommodityCode() {
                return commodityCode;
            }

            public void setCommodityCode(Object commodityCode) {
                this.commodityCode = commodityCode;
            }

            public long getOrderEndTime() {
                return orderEndTime;
            }

            public void setOrderEndTime(long orderEndTime) {
                this.orderEndTime = orderEndTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPassNumber() {
                return passNumber;
            }

            public void setPassNumber(int passNumber) {
                this.passNumber = passNumber;
            }

            public double getAppraisalFee() {
                return appraisalFee;
            }

            public void setAppraisalFee(double appraisalFee) {
                this.appraisalFee = appraisalFee;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getFakePassrate() {
                return fakePassrate;
            }

            public void setFakePassrate(int fakePassrate) {
                this.fakePassrate = fakePassrate;
            }

            public boolean isApplyState() {
                return applyState;
            }

            public void setApplyState(boolean applyState) {
                this.applyState = applyState;
            }

            public Object getArticleId() {
                return articleId;
            }

            public void setArticleId(Object articleId) {
                this.articleId = articleId;
            }

            public Object getCount() {
                return count;
            }

            public void setCount(Object count) {
                this.count = count;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public double getCollectionGuidePrice() {
                return collectionGuidePrice;
            }

            public void setCollectionGuidePrice(double collectionGuidePrice) {
                this.collectionGuidePrice = collectionGuidePrice;
            }

            public Object getWarehousingFee() {
                return warehousingFee;
            }

            public void setWarehousingFee(Object warehousingFee) {
                this.warehousingFee = warehousingFee;
            }

            public Object getCurNum() {
                return curNum;
            }

            public void setCurNum(Object curNum) {
                this.curNum = curNum;
            }

            public int getLeaseFate() {
                return leaseFate;
            }

            public void setLeaseFate(int leaseFate) {
                this.leaseFate = leaseFate;
            }

            public long getOrderBeginTime() {
                return orderBeginTime;
            }

            public void setOrderBeginTime(long orderBeginTime) {
                this.orderBeginTime = orderBeginTime;
            }
        }

        public static class ListBean implements Serializable{
            /**
             * weeks : 星期五
             * restNumber_pm : null
             * addTime : null
             * trusteeship_id : null
             * pmoram_pm : null
             * ids_pm : null
             * pmoram_am : am
             * curNumber : null
             * pmORam : am
             * ids_am : 110
             * number : null
             * restNumber_am : 111
             * deleteStatus : null
             * restNumber : 111
             * ids : 110
             * id : null
             * orderDate : 1530806400000
             */

            private String weeks;
            private String restNumber_pm;
            private Object addTime;
            private Object trusteeship_id;
            private Object pmoram_pm;
            private Object ids_pm;
            private String pmoram_am;
            private Object curNumber;
            private String pmORam;
            private String ids_am;
            private Object number;
            private String restNumber_am;
            private Object deleteStatus;
            private String restNumber;
            private String ids;
            private Object id;
            private long orderDate;

            public String getWeeks() {
                return weeks;
            }

            public void setWeeks(String weeks) {
                this.weeks = weeks;
            }

            public String getRestNumber_pm() {
                return restNumber_pm;
            }

            public void setRestNumber_pm(String restNumber_pm) {
                this.restNumber_pm = restNumber_pm;
            }

            public Object getAddTime() {
                return addTime;
            }

            public void setAddTime(Object addTime) {
                this.addTime = addTime;
            }

            public Object getTrusteeship_id() {
                return trusteeship_id;
            }

            public void setTrusteeship_id(Object trusteeship_id) {
                this.trusteeship_id = trusteeship_id;
            }

            public Object getPmoram_pm() {
                return pmoram_pm;
            }

            public void setPmoram_pm(Object pmoram_pm) {
                this.pmoram_pm = pmoram_pm;
            }

            public Object getIds_pm() {
                return ids_pm;
            }

            public void setIds_pm(Object ids_pm) {
                this.ids_pm = ids_pm;
            }

            public String getPmoram_am() {
                return pmoram_am;
            }

            public void setPmoram_am(String pmoram_am) {
                this.pmoram_am = pmoram_am;
            }

            public Object getCurNumber() {
                return curNumber;
            }

            public void setCurNumber(Object curNumber) {
                this.curNumber = curNumber;
            }

            public String getPmORam() {
                return pmORam;
            }

            public void setPmORam(String pmORam) {
                this.pmORam = pmORam;
            }

            public String getIds_am() {
                return ids_am;
            }

            public void setIds_am(String ids_am) {
                this.ids_am = ids_am;
            }

            public Object getNumber() {
                return number;
            }

            public void setNumber(Object number) {
                this.number = number;
            }

            public String getRestNumber_am() {
                return restNumber_am;
            }

            public void setRestNumber_am(String restNumber_am) {
                this.restNumber_am = restNumber_am;
            }

            public Object getDeleteStatus() {
                return deleteStatus;
            }

            public void setDeleteStatus(Object deleteStatus) {
                this.deleteStatus = deleteStatus;
            }

            public String getRestNumber() {
                return restNumber;
            }

            public void setRestNumber(String restNumber) {
                this.restNumber = restNumber;
            }

            public String getIds() {
                return ids;
            }

            public void setIds(String ids) {
                this.ids = ids;
            }

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public long getOrderDate() {
                return orderDate;
            }

            public void setOrderDate(long orderDate) {
                this.orderDate = orderDate;
            }
        }
    }
}
