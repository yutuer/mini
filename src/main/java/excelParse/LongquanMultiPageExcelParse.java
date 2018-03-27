package excelParse;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import com.google.common.collect.Lists;

import excelParse.visit.sheet.LongquanSheetVisit;

public class LongquanMultiPageExcelParse extends MultiPageAssembleExcelParse<Pair<Integer, Integer>>{

	public LongquanMultiPageExcelParse(String fileName, String[] pageNames) {
		super(fileName, pageNames);
	}

	@Override
	protected List<Pair<Integer, Integer>> parse(String pageName) {
		Sheet sheet = getWb().getSheet(pageName);
		if (sheet != null) {
			LongquanSheetVisit longquanSheetVisit = new LongquanSheetVisit(sheet, pageName);
			longquanSheetVisit.visitSheet();
			List<Pair<Integer, Integer>> result = longquanSheetVisit.getResult();
			return result;
		}
		return Lists.newArrayList();
	}

}
