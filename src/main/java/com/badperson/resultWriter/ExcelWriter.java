package com.badperson.resultWriter;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.badperson.interfaces.IParseModel;

public abstract class ExcelWriter<V> extends FileWriter {

	public static final Logger logger = LoggerFactory.getLogger(ExcelWriter.class);

	public abstract IParseModel<V> getParseBean(Map<Short, String> map);
}
