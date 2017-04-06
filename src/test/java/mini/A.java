package mini;

import java.io.FileNotFoundException;
import java.lang.invoke.MethodHandle;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.badperson.util.PropertiesReader;
import com.google.common.collect.Table;

import excelParse.ExcelParse;

public class A {
	
	private static final Logger logger = LoggerFactory.getLogger(A.class);
		
//	@Test
	public void a1(){
		try {
			ExcelParse ep = new ExcelParse("excel/yuenan.xlsx");
			Table<Integer, Short, String> table = ep.parse();
			for(Map<Short, String> map :table.rowMap().values()){
				logger.info(map.toString());
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void a2(){
		String fileName = "port.properties";
		for(String name:PropertiesReader.getAllKeys(fileName)){
			logger.info(name);
		}
	}
}
