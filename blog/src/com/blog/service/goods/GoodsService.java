package com.blog.service.goods;

import com.blog.bean.QueryResult;
import com.blog.bean.goods.Goods;
import com.blog.dao.common.DAO;
import com.blog.dto.goods.GoodsDTO;

public interface GoodsService extends DAO<Goods> {
	/** 常量 */
	public static final String GOODSSERVICE = "GoodsService";
	
	
	public QueryResult<Goods>  queryAll();
	
	public  void addSave(GoodsDTO goodsDTO);
}
