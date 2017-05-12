package com.badperson.interfaces;


public interface IParseModel<V> {

	V getParseResult();
	
	void write(IModelWriter sw) throws Exception;

}
