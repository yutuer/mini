package excelParse;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class ExcelParse extends FileParse {

	private Workbook wb = null;

	public ExcelParse(String fileName, ClassLoader cl) {
		super(fileName, cl);
		init();
	}

	public ExcelParse(String fileName) throws FileNotFoundException {
		this(fileName, null);
	}

	private void init() {
		if (!(fileName.endsWith(".xls") || fileName.endsWith(".xlsx")))
			throw new RuntimeException("inputFile error");
		boolean flag = fileName.endsWith(".xls") ? true : false;

		try {
			if (flag) {
				wb = new HSSFWorkbook(new POIFSFileSystem(getInputStream()));
			} else {
				wb = new XSSFWorkbook(getInputStream());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Table<Integer, Short, String> parse() {
		Table<Integer, Short, String> table = HashBasedTable.create();

		Sheet sheet = wb.getSheetAt(0);
		if (sheet == null)
			return table;

		for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			for (short j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				if(cell == null){
					continue;
				}
				String value = null;
				if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					value = String.valueOf((int)cell.getNumericCellValue());
				} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					value = cell.getStringCellValue();
				} else {
					throw new RuntimeException(String.format("check cell valueType, row:%d, column:%d, cellType:%d", 
							cell.getRow().getRowNum(), cell.getColumnIndex(), cell.getCellType()));
				}
				table.put(i, j, value);
			}
		}
		return table;
	}

}
