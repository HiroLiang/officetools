package com.hl.officetools.task.excel;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hl.officetools.task.impl.RunnableTask;

public class ExcelFillTableTask extends RunnableTask {
	
	private final XSSFWorkbook workbook;
	
	private final XSSFSheet sheet;
	
	private final String tagName;
	
	private final List<String[]> dataRows;
	
	private Integer[] coordinate;
	
	public ExcelFillTableTask(XSSFWorkbook workbook, String tagName, List<String[]> dataRows) {
		super();
		this.workbook = workbook;
		this.sheet = this.workbook.getSheetAt(0);
		this.tagName = tagName;
		this.dataRows = dataRows;
	}

	public ExcelFillTableTask(XSSFWorkbook workbook, int sheetIndex, String tagName, List<String[]> dataRows) {
		super();
		this.workbook = workbook;
		this.sheet = this.workbook.getSheetAt(sheetIndex);
		this.tagName = tagName;
		this.dataRows = dataRows;
	}
	
	public ExcelFillTableTask(XSSFWorkbook workbook, String sheetName, String tagName, List<String[]> dataRows) {
		super();
		this.workbook = workbook;
		this.sheet = this.workbook.getSheet(sheetName);
		this.tagName = tagName;
		this.dataRows = dataRows;
	}
	
	// -----------------------------------------------------------------------

	@Override
	public void run() {
		doTask();
	}

	@Override
	public void initial() {
		findTagCoordinate();
	}

	@Override
	public void doTask() {
		fillTable(this.dataRows, this.coordinate);
	}
	
	public ExcelFillTableTask render(Map<String, String> model) {
		Set<String> keys = model.keySet();
		for (Row row : sheet) {
			for (Cell cell : row) {
				replaceKey(model, keys, cell);
			}
		}
		return this;
	}
	
	// -----------------------------------------------------------------------
	
	private void replaceKey(Map<String, String> model, Set<String> keys, Cell cell) {
		if (cell.getCellType() == CellType.STRING) {
			for (String key : keys) {
				String replaced = cell.getStringCellValue().replace("{{" + key + "}}", model.get(key));
				cell.setCellValue(replaced);
			}
		}
	}
	
	private boolean hasTag(Cell cell) {
		if(cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals("${" + tagName + "}"))
			return true;
		return false;
	}
	
	private void addTag(Row row, Cell cell) {
		XSSFRow targetRow = sheet.getRow(row.getRowNum());
		XSSFCell targetCell = targetRow.getCell(cell.getColumnIndex());
		targetCell.setCellValue("");

		coordinate = new Integer[] { cell.getColumnIndex(), row.getRowNum() };
	}
	
	private void findTagCoordinate() {
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (hasTag(cell)) {
					addTag(row, cell);
					break;
				}
			}
		}
	}
	
	private void fillTable(List<String[]> table, Integer[] coordinate) {
		for (int i = 0; i < table.size(); i++) {
			String[] rowDatas = table.get(i);
			XSSFRow row = sheet.getRow(coordinate[1] + i);
			if (i > 0) {
				row = addRow(coordinate[1] + i);
			}

			for (int j = 0; j < rowDatas.length; j++) {
				XSSFCell cell = row.getCell(coordinate[0] + j);
				cell.setCellValue(rowDatas[j]);
			}
		}
	}
	
	private XSSFRow addRow(int start) {
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
