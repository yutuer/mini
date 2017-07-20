package com.badperson.vo;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;

@Component
public class GroupModel {

	private String version = "1.1";

	private List<VGroup> vgroups;

	private void addDefault() {
		VGroup vGroup = VGroup.newVGroup("mine");

		vGroup.getItems().add(MysqlItem.newItem("172.16.1.65"));
		vGroup.getItems().add(MysqlItem.newItem("172.16.1.56"));
		vGroup.getItems().add(MysqlItem.newItem("172.16.1.64"));
		vGroup.getItems().add(MysqlItem.newItem("localhost"));
	}

	public GroupModel() {
		super();
		addDefault();
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

	public VGroup getVGroupByName(String name) {
		if (StringUtils.isEmpty(name))
			return null;
		for (VGroup vGroup : vgroups) {
			if (name.equals(vGroup.getVgroup_name())) {
				return vGroup;
			}
		}
		return null;
	}

}
