package com.badperson.resultWriter;

import java.util.Map;

import com.badperson.config.MysqlConfig;
import com.badperson.interfaces.IMysqlWriter;
import com.badperson.interfaces.IParseModel;
import com.badperson.vo.ExcelMysqlParseModel;

public class MineExcelMysqlWriter extends ExcelMysqlWriter implements IMysqlWriter {

	protected MineExcelMysqlWriter() {
		super();
	}
	
	public static ExcelMysqlWriter newExcelMysqlWriter() {
		return new MineExcelMysqlWriter();
	}

	@Override
	public IParseModel<String> getParseBean(Map<Short, String> map, int index) {
		ExcelMysqlParseModel bean = new ExcelMysqlParseModel();
		bean.setDescription(map.get((short) 0));
		bean.setUserName(map.get((short) 4));
		bean.setPassword(map.get((short) 5));
		bean.setSourcePort(map.get((short) 3));
		return bean;
	}

	@Override
	public String getOutputFilePath() {
		return MysqlConfig.OUT_DIR + MysqlConfig.OUT_FILENAME;
	}

}
