package com.badperson.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.module.navicat.NavicatMysqlGroupWriterModule;
import com.badperson.module.navicat.NavicatMysqlTunnelWriterModule;

@Component
public class NavicatContainer {

	@Autowired
	private NavicatMysqlTunnelWriterModule navicatMysqlTunnelWriter;

	@Autowired
	private NavicatMysqlGroupWriterModule navicatMysqlGroupWriter;

	/**
	 * 包括 数据库隧道端口连接, 以及
	 * 
	 * @throws Exception
	 */
	public void write() throws Exception {
		navicatMysqlTunnelWriter.write();
		navicatMysqlGroupWriter.write();
	}

}
