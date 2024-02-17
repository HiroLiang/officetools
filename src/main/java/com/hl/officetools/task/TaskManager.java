package com.hl.officetools.task;

import com.hl.officetools.task.impl.RunnableTask;

public interface TaskManager {

	void add(RunnableTask task);
	
	void execute();
	
	void clean();
	
	int length();

	TaskManager withExecutor();
	
}
