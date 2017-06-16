package com.badperson.vo;

import com.badperson.interfaces.IParseModel;

public class ExceMysqlGroupJsonParseModel extends ExcelParseModel implements IParseModel<MysqlItem> {

	public MysqlItem getParseResult() {
		MysqlItem item = MysqlItem.newItem(getDescription());
		return item;
	}

}
