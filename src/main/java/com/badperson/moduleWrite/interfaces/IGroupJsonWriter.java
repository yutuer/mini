package com.badperson.moduleWrite.interfaces;

import com.badperson.moduleWrite.writerParse.ServerExcelWriter;

public interface IGroupJsonWriter {

	void toGroupJson(ServerExcelWriter parse) throws Exception;

}
