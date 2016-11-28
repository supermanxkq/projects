package com.paySystem.ic.dao.report.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.report.OrganSettTotal;
import com.paySystem.ic.dao.report.OrganSettTotalDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.report.OrganSettTotalDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:OrganSettTotalDAOImpl
 * @Description:机构结算报表查询实现
 * @date: 2014-3-20下午04:30:28
 * @author: 王月
 * @version: V1.0
 */
@Repository(OrganSettTotalDAO.ORGANSETTTOTALDAO)
public class OrganSettTotalDAOImpl extends DaoSupport<OrganSettTotal> implements
		OrganSettTotalDAO {

	/**
	 *@Title:queryResult
	 *@Description:机构结算报表查询
	 *@param:@param fristIndex
	 *@param:@param pageNum
	 *@param:@param organSettTotalDTO
	 *@param:@param orderby
	 *@param:@return
	 *@param:@throws Exception
	 *@author:王月
	 *@thorws: 2014-3-20
	 * 
	 */

	@SuppressWarnings("unchecked")
	public List<OrganSettTotalDTO> queryAll(int page, int pageNum,
			OrganSettTotalDTO organSettTotal,
			LinkedHashMap<String, String> orderBy) {
		List<OrganSettTotalDTO> organSettTotalList = new ArrayList<OrganSettTotalDTO>();
		UserSession us = Utils.getUserSession();
		StringBuilder sb =
			new StringBuilder("select org.organid,org.orgname,org.ownSettle," +
					"org.otherSettle,org.ownOutSettle,org.totalSettAmt,org.status" +
					" from V_ORGSETTREPORTVIEW org");
		sb.append(" where 1=1");
		/**交易报表条件*/
		if (us.getUserLevel() == 1) {/**如果登录为机构则查询机构*/
			sb.append(" and org.organid = " + us.getOrganId());
		}
		if (organSettTotal.getStatus() != -1) {
			sb.append(" and org.Status = " + organSettTotal.getStatus());
		}
		if (StringUtils.isNotBlank(organSettTotal.getOrganId())) {/**机构编号不为空*/
			sb.append(" and org.organid like '%" + organSettTotal.getOrganId()
					+ "%'");
		}
		if (StringUtils.isNotBlank(organSettTotal.getOrgName())) {/**机构名称不为空*/
			sb.append(" and org.orgname like '%" + organSettTotal.getOrgName()
					+ "%'");
		}		
		if (StringUtils.isNotBlank(organSettTotal.getBeginDate())) {
			sb.append(" and org.settletime >=to_timestamp('"
					+ organSettTotal.getBeginDate()
					+ "','yyyy-mm-dd hh24:mi:ss:ff')");
		}
		if (StringUtils.isNotBlank(organSettTotal.getEndDate())) {
			sb.append(" and org.settletime <=to_timestamp('"
					+ organSettTotal.getEndDate()
					+ "','yyyy-mm-dd hh24:mi:ss:ff')");
		}

		/**System.out.println(sb.toString());*/

		List<Object[]> organSettTotal1 = new ArrayList<Object[]>();
		try {
			organSettTotal1 = em.createNativeQuery(
					sb.toString()).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		OrganSettTotalDTO organSettTotal11 = null;
		for (int i = 0; i < organSettTotal1.size(); i++) {
			/**System.out.println("dddd");*/
			organSettTotal11 = new OrganSettTotalDTO();
			Object[] organSettTotalObj = organSettTotal1.get(i);
			organSettTotal11.setOrganId(organSettTotalObj[0].toString());
			organSettTotal11.setOrgName(organSettTotalObj[1].toString());
			organSettTotal11.setOwnSettle((BigDecimal)organSettTotalObj[2]);
			organSettTotal11.setOtherSettle((BigDecimal)organSettTotalObj[3]);
			organSettTotal11.setOwnOutSettle((BigDecimal)organSettTotalObj[4]);
			organSettTotal11.setTotalSettAmt((BigDecimal)organSettTotalObj[5]);
			organSettTotal11.setStatus(Integer.parseInt(organSettTotalObj[6].toString()));
			organSettTotalList.add(organSettTotal11);
		}
		return organSettTotalList;
	}
	
	

	/**
	 *@Title:queryorganSettTotalReport
	 *@Description:导出报表调用
	 *@param:@param organSettTotal
	 *@param:@param orderBy
	 *@param:@return
	 *@author:王月
	 *@thorws: 2014-3-24
	 */
	public List<OrganSettTotalDTO> queryorganSettTotalReport(
			OrganSettTotalDTO organSettTotalDTO,
			LinkedHashMap<String, String> orderBy) {
		// TODO Auto-generated method stub
		return queryAll(-1, -1, organSettTotalDTO, orderBy);
	}

}
