package com.ifeng_tech.treasuryyitong.bean;

import java.util.List;

/**
 * Created by zzt on 2018/6/29.
 */

public class Entrust_List_Bean {

    /**
     * code : 2000
     * message : 操作成功
     * data : {"list":[{"addTime":1530245512000,"deleteStatus":false,"deliveryTel":"18360981992","idCardUrl":"76356735","deliveryNo":"4523465254","deliveryPwd":"43532534","id":27,"deliveryDate":1530288000000,"deliveryName":"3453245","userId":4,"userCode":"000000000004","status":1},{"addTime":1530247493000,"deleteStatus":false,"deliveryTel":"18366669999","idCardUrl":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/card/1530247490476_IMG_2018.jpg","deliveryNo":"23415341535345","deliveryPwd":"12345678","id":28,"deliveryDate":1530288000000,"deliveryName":"网三","userId":4,"userCode":"000000000004","status":1}],"page":{"startRow":0,"lastPage":1,"navigatepageNums":[1],"prePage":0,"hasNextPage":false,"nextPage":0,"pageSize":2,"orderBy":null,"endRow":1,"pageNum":1,"navigatePages":8,"total":2,"pages":1,"size":2,"firstPage":1,"isLastPage":false,"isFirstPage":false}}
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
         * list : [{"addTime":1530245512000,"deleteStatus":false,"deliveryTel":"18360981992","idCardUrl":"76356735","deliveryNo":"4523465254","deliveryPwd":"43532534","id":27,"deliveryDate":1530288000000,"deliveryName":"3453245","userId":4,"userCode":"000000000004","status":1},{"addTime":1530247493000,"deleteStatus":false,"deliveryTel":"18366669999","idCardUrl":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/card/1530247490476_IMG_2018.jpg","deliveryNo":"23415341535345","deliveryPwd":"12345678","id":28,"deliveryDate":1530288000000,"deliveryName":"网三","userId":4,"userCode":"000000000004","status":1}]
         * page : {"startRow":0,"lastPage":1,"navigatepageNums":[1],"prePage":0,"hasNextPage":false,"nextPage":0,"pageSize":2,"orderBy":null,"endRow":1,"pageNum":1,"navigatePages":8,"total":2,"pages":1,"size":2,"firstPage":1,"isLastPage":false,"isFirstPage":false}
         */

        private PageBean page;
        private List<ListBean> list;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PageBean {
            /**
             * startRow : 0
             * lastPage : 1
             * navigatepageNums : [1]
             * prePage : 0
             * hasNextPage : false
             * nextPage : 0
             * pageSize : 2
             * orderBy : null
             * endRow : 1
             * pageNum : 1
             * navigatePages : 8
             * total : 2
             * pages : 1
             * size : 2
             * firstPage : 1
             * isLastPage : false
             * isFirstPage : false
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
            private boolean isFirstPage;
            private List<Integer> navigatepageNums;

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
        }

        public static class ListBean {
            /**
             * addTime : 1530245512000
             * deleteStatus : false
             * deliveryTel : 18360981992
             * idCardUrl : 76356735
             * deliveryNo : 4523465254
             * deliveryPwd : 43532534
             * id : 27
             * deliveryDate : 1530288000000
             * deliveryName : 3453245
             * userId : 4
             * userCode : 000000000004
             * status : 1
             */

            private long addTime;
            private boolean deleteStatus;
            private String deliveryTel;
            private String idCardUrl;
            private String deliveryNo;
            private String deliveryPwd;
            private int id;
            private long deliveryDate;
            private String deliveryName;
            private int userId;
            private String userCode;
            private int status;

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public boolean isDeleteStatus() {
                return deleteStatus;
            }

            public void setDeleteStatus(boolean deleteStatus) {
                this.deleteStatus = deleteStatus;
            }

            public String getDeliveryTel() {
                return deliveryTel;
            }

            public void setDeliveryTel(String deliveryTel) {
                this.deliveryTel = deliveryTel;
            }

            public String getIdCardUrl() {
                return idCardUrl;
            }

            public void setIdCardUrl(String idCardUrl) {
                this.idCardUrl = idCardUrl;
            }

            public String getDeliveryNo() {
                return deliveryNo;
            }

            public void setDeliveryNo(String deliveryNo) {
                this.deliveryNo = deliveryNo;
            }

            public String getDeliveryPwd() {
                return deliveryPwd;
            }

            public void setDeliveryPwd(String deliveryPwd) {
                this.deliveryPwd = deliveryPwd;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getDeliveryDate() {
                return deliveryDate;
            }

            public void setDeliveryDate(long deliveryDate) {
                this.deliveryDate = deliveryDate;
            }

            public String getDeliveryName() {
                return deliveryName;
            }

            public void setDeliveryName(String deliveryName) {
                this.deliveryName = deliveryName;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
