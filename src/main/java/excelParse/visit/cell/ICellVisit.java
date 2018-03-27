package excelParse.visit.cell;

import org.apache.poi.ss.usermodel.Cell;

public interface ICellVisit<V> {
	
	V visitCell(Cell cell);
	
}
