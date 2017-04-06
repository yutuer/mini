package com.badperson.writerParse;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.badperson.interfaces.IGroupJsonWriter;
import com.badperson.interfaces.IMysqlWriter;
import com.badperson.interfaces.IShellWriter;
import com.google.common.collect.Table;

import excelParse.IParse;

public class ServerExcelWriter implements IParse {

	public static final Logger logger = LoggerFactory.getLogger(ServerExcelWriter.class);

	private final IParse parse;

	private Table<Integer, Short, String> tableData;

	public ServerExcelWriter(IParse parse) {
		super();
		this.parse = parse;
	}

	public InputStream getInputStream() {
		return parse.getInputStream();
	}

	public void toShellTunnel(IShellWriter shellWriter) {
		try {
			shellWriter.toShell(this);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public void toMysql(IMysqlWriter mysqlWriter) {
		try {
			mysqlWriter.toMysql(this);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public void toGroupJson(IGroupJsonWriter groupJsonWriter) {
		try {
			groupJsonWriter.toGroupJson(this);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public Table<Integer, Short, String> parse() {
		if (tableData == null) {
			tableData = parse.parse();
		}
		return tableData;
	}

}
