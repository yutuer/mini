package excelParse;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class ExcelParse<V> extends FileParse<V> {

	private Workbook wb = null;
	
	public ExcelParse(String fileName, ClassLoader cl) {
		super(fileName, cl);
		init();
	}

	public ExcelParse(String fileName) {
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

	public Workbook getWb() {
		return wb;
	}

}
