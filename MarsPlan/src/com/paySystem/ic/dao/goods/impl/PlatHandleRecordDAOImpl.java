package com.paySystem.ic.dao.goods.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.goods.PlatHandleRecord;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.goods.PlatHandleRecordDAO;
import com.paySystem.ic.web.dto.goods.PlatHandleRecordDTO;

/**
 * 
 * @ProjectName:omall
 * @ClassName:PlatHandleRecordDAOImpl
 * @Description:平台处理商品记录dao实现类
 * @date: 2014-8-26
 * @author:Jacky
 * @version: V1.0
 */
@Repository(PlatHandleRecordDAO.PLATHANDLE)
public class PlatHandleRecordDAOImpl extends DaoSupport<PlatHandleRecord> implements PlatHandleRecordDAO {

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.PlatHandleRecordDAO#save(com.paySystem.ic.web.dto.goods.PlatHandleRecordDTO)
	 *@MethodName:save
	 *@Description:保存
	 *@param platHandleDTO
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:18:16
	 */
	public void save(PlatHandleRecordDTO platHandleDTO) {
		this.save(dto2Bean(platHandleDTO));
	}
	
	/**
	 * 
	 *@Title:findPlatHandleRecordDTO
	 *@Description:保存
	 *@Params:@param goodsId 商品id
	 *@Params:@throws Exception
	 *@Return:void
	 *@author:Jacky
	 *@Date:2014-8-26下午09:03:16
	 */
	public PlatHandleRecordDTO findPlatHandleRecordDTO(Long goodsId)
			throws Exception {
		List<PlatHandleRecord> resultList = em.createQuery("from PlatHandleRecord o where o.goodsId=? order by o.createTime desc limit 1").setParameter(1, goodsId).getResultList();
		if(CollectionUtils.isNotEmpty(resultList)) {
			PlatHandleRecord record = resultList.get(0);
			PlatHandleRecordDTO prd = new PlatHandleRecordDTO();
			BeanUtils.copyProperties(record, prd, new String[]{"phDescr"});
			prd.setPhDescr(new String(record.getPhDescr()));
			return prd;
		}
		return null;
	}



	/**
	 * 
	 *@Title:dto2Bean
	 *@Description:dto转bean
	 *@Params:@param platHandleDTO
	 *@Params:@return
	 *@Return:PlatHandleRecord
	 *@author:Jacky
	 *@Date:2014-8-26下午09:18:29
	 */
	private PlatHandleRecord dto2Bean(PlatHandleRecordDTO platHandleDTO) {
		PlatHandleRecord phr = new PlatHandleRecord();
		if(null != platHandleDTO) {
			BeanUtils.copyProperties(platHandleDTO, phr,new String[]{"goodPunishType","phDescr"});
			phr.setPhDescr(platHandleDTO.getPhDescr().getBytes());
			phr.setCreateTime(new Date());
		}
		return phr;
	}

}
