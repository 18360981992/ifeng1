package com.ifeng_tech.treasuryyitong.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzt on 2018/6/15.
 */

public class SelectAdvertise_Bean implements Serializable{

    /**
     * code : 2000
     * message : 操作成功
     * data : {"list":[{"addTime":1500608305000,"imgeUrl":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1509602008789","imgeLink":"http://www.baidu.com","deleteStatus":false,"name":"首页banner轮播图","belongTo":0,"orderNum":1,"updateTime":1517380856000,"id":1,"position":1,"plan":null},{"addTime":1512526282000,"imgeUrl":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1512526276260","imgeLink":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1512526276260","deleteStatus":false,"name":"test","belongTo":0,"orderNum":2,"updateTime":1517809888000,"id":13,"position":1,"plan":null},{"addTime":1513060257000,"imgeUrl":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1513060249984","imgeLink":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1512526276260","deleteStatus":false,"name":"测试","belongTo":0,"orderNum":3,"updateTime":1517809842000,"id":15,"position":1,"plan":null},{"addTime":1513060274000,"imgeUrl":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1513060268315","imgeLink":"https://www.baidu.com/","deleteStatus":false,"name":"测试","belongTo":0,"orderNum":4,"updateTime":1529051266000,"id":16,"position":1,"plan":null},{"addTime":1514168682000,"imgeUrl":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1514168667807","imgeLink":"http://192.168.1.11:18084/","deleteStatus":false,"name":"测试","belongTo":0,"orderNum":5,"updateTime":1517809855000,"id":18,"position":1,"plan":null},{"addTime":1526957753000,"imgeUrl":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1526957726141","imgeLink":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1526957726141","deleteStatus":false,"name":"大武当","belongTo":0,"orderNum":6,"updateTime":null,"id":20,"position":1,"plan":null},{"addTime":1529025474000,"imgeUrl":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1529053162598","imgeLink":"http://baoku.wjybk.com","deleteStatus":false,"name":"广告","belongTo":0,"orderNum":7,"updateTime":1529053165000,"id":21,"position":1,"plan":null}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            /**
             * addTime : 1500608305000
             * imgeUrl : http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1509602008789
             * imgeLink : http://www.baidu.com
             * deleteStatus : false
             * name : 首页banner轮播图
             * belongTo : 0
             * orderNum : 1
             * updateTime : 1517380856000
             * id : 1
             * position : 1
             * plan : null
             */

            private long addTime;
            private String imgeUrl;
            private String imgeLink;
            private boolean deleteStatus;
            private String name;
            private int belongTo;
            private int orderNum;
            private long updateTime;
            private int id;
            private int position;
            private Object plan;

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public String getImgeUrl() {
                return imgeUrl;
            }

            public void setImgeUrl(String imgeUrl) {
                this.imgeUrl = imgeUrl;
            }

            public String getImgeLink() {
                return imgeLink;
            }

            public void setImgeLink(String imgeLink) {
                this.imgeLink = imgeLink;
            }

            public boolean isDeleteStatus() {
                return deleteStatus;
            }

            public void setDeleteStatus(boolean deleteStatus) {
                this.deleteStatus = deleteStatus;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getBelongTo() {
                return belongTo;
            }

            public void setBelongTo(int belongTo) {
                this.belongTo = belongTo;
            }

            public int getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(int orderNum) {
                this.orderNum = orderNum;
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

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            public Object getPlan() {
                return plan;
            }

            public void setPlan(Object plan) {
                this.plan = plan;
            }
        }
    }
}
