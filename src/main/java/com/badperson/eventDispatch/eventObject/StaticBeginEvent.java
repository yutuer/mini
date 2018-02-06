package com.badperson.eventDispatch.eventObject;

public class StaticBeginEvent extends GeneralEvent{

	private static final long serialVersionUID = 1L;
	
	public StaticBeginEvent(){
		this("StaticBeginEventObject");
	}
	
	private StaticBeginEvent(String source) {
		super(source);
	}

}
