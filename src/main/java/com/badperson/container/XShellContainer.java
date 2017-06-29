package com.badperson.container;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.config.ShellConfig;
import com.badperson.interfaces.IHead;
import com.badperson.module.xshell.ExcelXShellWriterModule;

@Component
public class XShellContainer extends AbstractToolWrite {

	@Autowired
	private ExcelXShellWriterModule excelXShellWriterModule;

	@Override
	protected void init() {
		heads.add(new IHead() {
			@Override
			public void head() throws Exception {
				File dir = new File(ShellConfig.OUT_DIR);
				if (!dir.exists()) {
					dir.mkdirs();
				}
			}
		});

		actions.add(excelXShellWriterModule);
	}
}
