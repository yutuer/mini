package com.badperson.module.xshell;

import interfaces.IDataAccess;
import interfaces.IParse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.interfaces.IMultiplyFile;
import com.badperson.resultWriter.xshell.ExcelXShellWriter;
import com.badperson.util.PropertiesReader;
import com.badperson.util.SpringUtil;
import com.badperson.writerParse.ServerExcelWriter;

import excelParse.ExcelDataAccess;
import excelParse.ExcelParse;

@Component
public class ExcelXShellWriterModule implements IMultiplyFile, BeanFactoryAware {

	private BeanFactory beanFactory;

	public void write() throws Exception {
		action();
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	@Override
	public void action() throws Exception {
		PropertiesReader pr = new PropertiesReader(Config.PROP_FILE);
		for (String excelName : pr.getAllKeys()) {
			String trueExcelFileName = Config.EXCEL_DIR + excelName + ".xlsx";
			IParse parse = new ExcelParse(trueExcelFileName);
			IDataAccess dataAccess = new ExcelDataAccess(parse);
			ServerExcelWriter writer = new ServerExcelWriter(dataAccess);

			writer.toShellTunnel(SpringUtil.getBean(beanFactory, ExcelXShellWriter.class, excelName));
		}
	}

}
