package com.badperson.moduleWrite.interfaces;

import com.google.common.collect.Table;

public interface IDataAccess {
	
	Table<Integer, Short, String> getData();
}
