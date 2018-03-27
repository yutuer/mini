package excelParse.visit.sheet;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import excelParse.Pair;
import excelParse.visit.row.LongquanRowVisit;

public class LongquanSheetVisit implements ISheetVisit<List<Pair<Integer, Integer>>> {

	private Sheet sheet;
	private LongquanSheetContext context;

	public LongquanSheetVisit(Sheet sheet, String pageName) {
		super();
		this.sheet = sheet;
		context = new LongquanSheetContext(pageName);
	}

	@Override
	public void visitSheet() {
		for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			Cell idCell = row.getCell(0);
			if (idCell == null) {
				break;
			}
			
			LongquanRowVisit rowVisit = rowVisit(i);
			rowVisit.visitRow(row);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public LongquanRowVisit rowVisit(int rowIndex) {
		return new LongquanRowVisit(rowIndex, context);
	}

	@Override
	public List<Pair<Integer, Integer>> getResult() {
		return context.getRet();
	}

}
