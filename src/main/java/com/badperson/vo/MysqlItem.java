package com.badperson.vo;

public class MysqlItem {
	
	private String name;
	
	private final String type = "CONNECTION";
	
	private final String server_type = "MYSQL";

	private MysqlItem() {
		super();
	}
	
	public static MysqlItem newItem(String name){
		MysqlItem item = new MysqlItem();
		item.name = name;
		return item;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getServer_type() {
		return server_type;
	}
	
}
