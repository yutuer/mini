package com.badperson.module.other;

import java.io.File;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.config.OtherConfig;
import com.badperson.interfaces.IAction;
import com.badperson.interfaces.IHead;
import com.badperson.resultWriter.ExcelWriter;
import com.badperson.resultWriter.other.GaoyeWriter;
import com.badperson.util.FileUtil;
import com.badperson.util.PropertiesReader;
import com.badperson.util.SpringUtil;
import com.badperson.writerParse.ServerExcelWriter;

@Component
public class GaoyeModule extends ExcelWriter implements IHead, IAction, BeanFactoryAware {

	private static final PropertiesReader GaoyeConfigPR = new PropertiesReader(OtherConfig.GAOYE_CONF);

	private BeanFactory beanFactory;

	@Override
	public String getOutputFilePath() {
		return OtherConfig.OTHER_OUTPUT + OtherConfig.GAOYE_NEED_FILE;
	}

	@Override
	public void action() throws Exception {
		PropertiesReader pr = new PropertiesReader(Config.PROP_FILE);
		for (String excelName : pr.getAllKeys()) {
			String value = GaoyeConfigPR.getProperties().getProperty(excelName);
			if (value != null) {
				ServerExcelWriter writer = FileUtil.getWriters().get(excelName);

				writer.toGaoye(SpringUtil.getBean(beanFactory, GaoyeWriter.class, excelName));
			}
		}
	}

	@Override
	public void head() throws Exception {
		String outFilePath = getOutputFilePath();
		File file = FileUtil.getFile(outFilePath);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

}
