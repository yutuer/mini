package com.badperson.eventDispatch.eventObject;

public class StaticEndEventObject extends GeneralEventObject<String> {

	public StaticEndEventObject() {
		this("StaticEndEventObject");
	}

	private StaticEndEventObject(String source) {
		super(source);
	}
}
