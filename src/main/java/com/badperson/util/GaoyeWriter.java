package com.badperson.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.badperson.config.Config;
import com.badperson.config.ShellConfig;
import com.badperson.interfaces.IModelWriter;
import com.badperson.vo.ExcelShellParseModel;

public class GaoyeWriter implements IModelWriter{
	
	public static boolean hasInit = false;
	
	private static final Logger logger = LoggerFactory.getLogger(GaoyeWriter.class);

	private final String fileName;

	public GaoyeWriter() {
		super();
		this.fileName = ShellConfig.OTHER_OUTPUT + ShellConfig.GAOYE_NEED_FILE;
		init();
	}

	@Override
	public void write(ExcelShellParseModel excelShellParseModel) throws Exception {
		Writer fileWriter = null;
		try {
			fileWriter = new OutputStreamWriter(new FileOutputStream(fileName, true),
					Charset.forName(Config.ENCODING_UTF));
			String str = getContent(excelShellParseModel);
			if (str != null && str.length() > 0) {
				fileWriter.write(str);
			}
		} finally {
			fileWriter.close();
		}
	}

	private String getContent(ExcelShellParseModel excelShellParseModel) {
		StringBuilder sb = new StringBuilder();
		if (excelShellParseModel.getGaoye().equals("1")) {
			Pattern compile = Pattern.compile("不良人安卓[混专]服WEB-(\\d{3})\\-?(\\d{3})?服");
			Matcher matcher = compile.matcher(excelShellParseModel.getDescription());
			if (matcher.find()) {
				int hefuServerId = Integer.parseInt(matcher.group(1));
				int trueServerId = hefuServerId;
				if (matcher.group(2) != null) {
					trueServerId = Integer.parseInt(matcher.group(2));
				}
				int sourcePort = Integer.parseInt(excelShellParseModel.getSourcePort());

				int baseServerId = Integer.parseInt(excelShellParseModel.getBaseServerId());
				hefuServerId += baseServerId;
				trueServerId += baseServerId;
				for (int serverId = hefuServerId; serverId <= trueServerId; serverId++) {
					sb.append(String.format("portandServerId.put(\"%d\", \"%d-%d\");\n", serverId, sourcePort,
							hefuServerId));
				}
			}
		}
		return sb.toString();
	}

	private void init() {
		if(!hasInit){
			File file = new File(fileName);
			if (file.exists()) {
				file.delete();
			}
			try {
				file.createNewFile();
				hasInit = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
