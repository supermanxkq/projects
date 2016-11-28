package com.paySystem.ic.dao.stock.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.dao.card.CardBINDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.stock.HeadQuinDao;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:HeadQuinDaoImpl
 * @Description:总部入库Dao实现类
 * @date: 2013-12-11下午04:53:42
 * @author: 谢洪飞
 * @version: V1.0
 */
@Repository(HeadQuinDao.HEADQUINDAO)
public class HeadQuinDaoImpl extends DaoSupport<ModStock> implements
		HeadQuinDao {

	@Resource
	CardBINDAO cardBinDao;

	public QueryResult<ModStock> queryStockByCond(int firstindex,
			int maxresult, ModStockDTO modStockDTO,
			LinkedHashMap<String, String> orderby, int flag) throws Exception {
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
		
		sql.append(" and o.flag = " + flag + " ");
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
	 *@Title:addModStockInfo
	 *@Description:添加库存变动信息--总部入库/机构领卡
	 *@param:@param modStockDTO 库存变动DTO对象
	 *@param:@return
	 *@return:int 本次保存操作影响行数
	 *@author: 謝
	 *@thorws:
	 */
	public ReturnDTO addModStockInfo(ModStockDTO modStockDTO,int flag) throws Exception {
		ReturnDTO dto = new ReturnDTO();
		/** 库存变动流水号集合 ModStock.ids */
		List<String> ids = new ArrayList<String>();
		UserSession us = Utils.getUserSession();
		String organId = "";
		Map<String,Object> map =  new HashedMap();
		
		if (modStockDTO != null) {
			List<String> beginNos = modStockDTO.getBeginCardNos();
			List<String> endNos = modStockDTO.getEndNos();
			List<Integer> cardCounts = modStockDTO.getCardCounts();
			List<String> cardBins = modStockDTO.getCardBinNos();

			for (int i = 0; i < beginNos.size(); i++) {
				ModStock modStock = new ModStock();
				// 如果本条记录的起始卡号/数量/卡BIN都不为空，则:
				if (StringUtils.isNotBlank(beginNos.get(i))
						&& StringUtils.isNotBlank(endNos.get(i))
						&& cardCounts.get(i) != null
						&& StringUtils.isNotBlank(cardBins.get(i))) {
					if(flag == 1){
						organId = us.getOrganId();
					}
					else if (flag == 2) {
						organId = cardBinDao.find(cardBins.get(i)).getOrgans().getOrganId();
					}
					// 封装ModStock对象
					modStock = setModStock(cardBins.get(i), beginNos.get(i),
							cardCounts.get(i), organId,flag);

					// 添加到库存变动集合
					// 遍历ModStock集合并保存库存变动信息
					modStock.setCreateTime(new Date());
					this.save(modStock);
					ids.add(modStock.getId());
					map.put("modStock", modStock);
					map.put("id", modStock.getId());//库存变动ID
					dto.setFlag(true);
				}
			}
		}
        
		map.put("ids", ids);
		dto.setObj(map);
		
		return dto;
	}

	/**
	 *@Title:getModStockSerNo
	 *@Description:获取库存变动流水号
	 *@param:@return
	 *@return:String 返回流水号
	 *@author: 謝
	 *@thorws:
	 */
	public String getModStockSerNo() {
		String modStockId = "";
		modStockId = Utils.createSerialNum(em, "id", "ModStock", 15, 0, null,
				null, null);
		return modStockId;
	}

	/**
	 *@Title:setModStock
	 *@Description:封装库存变动信息--总部入库
	 *@param:@param beginNo
	 *@param:@param cardCount
	 *@param:@param cardBin
	 *@param:@return
	 *@return:ModStock
	 *@author:
	 *@thorws:
	 */
	ModStock setModStock(String cardBin, String beginNo, Integer cardCount,
			String organId,int flag) {
		UserSession us = Utils.getUserSession();
		ModStock modStock = new ModStock();
		String modStockId = getModStockSerNo();
		modStock.setId(modStockId);
		modStock.setCardBin(cardBinDao.find(cardBin));
		modStock.setBeginCardNo(beginNo);
		modStock.setCardCount(cardCount);
		modStock.setOutStype(0);// 设置出库类型为 - 0：机构
		modStock.setInStype(0); // 设置入库类型为 - 0：机构
		if (flag == 2) {
			modStock.setOutId(us.getOrganId());
			modStock.setInId(organId);
			modStock.setFlag(1);//发卡机构入库
		} else if (flag == 1){
			modStock.setOutId("99999999");// 表示卡厂出库
			modStock.setInId(us.getOrganId());
			modStock.setFlag(0); // 设定总部入库
		}
		modStock.setProposer(us.getUserName());
		modStock.setStatus(0);// 设定未入库-0状态
		modStock.setCreateTime(getSysTime());

		return modStock;
	}

	/**
	 *@Title:modfiyStatusStockIn
	 *@Description:确认入库:更改库存变动信息表中信息状态为： 从 0:未入库 → 1:已入库
	 *@Param:@return
	 *@Return:ReturnDTO
	 *@Throws:
	 */
	public ReturnDTO modfiyStatusStockIn(ModStockDTO modStockDTO)
			throws Exception {
		UserSession us = Utils.getUserSession();
		ReturnDTO dto = new ReturnDTO();
		ModStock modStock = this.find(modStockDTO.getId());
		modStock.setAuditTime(new Date());
		modStock.setStatus(1);
		modStock.setProposer(us.getUserName());
		this.update(modStock);
		dto.setFlag(true);
		return dto;
	}
	
	/**
	 *@Title:convertModStockDTO
	 *@Description:将ModStockDTO 转为 ModStock类型
	 *@Param:@return
	 *@Return:ModStock
	 *@Throws:
	 */
	public ModStock convertModStockDTO(ModStockDTO modStockDTO){
		ModStock modStock = new ModStock();
		modStock.setAuditTime(modStockDTO.getAuditTime());
		modStock.setBeginCardNo(modStockDTO.getBeginCardNo());
		modStock.setCardBin(modStockDTO.getCardBin());
		modStock.setCardCount(modStockDTO.getCardCount());
		return null;
	}

	/*
	 * <p>Title: saveModStockDTO</p> <p>Description: 保存ModStockDTO实现</p>
	 * @param modStockDTO
	 * @see
	 * com.paySystem.ic.dao.stock.HeadQuinDao#saveModStockDTO(com.paySystem.
	 * ic.web.dto.stock.ModStockDTO)
	 * @author lily
	 * @date 2013-12-24 下午07:37:21
	 */
	public void saveModStockDTO(ModStockDTO modStockDTO) {
		this.save(getModStock(modStockDTO));
	}

	/**
	 * @Title: getModStock
	 * @Description: 将ModStockDTO转换成ModStock实体的私有方法
	 * @param @param modStockDTO
	 * @param @return
	 * @return Object
	 * @throws
	 * @author lily
	 * @date 2013-12-24 下午07:37:45
	 */
	private ModStock getModStock(ModStockDTO modStockDTO) {
		if (modStockDTO == null) {
			return null;
		}
		ModStock modStock = new ModStock();
		if (modStockDTO.getId() != null) {
			modStock.setId(modStockDTO.getId());
		}
		if (modStockDTO.getOutStype() != null) {
			modStock.setOutStype(modStockDTO.getOutStype());
		}
		if (modStockDTO.getInStype() != null) {
			modStock.setInStype(modStockDTO.getInStype());
		}
		if (modStockDTO.getOutId() != null) {
			modStock.setOutId(modStockDTO.getOutId());
		}
		if (modStockDTO.getInId() != null) {
			modStock.setInId(modStockDTO.getInId());
		}
		if (modStockDTO.getCreateTime() != null) {
			modStock.setCreateTime(modStockDTO.getCreateTime());
		}
		if (modStockDTO.getAuditTime() != null) {
			modStock.setAuditTime(modStockDTO.getAuditTime());
		}
		if (modStockDTO.getCardBin() != null) {
			modStock.setCardBin(modStockDTO.getCardBin());
		}
		if (modStockDTO.getBeginCardNo() != null) {
			modStock.setBeginCardNo(modStockDTO.getBeginCardNo());
		}
		if (modStockDTO.getCardCount() != null) {
			modStock.setCardCount(modStockDTO.getCardCount());
		}
		if (modStockDTO.getProposer() != null) {
			modStock.setProposer(modStockDTO.getProposer());
		}
		if (modStockDTO.getStatus() != null) {
			modStock.setStatus(modStockDTO.getStatus());
		}
		if (modStockDTO.getFlag() != null) {
			modStock.setFlag(modStockDTO.getFlag());
		}
		if (modStockDTO.getVerson() != null) {
			modStock.setVerson(modStockDTO.getVerson());
		}
		return modStock;
	}

}
