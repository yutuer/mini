package com.badperson.interfaces;

import com.badperson.writerParse.ServerExcelWriter;

public interface IMysqlWriter {
	
	void toMysql(ServerExcelWriter parse) throws Exception;
	
}
