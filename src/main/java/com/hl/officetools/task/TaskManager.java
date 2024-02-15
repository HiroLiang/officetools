package com.hl.officetools.task;

public interface TaskManager {

	void add(RunnableTask task);
	void execute();
	void clean();
}
