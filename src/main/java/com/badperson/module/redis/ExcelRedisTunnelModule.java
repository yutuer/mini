package com.badperson.module.redis;

import org.springframework.stereotype.Component;

import com.badperson.config.RedisConfig;
import com.badperson.resultWriter.ExcelWriter;

@Component
public class ExcelRedisTunnelModule extends ExcelWriter {

	private final String fileName;

	public ExcelRedisTunnelModule(String fileName) {
		super();
		this.fileName = fileName;
	}

	public void write() throws Exception {
		
	}

	@Override
	public String getOutputFilePath() {
		return RedisConfig.OUT_DIR + fileName + "_Redis" + RedisConfig.OUT_FILE_SUFFIX;
	}

}
