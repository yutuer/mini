package com.badperson.eventDispatch.listener.beginEvent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.eventDispatch.eventObject.BeginEvent;
import com.badperson.eventDispatch.listener.AbstractExcelEventListener;
import com.badperson.vo.GroupModel;
import com.badperson.vo.VGroup;

@Component
public class ExcelNavicatMysqlGroupListener_BeginEvent extends AbstractExcelEventListener<BeginEvent> {

	@Autowired
	private GroupModel groupModel;

	@Override
	public void doHandler(BeginEvent event) throws Exception {
		String excelName = event.getName();

		List<VGroup> groupList = groupModel.getVgroups();
		VGroup vGroup = VGroup.newVGroup(getGroupName(excelName));
		groupList.add(vGroup);
	}

	private String getGroupName(String fileName) {
		return fileName + "_group";
	}
}
