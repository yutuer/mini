package com.badperson.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.module.redis.ExcelRedisShellModule;
import com.badperson.module.redis.ExcelRedisTunnelModule;

@Component
public class RedisContainer {

	@Autowired
	private ExcelRedisTunnelModule excelRedisTunnelModule;
	
	@Autowired
	private ExcelRedisShellModule excelRedisShellModule;
	
	public void write() throws Exception {
		
	}
}
