package com.badperson.vo;

import com.badperson.interfaces.ITransfer2Model;

public class ExcelRedisTunnelJsonParseModel extends ExcelParseModel implements ITransfer2Model<RedisTunnelJsonItem> {

	private String auth;
	private String host = "localhost";
	private String name;
	private int port;

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public RedisTunnelJsonItem getTransferResult() {
		RedisTunnelJsonItem ri = new RedisTunnelJsonItem();
		ri.setAuth(auth);
		ri.setHost(host);
		ri.setName(name);
		ri.setPort(port);
		return ri;
	}

}
