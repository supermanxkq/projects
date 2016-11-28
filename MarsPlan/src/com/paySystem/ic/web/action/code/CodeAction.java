package com.paySystem.ic.web.action.code;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.code.Code;
import com.paySystem.ic.service.code.CodeService;
import com.paySystem.ic.service.code.CodeStyleService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ListInfoDTOF;
import com.paySystem.ic.web.dto.article.ArticleDTO;
import com.paySystem.ic.web.dto.code.CodeDTO;

/**
 * @ProjectName:MarsPlan
 * @ClassName:CodeAction
 * @Description:代码管理控制类
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:Mar 6, 20165:13:32 AM
 */
@Controller("/code/code")
@Scope("prototype")
public class CodeAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;
	@Resource
	private CodeService codeService;
	@Resource
	private CodeStyleService codeStyleService;
	private CodeDTO codeDTO = new CodeDTO();
	private Code code;

	/**
	 * @MethodName:list
	 * @Description:前台跳转到代码库页面
	 * @author:徐凯强
	 * @return String
	 * @date:Mar 6, 20165:14:05 AM
	 */
	public String list() {
		return "list";
	}

	/**
	 * @MethodName:listB
	 * @Description:后台跳转到代码库集合页面
	 * @author:徐凯强
	 * @return String
	 * @date:Mar 6, 20165:17:30 AM
	 */
	public String listB() {
		return "listB";
	}

	/**
	 * @MethodName:addUI
	 * @Description:跳转到后台添加代码页面
	 * @author:徐凯强
	 * @return String
	 * @date:Mar 6, 20165:29:50 AM
	 */
	public String addUI() {
		this.getRequest().setAttribute("codeStyles", codeStyleService.queryStyles());
		this.setMethod("addSave");
		return "input";
	}

	/**
	 * @MethodName:findCodeById
	 * @Description:前台根据代码编号获取代码信息
	 * @author:徐凯强
	 * @return String
	 * @date:Mar 6, 20165:14:48 AM
	 */
	public String findCodeById() {
		code = codeService.find(codeDTO.getId());
		return "find";
	}

	/**
	 * @MethodName:addSave
	 * @Description:后台添加code对象
	 * @author:徐凯强
	 * @return String
	 * @date:Mar 6, 20166:05:32 AM
	 */
	public String addSave() {
		codeDTO.setAuthor("徐半仙儿");
		codeDTO.setCreateDate(new Date());
		codeService.addSave(codeDTO);
		return "listB";
	}

	/**
	 * @MethodName:updateData
	 * @Description:后台修改数据
	 * @author:徐凯强
	 * @return String
	 * @date:Mar 7, 20165:25:02 PM
	 */
	public String updateData() {
		codeService.updateData(codeDTO);
		return "listB";
	}

	/**
	 * @MethodName:jsonPageList
	 * @Description:前台Ajax获取代码集合信息
	 * @throws Exception
	 * @author:徐凯强
	 * @return String
	 * @date:Mar 6, 20165:15:38 AM
	 */
	public String jsonPageList() throws Exception {
		/**
		 * 查询结果排序参数设定
		 */
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		/** 判断排序参数是否有值 */
		if (StringUtils.isNotBlank(this.getOrderProperty()) && StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderBy.put("id", "desc");
		}
		/** 获取queryResult中的值 */
		QueryResult<Code> result = codeService.queryAll((codeDTO.getPage() - 1) * pageNum, pageNum, codeDTO, orderBy);
		/** 获取queryResult中的集合 */
		List<Code> codes = result.getResultlist();
		StringBuffer codeList = new StringBuffer();
		for (int i = 0; i < codes.size(); i++) {
			Code code = codes.get(i);
			codeList.append("<div class=\"media\">\n");
			codeList.append("<div class=\"media-body\">");
			codeList.append("<h4 class=\"media-heading\"><a href=\"code/code!findCodeById?codeDTO.id="
					+ code.getId() + "\">" + code.getName() + "</a></h4>\n");
			codeList.append(code.getSummary());
			codeList.append("</div>\n");
			codeList.append("<p class=\"text-right text-danger\">"
					+ DateTimeTool.dateFormat("", code.getCreateDate()) + " </p></div><hr/>");
		}
		// /** 实例化PageView对象,获取分页的参数、总页数、总记录数 */
		PageView pageView = new PageView(codeDTO.getPage(), result.getTotalrecord());
		/** 实例化ListInfoDTO */
		ListInfoDTOF codeListInfoDTO = new ListInfoDTOF(codeList.toString(), getPageHTMLF(pageView));
		return Utils.printInfo(codeListInfoDTO);
	}

	/**
	 * @MethodName:jsonPageListB
	 * @Description:后台查询所有的代码信息
	 * @throws Exception
	 * @author:徐凯强
	 * @return String
	 * @date:Mar 6, 20165:24:32 AM
	 */
	public String jsonPageListB() throws Exception {
		/**
		 * 查询结果排序参数设定
		 */
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		/** 判断排序参数是否有值 */
		if (StringUtils.isNotBlank(this.getOrderProperty()) && StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderBy.put("id", "desc");
		}
		/** 获取queryResult中的值 */
		QueryResult<Code> result = codeService.queryAll((codeDTO.getPage() - 1) * pageNum, pageNum, codeDTO, orderBy);
		List<List<String>> lists = new ArrayList<List<String>>();
		/** 获取queryResult中的集合 */
		List<Code> codes = result.getResultlist();
		for (int i = 0; i < codes.size(); i++) {
			Code code = codes.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(code.getId().toString());
			strings.add("<img src=" + code.getThumbnailPath() + " width='30px' height='30px'/>");
			strings.add(code.getName());
			strings.add(code.getAuthor());
			strings.add(code.getTypeId().toString());
			strings.add(code.getPanHref());
			strings.add(code.getSummary());
			strings.add(DateTimeTool.dateFormat("", code.getCreateDate()));
			String operation = "";
			if (Utils.checkPermission("sy-1101-04")) {
				operation += "<a href='base/goods!checkUI?goodsDTO.goodsId=" + code.getId() + "' title='查看'>"
						+ Globals.IMG_VIEW + "</a>&nbsp;";
			}
			if (Utils.checkPermission("sy-1101-03")) {
				operation += "<a href='code/code!editUI?codeDTO.id=" + code.getId() + "' title='修改'>" + Globals.IMG_EDIT
						+ "</a>&nbsp;";
			}
			if (Utils.checkPermission("sy-1101-02")) {
				operation += "<a href='code/code!checkUI?goodsDTO.goodsId=" + code.getId() + "' title='删除'>"
						+ Globals.IMG_DELETE + "</a>&nbsp;";
			}
			strings.add(operation);
			lists.add(strings);
		}
		PageView pageView = new PageView(codeDTO.getPage(), result.getTotalrecord());
		/** 实例化ListInfoDTO */
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 * @MethodName:editUI
	 * @Description:后台跳转到修改界面
	 * @author:徐凯强
	 * @return String
	 * @date:Mar 7, 20165:04:34 PM
	 */
	public String editUI() {
		try {
			this.getRequest().setAttribute("codeStyles", codeStyleService.queryStyles());
			Code code = codeService.find(codeDTO.getId());
			codeDTO = (CodeDTO) EntityDtoConverter.bean2Dto(code, codeDTO);
			this.setMethod("updateData");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}

	public CodeDTO getCodeDTO() {
		return codeDTO;
	}

	public void setCodeDTO(CodeDTO codeDTO) {
		this.codeDTO = codeDTO;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

}
