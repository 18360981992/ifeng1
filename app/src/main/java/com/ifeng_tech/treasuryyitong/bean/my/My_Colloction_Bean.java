package com.ifeng_tech.treasuryyitong.bean.my;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzt on 2018/5/31.
 */

public class My_Colloction_Bean implements Serializable{

    /**
     * code : 2000
     * message : 操作成功
     * data : {"pageInfo":{"startRow":1,"lastPage":4,"navigatepageNums":[1,2,3,4],"prePage":0,"hasNextPage":true,"nextPage":2,"pageSize":5,"orderBy":null,"endRow":5,"list":[{"insurance":null,"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070120.jpg","applyEndTime":"2018-06-01","trusteeTime":"2018-06-02","addTime":"2018-05-31 10:42:44.0","goodsId":836,"applyBeginTime":"2018-05-31","pmORam":"am","number":500,"minNumber":null,"inhouseFee":null,"deleteStatus":"0","price":null,"commodityCode":null,"id":141,"state":"ingno","beginTime":null,"applyTime":null,"goodsName":"2007-10《中国话剧诞生一百周年》纪念邮票11","appraisalFee":0.5,"address":"bj","orderNo":"0000000123201805311042443767","passnumber":null,"pictureUrl":null,"count":null,"articleId":null,"commodityId":836,"collectionGuidePrice":null,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":null,"endTime":null,"goodsCode":"3010070130","commodityName":null},{"insurance":null,"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070030.jpg","applyEndTime":"2018-06-01","trusteeTime":"2018-06-04","addTime":"2018-05-31 10:42:31.0","goodsId":839,"applyBeginTime":"2018-05-31","pmORam":"am","number":100,"minNumber":null,"inhouseFee":null,"deleteStatus":"0","price":null,"commodityCode":null,"id":140,"state":"ingok","beginTime":null,"applyTime":null,"goodsName":"2007-2《第六届亚洲冬季运动会》纪念邮票","appraisalFee":0.4,"address":"bj","orderNo":"0000000123201805311042317997","passnumber":null,"pictureUrl":null,"count":null,"articleId":null,"commodityId":839,"collectionGuidePrice":null,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":null,"endTime":null,"goodsCode":"3010070030","commodityName":null},{"insurance":null,"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070040.jpg","applyEndTime":"2018-06-01","trusteeTime":"2018-06-06","addTime":"2018-05-31 10:42:18.0","goodsId":840,"applyBeginTime":"2018-05-31","pmORam":"am","number":300,"minNumber":null,"inhouseFee":null,"deleteStatus":"0","price":null,"commodityCode":null,"id":139,"state":"ingok","beginTime":null,"applyTime":null,"goodsName":"2007-3《石湾陶瓷》特种邮票","appraisalFee":20,"address":"bj","orderNo":"0000000123201805311042189150","passnumber":null,"pictureUrl":null,"count":null,"articleId":null,"commodityId":840,"collectionGuidePrice":null,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":null,"endTime":null,"goodsCode":"3010070040","commodityName":null},{"insurance":null,"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070120.jpg","applyEndTime":"2018-06-02","trusteeTime":"2018-06-08","addTime":"2018-05-31 10:42:02.0","goodsId":836,"applyBeginTime":"2018-05-31","pmORam":"am","number":500,"minNumber":null,"inhouseFee":null,"deleteStatus":"0","price":null,"commodityCode":null,"id":138,"state":"ingok","beginTime":null,"applyTime":null,"goodsName":"2007-10《中国话剧诞生一百周年》纪念邮票11","appraisalFee":0.5,"address":"北京福利特收藏品市场","orderNo":"0000000123201805311042025715","passnumber":null,"pictureUrl":null,"count":null,"articleId":null,"commodityId":836,"collectionGuidePrice":null,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":null,"endTime":null,"goodsCode":"3010070130","commodityName":null},{"insurance":null,"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1079900020-1.jpg","applyEndTime":"2018-06-02","trusteeTime":"2018-06-10","addTime":"2018-05-31 10:41:45.0","goodsId":1364,"applyBeginTime":"2018-05-31","pmORam":"am","number":500,"minNumber":null,"inhouseFee":null,"deleteStatus":"0","price":null,"commodityCode":null,"id":137,"state":"ingok","beginTime":null,"applyTime":null,"goodsName":"普26民居","appraisalFee":17.2,"address":"北京福利特收藏品市场","orderNo":"0000000123201805311041452106","passnumber":null,"pictureUrl":null,"count":null,"articleId":null,"commodityId":1364,"collectionGuidePrice":null,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":null,"endTime":null,"goodsCode":"1079900020","commodityName":null}],"pageNum":1,"navigatePages":8,"total":19,"pages":4,"size":5,"firstPage":1,"isLastPage":false,"hasPreviousPage":false,"isFirstPage":true}}
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
         * pageInfo : {"startRow":1,"lastPage":4,"navigatepageNums":[1,2,3,4],"prePage":0,"hasNextPage":true,"nextPage":2,"pageSize":5,"orderBy":null,"endRow":5,"list":[{"insurance":null,"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070120.jpg","applyEndTime":"2018-06-01","trusteeTime":"2018-06-02","addTime":"2018-05-31 10:42:44.0","goodsId":836,"applyBeginTime":"2018-05-31","pmORam":"am","number":500,"minNumber":null,"inhouseFee":null,"deleteStatus":"0","price":null,"commodityCode":null,"id":141,"state":"ingno","beginTime":null,"applyTime":null,"goodsName":"2007-10《中国话剧诞生一百周年》纪念邮票11","appraisalFee":0.5,"address":"bj","orderNo":"0000000123201805311042443767","passnumber":null,"pictureUrl":null,"count":null,"articleId":null,"commodityId":836,"collectionGuidePrice":null,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":null,"endTime":null,"goodsCode":"3010070130","commodityName":null},{"insurance":null,"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070030.jpg","applyEndTime":"2018-06-01","trusteeTime":"2018-06-04","addTime":"2018-05-31 10:42:31.0","goodsId":839,"applyBeginTime":"2018-05-31","pmORam":"am","number":100,"minNumber":null,"inhouseFee":null,"deleteStatus":"0","price":null,"commodityCode":null,"id":140,"state":"ingok","beginTime":null,"applyTime":null,"goodsName":"2007-2《第六届亚洲冬季运动会》纪念邮票","appraisalFee":0.4,"address":"bj","orderNo":"0000000123201805311042317997","passnumber":null,"pictureUrl":null,"count":null,"articleId":null,"commodityId":839,"collectionGuidePrice":null,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":null,"endTime":null,"goodsCode":"3010070030","commodityName":null},{"insurance":null,"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070040.jpg","applyEndTime":"2018-06-01","trusteeTime":"2018-06-06","addTime":"2018-05-31 10:42:18.0","goodsId":840,"applyBeginTime":"2018-05-31","pmORam":"am","number":300,"minNumber":null,"inhouseFee":null,"deleteStatus":"0","price":null,"commodityCode":null,"id":139,"state":"ingok","beginTime":null,"applyTime":null,"goodsName":"2007-3《石湾陶瓷》特种邮票","appraisalFee":20,"address":"bj","orderNo":"0000000123201805311042189150","passnumber":null,"pictureUrl":null,"count":null,"articleId":null,"commodityId":840,"collectionGuidePrice":null,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":null,"endTime":null,"goodsCode":"3010070040","commodityName":null},{"insurance":null,"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070120.jpg","applyEndTime":"2018-06-02","trusteeTime":"2018-06-08","addTime":"2018-05-31 10:42:02.0","goodsId":836,"applyBeginTime":"2018-05-31","pmORam":"am","number":500,"minNumber":null,"inhouseFee":null,"deleteStatus":"0","price":null,"commodityCode":null,"id":138,"state":"ingok","beginTime":null,"applyTime":null,"goodsName":"2007-10《中国话剧诞生一百周年》纪念邮票11","appraisalFee":0.5,"address":"北京福利特收藏品市场","orderNo":"0000000123201805311042025715","passnumber":null,"pictureUrl":null,"count":null,"articleId":null,"commodityId":836,"collectionGuidePrice":null,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":null,"endTime":null,"goodsCode":"3010070130","commodityName":null},{"insurance":null,"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1079900020-1.jpg","applyEndTime":"2018-06-02","trusteeTime":"2018-06-10","addTime":"2018-05-31 10:41:45.0","goodsId":1364,"applyBeginTime":"2018-05-31","pmORam":"am","number":500,"minNumber":null,"inhouseFee":null,"deleteStatus":"0","price":null,"commodityCode":null,"id":137,"state":"ingok","beginTime":null,"applyTime":null,"goodsName":"普26民居","appraisalFee":17.2,"address":"北京福利特收藏品市场","orderNo":"0000000123201805311041452106","passnumber":null,"pictureUrl":null,"count":null,"articleId":null,"commodityId":1364,"collectionGuidePrice":null,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":null,"endTime":null,"goodsCode":"1079900020","commodityName":null}],"pageNum":1,"navigatePages":8,"total":19,"pages":4,"size":5,"firstPage":1,"isLastPage":false,"hasPreviousPage":false,"isFirstPage":true}
         */

