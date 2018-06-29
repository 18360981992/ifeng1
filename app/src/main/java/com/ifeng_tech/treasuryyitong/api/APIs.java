package com.ifeng_tech.treasuryyitong.api;

/**
 * Created by mypc on 2018/3/30.
 */

public class APIs {

//    public static String debugApi="http://192.168.1.130/";   // 宝库易通  本地

    public static String debugApi="http://192.168.1.134/";   // 宝库易通  测试

//    public static String debugApi="http://baoku.wjybk.com/";   // 宝库易通  线上

    // 登陆
    public static String login="login/do";

    // 获取短信验证码
    public static String getSmsCode="message/getSmsCode";

    // 注册
    public static String register="register/do";

    // 退出登录
    public static String logout = APIs.debugApi+"user/logout";

    // 修改密码
    public static String changePwd="user/changePwd";

    // 找回密码/忘记密码   user/modifyPasswordByMobile
    public static String modifyPasswordByMobile="user/modifyPasswordByMobile";

    // 验证短信验证码
    public static String verifySmsCode="message/verifySmsCode";

    // 获取图形验证码   verify/newImage
    public static String newImageCode = APIs.debugApi + "verify/newImage";

    // 验证图形验证码   verify/verifyCode
    public static String verifyCode="verify/verifyCode";

    // 获取  用户消息列表
    public static String getMessageList="user/message/getMessageList";

    // 个人中心
    public static String findPersonalUserAccount = APIs.debugApi+"user/personalcenter/findPersonalUserAccount";

    // 个人资产中的资金流水
    public static String findUserPaymentRecords = "user/personalcenter/findUserPaymentRecords";

    // 我的征集
    public static String getUserCollectList = "user/collect/getUserCollectList";

    // 我的托管
    public static String getUserTrusteeshipList = "user/trusteeship/getUserTrusteeshipList";

    // 转赠列表
    public static String getBestowList = "user/bestow/list";

    // 我的仓库
    public static String getHoldList = "user/depots/getHoldList";

    // 查询转赠手续费  根据goodsid
    public static String findTransferFee = "user/bestow/findTransferFee";

//    // 转赠  模糊查询藏品代码
//    public static String getGoodsListByCode = "goods/getGoodsListByCode";

    // 个人转赠   /user/bestow/add
    public static String getUserZhuanzeng = "user/bestow/add";

    // 个人转赠  同意 user/bestow/accept
    public static String getUserZhuanzeng_accept = "user/bestow/accept";

    // 个人转赠  拒绝 user/bestow/refuse
    public static String getUserZhuanzeng_refuse = "user/bestow/refuse";

    // 根据藏品id 查询仓库   user/depots/getDepotListByGoodsId
    public static String getDepotListByGoodsId = "user/depots/getDepotListByGoodsId";

    // 申请提货
    public static String applyTakeDelivery = "user/depots/applyTakeDelivery";

    // 提货单查询
    public static String getTakeDeliveryList = "user/depots/getTakeDeliveryList";

    // 提货单注销
    public static String cancelTakeDeliveryOrder = "user/depots/cancelTakeDeliveryOrder";

    // 一级页面 征集
    public static String getCollectList = "collect/getCollectList";


    // 更换绑定手机号
    public static String changeMobile = "user/manage/changeMobile";

    //  重置 业务密码  user/personalcenter/setBusinessPassword
    public static String setBusinessPassword = "user/personalcenter/modifyOrResetBusinessPassword";

    // 设置业务密码   user/personalcenter/setBusinessPassword
    public static String modifyOrResetBusinessPassword = "user/personalcenter/setBusinessPassword";


    // 托管预约列表  trusteeship/getCurrentTrusteeshipList
    public static String getCurrentTrusteeshipList = "trusteeship/getCurrentTrusteeshipList";

    // 托管预约详情的时间  trusteeship/getTrusteeshipDetail
    public static String getTrusteeshipDetail = "trusteeship/getTrusteeshipDetail";

    // 申请托管
    public static String applyTrusteeship = "user/trusteeship/applyTrusteeship";

    // 申请征集
    public static String applyCollect = "user/collect/applyCollect";


    // 上传文件
    public static String upload = APIs.debugApi+"file/upload";

    // 实名认证  user/manage/applyIdentify
    public static String applyIdentify="user/manage/applyIdentify";


    // 资讯  根据主栏目ID查询二级栏目列表  cms/getSubColumnList  id=147
    public static String getSubColumnList="cms/getSubColumnList";

    // 资讯 根据主栏目ID查询文章列表
    public static String getArticleListByMainColumnId="cms/getArticleListByMainColumnId";

    // 资讯热门  用于首页
    public static String getHotList="cms/getHotList";

    // 资讯 根据二级栏目ID查询文章列表
    public static String getArticleListBySubColumnId="cms/getArticleListBySubColumnId";

    // 文章详情
    public static String infodetail=APIs.debugApi+"#/app/infodetail?id=";

    // 藏品一级分类
    public static String getFirstCategoryList=APIs.debugApi+"goods/getFirstCategoryList?pageNum=1&pageSize=100";

    //  藏品详情
    public static String getonestamp="goods/getonestamp";

    // 显示商品列表、根据商品一级查询商品、商品code模糊查询获取商品信息/goods/queryCommodityList?pageNum=1&pageSize=20&firstCategoryName
    public static String queryCommodityList(int pageNum,String firstCategoryName){
        String queryCommodityList = APIs.debugApi + "goods/queryCommodityList?pageNum="+pageNum+"&pageSize=10&firstCategoryName="+firstCategoryName;
        return queryCommodityList;
    }

    // 提现申请   user/personalcenter/withdrawalApplication
    public static String withdrawalApplication="user/personalcenter/withdrawalApplication";

    // 充值
    public static String personalUserRecharge_app="user/personalcenter/personalUserRecharge_app";

    // 充值同步消息
    public static String pullAndUpdatePendingPaymentState="pullAndUpdatePendingPaymentState";

    // 发送邮箱验证码
    public static String checkSMSVerificationCodeAndSendEmailVerficationCode="user/checkSMSVerificationCodeAndSendEmailVerificationCode";

    // 再次发送邮箱
    public static String sendMailAgain="user/sendMailAgain";

    // 绑定邮箱
    public static String checkeMailCodeAndBindMailbox="user/checkeMailCodeAndBindMailbox";

    // 校验邮箱验证码  用于业务密码重置
    public static String checkeMailCode="user/checkeMailCode";

    // 轮播图   cms/selectAdvertise
    public static String selectAdvertise="cms/selectAdvertise";

    // 委托入库/      user/depots/entrustedStorage
    public static String entrustedStorage="user/depots/entrustedStorage";

    // 委托列表/user/depots/entrustedList   委托单状态 ： 1-等待审核 2-待处理 3-已完成 4-提货失败
    public static String entrustedList="user/depots/entrustedList";

    // 下载范例文档
    public static String download="http://flt-picture.oss-cn-beijing.aliyuncs.com/document%2F%E5%A7%94%E6%89%98%E5%8D%8F%E8%AE%AE.rtf";

}
