package com.ifeng_tech.treasuryyitong.bean.cangpin;

import java.util.List;

/**
 * Created by zzt on 2018/6/7.
 */

public class Collection_Directory_Detail_Bean {


    /**
     * code : 2000
     * message : 操作成功
     * data : {"commodity":{"commodityLink":["http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070030.jpg"],"commodityDescription":"{\"规格\":\"220mm*110mm\",\"发行量\":\"200000枚\",\"售价\":\"1.90元\"}","commodityDetails":"2007年1月28日发行,全套1枚,（1-1）贴2007-2全套1枚邮票 销首日纪念邮戳,首日封、戳设计者:孟洁","commodityCode":"3010070030","id":839,"commodityName":"2007-2《第六届亚洲冬季运动会》纪念邮票"},"description":{"售价":"1.90元","规格":"220mm*110mm","发行量":"200000枚"},"releventCommodity":[{"commodityLink":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070100.jpg","addTime":1515572834000,"firstCategoryId":null,"commodityDescribe":null,"commCode":null,"deleteStatus":false,"commodityDetails":null,"commodityCode":"3010070100","commName":null,"id":846,"state":true,"categoryId":67,"isHot":false,"commodityName":"2007-8《舞龙舞狮》（中国\u2014\u2014印度尼西亚联合发行）特种邮票"},{"commodityLink":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070040.jpg","addTime":1515572834000,"firstCategoryId":null,"commodityDescribe":null,"commCode":null,"deleteStatus":false,"commodityDetails":null,"commodityCode":"3010070040","commName":null,"id":840,"state":true,"categoryId":67,"isHot":false,"commodityName":"2007-3《石湾陶瓷》特种邮票"},{"commodityLink":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/formal/3010070270.jpg","addTime":1516861566000,"firstCategoryId":null,"commodityDescribe":null,"commCode":null,"deleteStatus":false,"commodityDetails":null,"commodityCode":"3010070270","commName":null,"id":874,"state":true,"categoryId":67,"isHot":false,"commodityName":"2007-24《金湖》特种邮票"},{"commodityLink":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070090.jpg","addTime":1515572834000,"firstCategoryId":null,"commodityDescribe":null,"commCode":null,"deleteStatus":false,"commodityDetails":null,"commodityCode":"3010070090","commName":null,"id":845,"state":true,"categoryId":67,"isHot":false,"commodityName":"2007-7《扬州园林》特种邮票"},{"commodityLink":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/formal/3010070250.jpg","addTime":1516861566000,"firstCategoryId":null,"commodityDescribe":null,"commCode":null,"deleteStatus":false,"commodityDetails":null,"commodityCode":"3010070250","commName":null,"id":872,"state":true,"categoryId":67,"isHot":false,"commodityName":"2007-22《第二十九届奥林匹克运动会\u2014\u2014运动项目》（二）纪念邮票"}],"descriptionList":[{"name":"规格","description":"220mm*110mm"},{"name":"发行量","description":"200000枚"},{"name":"售价","description":"1.90元"}],"values":[]}
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
         * commodity : {"commodityLink":["http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070030.jpg"],"commodityDescription":"{\"规格\":\"220mm*110mm\",\"发行量\":\"200000枚\",\"售价\":\"1.90元\"}","commodityDetails":"2007年1月28日发行,全套1枚,（1-1）贴2007-2全套1枚邮票 销首日纪念邮戳,首日封、戳设计者:孟洁","commodityCode":"3010070030","id":839,"commodityName":"2007-2《第六届亚洲冬季运动会》纪念邮票"}
         * description : {"售价":"1.90元","规格":"220mm*110mm","发行量":"200000枚"}
         * releventCommodity : [{"commodityLink":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070100.jpg","addTime":1515572834000,"firstCategoryId":null,"commodityDescribe":null,"commCode":null,"deleteStatus":false,"commodityDetails":null,"commodityCode":"3010070100","commName":null,"id":846,"state":true,"categoryId":67,"isHot":false,"commodityName":"2007-8《舞龙舞狮》（中国\u2014\u2014印度尼西亚联合发行）特种邮票"},{"commodityLink":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070040.jpg","addTime":1515572834000,"firstCategoryId":null,"commodityDescribe":null,"commCode":null,"deleteStatus":false,"commodityDetails":null,"commodityCode":"3010070040","commName":null,"id":840,"state":true,"categoryId":67,"isHot":false,"commodityName":"2007-3《石湾陶瓷》特种邮票"},{"commodityLink":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/formal/3010070270.jpg","addTime":1516861566000,"firstCategoryId":null,"commodityDescribe":null,"commCode":null,"deleteStatus":false,"commodityDetails":null,"commodityCode":"3010070270","commName":null,"id":874,"state":true,"categoryId":67,"isHot":false,"commodityName":"2007-24《金湖》特种邮票"},{"commodityLink":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070090.jpg","addTime":1515572834000,"firstCategoryId":null,"commodityDescribe":null,"commCode":null,"deleteStatus":false,"commodityDetails":null,"commodityCode":"3010070090","commName":null,"id":845,"state":true,"categoryId":67,"isHot":false,"commodityName":"2007-7《扬州园林》特种邮票"},{"commodityLink":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/formal/3010070250.jpg","addTime":1516861566000,"firstCategoryId":null,"commodityDescribe":null,"commCode":null,"deleteStatus":false,"commodityDetails":null,"commodityCode":"3010070250","commName":null,"id":872,"state":true,"categoryId":67,"isHot":false,"commodityName":"2007-22《第二十九届奥林匹克运动会\u2014\u2014运动项目》（二）纪念邮票"}]
         * descriptionList : [{"name":"规格","description":"220mm*110mm"},{"name":"发行量","description":"200000枚"},{"name":"售价","description":"1.90元"}]
         * values : []
         */

