package com.ifeng_tech.treasuryyitong.utils;

public enum ApplicationFormEnum {
	TO_BE_IDENTIFIED(0, "待鉴定"),
	IDENTIFIED(2, "待鉴定"), // IDENTIFIED(2, "已鉴定"),
	AUDITED(3, "已鉴定"),  // AUDITED(3, "已审核"),
	OVERDUE(4, "待鉴定"),  // 已逾期
	OVERDUE_PROCESS(5, "已逾期");  // 逾期过时

	private int id;

	private String name;

	// 普通方法
	public static String getName(int key)
	{
		for (ApplicationFormEnum c : ApplicationFormEnum.values())
		{
			if (c.getId() == key)
			{
				return c.name;
			}
		}
		return null;
	}

	private ApplicationFormEnum(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public int getId()
	{
		return id;
	}

	public void setKey(int Id)
	{
		this.id = Id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
