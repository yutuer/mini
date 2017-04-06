package com.badperson.interfaces;

import com.badperson.writerParse.ServerExcelWriter;

public interface IGroupJsonWriter {

	void toGroupJson(ServerExcelWriter parse) throws Exception;

}
