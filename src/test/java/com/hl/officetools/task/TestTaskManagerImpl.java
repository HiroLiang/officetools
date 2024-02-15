package com.hl.officetools.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.hl.officetools.task.impl.RunnableTask;
import com.hl.officetools.task.impl.TaskManagerImpl;

public class TestTaskManagerImpl {

	private RunnableTask task = new RunnableTask();
	
	@Test
	public void testExecute() {
		TaskManager tasks = new TaskManagerImpl();
		tasks.add(task);
		
	}

	@Test
	public void testAdd() {
		TaskManager tasks = new TaskManagerImpl();

		int i = tasks.length();

		tasks.add(task);

		assertEquals(tasks.length(), i + 1);
	}

	@Test
	public void testClean() {
		TaskManager tasks = new TaskManagerImpl();
		tasks.add(task);
		
		
	}
}
