package com.paySystem.ic.service.shopcar;

import java.util.List;

import com.paySystem.ic.bean.shopcar.ShopCar;
import com.paySystem.ic.dao.common.DAO;

public interface ShopCarService extends DAO<ShopCar>{

	/** 常量 */
	public static final String SHOPCARSERVICE = "ShopCarService";

	public void addSave(ShopCar shopCar);
	
	public ShopCar queryObjectByGoodsId(Integer goodsId);
	
	
	public  List<ShopCar>   queryNormalShopCar();
	
	public void deleteRecordByGoodsId(Integer goodsId);
}