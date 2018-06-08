package com.ifeng_tech.treasuryyitong.bean.cangpin;

import java.util.List;

/**
 * Created by zzt on 2018/6/7.
 */

public class Collection_directory_Bean {

    /**
     * code : 2000
     * message : 操作成功
     * data : {"FirstCategoryList":[{"subList":null,"addTime":1511760661000,"deleteStatus":false,"categoryLevel":2,"id":62,"categoryName":"钱币","parentId":0},{"subList":null,"addTime":1515551275000,"deleteStatus":false,"categoryLevel":3,"id":66,"categoryName":"邮资封片","parentId":0},{"subList":null,"addTime":1520328453000,"deleteStatus":false,"categoryLevel":4,"id":75,"categoryName":"新中国邮票","parentId":0}],"page":{"startRow":1,"lastPage":1,"navigatepageNums":[1],"prePage":0,"hasNextPage":false,"nextPage":0,"pageSize":20,"orderBy":null,"endRow":3,"pageNum":1,"navigatePages":8,"total":3,"pages":1,"size":3,"firstPage":1,"isLastPage":false,"isFirstPage":false}}
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
         * FirstCategoryList : [{"subList":null,"addTime":1511760661000,"deleteStatus":false,"categoryLevel":2,"id":62,"categoryName":"钱币","parentId":0},{"subList":null,"addTime":1515551275000,"deleteStatus":false,"categoryLevel":3,"id":66,"categoryName":"邮资封片","parentId":0},{"subList":null,"addTime":1520328453000,"deleteStatus":false,"categoryLevel":4,"id":75,"categoryName":"新中国邮票","parentId":0}]
         * page : {"startRow":1,"lastPage":1,"navigatepageNums":[1],"prePage":0,"hasNextPage":false,"nextPage":0,"pageSize":20,"orderBy":null,"endRow":3,"pageNum":1,"navigatePages":8,"total":3,"pages":1,"size":3,"firstPage":1,"isLastPage":false,"isFirstPage":false}
         */

        private PageBean page;
        private List<FirstCategoryListBean> FirstCategoryList;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<FirstCategoryListBean> getFirstCategoryList() {
            return FirstCategoryList;
        }

        public void setFirstCategoryList(List<FirstCategoryListBean> FirstCategoryList) {
            this.FirstCategoryList = FirstCategoryList;
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

        public static class FirstCategoryListBean {
            /**
             * subList : null
             * addTime : 1511760661000
             * deleteStatus : false
             * categoryLevel : 2
             * id : 62
             * categoryName : 钱币
             * parentId : 0
             */

            private Object subList;
            private long addTime;
            private boolean deleteStatus;
            private int categoryLevel;
            private int id;
            private String categoryName;
            private int parentId;

            public Object getSubList() {
                return subList;
            }

            public void setSubList(Object subList) {
                this.subList = subList;
            }

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

            public int getCategoryLevel() {
                return categoryLevel;
            }

            public void setCategoryLevel(int categoryLevel) {
                this.categoryLevel = categoryLevel;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
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
