package com.badperson.vo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.badperson.config.OtherConfig;
import com.badperson.interfaces.IParseModel;
import com.badperson.util.PropertiesReader;

public class GaoyeShellParseModel extends ExcelParseModel implements IParseModel<String> {

	private static final PropertiesReader PR = new PropertiesReader(OtherConfig.GAOYE_CONF);

	private String gaoye;

	private String baseServerId;

	private String sourcePort;

	public String getGaoye() {
		return gaoye;
	}

	public void setGaoye(String gaoye) {
		this.gaoye = gaoye;
	}

	public String getBaseServerId() {
		return baseServerId;
	}

	public void setBaseServerId(String baseServerId) {
		this.baseServerId = baseServerId;
	}

	public String getSourcePort() {
		return sourcePort;
	}

	public void setSourcePort(String sourcePort) {
		this.sourcePort = sourcePort;
	}

	@Override
	public String getParseResult() {
		StringBuilder sb = new StringBuilder();
		if (getGaoye().equals("1")) {
			Pattern compile = Pattern.compile("不良人安卓[混专]服WEB-(\\d{3})\\-?(\\d{3})?服");
			Matcher matcher = compile.matcher(getDescription());
			if (matcher.find()) {
				int hefuServerId = Integer.parseInt(matcher.group(1));
				int trueServerId = hefuServerId;
				if (matcher.group(2) != null) {
					trueServerId = Integer.parseInt(matcher.group(2));
				}
				int sourcePort = Integer.parseInt(getSourcePort());

				int baseServerId = Integer.parseInt(getBaseServerId());
				hefuServerId += baseServerId;
				trueServerId += baseServerId;
				for (int serverId = hefuServerId; serverId <= trueServerId; serverId++) {
					sb.append(String.format(PR.getProperValue("printstr", null), serverId, sourcePort, hefuServerId));
				}
			}
		}
		return sb.toString();
	}

}
