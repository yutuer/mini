package com.badperson.container;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.config.RedisConfig;
import com.badperson.interfaces.IToolWrite;
import com.badperson.module.redis.ExcelRedisShellModule;
import com.badperson.module.redis.ExcelRedisTunnelModule;

@Component
public class RedisContainer implements IToolWrite{

	@Autowired
	private ExcelRedisTunnelModule excelRedisTunnelModule;

	@Autowired
	private ExcelRedisShellModule excelRedisShellModule;

	public void write() throws Exception {
		{
			File dir = new File(RedisConfig.OUT_DIR);
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}
		
	}
}
