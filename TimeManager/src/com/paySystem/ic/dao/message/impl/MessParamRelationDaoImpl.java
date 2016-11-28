package com.paySystem.ic.dao.message.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.message.MessParamRelation;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.message.MessParamRelationDAO;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.message.MessageDTO;
import com.paySystem.ic.web.dto.system.UserSession;

@Repository(MessParamRelationDAO.MESSPARAMRALATIONDAO)
public class MessParamRelationDaoImpl extends 
		DaoSupport<MessParamRelation> implements MessParamRelationDAO {

	/**
	 *@Title:queryAll
	 *@Description:查询参数使用关系
	 *@param:@param page
	 *@param:@param pageNum
	 *@param:@param messageDto
	 *@param:@param orderBy
	 *@param:@return
	 *@param:@throws Exception
	 *@return:List<MessageDTO>
	 *@author:张亚运
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public List<MessageDTO> queryAll(int page, int pageNum,
			MessageDTO messageDto, LinkedHashMap<String, String> orderBy)
			throws Exception {
		UserSession us = Utils.getUserSession();
		List<MessageDTO> messdtolist = new ArrayList<MessageDTO>();
		StringBuilder sb = new StringBuilder("select mp.mprid,mf.messname,mf.messtype,mf.messperiod,");
		sb.append("mf.messfee,mp.state,mp.begintime,mp.endtime,mp.proposer,mp.updatetime");			
		if(us.getUserLevel()==0){
			sb.append(",o.name as orgname from b_messparamrelation mp" );
			sb.append(" left join b_organs o on mp.orgmerid = o.organid");
		}
		else
		{
			sb.append(",m.mername as mername from b_messparamrelation mp" );
			sb.append(" left join b_merchants m on mp.orgmerid = m.merid");
		}
		sb.append(" left join b_messfeeparam mf on mp.mfpid = mf.mfpid");
		sb.append(" where 1=1");
		
		if(us.getUserLevel()==0){
			sb.append(" and mp.parentorgid = 0");
		}
		else{
			sb.append(" and mp.parentorgid = " + us.getOrganId());
		}		
		if(us.getUserLevel()==0&&StringUtils.isNotBlank(messageDto.getOrgName())){
			sb.append(" and o.name like '%" + messageDto.getOrgName() + "%'");
		}	
		if(us.getUserLevel()==1&&StringUtils.isNotBlank(messageDto.getMerName())){
			sb.append(" and m.mername like '%" + messageDto.getMerName() + "%'");
		}		
		if(messageDto.getMessType()!=-1){
			sb.append(" and mf.messtype = " + messageDto.getMessType());
		}
		if(messageDto.getState()!=-1){
			sb.append(" and mp.state = " + messageDto.getState());
		}
		if(StringUtils.isNotBlank(messageDto.getBeginTime()))
		{
			sb.append(" and to_date(to_char(mp.updatetime,'yyyy-MM-dd'),'yyyy-MM-dd') " +
					">=to_date('"+messageDto.getBeginTime()+"','yyyy-MM-dd')");
		}
		if(StringUtils.isNotBlank(messageDto.getEndTime())){
			sb.append(" and to_date(to_char(mp.updatetime,'yyyy-MM-dd'),'yyyy-MM-dd') " +
					"<=to_date('"+messageDto.getEndTime()+"','yyyy-MM-dd')");
		}
		
		List<Object[]> objlist = new ArrayList<Object[]>();		
		try {
			objlist = em.createNativeQuery(sb.toString()).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		MessageDTO messagedto = null;
		for(int i = 0 ;i<objlist.size();i++){
			messagedto = new MessageDTO();
			Object[] obj = objlist.get(i);
			messagedto.setMprId(obj[0].toString());			
			messagedto.setMessName(obj[1].toString());
			messagedto.setMessType(Integer.parseInt(obj[2].toString()));
			messagedto.setMessPeriod(Integer.parseInt(obj[3].toString()));
			messagedto.setMessFee((BigDecimal)obj[4]);
			messagedto.setState(Integer.parseInt(obj[5].toString()));
			messagedto.setBeginTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", (Date)obj[6]));
			messagedto.setEndTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", (Date)obj[7]));
			messagedto.setProposer(obj[8].toString());
			messagedto.setUpdateTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", (Date)obj[9]));
			messagedto.setOrgMerName(obj[10].toString());
			messdtolist.add(messagedto);				
		}
		return messdtolist;
	}


	/**
	 *@Title:updateMessParamRelation
	 *@Description:保存修改后的数据	 
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:张亚运
	 *@thorws:
	 */

	public ReturnDTO updateMessParamRelation(MessageDTO messagedto) {
		ReturnDTO returnDTO = new ReturnDTO();
		//获得要更新的数据
		MessParamRelation mpr = this.find(messagedto.getMprId());
		UserSession us = Utils.getUserSession();
		if(mpr!=null){
			mpr.setMprId(messagedto.getMprId());
			mpr.setMfpId(messagedto.getMfpId());
			
			if(us.getUserLevel().equals(0)){
				mpr.setParentOrgId("0");
				mpr.setOrgMerId(messagedto.getOrgId());
			}else{
				mpr.setParentOrgId(us.getOrganId());
				mpr.setOrgMerId(messagedto.getMerId());
			}
			mpr.setState(messagedto.getState());
			mpr.setBeginTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",messagedto.getBeginTime()));
			mpr.setEndTime(DateTimeTool.nMonthsAfter(messagedto.getUseLives(), mpr.getBeginTime()));
			mpr.setProposer(us.getUserName());
			if(messagedto.getMfpDesc()!=null){
				mpr.setMprDesc(messagedto.getMfpDesc());
			}					
			mpr.setUpdateTime(this.getSysTime());
			this.update(mpr);
			returnDTO.setFlag(true);
		}
		return returnDTO;
	}

	/**
	 *@Title:createMprID
	 *@Description:生成ID	 
	 *@param:@return
	 *@return:messParamRelationId
	 *@author:张亚运
	 *@thorws:
	 */
	public String createMprID(){
		String messParamRelationId = Utils.createSerialNum(em, "mprId", 
				"MessParamRelation", 8, 0, null, null, null);
		return messParamRelationId;
	}
	
	/**
	 *@Title:saveMessParamRelation
	 *@Description:保存参数使用信息方法	 
	 *@param:@return
	 *@return:
	 *@author:张亚运
	 *@thorws:
	 */
	public MessParamRelation saveMessParamRelation(MessageDTO messagedto) {
		UserSession us = Utils.getUserSession();   		
		String mprId = this.createMprID();
		MessParamRelation mpr = new MessParamRelation();
		
	    /**封装实体*/
		mpr.setMprId(mprId);
		mpr.setMfpId(messagedto.getMfpId());			
		if(us.getUserLevel().equals(0)){
			mpr.setParentOrgId("0");
			mpr.setOrgMerId(messagedto.getOrgId());
		}else{
			mpr.setParentOrgId(us.getOrganId());
			mpr.setOrgMerId(messagedto.getMerId());
		}
		mpr.setState(messagedto.getState());
		mpr.setBeginTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",messagedto.getBeginTime()));
		mpr.setEndTime(DateTimeTool.nMonthsAfter(messagedto.getUseLives(), mpr.getBeginTime()));
		mpr.setProposer(us.getUserName());
		mpr.setMprDesc(messagedto.getMfpDesc());		
		mpr.setUpdateTime(this.getSysTime());
		this.save(mpr);		
		return null;
	}

	/**
	 *@Title:queryById
	 *@Description:根据ID查询参数使用信息	 
	 *@param:@return
	 *@return:MessageDTO
	 *@author:张亚运
	 *@thorws:
	 */
	public MessageDTO queryById(String mprId) {		
		UserSession us = Utils.getUserSession();  
		MessageDTO dto = new MessageDTO();
        StringBuilder sb = new StringBuilder("select mp.mprid,mp.mfpid,mf.messname,mp.orgmerid,mp.state,");
        sb.append("mp.begintime,mp.endtime,mp.mprdesc");			
		if(us.getUserLevel()==0){
			sb.append(",o.name as orgname from b_messparamrelation mp" );
			sb.append(" left join b_organs o on mp.orgmerid = o.organid");
		}
		else
		{
			sb.append(",m.mername as mername from b_messparamrelation mp" );
			sb.append(" left join b_merchants m on mp.orgmerid = m.merid");
		}
		sb.append(" left join b_messfeeparam mf on mp.mfpid = mf.mfpid");
		sb.append(" where 1=1 and mp.mprid=" + mprId);
		Object[] obj = (Object[]) em.createNativeQuery(sb.toString()).getSingleResult();
		if(obj!=null){
			dto.setMprId(obj[0].toString());
			dto.setMfpId(obj[1].toString());
			dto.setMessName(obj[2].toString());
			dto.setOrgId(obj[3].toString());
			dto.setMerId(obj[3].toString());
			dto.setState(Integer.parseInt(obj[4].toString()));
			dto.setBeginTime(obj[5].toString());
			dto.setEndTime(obj[6].toString());
			if(obj[7]!=null){
				dto.setMfpDesc(obj[7].toString());
			}			
			dto.setOrgName(obj[8].toString());
			dto.setMerName(obj[8].toString());
		}
		return dto;
	}

	/**
	 *@Title:queryRelation
	 *@Description:查询关系是否存在
	 *@param:@param orgMerId
	 *@param:@return
	 *@return:boolean
	 *@author:张亚运
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public boolean queryRelation(String orgMerId) {
		StringBuilder sql = new StringBuilder(" select m from MessParamRelation m where m.state = 1"
                +" and m.orgMerId = '" + orgMerId + "'" );
		Query query = em.createQuery(sql.toString());
		List<MessParamRelation> mpr = query.getResultList();
		return mpr.size()<1;		
	}
}
