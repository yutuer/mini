package com.badperson.eventDispatch.eventObject;

public class StaticBeginEventObject extends GeneralEventObject<String>{

	public StaticBeginEventObject(){
		this("StaticBeginEventObject");
	}
	
	private StaticBeginEventObject(String source) {
		super(source);
	}

}
