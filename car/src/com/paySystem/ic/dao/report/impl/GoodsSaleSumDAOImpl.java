package com.paySystem.ic.dao.report.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.report.GoodsSaleSumDAO;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.report.GoodsSaleSumDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:GoodsSaleSumDAOImpl
 * @Description:商品销量汇总排名DAO接口的实现类
 * @date: 2014-9-10
 * @author: 王楠
 * @version: V1.0
 */
@Repository(GoodsSaleSumDAO.GOODSSALESUMDAO)
public class GoodsSaleSumDAOImpl extends DaoSupport<GoodsSaleSumDTO>implements GoodsSaleSumDAO{

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.report.GoodsSaleSumDAO#queryAll(int, int, com.paySystem.ic.web.dto.report.GoodsSaleSumDTO, java.util.LinkedHashMap)
	 *@MethodName:queryAll
	 *@Description:查询商品销量汇总排名信息
	 *@param page 起始页
	 *@param pageNum 页容量
	 *@param goodsSaleSumDTO 商品销量汇总实体的DTO 
	 *@param orderBy 排序
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-9-10下午02:09:47
	 */
	@SuppressWarnings("unchecked")
	public QueryResult<GoodsSaleSumDTO> queryAll(int page, int pageNum,
			GoodsSaleSumDTO goodsSaleSumDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		List<GoodsSaleSumDTO> goodsList=new ArrayList<GoodsSaleSumDTO>();
		/**封装查询where条件*/
		StringBuilder sql=new StringBuilder();
		sql.append("select gss.goodsName,gss.goodsItem,sum(gss.qty),sum(gss.price)" +
				   " from v_goodssalesum gss where 1=1 ");
		UserSession us=Utils.getUserSession();
		/**设置权限*/
		switch(us.getUserLevel()){
		case 0:
			break;
		case 2:
			sql.append(" and gss.merid='"+us.getMerId()+"'");
			break;
		}
		if(StringUtils.isNotBlank(goodsSaleSumDTO.getBeginDate())){
			sql.append(" and str_to_date(gss.orderTime,'%Y-%m-%d')>='"
					+goodsSaleSumDTO.getBeginDate()+"'");
		}
		if(StringUtils.isNotBlank(goodsSaleSumDTO.getEndDate())){
			sql.append(" and str_to_date(gss.orderTime,'%Y-%m-%d')<='"
					+goodsSaleSumDTO.getEndDate()+"'");
		}
		sql.append(" group by gss.goodsName,gss.goodsItem ");
		/**排序功能*/
		sql.append(buildViewOrderby(orderBy));
		QueryResult<GoodsSaleSumDTO> queryResult=new QueryResult<GoodsSaleSumDTO>();
		List<Object[]> objList=new ArrayList<Object[]>();
		Query query =em.createNativeQuery(sql.toString());
		queryResult.setTotalrecord(query.getResultList().size());
		if(page!=-1 && pageNum!=-1){
			query.setFirstResult(page);
			query.setMaxResults(pageNum);
		}
		objList=(List<Object[]>)query.getResultList();
		/**创建GoodsSaleSumDTO对象*/
		for(int i=0;i<objList.size();i++){
			GoodsSaleSumDTO goodsDTO=new GoodsSaleSumDTO();
			/**构造每一个goodsDTO对象*/
			Object[] goodsObj=objList.get(i);
			goodsDTO.setGoodsName((String)goodsObj[0]);
			goodsDTO.setGoodsItem((String)goodsObj[1]);
			goodsDTO.setQty(Integer.parseInt(goodsObj[2].toString()));
			goodsDTO.setPrice(new BigDecimal(goodsObj[3].toString()));
			goodsDTO.setAvgPrice(NumberUtil.div(goodsDTO.getPrice(), new BigDecimal(goodsDTO.getQty().toString())));
			goodsList.add(goodsDTO);
		}
		queryResult.setResultlist(goodsList);
		/**返回queryResult对象到Service层*/
		return queryResult;
	}

	/**
	*@Title:buildViewOrderby
	*@Description:查询视图排序，构造排序sql
	*@Params:@param orderby 排序的参数
	*@Params:@return 返回值
	*@Return:String
	*@author:王楠
	*@Date:2014-9-10下午02:34:14
	*/
	private  String buildViewOrderby(LinkedHashMap<String, String> orderby) {
		StringBuffer orderbyql = new StringBuffer("");
		if (orderby != null && orderby.size() > 0) {
			orderbyql.append(" order by ");
			for (String key : orderby.keySet()) {
				orderbyql.append("").append(key).append(" ").append(
						orderby.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length() - 1);// 删除最后一个字符
		}
		return orderbyql.toString();
	}


	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.report.GoodsSaleSumDAO#exportGoodsSale(com.paySystem.ic.web.dto.report.GoodsSaleSumDTO, java.util.LinkedHashMap)
	 *@MethodName:exportGoodsSale
	 *@Description:导出商品销量汇总信息
	 *@param goodsSaleSumDTO 商品销量汇总排名实体的DTO
	 *@param orderBy 排序
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-9-10下午02:16:07
	 */
	public QueryResult<GoodsSaleSumDTO> exportGoodsSale(
			GoodsSaleSumDTO goodsSaleSumDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return queryAll(-1,-1,goodsSaleSumDTO,orderBy);
	}

}
