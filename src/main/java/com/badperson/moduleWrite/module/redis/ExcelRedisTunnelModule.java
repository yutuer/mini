package com.badperson.moduleWrite.module.redis;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.badperson.config.RedisConfig;
import com.badperson.moduleWrite.resultWriter.ExcelWriter;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class ExcelRedisTunnelModule extends ExcelWriter {

	private final String fileName;

	public ExcelRedisTunnelModule(String fileName) {
		super();
		this.fileName = fileName;
	}

	@Override
	public String getOutputFilePath() {
		return RedisConfig.OUT_DIR + fileName + "_Redis" + RedisConfig.OUT_FILE_SUFFIX;
	}

}
