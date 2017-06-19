package com.badperson.vo;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.badperson.interfaces.IParseModel;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
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
