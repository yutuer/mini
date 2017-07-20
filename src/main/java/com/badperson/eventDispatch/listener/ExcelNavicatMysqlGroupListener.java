package com.badperson.eventDispatch.listener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.badperson.config.Config;
import com.badperson.config.GroupJsonConfig;
import com.badperson.eventDispatch.eventObject.BeginData;
import com.badperson.eventDispatch.eventObject.BeginEventObject;
import com.badperson.eventDispatch.eventObject.RowData;
import com.badperson.eventDispatch.eventObject.RowDataEventObject;
import com.badperson.eventDispatch.eventObject.StaticEndEventObject;
import com.badperson.moduleWrite.interfaces.ITransfer2Model;
import com.badperson.util.FileUtil;
import com.badperson.vo.ExceMysqlGroupJsonParseModel;
import com.badperson.vo.GroupModel;
import com.badperson.vo.MysqlItem;
import com.badperson.vo.VGroup;

@Component
public class ExcelNavicatMysqlGroupListener extends AbstractExcelEventListener {

	@Autowired
	private GroupModel groupModel;

	@Override
	public void onEvent(BeginEventObject eventObject) throws Exception {
		BeginData source = eventObject.getSource();
		String excelName = source.getExcelName();

		List<VGroup> groupList = groupModel.getVgroups();
		VGroup vGroup = VGroup.newVGroup(getGroupName(excelName));
		groupList.add(vGroup);
	}

	@Override
	public void onEvent(RowDataEventObject eventObject) throws Exception {
		RowData source = eventObject.getSource();
		String excelName = source.getExcelName();
		Map<Short, String> map = source.getMap();
		ITransfer2Model<MysqlItem> parseModel = getParseBean(map);

		VGroup vGroup = groupModel.getVGroupByName(getGroupName(excelName));
		vGroup.getItems().add(parseModel.getTransferResult());
	}

	@Override
	public void onEvent(StaticEndEventObject eventObject) throws Exception {
		String outFilePath = getOutputFilePath();
		File file = FileUtil.getFile(outFilePath);

		String json = JSON.toJSONString(groupModel, true);

		try (Writer fileWriter = new OutputStreamWriter(new FileOutputStream(file, true),
				Charset.forName(Config.ENCODING_UTF))) {
			fileWriter.write(json);
		}
	}

	private String getOutputFilePath() {
		return GroupJsonConfig.OUT_DIR + GroupJsonConfig.OUT_FILENAME;
	}

	public ExceMysqlGroupJsonParseModel getParseBean(Map<Short, String> map) {
		ExceMysqlGroupJsonParseModel bean = new ExceMysqlGroupJsonParseModel();
		bean.setDescription(map.get((short) 0));
		return bean;
	}

	private String getGroupName(String fileName) {
		return fileName + "_group";
	}
}
