package com.badperson;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.badperson.eventDispatch.ExcelParseContext;
import com.badperson.moduleWrite.interfaces.IToolWrite;

/**
 * 导出xshell、mysql连接配置
 * 
 * @author Administrator
 *
 */
@Component
public class App {

	private static final Logger logger = LoggerFactory.getLogger(App.class);

	@Autowired
	private ExcelParseContext excelParseContext;
	
	@Autowired
	private List<IToolWrite> toolWriteList;

	public void action() throws Exception {
//		FileUtil.init();
//		for (IToolWrite iToolWrite : toolWriteList) {
//			iToolWrite.write();
//		}
		
		excelParseContext.parse();
		
	}

	public static void main(String[] args) throws Exception {
		String[] configs = new String[] { "classpath:anno.xml" };
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configs)) {
			logger.info("parse begin");
			App app = context.getBean(App.class);
			app.action();

			logger.info("parse end success");
		}
	}

}
