package com.badperson.vo;

import com.badperson.interfaces.IParseModel;

public class ExcelRedisParseModel extends ExcelParseModel implements IParseModel<RedisItem> {

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
	public RedisItem getParseResult() {
		RedisItem ri = new RedisItem();
		ri.setAuth(auth);
		ri.setHost(host);
		ri.setName(name);
		ri.setPort(port);
		return ri;
	}

}
