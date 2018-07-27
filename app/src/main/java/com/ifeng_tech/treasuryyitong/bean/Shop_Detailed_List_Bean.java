package com.ifeng_tech.treasuryyitong.bean;

import java.util.List;

/**
 * Created by zzt on 2018/7/5.
 */

public class Shop_Detailed_List_Bean {

    /**
     * code : 2000
     * message : 请求成功
     * data : {"list":{"number":0,"last":false,"numberOfElements":10,"size":10,"totalPages":2,"sort":null,"content":[{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012005,"id":"5b3a2f446f92e8b9b7bde9a6","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012006,"id":"5b3a2f446f92e8b9b7bde9a7","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012007,"id":"5b3a2f446f92e8b9b7bde9a8","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012007,"id":"5b3a2f446f92e8b9b7bde9a9","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012008,"id":"5b3a2f446f92e8b9b7bde9aa","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012009,"id":"5b3a2f446f92e8b9b7bde9ab","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012010,"id":"5b3a2f446f92e8b9b7bde9ac","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012011,"id":"5b3a2f446f92e8b9b7bde9ad","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012012,"id":"5b3a2f446f92e8b9b7bde9ae","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012013,"id":"5b3a2f446f92e8b9b7bde9af","goodsCode":"3010090160","userId":367}],"first":true,"totalElements":20},"page":{"pageNum":1,"pageSize":10,"totalPage":null,"totalNum":20,"resultNum":null}}
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
         * list : {"number":0,"last":false,"numberOfElements":10,"size":10,"totalPages":2,"sort":null,"content":[{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012005,"id":"5b3a2f446f92e8b9b7bde9a6","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012006,"id":"5b3a2f446f92e8b9b7bde9a7","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012007,"id":"5b3a2f446f92e8b9b7bde9a8","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012007,"id":"5b3a2f446f92e8b9b7bde9a9","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012008,"id":"5b3a2f446f92e8b9b7bde9aa","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012009,"id":"5b3a2f446f92e8b9b7bde9ab","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012010,"id":"5b3a2f446f92e8b9b7bde9ac","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012011,"id":"5b3a2f446f92e8b9b7bde9ad","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012012,"id":"5b3a2f446f92e8b9b7bde9ae","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012013,"id":"5b3a2f446f92e8b9b7bde9af","goodsCode":"3010090160","userId":367}],"first":true,"totalElements":20}
         * page : {"pageNum":1,"pageSize":10,"totalPage":null,"totalNum":20,"resultNum":null}
         */

        private ListBean list;
        private PageBean page;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public static class ListBean {
            /**
             * number : 0
             * last : false
             * numberOfElements : 10
             * size : 10
             * totalPages : 2
             * sort : null
             * content : [{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012005,"id":"5b3a2f446f92e8b9b7bde9a6","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012006,"id":"5b3a2f446f92e8b9b7bde9a7","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012007,"id":"5b3a2f446f92e8b9b7bde9a8","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012007,"id":"5b3a2f446f92e8b9b7bde9a9","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012008,"id":"5b3a2f446f92e8b9b7bde9aa","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012009,"id":"5b3a2f446f92e8b9b7bde9ab","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012010,"id":"5b3a2f446f92e8b9b7bde9ac","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012011,"id":"5b3a2f446f92e8b9b7bde9ad","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012012,"id":"5b3a2f446f92e8b9b7bde9ae","goodsCode":"3010090160","userId":367},{"addTime":1530539844496,"goodsId":1591,"indepotNo":"R2018070221570322r","updateTime":1530582012013,"id":"5b3a2f446f92e8b9b7bde9af","goodsCode":"3010090160","userId":367}]
             * first : true
             * totalElements : 20
             */

            private int number;
            private boolean last;
            private int numberOfElements;
            private int size;
            private int totalPages;
            private Object sort;
            private boolean first;
            private int totalElements;
            private List<ContentBean> content;

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public boolean isLast() {
                return last;
            }

            public void setLast(boolean last) {
                this.last = last;
            }

            public int getNumberOfElements() {
                return numberOfElements;
            }

            public void setNumberOfElements(int numberOfElements) {
                this.numberOfElements = numberOfElements;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public int getTotalPages() {
                return totalPages;
            }

            public void setTotalPages(int totalPages) {
                this.totalPages = totalPages;
            }

            public Object getSort() {
                return sort;
            }

            public void setSort(Object sort) {
                this.sort = sort;
            }

            public boolean isFirst() {
                return first;
            }

            public void setFirst(boolean first) {
                this.first = first;
            }

            public int getTotalElements() {
                return totalElements;
            }

            public void setTotalElements(int totalElements) {
                this.totalElements = totalElements;
            }

            public List<ContentBean> getContent() {
                return content;
            }

            public void setContent(List<ContentBean> content) {
                this.content = content;
            }

            public static class ContentBean {
                /**
                 * addTime : 1530539844496
                 * goodsId : 1591
                 * indepotNo : R2018070221570322r
                 * updateTime : 1530582012005
                 * id : 5b3a2f446f92e8b9b7bde9a6
                 * goodsCode : 3010090160
                 * userId : 367
                 */

                private long addTime;
                private int goodsId;
                private String indepotNo;
                private long updateTime;
                private String id;
                private String goodsCode;
                private int userId;

                public long getAddTime() {
                    return addTime;
                }

                public void setAddTime(long addTime) {
                    this.addTime = addTime;
                }

                public int getGoodsId() {
                    return goodsId;
                }

                public void setGoodsId(int goodsId) {
                    this.goodsId = goodsId;
                }

                public String getIndepotNo() {
                    return indepotNo;
                }

                public void setIndepotNo(String indepotNo) {
                    this.indepotNo = indepotNo;
                }

                public long getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(long updateTime) {
                    this.updateTime = updateTime;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getGoodsCode() {
                    return goodsCode;
                }

                public void setGoodsCode(String goodsCode) {
                    this.goodsCode = goodsCode;
                }

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }
            }
        }

        public static class PageBean {
            /**
             * pageNum : 1
             * pageSize : 10
             * totalPage : null
             * totalNum : 20
             * resultNum : null
             */

            private int pageNum;
            private int pageSize;
            private Object totalPage;
            private int totalNum;
            private Object resultNum;

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

            public Object getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(Object totalPage) {
                this.totalPage = totalPage;
            }

            public int getTotalNum() {
                return totalNum;
            }

            public void setTotalNum(int totalNum) {
                this.totalNum = totalNum;
            }

            public Object getResultNum() {
                return resultNum;
            }

            public void setResultNum(Object resultNum) {
                this.resultNum = resultNum;
            }
        }
    }
}
