package com.paySystem.ic.dao.stock.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.CardBIN;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.dao.card.CardBINDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.stock.MerStockDao;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:MerStockDaoImpl
 * @Description:商户库存变动Dao实现类
 * @date: 2014-5-27上午11:02:05
 * @author: 谢洪飞
 * @version: V1.0
 */
@Repository(MerStockDao.MERSTOCKDAO)
public class MerStockDaoImpl extends DaoSupport<ModStock> implements MerStockDao {

	@Resource CardBINDAO  cardBinDao;
	
	
	/**
	 *@Title:saveMerStock
	 *@Description:
	 *            商户领卡，保存库存变动信息
	 *@param:
	 *            @param modStockDTO
	 *@return:
	 *             void
	 *@author:
	 *             谢洪飞
	 */
	public ReturnDTO saveMerStock(ModStockDTO modStockDTO) {
		
		ModStock modStock =
			 modStockDTO ==null?new ModStock():this.convertBean(modStockDTO);
	    
	    this.save(modStock);

	    ReturnDTO dto = new ReturnDTO();
		/** 库存变动流水号集合 ModStock.ids */
		List<String> ids = new ArrayList<String>();
		ids.add(modStock.getId());
		Map map = new HashMap();
		map.put("ids", ids);
		dto.setObj(map);
		return dto;
	}
	
	ModStock setModStock(String cardBinNo, String beginNo, Integer cardCount,
			String organId,String merId) {
		UserSession us = Utils.getUserSession();
		ModStock modStock = new ModStock();
		String modStockId = getModStockSerNo();
		modStock.setId(modStockId);
		
		modStock.setBeginCardNo(beginNo);
		modStock.setCardCount(cardCount);
		modStock.setOutStype(0);// 设置出库类型为 - 0：机构
		modStock.setInStype(1); // 设置入库类型为 - 0：机构
		modStock.setOutId(organId);
		modStock.setInId(merId);
		modStock.setProposer(us.getUserName());
		modStock.setStatus(0);// 设定未入库-0状态
		modStock.setCreateTime(getSysTime());
		CardBIN cardBin = new CardBIN();
		if(cardBinNo!=null
		  &&!(cardBinNo.equals("-1")))
		{
			//如果cardBinNo 不为 -1，即：cardBinNo 已选中
			cardBin = cardBinDao.find(cardBinNo);
		}
		modStock.setCardBin(cardBin);
		
		return modStock;
	}
	
