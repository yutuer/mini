package com.badperson.moduleWrite.container;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.badperson.moduleWrite.interfaces.IAction;
import com.badperson.moduleWrite.interfaces.IHead;
import com.badperson.moduleWrite.interfaces.ITail;
import com.badperson.moduleWrite.interfaces.IToolWrite;
import com.google.common.collect.Lists;

public abstract class AbstractToolWrite implements IToolWrite, InitializingBean {

	protected List<IHead> heads = Lists.newArrayList();
	protected List<IAction> actions = Lists.newArrayList();
	protected List<ITail> tails = Lists.newArrayList();

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
