package excelParse;

import com.google.common.collect.Table;

import interfaces.IDataAccess;
import interfaces.IParse;

public class ExcelDataAccess implements IDataAccess {

	private IParse parse;

	private Table<Integer, Short, String> data;

	public ExcelDataAccess(IParse parse) {
		super();
		this.parse = parse;
	}

	@Override
	public Table<Integer, Short, String> getData() {
		if (data == null) {
			data = parse.parse();
		}
		return data;
	}

}
