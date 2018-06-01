package com.ifeng_tech.treasuryyitong.utils;

/**
 * Created by zzt on 2018/5/31.
 *
 *
 */

public enum EGiven_ListStage {
    // 下拉框可选状态_待审核_审核驳回_停止征集_征集中_未开始
    // 未开始包括发布中_去掉了发布中状态
    PENDING(0, "等待确认"),
    AUDIT_REBUTTED(1, "完成"),
    WAITING(2, "失败"),
    ONGOING(3, "取消"),
    TIMEOUT(4,"拒绝"),
    STOPPED(5, "已过期");

    public static final int MIN_ID = 0;
    public static final int MAX_ID = 5;
    private int id;

    private String name;

    EGiven_ListStage(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // 普通方法
    public static String getName(int key) {
        for (EGiven_ListStage c : EGiven_ListStage.values()) {
            if (c.getId() == key) {
                return c.name;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
