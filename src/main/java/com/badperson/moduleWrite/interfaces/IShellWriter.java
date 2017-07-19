package com.badperson.moduleWrite.interfaces;

import com.badperson.moduleWrite.writerParse.ServerExcelWriter;

public interface IShellWriter {
	
	void toShell(ServerExcelWriter parse) throws Exception;

}
