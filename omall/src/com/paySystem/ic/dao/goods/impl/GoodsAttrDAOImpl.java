package com.paySystem.ic.dao.goods.impl;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.goods.AttrEntity;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.goods.GoodsAttrDAO;

@Repository(GoodsAttrDAO.GOODSATTRDAO)
public class GoodsAttrDAOImpl extends DaoSupport<AttrEntity> 
                          implements GoodsAttrDAO{

}
