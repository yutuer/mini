package com.badperson.eventDispatch.listener;

import java.io.File;

import org.springframework.stereotype.Component;

import com.badperson.config.MysqlConfig;
import com.badperson.config.ShellConfig;
import com.badperson.eventDispatch.eventObject.StaticBeginEventObject;

@Component
public class CreateDirListener extends AbstractExcelEventListener {

	@Override
	public void onEvent(StaticBeginEventObject eventObject) throws Exception {
		{
			File dir = new File(MysqlConfig.OUT_DIR);
			dir.delete();
			dir.mkdirs();
		}
		{
			File dir = new File(ShellConfig.OUT_DIR);
			dir.delete();
			dir.mkdirs();
		}
	}

}
