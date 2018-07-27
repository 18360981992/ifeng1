package com.ifeng_tech.treasuryyitong.utils;

/**
 * 代理商操作
 */
public enum EPickUpStage {
  // 下拉框可选状态_待审核_审核驳回_停止征集_征集中_未开始
  // 未开始包括发布中_去掉了发布中状态
// TIMEOUT(4,"审核驳回"),
//AUDIT_REBUTTED(1, "等待提货"),

      PENDING(0, "等待提货"),

      WAITING(2, "注销"),
      ONGOING(3, "已完成"),
      STOPPED(5, "已逾期"),
      TIMEOUT(6, "已完成");
  public static final int MIN_ID = 0;
  public static final int MAX_ID = 5;
  private int id;

  private String name;

  EPickUpStage(int id, String name) {
    this.id = id;
    this.name = name;
  }

  // 普通方法
  public static String getName(int key) {
    for (EPickUpStage c : EPickUpStage.values()) {
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
