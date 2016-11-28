package com.paySystem.ic.dao.message.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.message.MessFeeParam;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.message.MessFeeParamDAO;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.message.MessageDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:MessFeeParamDaoImpl
 * @Description:短信参数Dao的实现类
 * @date: 2014-3-19下午06:20:14
 * @author: 张亚运
 * @version: V1.0
 */
@Repository(MessFeeParamDAO.MESSFEEPARAMDAO)
public class MessFeeParamDaoImpl extends DaoSupport<MessFeeParam> 
				implements MessFeeParamDAO {

	/**
	 *@Title:queryAll
	 *@Description:查询短信参数记录
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
		StringBuilder sb = new StringBuilder("select m.mfpid,m.messname,m.messtype,m.messperiod,");
		sb.append("m.messfee,m.usesign,m.proposer,m.updatetime");
		sb.append(" from b_messfeeparam m where 1=1");
		if(us.getUserLevel()==0){
			sb.append(" and m.orgmersign = 0");
		}else{
			sb.append(" and m.orgmersign = 1");
		}
        switch (messageDto.getHelpSign()) {
		case 0:
			if(StringUtils.isNotBlank(messageDto.getMessName())){
				sb.append(" and m.messname like '%" + messageDto.getMessName() + "%'");
			}
			if(messageDto.getMessType()!=-1){
				sb.append(" and m.messtype = " + messageDto.getMessType());
			}
			if(messageDto.getUseSign()!=-1){
				sb.append(" and m.usesign = " + messageDto.getUseSign());
			}
			if(StringUtils.isNotBlank(messageDto.getBeginTime()))
			{
				sb.append(" and to_date(to_char(m.updatetime,'yyyy-MM-dd'),'yyyy-MM-dd') " +
						">=to_date('"+messageDto.getBeginTime()+"','yyyy-MM-dd')");
			}
			if(StringUtils.isNotBlank(messageDto.getEndTime())){
				sb.append(" and to_date(to_char(m.updatetime,'yyyy-MM-dd'),'yyyy-MM-dd') " +
						"<=to_date('"+messageDto.getEndTime()+"','yyyy-MM-dd')");
			}
			break;
		case 1:
			if(StringUtils.isNotBlank(messageDto.getMfpId())){
				sb.append(" and m.mfpid like '%" + messageDto.getMfpId() + "%'");
			}
			if(StringUtils.isNotBlank(messageDto.getMessName())){
				sb.append(" and m.messname like '%" + messageDto.getMessName() + "%'");
			}
			sb.append(" and m.usesign = 1");
			break;
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
			messagedto.setMfpId(obj[0].toString());
			messagedto.setMessName(obj[1].toString());
			messagedto.setMessType(Integer.parseInt(obj[2].toString()));
			messagedto.setMessPeriod(Integer.parseInt(obj[3].toString()));
			messagedto.setMessFee((BigDecimal)obj[4]);
			messagedto.setUseSign(Integer.parseInt(obj[5].toString()));
			messagedto.setProposer(obj[6].toString());
			messagedto.setUpdateTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", (Date)obj[7]));
			messdtolist.add(messagedto);				
		}
		return messdtolist;
	}
	
	/**
	 *@Title:createMfpID
	 *@Description:新建ID
	 *@param:@return
	 *@return:ID
	 *@author:张亚运
	 *@thorws:
	 */
	public String createMfpID(){
		String messFeeParamId = Utils.createSerialNum(em, "mfpId", 
				"MessFeeParam", 8, 0, null, null, null);
		return messFeeParamId;
	}
	
	/**
	 *@Title:saveMessFeeparam
	 *@Description:保存参数信息
	 *@param:@param MessageDTO
	 *@param:@return
	 *@return:null
	 *@author:张亚运
	 *@thorws:
	 */
	public MessFeeParam saveMessFeeParam(MessageDTO messagedto) {
		UserSession us = Utils.getUserSession();   
		String mfpId = this.createMfpID();
		MessFeeParam mfp = new MessFeeParam();
	    /**封装实体 singleRelation*/
		mfp.setMfpId(mfpId);
		mfp.setMessName(messagedto.getMessName());
		mfp.setMessType(messagedto.getMessType());
	    mfp.setMessFee(messagedto.getMessFee());
		mfp.setMessPeriod(messagedto.getMessPeriod());
	    mfp.setUseSign(messagedto.getUseSign());
		if(us.getUserLevel()==0){
		  mfp.setOrgMerSign(0);
		}
		else{
		  mfp.setOrgMerSign(1);
		}
		mfp.setProposer(us.getUserName());
		mfp.setUpdateTime(this.getSysTime());
		mfp.setMfpDesc(messagedto.getMfpDesc());
		this.save(mfp);
		
		return null;
	}

	/**
	 *@Title:updateMessFeeParam
	 *@Description:更新修改后的数据
	 *@param:@param MessageDTO
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:张亚运
	 *@thorws:
	 */
	public ReturnDTO updateMessFeeParam(MessageDTO messagedto) {
		ReturnDTO returnDTO = new ReturnDTO();
		//获得要更新的数据
		MessFeeParam mfp = this.find(messagedto.getMfpId());
		
		UserSession us = Utils.getUserSession();
		if(mfp!=null){
			mfp.setMfpId(messagedto.getMfpId());
			mfp.setMessName(messagedto.getMessName());
		    mfp.setMessType(messagedto.getMessType());
		    mfp.setMessFee(messagedto.getMessFee());
		    mfp.setMessPeriod(messagedto.getMessPeriod());
			mfp.setUseSign(messagedto.getUseSign());
			if(us.getUserLevel()==0){
			  mfp.setOrgMerSign(0);
			}
			else{
			  mfp.setOrgMerSign(1);
			}
			mfp.setProposer(us.getUserName());
			mfp.setUpdateTime(this.getSysTime());
			mfp.setMfpDesc(messagedto.getMfpDesc());
			this.update(mfp);
			returnDTO.setFlag(true);
		}
		return returnDTO;
	}

	/**
	 *@Title:queryMessName
	 *@Description:查询关系是否存在
	 *@param:@param messName
	 *@param:@return
	 *@return:boolean
	 *@author:张亚运
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public boolean queryMessName(String messName) {
		StringBuilder sql = new StringBuilder(" select m from MessFeeParam m " +
				"where m.messName = "+"'"+messName+"'");		
		Query query = em.createQuery(sql.toString());
		List<MessFeeParam> mfp = query.getResultList();
		return mfp.size()<1;	
	}

}
