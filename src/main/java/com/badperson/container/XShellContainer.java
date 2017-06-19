package com.badperson.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.module.xshell.ExcelXShellWriterModule;

@Component
public class XShellContainer {

	@Autowired
	private ExcelXShellWriterModule excelXShellWriterModule;

	public void write() throws Exception {
		excelXShellWriterModule.write();
	}
}
