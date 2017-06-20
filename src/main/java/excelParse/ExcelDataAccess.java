package excelParse;

import com.badperson.interfaces.IDataAccess;
import com.badperson.interfaces.IParse;
import com.google.common.collect.Table;

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
