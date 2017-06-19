package com.badperson;

import interfaces.IDataAccess;
import interfaces.IParse;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.config.RedisConfig;
import com.badperson.config.ShellConfig;
import com.badperson.container.NavicatContainer;
import com.badperson.resultWriter.mysql.ExcelMysqlSpecialWriter;
import com.badperson.resultWriter.mysql.GroupModelWriter;
import com.badperson.resultWriter.navicat.ExcelNavicatGroupJsonWriter;
import com.badperson.resultWriter.navicat.MineExcelNavicatTunnelWriter;
import com.badperson.resultWriter.redis.ExcelRedisShellWriter;
import com.badperson.resultWriter.xshell.ExcelXShellWriter;
import com.badperson.util.PropertiesReader;
import com.badperson.util.SpringUtil;
import com.badperson.writerParse.ServerExcelWriter;

import excelParse.ExcelDataAccess;
import excelParse.ExcelParse;

/**
 * 导出xshell、mysql连接配置
 * 
 * @author Administrator
 *
 */
@Component
public class App implements BeanFactoryAware {

	private static final Logger logger = LoggerFactory.getLogger(App.class);

	@Autowired
	private NavicatContainer navicatContainer;
	
	@Autowired
	private MineExcelNavicatTunnelWriter mineExcelMysqlWriter;

	@Autowired
	private GroupModelWriter groupModelWriter;

	private BeanFactory beanFactory;

	public void action() throws Exception {
		navicatContainer.write();
		
		
		PropertiesReader pr = new PropertiesReader(Config.PROP_FILE);
		for (String excelName : pr.getAllKeys()) {
			String trueExcelFileName = Config.EXCEL_DIR + excelName + ".xlsx";
			IParse parse = new ExcelParse(trueExcelFileName);
			IDataAccess dataAccess = new ExcelDataAccess(parse);
			ServerExcelWriter writer = new ServerExcelWriter(dataAccess);

			if ("mine".equals(excelName)) {
				writer.toMysql(mineExcelMysqlWriter);
			} else {
				writer.toShellTunnel(SpringUtil.getBean(beanFactory, ExcelXShellWriter.class, excelName));
				writer.toMysql(mineExcelMysqlWriter);
				writer.toShellTunnel(SpringUtil.getBean(beanFactory, ExcelRedisShellWriter.class, excelName));
			}
			writer.toGroupJson(ExcelNavicatGroupJsonWriter.newExcelGroupJsonWriter(excelName));
		}
	}

	public void tail() throws Exception {
		groupModelWriter.writeToFile();
	}

	public static void main(String[] args) throws Exception {
		{
			File dir = new File(ShellConfig.OUT_DIR);
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}
		{
			File dir = new File(RedisConfig.OUT_DIR);
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}
		{
			File dir = new File(ShellConfig.OTHER_OUTPUT);
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}

		String[] configs = new String[] { "classpath:anno.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(configs);

		logger.info("parse begin");
		App app = context.getBean(App.class);
		app.action();

		app.tail();

		logger.info("parse end success");
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
}
