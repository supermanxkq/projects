package com.paySystem.ic.web.action.goodstype;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.goods.GoodsType;
import com.paySystem.ic.service.goods.GoodsTypeService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.goods.GoodsTypeDTO;

@Controller("/basic/type")
@Scope("prototype")
public class GoodsTypeAction extends BaseAction implements Serializable{
	private static final long serialVersionUID = 4566492141509678748L;
	private GoodsType goodsType;
	private GoodsTypeDTO goodsTypeDTO = new GoodsTypeDTO();
	@Resource
	FunctionsService functionsService;
	@Resource
	private GoodsTypeService goodsTypeService;

	/**
	 * 添加订单页面
	 * 
	 * @return
	 */
	public String addUI() {
		this.setMethod("addSave");
		this.getSession().setAttribute("goodsTypeStatus", OptionsValue.GOODSTYPESTATUS);
		return "input";
	}

	public String addSave() {
		goodsTypeService.addSave(goodsType);
		return "list";
	}

	public String list() {
		
		this.getSession().setAttribute("goodsTypeStatus", OptionsValue.GOODSTYPESTATUS);
		return "list";
	}

	/**
	 * 
	 * @MethodName:jsonPageList
	 * @Description:Ajax查询所有的商品信息
	 * @return
	 * @throws Exception
	 * @author:徐凯强
	 * @return String
	 * @date:2016年2月7日下午2:26:52
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
		QueryResult<GoodsTypeDTO> result = goodsTypeService.queryAll((goodsTypeDTO.getPage() - 1) * pageNum, pageNum,
				goodsTypeDTO, orderBy);
		/** 获取queryResult中的集合 */
		List<GoodsTypeDTO> goodsTypeDTOs = result.getResultlist();
		/** 字符串信息集合 */
		List<List<String>> lists = new ArrayList<List<String>>();
		/** QueryResult对象的getResultlist方法获取SalesSummaryDTO集合 */
		for (int i = 0; i < goodsTypeDTOs.size(); i++) {
			/** 向页面赋值 */
			GoodsTypeDTO goodsTypeDTO = goodsTypeDTOs.get(i);
			List<String> strings = new ArrayList<String>();
			/** 添加到字符串集合中去 */
			strings.add(goodsTypeDTO.getId().toString());
			/** 商品名称 */
			strings.add(goodsTypeDTO.getName());
			/** 品牌 */
			strings.add(Utils.getOptionsIntegerName(OptionsValue.GOODSTYPESTATUS, goodsTypeDTO.getStatus()));
			String operation = "";
			if (goodsTypeDTO.getStatus() != 9) {
				operation += "<a href=basic/type!editUI?goodsTypeDTO.id=" + goodsTypeDTO.getId() + " title='修改'>"
						+ Globals.IMG_EDIT + "</a>";
				operation += "<a href=javascript:deleteData('basic/type!delete','" + goodsTypeDTO.getId()
						+ "') title='删除'>" + Globals.IMG_DELETE + "</a>&nbsp;";
				strings.add(operation);
			} else {
				operation += "<a href=managers/goodsType!checkUI?goodsDTO.id=" + goodsTypeDTO.getId() + " title='查看'>"
						+ Globals.IMG_VIEW + "</a>";
				strings.add(operation);
			}
			lists.add(strings);
		}
		// /** 实例化PageView对象,获取分页的参数、总页数、总记录数 */
		PageView pageView = new PageView(goodsTypeDTO.getPage(), result.getTotalrecord());
		/** 实例化ListInfoDTO */
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	public String delete() {
		try {
			GoodsType goodsType = goodsTypeService.find(Integer.parseInt(this.getId()));
			goodsType.setStatus(9);
			goodsTypeService.update(goodsType);
			functionsService.saveFunction("商品类别管理", 3, "删除商品类别：" + this.getId());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}

	public String editUI() {
		this.getRequest().setAttribute("goodsStatus", OptionsValue.GOODSSTATUS);// 状态
		this.setMethod("updateData");
		try {
			// 通过编号查找数据库中的数据
			goodsType = goodsTypeService.find(goodsTypeDTO.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	public String updateData() {
		goodsTypeService.update(goodsType);
		return "list";
	}

	public GoodsType getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}

	public GoodsTypeDTO getGoodsTypeDTO() {
		return goodsTypeDTO;
	}

	public void setGoodsTypeDTO(GoodsTypeDTO goodsTypeDTO) {
		this.goodsTypeDTO = goodsTypeDTO;
	}

}
