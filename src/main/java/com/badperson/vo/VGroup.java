package com.badperson.vo;

import java.util.List;

import com.google.common.collect.Lists;

public class VGroup {
	
	private String vgroup_name;
	private String vgroup_type = "CONNECTION";
	
	private List<Item> items;
	
	public static VGroup newVGroup(String vgroup_name){
		VGroup v = new VGroup();
		v.vgroup_name = vgroup_name;
		return v;
	}
	
	private VGroup() {
		super();
	}

	public String getVgroup_name() {
		return vgroup_name;
	}

	public String getVgroup_type() {
		return vgroup_type;
	}

	public List<Item> getItems() {
		if(items == null){
			items = Lists.newArrayList();
		}
		return items;
	}

}
