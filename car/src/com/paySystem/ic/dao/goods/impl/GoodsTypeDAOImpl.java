package com.paySystem.ic.dao.goods.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.goods.Goods;
import com.paySystem.ic.bean.goods.GoodsType;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.goods.GoodsTypeDAO;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.goods.GoodsDTO;
import com.paySystem.ic.web.dto.goods.GoodsTypeDTO;
@Repository(GoodsTypeDAO.GOODSTYPEDAO)
public class GoodsTypeDAOImpl extends DaoSupport<GoodsType> implements GoodsTypeDAO {
	public QueryResult<GoodsTypeDTO> queryAll(int firstindex, int pageNum,		GoodsTypeDTO goodsTypeDTO, LinkedHashMap<String, String> orderBy){
		StringBuffer wherejpql=new StringBuffer();
		List<Object> queryParams =new ArrayList<Object>();
		QueryResult<GoodsType> queryResult=null;
		QueryResult<GoodsTypeDTO> queryResult2=new QueryResult<GoodsTypeDTO>();
		List<GoodsTypeDTO> list=new ArrayList<GoodsTypeDTO>();
		
		if (goodsTypeDTO.getName()!=null&&!goodsTypeDTO.getName().equals("")) {
			wherejpql.append(" and o.name like ?");
			queryParams.add("%"+goodsTypeDTO.getName()+"%");
		}
		if(goodsTypeDTO.getStatus()!=-1&&goodsTypeDTO.getStatus()!=null){
			wherejpql.append("and o.status="+goodsTypeDTO.getStatus());
		}
		try {
			queryResult=this.getScrollData(firstindex, pageNum, wherejpql.toString(), queryParams.toArray(), orderBy);
			for (GoodsType goodsType : queryResult.getResultlist()) {
				GoodsTypeDTO goodsTypeDTO2=new GoodsTypeDTO();
				goodsTypeDTO2=(GoodsTypeDTO) EntityDtoConverter.bean2Dto(goodsType, goodsTypeDTO2);
				list.add(goodsTypeDTO2);
			}
			queryResult2.setResultlist(list);
			queryResult2.setTotalrecord(queryResult.getTotalrecord());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryResult2;
		
	}

}
