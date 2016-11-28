package com.paySystem.ic.web.action.goods;

import java.io.Serializable;
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
import com.paySystem.ic.bean.goods.Goods;
import com.paySystem.ic.bean.shopcar.ShopCar;
import com.paySystem.ic.service.goods.GoodsService;
import com.paySystem.ic.service.goods.GoodsTypeService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.shopcar.ShopCarService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.goods.GoodsDTO;

/**
 * 
 * @ProjectName:car
 * @ClassName:GoodAction
 * @Description:商品管理控制类
 * @date: 2016年2月7日下午1:29:37
 * @author: 徐凯强
 * @version: V1.0
 * @date:2016年2月7日下午1:29:37
 */
@Controller("/managers/goods")
@Scope("prototype")
public class GoodAction extends BaseAction implements Serializable {
	private static final long serialVersionUID = 4566492141509678748L;
	// 商品实体
	private Goods goods;
	// 商品DTO
	private GoodsDTO goodsDTO = new GoodsDTO();
	@Resource
	FunctionsService functionsService;
	@Resource
	private GoodsService goodsService;
	@Resource
	private GoodsTypeService goodsTypeService;
	@Resource
	private ShopCarService shopCarService;
	private ShopCar shopCar = new ShopCar();

	/**
	 * 添加订单页面
	 * 
	 * @return
	 */
	public String addUI() {
		this.setMethod("addSave");
		this.getRequest().setAttribute("goodsTypes", goodsTypeService.queryTypes());
		this.getSession().setAttribute("goodsSessionStatus", OptionsValue.GOODSSTATUS);// 状态
		return "input";
	}

	/**
	 * 
	 * @MethodName:addSave
	 * @Description:添加商品
	 * @return
	 * @author:徐凯强
	 * @return String
	 * @date:2016年2月7日下午1:31:56
	 */
	public String addSave() {
		goods.setPurchaseDate(new Date());
		goods.setGoodsTypeName(Utils.getOptionsIntegerName(goodsTypeService.queryTypes(), goods.getGoodsTypeId()));
		goodsService.addSave(goods);
		return "list";
	}

