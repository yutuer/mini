package excelParse.visit.row;

import org.apache.poi.ss.usermodel.Row;

import excelParse.visit.cell.ICellVisit;

public interface IRowVisit<V> {

	void visitRow(Row row);

	ICellVisit<V> visitCell(int index);

}
