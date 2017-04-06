package com.badperson.resultWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.badperson.config.Config;
import com.badperson.config.GroupJsonConfig;
import com.badperson.interfaces.IGroupJsonWriter;
import com.badperson.interfaces.IParseModel;
import com.badperson.vo.ExcelGroupJsonParseModel;
import com.badperson.vo.GroupModel;
import com.badperson.vo.Item;
import com.badperson.vo.VGroup;
import com.badperson.writerParse.ServerExcelWriter;
import com.google.common.collect.Table;

public class ExcelGroupJsonWriter extends ExcelWriter<Item> implements IGroupJsonWriter {

	private static GroupModelWriter _groupModelWriter;

	private final String fileName;

	public static GroupModelWriter getGroupModelWriter() {
		if (_groupModelWriter == null) {
			_groupModelWriter = new GroupModelWriter();
		}
		return _groupModelWriter;
	}

	public static ExcelGroupJsonWriter newExcelGroupJsonWriter(String fileName) {
		return new ExcelGroupJsonWriter(fileName);
	}

	private ExcelGroupJsonWriter(String fileName) {
		super();
		this.fileName = fileName;
	}

	@Override
	public String getOutputFilePath() {
		return GroupJsonConfig.OUT_DIR + GroupJsonConfig.OUT_FILENAME;
	}

	private String getGroupName() {
		return fileName + "_group";
	}

	public void toGroupJson(ServerExcelWriter parse) throws Exception {
		GroupModel groupModel = getGroupModelWriter().getGroupModel();
		List<VGroup> groupList = groupModel.getVgroups();
		VGroup vGroup = VGroup.newVGroup(getGroupName());
		groupList.add(vGroup);

		Table<Integer, Short, String> tableData = parse.parse();
		for (Integer row : tableData.rowKeySet()) {
			Map<Short, String> map = tableData.row(row);
			IParseModel<Item> parseModel = getParseBean(map, 0);
			vGroup.getItems().add(parseModel.getParseResult());
		}
	}

	@Override
	public IParseModel<Item> getParseBean(Map<Short, String> map, int index) {
		ExcelGroupJsonParseModel bean = new ExcelGroupJsonParseModel();
		bean.setDescription(map.get((short) 0));
		return bean;
	}

	public static final class GroupModelWriter {
		private GroupModel _groupModel;

		public GroupModel getGroupModel() {
			if (_groupModel == null) {
				_groupModel = GroupModel.newGroupModel();
			}
			return _groupModel;
		}

		public void writeToFile() {
			Writer fileWriter = null;
			try {
				String outFilePath = newExcelGroupJsonWriter("").getOutputFilePath();
				File file = new File(outFilePath);
				if (file.exists()) {
					file.delete();
				}
				file.createNewFile();

				String json = JSON.toJSONString(getGroupModel(), true);
				fileWriter = new OutputStreamWriter(new FileOutputStream(outFilePath, true), Charset.forName(Config.ENCODING_UTF));
				fileWriter.write(json);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				if (fileWriter != null) {
					try {
						fileWriter.close();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
	}

}
