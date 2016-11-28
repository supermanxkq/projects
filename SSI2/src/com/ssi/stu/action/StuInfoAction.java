package com.ssi.stu.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.ssi.stu.bo.StuInfo;
import com.ssi.stu.bo.StudentDetail;
import com.ssi.stu.logic.StuInfoService;
import com.ssi.stu.util.ExcelUtil;
import com.sun.org.apache.xml.internal.utils.SuballocatedByteVector;

public class StuInfoAction extends BasePageAction {
	private static final long serialVersionUID = 1L;
	private StuInfoService service;
	private StuInfo stuInfo;
	private StudentDetail studentDetail;
	private List<StuInfo> stuList;
	private File file;
	private String fileFileName;

	private static List<StuInfo> stuInfos = new ArrayList<StuInfo>();

	static {
		stuInfos.add(new StuInfo(null, "xukaiqiang", 22, "男", "994028591@qq.com", "1234123423", "北京",
				new StudentDetail(1, "")));
		stuInfos.add(new StuInfo(null, "xukaiqiang", 22, "男", "994028591@qq.com", "1234123423", "北京",
				new StudentDetail(1, "")));
		stuInfos.add(new StuInfo(null, "xukaiqiang", 22, "男", "994028591@qq.com", "1234123423", "北京",
				new StudentDetail(1, "")));
		stuInfos.add(new StuInfo(null, "xukaiqiang", 22, "男", "994028591@qq.com", "1234123423", "北京",
				new StudentDetail(1, "")));
		stuInfos.add(new StuInfo(null, "xukaiqiang", 22, "男", "994028591@qq.com", "1234123423", "北京",
				new StudentDetail(1, "")));
		stuInfos.add(new StuInfo(null, "xukaiqiang", 22, "男", "994028591@qq.com", "1234123423", "北京",
				new StudentDetail(1, "")));
	}

	public String save() {
		stuInfo.setSex("男");
		stuInfo.setAddress("北京市朝阳区");
		stuInfo.setAge(25);
		stuInfo.setEmail("994028591@qq.com");
		stuInfo.setMobile("1231312312");
		service.save(stuInfo);
		return "success";
	}

	public String addUI() {
		return "success";
	}

	public String queryAll() {
		stuList = service.queryAll(stuInfo);
		return "success";
	}

	public String deleteStuInfoById() {
		service.deleteStuInfoById(stuInfo.getStuId());
		return "success";
	}

	public String updateStuInfoById() {
		service.updateStuInfoById(stuInfo);
		return "success";
	}

	public String editUI() {
		stuInfo = service.findStuInfoById(stuInfo.getStuId());
		return "success";
	}

