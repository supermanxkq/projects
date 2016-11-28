package com.paySystem.ic.dao.stock.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.bean.stock.StockInfo;
import com.paySystem.ic.dao.base.MerchantsDao;
import com.paySystem.ic.dao.base.OrgansDao;
import com.paySystem.ic.dao.card.CardBINDAO;
import com.paySystem.ic.dao.stock.HeadQuinDao;
import com.paySystem.ic.dao.stock.StockInfoDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;
import com.paySystem.ic.web.dto.stock.StockInfoDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 *@ClassName:StockInfoDaoImpl
 *@Description:库存信息Dao实现类
 *@Date:2013-12-15下午04:41:49
 *@Author:谢工
 *@Version: V1.0
 */
@Repository(StockInfoDao.STOCKINFODAO)
public class StockInfoDaoImpl extends DaoSupport<StockInfo> implements
		StockInfoDao {
	@Resource
	OrgansDao organsDao;
	@Resource
	MerchantsDao merchantsDao;
	@Resource
	HeadQuinDao modStockDao;
	@Resource
	CardBINDAO cardBinDao;

	/**
	 *@Title:checkExistStockInfo
	 *@Description:根据机构编号/商户编号查看是否已经存在该单位的库存信息
	 *@Param:@param deptId1 -第一个机构/商户号
	 *@Param:@param deptId2 -第二个机构/商户号
	 *@param:@param flag -参数标志： 0-deptId1机构号，deptId2机构号 1-deptId1机构号，deptId2商户号
	 *               2-deptId1商户号，deptId2机构号 2-deptId1商户号，deptId2商户号
	 *@Param:@return
	 *@Return:boolean
	 *@author 谢
	 *@Throws:
	 */
	public boolean checkExistStockInfo(String deptId1, String deptId2,
			String cardbinNo, int flag) {
		boolean existFlag = false;
		StringBuilder jpql = new StringBuilder(
				" select o from StockInfo o where 1 = 1");
		String organId = deptId1 == null || deptId1 == "" ? "" : deptId1;
		String merId = deptId2 == null || deptId2 == "" ? "" : deptId2;
		String binNo = cardbinNo == null ? "" : cardbinNo;
		if (flag == 0) {// 非常规，总部入库:第二个是机构 第一个为卡厂

		} else {

		}
		if (organId != "") {
			jpql.append(" and o.organs.organId=?1");
		}
		if (merId != "") {
			jpql.append(" and o.merchants.merId=?1");
		}
		if (binNo != "") {
			jpql.append(" and o.cardBin.binId=?2");
		}
		Query query = em.createQuery(jpql.toString());
		if (organId != "") {
			query.setParameter(1, organId);
		}
		if (merId != "") {
			query.setParameter(1, merId);
		}
		if (binNo != "") {
			query.setParameter(2, binNo);
		}
		List<StockInfo> stockInfoList = query.getResultList();
		if (stockInfoList.size() < 1) {
			existFlag = true;
		}

		return existFlag;
	}

	/**
	 *@Title:saveStockInfo
	 *@Description:新增库存信息
	 *@Param:@param modStockDTO
	 *@param:@param flag 0-机构，1-商户
	 *@param:@param outinSign 0-出库，1-入库
	 *@Param:@return
	 *@Return:ReturnDTO
	 *@Throws:
	 */
	public ReturnDTO saveStockInfo(ModStock modStock) throws Exception {
		ReturnDTO dto = new ReturnDTO();
		StockInfo stockInfo = null;
		StockInfo outStockInfo = null;
		StockInfo inStockInfo = null;
		// 获得出库方机构/商户的库存信息
		if (modStock.getOutStype() == 0) {
			// 出库方类型为机构
			if (modStock.getOutId() != "99999999"
					&& !"99999999".equals(modStock.getOutId())) {// 并且出库方不为卡厂99999999
				outStockInfo = queryStockInfoByOrgMer(modStock.getOutId(),
						modStock.getCardBin().getBinId(), 0);
			}
		} else {
			outStockInfo = queryStockInfoByOrgMer(modStock.getOutId(), modStock
					.getCardBin().getBinId(), 1);
		}
		// 获得入库方机构/商户的库存信息
		if (modStock.getInStype() == 0) {
			// 如果入库方类型为机构
			inStockInfo = queryStockInfoByOrgMer(modStock.getInId(), modStock
					.getCardBin().getBinId(), 0);
		} else {
			inStockInfo = queryStockInfoByOrgMer(modStock.getInId(), modStock
					.getCardBin().getBinId(), 1);
		}
		// 如果出库方的库存信息不为空，则更新库存
		// 出库方更新库存： 出库总数 = 出库总数 - 本次库存变动数量
		if (outStockInfo != null) {
			outStockInfo.setOutTotal(outStockInfo.getOutTotal()
					+ modStock.getCardCount());
			this.update(outStockInfo);
		}
		// 如果入库方库存信息不为空，则更新库存
		// 入库方更新库存： 入库总数 = 入库总数 + 本次库存变动数量
		if (inStockInfo!= null&&inStockInfo.getId()!=null) {
			inStockInfo.setInTotal(inStockInfo.getInTotal()
					+ modStock.getCardCount());
			this.update(inStockInfo);
		}
		// 如果入库方库存信息不存在，则保存库存信息
		// 入库方保存库存： 入库总数 = 本次库存变动数量
		else {
			inStockInfo = new StockInfo();
			inStockInfo.setInTotal(modStock.getCardCount());
			inStockInfo.setCardBin(modStock.getCardBin());

			if (modStock.getInStype() == 0) {

				inStockInfo.setOrgans(organsDao.find(modStock.getInId()));
			} else {

				inStockInfo.setMerchants(merchantsDao.find(modStock.getInId()));
			}

			try {
				this.save(inStockInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		dto.setObj(stockInfo);
		dto.setFlag(true);

		return dto;
	}

	/**
	 *@Title:queryStockInfoByOrgMer
	 *@Description:获取机构/商户的库存信息
	 *@param:@param id
	 *@param:@param flag
	 *@param:@return
	 *@return:StockInfo
	 *@author:
	 *@Thorws:
	 */
	public StockInfo queryStockInfoByOrgMer(String id, String binId, int flag) {
		StockInfo stockInfo = null;
		StringBuilder sql = new StringBuilder(
				" select o from StockInfo o where 1=1 ");
		if (flag == 0) {
			// 如果是机构的库存信息
			sql.append(" and o.organs.organId = '" + id + "'");
		} else {
			sql.append(" and o.merchants.merId = '" + id + "'");
		}
		sql.append(" and o.cardBin.binId='" + binId + "'");
		List<StockInfo> stockList = em.createQuery(sql.toString())
				.getResultList();
		if (stockList.size() > 0) {
			stockInfo = new StockInfo();
			stockInfo = stockList.get(0);
		}
		return stockInfo;

	}

	/*
	 * <p>Title: updateStockByBinForSingleSale</p> <p>Description:
	 * 为单张收卡功能提供修改库存信息的方法实现</p>
	 * 
	 * @param substring
	 * 
	 * @see
	 * com.paySystem.ic.dao.stock.StockInfoDao#updateStockByBinForSingleSale
	 * (java.lang.String)
	 * 
	 * @author lily
	 * 
	 * @date 2013-12-24 下午06:23:57
	 */
	public void updateStockByBinForSingleSale(String bin) {
		UserSession us = Utils.getUserSession();
		StockInfoDTO stockInfoDTO = findByBin(bin);
		if (stockInfoDTO.getOutTotal() != null) {
			stockInfoDTO.setOutTotal(stockInfoDTO.getOutTotal() + 1);
		} else {
			stockInfoDTO.setOutTotal(1);
		}
		if (stockInfoDTO.getExistsTotal() != null) {
			stockInfoDTO.setExistsTotal(stockInfoDTO.getExistsTotal() - 1);
		}
		stockInfoDTO.setUpdateTime(new Date());
		this.updateStockInfoDTO(stockInfoDTO);
	}

	/*
	 * <p>Title: updateStockInfoDTO</p> <p>Description:
	 * 通过StockInfoDTO保存StockInfo实体的方法实现</p>
	 * 
	 * @param stockInfoDTO
	 * 
	 * @see
	 * com.paySystem.ic.dao.stock.StockInfoDao#updateStockInfoDTO(com.paySystem
	 * .ic.web.dto.stock.StockInfoDTO)
	 * 
	 * @author lily
	 * 
	 * @date 2013-12-24 下午07:00:41
	 */
	public void updateStockInfoDTO(StockInfoDTO stockInfoDTO) {
		this.update(getStockInfoDTO(stockInfoDTO));
	}

	/**
	 * @Title: getStockInfoDTO
	 * @Description: 将StockInfoDTO封装为StockInfo的私有方法
	 * @param @param stockInfoDTO
	 * @param @return
	 * @return StockInfo
	 * @throws
	 * @author lily
	 * @date 2013-12-24 下午07:07:57
	 */
	private StockInfo getStockInfoDTO(StockInfoDTO stockInfoDTO) {
		if (stockInfoDTO == null) {
			return null;
		}
		StockInfo stockInfo = new StockInfo();

		if (stockInfoDTO.getId() != null) {
			stockInfo.setId(stockInfoDTO.getId());
		}

		if (stockInfoDTO.getOrgans() != null) {
			stockInfo.setOrgans(stockInfoDTO.getOrgans());
		}

		if (stockInfoDTO.getMerchants() != null) {
			stockInfo.setMerchants(stockInfoDTO.getMerchants());
		}

		if (stockInfoDTO.getInTotal() != null) {
			stockInfo.setInTotal(stockInfoDTO.getInTotal());
		}

		if (stockInfoDTO.getOutTotal() != null) {
			stockInfo.setOutTotal(stockInfoDTO.getOutTotal());
		}

		if (stockInfoDTO.getExistsTotal() != null) {
			stockInfo.setExistsTotal(stockInfoDTO.getExistsTotal());
		}

		if (stockInfoDTO.getCardBin() != null) {
			stockInfo.setCardBin(stockInfoDTO.getCardBin());
		}

		if (stockInfoDTO.getUpdateTime() != null) {
			stockInfo.setUpdateTime(stockInfoDTO.getUpdateTime());
		}

		return stockInfo;
	}

	/***
	 * <p>Title: findByBin</p> <p>Description: 根据卡BIN查询库存信息的方法实现</p>
	 * 
	 * @param bin
	 * 
	 * @return
	 * 
	 * @see com.paySystem.ic.dao.stock.StockInfoDao#findByBin(java.lang.String)
	 * 
	 * @author lily
	 * 
	 * @date 2013-12-24 下午06:25:42
	 */
	// @Override
	public StockInfoDTO findByBin(String bin) {
		UserSession us = Utils.getUserSession();
		String hql = "select o from StockInfo o where o.cardBin.binId = ? and o.organs.organId = ?";
		Query query = em.createQuery(hql);
		List<Object> params = new ArrayList<Object>();
		params.add(bin);
		params.add(us.getOrganId());
		// query.setParameter(1, bin);
		// query.setParameter(1, orgId);
		setQueryParams(query, params.toArray());
		List<StockInfo> list = query.getResultList();
		return getStockInfoDTO(list.get(0));
	}

	/**
	 * @Title: getStockInfoDTO
	 * @Description: 将StockInfo封装为StockInfoDTO的私有方法
	 * @param @param stockInfo
	 * @param @return
	 * @return StockInfoDTO
	 * @throws
	 * @author lily
	 * @date 2013-12-24 下午06:39:50
	 */
	private StockInfoDTO getStockInfoDTO(StockInfo stockInfo) {
		if (stockInfo == null) {
			return null;
		}
		StockInfoDTO stockInfoDTO = new StockInfoDTO();
		if (stockInfo.getId() != null) {
			stockInfoDTO.setId(stockInfo.getId());
		}
		if (stockInfo.getOrgans() != null) {
			stockInfoDTO.setOrgans(stockInfo.getOrgans());
		}
		if (stockInfo.getMerchants() != null) {
			stockInfoDTO.setMerchants(stockInfo.getMerchants());
			// stockInfoDTO.setMerName(stockInfo.getMerchants().getMerName());
		}
		if (stockInfo.getInTotal() != null) {
			stockInfoDTO.setInTotal(stockInfo.getInTotal());
		}
		if (stockInfo.getOutTotal() != null) {
			stockInfoDTO.setOutTotal(stockInfo.getOutTotal());
		}
		if (stockInfo.getExistsTotal() != null) {
			stockInfoDTO.setExistsTotal(stockInfo.getExistsTotal());
		}
		if (stockInfo.getCardBin() != null) {
			stockInfoDTO.setCardBin(stockInfo.getCardBin());
		}
		if (stockInfo.getUpdateTime() != null) {
			stockInfoDTO.setUpdateTime(stockInfo.getUpdateTime());
		}
		return stockInfoDTO;
	}

	/**
	 * 
	 *@Title:findByCardBin
	 *@TODO:通过卡bin查找实体
	 *@param:@param subSequence
	 *@param:@return
	 *@author:孟凡岭
	 *@Thorws:
	 */
	public StockInfo findByCardBin(String cardBin) {
		String sql="from StockInfo s where s.cardBin.binId='"+cardBin+"'";
		
		return (StockInfo) em.createQuery(sql).getResultList().get(0);
	}

	/**
	 *@Title:merStockSureIn
	 *@Description:商户领卡：确认领卡--库存信息维护
	 *@param:@param modStockDTO 库存变动DTO
	 *@return: void
	 *@author:谢洪飞
	 */
	public void merStockSureIn(ModStockDTO modStockDTO) {
		
		UserSession us = Utils.getUserSession();
		//入库方库存信息
		StockInfo inStockInfo =
			this.queryStockInfoByOrgMer(modStockDTO.getInId(), modStockDTO.getCardBinNo(), 1);
		ModStock modStock = modStockDao.find(modStockDTO.getId());
		if(inStockInfo==null)
		{//如果库存信息中不存在该商户该卡BIN库存信息；
			inStockInfo = new StockInfo();
			inStockInfo.setCardBin(modStock.getCardBin());
			inStockInfo.setInTotal(modStock.getCardCount());
			inStockInfo.setMerchants(new Merchants(us.getMerId()));
			inStockInfo.setUpdateTime(getSysTime());
			this.save(inStockInfo);
		}
		else
		{
			inStockInfo.setInTotal(modStock.getCardCount());
			inStockInfo.setUpdateTime(getSysTime());
			this.update(inStockInfo);
		}
		
		//出库方库存信息
		StockInfo outStockInfo =
			this.queryStockInfoByOrgMer(modStock.getOutId(), modStock.getCardBin().getBinId(), 0);
		
		outStockInfo.setOutTotal(modStock.getCardCount());
		this.update(outStockInfo);
		
		
	}

	/**
	 *@Title:
	 *       queryStockByCond
	 *@Description:
	 *       根据条件获取库存信息
	 *@param:
	 *       @param firstindex 起始记录数
	 *@param:
	 *       @param maxresult 每页最大条数
	 *@param:
	 *       @param stockInfoDTO 库存信息DTO
	 *@param:
	 *       @param orderby 排序
	 *@param:
	 *       @return 
	 *@param:
	 *       @throws Exception
	 *@return:
	 *       QueryResult<StockInfo> 查询结果
	 *@author:
	 *       谢洪飞
	 */
	public QueryResult<StockInfo> queryStockByCond(int firstindex,
			int maxresult, StockInfoDTO stockInfoDTO,
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
			sql.append(" and o.organs.organId ='" + us.getOrganId()+"'");
			break;
		case 2:
			sql.append(" and o.merchants.merId ='"+us.getMerId()+"'");
			break;
		}
		
		if(StringUtils.isNotBlank(stockInfoDTO.getMerId()))
		{
			sql.append(" and o.merchants.merId like ?").append(params.size()+1);
			params.add("%"+stockInfoDTO.getMerId()+"%");
		}
		if(StringUtils.isNotBlank(stockInfoDTO.getMerName())){
			sql.append(" and o.merchants.merName like ?").append(params.size()+1);
			params.add("%"+stockInfoDTO.getMerName()+"%");
		}
		
		QueryResult<StockInfo> queryResult = getScrollData(firstindex,
				maxresult, sql.toString(), params.toArray(), orderby);
		
		return queryResult;
	}

	/**
	 *@Title:queryStockInfoById
	 *@Description:根据Id获取StockInfoDTO信息
	 *@param:@param stockInfoId
	 *@param:@return
	 *@return:StockInfoDTO
	 *@author:谢洪飞
	 *@Thorws:
	 */
	public StockInfoDTO queryStockInfoById(Integer stockInfoId) {
		
		StockInfo stockInfo = this.find(stockInfoId);
		
		StockInfoDTO stockInfoDto = this.convertDTO(stockInfo);
		
		return stockInfoDto;
	}
	
	
	/**
	 *@Title:convertDTO
	 *@Description:转换Bean到DTO
	 *@param:@param stockInfo
	 *@param:@return
	 *@return:StockInfoDTO
	 *@author:谢洪飞
	 *@Thorws:
	 */
	private StockInfoDTO convertDTO(StockInfo stockInfo){
		StockInfoDTO stockInfoDTO =null;
		
		if(stockInfo!=null){
			stockInfoDTO = new StockInfoDTO();
			stockInfoDTO.setId(stockInfo.getId());
			
			Integer stockCount =
				  (stockInfo.getInTotal()==null?0:stockInfo.getInTotal())
			    - (stockInfo.getOutTotal()==null?0:stockInfo.getOutTotal());
			
			stockInfoDTO.setExistsTotal(stockCount);
			stockInfoDTO.setCardBinName(stockInfo.getCardBin().getBinName());
			stockInfoDTO.setInTotal(stockInfo.getInTotal());
			stockInfoDTO.setOutTotal(stockInfo.getOutTotal()==null?0:stockInfo.getOutTotal());
			stockInfoDTO.setUpdateTime(stockInfo.getUpdateTime());
			
			stockInfoDTO.setMerName(stockInfo.getMerchants()==null
					                     ?stockInfo.getOrgans().getName():stockInfo.getMerchants().getMerName());
			
			stockInfoDTO.setDwxz(stockInfo.getMerchants()==null?"机构":"商户");
		}
		
		return stockInfoDTO;
	}
	
	
}
