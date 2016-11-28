package com.paySystem.ic.web.action.software;

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
import com.paySystem.ic.bean.software.SoftWare;
import com.paySystem.ic.service.software.SoftWareService;
import com.paySystem.ic.service.software.SoftWareStyleService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ListInfoDTOF;
import com.paySystem.ic.web.dto.software.SoftWareDTO;

/**
 * @ProjectName:MarsPlan
 * @ClassName:CodeAction
 * @Description:代码管理控制类
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:Mar 6, 20165:13:32 AM
 */
@Controller("/software/software")
@Scope("prototype")
public class SoftWareAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;
	@Resource
	private SoftWareService softWareService;
	@Resource
	private SoftWareStyleService softWareStyleService;
	private SoftWareDTO softWareDTO = new SoftWareDTO();
	private SoftWare softWare;

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
		this.getRequest().setAttribute("softWareStyles", softWareStyleService.queryStyles());
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
	public String findSoftWareById() {
		softWare = softWareService.find(softWareDTO.getId());
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
		softWareDTO.setAuthor("徐半仙儿");
		softWareDTO.setCreateDate(new Date());
		softWareService.addSave(softWareDTO);
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
		softWareService.updateData(softWareDTO);
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
		QueryResult<SoftWare> result = softWareService.queryAll((softWareDTO.getPage() - 1) * pageNum, pageNum, softWareDTO,
				orderBy);
		/** 获取queryResult中的集合 */
		List<SoftWare> softWares = result.getResultlist();
		StringBuffer softWareList = new StringBuffer();
		for (int i = 0; i < softWares.size(); i++) {
			SoftWare softWare = softWares.get(i);
			softWareList.append("<div class=\"media\">\n");
			softWareList.append("<div class=\"media-body\">");
			softWareList.append("<h4 class=\"media-heading\"><a href=\"software/software!findSoftWareById?softWareDTO.id="
					+ softWare.getId() + "\">" + softWare.getName() + "</a></h4>\n");
			softWareList.append(softWare.getSummary());
			softWareList.append("</div>\n");
			softWareList.append("<p class=\"text-right text-danger\">"
					+ DateTimeTool.dateFormat("", softWare.getCreateDate()) + " </p></div><hr/>");
		}
		// /** 实例化PageView对象,获取分页的参数、总页数、总记录数 */
		PageView pageView = new PageView(softWareDTO.getPage(), result.getTotalrecord());
		/** 实例化ListInfoDTO */
		ListInfoDTOF listInfoDTOF = new ListInfoDTOF(softWareList.toString(), getPageHTMLF(pageView));
		return Utils.printInfo(listInfoDTOF);
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
		QueryResult<SoftWare> result = softWareService.queryAll((softWareDTO.getPage() - 1) * pageNum, pageNum, softWareDTO,
				orderBy);
		List<List<String>> lists = new ArrayList<List<String>>();
		/** 获取queryResult中的集合 */
		List<SoftWare> softWares=result.getResultlist();
		for (int i = 0; i < softWares.size(); i++) {
			SoftWare softWare = softWares.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(softWare.getId().toString());
			strings.add("<img src=" + softWare.getThumbnailPath() + " width='30px' height='30px'/>");
			strings.add(softWare.getName());
			strings.add(softWare.getAuthor());
			strings.add(softWare.getTypeId().toString());
			strings.add(softWare.getPanHref());
			strings.add(softWare.getSummary());
			strings.add(DateTimeTool.dateFormat("", softWare.getCreateDate()));
			String operation = "";
			if (Utils.checkPermission("sy-2101-04")) {
				operation += "<a href='base/goods!checkUI?goodsDTO.goodsId=" + softWare.getId() + "' title='查看'>"
						+ Globals.IMG_VIEW + "</a>&nbsp;";
			}
			if (Utils.checkPermission("sy-2101-03")) {
				operation += "<a href='software/software!editUI?SoftWareDTO.id=" + softWare.getId() + "' title='修改'>"
						+ Globals.IMG_EDIT + "</a>&nbsp;";
			}
			if (Utils.checkPermission("sy-2101-02")) {
				operation += "<a href='code/code!checkUI?goodsDTO.goodsId=" + softWare.getId() + "' title='删除'>"
						+ Globals.IMG_DELETE + "</a>&nbsp;";
			}
			strings.add(operation);
			lists.add(strings);
		}
		PageView pageView = new PageView(softWareDTO.getPage(), result.getTotalrecord());
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
			this.getRequest().setAttribute("softWareStyles", softWareStyleService.queryStyles());
			SoftWare softWare = softWareService.find(softWareDTO.getId());
			softWareDTO = (SoftWareDTO) EntityDtoConverter.bean2Dto(softWare, softWareDTO);
			this.setMethod("updateData");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}

	public SoftWareDTO getSoftWareDTO() {
		return softWareDTO;
	}

	public void setSoftWareDTO(SoftWareDTO softWareDTO) {
		this.softWareDTO = softWareDTO;
	}

	public SoftWare getSoftWare() {
		return softWare;
	}

	public void setSoftWare(SoftWare softWare) {
		this.softWare = softWare;
	}

}
