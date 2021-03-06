package com.badperson.moduleWrite.module.xshell;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.moduleWrite.interfaces.IAction;
import com.badperson.moduleWrite.resultWriter.xshell.ExcelMysqlXShellWriter;
import com.badperson.moduleWrite.writerParse.ServerExcelWriter;
import com.badperson.util.FileUtil;
import com.badperson.util.PropertiesReader;
import com.badperson.util.SpringUtil;

@Component
public class ExcelXShellWriterModule implements IAction, BeanFactoryAware {

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
		PropertiesReader pr = new PropertiesReader(Config.PORT_FILE);
		for (String excelName : pr.getAllKeys()) {
			ServerExcelWriter writer = FileUtil.getWriters().get(excelName);

			writer.toShellTunnel(SpringUtil.getBean(beanFactory, ExcelMysqlXShellWriter.class, excelName));
		}
	}

}
