package excelParse.visit.row;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.google.common.collect.Table;

import excelParse.visit.cell.ICellVisit;

public abstract class RowVisit<V> implements IRowVisit<V> {

	private Table<Integer, Short, V> table;
	private IRowVisit<V> rv;
	private int rowIndex;

	public RowVisit(int rowIndex, Table<Integer, Short, V> table) {
		this.rowIndex = rowIndex;
		this.table = table;
	}

	@Override
	public void visitRow(Row row) {
		if (rv != null) {
			rv.visitRow(row);
		}

		for (short j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
			Cell cell = row.getCell(j);
			if (cell == null) {
				continue;
			}

			ICellVisit<V> visitCell = visitCell(j);
			if (visitCell != null) {
				V cellVaule = visitCell.visitCell(cell);
				table.put(rowIndex, j, cellVaule);
			}
		}
	}

}
