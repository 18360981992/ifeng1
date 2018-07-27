package com.ifeng_tech.treasuryyitong.bean.weituo;

import java.util.List;

/**
 * Created by zzt on 2018/7/10.
 */

public class Entrust_ClientGoodsByClientCode_Bean {

    /**
     * code : 2000
     * message : 操作成功
     * data : {"pageInfo":{"pageCount":1,"pageSize":20,"elementCount":1,"pageNum":0},"list":[{"addTime":1531212150000,"deleteSate":false,"commodityCode":"0000011223","client":{"addTime":1531212150000,"clientName":"老王","clientCode":"000111","id":1,"deleteState":false},"id":1,"remarks":"备注","commodityName":"宝库测试物品123"}]}
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
         * pageInfo : {"pageCount":1,"pageSize":20,"elementCount":1,"pageNum":0}
         * list : [{"addTime":1531212150000,"deleteSate":false,"commodityCode":"0000011223","client":{"addTime":1531212150000,"clientName":"老王","clientCode":"000111","id":1,"deleteState":false},"id":1,"remarks":"备注","commodityName":"宝库测试物品123"}]
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
             * pageCount : 1
             * pageSize : 20
             * elementCount : 1
             * pageNum : 0
             */

            private int pageCount;
            private int pageSize;
            private int elementCount;
            private int pageNum;

            public int getPageCount() {
                return pageCount;
            }

            public void setPageCount(int pageCount) {
                this.pageCount = pageCount;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getElementCount() {
                return elementCount;
            }

            public void setElementCount(int elementCount) {
                this.elementCount = elementCount;
            }

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }
        }

        public static class ListBean {
            /**
             * addTime : 1531212150000
             * deleteSate : false
             * commodityCode : 0000011223
             * client : {"addTime":1531212150000,"clientName":"老王","clientCode":"000111","id":1,"deleteState":false}
             * id : 1
             * remarks : 备注
             * commodityName : 宝库测试物品123
             */

            private boolean cpFlag=false;
            private long addTime;
            private boolean deleteSate;
            private String commodityCode;
            private ClientBean client;
            private int id;
            private String remarks;
            private String commodityName;

            public void setCpFlag(boolean cpFlag) {
                this.cpFlag = cpFlag;
            }

            public boolean isCpFlag() {

                return cpFlag;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public boolean isDeleteSate() {
                return deleteSate;
            }

            public void setDeleteSate(boolean deleteSate) {
                this.deleteSate = deleteSate;
            }

            public String getCommodityCode() {
                return commodityCode;
            }

            public void setCommodityCode(String commodityCode) {
                this.commodityCode = commodityCode;
            }

            public ClientBean getClient() {
                return client;
            }

            public void setClient(ClientBean client) {
                this.client = client;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public static class ClientBean {
                /**
                 * addTime : 1531212150000
                 * clientName : 老王
                 * clientCode : 000111
                 * id : 1
                 * deleteState : false
                 */

                private long addTime;
                private String clientName;
                private String clientCode;
                private int id;
                private boolean deleteState;

                public long getAddTime() {
                    return addTime;
                }

                public void setAddTime(long addTime) {
                    this.addTime = addTime;
                }

                public String getClientName() {
                    return clientName;
                }

                public void setClientName(String clientName) {
                    this.clientName = clientName;
                }

                public String getClientCode() {
                    return clientCode;
                }

                public void setClientCode(String clientCode) {
                    this.clientCode = clientCode;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public boolean isDeleteState() {
                    return deleteState;
                }

                public void setDeleteState(boolean deleteState) {
                    this.deleteState = deleteState;
                }
            }
        }
    }
}
