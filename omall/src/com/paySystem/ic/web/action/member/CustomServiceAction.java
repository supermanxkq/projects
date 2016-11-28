/**  
 * @Title: CustomServiceAction.java
 * @Package: com.paySystem.ic.web.action.member
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-11-13 下午03:44:39
 * @Version: V1.0  
 */
package com.paySystem.ic.web.action.member;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.member.CustomServiceService;
import com.paySystem.ic.service.member.impl.CustomServiceServiceImpl;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.member.CustomServiceDTO;

/**
 * @ProjectName:omal
 * @ClassName:CustomServiceAction
 * @Description:客服Action
 * @date: 2014-11-13
 * @author: 孟凡岭
 * @version: V1.0
 */
@Controller("/member/customService")
@Scope("prototype")
public class CustomServiceAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private CustomServiceService customServiceServic = new CustomServiceServiceImpl();
	/**
	 * 客服DTO类
	 */
	private CustomServiceDTO customServiceDTO = new CustomServiceDTO();
	@Resource
	FunctionsService functionsService;

	public String list() {
		return "list";
	}

	public String jsonPageList() throws Exception {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {// 如果页面没有要求排序方式，则设置按照会员编号排序
			orderby.put("createTime", "desc");
		}
		QueryResult<CustomServiceDTO> queryResult = customServiceServic
				.queryResult((customServiceDTO.getPage() - 1) * pageNum,
						pageNum, customServiceDTO, orderby);
		List<CustomServiceDTO> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		CustomServiceDTO c = null;
		for (int i = 0; i < list.size(); i++) {
			List<String> str = new ArrayList<String>();
			c = list.get(i);
			str.add(String.valueOf(i + 1));
			str.add(c.getName());
			str.add(c.getQq());
			str.add(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", c
					.getCreateTime()));
			String oper = "<a href=javascript:updateData('member/customService!update','"
					+ c.getId()
					+ "') title='修改'>"
					+ Globals.IMG_EDIT
					+ "</a>&nbsp;";
			oper += "<a href=javascript:deleteData('member/customService!delete','"
					+ c.getId()
					+ "') title='刪除'>"
					+ Globals.IMG_DELETE
					+ "</a>&nbsp;";
			str.add(oper);
			lists.add(str);
		}
		PageView pageView = new PageView(customServiceDTO.getPage(),
				queryResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 * 
	 *@Title:delete
	 *@Description:删除
	 *@Params:
	 *@Return:void
	 *@author:孟凡岭
	 *@Date:2014-11-18上午09:35:47
	 */
	public void delete() {
		customServiceServic.delete(this.id);
		functionsService.saveFunction("QQ客服", 3, "客服删除");
	}

	/**
	 * 
	 *@Title:update
	 *@Description:更新
	 *@Params:
	 *@Return:void
	 *@author:孟凡岭
	 *@Date:2014-11-18上午09:35:59
	 */
	public void update() {
		customServiceServic.update(customServiceDTO);
		functionsService.saveFunction("QQ客服", 2, "客服更新");
	}

	/***
	 * 
	 *@Title:add
	 *@Description:添加
	 *@Params:@throws Exception
	 *@Return:void
	 *@author:孟凡岭
	 *@Date:2014-11-18上午09:36:07
	 */
	public void add() throws Exception {
		customServiceServic.add(customServiceDTO);
		functionsService.saveFunction("QQ客服", 1, "客服添加");
	}

	/**
	 * 
	 *@Title:loadData
	 *@Description:加载单条数据
	 *@Params:
	 *@Return:void
	 *@author:孟凡岭
	 *@Date:2014-11-17下午06:37:40
	 */
	public void loadData() throws Exception {
		CustomServiceDTO c = customServiceServic.findById(customServiceDTO
				.getId());
		Utils.printInfo(c);
	}

	/**
	 * 
	 *@Title:loadCustom
	 *@Description:加载客服
	 *@Params:
	 *@Return:void
	 *@author:孟凡岭
	 *@Date:2014-11-13下午04:17:30
	 */
	public void loadCustom() throws Exception {
		List<CustomServiceDTO> list = customServiceServic
				.findByStoreId(customServiceDTO.getStoreId());
		Utils.printInfo(list);
	}

	public CustomServiceDTO getCustomServiceDTO() {
		return customServiceDTO;
	}

	public void setCustomServiceDTO(CustomServiceDTO customServiceDTO) {
		this.customServiceDTO = customServiceDTO;
	}

	public CustomServiceService getCustomServiceServic() {
		return customServiceServic;
	}

	public void setCustomServiceServic(CustomServiceService customServiceServic) {
		this.customServiceServic = customServiceServic;
	}

	public FunctionsService getFunctionsService() {
		return functionsService;
	}

	public void setFunctionsService(FunctionsService functionsService) {
		this.functionsService = functionsService;
	}


}
