package com.badperson.interfaces;

import com.badperson.vo.ExcelShellParseModel;

public interface IModelWriter {

	public abstract void write(ExcelShellParseModel excelShellParseModel) throws Exception;

}
