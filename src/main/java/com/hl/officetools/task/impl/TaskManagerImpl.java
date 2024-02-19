package com.hl.officetools.task.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.hl.officetools.task.TaskManager;

public class TaskManagerImpl implements TaskManager {
	/*
	 * to follow sequence - don't use executor.
	 * executor will 
	 */

	private final List<RunnableTask> tasks = new ArrayList<RunnableTask>();

	private boolean withExecutor = false;

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		before();
		
		if (withExecutor) {
			CompletableFuture<Void>[] futures = new CompletableFuture[tasks.size()];
			for (int i = 0; i < tasks.size(); i++) {
				RunnableTask task = tasks.get(i);
				futures[i] =  CompletableFuture.runAsync(() -> {
					task.run();
				});
			}
			CompletableFuture.allOf(futures).join();
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
		// add process before task
	}

	private void after() {
		withExecutor = false;
	}
}
