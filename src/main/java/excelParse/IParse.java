package excelParse;

import java.io.InputStream;

import com.google.common.collect.Table;

public interface IParse {
	
	Table<Integer, Short, String> parse();
	
	InputStream getInputStream();
	
}