        private PageInfoBean pageInfo;

        public PageInfoBean getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfoBean pageInfo) {
            this.pageInfo = pageInfo;
        }

        public static class PageInfoBean implements Serializable{
            /**
             * startRow : 1
             * lastPage : 4
             * navigatepageNums : [1,2,3,4]
             * prePage : 0
             * hasNextPage : true
             * nextPage : 2
             * pageSize : 5
             * orderBy : null
             * endRow : 5
             * list : [{"insurance":null,"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070120.jpg","applyEndTime":"2018-06-01","trusteeTime":"2018-06-02","addTime":"2018-05-31 10:42:44.0","goodsId":836,"applyBeginTime":"2018-05-31","pmORam":"am","number":500,"minNumber":null,"inhouseFee":null,"deleteStatus":"0","price":null,"commodityCode":null,"id":141,"state":"ingno","beginTime":null,"applyTime":null,"goodsName":"2007-10《中国话剧诞生一百周年》纪念邮票11","appraisalFee":0.5,"address":"bj","orderNo":"0000000123201805311042443767","passnumber":null,"pictureUrl":null,"count":null,"articleId":null,"commodityId":836,"collectionGuidePrice":null,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":null,"endTime":null,"goodsCode":"3010070130","commodityName":null},{"insurance":null,"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070030.jpg","applyEndTime":"2018-06-01","trusteeTime":"2018-06-04","addTime":"2018-05-31 10:42:31.0","goodsId":839,"applyBeginTime":"2018-05-31","pmORam":"am","number":100,"minNumber":null,"inhouseFee":null,"deleteStatus":"0","price":null,"commodityCode":null,"id":140,"state":"ingok","beginTime":null,"applyTime":null,"goodsName":"2007-2《第六届亚洲冬季运动会》纪念邮票","appraisalFee":0.4,"address":"bj","orderNo":"0000000123201805311042317997","passnumber":null,"pictureUrl":null,"count":null,"articleId":null,"commodityId":839,"collectionGuidePrice":null,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":null,"endTime":null,"goodsCode":"3010070030","commodityName":null},{"insurance":null,"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070040.jpg","applyEndTime":"2018-06-01","trusteeTime":"2018-06-06","addTime":"2018-05-31 10:42:18.0","goodsId":840,"applyBeginTime":"2018-05-31","pmORam":"am","number":300,"minNumber":null,"inhouseFee":null,"deleteStatus":"0","price":null,"commodityCode":null,"id":139,"state":"ingok","beginTime":null,"applyTime":null,"goodsName":"2007-3《石湾陶瓷》特种邮票","appraisalFee":20,"address":"bj","orderNo":"0000000123201805311042189150","passnumber":null,"pictureUrl":null,"count":null,"articleId":null,"commodityId":840,"collectionGuidePrice":null,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":null,"endTime":null,"goodsCode":"3010070040","commodityName":null},{"insurance":null,"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070120.jpg","applyEndTime":"2018-06-02","trusteeTime":"2018-06-08","addTime":"2018-05-31 10:42:02.0","goodsId":836,"applyBeginTime":"2018-05-31","pmORam":"am","number":500,"minNumber":null,"inhouseFee":null,"deleteStatus":"0","price":null,"commodityCode":null,"id":138,"state":"ingok","beginTime":null,"applyTime":null,"goodsName":"2007-10《中国话剧诞生一百周年》纪念邮票11","appraisalFee":0.5,"address":"北京福利特收藏品市场","orderNo":"0000000123201805311042025715","passnumber":null,"pictureUrl":null,"count":null,"articleId":null,"commodityId":836,"collectionGuidePrice":null,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":null,"endTime":null,"goodsCode":"3010070130","commodityName":null},{"insurance":null,"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1079900020-1.jpg","applyEndTime":"2018-06-02","trusteeTime":"2018-06-10","addTime":"2018-05-31 10:41:45.0","goodsId":1364,"applyBeginTime":"2018-05-31","pmORam":"am","number":500,"minNumber":null,"inhouseFee":null,"deleteStatus":"0","price":null,"commodityCode":null,"id":137,"state":"ingok","beginTime":null,"applyTime":null,"goodsName":"普26民居","appraisalFee":17.2,"address":"北京福利特收藏品市场","orderNo":"0000000123201805311041452106","passnumber":null,"pictureUrl":null,"count":null,"articleId":null,"commodityId":1364,"collectionGuidePrice":null,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":null,"endTime":null,"goodsCode":"1079900020","commodityName":null}]
             * pageNum : 1
             * navigatePages : 8
             * total : 19
             * pages : 4
             * size : 5
             * firstPage : 1
             * isLastPage : false
             * hasPreviousPage : false
             * isFirstPage : true
             */

            private int startRow;
            private int lastPage;
            private int prePage;
            private boolean hasNextPage;
            private int nextPage;
            private int pageSize;
            private Object orderBy;
            private int endRow;
            private int pageNum;
            private int navigatePages;
            private int total;
            private int pages;
            private int size;
            private int firstPage;
            private boolean isLastPage;
            private boolean hasPreviousPage;
            private boolean isFirstPage;
            private List<Integer> navigatepageNums;
            private List<ListBean> list;

            public int getStartRow() {
                return startRow;
            }

            public void setStartRow(int startRow) {
                this.startRow = startRow;
            }

            public int getLastPage() {
                return lastPage;
            }

            public void setLastPage(int lastPage) {
                this.lastPage = lastPage;
            }

            public int getPrePage() {
                return prePage;
            }

            public void setPrePage(int prePage) {
                this.prePage = prePage;
            }

            public boolean isHasNextPage() {
                return hasNextPage;
            }

            public void setHasNextPage(boolean hasNextPage) {
                this.hasNextPage = hasNextPage;
            }

            public int getNextPage() {
                return nextPage;
            }

            public void setNextPage(int nextPage) {
                this.nextPage = nextPage;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public Object getOrderBy() {
                return orderBy;
            }

            public void setOrderBy(Object orderBy) {
                this.orderBy = orderBy;
            }

            public int getEndRow() {
                return endRow;
            }

            public void setEndRow(int endRow) {
                this.endRow = endRow;
            }

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }

            public int getNavigatePages() {
                return navigatePages;
            }

            public void setNavigatePages(int navigatePages) {
                this.navigatePages = navigatePages;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public int getFirstPage() {
                return firstPage;
            }

            public void setFirstPage(int firstPage) {
                this.firstPage = firstPage;
            }

            public boolean isIsLastPage() {
                return isLastPage;
            }

            public void setIsLastPage(boolean isLastPage) {
                this.isLastPage = isLastPage;
            }

            public boolean isHasPreviousPage() {
                return hasPreviousPage;
            }

            public void setHasPreviousPage(boolean hasPreviousPage) {
                this.hasPreviousPage = hasPreviousPage;
            }

            public boolean isIsFirstPage() {
                return isFirstPage;
            }

            public void setIsFirstPage(boolean isFirstPage) {
                this.isFirstPage = isFirstPage;
            }

            public List<Integer> getNavigatepageNums() {
                return navigatepageNums;
            }

            public void setNavigatepageNums(List<Integer> navigatepageNums) {
                this.navigatepageNums = navigatepageNums;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean implements Serializable{
                /**
                 * insurance : null
                 * goodsImg : http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070120.jpg
                 * applyEndTime : 2018-06-01
                 * trusteeTime : 2018-06-02
                 * addTime : 2018-05-31 10:42:44.0
                 * goodsId : 836
                 * applyBeginTime : 2018-05-31
                 * pmORam : am
                 * number : 500
                 * minNumber : null
                 * inhouseFee : null
                 * deleteStatus : 0
                 * price : null
                 * commodityCode : null
                 * id : 141
                 * state : ingno
                 * beginTime : null
                 * applyTime : null
                 * goodsName : 2007-10《中国话剧诞生一百周年》纪念邮票11
                 * appraisalFee : 0.5
                 * address : bj
                 * orderNo : 0000000123201805311042443767
                 * passnumber : null
                 * pictureUrl : null
                 * count : null
                 * articleId : null
                 * commodityId : 836
                 * collectionGuidePrice : null
                 * fakepassrate : null
                 * warehousingFee : null
                 * unit : null
                 * firmId : null
                 * leaseFate : null
                 * endTime : null
                 * goodsCode : 3010070130
                 * commodityName : null
                 */

                private Object insurance;
                private String goodsImg;
                private String applyEndTime;
                private String trusteeTime;
                private String addTime;
                private int goodsId;
                private String applyBeginTime;
                private String pmORam;
                private int number;
                private Object minNumber;
                private Object inhouseFee;
                private String deleteStatus;
                private double price;
                private Object commodityCode;
                private int id;
                private String state;
                private long beginTime;
                private long applyTime;
                private String goodsName;
                private double appraisalFee;
                private String address;
                private String orderNo;
                private Object passnumber;
                private Object pictureUrl;
                private Object count;
                private Object articleId;
                private int commodityId;
                private Object collectionGuidePrice;
                private Object fakepassrate;
                private Object warehousingFee;
                private Object unit;
                private Object firmId;
                private int leaseFate;
                private long endTime;
                private String goodsCode;
                private Object commodityName;

                public Object getInsurance() {
                    return insurance;
                }

                public void setInsurance(Object insurance) {
                    this.insurance = insurance;
                }

                public String getGoodsImg() {
                    return goodsImg;
                }

                public void setGoodsImg(String goodsImg) {
                    this.goodsImg = goodsImg;
                }

                public String getApplyEndTime() {
                    return applyEndTime;
                }

                public void setApplyEndTime(String applyEndTime) {
                    this.applyEndTime = applyEndTime;
                }

                public String getTrusteeTime() {
                    return trusteeTime;
                }

                public void setTrusteeTime(String trusteeTime) {
                    this.trusteeTime = trusteeTime;
                }

                public String getAddTime() {
                    return addTime;
                }

                public void setAddTime(String addTime) {
                    this.addTime = addTime;
                }

                public int getGoodsId() {
                    return goodsId;
                }

                public void setGoodsId(int goodsId) {
                    this.goodsId = goodsId;
                }

                public String getApplyBeginTime() {
                    return applyBeginTime;
                }

                public void setApplyBeginTime(String applyBeginTime) {
                    this.applyBeginTime = applyBeginTime;
                }

                public String getPmORam() {
                    return pmORam;
                }

                public void setPmORam(String pmORam) {
                    this.pmORam = pmORam;
                }

                public int getNumber() {
                    return number;
                }

                public void setNumber(int number) {
                    this.number = number;
                }

                public Object getMinNumber() {
                    return minNumber;
                }

                public void setMinNumber(Object minNumber) {
                    this.minNumber = minNumber;
                }

                public Object getInhouseFee() {
                    return inhouseFee;
                }

                public void setInhouseFee(Object inhouseFee) {
                    this.inhouseFee = inhouseFee;
                }

                public String getDeleteStatus() {
                    return deleteStatus;
                }

                public void setDeleteStatus(String deleteStatus) {
                    this.deleteStatus = deleteStatus;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public Object getCommodityCode() {
                    return commodityCode;
                }

                public void setCommodityCode(Object commodityCode) {
                    this.commodityCode = commodityCode;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getState() {
                    return state;
                }

                public void setState(String state) {
                    this.state = state;
                }

                public long getBeginTime() {
                    return beginTime;
                }

                public void setBeginTime(long beginTime) {
                    this.beginTime = beginTime;
                }

                public long getApplyTime() {
                    return applyTime;
                }

                public void setApplyTime(long applyTime) {
                    this.applyTime = applyTime;
                }

                public String getGoodsName() {
                    return goodsName;
                }

                public void setGoodsName(String goodsName) {
                    this.goodsName = goodsName;
                }

                public double getAppraisalFee() {
                    return appraisalFee;
                }

                public void setAppraisalFee(double appraisalFee) {
                    this.appraisalFee = appraisalFee;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getOrderNo() {
                    return orderNo;
                }

                public void setOrderNo(String orderNo) {
                    this.orderNo = orderNo;
                }

                public Object getPassnumber() {
                    return passnumber;
                }

                public void setPassnumber(Object passnumber) {
                    this.passnumber = passnumber;
                }

                public Object getPictureUrl() {
                    return pictureUrl;
                }

                public void setPictureUrl(Object pictureUrl) {
                    this.pictureUrl = pictureUrl;
                }

                public Object getCount() {
                    return count;
                }

                public void setCount(Object count) {
                    this.count = count;
                }

                public Object getArticleId() {
                    return articleId;
                }

                public void setArticleId(Object articleId) {
                    this.articleId = articleId;
                }

                public int getCommodityId() {
                    return commodityId;
                }

                public void setCommodityId(int commodityId) {
                    this.commodityId = commodityId;
                }

                public Object getCollectionGuidePrice() {
                    return collectionGuidePrice;
                }

                public void setCollectionGuidePrice(Object collectionGuidePrice) {
                    this.collectionGuidePrice = collectionGuidePrice;
                }

                public Object getFakepassrate() {
                    return fakepassrate;
                }

                public void setFakepassrate(Object fakepassrate) {
                    this.fakepassrate = fakepassrate;
                }

                public Object getWarehousingFee() {
                    return warehousingFee;
                }

                public void setWarehousingFee(Object warehousingFee) {
                    this.warehousingFee = warehousingFee;
                }

                public Object getUnit() {
                    return unit;
                }

                public void setUnit(Object unit) {
                    this.unit = unit;
                }

                public Object getFirmId() {
                    return firmId;
                }

                public void setFirmId(Object firmId) {
                    this.firmId = firmId;
                }

                public int getLeaseFate() {
                    return leaseFate;
                }

                public void setLeaseFate(int leaseFate) {
                    this.leaseFate = leaseFate;
                }

                public long getEndTime() {
                    return endTime;
                }

                public void setEndTime(long endTime) {
                    this.endTime = endTime;
                }

                public String getGoodsCode() {
                    return goodsCode;
                }

                public void setGoodsCode(String goodsCode) {
                    this.goodsCode = goodsCode;
                }

                public Object getCommodityName() {
                    return commodityName;
                }

                public void setCommodityName(Object commodityName) {
                    this.commodityName = commodityName;
                }
            }
        }
    }
}
