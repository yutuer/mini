package excelParse.visit.row;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

import excelParse.visit.cell.ICellVisit;

public class ColorCellVisit implements ICellVisit<String> {

	private int index;

	public ColorCellVisit(int index) {
		this.index = index;
	}

	@Override
	public String visitCell(Cell cell) {
		CellStyle cellStyle = cell.getCellStyle();
		XSSFCellStyle cast = XSSFCellStyle.class.cast(cellStyle);
		XSSFColor XSSFColor = cast.getFillForegroundXSSFColor();
		String colorHex = XSSFColor.getARGBHex();
		return colorHex;
	}

}
