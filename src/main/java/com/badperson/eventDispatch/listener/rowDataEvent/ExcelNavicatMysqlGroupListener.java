package com.badperson.eventDispatch.listener.rowDataEvent;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.eventDispatch.eventObject.RowDataEvent;
import com.badperson.eventDispatch.eventObject.eventSource.RowEventSource;
import com.badperson.eventDispatch.listener.AbstractExcelEventListener;
import com.badperson.moduleWrite.interfaces.ITransfer2Model;
import com.badperson.vo.ExceMysqlGroupJsonParseModel;
import com.badperson.vo.GroupModel;
import com.badperson.vo.MysqlItem;
import com.badperson.vo.VGroup;

@Component
public class ExcelNavicatMysqlGroupListener extends AbstractExcelEventListener<RowDataEvent>{

	@Autowired
	private GroupModel groupModel;
	
	@Override
	public void doHandler(RowDataEvent event) throws Exception {
		RowEventSource source = event.getRowEventSource();
		String excelName = source.getExcelName();
		Map<Short, String> map = source.getMap();
		ITransfer2Model<MysqlItem> parseModel = getParseBean(map);

		VGroup vGroup = groupModel.getVGroupByName(getGroupName(excelName));
		vGroup.getItems().add(parseModel.getTransferResult());
	}
	
	private ExceMysqlGroupJsonParseModel getParseBean(Map<Short, String> map) {
		ExceMysqlGroupJsonParseModel bean = new ExceMysqlGroupJsonParseModel();
		bean.setDescription(map.get((short) 0));
		return bean;
	}
	
	private String getGroupName(String fileName) {
		return fileName + "_group";
	}
	
}
