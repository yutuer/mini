package com.badperson.eventDispatch.eventObject;

import java.util.EventObject;

public class GeneralEventObject<T> extends EventObject {

	private static final long serialVersionUID = 1L;

	public GeneralEventObject(T source) {
		super(source);
	}

	@Override
	public T getSource() {
		return (T) super.getSource();
	}

}
