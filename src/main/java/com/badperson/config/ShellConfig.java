package com.badperson.config;

public class ShellConfig {
	
	public static final String OUT_DIR = "shell/";
	public static final  String OUT_FILE_SUFFIX = "_xshell.fwr";

	public static final  String SplitChar = "_";
	public static final  String FirstLine = "[CONNECTION:SSH]\r\n";
	public static final  String FwdPrefix = "FwdReq_";
	public static final  String FwdReqCount = "FwdReqCount";
	public static final  String[] FwdFileds = 
		{ "_Incoming=", "_Description=", "_Source=", "_Port=", "_LocalOnly=", "_Host=", "_HostPort=" };
}
