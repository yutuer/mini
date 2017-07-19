package com.badperson.eventDispatch.eventObject;

import java.util.Map;

import com.google.common.collect.Maps;

public class RowData {
	private final String excelName;
	private final int row;
	private final Map<Short, String> map;

	public RowData(String excelName, int row, Map<Short, String> map) {
		super();
		this.excelName = excelName;
		this.row = row;
		this.map = Maps.newHashMap(map);
	}

	public String getExcelName() {
		return excelName;
	}

	public int getRow() {
		return row;
	}

	public Map<Short, String> getMap() {
		return map;
	}

}
