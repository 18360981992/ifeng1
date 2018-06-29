package com.ifeng_tech.treasuryyitong.bean;

import java.util.List;

/**
 * Created by zzt on 2018/5/22.
 */

public class Message_Lists_Bean {


    /**
     * code : 2000
     * message : 操作成功
     * data : {"pageInfo":{"pageNum":1,"pageSize":10,"totalPage":2,"totalNum":24,"resultNum":10},"list":[{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530150893000,"messageId":60,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】您向用户000000000005发起的转赠已成功。2018-06-28 09:54:52","url":"/user/transfer","addTimeStr":"2018-06-28 09:54:53"},{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530150812000,"messageId":59,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】您向用户000000000005发起的转赠已成功。2018-06-28 09:53:32","url":"/user/transfer","addTimeStr":"2018-06-28 09:53:32"},{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530150701000,"messageId":57,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】000000000005向您发起转赠申请，藏品名称：特6-2007《中国探月首飞成功纪念》邮票，数量：1。请及时处理。2018-06-28 09:51:41","url":"/user/transfer","addTimeStr":"2018-06-28 09:51:41"},{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530149698000,"messageId":55,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】您向用户000000000005发起的转赠已成功。2018-06-28 09:34:58","url":"/user/transfer","addTimeStr":"2018-06-28 09:34:58"},{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530149666000,"messageId":54,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】您向用户000000000005发起的转赠已成功。2018-06-28 09:34:25","url":"/user/transfer","addTimeStr":"2018-06-28 09:34:26"},{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530086911000,"messageId":46,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】您向用户000000000005发起的转赠已成功。2018-06-27 16:08:30","url":"/user/transfer","addTimeStr":"2018-06-27 16:08:31"},{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530086890000,"messageId":45,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】您向用户000000000005发起的转赠已成功。2018-06-27 16:08:10","url":"/user/transfer","addTimeStr":"2018-06-27 16:08:10"},{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530086622000,"messageId":43,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】您向用户000000000005发起的转赠已成功。2018-06-27 16:03:41","url":"/user/transfer","addTimeStr":"2018-06-27 16:03:42"},{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530085867000,"messageId":41,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】000000000002向您发起转赠申请，藏品名称：2007-11《内蒙古自治区成立六十周年》纪念邮票，数量：11。请及时处理。2018-06-27 15:51:07","url":"/user/transfer","addTimeStr":"2018-06-27 15:51:07"},{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530085744000,"messageId":39,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】000000000002向您发起转赠申请，藏品名称：2007-11《内蒙古自治区成立六十周年》纪念邮票，数量：1。请及时处理。2018-06-27 15:49:04","url":"/user/transfer","addTimeStr":"2018-06-27 15:49:04"}]}
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
         * pageInfo : {"pageNum":1,"pageSize":10,"totalPage":2,"totalNum":24,"resultNum":10}
         * list : [{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530150893000,"messageId":60,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】您向用户000000000005发起的转赠已成功。2018-06-28 09:54:52","url":"/user/transfer","addTimeStr":"2018-06-28 09:54:53"},{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530150812000,"messageId":59,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】您向用户000000000005发起的转赠已成功。2018-06-28 09:53:32","url":"/user/transfer","addTimeStr":"2018-06-28 09:53:32"},{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530150701000,"messageId":57,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】000000000005向您发起转赠申请，藏品名称：特6-2007《中国探月首飞成功纪念》邮票，数量：1。请及时处理。2018-06-28 09:51:41","url":"/user/transfer","addTimeStr":"2018-06-28 09:51:41"},{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530149698000,"messageId":55,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】您向用户000000000005发起的转赠已成功。2018-06-28 09:34:58","url":"/user/transfer","addTimeStr":"2018-06-28 09:34:58"},{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530149666000,"messageId":54,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】您向用户000000000005发起的转赠已成功。2018-06-28 09:34:25","url":"/user/transfer","addTimeStr":"2018-06-28 09:34:26"},{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530086911000,"messageId":46,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】您向用户000000000005发起的转赠已成功。2018-06-27 16:08:30","url":"/user/transfer","addTimeStr":"2018-06-27 16:08:31"},{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530086890000,"messageId":45,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】您向用户000000000005发起的转赠已成功。2018-06-27 16:08:10","url":"/user/transfer","addTimeStr":"2018-06-27 16:08:10"},{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530086622000,"messageId":43,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】您向用户000000000005发起的转赠已成功。2018-06-27 16:03:41","url":"/user/transfer","addTimeStr":"2018-06-27 16:03:42"},{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530085867000,"messageId":41,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】000000000002向您发起转赠申请，藏品名称：2007-11《内蒙古自治区成立六十周年》纪念邮票，数量：11。请及时处理。2018-06-27 15:51:07","url":"/user/transfer","addTimeStr":"2018-06-27 15:51:07"},{"readTimeStr":null,"readState":0,"msgType":1,"addTime":1530085744000,"messageId":39,"readTime":null,"type":0,"userId":4,"content":"【宝库易通】000000000002向您发起转赠申请，藏品名称：2007-11《内蒙古自治区成立六十周年》纪念邮票，数量：1。请及时处理。2018-06-27 15:49:04","url":"/user/transfer","addTimeStr":"2018-06-27 15:49:04"}]
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
             * totalPage : 2
             * totalNum : 24
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

        public static class ListBean {
            /**
             * readTimeStr : null
             * readState : 0
             * msgType : 1
             * addTime : 1530150893000
             * messageId : 60
             * readTime : null
             * type : 0
             * userId : 4
             * content : 【宝库易通】您向用户000000000005发起的转赠已成功。2018-06-28 09:54:52
             * url : /user/transfer
             * addTimeStr : 2018-06-28 09:54:53
             */

            private Object readTimeStr;
            private int readState;
            private int msgType;
            private long addTime;
            private int messageId;
            private Object readTime;
            private int type;
            private int userId;
            private String content;
            private String url;
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

            public int getMsgType() {
                return msgType;
            }

            public void setMsgType(int msgType) {
                this.msgType = msgType;
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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
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
