package com.paySystem.ic.dao.shopcar;

import java.util.List;

import com.paySystem.ic.bean.shopcar.ShopCar;
public interface ShopCarDao {
	
	public static final String SHOPCARDAO = "ShopCarDao";
	public ShopCar queryObjectByGoodsId(Integer goodsId);
	public  List<ShopCar>   queryNormalShopCar();
	public void deleteRecordByGoodsId(Integer goodsId);
}
