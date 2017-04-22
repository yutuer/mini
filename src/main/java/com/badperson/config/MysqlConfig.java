package com.badperson.config;

public class MysqlConfig {

	public static final String OUT_DIR = "mysql/";
	public static final String OUT_FILENAME = "connections.ncx";
	public static final String TEMPLATE_FILENAME = Config.CONF_PREFIX + "mysqlModel.txt";

	public static final String FirstLine = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n";
	public static final String SecondLine = "<Connections Ver=\"1.1\">\r\n";
	public static final String LastLine = "</Connections>";

	public static final String[] FwdFileds = { "\\$\\{name\\}", "\\$\\{port\\}", "\\$\\{userName\\}", "\\$\\{password\\}" ,"\\$\\{host\\}"};
}
