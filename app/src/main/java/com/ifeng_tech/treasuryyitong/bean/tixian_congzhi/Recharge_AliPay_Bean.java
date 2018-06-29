package com.ifeng_tech.treasuryyitong.bean.tixian_congzhi;

/**
 * Created by zzt on 2018/6/14.
 *
 *  支付宝 bean
 */

public class Recharge_AliPay_Bean {

    /**
     * code : 2000
     * message : 操作成功
     * data : {"orderStr":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2016091500514662&biz_content=%7B%22out_trade_no%22%3A%22P00000486902018061409033079699854%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22APP%E7%AB%AF%E5%85%85%E5%80%BC%22%2C%22total_amount%22%3A%22100.0%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fdemo.com%2Falipay%2Fnotify%2F&sign=akVjnZXnPlnN%2BVdSPGVUfXHm2qlIFhlZig0S7gXiBKXB6RLDAiY77kMDm5Zr3U4i2%2FdwIIQRwJYwmsn9hQ9B0D2vbnn1h8kB6xZziUGRDMayDfiYct3ThDOcPFs9QZVwsVRLh9F5RpVFUVs1KvAEIdD5gjBKkllJMSMM9hwB7g84j3Wy7VU9YFa7qR0hZ%2Bu%2FJ6nnH1TQLIYZgN6QzYlwdWut1I12ZREFKE5eeJJ5eEuo%2F2j5sCVDPebBZRvbvJ5GxC9sGumSGDFUsO99B5fiv35BQeFz96O2ghcd2PvX9B2sRzWCw%2FMgWOVl7bZf1wnYoQwcPHuydNxTX5ZAY6fJnw%3D%3D&sign_type=RSA2&timestamp=2018-06-14+09%3A03%3A30&version=1.0"}
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
         * orderStr : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2016091500514662&biz_content=%7B%22out_trade_no%22%3A%22P00000486902018061409033079699854%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22APP%E7%AB%AF%E5%85%85%E5%80%BC%22%2C%22total_amount%22%3A%22100.0%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fdemo.com%2Falipay%2Fnotify%2F&sign=akVjnZXnPlnN%2BVdSPGVUfXHm2qlIFhlZig0S7gXiBKXB6RLDAiY77kMDm5Zr3U4i2%2FdwIIQRwJYwmsn9hQ9B0D2vbnn1h8kB6xZziUGRDMayDfiYct3ThDOcPFs9QZVwsVRLh9F5RpVFUVs1KvAEIdD5gjBKkllJMSMM9hwB7g84j3Wy7VU9YFa7qR0hZ%2Bu%2FJ6nnH1TQLIYZgN6QzYlwdWut1I12ZREFKE5eeJJ5eEuo%2F2j5sCVDPebBZRvbvJ5GxC9sGumSGDFUsO99B5fiv35BQeFz96O2ghcd2PvX9B2sRzWCw%2FMgWOVl7bZf1wnYoQwcPHuydNxTX5ZAY6fJnw%3D%3D&sign_type=RSA2&timestamp=2018-06-14+09%3A03%3A30&version=1.0
         */

        private String orderStr;

        public String getOrderStr() {
            return orderStr;
        }

        public void setOrderStr(String orderStr) {
            this.orderStr = orderStr;
        }
    }
}
