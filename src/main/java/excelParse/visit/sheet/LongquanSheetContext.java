package excelParse.visit.sheet;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import com.google.common.collect.Lists;

import excelParse.LongquanGroupPair;
import excelParse.Pair;
import excelParse.visit.cell.ICellVisit;

public class LongquanSheetContext {

	private final List<Pair<Integer, Integer>> ret = Lists.newArrayList();
	private String pageName;
	private String lastColorHex;
	private LongquanGroupPair<Integer> lastLongquanGroupPair;

	public LongquanSheetContext(String pageName) {
		super();
		this.pageName = pageName;
	}

	public String getPageName() {
		return pageName;
	}

	public void add(LongquanGroupPair<Integer> pair) {
		ret.add(pair);
	}

	public void visitCell(ICellVisit<String> visitCell, Cell cell, int serverId) {
		String colorHex = visitCell.visitCell(cell);
		if (!colorHex.equals(lastColorHex)) {
			lastColorHex = colorHex;
			lastLongquanGroupPair = new LongquanGroupPair<Integer>(Integer.MAX_VALUE, Integer.MIN_VALUE);
			add(lastLongquanGroupPair);
		}
		lastLongquanGroupPair.fill(serverId);
	}

	public List<Pair<Integer, Integer>> getRet() {
		return ret;
	}
	
}
