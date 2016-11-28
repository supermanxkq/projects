package com.blog.web.action.code;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.bean.PageView;
import com.blog.bean.QueryResult;
import com.blog.bean.code.Code;
import com.blog.dto.CodeListInfoDTO;
import com.blog.dto.code.CodeDTO;
import com.blog.service.code.CodeService;
import com.blog.util.Utils;
import com.blog.web.action.BaseAction;

/**
 * 
 * @author neofans
 *
 */
@Controller("/code/code")
@Scope("prototype")
public class CodeAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;
	@Resource
	private CodeService codeService;
	private CodeDTO codeDTO=new CodeDTO();
	private Code  code;
	/**
	 *@Title:list
	 *@Description:list方法
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-22下午03:02:16
	 */
	public String list() {
		return "list";
	}
	
	
	/**
	 * 查询
	 * @return
	 */
	public String findCodeById(){
		code=codeService.find(codeDTO.getId());
		return "find";
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
		QueryResult<Code> result = codeService.queryAll((codeDTO.getPage() - 1) * pageNum, pageNum, codeDTO,
				orderBy);
		/** 获取queryResult中的集合 */
		List<Code> codes = result.getResultlist();
		StringBuffer codeList=new StringBuffer();
		for (int i = 0; i < codes.size(); i++) {
			Code code = codes.get(i);
			if(i%3==0){
				codeList.append("<div class=\"row\">\n");
			}
			
			codeList.append("<div class=\"col-md-4\"><div class=\"thumbnail\">\n");
			codeList.append("<img alt=\"Bootstrap Thumbnail\" src=\""+code.getThumbnailPath()+"\" />\n");
			codeList.append("<div class=\"caption\">");
			codeList.append("<h3>"+code.getName()+"</h3>\n");
			codeList.append("<p>"+code.getSummary()+"</p>\n");
			codeList.append("<p><a class=\"btn btn-primary\" href=\"code/code!findCodeById?codeDTO.id="+code.getId()+"\">获取源码</a></p>\n");
			codeList.append("</div></div></div>\n");
			if((i+1)%3==0||i==codes.size()-1){
				codeList.append("</div>\n");
			}
		}
		// /** 实例化PageView对象,获取分页的参数、总页数、总记录数 */
		PageView pageView = new PageView(codeDTO.getPage(), result.getTotalrecord());
		/** 实例化ListInfoDTO */
		CodeListInfoDTO codeListInfoDTO = new CodeListInfoDTO(codeList.toString(), getPageHTMLF(pageView));
		return Utils.printInfo(codeListInfoDTO);
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
