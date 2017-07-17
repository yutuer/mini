package com.badperson.moduleWrite.module.redis;

import org.springframework.stereotype.Component;

import com.badperson.moduleWrite.resultWriter.ExcelWriter;

@Component
public class ExcelRedisShellModule extends ExcelWriter {

	@Override
	public String getOutputFilePath() {
		return null;
	}

}
