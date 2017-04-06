package com.badperson.interfaces;

import com.badperson.writerParse.ServerExcelWriter;

public interface IShellWriter {
	
	void toShell(ServerExcelWriter parse) throws Exception;

}
