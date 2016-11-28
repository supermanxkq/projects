package com.paySystem.ic.service.shopcar.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.shopcar.ShopCar;
import com.paySystem.ic.dao.shopcar.ShopCarDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.shopcar.ShopCarService;

/**
 * @ProjectName:omall
 * @ClassName:AttrEntityServiceImpl
 * @Description:属性值服务实现类
 * @date: 2014-10-10下午04:49:42
 * @author: 徐凯强
 * @version: V1.0
 */
@Service(ShopCarService.SHOPCARSERVICE)
public class ShopCarServiceImpl extends DaoSupport<ShopCar> implements ShopCarService {

	/** 注入GoodsDao */
	@Resource
	private ShopCarDao shopCarDao;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSave(ShopCar shopCar) {
		this.save(shopCar);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ShopCar queryObjectByGoodsId(Integer goodsId) {
		return shopCarDao.queryObjectByGoodsId(goodsId);
	}

	@Override
	public List<ShopCar> queryNormalShopCar() {
		return shopCarDao.queryNormalShopCar();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteRecordByGoodsId(Integer goodsId) {
		shopCarDao.deleteRecordByGoodsId(goodsId);
	}
}
