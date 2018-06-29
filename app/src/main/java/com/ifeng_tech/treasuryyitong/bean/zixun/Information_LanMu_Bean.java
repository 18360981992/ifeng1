package com.ifeng_tech.treasuryyitong.bean.zixun;

import java.util.List;

/**
 * Created by zzt on 2018/6/6.
 */

public class Information_LanMu_Bean {

    /**
     * code : 2000
     * message : 操作成功
     * data : {"pageInfo":{"pageNum":1,"pageSize":10,"totalPage":1,"totalNum":1,"resultNum":1},"list":[{"addTime":1526963771000,"articlelist":null,"level":1,"sublist":null,"deleteStatus":false,"name":"公告","subType":null,"id":194,"mainType":null,"parentId":147}]}
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
         * pageInfo : {"pageNum":1,"pageSize":10,"totalPage":1,"totalNum":1,"resultNum":1}
         * list : [{"addTime":1526963771000,"articlelist":null,"level":1,"sublist":null,"deleteStatus":false,"name":"公告","subType":null,"id":194,"mainType":null,"parentId":147}]
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

        public static class PageInfoBean {
            /**
             * pageNum : 1
             * pageSize : 10
             * totalPage : 1
             * totalNum : 1
             * resultNum : 1
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

        public static class ListBean {
            /**
             * addTime : 1526963771000
             * articlelist : null
             * level : 1
             * sublist : null
             * deleteStatus : false
             * name : 公告
             * subType : null
             * id : 194
             * mainType : null
             * parentId : 147
             */

            private long addTime;
            private Object articlelist;
            private int level;
            private Object sublist;
            private boolean deleteStatus;
            private String name;
            private Object subType;
            private int id;
            private Object mainType;
            private int parentId;

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public Object getArticlelist() {
                return articlelist;
            }

            public void setArticlelist(Object articlelist) {
                this.articlelist = articlelist;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public Object getSublist() {
                return sublist;
            }

            public void setSublist(Object sublist) {
                this.sublist = sublist;
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

            public Object getSubType() {
                return subType;
            }

            public void setSubType(Object subType) {
                this.subType = subType;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getMainType() {
                return mainType;
            }

            public void setMainType(Object mainType) {
                this.mainType = mainType;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }
        }
    }
}
