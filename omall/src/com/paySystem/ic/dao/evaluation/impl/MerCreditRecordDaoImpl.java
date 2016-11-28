package com.paySystem.ic.dao.evaluation.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.evaluation.CriticalContext;
import com.paySystem.ic.bean.evaluation.MerCreditRecord;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.evaluation.MerCreditRecordDao;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.evaluation.CriticalContextDTO;
import com.paySystem.ic.web.dto.evaluation.MerCreditRecordDTO;
import com.paySystem.ic.web.dto.report.MessConsTotalDTO;
import com.paySystem.ic.web.dto.system.UserSession;


/**  
 * @Title: MerCreditRecordDaoImpl.java
 * @Package: com.paySystem.ic.dao.evaluation.impl
 * @Description: 评论记录dao
 * @Author: yanwuyang 
 * @Date: 2014-10-20 下午6:10:48
 * @Version: V1.0  
 */
@Repository(MerCreditRecordDao.MERCREDITRECORDDAO)
public class MerCreditRecordDaoImpl extends DaoSupport<MerCreditRecord>
		implements MerCreditRecordDao {

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.evaluation.MerCreditRecordDao#save(com.paySystem.ic.web.dto.evaluation.MerCreditRecordDTO)
	 *@MethodName:save
	 *@Description:保存
	 *@param merCreditRecordDTO
	 *@Author:yanwuyang
	 *@Date:2014-10-20下午09:04:06
	 */
	public Integer save(MerCreditRecordDTO merCreditRecordDTO) {
		MerCreditRecord creditRecord = new MerCreditRecord();
		BeanUtils.copyProperties(merCreditRecordDTO, creditRecord);
		this.save(creditRecord);
		return creditRecord.getMcrId();
	}
	

	/**
	 * 
	 *@Title:queryMerCreditRecord
	 *@Description:查询评论记录
	 *@Params:@param merId 会员ID
	 *@Params:@param type 查询类型
	 *@Params:@param appType 评价类型
	 *@Params:@param griType 评论类型
	 *@Params:@return
	 *@Return:List<MerCreditRecordDTO>
	 *@author:yanwuyang
	 *@Date:2014-10-21下午10:25:07
	 */
	public QueryResult<MerCreditRecordDTO> queryMerCreditRecord(String merId,int type,String appType,String griType,int firstPage, int pageNum) {
		QueryResult<MerCreditRecordDTO> qr = new QueryResult<MerCreditRecordDTO>();
		String sql = "select c.mcrId,c.updateTIme,c.appType,c.goodsId";
		if(type==0 || type==2){
			sql+=",(select realName from s_user u where u.username=c.memId) as memId";
		}else{
			sql+=",(select memName from o_orders o where o.orderId=c.orderId ) as memId";
		}
		sql+=",g.goodsName,b.memType,b.context,b.griType,b.griId from B_MerCreditRecord c left join b_goodsinfo g on c.goodsId=g.goodsId left join b_criticalcontext b on  c.mcrId=b.mcrid ";
		if(type==0){
			 sql+= " where c.merId=? and c.flag=1 and b.griReplyId is null";
		}else if(type==1){
			 sql+= " where c.memId=? and c.flag=2 and b.griReplyId is null";
		}else if(type==2){
			sql+= " where c.merId=? and c.flag=1 and b.griReplyId is null and c.orderId in (select m.orderId from o_orders m where m.status=9)";
		}
		if(appType!=null && !appType.equals("")){
			sql+=" and c.appType="+appType;
		}
		if(griType!=null && !griType.equals("")){
			sql+=" and b.griType="+griType;
		}
		sql+=" order by c.createTime desc";
		Query query =this.em.createNativeQuery(sql);
		query.setParameter(1, merId);
		if (firstPage != -1 && pageNum != -1) {
			query.setFirstResult(firstPage).setMaxResults(pageNum);
		}
		
		List<Object[]> os= query.getResultList();
		List<MerCreditRecordDTO> creditRecordDTOs = new ArrayList<MerCreditRecordDTO>();
		MerCreditRecordDTO creditRecordDTO;
		for (int i = 0; i < os.size(); i++) {
			creditRecordDTO  = new MerCreditRecordDTO();
			Object[] o = os.get(i);
			creditRecordDTO.setMcrId((Integer)o[0]);
			creditRecordDTO.setUpdateTIme((String)o[1]);
			creditRecordDTO.setAppType(Integer.parseInt(o[2]+""));
			creditRecordDTO.setGoodsId((Integer)o[3]);
			creditRecordDTO.setMemId((String)o[4]);
			creditRecordDTO.setGoodsName((String)o[5]);
			creditRecordDTO.setMemType(Integer.parseInt((o[6]==null?0:o[6])+""));
			creditRecordDTO.setContext((String)o[7]);
			creditRecordDTO.setGriType(Integer.parseInt((o[8]==null?0:o[8])+""));
			creditRecordDTO.setGriId(Integer.parseInt((o[9]==null?0:o[9])+""));
			
			sql="from CriticalContext where griReplyId=?";
			query =this.em.createQuery(sql);
			query.setParameter(1, o[9]);
			List<CriticalContext> contexts=query.getResultList();
			List<CriticalContextDTO> contextDTOs = new ArrayList<CriticalContextDTO>();
			for (int j = 0; j < contexts.size(); j++) {
				CriticalContextDTO contextDTO = new CriticalContextDTO();
				BeanUtils.copyProperties(contexts.get(j), contextDTO);
				contextDTOs.add(contextDTO);
			}
			creditRecordDTO.setContextDTOs(contextDTOs);
			creditRecordDTOs.add(creditRecordDTO);
		}
	    qr.setResultlist(creditRecordDTOs);
		
	    sql = "select count(*) from B_MerCreditRecord c left join b_goodsinfo g on c.goodsId=g.goodsId left join b_criticalcontext b on  c.mcrId=b.mcrid ";
		if(type==0){
			 sql+= " where c.merId=? and c.flag=1 and b.griReplyId is null";
		}else if(type==1){
			 sql+= " where c.memId=? and c.flag=2 and b.griReplyId is null";
		}else if(type==2){
			sql+= " where c.merId=? and c.flag=1 and b.griReplyId is null and c.orderId in (select m.orderId from o_orders m where m.status=9)";
		}
		if(appType!=null && !appType.equals("")){
			sql+=" and c.appType="+appType;
		}
		if(griType!=null && !griType.equals("")){
			sql+=" and b.griType="+griType;
		}
		query =this.em.createNativeQuery(sql);
		query.setParameter(1, merId);
		qr.setTotalrecord(Long.parseLong(query.getSingleResult()+""));
		return qr;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<MerCreditRecordDTO> queryAll(int firstindex, int maxresult,
			MerCreditRecordDTO merCreditRecordDTO,
			LinkedHashMap<String, String> orderBy)  {
		List<MerCreditRecordDTO> merCreditRecordList = new ArrayList<MerCreditRecordDTO>();
		
		UserSession us = Utils.getUserSession();
		
		StringBuilder sb = new StringBuilder("select * from b_mercreditrecord mct");
				sb.append(" where 1=1");	
					
		        if(us.getUserLevel()==1){//如果登录为机构则查询机构下商户
					sb.append(" and mct.organid = "+us.getOrganId());
				}
				if(us.getUserLevel()==2){//如果登录为商户则直接按商户id查询
					sb.append(" and mct.merid = "+us.getMerId());//如果登录为商户则直接按商户id查询
				}

				if(StringUtils.isNotBlank(merCreditRecordDTO.getMerId())){//商户编号不为空
					sb.append(" and mct.merid like '%"+merCreditRecordDTO.getMerId()+"%'");
				}

				if (StringUtils.isNotBlank(merCreditRecordDTO.getBeginDate())) {// 判断起始时间
					sb.append(" and str_to_date(mct.createTime,'%Y-%m-%d') " + ">='"
							+ merCreditRecordDTO.getBeginDate() + "'");
				}

				if (StringUtils.isNotBlank(merCreditRecordDTO.getEndDate())) {// 检索结束时间
					sb.append(" and str_to_date(mct.createTime,'%Y-%m-%d') " + "<='"
							+ merCreditRecordDTO.getEndDate() + "'");
				}
				
				List<Object []> merConsList = new ArrayList<Object []>();
				try {
					merConsList = em.createNativeQuery(sb.toString()).getResultList();
				} catch (Exception e) {
					e.printStackTrace();
				}
				MerCreditRecordDTO merCons = null;
				for(int i = 0 ;i<merConsList.size();i++){
					merCons = new MerCreditRecordDTO();
					Object[] merConsObj = merConsList.get(i);
					/**评价编号 */
					merCons.setMcrId(Integer.parseInt(merConsObj[0].toString()));
					
					
					/**评价类型(好评/中评/差评)类型 */
					merCons.setAppType(Integer.parseInt(merConsObj[1].toString()));
					merCons.setCreateTIme(merConsObj[2].toString());
					/**好评分数 */
					merCons.setGoodsapprise(Integer.parseInt(merConsObj[5].toString()));
					/**宝贝描述相符 */
					merCons.setGoodsMatch(Integer.parseInt(merConsObj[4].toString()));
					
					merCons.setMemId(merConsObj[10].toString());
					/**商户编号 */
					merCons.setMerId((String)merConsObj[11]);
					merCons.setOrderId(merConsObj[12].toString());
					/** 发货速度*/
					merCons.setSendSpead(Integer.parseInt(merConsObj[7].toString()));
					/** 服务态度*/
					merCons.setServiceAtt(Integer.parseInt(merConsObj[8].toString()));
					
					merCreditRecordList.add(merCons);				
				}
				
//				/**评价类型(好评/中评/差评)类型 */
//				private Integer appType;
//				/**好评分数 */
//				private Integer goodsapprise;
//				/**宝贝描述相符 */
//				private Integer goodsMatch;
//				/** 服务态度*/
//				private Integer serviceAtt;
//				/** 发货速度*/
//				private Integer sendSpead;
//				/**会员ID（评价人ID） */
//				private String memId;
		return merCreditRecordList;
	}
	
	@Override
	public List<MerCreditRecordDTO> queryMessConsTotalReport(
			MerCreditRecordDTO merCreditRecordDTO,
			LinkedHashMap<String, String> orderBy) {
		// TODO Auto-generated method stub
		return queryAll(-1,-1,merCreditRecordDTO,orderBy);
	}
}
