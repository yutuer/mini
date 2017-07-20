package com.badperson.eventDispatch.eventObject;

public class StaticBeginEvent extends GeneralEvent<String>{

	public StaticBeginEvent(){
		this("StaticBeginEventObject");
	}
	
	private StaticBeginEvent(String source) {
		super(source);
	}

}
