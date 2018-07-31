package com.ifeng_tech.treasuryyitong.bean.bangzu;

import java.util.List;

/**
 * Created by zzt on 2018/7/30.
 */

public class Help_One_Bean {

    /**
     * code : 2000
     * message : 操作成功
     * data : {"list":[{"addTime":1511422686000,"articlelist":null,"level":3,"sublist":null,"deleteStatus":false,"name":"公告中心","subType":null,"id":147,"mainType":null,"parentId":0},{"addTime":1526962849000,"articlelist":null,"level":2,"sublist":null,"deleteStatus":false,"name":"常见问题","subType":null,"id":186,"mainType":null,"parentId":0}]}
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
             * addTime : 1511422686000
             * articlelist : null
             * level : 3
             * sublist : null
             * deleteStatus : false
             * name : 公告中心
             * subType : null
             * id : 147
             * mainType : null
             * parentId : 0
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
            private List<Help_Two_Bean.DataBean.ListBean> list;

            public void setList(List<Help_Two_Bean.DataBean.ListBean> list) {
                this.list = list;
            }

            public List<Help_Two_Bean.DataBean.ListBean> getList() {
                return list;
            }

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
