package com.badperson.vo;

public class RedisItem {

	private String auth;
	private String host = "localhost";
	private String keys_pattern = "*";
	private String name;
	private String namespace_separator = ":";
	private int port;
	private int ssh_port = 22;
	private int timeout_connect = 60000;
	private int timeout_execute = 60000;
	
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
	public String getAuth() {
		return auth;
	}
	public String getHost() {
		return host;
	}
	public String getKeys_pattern() {
		return keys_pattern;
	}
	public String getName() {
		return name;
	}
	public String getNamespace_separator() {
		return namespace_separator;
	}
	public int getPort() {
		return port;
	}
	public int getSsh_port() {
		return ssh_port;
	}
	public int getTimeout_connect() {
		return timeout_connect;
	}
	public int getTimeout_execute() {
		return timeout_execute;
	}
	
	
	
}
