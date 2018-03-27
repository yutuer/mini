package excelParse.visit.cell;

import org.apache.poi.ss.usermodel.Cell;

public class StringCellVisit implements ICellVisit<String> {

	private int index;

	public StringCellVisit(int index) {
		this.index = index;
	}

	@Override
	public String visitCell(Cell cell) {
		String value = null;
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			value = String.valueOf((int) cell.getNumericCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			value = cell.getStringCellValue();
		} else {
			throw new RuntimeException(String.format("check cell valueType, row:%d, column:%d, cellType:%d", cell
					.getRow().getRowNum(), cell.getColumnIndex(), cell.getCellType()));
		}
		return value;
	}

}
