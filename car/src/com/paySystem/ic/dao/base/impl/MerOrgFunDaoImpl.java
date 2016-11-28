package com.paySystem.ic.dao.base.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.MerOrgFuncSwitch;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.dao.base.MerOrgFunDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.MerOrgFuncSwitchDTO;

@Repository(MerOrgFunDao.MERORGFUNDAO)
public class MerOrgFunDaoImpl extends DaoSupport<MerOrgFuncSwitch> implements MerOrgFunDao {

	public ReturnDTO updateMof(MerOrgFuncSwitchDTO merOrgFuncSwitchDTO) {
		
		ReturnDTO dto = new ReturnDTO();
		MerOrgFuncSwitch merSwitch = this.find(merOrgFuncSwitchDTO.getFmoId());
		if(merSwitch!=null)
		 {
			merSwitch.setOpenAgencySign(merOrgFuncSwitchDTO.getOpenAgencySign());
			merSwitch.setOpenBriGiftSign(merOrgFuncSwitchDTO.getOpenBriGiftSign());
			merSwitch.setOpenBussManSign(merOrgFuncSwitchDTO.getOpenBussManSign());
			merSwitch.setOpenHolGiftSign(merOrgFuncSwitchDTO.getOpenHolGiftSign());
			merSwitch.setOpenMessSign(merOrgFuncSwitchDTO.getOpenMessSign());
			merSwitch.setOpenPreferSign(merOrgFuncSwitchDTO.getOpenPreferSign());
			merSwitch.setOpenPreferTSign(merOrgFuncSwitchDTO.getOpenPreferTSign());
			merSwitch.setEnciVipMess(merOrgFuncSwitchDTO.getEnciVipMess());
			merSwitch.setUpdateTime(getSysTime());
			this.update(merSwitch);
			dto.setFlag(true);
		 }
		return dto;
	}

	public QueryResult<MerOrgFuncSwitch> queryMerOrgSw(int fristindex,
			int pageNum, MerOrgFuncSwitchDTO mofSwitchDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
StringBuilder sql = new StringBuilder(); //封装查询where条件
		
		List<Object> params = new ArrayList<Object>();

		/**判断页面条件*/
		if(StringUtils.isNotBlank(mofSwitchDTO.getOrganId())){
			sql.append(" and ( o.organs.organId like '%"+mofSwitchDTO.getOrganId()+"%' or o.merchants.merId like '%"+mofSwitchDTO.getOrganId()+"%' )");
		}
		QueryResult<MerOrgFuncSwitch> queryResult = getScrollData(fristindex, pageNum, sql.toString(), params.toArray(), orderBy);
		
		return queryResult;
	}

	/**
	 * 保存商户机构功能设置信息
	 */
	public MerOrgFuncSwitch saveMerOrgFuncSwitch(Merchants merchants,Organs organs,int flag) {
		MerOrgFuncSwitch merOrgFSwitch = new MerOrgFuncSwitch();
		String mofId = Utils.createSerialNum(em, "fmoId", "MerOrgFuncSwitch", 15, 0, null, null, null);
		merOrgFSwitch.setEnciVipMess(0);
		merOrgFSwitch.setOpenAgencySign(0);
		merOrgFSwitch.setOpenBriGiftSign(0);
		merOrgFSwitch.setOpenBussManSign(0);
		merOrgFSwitch.setOpenHolGiftSign(0);
		merOrgFSwitch.setOpenMessSign(0);
		merOrgFSwitch.setOpenPreferSign(0);
		merOrgFSwitch.setOpenPreferTSign(0);
		merOrgFSwitch.setCreateTime(new Date());
		merOrgFSwitch.setUpdateTime(new Date());
		merOrgFSwitch.setFmoId(mofId);
		/** 设置机构商户标志 */
		merOrgFSwitch.setOrgMercSign(flag);
		if(flag ==0){//机构
			/** 设置机构编号  */
			merOrgFSwitch.setOrgans(organs);
			
		}
		else{//商户
		    /** 设置商户编号 */
			merOrgFSwitch.setMerchants(merchants);
		}
		
		this.save(merOrgFSwitch);
		return merOrgFSwitch;
	}

}
