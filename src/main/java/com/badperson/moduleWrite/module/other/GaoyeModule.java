package com.badperson.moduleWrite.module.other;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.config.OtherConfig;
import com.badperson.moduleWrite.interfaces.IAction;
import com.badperson.moduleWrite.interfaces.IHead;
import com.badperson.moduleWrite.resultWriter.ExcelWriter;
import com.badperson.moduleWrite.resultWriter.other.GaoyeWriter;
import com.badperson.util.FileUtil;
import com.badperson.util.PropertiesReader;
import com.badperson.writerParse.ServerExcelWriter;

@Component
public class GaoyeModule extends ExcelWriter implements IHead, IAction {

	private static final PropertiesReader GaoyeConfigPR = new PropertiesReader(OtherConfig.GAOYE_CONF);

	@Autowired
	private GaoyeWriter gaoyeWriter;

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
				writer.toGaoye(gaoyeWriter);
			}
		}
	}

	@Override
	public void head() throws Exception {
		String outFilePath = getOutputFilePath();
		File file = FileUtil.getFile(outFilePath);
	}

}
