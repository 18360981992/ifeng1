package com.ifeng_tech.treasuryyitong.api;

import com.ifeng_tech.treasuryyitong.appliction.DashApplication;

/**
 * Created by mypc on 2018/3/30.
 */

public class APIs {

    public static String PRIVATE_KEY="2074509615ee2557631024d80fd7a1a2";

    public static String debugApi="http://192.168.1.134/";   // 宝库易通


    // 登陆
    public static String login="login/do?e="+ DashApplication.android;

    // 获取短信验证码
    public static String getSmsCode="message/getSmsCode?e="+ DashApplication.android;

    // 注册
    public static String register="register/do?e="+ DashApplication.android;

    // 退出登录
    public static String logout=APIs.debugApi+"user/logout";

    // 修改密码
    public static String changePwd="user/changePwd?e="+ DashApplication.android;

    // 验证短信验证码
    public static String verifySmsCode="message/verifySmsCode?e="+ DashApplication.android;

    // 获取消息列表
    public static String getMessageList="user/message/getMessageList?e="+ DashApplication.android;

    // 个人中心
    public static String getUser = APIs.debugApi+"user/personalcenter/findPersonalUserAccount";


}
