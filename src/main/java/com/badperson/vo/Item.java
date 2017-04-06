package com.badperson.vo;

public class Item {
	
	private String name;
	
	private final String type = "CONNECTION";
	
	private final String server_type = "MYSQL";

	private Item() {
		super();
	}
	
	public static Item newItem(String name){
		Item item = new Item();
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
