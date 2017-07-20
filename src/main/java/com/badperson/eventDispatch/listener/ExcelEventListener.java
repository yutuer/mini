package com.badperson.eventDispatch.listener;

import java.util.EventListener;

import com.badperson.eventDispatch.eventObject.BeginEvent;
import com.badperson.eventDispatch.eventObject.EndEvent;
import com.badperson.eventDispatch.eventObject.RowDataEvent;
import com.badperson.eventDispatch.eventObject.StaticBeginEvent;
import com.badperson.eventDispatch.eventObject.StaticEndEvent;

public interface ExcelEventListener extends EventListener {

	public void onEvent(StaticBeginEvent eventObject) throws Exception;

	public void onEvent(BeginEvent eventObject) throws Exception;

	public void onEvent(EndEvent eventObject) throws Exception;

	public void onEvent(RowDataEvent eventObject) throws Exception;

	public void onEvent(StaticEndEvent eventObject) throws Exception;
}
