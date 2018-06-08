package com.ifeng_tech.treasuryyitong.bean.cangpin;

import java.util.List;

/**
 * Created by zzt on 2018/6/7.
 */

public class Collection_directory_Fragment_Bean {

    /**
     * code : 2000
     * message : 操作成功
     * data : {"list":[{"commodityLink":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/198020180607093408.png","addTime":1528336405000,"secondCategory":"纸币","firstCategory":"钱币","firstCategoryId":62,"deleteStatus":false,"commodityCode":"1980002","id":1432,"state":1,"secondCategoryId":118,"categoryId":118,"isHot":0,"commodityName":"1980年第四套人民币两角"},{"commodityLink":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/198020180607093556.png","addTime":1528336405000,"secondCategory":"纸币","firstCategory":"钱币","firstCategoryId":62,"deleteStatus":false,"commodityCode":"1980001","id":1433,"state":1,"secondCategoryId":118,"categoryId":118,"isHot":0,"commodityName":"1980年第四套人民币一元"},{"commodityLink":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/198020180607093500.png","addTime":1528336405000,"secondCategory":"纸币","firstCategory":"钱币","firstCategoryId":62,"deleteStatus":false,"commodityCode":"1980005","id":1434,"state":1,"secondCategoryId":118,"categoryId":118,"isHot":0,"commodityName":"1980年第四套人民币伍元"}],"page":{"startRow":1,"lastPage":1,"navigatepageNums":[1],"prePage":0,"hasNextPage":false,"nextPage":0,"pageSize":20,"orderBy":null,"endRow":3,"pageNum":1,"navigatePages":8,"total":3,"pages":1,"size":3,"firstPage":1,"isLastPage":false,"isFirstPage":false}}
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
         * list : [{"commodityLink":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/198020180607093408.png","addTime":1528336405000,"secondCategory":"纸币","firstCategory":"钱币","firstCategoryId":62,"deleteStatus":false,"commodityCode":"1980002","id":1432,"state":1,"secondCategoryId":118,"categoryId":118,"isHot":0,"commodityName":"1980年第四套人民币两角"},{"commodityLink":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/198020180607093556.png","addTime":1528336405000,"secondCategory":"纸币","firstCategory":"钱币","firstCategoryId":62,"deleteStatus":false,"commodityCode":"1980001","id":1433,"state":1,"secondCategoryId":118,"categoryId":118,"isHot":0,"commodityName":"1980年第四套人民币一元"},{"commodityLink":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/198020180607093500.png","addTime":1528336405000,"secondCategory":"纸币","firstCategory":"钱币","firstCategoryId":62,"deleteStatus":false,"commodityCode":"1980005","id":1434,"state":1,"secondCategoryId":118,"categoryId":118,"isHot":0,"commodityName":"1980年第四套人民币伍元"}]
         * page : {"startRow":1,"lastPage":1,"navigatepageNums":[1],"prePage":0,"hasNextPage":false,"nextPage":0,"pageSize":20,"orderBy":null,"endRow":3,"pageNum":1,"navigatePages":8,"total":3,"pages":1,"size":3,"firstPage":1,"isLastPage":false,"isFirstPage":false}
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
             * startRow : 1
             * lastPage : 1
             * navigatepageNums : [1]
             * prePage : 0
             * hasNextPage : false
             * nextPage : 0
             * pageSize : 20
             * orderBy : null
             * endRow : 3
             * pageNum : 1
             * navigatePages : 8
             * total : 3
             * pages : 1
             * size : 3
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
             * commodityLink : http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/198020180607093408.png
             * addTime : 1528336405000
             * secondCategory : 纸币
             * firstCategory : 钱币
             * firstCategoryId : 62
             * deleteStatus : false
             * commodityCode : 1980002
             * id : 1432
             * state : 1
             * secondCategoryId : 118
             * categoryId : 118
             * isHot : 0
             * commodityName : 1980年第四套人民币两角
             */

            private String commodityLink;
            private long addTime;
            private String secondCategory;
            private String firstCategory;
            private int firstCategoryId;
            private boolean deleteStatus;
            private String commodityCode;
            private int id;
            private int state;
            private int secondCategoryId;
            private int categoryId;
            private int isHot;
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

            public String getSecondCategory() {
                return secondCategory;
            }

            public void setSecondCategory(String secondCategory) {
                this.secondCategory = secondCategory;
            }

            public String getFirstCategory() {
                return firstCategory;
            }

            public void setFirstCategory(String firstCategory) {
                this.firstCategory = firstCategory;
            }

            public int getFirstCategoryId() {
                return firstCategoryId;
            }

            public void setFirstCategoryId(int firstCategoryId) {
                this.firstCategoryId = firstCategoryId;
            }

            public boolean isDeleteStatus() {
                return deleteStatus;
            }

            public void setDeleteStatus(boolean deleteStatus) {
                this.deleteStatus = deleteStatus;
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

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getSecondCategoryId() {
                return secondCategoryId;
            }

            public void setSecondCategoryId(int secondCategoryId) {
                this.secondCategoryId = secondCategoryId;
            }

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public int getIsHot() {
                return isHot;
            }

            public void setIsHot(int isHot) {
                this.isHot = isHot;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }
        }
    }
}
