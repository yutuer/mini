package com.badperson.eventDispatch.eventObject;

public class DataEventObject<T> extends GeneralEventObject<T> {
	private static final long serialVersionUID = 1L;

	public DataEventObject(T source) {
		super(source);
	}

}
