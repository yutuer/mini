package excelParse;

import org.apache.poi.ss.usermodel.Sheet;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import excelParse.visit.sheet.ISheetVisit;

public abstract class SinglePageExcelParse<V> extends ExcelParse<Table<Integer, Short, V>> {

	public SinglePageExcelParse(String fileName) {
		super(fileName);
	}

	@Override
	public Table<Integer, Short, V> parse() {
		Sheet sheet = getWb().getSheetAt(0);
		if (sheet == null)
			return HashBasedTable.create();

		ISheetVisit<Table<Integer, Short, V>> sheetVisit = sheetVisit(sheet);
		sheetVisit.visitSheet();

		return sheetVisit.getResult();
	}

	protected abstract ISheetVisit<Table<Integer, Short, V>> sheetVisit(Sheet sheet);
}