	/**
	 *@Title:
	 *         convertBean
	 *@Description:
	 *         转换DTO到Bean
	 *@param:
	 *         @param modStockDto
	 *@param:
	 *         @return
	 *@return:
	 *         ModStock
	 *@author:
	 *         谢洪飞
	 */
	private ModStock convertBean(ModStockDTO modStockDto)
	{
		UserSession us = Utils.getUserSession();
		ModStock modStock = new ModStock();
		
		modStock.setId(this.getModStockSerNo());
		modStock.setAuditTime(modStockDto.getAuditTime());
		modStock.setBeginCardNo(modStockDto.getBeginCardNo());
		
		CardBIN cardBin = new CardBIN();
		
		if(modStockDto.getCardBinNo()!=null
		  &&!(modStockDto.getCardBinNo().equals("-1")))
		{
			//如果cardBinNo 不为 -1，即：cardBinNo 已选中
			cardBin = cardBinDao.find(modStockDto.getCardBinNo());
		}
		
		modStock.setCardBin(cardBin);
		modStock.setCardCount(modStockDto.getCardCount());
		modStock.setCheckMen(modStockDto.getCheckMen());
		modStock.setCreateTime(getSysTime());
		modStock.setFlag(2);//商户入库标志
		modStock.setInId(modStockDto.getMerId());
		modStock.setInStype(1);//入库单位级别：  0 - 机构 ， 1 - 商户
        modStock.setOutId(us.getOrganId());
        modStock.setOutStype(0);//出库单位级别： 0 - 机构 ， 1 - 商户
        modStock.setMerId(modStockDto.getMerId());
        modStock.setOrganId(us.getOrganId());
        modStock.setProposer(us.getUserName());
        modStock.setStatus(0);//入库状态： 0 - 未入库 ， 1 - 已入库 ， 2 - 已售出
        
		return modStock;
	}
	
	
	/**
	 *@Title:getModStockSerNo
	 *@Description:获取库存变动流水号
	 *@param:@return
	 *@return:String 返回流水号
	 *@author: 謝
	 *@thorws:
	 */
	private String getModStockSerNo() {
		String modStockId = "";
		modStockId = Utils.createSerialNum(em, "id", "ModStock", 15, 0, null,
				null, null);
		return modStockId;
	}

	
	/**
	 *@Title:queryStockByCond
	 *@Description:根据条件查询商户领卡信息Service方法
	 *@param:@param firstindex
	 *@param:@param maxresult
	 *@param:@param modStockDTO
	 *@param:@param orderby
	 *@param:@return
	 *@param:@throws Exception
	 *@return:QueryResult<ModStock>
	 *@thorws:
	 */
	public QueryResult<ModStock> queryStockByCond(int firstindex,
			int maxresult, ModStockDTO modStockDTO,
			LinkedHashMap<String, String> orderby) throws Exception {
		
		StringBuilder sql = new StringBuilder(); // 封装查询where条件
		List<Object> params = new ArrayList<Object>();
		// 获取UserSession
		UserSession us = Utils.getUserSession();
		/**
		 * 限定不同级别操作员的限制条件
		 */
		switch (us.getUserLevel()) {
		case 0:
			break;
		case 1:
			sql.append(" and ( o.inId ='" + us.getOrganId() + "' or o.outId='"+ us.getOrganId() + "') ");
			break;
		case 2:
			sql.append(" and ( o.inId ='"+us.getMerId()+"' or o.outId='"+us.getMerId()+"')");
			break;
		}
		
		if(StringUtils.isNotBlank(modStockDTO.getBeginDate()))
		{
			sql.append(" and o.createTime >=to_timestamp(?").append(params.size()+1).append(",'yyyy-mm-dd hh24:mi:ss:ff')");
			params.add(DateTimeTool.queryStartDate(modStockDTO.getBeginDate()));
		}
		
		//查询时间  结束时间
		if(StringUtils.isNotBlank(modStockDTO.getEndDate())){
			sql.append(" and o.createTime <=to_timestamp(?").append(params.size()+1).append(",'yyyy-mm-dd hh24:mi:ss:ff')");
			params.add(DateTimeTool.queryEndDate(modStockDTO.getEndDate()));	
		}
		sql.append(" and o.flag = "+2+" ");
		
		//sql.append(" and o.flag = " + flag + " ");
		/** 判断页面条件 */
		if (StringUtils.isNotBlank(modStockDTO.getId())) {
			sql.append(" and o.id like ?").append(params.size() + 1);
			params.add("%" + modStockDTO.getId() + "%");
		}
		if (modStockDTO.getStatus() != -1) {
			sql.append(" and o.status = ?").append(params.size() + 1);
			params.add(modStockDTO.getStatus());
		}
		QueryResult<ModStock> queryResult = getScrollData(firstindex,
				maxresult, sql.toString(), params.toArray(), orderby);
		
		return queryResult;
	}

	
	/**
	 *@Title:sureStockIn
	 *@Description:商户领卡：确认入库操作 
	 *@param:@param modStockDTO
	 *@return: void
	 *@author: 谢洪飞
	 */
	public void sureStockIn(ModStockDTO modStockDTO) {
		
		UserSession us = Utils.getUserSession();
		
		ModStock modStock = this.find(modStockDTO.getId());
		
		modStock.setAuditTime(getSysTime());
		modStock.setStatus(1);
		modStock.setCheckMen(us.getUserName());
		modStock.setMerId(us.getMerId());
		
		this.update(modStock);
	}

	
}
