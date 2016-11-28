package com.paySystem.ic.web.action.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.code.CodeStyle;
import com.paySystem.ic.service.code.CodeStyleService;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.code.CodeStyleDTO;

/**
 * @ProjectName:MarsPlan
 * @ClassName:CodeStyleAction
 * @Description:代码分类控制类
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:Mar 6, 20163:34:22 AM
 */
@Controller("/codestyle/codestyle")
@Scope("prototype")
public class CodeStyleAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;
	@Resource
	private CodeStyleService codeStyleService;
	private CodeStyleDTO codeStyleDTO = new CodeStyleDTO();
	private CodeStyle codeStyle = new CodeStyle();

	/**
	 * @MethodName:list
	 * @Description:后台跳转到查询所有代码分类的界面
	 * @author:徐凯强
	 * @return String
	 * @date:Mar 6, 20163:36:16 AM
	 */
	public String list() {
		return "list";
	}

	/**
	 * @MethodName:jsonPageList
	 * @Description:前台Ajax获取所有的代码分类到codelist.jsp页面
	 * @throws Exception
	 * @author:徐凯强
	 * @return String
	 * @date:Mar 6, 20163:31:05 AM
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
		QueryResult<CodeStyle> result = codeStyleService.queryAll();
		/** 获取queryResult中的集合 */
		List<CodeStyle> codeStyles = result.getResultlist();
		StringBuffer codeStyleList = new StringBuffer();
		codeStyleList.append("<h4>代码分类</h4>");
		for (int i = 0; i < codeStyles.size(); i++) {
			CodeStyle codeStyle = codeStyles.get(i);
			codeStyleList.append("<button type=\"button\" class=\"btn btn-link\" onclick=\"queryByTypeId(1,"
					+ codeStyle.getId() + ")\">" + codeStyle.getName() + "</button>");
		}
		return Utils.printInfo(codeStyleList.toString());
	}

	/**
	 * @MethodName:jsonPageListB
	 * @Description:后台获取所有代码分类
	 * @throws Exception
	 * @author:徐凯强
	 * @return String
	 * @date:Mar 6, 20163:43:10 AM
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
		QueryResult<CodeStyle> result = codeStyleService.queryAll((codeStyleDTO.getPage() - 1) * pageNum, pageNum,
				codeStyleDTO, orderBy);
		List<List<String>> lists = new ArrayList<List<String>>();
		/** 获取queryResult中的集合 */
		List<CodeStyle> codeStyles = result.getResultlist();
		StringBuffer codeStyleList = new StringBuffer();
		codeStyleList.append("<h4>代码分类</h4>");
		for (int i = 0; i < codeStyles.size(); i++) {
			CodeStyle codeStyle = codeStyles.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(codeStyle.getId().toString());
			strings.add(codeStyle.getName());
			String operation = "";
			if (Utils.checkPermission("sy-1102-04")) {
				operation += "<a href='base/goods!checkUI?goodsDTO.goodsId=" + codeStyle.getId() + "' title='查看'>"
						+ Globals.IMG_VIEW + "</a>&nbsp;";
			}
			if (Utils.checkPermission("sy-1102-03")) {
				operation += "<a href='base/goods!checkUI?goodsDTO.goodsId=" + codeStyle.getId() + "' title='修改'>"
						+ Globals.IMG_EDIT + "</a>&nbsp;";
			}
			if (Utils.checkPermission("sy-1102-02")) {
				operation += "<a href='base/goods!checkUI?goodsDTO.goodsId=" + codeStyle.getId() + "' title='删除'>"
						+ Globals.IMG_DELETE + "</a>&nbsp;";
			}
			strings.add(operation);
			lists.add(strings);
		}
		PageView pageView = new PageView(codeStyleDTO.getPage(), result.getTotalrecord());
		/** 实例化ListInfoDTO */
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 * @MethodName:addUI
	 * @Description:后台跳转到添加分类页面
	 * @author:徐凯强
	 * @return String
	 * @date:Mar 6, 20164:33:05 AM
	 */
	public String addUI() {
		this.setMethod("addSave");
		return "input";
	}

	/**
	 * @MethodName:addSave
	 * @Description:后台添加分类
	 * @author:徐凯强
	 * @return String
	 * @date:Mar 6, 20164:43:35 AM
	 */
	public String addSave() {
		codeStyleService.addSave(codeStyle);
		return "list";
	}

	public CodeStyle getCodeStyle() {
		return codeStyle;
	}

	public void setCodeStyle(CodeStyle codeStyle) {
		this.codeStyle = codeStyle;
	}

	public CodeStyleDTO getCodeStyleDTO() {
		return codeStyleDTO;
	}

	public void setCodeStyleDTO(CodeStyleDTO codeStyleDTO) {
		this.codeStyleDTO = codeStyleDTO;
	}

}
