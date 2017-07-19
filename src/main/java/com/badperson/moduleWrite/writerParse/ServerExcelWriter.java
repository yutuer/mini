package com.badperson.moduleWrite.writerParse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.badperson.moduleWrite.interfaces.IDataAccess;
import com.badperson.moduleWrite.interfaces.IGroupJsonWriter;
import com.badperson.moduleWrite.interfaces.IMysqlWriter;
import com.badperson.moduleWrite.interfaces.IShellWriter;
import com.badperson.moduleWrite.resultWriter.other.GaoyeWriter;
import com.google.common.collect.Table;

public class ServerExcelWriter {

	public static final Logger logger = LoggerFactory.getLogger(ServerExcelWriter.class);

	private final IDataAccess dataAccess;

	public ServerExcelWriter(IDataAccess dataAccess) {
		super();
		this.dataAccess = dataAccess;
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

	public Table<Integer, Short, String> getData() {
		return dataAccess.getData();
	}

	public void toGaoye(GaoyeWriter gaoyeWriter) {
		try {
			gaoyeWriter.toGaoye(this);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}
