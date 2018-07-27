package com.ifeng_tech.treasuryyitong.api;

/**
 * Created by mypc on 2018/3/30.
 */

public class APIs {

//    public static String debugApi="http://192.168.1.130/";   // 宝库易通  本地
//    public static String debugApi1="http://192.168.1.130:4200/";

//    public static String debugApi="http://192.168.1.134/";   // 宝库易通  测试
//    public static String debugApi1="http://192.168.1.134:4200/";

//    public static String debugApi="http://www.baokuyt.com:8020/";   // 宝库易通  线上测试  175.25.17.52
//    public static String debugApi1="http://www.baokuyt.com:8020/";  //  192.168.1.192:8020

    public static String debugApi="http://www.baokuyt.com/";   // 宝库易通  线上正式
    public static String debugApi1="http://www.baokuyt.com/";  // www.baokuyt.com

    // 登陆
    public static String login="login/do";

    // 通过token保持登录  user/loginByToken
    public static String loginByToken="user/loginByToken";

    // 验证短信验证码或者使用手机验证码登录/message/verifySmsCode
    public static String loginBySmsCode="message/verifySmsCode";


    // 根据用户名查询用户手机号(个人用户)/user/getUserByAccount
    public static String getUserByAccount="user/getUserByAccount";

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
   // public static String getCurrentTrusteeshipList = "trusteeship/getCurrentTrusteeshipList";
    public static String getCurrentTrusteeshipList(int pageNum,int pageSize){
        String ss=APIs.debugApi+"trusteeship/getCurrentTrusteeshipList?pageNum="+pageNum+"&pageSize="+pageSize;
        return ss;
    }


    // 托管预约详情的时间  trusteeship/getTrusteeshipDetail
    public static String getTrusteeshipDetail = "trusteeship/getTrusteeshipDetail";

    // 申请托管
    public static String applyTrusteeship = "user/trusteeship/applyTrusteeship";

    // 申请征集
    public static String applyCollect = "user/collect/applyCollect";


    // 上传文件
    public static String upload = APIs.debugApi+"file/upload";

    // 实名认证  user/manage/applyIdentify 2862559976
    public static String applyIdentify="user/manage/applyIdentify";


    // 资讯  根据主栏目ID查询二级栏目列表  cms/getSubColumnList  id=147   get请求
    public static String getSubColumnList=APIs.debugApi+"cms/getSubColumnList?mainColumnId=147";

    // 资讯 根据主栏目ID查询文章列表   查全部
//    public static String getArticleListByMainColumnId="cms/getArticleListByMainColumnId";
    public static String getArticleListByMainColumnId(String mainColumnId,int pageNum,int pageSize){
        String ss=APIs.debugApi+"cms/getArticleListByMainColumnId?mainColumnId="+mainColumnId+"&pageNum="+pageNum+"&pageSize="+pageSize;
        return ss;
    }

    // 资讯 根据二级栏目ID查询文章列表  查子栏目 例如：公告
    public static String getArticleListBySubColumnId="cms/getArticleListBySubColumnId";
//    public static String getArticleListBySubColumnId(String subColumnId,int pageNum,int pageSize){
//        String ss=APIs.debugApi+"cms/getArticleListBySubColumnId?subColumnId="+subColumnId+"&pageNum="+pageNum+"&pageSize="+pageSize;
//        return ss;
//    }

    // 资讯热门  用于首页
//    public static String getHotList="cms/getHotList";
    public static String getHotList(){
        String ss=APIs.debugApi+"cms/getHotList?pageNum=1&pageSize=3";
        return ss;
    }



    /* 文章详情
        首页进入详情：?id=239&type=home&index=1&plateId=1
        最新资讯进入详情：?id=239&type=new&index=1&plateId=1
        主列表进入详情：?id=239&index=1&plateId=1&mainId=147&typeList=list&typeAll=all
        子列表进入详情：?id=194&index=2&plateId=1&mainId=147&subId=194&typeList=list&typeAll=all
      */

    // 首页进入详情
    public static String home_infodetail(String id,String type,String index){
        String ss=APIs.debugApi1+"#/app/infodetail?id="+id+"&type="+type+"&index="+index+"&plateId=1";
        return ss;
    }

    // 子列表进入详情 传入subid
    public static String zi_infodetail(String id,String index,String subId){
        String ss=APIs.debugApi1+"#/app/infodetail?id="+id+"&index="+index+"&plateId=1&mainId=147&subId="+subId+"&typeList=list&typeAll=all";
        return ss;
    }

    //  主列表进入详情 不传subid
    public static String zhu_infodetail(String id,String index){
        String ss=APIs.debugApi1+"#/app/infodetail?id="+id+"&index="+index+"&plateId=1&mainId=147&typeList=list&typeAll=all";
        return ss;
    }

    // 藏品一级分类
    public static String getFirstCategoryList=APIs.debugApi+"goods/getFirstCategoryList?pageNum=1&pageSize=100";

    //  藏品详情
    public static String getonestamp="goods/getonestamp?commodityId=";

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

    // 校验短信验证码并且发送邮箱验证码（修改绑定邮箱） user/checkSMSVerificationCodeAndSendEmailVerificationCode
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

    // 查看范例文档
    public static String fanLie_url = "http://flt-picture.oss-cn-beijing.aliyuncs.com/postage%2F%7BB8F3F21F-FF02-6A8F-DE72-04325AAF8D97%7D.jpg";

    // 区块链电子身份信息
    public static String queryInventory="user/store/queryInventory";

    // 区块链详情
    public static String queryInventory_Detailed=APIs.debugApi1+"#/phone/product?ReferralCode=";

    // 校验业务密码
    public static String verifyServicePassword="user/personalcenter/verifyServicePassword";

    // 查询全部第三方平台/   user/depots/getClient
    public static String getClient="user/depots/getClient";

    // 根据平台code查询藏品信息/   user/depots/getClientGoodsByClientCode
    public static String getClientGoodsByClientCode="user/depots/getClientGoodsByClientCode";
}
