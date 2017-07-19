package com.badperson.moduleWrite.interfaces;

import com.badperson.moduleWrite.writerParse.ServerExcelWriter;

public interface IMysqlWriter {
	
	void toMysql(ServerExcelWriter parse) throws Exception;
	
}
