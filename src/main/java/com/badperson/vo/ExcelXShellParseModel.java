package com.badperson.vo;

import com.badperson.config.ShellConfig;
import com.badperson.interfaces.IModelWriter;
import com.badperson.interfaces.IParseModel;

public class ExcelXShellParseModel extends ExcelParseModel implements IParseModel<String> {
	private int index;
	private final String incoming = "0";

	private final String source = "localhost";
	private String sourcePort;

	private final String localOnly = "0";

	private String destHost;
	private String destHostPort;
	
	public String getSourcePort() {
		return sourcePort;
	}

	public String getDestHost() {
		return destHost;
	}

	public String getDestHostPort() {
		return destHostPort;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ExcelXShellParseModel() {
		super();
	}

	public void setSourcePort(String sourcePort) {
		this.sourcePort = sourcePort;
	}

	public void setDestHost(String destHost) {
		this.destHost = destHost;
	}

	public void setDestHostPort(String destHostPort) {
		this.destHostPort = destHostPort;
	}

	@Override
	public String getParseResult() {
		StringBuilder sb = new StringBuilder();
		sb.append(ShellConfig.FwdPrefix).append(index).append(ShellConfig.FwdFileds[0]).append(incoming).append("\r\n");
		sb.append(ShellConfig.FwdPrefix).append(index).append(ShellConfig.FwdFileds[1]).append(getDescription()).append("\r\n");
		sb.append(ShellConfig.FwdPrefix).append(index).append(ShellConfig.FwdFileds[2]).append(source).append("\r\n");
		sb.append(ShellConfig.FwdPrefix).append(index).append(ShellConfig.FwdFileds[3]).append(sourcePort).append("\r\n");
		sb.append(ShellConfig.FwdPrefix).append(index).append(ShellConfig.FwdFileds[4]).append(localOnly).append("\r\n");
		sb.append(ShellConfig.FwdPrefix).append(index).append(ShellConfig.FwdFileds[5]).append(destHost).append("\r\n");
		sb.append(ShellConfig.FwdPrefix).append(index).append(ShellConfig.FwdFileds[6]).append(destHostPort).append("\r\n");
		return sb.toString();
	}

}
