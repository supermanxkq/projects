package com.paySystem.ic.dao.report.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.report.ModStockDAO;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.stock.ModStockDTO;
import com.paySystem.ic.web.dto.system.UserSession;

@Repository(ModStockDAO.MODSTOCKDDAO)
public class ModStockDAOImpl extends DaoSupport<ModStock> implements
		ModStockDAO {
	@Resource
	ModStockDAO modStockDAO;

	@SuppressWarnings("unchecked")
	/**
	 * @ClassName:ModStockDAOImpl
	 * @Description:库存变动查询功能
	 * @date: 2014-2-28上午09:33:48
	 * @author: 王楠
	 * @version: V1.0
	 */
	public QueryResult<ModStockDTO> queryAll(int firstindex, int pageNum,
			ModStockDTO modStockDTO, LinkedHashMap<String, String> order)
			throws Exception {
		List<ModStockDTO> modStockList = new ArrayList<ModStockDTO>();
		/** 封装查询where条件 */
		StringBuilder sql = new StringBuilder();
		String strSql="select vms.id,vms.inStype,vms.outStype,vms.cardCount,vms.status,vms.beginCardNo,"
			+ "vms.checkmen,vms.proposer,vms.createTime,vms.auditTime from v_modstock vms where 1=1 ";
//		sql
//				.append("select vms.id,vms.inStype,vms.outStype,vms.cardCount,vms.status,vms.beginCardNo,"
//						+ "vms.checkmen,vms.proposer,vms.createTime,vms.auditTime from v_modstock vms where 1=1 ");
		/** 判断页面条件 */
		UserSession session = Utils.getUserSession();
		/**
		 *设置权限
		 */
		switch (session.getUserLevel()) {
		case 0:
			break;
		case 1:
			sql.append(" and (vms.inorganid = '" + session.getOrganId() + "'"
					+ " or vms.outorganid = '" + session.getOrganId() + "')");
			break;
		case 2:
			sql.append("and (vms.inmerid = '" + session.getMerId() + "'"
					+ " or vms.outmerid = '" + session.getMerId() + "')");
			break;
		}
		if (StringUtils.isNotBlank(modStockDTO.getId())) {
			sql.append(" and vms.id like '%" + modStockDTO.getId() + "%'");
		}
		if (StringUtils.isNotBlank(modStockDTO.getName())) {
			sql.append(" and (vms.inorganame like " + "'%"
					+ modStockDTO.getName() + "%'"
					+ " or  vms.outorganame like " + "'%"
					+ modStockDTO.getName() + "%')");
		}
		if (StringUtils.isNotBlank(modStockDTO.getMerName())) {
			sql.append(" and (vms.inmername like " + "'%"
					+ modStockDTO.getMerName() + "%'"
					+ " or  vms.outmername like " + "'%"
					+ modStockDTO.getMerName() + "%')");
		}
		if (modStockDTO.getStatus() != -1) {
			sql.append(" and vms.status= " + modStockDTO.getStatus());
		}
				
		if(StringUtils.isNotBlank(modStockDTO.getBeginDate()))
		{
			sql.append(" and to_date(to_char(vms.auditTime,'yyyy-MM-dd'),'yyyy-MM-dd')" +
					" >=to_date('"+modStockDTO.getBeginDate()+"','yyyy-MM-dd')");
		}
		if(StringUtils.isNotBlank(modStockDTO.getEndDate())){
			sql.append(" and to_date(to_char(vms.auditTime,'yyyy-MM-dd'),'yyyy-MM-dd')" +
					" <=to_date('"+modStockDTO.getEndDate()+"','yyyy-MM-dd')");	
		}
		System.out.println(strSql+sql.toString());
		Query query=em.createNativeQuery(strSql+
				sql.toString());
		/**分页显示**/
		query.setFirstResult(firstindex);
		query.setMaxResults(pageNum);
		List<Object[]> objList = query.getResultList();
		/** 循环遍历List集合 */
		for (int i = 0; i < objList.size(); i++) {
			ModStockDTO modStock = new ModStockDTO();
			Object[] modStockObj = objList.get(i);
			modStock.setId((String) modStockObj[0]);
			if (modStockObj[1] != null) {
				/** 如果OutStype不为空，将其值放入集合中 */
				modStock.setOutStype(((BigDecimal) modStockObj[1])
						.intValue());
			} else {
				/** 如果为空，将null放入 */
				modStock.setOutStype(null);
			}
			modStock.setInStype(((BigDecimal) modStockObj[2])
					.intValue());
			modStock.setCardCount(((BigDecimal) modStockObj[3])
					.intValue());
			modStock.setStatus(((BigDecimal) modStockObj[4])
					.intValue());
			modStock.setBeginCardNo((String) modStockObj[5]);
			modStock.setProposer((String) modStockObj[6]);
			modStock.setCheckMen((String) modStockObj[7]);
			modStock.setCreateTimeStr(DateTimeTool.dateFormat(
					"yyyy-MM-dd HH:mm:ss", ((Date) modStockObj[8])));
			if (modStockObj[9] != null) {
				modStock.setAuditTimeStr(DateTimeTool.dateFormat(
						"yyyy-MM-dd HH:mm:ss", ((Date) modStockObj[9])));
			} else {
				modStock.setAuditTimeStr("");
			}
			modStockList.add(modStock);
		}
		String sql2 = "select count(*) from v_modstock vms"
			+ (sql == null || "".equals(sql.toString().trim()) ? ""
					: " where 1=1 " + sql.toString());
		System.out.println(sql2);
		Query retQuery = em.createNativeQuery(sql2);
		QueryResult<ModStockDTO> qr=new QueryResult<ModStockDTO>();
		qr.setResultlist(modStockList);
		qr.setTotalrecord(((BigDecimal) retQuery.getSingleResult()).intValue());
		
		return qr;
	}

	/**
	 * @ClassName:ModStockDAOImpl
	 * @Description:库存变动报表导出功能
	 * @date: 2014-2-28上午09:33:48
	 * @author: 王楠
	 * @version: V1.0
	 */
	public List<ModStockDTO> exportModStockXls(ModStockDTO modStockDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return queryAll(-1, -1, modStockDTO, orderBy).getResultlist();
	}
}
