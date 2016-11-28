package com.paySystem.ic.utils;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import com.paySystem.ic.web.dto.system.UserSession;

public class ExportUtil {
	private static final Log logger = LogFactory.getLog(ExportUtil.class);
	
	/**
	 * 创建EXCEL表格
	 * @param headers
	 * @param datas
	 * @param title
	 * @return
	 */
	public String createXls( List<String> headers, List<List<String>> datas, String title,HttpServletResponse response ){
		try {
			HSSFWorkbook work = new HSSFWorkbook();
			HSSFCellStyle titleStyle = this.getTitleStyle(work); //创建标题样式	
			HSSFCellStyle headerStyle = this.getHeaderStyle(work); //创建表头样式
			HSSFCellStyle contentStyle = this.getContentStyle(work); //创建表内容样式
			HSSFCellStyle tailStyle = this.getTailStyle(work); //创建落款样式
			HSSFSheet sheet = work.createSheet();
	        sheet.setDefaultColumnWidth( (short)18 );
			work.setSheetName(0, title, HSSFWorkbook.ENCODING_UTF_16);
			sheet.addMergedRegion( new Region(0, (short) 0, 0, (short)(headers.size()-1)) );// 合并第一行
			sheet.createFreezePane(0,2);//冻结窗口
			
			//设置标题
			setCell( sheet, titleStyle, 0, 0, title );
			for (int i = 0;i<headers.size();i++) {
				setCell( sheet, headerStyle, 1, i, headers.get(i) );
			}
			
			//设置内容
			if ( datas != null && datas.size()>0) {
				for (int i = 0;i<datas.size();i++) {
					for (int j = 0;j<datas.get(i).size();j++) {
						setCell( sheet, contentStyle, i+2,j, datas.get(i).get(j) );
					}
				}
			}else {
				sheet.addMergedRegion( new Region(2, (short) 0, 2, (short)(headers.size()-1)) );// 合并第一行
				setCell( sheet, contentStyle, 2, 0, "无数据" );
			}
			UserSession us = Utils.getUserSession();
	    	setCell( sheet, tailStyle, (datas.size()+3), 0, "制表人：");
			setCell( sheet, tailStyle, (datas.size()+3), 1, us.getRealName() );
			setCell( sheet, tailStyle, (datas.size()+3), 2, "制表日期：");
			setCell( sheet, tailStyle, (datas.size()+3), 3, DateTimeTool.getDateByToday() );
			work.write( response.getOutputStream() );
			} catch (Exception e) {
			    logger.info("用户管理---创建EXCEL表格--错误"+e);
		}
		return null;
	}
	
	
	// excel设置单元格的值，不带样式
	public void setCell(HSSFSheet sheet, int rowCount, int cellCount, Object va) {
		HSSFRow row = sheet.createRow(rowCount);
		HSSFCell cell = row.createCell((short) cellCount);
		cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
		if ((va instanceof Long) || (va instanceof Integer)) {
			cell.setCellValue(Long.valueOf(va + ""));
		} else if (va instanceof Double) {
			cell.setCellValue(Double.valueOf(va + ""));
		} else if (va instanceof BigDecimal) {
			cell.setCellValue(new BigDecimal(va + "").doubleValue());
		} else {
			cell.setCellValue(va + "");
		}
	}

	// 带样式的单元格
	public void setCell(HSSFSheet sheet, HSSFCellStyle style, int rowCount,
			int cellCount, Object va) {
		HSSFRow row = sheet.createRow(rowCount);
		HSSFCell cell = row.createCell((short) cellCount);
		cell.setCellStyle(style);
		
		cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
		if ((va instanceof Long) || (va instanceof Integer)) {
			cell.setCellValue(Long.valueOf(va + ""));
		} else if (va instanceof Double) {
			cell.setCellValue(Double.valueOf(va + ""));
		} else if (va instanceof BigDecimal) {
			cell.setCellValue(new BigDecimal(va + "").doubleValue());
		} else {
			cell.setCellValue(va + "");
		}
	}

