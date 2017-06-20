package com.badperson.container;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.config.OtherConfig;
import com.badperson.interfaces.IToolWrite;
import com.badperson.module.other.GaoyeModule;

@Component
public class OtherContainer implements IToolWrite{

	@Autowired
	private GaoyeModule gaoyeModule;

	public void write() throws Exception {
		{
			File dir = new File(OtherConfig.OTHER_OUTPUT);
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}
		gaoyeModule.write();
	}

}
