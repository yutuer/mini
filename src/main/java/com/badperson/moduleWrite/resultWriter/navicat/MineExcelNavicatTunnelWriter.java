package com.badperson.moduleWrite.resultWriter.navicat;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.badperson.vo.ExcelMysqlParseModel_ForNavitorTunnel;

@Component
public class MineExcelNavicatTunnelWriter extends ExcelNavitorTunnelWriter {

	protected MineExcelNavicatTunnelWriter() {
		super();
	}

	@Override
	public ExcelMysqlParseModel_ForNavitorTunnel getMysqlParseBean(Map<Short, String> map) {
		ExcelMysqlParseModel_ForNavitorTunnel bean = new ExcelMysqlParseModel_ForNavitorTunnel();
		bean.setDescription(map.get((short) 0));
		bean.setHost(map.get((short) 1));
		bean.setSourcePort(map.get((short) 3));
		bean.setUserName(map.get((short) 4));
		bean.setPassword(map.get((short) 5));
		return bean;
	}

}
