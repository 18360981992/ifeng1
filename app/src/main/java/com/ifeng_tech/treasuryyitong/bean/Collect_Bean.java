package com.ifeng_tech.treasuryyitong.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzt on 2018/6/1.
 */

public class Collect_Bean implements Serializable{

    /**
     * code : 2000
     * message : 操作成功
     * data : {"pageInfo":{"pageNum":2,"pageSize":10,"totalPage":8,"totalNum":71,"resultNum":10},"list":[{"goodsImg":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511501437035","publishTime":1527177600000,"applyEndTime":1527436799000,"addTime":1527217225000,"goodsId":780,"residueAmount":0,"upsideFee":5,"agencyId":145,"agencyCode":"0058","mechanismId":"0058","applyBeginTime":1527177600000,"agencyName":"福利特寄卖平台","allAmount":50,"stage":6,"commodityCode":"001","collectCode":"C0001477795201805251100254220","id":132,"goodsCode":"001","goodsName":"黄小琥"},{"goodsImg":"0104980249-1.jpg","publishTime":1527177600000,"applyEndTime":1529510399000,"addTime":1527216207000,"goodsId":1303,"residueAmount":96,"upsideFee":1.1,"agencyId":145,"agencyCode":"0058","mechanismId":"0058","applyBeginTime":1527177600000,"agencyName":"福利特寄卖平台","allAmount":100,"stage":6,"commodityCode":"1049802490","collectCode":"C0001477795201805251043269767","id":131,"goodsCode":"1049802490","goodsName":"T49邮政运输"},{"goodsImg":null,"publishTime":1527177600000,"applyEndTime":1528732799000,"addTime":1527214675000,"goodsId":1419,"residueAmount":1521,"upsideFee":0.5,"agencyId":145,"agencyCode":"0058","mechanismId":"0058","applyBeginTime":1527177600000,"agencyName":"福利特寄卖平台","allAmount":1521,"stage":6,"commodityCode":"050701","collectCode":"C0001477795201805251017549587","id":130,"goodsCode":"050701","goodsName":"测试商品0507"},{"goodsImg":null,"publishTime":1527177600000,"applyEndTime":1527263999000,"addTime":1527214580000,"goodsId":1419,"residueAmount":12544,"upsideFee":0.5,"agencyId":145,"agencyCode":"0058","mechanismId":"0058","applyBeginTime":1527177600000,"agencyName":"福利特寄卖平台","allAmount":12544,"stage":6,"commodityCode":"050701","collectCode":"C0001477795201805251016202154","id":129,"goodsCode":"050701","goodsName":"测试商品0507"},{"goodsImg":null,"publishTime":1527177600000,"applyEndTime":1527263999000,"addTime":1527214530000,"goodsId":1419,"residueAmount":1000,"upsideFee":0.2,"agencyId":145,"agencyCode":"0058","mechanismId":"0058","applyBeginTime":1527177600000,"agencyName":"福利特寄卖平台","allAmount":1000,"stage":6,"commodityCode":"050701","collectCode":"C0001477795201805251015299842","id":128,"goodsCode":"050701","goodsName":"测试商品0507"},{"goodsImg":null,"publishTime":1527177600000,"applyEndTime":1527263999000,"addTime":1527214498000,"goodsId":1419,"residueAmount":858,"upsideFee":0.56,"agencyId":145,"agencyCode":"0058","mechanismId":"0058","applyBeginTime":1527177600000,"agencyName":"福利特寄卖平台","allAmount":858,"stage":0,"commodityCode":"050701","collectCode":"C0001477795201805251014581154","id":127,"goodsCode":"050701","goodsName":"测试商品0507"},{"goodsImg":null,"publishTime":1527177600000,"applyEndTime":1527263999000,"addTime":1527214186000,"goodsId":1419,"residueAmount":152,"upsideFee":0.5,"agencyId":145,"agencyCode":"0058","mechanismId":"0058","applyBeginTime":1527177600000,"agencyName":"福利特寄卖平台","allAmount":152,"stage":6,"commodityCode":"050701","collectCode":"C0001477795201805251009456513","id":126,"goodsCode":"050701","goodsName":"测试商品0507"},{"goodsImg":"","publishTime":1527177600000,"applyEndTime":1527263999000,"addTime":1527213440000,"goodsId":null,"residueAmount":100000,"upsideFee":0.5,"agencyId":null,"agencyCode":"0406","mechanismId":"0406","applyBeginTime":1527177600000,"agencyName":"","allAmount":100000,"stage":6,"commodityCode":"565758","collectCode":"C0001481482201805250957203697","id":125,"goodsCode":"565758","goodsName":""},{"goodsImg":null,"publishTime":1527091200000,"applyEndTime":1528041599000,"addTime":1527151288000,"goodsId":1419,"residueAmount":1422,"upsideFee":1,"agencyId":111,"agencyCode":"0046","mechanismId":"0046","applyBeginTime":1527091200000,"agencyName":"13121838011","allAmount":1423,"stage":6,"commodityCode":"050701","collectCode":"C0001477762201805241641284903","id":123,"goodsCode":"050701","goodsName":"测试商品0507"},{"goodsImg":"","publishTime":1527177600000,"applyEndTime":1527350399000,"addTime":1527149965000,"goodsId":null,"residueAmount":100,"upsideFee":0.5,"agencyId":null,"agencyCode":"104","mechanismId":"104","applyBeginTime":1527264000000,"agencyName":"","allAmount":100,"stage":5,"commodityCode":"05411","collectCode":"C0000048629201805241619245178","id":121,"goodsCode":"05411","goodsName":""}]}
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
         * pageInfo : {"pageNum":2,"pageSize":10,"totalPage":8,"totalNum":71,"resultNum":10}
         * list : [{"goodsImg":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511501437035","publishTime":1527177600000,"applyEndTime":1527436799000,"addTime":1527217225000,"goodsId":780,"residueAmount":0,"upsideFee":5,"agencyId":145,"agencyCode":"0058","mechanismId":"0058","applyBeginTime":1527177600000,"agencyName":"福利特寄卖平台","allAmount":50,"stage":6,"commodityCode":"001","collectCode":"C0001477795201805251100254220","id":132,"goodsCode":"001","goodsName":"黄小琥"},{"goodsImg":"0104980249-1.jpg","publishTime":1527177600000,"applyEndTime":1529510399000,"addTime":1527216207000,"goodsId":1303,"residueAmount":96,"upsideFee":1.1,"agencyId":145,"agencyCode":"0058","mechanismId":"0058","applyBeginTime":1527177600000,"agencyName":"福利特寄卖平台","allAmount":100,"stage":6,"commodityCode":"1049802490","collectCode":"C0001477795201805251043269767","id":131,"goodsCode":"1049802490","goodsName":"T49邮政运输"},{"goodsImg":null,"publishTime":1527177600000,"applyEndTime":1528732799000,"addTime":1527214675000,"goodsId":1419,"residueAmount":1521,"upsideFee":0.5,"agencyId":145,"agencyCode":"0058","mechanismId":"0058","applyBeginTime":1527177600000,"agencyName":"福利特寄卖平台","allAmount":1521,"stage":6,"commodityCode":"050701","collectCode":"C0001477795201805251017549587","id":130,"goodsCode":"050701","goodsName":"测试商品0507"},{"goodsImg":null,"publishTime":1527177600000,"applyEndTime":1527263999000,"addTime":1527214580000,"goodsId":1419,"residueAmount":12544,"upsideFee":0.5,"agencyId":145,"agencyCode":"0058","mechanismId":"0058","applyBeginTime":1527177600000,"agencyName":"福利特寄卖平台","allAmount":12544,"stage":6,"commodityCode":"050701","collectCode":"C0001477795201805251016202154","id":129,"goodsCode":"050701","goodsName":"测试商品0507"},{"goodsImg":null,"publishTime":1527177600000,"applyEndTime":1527263999000,"addTime":1527214530000,"goodsId":1419,"residueAmount":1000,"upsideFee":0.2,"agencyId":145,"agencyCode":"0058","mechanismId":"0058","applyBeginTime":1527177600000,"agencyName":"福利特寄卖平台","allAmount":1000,"stage":6,"commodityCode":"050701","collectCode":"C0001477795201805251015299842","id":128,"goodsCode":"050701","goodsName":"测试商品0507"},{"goodsImg":null,"publishTime":1527177600000,"applyEndTime":1527263999000,"addTime":1527214498000,"goodsId":1419,"residueAmount":858,"upsideFee":0.56,"agencyId":145,"agencyCode":"0058","mechanismId":"0058","applyBeginTime":1527177600000,"agencyName":"福利特寄卖平台","allAmount":858,"stage":0,"commodityCode":"050701","collectCode":"C0001477795201805251014581154","id":127,"goodsCode":"050701","goodsName":"测试商品0507"},{"goodsImg":null,"publishTime":1527177600000,"applyEndTime":1527263999000,"addTime":1527214186000,"goodsId":1419,"residueAmount":152,"upsideFee":0.5,"agencyId":145,"agencyCode":"0058","mechanismId":"0058","applyBeginTime":1527177600000,"agencyName":"福利特寄卖平台","allAmount":152,"stage":6,"commodityCode":"050701","collectCode":"C0001477795201805251009456513","id":126,"goodsCode":"050701","goodsName":"测试商品0507"},{"goodsImg":"","publishTime":1527177600000,"applyEndTime":1527263999000,"addTime":1527213440000,"goodsId":null,"residueAmount":100000,"upsideFee":0.5,"agencyId":null,"agencyCode":"0406","mechanismId":"0406","applyBeginTime":1527177600000,"agencyName":"","allAmount":100000,"stage":6,"commodityCode":"565758","collectCode":"C0001481482201805250957203697","id":125,"goodsCode":"565758","goodsName":""},{"goodsImg":null,"publishTime":1527091200000,"applyEndTime":1528041599000,"addTime":1527151288000,"goodsId":1419,"residueAmount":1422,"upsideFee":1,"agencyId":111,"agencyCode":"0046","mechanismId":"0046","applyBeginTime":1527091200000,"agencyName":"13121838011","allAmount":1423,"stage":6,"commodityCode":"050701","collectCode":"C0001477762201805241641284903","id":123,"goodsCode":"050701","goodsName":"测试商品0507"},{"goodsImg":"","publishTime":1527177600000,"applyEndTime":1527350399000,"addTime":1527149965000,"goodsId":null,"residueAmount":100,"upsideFee":0.5,"agencyId":null,"agencyCode":"104","mechanismId":"104","applyBeginTime":1527264000000,"agencyName":"","allAmount":100,"stage":5,"commodityCode":"05411","collectCode":"C0000048629201805241619245178","id":121,"goodsCode":"05411","goodsName":""}]
         */

