package com.hl.officetools.task.impl;

import com.hl.officetools.task.officeTask;

public class RunnableTask implements officeTask, Runnable {
	/*
	 * Parent class for all tasks
	 */

	@Override
	public void run() {
		doTask();
	}

	@Override
	public void initial() {
		System.out.println(Thread.currentThread().getName() + " initial has not been override yet.");
	}

	@Override
	public void doTask() {
		System.out.println(Thread.currentThread().getName() + " doTask has not been override yet.");
	}

}
