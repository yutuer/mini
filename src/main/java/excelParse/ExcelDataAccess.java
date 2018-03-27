package excelParse;

import com.badperson.moduleWrite.interfaces.IDataAccess;
import com.badperson.moduleWrite.interfaces.IParse;
import com.google.common.collect.Table;

public class ExcelDataAccess implements IDataAccess {

	private IParse<Table<Integer, Short, String>> parse;

	private Table<Integer, Short, String> data;

	public ExcelDataAccess(IParse<Table<Integer, Short, String>> parse) {
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
