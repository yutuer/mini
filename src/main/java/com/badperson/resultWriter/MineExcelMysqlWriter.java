package com.badperson.resultWriter;

import java.util.Map;

import com.badperson.vo.ExcelMysqlParseModel;

public class MineExcelMysqlWriter extends ExcelMysqlWriter{

	protected MineExcelMysqlWriter() {
		super();
	}
	
	public static ExcelMysqlWriter newExcelMysqlWriter() {
		return new MineExcelMysqlWriter();
	}

	@Override
	public ExcelMysqlParseModel getMysqlParseBean(Map<Short, String> map) {
		ExcelMysqlParseModel bean = new ExcelMysqlParseModel();
		bean.setDescription(map.get((short) 0));
		bean.setHost(map.get((short) 1));
		bean.setSourcePort(map.get((short) 3));
		bean.setUserName(map.get((short) 4));
		bean.setPassword(map.get((short) 5));
		return bean;
	}

}
