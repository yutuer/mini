package com.badperson.vo;

import java.util.List;

import com.google.common.collect.Lists;

public class VGroup {
	
	private final String vgroup_name;
	private String vgroup_type = "CONNECTION";
	
	private List<MysqlItem> items;
	
	public VGroup(String vgroup_name) {
		super();
		this.vgroup_name = vgroup_name;
	}

	public static VGroup newVGroup(String vgroup_name){
		return new VGroup(vgroup_name);
	}
	
	public String getVgroup_name() {
		return vgroup_name;
	}

	public String getVgroup_type() {
		return vgroup_type;
	}

	public List<MysqlItem> getItems() {
		if(items == null){
			items = Lists.newArrayList();
		}
		return items;
	}

}
