package com.badperson.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.module.navicat.NavicatMysqlGroupWriterModule;
import com.badperson.module.navicat.NavicatMysqlTunnelWriterModule;

@Component
public class NavicatContainer extends AbstractToolWrite {

	@Autowired
	private NavicatMysqlTunnelWriterModule navicatMysqlTunnelWriterModule;

	@Autowired
	private NavicatMysqlGroupWriterModule navicatMysqlGroupWriterModule;

	public NavicatContainer() {
		super();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		heads.add(navicatMysqlTunnelWriterModule);
		heads.add(navicatMysqlGroupWriterModule);

		actions.add(navicatMysqlTunnelWriterModule);
		actions.add(navicatMysqlGroupWriterModule);

		tails.add(navicatMysqlTunnelWriterModule);
		tails.add(navicatMysqlGroupWriterModule);
	}

}
