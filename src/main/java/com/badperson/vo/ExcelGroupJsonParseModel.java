package com.badperson.vo;

import com.badperson.interfaces.IModelWriter;
import com.badperson.interfaces.IParseModel;

public class ExcelGroupJsonParseModel extends ExcelParseModel implements IParseModel<Item> {

	public Item getParseResult() {
		Item item = Item.newItem(getDescription());
		return item;
	}

	@Override
	public void write(IModelWriter sw) {
		
	}

}
