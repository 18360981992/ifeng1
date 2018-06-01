package com.ifeng_tech.treasuryyitong.utils;

/**
 * 代理商操作
 */
public enum ECollectStage {
  // 下拉框可选状态_待审核_审核驳回_停止征集_征集中_未开始
  // 未开始包括发布中_去掉了发布中状态
  PENDING(1, "待审核"), AUDIT_REBUTTED(2, "审核被驳回"), WAITING(3, "征集未开始"), ONGOING(4, "征集中"), TIMEOUT(5,
      "征集已过期"), STOPPED(6, "机构关闭（已停止）"), CHECKED(7, "机构已确认"), FINISHED(8, "已结束");

  public static final int MIN_ID = 1;
  public static final int MAX_ID = 8;
  private int id;

  private String name;

  ECollectStage(int id, String name) {
    this.id = id;
    this.name = name;
  }

  // 普通方法
  public static String getName(int key) {
    for (ECollectStage c : ECollectStage.values()) {
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
