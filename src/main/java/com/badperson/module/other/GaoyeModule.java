package com.badperson.module.other;

import interfaces.IDataAccess;
import interfaces.IParse;

import java.io.File;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.config.OtherConfig;
import com.badperson.interfaces.ISingleFile;
import com.badperson.resultWriter.ExcelWriter;
import com.badperson.resultWriter.other.GaoyeWriter;
import com.badperson.util.FileUtil;
import com.badperson.util.PropertiesReader;
import com.badperson.util.SpringUtil;
import com.badperson.writerParse.ServerExcelWriter;

import excelParse.ExcelDataAccess;
import excelParse.ExcelParse;

@Component
public class GaoyeModule extends ExcelWriter implements ISingleFile, BeanFactoryAware {

	private static final PropertiesReader GaoyeConfigPR = new PropertiesReader(OtherConfig.GAOYE_CONF);

	private BeanFactory beanFactory;

	@Override
	public String getOutputFilePath() {
		return OtherConfig.OTHER_OUTPUT + OtherConfig.GAOYE_NEED_FILE;
	}

	public void write() throws Exception {
		head();
		action();
		tail();
	}

	@Override
	public void action() throws Exception {
		PropertiesReader pr = new PropertiesReader(Config.PROP_FILE);
		for (String excelName : pr.getAllKeys()) {
			String value = GaoyeConfigPR.getProperties().getProperty(excelName);
			if (value != null) {
				String trueExcelFileName = Config.EXCEL_DIR + excelName + ".xlsx";
				IParse parse = new ExcelParse(trueExcelFileName);
				IDataAccess dataAccess = new ExcelDataAccess(parse);
				ServerExcelWriter writer = new ServerExcelWriter(dataAccess);

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
	public void tail() throws Exception {

	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

}
