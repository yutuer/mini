package com.badperson.interfaces;

import java.util.Map;

public interface IRowAction {

	Object rowAction(Map<Short, String> map) throws Exception;
	
	void parseResult(Object obj);
}
