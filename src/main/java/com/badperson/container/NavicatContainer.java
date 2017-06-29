package com.badperson.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.module.navicat.NavicatMysqlGroupWriterModule;
import com.badperson.module.navicat.NavicatMysqlTunnelWriterModule;

@Component
public class NavicatContainer extends AbstractToolWrite {

	@Autowired
	private NavicatMysqlTunnelWriterModule navicatMysqlTunnelWriter;

	@Autowired
	private NavicatMysqlGroupWriterModule navicatMysqlGroupWriter;

	@Override
	protected void init() {
		heads.add(navicatMysqlTunnelWriter);
		heads.add(navicatMysqlGroupWriter);

		actions.add(navicatMysqlTunnelWriter);
		actions.add(navicatMysqlGroupWriter);

		tails.add(navicatMysqlTunnelWriter);
		tails.add(navicatMysqlGroupWriter);
	}

}
