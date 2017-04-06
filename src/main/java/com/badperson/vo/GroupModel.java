package com.badperson.vo;

import java.util.List;

import com.google.common.collect.Lists;

public class GroupModel {

	private String version = "1.1";

	private List<VGroup> vgroups;

	public static GroupModel newGroupModel(){
		GroupModel groupModel = new GroupModel();
		groupModel.addDefault();
		return groupModel;
	}
	
	private void addDefault() {
		VGroup vGroup = VGroup.newVGroup("mine");
		
		vGroup.getItems().add(Item.newItem("172.16.1.65"));
		vGroup.getItems().add(Item.newItem("172.16.1.56"));
		vGroup.getItems().add(Item.newItem("172.16.1.64"));
		vGroup.getItems().add(Item.newItem("localhost"));
		
	}

	private GroupModel() {
		super();
	}

	public String getVersion() {
		return version;
	}

	public List<VGroup> getVgroups() {
		if (vgroups == null) {
			vgroups = Lists.newArrayList();
		}
		return vgroups;
	}

}
