package com.badperson.container;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.config.ShellConfig;
import com.badperson.interfaces.IToolWrite;
import com.badperson.module.xshell.ExcelXShellWriterModule;

@Component
public class XShellContainer implements IToolWrite{

	@Autowired
	private ExcelXShellWriterModule excelXShellWriterModule;

	public void write() throws Exception {
		{
			File dir = new File(ShellConfig.OUT_DIR);
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}
		excelXShellWriterModule.write();
	}
}
