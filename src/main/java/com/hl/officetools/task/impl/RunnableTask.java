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
		System.out.println(Thread.currentThread().getName() + " run do task");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