        private PageInfoBean pageInfo;
        private List<ListBean> list;

        public PageInfoBean getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfoBean pageInfo) {
            this.pageInfo = pageInfo;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PageInfoBean implements Serializable{
            /**
             * pageNum : 2
             * pageSize : 10
             * totalPage : 8
             * totalNum : 71
             * resultNum : 10
             */

            private int pageNum;
            private int pageSize;
            private int totalPage;
            private int totalNum;
            private int resultNum;

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getTotalNum() {
                return totalNum;
            }

            public void setTotalNum(int totalNum) {
                this.totalNum = totalNum;
            }

            public int getResultNum() {
                return resultNum;
            }

            public void setResultNum(int resultNum) {
                this.resultNum = resultNum;
            }
        }

        public static class ListBean implements Serializable{
            /**
             * goodsImg : http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511501437035
             * publishTime : 1527177600000
             * applyEndTime : 1527436799000
             * addTime : 1527217225000
             * goodsId : 780
             * residueAmount : 0
             * upsideFee : 5
             * agencyId : 145
             * agencyCode : 0058
             * mechanismId : 0058
             * applyBeginTime : 1527177600000
             * agencyName : 福利特寄卖平台
             * allAmount : 50
             * stage : 6
             * commodityCode : 001
             * collectCode : C0001477795201805251100254220
             * id : 132
             * goodsCode : 001
             * goodsName : 黄小琥
             */

            private String goodsImg;
            private long publishTime;
            private long applyEndTime;
            private long addTime;
            private int goodsId;
            private int residueAmount;
            private double upsideFee;
            private int agencyId;
            private String agencyCode;
            private String mechanismId;
            private long applyBeginTime;
            private String agencyName;
            private int allAmount;
            private int stage;
            private String commodityCode;
            private String collectCode;
            private int id;
            private String goodsCode;
            private String goodsName;

            public String getGoodsImg() {
                return goodsImg;
            }

            public void setGoodsImg(String goodsImg) {
                this.goodsImg = goodsImg;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
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

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public int getResidueAmount() {
                return residueAmount;
            }

            public void setResidueAmount(int residueAmount) {
                this.residueAmount = residueAmount;
            }

            public double getUpsideFee() {
                return upsideFee;
            }

            public void setUpsideFee(double upsideFee) {
                this.upsideFee = upsideFee;
            }

            public int getAgencyId() {
                return agencyId;
            }

            public void setAgencyId(int agencyId) {
                this.agencyId = agencyId;
            }

            public String getAgencyCode() {
                return agencyCode;
            }

            public void setAgencyCode(String agencyCode) {
                this.agencyCode = agencyCode;
            }

            public String getMechanismId() {
                return mechanismId;
            }

            public void setMechanismId(String mechanismId) {
                this.mechanismId = mechanismId;
            }

            public long getApplyBeginTime() {
                return applyBeginTime;
            }

            public void setApplyBeginTime(long applyBeginTime) {
                this.applyBeginTime = applyBeginTime;
            }

            public String getAgencyName() {
                return agencyName;
            }

            public void setAgencyName(String agencyName) {
                this.agencyName = agencyName;
            }

            public int getAllAmount() {
                return allAmount;
            }

            public void setAllAmount(int allAmount) {
                this.allAmount = allAmount;
            }

            public int getStage() {
                return stage;
            }

            public void setStage(int stage) {
                this.stage = stage;
            }

            public String getCommodityCode() {
                return commodityCode;
            }

            public void setCommodityCode(String commodityCode) {
                this.commodityCode = commodityCode;
            }

            public String getCollectCode() {
                return collectCode;
            }

            public void setCollectCode(String collectCode) {
                this.collectCode = collectCode;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGoodsCode() {
                return goodsCode;
            }

            public void setGoodsCode(String goodsCode) {
                this.goodsCode = goodsCode;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }
        }
    }
}
