package com.hl.officetools.task;

import java.util.ArrayList;
import java.util.List;

public class TaskManagerImpl implements TaskManager {

	private final List<RunnableTask> tasks = new ArrayList<RunnableTask>();

	@Override
	public void execute() {
		for (RunnableTask task : tasks) {
			before();
			task.doTask();
			after();
		}
	}

	@Override
	public void add(RunnableTask task) {
		this.tasks.add(task);
	}

	@Override
	public void clean() {
		tasks.clear();
	}

	// -----------------------------------------------------------------------

	private void before() {
		// override when needed
	}

	private void after() {
		// override when needed
	}
}
