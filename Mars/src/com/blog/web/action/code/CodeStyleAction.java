package com.blog.web.action.code;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.bean.QueryResult;
import com.blog.bean.code.CodeStyle;
import com.blog.dto.code.CodeStyleDTO;
import com.blog.service.code.CodeStyleService;
import com.blog.util.Utils;
import com.blog.web.action.BaseAction;

/**
 * 
 * @author neofans
 *
 */
@Controller("/codestyle/codestyle")
@Scope("prototype")
public class CodeStyleAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;
	@Resource
	private CodeStyleService codeStyleService;
	private CodeStyleDTO codeStyleDTO = new CodeStyleDTO();

	public String list() {
		return "list";
	}

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
			codeStyleList.append("<button type=\"button\" class=\"btn btn-link\" onclick=\"queryByTypeId(1,"+codeStyle.getId()+")\">" + codeStyle.getName() + "</button>");
		}
		return Utils.printInfo(codeStyleList.toString());
	}

	public CodeStyleDTO getCodeStyleDTO() {
		return codeStyleDTO;
	}

	public void setCodeStyleDTO(CodeStyleDTO codeStyleDTO) {
		this.codeStyleDTO = codeStyleDTO;
	}

}
