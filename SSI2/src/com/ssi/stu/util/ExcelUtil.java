package com.ssi.stu.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelUtil {


	/**
	 * @param row表示当前行的对象
	 * @param treatAsStr
	 * @return currentRowColumnNum表示当前行的第几列
	 */
	public static String getCellValue(Row row, int currentRowColumnNum) {
		Cell cell = row.getCell(currentRowColumnNum);
		if (cell == null) {
			return "";
		}
		cell.setCellType(Cell.CELL_TYPE_STRING);
		if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}

}