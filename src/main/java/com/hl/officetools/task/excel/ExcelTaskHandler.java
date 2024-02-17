package com.hl.officetools.task.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTaskHandler {
	
	private final XSSFWorkbook workbook;
	
	private File file;
	
	private String outputPath = "/";
	
	public ExcelTaskHandler(String templatePath, String outputPath) throws IOException {
		super();
		FileInputStream fis = new FileInputStream(templatePath);
		this.workbook = new XSSFWorkbook(fis);
		this.outputPath = outputPath;
	}
	
}
