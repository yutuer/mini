package excelParse.visit.sheet;

import excelParse.visit.row.IRowVisit;

public interface ISheetVisit<RESULT> {

	void visitSheet();

	<T> IRowVisit<T> rowVisit(int rowIndex);

	RESULT getResult();
	
}
