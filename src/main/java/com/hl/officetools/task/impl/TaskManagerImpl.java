package com.hl.officetools.task.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.hl.officetools.task.TaskManager;

public class TaskManagerImpl implements TaskManager {
	/*
	 * 
	 */

	private final List<RunnableTask> tasks = new ArrayList<RunnableTask>();

	private boolean withExecutor = false;

	@Override
	public void execute() {
		before();
		
		if (withExecutor) {
			for (RunnableTask task : tasks) {
				CompletableFuture.runAsync(() -> {
					task.run();
				});
			}
		} else {
			for (RunnableTask task : tasks) {
				task.doTask();
			}
		}
		
		after();
	}

	@Override
	public void add(RunnableTask task) {
		this.tasks.add(task);
	}

	@Override
	public void clean() {
		tasks.clear();
	}

	@Override
	public int length() {
		return tasks.size();
	}

	@Override
	public TaskManager withExecutor() {
		withExecutor = true;
		return this;
	}

	// -----------------------------------------------------------------------

	private void before() {
		// override when needed
	}

	private void after() {
		withExecutor = false;
	}
}
