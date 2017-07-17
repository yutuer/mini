package com.badperson.moduleWrite.container;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.config.RedisConfig;
import com.badperson.moduleWrite.interfaces.IHead;
import com.badperson.moduleWrite.module.redis.ExcelRedisShellModule;

@Component
public class RedisContainer extends AbstractToolWrite {

	// @Autowired
	// private ExcelRedisTunnelModule excelRedisTunnelModule;

	@Autowired
	private ExcelRedisShellModule excelRedisShellModule;

	@Override
	public void afterPropertiesSet() throws Exception {
		heads.add(new IHead() {
			@Override
			public void head() throws Exception {
				File dir = new File(RedisConfig.OUT_DIR);
				if (!dir.exists()) {
					dir.mkdirs();
				}
			}
		});
	}
}
