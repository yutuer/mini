package com.badperson.resultWriter.navicat;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.interfaces.IGroupJsonWriter;
import com.badperson.interfaces.IParseModel;
import com.badperson.vo.ExceMysqlGroupJsonParseModel;
import com.badperson.vo.GroupModel;
import com.badperson.vo.MysqlItem;
import com.badperson.vo.VGroup;
import com.badperson.writerParse.ServerExcelWriter;
import com.google.common.collect.Table;

@Component
public class ExcelNavicatGroupJsonWriter implements IGroupJsonWriter {

	@Autowired
	private GroupModel groupModel;

	private final String fileName;

	public ExcelNavicatGroupJsonWriter(String fileName) {
		super();
		this.fileName = fileName;
	}

	private String getGroupName() {
		return fileName + "_group";
	}

	public void toGroupJson(ServerExcelWriter parse) throws Exception {
		List<VGroup> groupList = groupModel.getVgroups();
		VGroup vGroup = VGroup.newVGroup(getGroupName());
		groupList.add(vGroup);

		Table<Integer, Short, String> tableData = parse.getData();
		for (Integer row : tableData.rowKeySet()) {
			Map<Short, String> map = tableData.row(row);
			IParseModel<MysqlItem> parseModel = getParseBean(map);
			vGroup.getItems().add(parseModel.getParseResult());
		}
	}

	public ExceMysqlGroupJsonParseModel getParseBean(Map<Short, String> map) {
		ExceMysqlGroupJsonParseModel bean = new ExceMysqlGroupJsonParseModel();
		bean.setDescription(map.get((short) 0));
		return bean;
	}

}