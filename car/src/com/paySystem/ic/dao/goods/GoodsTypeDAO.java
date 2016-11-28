package com.paySystem.ic.dao.goods;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.goods.GoodsTypeDTO;
public interface GoodsTypeDAO {
	
	public static final String GOODSTYPEDAO = "GoodsTypeDAO";
	public QueryResult<GoodsTypeDTO> queryAll(int firstindex, int pageNum,		GoodsTypeDTO goodsTypeDTO, LinkedHashMap<String, String> orderBy);
}
