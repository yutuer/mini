package com.badperson.eventDispatch.eventObject;

public class StaticEndEvent extends GeneralEvent {

	private static final long serialVersionUID = 1L;

	public StaticEndEvent() {
		this("StaticEndEventObject");
	}

	private StaticEndEvent(String source) {
		super(source);
	}
}
