package com.badperson.container;

import java.util.List;

import com.badperson.interfaces.IAction;
import com.badperson.interfaces.IHead;
import com.badperson.interfaces.ITail;
import com.badperson.interfaces.IToolWrite;
import com.google.common.collect.Lists;

public abstract class AbstractToolWrite implements IToolWrite{

	protected List<IHead> heads = Lists.newArrayList();
	protected List<IAction> actions = Lists.newArrayList();
	protected List<ITail> tails = Lists.newArrayList();
	
	protected abstract void init();
	
	public AbstractToolWrite() {
		super();
		init();
	}

	/**
	 * 包括 数据库隧道端口连接, 以及
	 * 
	 * @throws Exception
	 */
	@Override
	public void write() throws Exception {
		for (IHead iHead : heads) {
			iHead.head();
		}
		for (IAction iAction : actions) {
			iAction.action();
		}
		for (ITail iTail : tails) {
			iTail.tail();
		}
	}

}