	// 创建标题样式
	public HSSFCellStyle getTitleStyle(HSSFWorkbook workbook) {   
        //设置字体;   
        HSSFFont font = workbook.createFont();   
        //设置字体大小;   
        font.setFontHeightInPoints((short) 20);  
        //设置字体名字;   
        font.setFontName("黑体");  
        //粗体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置样式;   
        HSSFCellStyle style = workbook.createCellStyle(); 
        style.setFont(font);  
        
        //设置自动换行;   
       // style.setWrapText(false);   
        //设置水平对齐的样式为居中对齐;   
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        //设置垂直对齐的样式为居中对齐;   
        style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);  

        //单元格的边框设置 
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); 
        style.setBottomBorderColor(HSSFColor.BLACK.index); 
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN); 
        style.setLeftBorderColor(HSSFColor.BLACK.index); 
        style.setBorderRight(HSSFCellStyle.BORDER_THIN); 
        style.setRightBorderColor(HSSFColor.BLACK.index); 
        style.setBorderTop(HSSFCellStyle.BORDER_THIN); 
        style.setTopBorderColor(HSSFColor.BLACK.index); 
        
      /*  //填充和颜色设置
        style.setFillForegroundColor(HSSFColor.TAN.index); 
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);*/

        return style;   
    }  
	
	// 创建落款样式
	public HSSFCellStyle getTailStyle(HSSFWorkbook workbook) {   
        //设置字体;   
        HSSFFont font = workbook.createFont(); 
        //粗体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置样式;   
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);   
        //style.setWrapText(false);   
        //设置水平对齐的样式为居中对齐;   
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);   
        //设置垂直对齐的样式为居中对齐;   
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);       
        return style;   
    }  
		
	// 创建表头样式
	public HSSFCellStyle getHeaderStyle(HSSFWorkbook workbook) {   
        //设置字体;   
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);  
        //粗体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置样式;   
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);   
        //style.setWrapText(false);   
        //设置水平对齐的样式为居中对齐;   
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);   
        //设置垂直对齐的样式为居中对齐;   
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
        
        //单元格的边框设置 
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); 
        style.setBottomBorderColor(HSSFColor.BLACK.index); 
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN); 
        style.setLeftBorderColor(HSSFColor.BLACK.index); 
        style.setBorderRight(HSSFCellStyle.BORDER_THIN); 
        style.setRightBorderColor(HSSFColor.BLACK.index); 
        style.setBorderTop(HSSFCellStyle.BORDER_THIN); 
        style.setTopBorderColor(HSSFColor.BLACK.index); 
        //填充和颜色设置
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index); 
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 

        return style;   
    }  
	// 创建表头局中
	public HSSFCellStyle getHeaderCenterStyle(HSSFWorkbook workbook) {   
        //设置字体;   
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);  
        //粗体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置样式;   
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);   
        //style.setWrapText(false);   
        //设置水平对齐的样式为居中对齐;   
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        //设置垂直对齐的样式为居中对齐;   
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
        
        //单元格的边框设置 
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); 
        style.setBottomBorderColor(HSSFColor.BLACK.index); 
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN); 
        style.setLeftBorderColor(HSSFColor.BLACK.index); 
        style.setBorderRight(HSSFCellStyle.BORDER_THIN); 
        style.setRightBorderColor(HSSFColor.BLACK.index); 
        style.setBorderTop(HSSFCellStyle.BORDER_THIN); 
        style.setTopBorderColor(HSSFColor.BLACK.index); 
        //填充和颜色设置
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index); 
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 

        return style;   
    }  
	
	// 创建表内容样式
	public HSSFCellStyle getContentStyle(HSSFWorkbook work) {
		HSSFCellStyle style = work.createCellStyle();
		style.setWrapText(false);
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT); 
		//设置自动换行;   
       /* style.setWrapText(false);  				 
        //设置水平对齐的样式为居中对齐;   
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        //设置垂直对齐的样式为居中对齐;   
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
        
        //单元格的边框设置 
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); 
        style.setBottomBorderColor(HSSFColor.BLACK.index); 
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN); 
        style.setLeftBorderColor(HSSFColor.BLACK.index); 
        style.setBorderRight(HSSFCellStyle.BORDER_THIN); 
        style.setRightBorderColor(HSSFColor.BLACK.index); 
        style.setBorderTop(HSSFCellStyle.BORDER_THIN); 
        style.setTopBorderColor(HSSFColor.BLACK.index); */
       /* 
        //填充和颜色设置
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);*/
		style.setWrapText(false);
		return style;
	}
	
}
