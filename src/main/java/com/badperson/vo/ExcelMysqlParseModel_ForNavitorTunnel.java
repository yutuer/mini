package com.badperson.vo;

import com.badperson.config.MysqlConfig;
import com.badperson.interfaces.IParseModel;
import com.badperson.module.navicat.NavicatMysqlTunnelWriterModule;

public class ExcelMysqlParseModel_ForNavitorTunnel extends ExcelParseModel implements IParseModel<String> {

	private String host = "localhost";
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

	public void setHost(String host) {
		this.host = host;
	}

	public String getParseResult() {
		String str = new String(NavicatMysqlTunnelWriterModule.ReplaceString) + "\r\n";
		return str.replaceAll(MysqlConfig.FwdFileds[0], getDescription())
				.replaceAll(MysqlConfig.FwdFileds[1], sourcePort).replaceAll(MysqlConfig.FwdFileds[2], userName)
				.replaceAll(MysqlConfig.FwdFileds[3], password).replaceAll(MysqlConfig.FwdFileds[4], host);
	}

}
