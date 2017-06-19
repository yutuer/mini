package com.badperson.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.module.other.GaoyeModule;

@Component
public class OtherContainer {
	
	@Autowired
	private GaoyeModule gaoyeModule;
	
	public void write() throws Exception {
	}
	
}
