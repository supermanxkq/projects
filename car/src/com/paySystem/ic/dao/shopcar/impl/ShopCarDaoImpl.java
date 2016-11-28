package com.paySystem.ic.dao.shopcar.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.shopcar.ShopCar;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.shopcar.ShopCarDao;

@Repository(ShopCarDao.SHOPCARDAO)
public class ShopCarDaoImpl extends DaoSupport<ShopCar> implements ShopCarDao {

	@Override
	public ShopCar queryObjectByGoodsId(Integer goodsId) {
		String sql = "select * from t_shopcar where goodsId=?";
		ShopCar shopCarR = new ShopCar();
		Query query = this.em.createNativeQuery(sql);
		query.setParameter(1, goodsId);
		List<Object[]> shopCars = query.getResultList();
		if (shopCars.size() > 0) {
			Object[] object = shopCars.get(0);
			shopCarR.setId(Integer.parseInt(object[0].toString()));
			shopCarR.setCount(Integer.parseInt(object[1].toString()));
			shopCarR.setGoodsId(Integer.parseInt(object[2].toString()));
			return shopCarR;
		} else {
			return null;
		}
	}

	@Override
	public List<ShopCar> queryNormalShopCar() {
		List<ShopCar> shopCarList = new ArrayList<>();
		String sql = "select * from t_shopcar  s where s.goodsId  in( select g.id from t_goods g where g.status!=9 and g.status!=2)";
		Query query = this.em.createNativeQuery(sql);
		List<Object[]> shopCars = query.getResultList();
		if (shopCars.size() > 0) {
			for (Object[] object : shopCars) {
				ShopCar shopCarR = new ShopCar();
				shopCarR.setId(Integer.parseInt(object[0].toString()));
				shopCarR.setCount(Integer.parseInt(object[1].toString()));
				shopCarR.setGoodsId(Integer.parseInt(object[2].toString()));
				shopCarR.setGoodsName(object[3].toString());
				shopCarR.setPrice(Float.parseFloat(object[4].toString()));
				shopCarList.add(shopCarR);
			}
			return shopCarList;
		} else {
			return null;
		}
	}

	@Override
	public void deleteRecordByGoodsId(Integer goodsId) {
		String sql = "delete  from t_shopcar  where  goodsId=" + goodsId;
		Query query = this.em.createNativeQuery(sql);
		query.executeUpdate();
	}

}
