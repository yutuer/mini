package com.badperson.module.redis;

import org.springframework.stereotype.Component;

import com.badperson.resultWriter.ExcelWriter;

@Component
public class ExcelRedisShellModule extends ExcelWriter {

	@Override
	public String getOutputFilePath() {
		return null;
	}

}
