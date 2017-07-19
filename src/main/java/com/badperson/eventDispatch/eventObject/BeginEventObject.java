package com.badperson.eventDispatch.eventObject;

public class BeginEventObject<T> extends GeneralEventObject<T> {

	private static final long serialVersionUID = 1L;

	public BeginEventObject(T source) {
		super(source);
	}
	
}
