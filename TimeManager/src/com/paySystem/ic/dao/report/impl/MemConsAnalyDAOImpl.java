package com.paySystem.ic.dao.report.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.report.Trades;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.report.MemConsAnalyDAO;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.report.MemConsAnalyDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:MemConsAnalyDAOImpl
 * @Description:会员消费分析实现类
 * @date: 2014-4-3下午03:05:48
 * @author: 张亚运
 * @version: V1.0
 */
@Repository(MemConsAnalyDAO.MEMCONSANALYDAO)
public class MemConsAnalyDAOImpl extends DaoSupport<Trades> implements
		MemConsAnalyDAO {

	/**
	 *@Title:queryAll
	 *@Description:查询实现方法
	 *@param:@param page
	 *@param:@param pageNum
	 *@param:@param memlistDto
	 *@param:@param orderBy
	 *@param:@return
	 *@param:@throws Exception
	 *@return:List<TermConsTotalDTO>
	 *@author:张亚运
	 */
	@SuppressWarnings("unchecked")
	public List<MemConsAnalyDTO> queryAll(int page, int pageNum,
			MemConsAnalyDTO memlistDto, LinkedHashMap<String, String> orderBy) {
		UserSession us = Utils.getUserSession();
		List<MemConsAnalyDTO> memListDto = new ArrayList<MemConsAnalyDTO>();
		StringBuffer sql = null;
		if(memlistDto.getCountType()==0){
			sql = new StringBuffer("select t.memid,t.memrealname,");
		}
		if(memlistDto.getCountType()==1){
			sql = new StringBuffer("select t.memid,t.memrealname,t.orgname,");
		}
		if(memlistDto.getCountType()==2){
			sql = new StringBuffer("select t.memid,t.memrealname,t.mername,");
		}
		sql.append("sum(t.amt) as amt,count(t.memid) as counts from v_memconsanaly t");
		sql.append(" where t.tradetype = 15 and t.acctypeid = 0");
		if (us.getUserLevel() == 1) {// 如果登录为机构则查询该机构下
			sql.append(" and t.organid = " + us.getOrganId());
		}
		if (us.getUserLevel() == 2) {// 如果登录为商户则查询该商户下
			sql.append(" and t.merid = " + us.getMerId());
		}
		if (StringUtils.isNotBlank(memlistDto.getBeginDate())) {// 开始日期不为空
			sql.append(" and to_date(to_char(t.placedtime,'yyyy-MM-dd'),'yyyy-MM-dd')"
							+ " >=to_date('"
							+ memlistDto.getBeginDate()
							+ "','yyyy-MM-dd')");
		}
		if (StringUtils.isNotBlank(memlistDto.getEndDate())) {//开始日期不为空
			sql.append(" and to_date(to_char(t.placedtime,'yyyy-MM-dd'),'yyyy-MM-dd')"
							+ "<=to_date('"
							+ memlistDto.getEndDate()
							+ "','yyyy-MM-dd')");
		}
		if (memlistDto.getCountType()==0) {
			sql.append(" group by t.memid,t.memrealname");
		}
		if (memlistDto.getCountType()==1) {
			sql.append(" group by t.memid,t.memrealname,t.orgname");
		}
		if (memlistDto.getCountType()==2) {
			sql.append(" group by t.memid,t.memrealname,t.mername");
		}
		if (memlistDto.getRankSign() == 1) {
			sql.append(" order by counts desc");
		} else {
			sql.append(" order by amt desc");
		}
		String sb = ("select * from(" + sql.toString() + ") where rownum <= " + memlistDto
				.getRank());
		System.out.println(sb);
		List<Object[]> objList = new ArrayList<Object[]>();
		try {
			objList = em.createNativeQuery(sb).getResultList();
		} catch (Exception e) {
			e.getMessage();
		}
		MemConsAnalyDTO memDto = null;
		for (int i = 0; i < objList.size(); i++) {
			memDto = new MemConsAnalyDTO();
			Object[] obj = objList.get(i);
			if(memlistDto.getCountType()==0){
				memDto.setMemId(obj[0].toString());
				memDto.setMemName(obj[1].toString());
				memDto.setConsAmt((BigDecimal) obj[2]);
				memDto.setConsCount(Integer.parseInt(obj[3].toString()));
			}
			else{
				memDto.setMemId(obj[0].toString());
				memDto.setMemName(obj[1].toString());
				memDto.setOrgMerName(obj[2].toString());
				memDto.setConsAmt((BigDecimal) obj[3]);
				memDto.setConsCount(Integer.parseInt(obj[4].toString()));
			}			
			memListDto.add(memDto);
		}
		return memListDto;
	}

}
