package com.hl.officetools.task.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.hl.officetools.task.TaskManager;

public class TaskManagerImpl implements TaskManager {

	private final List<RunnableTask> tasks = new ArrayList<RunnableTask>();

	private boolean withExecutor = false;

	@Override
	public void execute() {
		before();
		
		if (withExecutor) {
			
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
	
	public void withExecutor() {
		withExecutor = true;
	}

	// -----------------------------------------------------------------------

	private void before() {
		// override when needed
	}

	private void after() {
		withExecutor = false;
	}
}
