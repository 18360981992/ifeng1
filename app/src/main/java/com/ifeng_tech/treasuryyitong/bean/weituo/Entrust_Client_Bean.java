package com.ifeng_tech.treasuryyitong.bean.weituo;

import java.util.List;

/**
 * Created by zzt on 2018/7/10.
 */

public class Entrust_Client_Bean {

    /**
     * code : 2000
     * message : 操作成功
     * data : {"client":[{"addTime":1531212150000,"clientName":"老王","clientCode":"000111","id":1,"deleteState":false}]}
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
        private List<ClientBean> client;

        public List<ClientBean> getClient() {
            return client;
        }

        public void setClient(List<ClientBean> client) {
            this.client = client;
        }

        public static class ClientBean {
            /**
             * addTime : 1531212150000
             * clientName : 老王
             * clientCode : 000111
             * id : 1
             * deleteState : false
             */

            private boolean ptFlag=false;
            private long addTime;
            private String clientName;
            private String clientCode;
            private int id;
            private boolean deleteState;

            public void setPtFlag(boolean ptFlag) {
                this.ptFlag = ptFlag;
            }

            public boolean isPtFlag() {

                return ptFlag;
            }

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
