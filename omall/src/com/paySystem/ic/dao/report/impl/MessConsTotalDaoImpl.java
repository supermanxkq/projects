package com.paySystem.ic.dao.report.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.report.MessConsTotal;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.report.MessConsTotalDao;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.report.MessConsTotalDTO;
import com.paySystem.ic.web.dto.system.UserSession;


/**
 * @ClassName:MessConsTotalDaoImpl
 * @Description:短信费用汇总查询实现
 * @date: 2014-11-14上午09:05:48
 * @author: 张国法
 * @version: V1.0
 */


@Repository(MessConsTotalDao.MESSCONSTOTALDAO)
public class MessConsTotalDaoImpl extends DaoSupport<MessConsTotal> implements
			MessConsTotalDao {

	/**
	 *@Title:queryAll
	 *@Description:短信费用汇总查询
	 *@param:@return
	 *@param:@throws Exception
	 *@return:List<messConsTotalDTO>
	 *@author:张国法
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public List<MessConsTotalDTO> queryAll(int page, int pageNum,
			MessConsTotalDTO messConsTotalDTO, LinkedHashMap<String, String> orderBy) {
		
		List<MessConsTotalDTO> messConsTotalList = new ArrayList<MessConsTotalDTO>();
		
		UserSession us = Utils.getUserSession();
		
		StringBuilder sb = new StringBuilder("select * from b_messconstotal mct");
				sb.append(" where 1=1");	
					
		        if(us.getUserLevel()==1){//如果登录为机构则查询机构下商户
					sb.append(" and mct.organid = "+us.getOrganId());
				}
				if(us.getUserLevel()==2){//如果登录为商户则直接按商户id查询
					sb.append(" and mct.merid = "+us.getMerId());//如果登录为商户则直接按商户id查询
				}

				if(StringUtils.isNotBlank(messConsTotalDTO.getMerId())){//商户编号不为空
					sb.append(" and mct.merid like '%"+messConsTotalDTO.getMerId()+"%'");
				}
//				if(StringUtils.isNotBlank(messConsTotalDTO.getBeginDate()))
//				{
//					sb.append(" and to_date(to_char(mct.createTime,'yyyy-MM-dd'),'yyyy-MM-dd')" +
//							" >=to_date('"+messConsTotalDTO.getBeginDate()+"','yyyy-MM-dd')");
//				}
//				if(StringUtils.isNotBlank(messConsTotalDTO.getEndDate())){
//					sb.append(" and to_date(to_char(mct.createTime,'yyyy-MM-dd'),'yyyy-MM-dd')" +
//							" <=to_date('"+messConsTotalDTO.getEndDate()+"','yyyy-MM-dd')");	
//				}
				if (StringUtils.isNotBlank(messConsTotalDTO.getBeginDate())) {// 判断起始时间
					sb.append(" and str_to_date(mct.createTime,'%Y-%m-%d') " + ">='"
							+ messConsTotalDTO.getBeginDate() + "'");
				}

				if (StringUtils.isNotBlank(messConsTotalDTO.getEndDate())) {// 检索结束时间
					sb.append(" and str_to_date(mct.createTime,'%Y-%m-%d') " + "<='"
							+ messConsTotalDTO.getEndDate() + "'");
				}
//				sb.append(" group by (mct.merid,mct.mername,mct.tradetype)");
				
				List<Object []> merConsList = new ArrayList<Object []>();
				try {
					merConsList = em.createNativeQuery(sb
							.toString()).getResultList();
				} catch (Exception e) {
					e.printStackTrace();
				}
				MessConsTotalDTO merCons = null;
				for(int i = 0 ;i<merConsList.size();i++){
					merCons = new MessConsTotalDTO();
					Object[] merConsObj = merConsList.get(i);
					merCons.setMerId((String)merConsObj[1]);
					merCons.setMessFee(new BigDecimal(merConsObj[2].toString()));
					merCons.setMessPeriod(Integer.parseInt(merConsObj[3].toString()));
					merCons.setMessType(Integer.parseInt(merConsObj[4].toString()));
					merCons.setCreateTime(merConsObj[5].toString());
					messConsTotalList.add(merCons);				
				}
		return messConsTotalList;
	}

	@Override
	public List<MessConsTotalDTO> queryMessConsTotalReport(
			MessConsTotalDTO messConsTotalDTO,
			LinkedHashMap<String, String> orderBy) {
		// TODO Auto-generated method stub
		return queryAll(-1,-1,messConsTotalDTO,orderBy);
	}

//	@Override
//	public void SaveMessConsTotal(MessConsTotalDTO messConsTotalDTO)
//			throws Exception {
//		MessConsTotal messConsTotal = new MessConsTotal();
//		messConsTotalDTO.setCreateTime(new Date());
//		messConsTotal = (MessConsTotal) EntityDtoConverter.dto2Bean(messConsTotalDTO,
//				messConsTotal);
//		save(messConsTotal);
//		
//	}




}
