package com.paySystem.ic.dao.report.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.report.Trades;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.report.MerConsTotalDao;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.report.TermConsTotalDTO;
import com.paySystem.ic.web.dto.system.UserSession;


/**
 * @ClassName:MerConsTotalDaoImpl
 * @Description:商户汇总查询实现
 * @date: 2014-2-28上午09:05:48
 * @author: 张亚运
 * @version: V1.0
 */


@Repository(MerConsTotalDao.MERCONSTOTALDAO)
public class MerConsTotalDaoImpl extends DaoSupport<Trades> implements
			MerConsTotalDao {

	/**
	 *@Title:queryAll
	 *@Description:商户消费汇总查询
	 *@param:@return
	 *@param:@throws Exception
	 *@return:List<MerConsTotalDTO>
	 *@author:张亚运
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public List<TermConsTotalDTO> queryAll(int page, int pageNum,
			TermConsTotalDTO merConsTotal, LinkedHashMap<String, String> orderBy) {
		
		List<TermConsTotalDTO> merConsTotalList = new ArrayList<TermConsTotalDTO>();
		
		UserSession us = Utils.getUserSession();
		
		StringBuilder sb = new StringBuilder("select mct.merid,mct.mername,sum(mct.tradeamt),sum(mct.sxf),count(mct.merid),mct.tradetype from V_MerConsTotal mct");
				sb.append(" where 1=1");	
				//交易报表条件		
		        if(us.getUserLevel()==1){//如果登录为机构则查询机构下商户
					sb.append(" and mct.organid = "+us.getOrganId());
				}
				if(us.getUserLevel()==2){//如果登录为商户则直接按商户id查询
					sb.append(" and mct.merid = "+us.getMerId());//如果登录为商户则直接按商户id查询
				}
				if(merConsTotal.getTradeType()!=-1){
					sb.append(" and mct.tradetype = "+merConsTotal.getTradeType());
				}
				if(StringUtils.isNotBlank(merConsTotal.getMerId())){//商户编号不为空
					sb.append(" and mct.merid like '%"+merConsTotal.getMerId()+"%'");
				}
				if(StringUtils.isNotBlank(merConsTotal.getMerName())){//商户名称不为空
					sb.append(" and mct.mername like '%"+merConsTotal.getMerName()+"%'");
				}
				if(StringUtils.isNotBlank(merConsTotal.getBeginDate()))
				{
					sb.append(" and to_date(to_char(mct.placedtime,'yyyy-MM-dd'),'yyyy-MM-dd')" +
							" >=to_date('"+merConsTotal.getBeginDate()+"','yyyy-MM-dd')");
				}
				if(StringUtils.isNotBlank(merConsTotal.getEndDate())){
					sb.append(" and to_date(to_char(mct.placedtime,'yyyy-MM-dd'),'yyyy-MM-dd')" +
							" <=to_date('"+merConsTotal.getEndDate()+"','yyyy-MM-dd')");	
				}
				sb.append(" group by (mct.merid,mct.mername,mct.tradetype)");
				
				List<Object []> merConsList = new ArrayList<Object []>();
				try {
					merConsList = em.createNativeQuery(sb
							.toString()).getResultList();
				} catch (Exception e) {
					e.printStackTrace();
				}
				TermConsTotalDTO merCons = null;
				for(int i = 0 ;i<merConsList.size();i++){
					merCons = new TermConsTotalDTO();
					Object[] merConsObj = merConsList.get(i);
					merCons.setMerId((String)merConsObj[0]);
					merCons.setMerName((String)merConsObj[1]);
					merCons.setConsAmt((BigDecimal)merConsObj[2]);
					merCons.setConsCommis((BigDecimal)merConsObj[3]);
					merCons.setTradesNum((BigDecimal)merConsObj[4]);
					merCons.setTradeType(Integer.parseInt((String)merConsObj[5]));
					merConsTotalList.add(merCons);				
				}
		return merConsTotalList;
	}
	

	/**
	 *@Title:queryMerConsTotalReport
	 *@Description:导出报表调用
	 *@param:@param merConsTotalDTO
	 *@param:@param orderBy按xx排序
	 *@author:张亚运
	 *@thorws:
	 */
	public List<TermConsTotalDTO> queryMerConsTotalReport(
			TermConsTotalDTO merConsTotalDTO,
			LinkedHashMap<String, String> orderBy) {
		
		return queryAll(-1,-1,merConsTotalDTO,orderBy);
	}



}
