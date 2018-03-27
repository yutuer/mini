package excelParse;

import org.apache.poi.ss.usermodel.Sheet;

import com.google.common.collect.Table;

import excelParse.visit.sheet.ISheetVisit;
import excelParse.visit.sheet.StringSheetVisit;

public class StringSinglePageExcelParse extends SinglePageExcelParse<String> {

	public StringSinglePageExcelParse(String fileName) {
		super(fileName);
	}

	@Override
	protected ISheetVisit<Table<Integer, Short, String>> sheetVisit(Sheet sheet) {
		return new StringSheetVisit(sheet);
	}

}
