package com.badperson.resultWriter;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.badperson.interfaces.IParseModel;

public abstract class ExcelWriter<V> {

	public static final Logger logger = LoggerFactory.getLogger(ExcelWriter.class);

	public abstract String getOutputFilePath();

	public abstract IParseModel<V> getParseBean(Map<Short, String> map, int index);
}
