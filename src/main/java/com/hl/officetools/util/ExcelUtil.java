package com.hl.officetools.util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelUtil {
	
	public static XSSFRow addRow(XSSFSheet sheet, int start) {
		XSSFRow row = sheet.getRow(start);
		short cellNum = row.getLastCellNum();
		sheet.shiftRows(start, sheet.getLastRowNum(), 1);
		XSSFRow newRow = sheet.getRow(start) == null ? sheet.createRow(start) : sheet.getRow(start);
		for (int i = 0; i < cellNum; i++) {
			XSSFCell cell = row.getCell(i);
			XSSFCell newCell = newRow.getCell(i) == null ? newRow.createCell(i) : newRow.getCell(i);
			newCell.setCellStyle(cell.getCellStyle());
		}
		return newRow;
	}

}
