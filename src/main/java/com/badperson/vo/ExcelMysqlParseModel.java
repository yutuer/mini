package com.badperson.vo;

import com.badperson.config.MysqlConfig;
import com.badperson.interfaces.IParseModel;
import com.badperson.resultWriter.ExcelMysqlWriter;

public class ExcelMysqlParseModel extends ExcelParseModel implements IParseModel<String> {
	
	private String sourcePort;
	
	// 15057D7BA390, root
	private String userName = "badperson";
	private String password = "17FD5DB20A57B8EB";
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setSourcePort(String sourcePort) {
		this.sourcePort = sourcePort;
	}

	public String getParseResult() {
		String str = new String(ExcelMysqlWriter.ExcelMysqlSpecialWriter.ReplaceString) + "\r\n";
		return str.replaceAll(MysqlConfig.FwdFileds[0], getDescription()).replaceAll(MysqlConfig.FwdFileds[1], sourcePort)
				.replaceAll(MysqlConfig.FwdFileds[2], userName).replaceAll(MysqlConfig.FwdFileds[3], password);
	}

}
