package com.badperson.module.xshell;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.interfaces.IMultiplyFile;
import com.badperson.resultWriter.xshell.ExcelMysqlXShellWriter;
import com.badperson.util.FileUtil;
import com.badperson.util.PropertiesReader;
import com.badperson.util.SpringUtil;
import com.badperson.writerParse.ServerExcelWriter;

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
			ServerExcelWriter writer = FileUtil.getWriters().get(excelName);

			writer.toShellTunnel(SpringUtil.getBean(beanFactory, ExcelMysqlXShellWriter.class, excelName));
		}
	}

}
