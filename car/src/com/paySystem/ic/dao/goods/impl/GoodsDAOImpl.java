package com.paySystem.ic.dao.goods.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.goods.Goods;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.goods.GoodsDAO;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.goods.GoodsDTO;
/**
 * 
 * @ProjectName:car 
 * @ClassName:GoodsDAOImpl  
 * @Description:商品数据库操作Dao实现类
 * @date: 2016年2月7日下午1:47:32
 * @author: 徐凯强
 * @version: V1.0
 * @date:2016年2月7日下午1:47:32
 */
@Repository(GoodsDAO.GOODSDAO)
public class GoodsDAOImpl extends DaoSupport<Goods> implements GoodsDAO {
	/**
	 *@MethodName:queryAll 
	 *@Description:查询所有商品信息
	 *@param firstindex
	 *@param pageNum
	 *@param goodsDTO
	 *@param orderBy
	 *@author:徐凯强
	 *@return QueryResult<GoodsDTO>
	 *@date:2016年2月7日下午2:17:09
	 */
	public QueryResult<GoodsDTO> queryAll(int firstindex, int pageNum,		GoodsDTO goodsDTO, LinkedHashMap<String, String> orderBy){
		StringBuffer wherejpql=new StringBuffer();
		List<Object> queryParams =new ArrayList<Object>();
		QueryResult<Goods> queryResult=null;
		QueryResult<GoodsDTO> queryResult2=new QueryResult<GoodsDTO>();
		List<GoodsDTO> list=new ArrayList<GoodsDTO>();
		if(goodsDTO.getGoodsName()!=null&&!goodsDTO.getGoodsName().trim().equals("")){
			wherejpql.append(" and o.goodsName like ?");
			queryParams.add("%"+goodsDTO.getGoodsName()+"%");
		}
		if(goodsDTO.getBrand()!=null&&!goodsDTO.getBrand().trim().equals("")){
			wherejpql.append(" and o.brand like ?");
			queryParams.add("%"+goodsDTO.getBrand()+"%");
		}
		if(goodsDTO.getMerchant()!=null&&!goodsDTO.getMerchant().trim().equals("")){
			wherejpql.append(" and o.merchant like ?");
			queryParams.add("%"+goodsDTO.getMerchant()+"%");
		}
//		if(goodsDTO.getBeginDate()!=null&&!goodsDTO.getBeginDate().trim().equals("")){
//			wherejpql.append(" and o.purchaseDate>='"+goodsDTO.getBeginDate()+" 00:00:00'");
//		}
//		
//		if(goodsDTO.getEndDate()!=null&&!goodsDTO.getEndDate().trim().equals("")){
//			wherejpql.append(" and o.purchaseDate<='"+goodsDTO.getEndDate()+" 23:59:59'");
//		}
		if(goodsDTO.getStatus()!=null&&goodsDTO.getStatus()!=-1){
			wherejpql.append(" and o.status="+goodsDTO.getStatus());
		}
		if(goodsDTO.getGoodsTypeId()!=null&&goodsDTO.getGoodsTypeId()!=-1){
			wherejpql.append(" and o.goodsTypeId="+goodsDTO.getGoodsTypeId());
		}
		if(goodsDTO.getModel()!=null&&!goodsDTO.getModel().equals("")){
			wherejpql.append(" and o.model like ?");
			queryParams.add("%"+goodsDTO.getModel()+"%");
		}
		try {
			queryResult=this.getScrollData(firstindex, pageNum, wherejpql.toString(), queryParams.toArray(), orderBy);
			for (Goods goods : queryResult.getResultlist()) {
				GoodsDTO goodsDTO2=new GoodsDTO();
				goodsDTO2=(GoodsDTO) EntityDtoConverter.bean2Dto(goods, goodsDTO2);
				list.add(goodsDTO2);
			}
			queryResult2.setResultlist(list);
			queryResult2.setTotalrecord(queryResult.getTotalrecord());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryResult2;
		
	}

}
