package excelParse.visit.sheet;

import org.apache.poi.ss.usermodel.Sheet;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import excelParse.visit.row.IRowVisit;
import excelParse.visit.row.StringRowVisit;

public class StringSheetVisit implements ISheetVisit<Table<Integer, Short, String>> {

	private Table<Integer, Short, String> table = HashBasedTable.create();
	private Sheet sheet;

	public StringSheetVisit(Sheet sheet) {
		super();
		this.sheet = sheet;
	}

	@Override
	public void visitSheet() {
		for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
			IRowVisit<String> rowVisit = rowVisit(i);
			rowVisit.visitRow(sheet.getRow(i));
		}
	}

	@Override
	public IRowVisit<String> rowVisit(int rowIndex) {
		return new StringRowVisit(rowIndex, table);
	}

	@Override
	public Table<Integer, Short, String> getResult() {
		return table;
	}

}
