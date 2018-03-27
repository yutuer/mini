package excelParse.visit.row;

import com.google.common.collect.Table;

import excelParse.visit.cell.ICellVisit;
import excelParse.visit.cell.StringCellVisit;

public class StringRowVisit extends RowVisit<String>{

	public StringRowVisit(int rowIndex, Table<Integer, Short, String> table) {
		super(rowIndex, table);
	}

	@Override
	public ICellVisit<String> visitCell(int index) {
		return new StringCellVisit(index);
	}

}