	/**
	 * @MethodName:list
	 * @Description:显示列表页面
	 * @author:徐凯强
	 * @return String返回字符串到struts.xml
	 * @date:2014-12-20下午10:18:36
	 */
	public String list() {
		this.getSession().setAttribute("goodsTypes", goodsTypeService.queryTypes());
		this.getSession().setAttribute("goodsSessionStatus", OptionsValue.GOODSSTATUS);// 状态
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
		QueryResult<GoodsDTO> result = goodsService.queryAll((goodsDTO.getPage() - 1) * pageNum, pageNum, goodsDTO,
				orderBy);
		/** 获取queryResult中的集合 */
		List<GoodsDTO> goodsDTOs = result.getResultlist();
		/** 字符串信息集合 */
		List<List<String>> lists = new ArrayList<List<String>>();
		/** QueryResult对象的getResultlist方法获取SalesSummaryDTO集合 */
		for (int i = 0; i < goodsDTOs.size(); i++) {
			/** 向页面赋值 */
			GoodsDTO goodsDTO = goodsDTOs.get(i);
			List<String> strings = new ArrayList<String>();
			/** 添加到字符串集合中去 */
			strings.add(goodsDTO.getId().toString());
			/** 商品名称 */
			strings.add(goodsDTO.getGoodsName());
			/** 品牌 */
			strings.add(goodsDTO.getBrand());
			strings.add(goodsDTO.getGoodsTypeName());
			/** 型号 */
			strings.add(goodsDTO.getModel());
			strings.add(goodsDTO.getPrice() + "");
			strings.add(goodsDTO.getCount() + "");
			strings.add(goodsDTO.getMerchant());
			strings.add(Utils.getOptionsIntegerName(OptionsValue.GOODSSTATUS, goodsDTO.getStatus()));
			strings.add(DateTimeTool.dateFormat("", goodsDTO.getPurchaseDate()));
			String operation = "";
			if (goodsDTO.getStatus() != 9) {
				operation += "<a href=managers/goods!checkUI?goodsDTO.id=" + goodsDTO.getId() + " title='加入购物车'>"
						+ Globals.IMG_SHOPPING + "</a>";
				operation += "<a href=managers/goods!editUI?goodsDTO.id=" + goodsDTO.getId() + " title='修改'>"
						+ Globals.IMG_EDIT + "</a>";
				operation += "<a href=javascript:deleteData('managers/goods!delete','" + goodsDTO.getId()
						+ "') title='删除'>" + Globals.IMG_DELETE + "</a>&nbsp;";
				strings.add(operation);
			} else {
				operation += "<a href=managers/goods!checkUI?goodsDTO.id=" + goodsDTO.getId() + " title='查看'>"
						+ Globals.IMG_VIEW + "</a>";
				strings.add(operation);
			}
			lists.add(strings);
		}
		// /** 实例化PageView对象,获取分页的参数、总页数、总记录数 */
		PageView pageView = new PageView(goodsDTO.getPage(), result.getTotalrecord());
		/** 实例化ListInfoDTO */
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	public String checkUI() {
		this.setMethod("addSave");
		goods = goodsService.find(goodsDTO.getId());
		goodsDTO.setStatusName(Utils.getOptionsIntegerName(OptionsValue.GOODSSTATUS, goods.getStatus()));
		goodsDTO.setGoodsTypeName(Utils.getOptionsIntegerName(goodsTypeService.queryTypes(), goods.getGoodsTypeId()));
		return "checkUI";
	}

	public String checkCount() {
		// 库存总数量
		Goods goods2 = goodsService.find(goods.getId());
		// 购物车总数量
		try {
			// 购物车中是否有该商品
			if (shopCarService.queryObjectByGoodsId(goods.getId()) != null) {
				ShopCar shopCarDB = shopCarService.queryObjectByGoodsId(goods.getId());

				if ((shopCar.getCount() + shopCarDB.getCount()) > goods2.getCount()) {
					return Utils.printInfo(true);
				} else {
					return Utils.printInfo(false);
				}
			} else {
				if ((shopCar.getCount()) > goods2.getCount()) {
					return Utils.printInfo(true);
				} else {
					return Utils.printInfo(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Utils.printInfo(false);
	}

	/***
	 * 
	 * @MethodName:delete
	 * @Description:逻辑删除商品
	 * @return
	 * @author:徐凯强
	 * @return String
	 * @date:2016年2月9日上午11:11:27
	 */
	public String delete() {
		try {
			Goods goods = goodsService.find(Integer.parseInt(this.getId()));
			goods.setStatus(9);
			goodsService.update(goods);
			shopCarService.deleteRecordByGoodsId(goods.getId());
			functionsService.saveFunction("商品入库", 3, "删除商品：" + this.getId());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}

	/**
	 * 
	 * @MethodName:editUI
	 * @Description:跳转到修改页面
	 * @return
	 * @author:徐凯强
	 * @return String
	 * @date:2016年2月8日下午11:26:16
	 */
	public String editUI() {
		this.getRequest().setAttribute("goodsTypes", goodsTypeService.queryTypes());
		this.getRequest().setAttribute("goodsStatus", OptionsValue.GOODSSTATUS);// 状态
		this.setMethod("updateData");
		try {
			// 通过编号查找数据库中的数据
			goods = goodsService.find(goodsDTO.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	/**
	 * 
	 * @MethodName:updateData
	 * @Description:修改商品
	 * @return
	 * @author:徐凯强
	 * @return String
	 * @date:2016年2月9日上午10:10:13
	 */
	public String updateData() {
		goods.setPurchaseDate(new Date());
		goods.setGoodsTypeName(Utils.getOptionsIntegerName(goodsTypeService.queryTypes(), goods.getGoodsTypeId()));
		goodsService.update(goods);
		this.getRequest().setAttribute("url", "managers/goods");
		return "success";
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public GoodsDTO getGoodsDTO() {
		return goodsDTO;
	}

	public void setGoodsDTO(GoodsDTO goodsDTO) {
		this.goodsDTO = goodsDTO;
	}

	public ShopCar getShopCar() {
		return shopCar;
	}

	public void setShopCar(ShopCar shopCar) {
		this.shopCar = shopCar;
	}

}
