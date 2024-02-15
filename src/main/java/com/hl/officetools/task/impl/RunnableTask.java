package com.hl.officetools.task.impl;

import com.hl.officetools.task.officeTask;

public class RunnableTask implements officeTask, Runnable {

	@Override
	public void run() {
		doTask();
	}

	@Override
	public void initial() {
		
	}

	@Override
	public void doTask() {

	}

}
