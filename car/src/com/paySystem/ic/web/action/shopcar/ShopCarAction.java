package com.paySystem.ic.web.action.shopcar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.management.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.goods.Goods;
import com.paySystem.ic.bean.goods.GoodsType;
import com.paySystem.ic.bean.shopcar.ShopCar;
import com.paySystem.ic.service.goods.GoodsService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.shopcar.ShopCarService;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.goods.GoodsTypeDTO;
import com.paySystem.ic.web.dto.shopcar.ShopCarDTO;

@Controller("/shopcar/shopcar")
@Scope("prototype")
public class ShopCarAction extends BaseAction implements Serializable {
	private static final long serialVersionUID = 4566492141509678748L;
	// 商品实体
	private ShopCar shopCar = new ShopCar();
	// 商品DTO
	private ShopCarDTO shopCarDTO = new ShopCarDTO();
	private Goods goods;
	@Resource
	FunctionsService functionsService;
	@Resource
	private ShopCarService shopCarService;
	@Resource
	private GoodsService goodsService;

	public String addSave() {
		try {
			// 获取购物车列表
			QueryResult<ShopCar> queryResult = shopCarService.getScrollData();
			shopCar.setGoodsId(goods.getId());
			Goods goods2 = goodsService.find(goods.getId());
			shopCar.setGoodsName(goods2.getGoodsName());
			shopCar.setPrice(goods2.getPrice());
			// 循环遍历所有的记录，如果和提交的goods编号相同，就进行更新
			for (ShopCar shopCar1 : queryResult.getResultlist()) {
				if (shopCar1.getGoodsId().equals(goods.getId())) {
					ShopCar shopCarDB = shopCarService.queryObjectByGoodsId(goods.getId());
					// 总数量＝数据库中的数量+表单提交的数量。
					shopCarDB.setGoodsName(shopCar.getGoodsName());
					shopCarDB.setPrice(goods2.getPrice());
					shopCarDB.setCount(shopCar.getCount() + shopCarDB.getCount());
					shopCarService.update(shopCarDB);
					return "list";
				}
			}
			shopCarService.addSave(shopCar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

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
		QueryResult<ShopCar> result = shopCarService.getScrollData((shopCarDTO.getPage() - 1) * pageNum, pageNum,
				orderBy);
		/** 获取queryResult中的集合 */
		List<ShopCar> shopCars = result.getResultlist();
		/** 字符串信息集合 */
		List<List<String>> lists = new ArrayList<List<String>>();
		/** QueryResult对象的getResultlist方法获取SalesSummaryDTO集合 */
		for (int i = 0; i < shopCars.size(); i++) {
			/** 向页面赋值 */
			ShopCar shopCar = shopCars.get(i);

			Goods goods = goodsService.find(shopCar.getGoodsId());
			//如果是下架或者是删除的商品，不在购物车中进行显示
			if (goods.getStatus() != 9 && goods.getStatus() != 2) {
				List<String> strings = new ArrayList<String>();
				/** 添加到字符串集合中去 */
				strings.add(shopCar.getId().toString());
				/** 商品名称 */
				strings.add(shopCar.getGoodsName());
				strings.add(shopCar.getCount() + "");
				String operation = "";
				operation += "<a href=javascript:deleteData('shopcar/shopcar!delete','" + shopCar.getId()
						+ "') title='删除'>" + Globals.IMG_DELETE + "</a>&nbsp;";
				strings.add(operation);
				lists.add(strings);
			}
		}
		// /** 实例化PageView对象,获取分页的参数、总页数、总记录数 */
		PageView pageView = new PageView(shopCarDTO.getPage(), result.getTotalrecord());
		/** 实例化ListInfoDTO */
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	public String delete() {

		try {
			shopCarService.delete(Integer.parseInt(this.getId()));
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}

	public ShopCar getShopCar() {
		return shopCar;
	}

	public void setShopCar(ShopCar shopCar) {
		this.shopCar = shopCar;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

}