	public void addSave() {
		PrintWriter out = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			out = response.getWriter();
			stuInfo.setSex("男");
			stuInfo.setAddress("北京市朝阳区");
			stuInfo.setAge(25);
			stuInfo.setEmail("994028591@qq.com");
			stuInfo.setMobile("1231312312");
			service.save(stuInfo);
			stuList = service.queryAll(stuInfo);
			out.print(JSONArray.fromObject(stuList));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * @MethodName:queryStuNamesAsAjax
	 * @Description:通过Ajax获取所有学生的名称
	 * @author:半仙儿
	 * @return String
	 * @date:2015-8-24下午04:33:17
	 */
	public void queryStuNamesAsAjax() {
		// 获取所有的学生的信息
		stuList = service.queryAll(stuInfo);
		HttpServletResponse response = ServletActionContext.getResponse();
		// Ajax传入前台处理页面显示乱码问题
		response.setContentType("application/xml; charset=utf-8");// charset=gb2312
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xml = new StringBuffer("<?xml version='1.0' encoding='utf-8'?><data>");
		// 跌打所有的学生的姓名，然后添加到xml文件中
		for (int i = 0; i < stuList.size(); i++) {
			// 将学生的编号放入到xml节点中
			xml.append("<stuId>");
			xml.append(stuList.get(i).getStuId());
			xml.append("</stuId>");
			// 将学生的姓名放入到xml节点中
			xml.append("<stuName>");
			xml.append(stuList.get(i).getStuName());
			xml.append("</stuName>");
		}
		xml.append("</data>");
		response.setContentType("text/xml");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(xml.toString());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String importAll() {
		FileInputStream is = null;
		Workbook workbook = null;
		try {
			is = new FileInputStream(file.getAbsolutePath());
			if (fileFileName.endsWith("xls")) {
				workbook = new HSSFWorkbook(is);
			} else if (fileFileName.endsWith("xlsx")) {
				workbook = new XSSFWorkbook(is);
			}
			for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
				Sheet sheet = workbook.getSheetAt(numSheet);
				if (sheet.getSheetName().equals("Sheet2")) {
					break;
				}
				System.out.println("=======================" + sheet.getSheetName() + "=========================");
				int firstRowIndex = sheet.getFirstRowNum();
				int lastRowIndex = sheet.getLastRowNum();
				int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells();
				System.out.println("当前文件有" + lastRowIndex + "行");
				System.out.println("当前文件有" + coloumNum + "列");
				// 读取数据行
				for (int rowIndex = 0; rowIndex <= lastRowIndex; rowIndex++) {
					Row currentRow = sheet.getRow(rowIndex);// 当前行
					// int firstColumnIndex = currentRow.getFirstCellNum(); //
					// 首列
					// int lastColumnIndex = currentRow.getLastCellNum();// 最后一列
					// for (int columnIndex = firstColumnIndex; columnIndex <=
					// lastColumnIndex; columnIndex++) {
					// Cell currentCell = currentRow.getCell(columnIndex);//
					// 当前单元格
					// String currentCellValue =
					// ExcelUtil.getCellValue(currentCell, true);// 当前单元格的值
					// System.out.print(currentCellValue + "\t");
					// }
					String name = ExcelUtil.getCellValue(currentRow, 0);
					String sex = ExcelUtil.getCellValue(currentRow, 1);
					Integer age = Integer.parseInt(ExcelUtil.getCellValue(currentRow, 2));
					String email = ExcelUtil.getCellValue(currentRow, 3);
					String mobile = ExcelUtil.getCellValue(currentRow, 4);
					Integer stuDetailId = Integer.parseInt(ExcelUtil.getCellValue(currentRow, 5));
					String address = ExcelUtil.getCellValue(currentRow, 6);
					studentDetail = new StudentDetail();
					studentDetail.setStudentDetailId(stuDetailId);
					stuInfo = new StuInfo();
					stuInfo.setStuName(name);
					stuInfo.setAddress(address);
					stuInfo.setAge(age);
					stuInfo.setEmail(email);
					stuInfo.setMobile(mobile);
					stuInfo.setSex(sex);
					stuInfo.setStudentDetail(studentDetail);
					service.save(stuInfo);
				}
				System.out.println("======================================================");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	/**
	 * 
	 * 批量进行添加
	 * @return
	 */
	public String batchAdd() {
		List<Map> mapList = new ArrayList<Map>();
		for (int i = 0; i < stuInfos.size(); i++) {
			Map sub = new HashMap();
			sub.put("stuId", stuInfos.get(i).getStuId());
			sub.put("stuName", stuInfos.get(i).getStuName());
			sub.put("age", stuInfos.get(i).getAge());
			sub.put("sex", stuInfos.get(i).getSex());
			sub.put("email", stuInfos.get(i).getEmail());
			sub.put("mobile", stuInfos.get(i).getMobile());
			sub.put("address", stuInfos.get(i).getAddress());
			sub.put("studentDetail", stuInfos.get(i).getStudentDetail().getStudentDetailId());
			mapList.add(sub);
		}
		service.batchAdd(mapList);
		return "success";
	}

	// 使用Map进行SQl_MAP中的数据传输
	public String insertStuInfoByMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("isHave", "0");
		service.insertStuInfoByMap(map);
		return "success";
	}

	public StuInfoService getService() {
		return service;
	}

	public void setService(StuInfoService service) {
		this.service = service;
	}

	public StuInfo getStuInfo() {
		return stuInfo;
	}

	public void setStuInfo(StuInfo stuInfo) {
		this.stuInfo = stuInfo;
	}

	public List<StuInfo> getStuList() {
		return stuList;
	}

	public void setStuList(List<StuInfo> stuList) {
		this.stuList = stuList;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public StudentDetail getStudentDetail() {
		return studentDetail;
	}

	public void setStudentDetail(StudentDetail studentDetail) {
		this.studentDetail = studentDetail;
	}

}
