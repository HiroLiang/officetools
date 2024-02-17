package com.hl.officetools.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.hl.officetools.task.excel.ExcelFillTableTask;
import com.hl.officetools.task.impl.RunnableTask;
import com.hl.officetools.task.impl.TaskManagerImpl;

public class TestTaskManagerImpl {

	private RunnableTask task = new RunnableTask();
	private RunnableTask task2 = new ExcelFillTableTask();
	
	@Test
	public void testExecute() {
		TaskManager tasks = new TaskManagerImpl();
		tasks.add(task);
		tasks.add(task2);
		tasks.add(task);
		
		assertDoesNotThrow(() -> tasks.withExecutor().execute());
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
		tasks.clean();
		
		assertEquals(tasks.length(), 0);
	}
}
