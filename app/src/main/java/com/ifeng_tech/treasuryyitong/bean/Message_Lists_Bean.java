package com.ifeng_tech.treasuryyitong.bean;

import java.util.List;

/**
 * Created by zzt on 2018/5/22.
 */

public class Message_Lists_Bean {

    /**
     * code : 2000
     * message : 操作成功
     * data : {"pageInfo":{"pageNum":2,"pageSize":10,"totalPage":4,"totalNum":46,"resultNum":11},"list":[{"readTimeStr":null,"readState":0,"addTime":1527648209000,"messageId":263,"readTime":null,"type":0,"userId":123,"content":"预约托管商品3010070030，10张成功","url":null,"addTimeStr":"2018-05-30 10:43:29"},{"readTimeStr":null,"readState":0,"addTime":1527648161000,"messageId":262,"readTime":null,"type":0,"userId":123,"content":"参与申请征集商品3010070030，10张成功","url":null,"addTimeStr":"2018-05-30 10:42:41"},{"readTimeStr":null,"readState":0,"addTime":1527648152000,"messageId":261,"readTime":null,"type":0,"userId":123,"content":"参与申请征集商品3010070030，10张成功","url":null,"addTimeStr":"2018-05-30 10:42:32"},{"readTimeStr":null,"readState":0,"addTime":1527586019000,"messageId":245,"readTime":null,"type":0,"userId":123,"content":"高温预警","url":null,"addTimeStr":"2018-05-29 17:26:59"},{"readTimeStr":null,"readState":0,"addTime":1527586017000,"messageId":244,"readTime":null,"type":0,"userId":123,"content":"高温预警","url":null,"addTimeStr":"2018-05-29 17:26:57"},{"readTimeStr":null,"readState":0,"addTime":1527582854000,"messageId":241,"readTime":null,"type":0,"userId":123,"content":"修改登录密码","url":null,"addTimeStr":"2018-05-29 16:34:14"},{"readTimeStr":null,"readState":0,"addTime":1527582755000,"messageId":240,"readTime":null,"type":0,"userId":123,"content":"修改密码","url":null,"addTimeStr":"2018-05-29 16:32:35"},{"readTimeStr":null,"readState":0,"addTime":1527556227000,"messageId":225,"readTime":null,"type":0,"userId":123,"content":"\n今日生活指数\n洗车指数： \t较不宜\n旅游指数： \t适宜\n紫外线指数： \t很强\n感冒指数： \t少发\n运动指数： \t较适宜 ","url":null,"addTimeStr":"2018-05-29 09:10:27"},{"readTimeStr":null,"readState":0,"addTime":1527556221000,"messageId":224,"readTime":null,"type":0,"userId":123,"content":"\n今日生活指数\n洗车指数： \t较不宜\n旅游指数： \t适宜\n紫外线指数： \t很强\n感冒指数： \t少发\n运动指数： \t较适宜 ","url":null,"addTimeStr":"2018-05-29 09:10:21"},{"readTimeStr":null,"readState":0,"addTime":1527504200000,"messageId":223,"readTime":null,"type":0,"userId":123,"content":"000000000000000000222","url":null,"addTimeStr":"2018-05-28 18:43:20"},{"readTimeStr":null,"readState":null,"addTime":1527476690000,"messageId":222,"readTime":null,"type":0,"userId":123,"content":"测试000000000000005554","url":null,"addTimeStr":"2018-05-28 11:04:50"}]}
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
         * pageInfo : {"pageNum":2,"pageSize":10,"totalPage":4,"totalNum":46,"resultNum":11}
         * list : [{"readTimeStr":null,"readState":0,"addTime":1527648209000,"messageId":263,"readTime":null,"type":0,"userId":123,"content":"预约托管商品3010070030，10张成功","url":null,"addTimeStr":"2018-05-30 10:43:29"},{"readTimeStr":null,"readState":0,"addTime":1527648161000,"messageId":262,"readTime":null,"type":0,"userId":123,"content":"参与申请征集商品3010070030，10张成功","url":null,"addTimeStr":"2018-05-30 10:42:41"},{"readTimeStr":null,"readState":0,"addTime":1527648152000,"messageId":261,"readTime":null,"type":0,"userId":123,"content":"参与申请征集商品3010070030，10张成功","url":null,"addTimeStr":"2018-05-30 10:42:32"},{"readTimeStr":null,"readState":0,"addTime":1527586019000,"messageId":245,"readTime":null,"type":0,"userId":123,"content":"高温预警","url":null,"addTimeStr":"2018-05-29 17:26:59"},{"readTimeStr":null,"readState":0,"addTime":1527586017000,"messageId":244,"readTime":null,"type":0,"userId":123,"content":"高温预警","url":null,"addTimeStr":"2018-05-29 17:26:57"},{"readTimeStr":null,"readState":0,"addTime":1527582854000,"messageId":241,"readTime":null,"type":0,"userId":123,"content":"修改登录密码","url":null,"addTimeStr":"2018-05-29 16:34:14"},{"readTimeStr":null,"readState":0,"addTime":1527582755000,"messageId":240,"readTime":null,"type":0,"userId":123,"content":"修改密码","url":null,"addTimeStr":"2018-05-29 16:32:35"},{"readTimeStr":null,"readState":0,"addTime":1527556227000,"messageId":225,"readTime":null,"type":0,"userId":123,"content":"\n今日生活指数\n洗车指数： \t较不宜\n旅游指数： \t适宜\n紫外线指数： \t很强\n感冒指数： \t少发\n运动指数： \t较适宜 ","url":null,"addTimeStr":"2018-05-29 09:10:27"},{"readTimeStr":null,"readState":0,"addTime":1527556221000,"messageId":224,"readTime":null,"type":0,"userId":123,"content":"\n今日生活指数\n洗车指数： \t较不宜\n旅游指数： \t适宜\n紫外线指数： \t很强\n感冒指数： \t少发\n运动指数： \t较适宜 ","url":null,"addTimeStr":"2018-05-29 09:10:21"},{"readTimeStr":null,"readState":0,"addTime":1527504200000,"messageId":223,"readTime":null,"type":0,"userId":123,"content":"000000000000000000222","url":null,"addTimeStr":"2018-05-28 18:43:20"},{"readTimeStr":null,"readState":null,"addTime":1527476690000,"messageId":222,"readTime":null,"type":0,"userId":123,"content":"测试000000000000005554","url":null,"addTimeStr":"2018-05-28 11:04:50"}]
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
             * pageNum : 2
             * pageSize : 10
             * totalPage : 4
             * totalNum : 46
             * resultNum : 11
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
             * readTimeStr : null
             * readState : 0
             * addTime : 1527648209000
             * messageId : 263
             * readTime : null
             * type : 0
             * userId : 123
             * content : 预约托管商品3010070030，10张成功
             * url : null
             * addTimeStr : 2018-05-30 10:43:29
             */

            private Object readTimeStr;
            private int readState;
            private long addTime;
            private int messageId;
            private Object readTime;
            private int type;
            private int userId;
            private String content;
            private Object url;
            private String addTimeStr;

            public Object getReadTimeStr() {
                return readTimeStr;
            }

            public void setReadTimeStr(Object readTimeStr) {
                this.readTimeStr = readTimeStr;
            }

            public int getReadState() {
                return readState;
            }

            public void setReadState(int readState) {
                this.readState = readState;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public int getMessageId() {
                return messageId;
            }

            public void setMessageId(int messageId) {
                this.messageId = messageId;
            }

            public Object getReadTime() {
                return readTime;
            }

            public void setReadTime(Object readTime) {
                this.readTime = readTime;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Object getUrl() {
                return url;
            }

            public void setUrl(Object url) {
                this.url = url;
            }

            public String getAddTimeStr() {
                return addTimeStr;
            }

            public void setAddTimeStr(String addTimeStr) {
                this.addTimeStr = addTimeStr;
            }
        }
    }
}
