package com.hl.officetools.task.excel;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hl.officetools.task.impl.RunnableTask;

public class ExcelFillTableTask extends RunnableTask {
	
	private XSSFWorkbook workbook;
	
	private String tagName;
	
	private Integer[] coordinate;
	
	private List<String[]> dataRows;

	@Override
	public void run() {
		doTask();
	}

	@Override
	public void initial() {
		// TODO Auto-generated method stub
		super.initial();
	}

	@Override
	public void doTask() {
		System.out.println(Thread.currentThread().getName() + " run ExcelFillTableTask");
	}
	
	// -----------------------------------------------------------------------
	
	
}
