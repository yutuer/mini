package excelParse.visit.row;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import excelParse.visit.cell.ICellVisit;
import excelParse.visit.sheet.LongquanSheetContext;

public class LongquanRowVisit implements IRowVisit<String> {

	private final Pattern pattern = Pattern.compile("\\D*(\\d+)\\D*");

	private final int colorColumn = 8;
	private int rowIndex;
	private LongquanSheetContext context;

	public LongquanRowVisit(int rowIndex, LongquanSheetContext context) {
		this.rowIndex = rowIndex;
		this.context = context;
	}

	@Override
	public void visitRow(Row row) {
		Cell cell = row.getCell(colorColumn);
		if (cell != null) {
			Cell idCell = row.getCell(0);
			int serverId = extractId(idCell, context.getPageName());

			ICellVisit<String> visitCell = visitCell(colorColumn);
			context.visitCell(visitCell, cell, serverId);
		}
	}

	@Override
	public ICellVisit<String> visitCell(int index) {
		return new ColorCellVisit(index);
	}

	private int extractId(Cell idCell, String pageName) {
		int offset = pageName.equals("专服") ? 5000 : 0;
		String stringCellValue;
		try {
			stringCellValue = idCell.getStringCellValue();
			Matcher matcher = pattern.matcher(stringCellValue);
			if (matcher.find()) {
				String group = matcher.group(1);
				return Integer.valueOf(group) + offset;
			}
			throw new RuntimeException();
		} catch (Exception e) {
			double numericCellValue = idCell.getNumericCellValue();
			return Double.valueOf(numericCellValue).intValue() + offset;
		}
	}

}
