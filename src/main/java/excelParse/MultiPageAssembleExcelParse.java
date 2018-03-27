package excelParse;

import java.util.List;

import com.google.common.collect.Lists;

public abstract class MultiPageAssembleExcelParse<T> extends ExcelParse<List<T>> {

	private String[] pageNames;

	public MultiPageAssembleExcelParse(String fileName, String[] pageNames) {
		super(fileName, null);
		this.pageNames = pageNames;
	}

	protected String[] getPageNames() {
		return pageNames;
	}

	@Override
	public List<T> parse() {
		List<T> list = Lists.newArrayList();
		for (String pageName : pageNames) {
			list.addAll(parse(pageName));
		}
		return list;
	}

	protected abstract List<T> parse(String pageName);
}
