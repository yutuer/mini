package com.badperson;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.badperson.interfaces.IToolWrite;
import com.badperson.util.FileUtil;

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
	private List<IToolWrite> toolWriteList;

	public void action() throws Exception {
		for (IToolWrite iToolWrite : toolWriteList) {
			iToolWrite.write();
		}
	}

	public static void main(String[] args) throws Exception {
		FileUtil.init();

		String[] configs = new String[] { "classpath:anno.xml" };
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configs)) {
			logger.info("parse begin");
			App app = context.getBean(App.class);
			app.action();

			logger.info("parse end success");
		}
	}

}
