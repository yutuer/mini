package com.badperson.module.navicat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.badperson.config.Config;
import com.badperson.config.GroupJsonConfig;
import com.badperson.interfaces.IAction;
import com.badperson.interfaces.IHead;
import com.badperson.interfaces.ITail;
import com.badperson.resultWriter.ExcelWriter;
import com.badperson.resultWriter.navicat.ExcelNavicatGroupJsonWriter;
import com.badperson.util.FileUtil;
import com.badperson.util.PropertiesReader;
import com.badperson.util.SpringUtil;
import com.badperson.vo.GroupModel;
import com.badperson.writerParse.ServerExcelWriter;

@Component
public class NavicatMysqlGroupWriterModule extends ExcelWriter implements IHead, IAction, ITail, BeanFactoryAware {

	@Autowired
	private GroupModel groupModel;

	private BeanFactory beanFactory;

	public void write() throws Exception {
		head();
		action();
		tail();
	}

	private void writeToFile() {
		Writer fileWriter = null;
		try {
			String outFilePath = getOutputFilePath();
			File file = FileUtil.getFile(outFilePath);

			String json = JSON.toJSONString(groupModel, true);
			fileWriter = new OutputStreamWriter(new FileOutputStream(file, true), Charset.forName(Config.ENCODING_UTF));
			fileWriter.write(json);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	@Override
	public String getOutputFilePath() {
		return GroupJsonConfig.OUT_DIR + GroupJsonConfig.OUT_FILENAME;
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

			// 分组的单个
			writer.toGroupJson(SpringUtil.getBean(beanFactory, ExcelNavicatGroupJsonWriter.class, excelName));
		}
	}

	@Override
	public void head() throws Exception {

	}

	@Override
	public void tail() throws Exception {
		writeToFile();
	}
}
