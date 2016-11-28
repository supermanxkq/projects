package com.ssi.stu.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class CellStyle {
	/**
	 *@MethodName:createCellStyle
	 *@Description:设置Excel样式
	 *@param wb
	 *@author:半仙儿
	 *@return HSSFCellStyle
	 *@date:2015-10-29下午02:54:49
	 */
	public static HSSFCellStyle createCellStyle(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		return style;
	}

	/**
	 *@MethodName:createCellStyle1
	 *@Description:设置Excel样式1
	 *@param wb
	 *@author:半仙儿
	 *@return HSSFCellStyle
	 *@date:2015-10-29下午02:54:49
	 */
	public static HSSFCellStyle createCellStyle1(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return style;
	}

	/**
	 *@MethodName:createCell
	 *@Description:创建单元格
	 *@param style
	 *@param row
	 *@param cellCount
	 *@author:半仙儿
	 *@return HSSFCell
	 *@date:2015-10-29下午04:40:21
	 */
	public static HSSFCell createCell(HSSFCellStyle style, HSSFRow row,
			int cellCount) {
		HSSFCell cell = row.createCell(cellCount);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellStyle(style);
		return cell;
	}
}
