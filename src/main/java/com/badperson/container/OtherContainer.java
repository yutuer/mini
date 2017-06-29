package com.badperson.container;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.config.OtherConfig;
import com.badperson.interfaces.IHead;
import com.badperson.module.other.GaoyeModule;

@Component
public class OtherContainer extends AbstractToolWrite {

	@Autowired
	private GaoyeModule gaoyeModule;

	@Override
	protected void init() {
		heads.add(new IHead() {
			@Override
			public void head() throws Exception {
				File dir = new File(OtherConfig.OTHER_OUTPUT);
				if (!dir.exists()) {
					dir.mkdirs();
				}
			}
		});
		heads.add(gaoyeModule);

		actions.add(gaoyeModule);
	}

}
