package com.hl.officetools.task.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTaskHandler {
	
	private final XSSFWorkbook workbook;
	
	private File file;
	
	private String outputPath = "/" + UUID.randomUUID() + ".xlsx";
	
	private String tagName = "tag";
	
	public ExcelTaskHandler(String templatePath, String outputPath) throws IOException {
		super();
		FileInputStream fis = new FileInputStream(templatePath);
		this.workbook = new XSSFWorkbook(fis);
		this.outputPath = outputPath + UUID.randomUUID() + ".xlsx";
	}
	
	public ExcelTaskHandler(XSSFWorkbook workbook, String outputPath) {
		super();
		this.workbook = workbook;
		this.outputPath = outputPath + UUID.randomUUID() + ".xlsx";
	}

	public String getTagName() {
		return tagName;
	}

	public ExcelTaskHandler setTagName(String tagName) {
		this.tagName = tagName;
		return this;
	}
	
	// -----------------------------------------------------------------------
	
	public ExcelFillTableTask newExcelFillTableTask(List<String[]> dataRows) {
		return new ExcelFillTableTask(workbook, tagName, dataRows);
	}
	
	// write file
	public File write() throws IOException {
		FileOutputStream fos = new FileOutputStream(outputPath);
		this.workbook.write(fos);
		fos.close();
		this.file = new File(outputPath);
		return this.file;
	}
	
	public File write(String pathName) throws IOException {
		FileOutputStream fos = new FileOutputStream(pathName);
		this.workbook.write(fos);
		fos.close();
		this.file = new File(pathName);
		return this.file;
	}
	
	// -----------------------------------------------------------------------
}
