package com.paySystem.ic.web.action.goods;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.goods.AttrEntityService;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.base.GoodsAttributeDTO;
import com.paySystem.ic.web.dto.goods.AttrEntityDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ProjectName:omall
 * @ClassName:AttrEntityAction
 * @Description:商品属性值管理控制类
 * @date: 2014-10-10下午04:10:08
 * @author: 徐凯强
 * @version: V1.0
 */
@Controller("/base/attrEntity")
@Scope("prototype")
public class AttrEntityAction extends BaseAction {
	private static final long serialVersionUID = 4566492141509678748L;

	/** 注入Service */
	@Resource
	private AttrEntityService attrEntityService;

	/** 实例化DTO对象 */
	private AttrEntityDTO attrEntityDTO = new AttrEntityDTO();

	public AttrEntityDTO getAttrEntityDTO() {
		return attrEntityDTO;
	}

	public void setAttrEntityDTO(AttrEntityDTO attrEntityDTO) {
		this.attrEntityDTO = attrEntityDTO;
	}

	/** 商品属性值数据传输对象 */
	private GoodsAttributeDTO goodsAttributeDTO = new GoodsAttributeDTO();

	public GoodsAttributeDTO getGoodsAttributeDTO() {
		return goodsAttributeDTO;
	}

	public void setGoodsAttributeDTO(GoodsAttributeDTO goodsAttributeDTO) {
		this.goodsAttributeDTO = goodsAttributeDTO;
	}

	/**
	 *@Title:list
	 *@Description:异步加载列表页面
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-10-10下午04:11:19
	 */
	public String list() throws Exception {
		/** 获取所有的属性的集合 */
		QueryResult<GoodsAttributeDTO> aQueryResultDTO = attrEntityService
				.findAll(-1, -1, goodsAttributeDTO, null);
		/** 从集合中获取所有属性名称 */
		List<OptionsInteger> names = new ArrayList<OptionsInteger>();
		/** 使用for循环将获取的属性名称添加到集合中 */
		for (int i = 0; i < aQueryResultDTO.getResultlist().size(); i++) {
			/** 获取集合中对象的属性名称并添加到集合中去 */
			names.add(new OptionsInteger(i, aQueryResultDTO.getResultlist()
					.get(i).getAttrName()));
		}
		/** 通过request对象进行传递到页面中 */
		this.getRequest().setAttribute("goodsAttributeName", names);
		return "list";
	}

	/**
	 *@MethodName:jsonPageList
	 *@Description:获取所有商品属性记录，并返回到GoodsAttributesList.jsp页面中
	 *@throws Exception抛出异常
	 *@author:徐凯强
	 *@return String Date:2014-10-12下午03:50:38
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {
		/** 存储排序信息的集合 */
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		/** 如果获取排序顺序为空，设置为降序排列 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderby.put("attrId", "desc");
		}
		/** 获取queryResult中的值 */
		QueryResult<GoodsAttributeDTO> quResult = attrEntityService.findAll(
				(goodsAttributeDTO.getPage() - 1) * pageNum, pageNum,
				goodsAttributeDTO, orderby);
		/** 获取queryResult中的集合 */
		List<GoodsAttributeDTO> dtoResult = quResult.getResultlist();
		/** 结算信息字符串信息集合 */
		List<List<String>> lists = new ArrayList<List<String>>();
		/** QueryResult对象的getResultlist方法获取GoodsAttributeDTO集合 */
		for (int i = 0; i < dtoResult.size(); i++) {
			GoodsAttributeDTO goodsAttributeDTO = dtoResult.get(i);
			List<String> strings = new ArrayList<String>();
			/** 添加到字符串集合中去 */
			/** 属性编号 */
			strings.add(Utils.getString(goodsAttributeDTO.getAttrId()));
			/** 属性名称 */
			strings.add(Utils.getString(goodsAttributeDTO.getAttrName()));
			/** 是否枚举 */
			strings.add(Utils.getOptionsIntegerName(
					OptionsValue.VISIBLE_STATUS, goodsAttributeDTO.getIsEn()));
			/** 操作字段 */
			String operation = "";
			operation += "<a href=base/attrEntity!showAttrEntities?goodsAttributeDTO.attrId="
					+ goodsAttributeDTO.getAttrId()
					+ " title='添加枚举值'>"
					+ Globals.IMG_VIEW + "</a>&nbsp;";
			strings.add(operation);
			lists.add(strings);
		}
		/** 实例化PageView对象,获取分页的参数、总页数、总记录数 */
		PageView pageView = new PageView(goodsAttributeDTO.getPage(), quResult
				.getTotalrecord());
		/** 实例化ListInfoDTO */
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 *@Title:showAttrEntities
	 *@Description:跳转到属性值界面
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-10-12下午08:06:19
	 */
	public String showAttrEntities() {
		/** 传递goodsAttributeDTO，用于传递编号 */
		this.getRequest().setAttribute("goodsAttributeDTO", goodsAttributeDTO);
		return "show";
	}

	/**
	 *@MethodName:jsonPageListForAttrEntities
	 *@Description:获取所有商品属性值记录，并返回到AttrEntityList.jsp页面中
	 *@throws Exception抛出异常
	 *@author:徐凯强
	 *@return String Date:2014-10-12下午03:50:38
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageListForAttrEntities() throws Exception {
		/** 存储排序信息的集合 */
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		/** 如果获取排序顺序为空，设置为降序排列 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderby.put("attrEnId", "desc");
		}
		/** 获取queryResult中的值 */
		QueryResult<AttrEntityDTO> quResult = attrEntityService
				.showAttrEntities((attrEntityDTO.getPage() - 1) * pageNum,
						pageNum, attrEntityDTO, goodsAttributeDTO, orderby);
		/** 获取queryResult中的集合 */
		List<AttrEntityDTO> dtoResult = quResult.getResultlist();
		/** 结算信息字符串信息集合 */
		List<List<String>> lists = new ArrayList<List<String>>();
		/** QueryResult对象的getResultlist方法获取AttrEntityDTO集合 */
		for (int i = 0; i < dtoResult.size(); i++) {
			AttrEntityDTO attrEntityDTO = dtoResult.get(i);
			List<String> strings = new ArrayList<String>();
			/** 添加到字符串集合中去 */
			/** 属性编号 */
			strings.add(Utils.getString(attrEntityDTO.getAttrEnId()));
			/** 属性名称 */
			strings.add(Utils.getString(attrEntityDTO.getAttrEnName()));
			lists.add(strings);
		}
		/** 实例化PageView对象,获取分页的参数、总页数、总记录数 */
		PageView pageView = new PageView(attrEntityDTO.getPage(), quResult
				.getTotalrecord());
		/** 实例化ListInfoDTO */
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 *@Title:addUI
	 *@Description:跳转到添加属性值页面
	 *@Return:String返回字符串结果到struts.xml
	 *@author:徐凯强
	 *@Date:2014-10-13上午12:05:40
	 */
	public String addUI() {
		this.setMethod("addSave");
		return "input";
	}

	/**
	 *@Title:addSave
	 *@Description:添加
	 *@Return:String返回字符串结果到struts.xml
	 *@author:徐凯强
	 *@Date:2014-10-13上午12:05:40
	 */
	public String addSave() {
		attrEntityService.addSave(attrEntityDTO);
		this.getRequest().setAttribute("result",
				this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url", "base/attrEntity!list");
		return SUCCESS;
	}
}
