package com.ifeng_tech.treasuryyitong.bean.tihuo;

import java.util.List;

/**
 * Created by zzt on 2018/6/8.
 *
 *  根据商品id查仓库
 */

public class DepotListByGoodsId_Bean {

    /**
     * code : 2000
     * message : 操作成功
     * data : {"list":[{"effectQty":900,"depotName":"测试仓库","areaId":110101,"address":"北京","depotId":26,"depotCode":"1111","commodityId":839}]}
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
             * effectQty : 900
             * depotName : 测试仓库
             * areaId : 110101
             * address : 北京
             * depotId : 26
             * depotCode : 1111
             * commodityId : 839
             */

            private int effectQty;
            private String depotName;
            private int areaId;
            private String address;
            private int depotId;
            private String depotCode;
            private int commodityId;

            public int getEffectQty() {
                return effectQty;
            }

            public void setEffectQty(int effectQty) {
                this.effectQty = effectQty;
            }

            public String getDepotName() {
                return depotName;
            }

            public void setDepotName(String depotName) {
                this.depotName = depotName;
            }

            public int getAreaId() {
                return areaId;
            }

            public void setAreaId(int areaId) {
                this.areaId = areaId;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getDepotId() {
                return depotId;
            }

            public void setDepotId(int depotId) {
                this.depotId = depotId;
            }

            public String getDepotCode() {
                return depotCode;
            }

            public void setDepotCode(String depotCode) {
                this.depotCode = depotCode;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }
        }
    }
}