        private CommodityBean commodity;
        private DescriptionBean description;
        private List<ReleventCommodityBean> releventCommodity;
        private List<DescriptionListBean> descriptionList;
        private List<?> values;

        public CommodityBean getCommodity() {
            return commodity;
        }

        public void setCommodity(CommodityBean commodity) {
            this.commodity = commodity;
        }

        public DescriptionBean getDescription() {
            return description;
        }

        public void setDescription(DescriptionBean description) {
            this.description = description;
        }

        public List<ReleventCommodityBean> getReleventCommodity() {
            return releventCommodity;
        }

        public void setReleventCommodity(List<ReleventCommodityBean> releventCommodity) {
            this.releventCommodity = releventCommodity;
        }

        public List<DescriptionListBean> getDescriptionList() {
            return descriptionList;
        }

        public void setDescriptionList(List<DescriptionListBean> descriptionList) {
            this.descriptionList = descriptionList;
        }

        public List<?> getValues() {
            return values;
        }

        public void setValues(List<?> values) {
            this.values = values;
        }

        public static class CommodityBean {
            /**
             * commodityLink : ["http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070030.jpg"]
             * commodityDescription : {"规格":"220mm*110mm","发行量":"200000枚","售价":"1.90元"}
             * commodityDetails : 2007年1月28日发行,全套1枚,（1-1）贴2007-2全套1枚邮票 销首日纪念邮戳,首日封、戳设计者:孟洁
             * commodityCode : 3010070030
             * id : 839
             * commodityName : 2007-2《第六届亚洲冬季运动会》纪念邮票
             */

            private String commodityDescription;
            private String commodityDetails;
            private String commodityCode;
            private int id;
            private String commodityName;
            private List<String> commodityLink;

            public String getCommodityDescription() {
                return commodityDescription;
            }

            public void setCommodityDescription(String commodityDescription) {
                this.commodityDescription = commodityDescription;
            }

            public String getCommodityDetails() {
                return commodityDetails;
            }

            public void setCommodityDetails(String commodityDetails) {
                this.commodityDetails = commodityDetails;
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

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public List<String> getCommodityLink() {
                return commodityLink;
            }

            public void setCommodityLink(List<String> commodityLink) {
                this.commodityLink = commodityLink;
            }
        }

        public static class DescriptionBean {
            /**
             * 售价 : 1.90元
             * 规格 : 220mm*110mm
             * 发行量 : 200000枚
             */

            private String 售价;
            private String 规格;
            private String 发行量;

            public String get售价() {
                return 售价;
            }

            public void set售价(String 售价) {
                this.售价 = 售价;
            }

            public String get规格() {
                return 规格;
            }

            public void set规格(String 规格) {
                this.规格 = 规格;
            }

            public String get发行量() {
                return 发行量;
            }

            public void set发行量(String 发行量) {
                this.发行量 = 发行量;
            }
        }

        public static class ReleventCommodityBean {
            /**
             * commodityLink : http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070100.jpg
             * addTime : 1515572834000
             * firstCategoryId : null
             * commodityDescribe : null
             * commCode : null
             * deleteStatus : false
             * commodityDetails : null
             * commodityCode : 3010070100
             * commName : null
             * id : 846
             * state : true
             * categoryId : 67
             * isHot : false
             * commodityName : 2007-8《舞龙舞狮》（中国——印度尼西亚联合发行）特种邮票
             */

            private String commodityLink;
            private long addTime;
            private Object firstCategoryId;
            private Object commodityDescribe;
            private Object commCode;
            private boolean deleteStatus;
            private Object commodityDetails;
            private String commodityCode;
            private Object commName;
            private int id;
            private boolean state;
            private int categoryId;
            private boolean isHot;
            private String commodityName;

            public String getCommodityLink() {
                return commodityLink;
            }

            public void setCommodityLink(String commodityLink) {
                this.commodityLink = commodityLink;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public Object getFirstCategoryId() {
                return firstCategoryId;
            }

            public void setFirstCategoryId(Object firstCategoryId) {
                this.firstCategoryId = firstCategoryId;
            }

            public Object getCommodityDescribe() {
                return commodityDescribe;
            }

            public void setCommodityDescribe(Object commodityDescribe) {
                this.commodityDescribe = commodityDescribe;
            }

            public Object getCommCode() {
                return commCode;
            }

            public void setCommCode(Object commCode) {
                this.commCode = commCode;
            }

            public boolean isDeleteStatus() {
                return deleteStatus;
            }

            public void setDeleteStatus(boolean deleteStatus) {
                this.deleteStatus = deleteStatus;
            }

            public Object getCommodityDetails() {
                return commodityDetails;
            }

            public void setCommodityDetails(Object commodityDetails) {
                this.commodityDetails = commodityDetails;
            }

            public String getCommodityCode() {
                return commodityCode;
            }

            public void setCommodityCode(String commodityCode) {
                this.commodityCode = commodityCode;
            }

            public Object getCommName() {
                return commName;
            }

            public void setCommName(Object commName) {
                this.commName = commName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public boolean isState() {
                return state;
            }

            public void setState(boolean state) {
                this.state = state;
            }

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public boolean isIsHot() {
                return isHot;
            }

            public void setIsHot(boolean isHot) {
                this.isHot = isHot;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }
        }

        public static class DescriptionListBean {
            /**
             * name : 规格
             * description : 220mm*110mm
             */

            private String name;
            private String description;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }
}
