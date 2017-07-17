package com.badperson.moduleWrite.container;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.config.OtherConfig;
import com.badperson.moduleWrite.interfaces.IHead;
import com.badperson.moduleWrite.interfaces.IRowAction;
import com.badperson.moduleWrite.interfaces.ITail;
import com.badperson.moduleWrite.module.other.GaoyeModule;
import com.badperson.util.FileUtil;
import com.badperson.util.PropertiesReader;
import com.badperson.writerParse.ServerExcelWriter;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

@Component
public class OtherContainer extends AbstractToolWrite {

	private static final Logger logger = LoggerFactory.getLogger(OtherContainer.class);

	@Autowired
	private GaoyeModule gaoyeModule;

	private List<IRowAction> rowActions = Lists.newArrayList();

	public boolean register(IRowAction rowAction) {
		for (IRowAction iRowAction : rowActions) {
			if (iRowAction.getClass() == rowAction.getClass()) {
				logger.error("register rowAction error:" + rowAction.getClass());
				return false;
			}
		}
		rowActions.add(rowAction);
		logger.info("register rowAction success:" + rowAction.getClass());
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
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

		tails.add(new ITail() {
			@Override
			public void tail() throws Exception {
				PropertiesReader pr = new PropertiesReader(Config.PROP_FILE);
				for (String excelName : pr.getAllKeys()) {
					ServerExcelWriter parse = FileUtil.getWriters().get(excelName);
					Table<Integer, Short, String> tableData = parse.getData();
					for (Integer row : tableData.rowKeySet()) {
						Map<Short, String> map = tableData.row(row);

						for (IRowAction iRowAction : rowActions) {
							Object obj = iRowAction.rowAction(map);
							iRowAction.parseResult(obj);
						}
					}
				}
			}
		});
	}
}
