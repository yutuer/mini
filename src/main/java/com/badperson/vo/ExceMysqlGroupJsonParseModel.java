package com.badperson.vo;

import com.badperson.interfaces.ITransfer2Model;

public class ExceMysqlGroupJsonParseModel extends ExcelParseModel implements ITransfer2Model<MysqlItem> {

	public MysqlItem getTransferResult() {
		MysqlItem item = MysqlItem.newItem(getDescription());
		return item;
	}

}
