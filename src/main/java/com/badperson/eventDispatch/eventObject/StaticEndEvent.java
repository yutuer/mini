package com.badperson.eventDispatch.eventObject;

public class StaticEndEvent extends GeneralEvent<String> {

	public StaticEndEvent() {
		this("StaticEndEventObject");
	}

	private StaticEndEvent(String source) {
		super(source);
	}
}
