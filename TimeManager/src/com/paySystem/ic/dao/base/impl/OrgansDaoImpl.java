package com.paySystem.ic.dao.base.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.dao.base.MerchantsDao;
import com.paySystem.ic.dao.base.OrgansDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.base.OrgansDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsString;

@Repository(OrgansDao.ORGANSDAO)
public class OrgansDaoImpl extends DaoSupport<Organs> implements OrgansDao {
	
	@Resource MerchantsDao merchantsDao;
	@SuppressWarnings("unchecked")
	public List<OptionsString> getOption() {
		List<Organs> organsList = em.createQuery("from Organs o where o.status = 1 and o.organId<>'99999999' order by organId asc").getResultList();
		List<OptionsString> list = new ArrayList<OptionsString>();
		for (Organs organs : organsList) {
			list.add(new OptionsString(organs.getOrganId(), organs.getName()));
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<OptionsString> getOptionByMerId(String merId) {
		List<OptionsString> list = new ArrayList<OptionsString>();
		String topOrganId = "";
		Merchants merchants = merchantsDao.find(merId);
		Organs organs = merchants.getOrgans();
		if(organs.getParentId().equals("0")){
			topOrganId = organs.getOrganId();
		}else{
			topOrganId = organs.getParentId();
		}
		List<Organs> organsList = em.createQuery("from Organs o where o.status = 1 and (o.parentId = '"+topOrganId+"' or o.organId = '"+topOrganId+"') order by o.organId asc").getResultList();
		for (Organs organ : organsList) {
			list.add(new OptionsString(organ.getOrganId(), organ.getName()));
		}
		
		return list;
	}

	
	public String getOrganId() {
		String organId = "";
		List<String> string = em.createQuery("select o.organId from Organs o where o.organId <> '99999999' order by o.createTime desc").setMaxResults(1).getResultList();
		if(string==null||string.isEmpty()){
			organId = "1";
		}else{
			organId = string.get(0);
			Integer neworganId = Integer.valueOf(organId)+1;
			organId = neworganId.toString();
		}
		
		while(organId.length()<8){
			organId = "0" + organId;
		}
		
		while(this.validate(organId)){
			Integer neworganId = Integer.valueOf(organId)+1;
			organId = neworganId.toString();
			while(organId.length()<8){
				organId = "0" + organId;
			}
		}
		return organId;
	}

	@SuppressWarnings("unchecked")
	public List<OptionsString> getTopOption() {
		List<Organs> organsList = em.createQuery("from Organs o where o.status = 1 and o.parentId = '0' and o.organId<>'99999999' order by o.organId asc").getResultList();
		List<OptionsString> list = new ArrayList<OptionsString>();
		for (Organs organs : organsList) {
			list.add(new OptionsString(organs.getOrganId(), organs.getName()));
		}
		return list;
	}

	public String getTopOrganId(String organId) {
		Organs organs = this.find(organId);
		if(organs!=null){
			if(organs.getParentId().equals("0")){
				return organId;
			}else{
				return organs.getParentId();
			}
		}else{
			return null;
		}
	}


	public void saveOrgan(Organs organs) throws Exception {
		
		this.save(organs);
	}


	public boolean validate(String organId) {
		long count = (Long) em.createQuery("select count(o) from Organs o where o.organId=?1").setParameter(1, organId).getSingleResult();
		boolean flag =  count > 0;
		return flag;
	}


	public QueryResult<Organs> queryOrgByCond(int firstIndex, int pageNum,
			OrgansDTO organsDTO, LinkedHashMap<String, String> orderBy) throws Exception {
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
		case 1:
			sb.append(" and o.organId = '"+us.getOrganId()+"'");
			break;
		case 2:
			sb.append(" and o.organId = '"+us.getOrganId()+"'");
			break;
		}
		sb = queryOutKC(sb);

			//帮助标志，如果是"1" 则是查询帮助列表。否则，条件查询
			if("1".equals(organsDTO.getHelpSign())||organsDTO.getHelpSign()=="1"){
				sb.append(" and o.status = 1 ");
			}

		
		
		/** 判断过滤条件，如果无过滤条查询全部数据 */
		if (StringUtils.isNotBlank(organsDTO.getOrganId())) {
			sb.append(" and o.organId like ?").append(params.size() + 1);
			params.add("%" + organsDTO.getOrganId().trim() + "%");
		}
		if (StringUtils.isNotBlank(organsDTO.getName())) {
			sb.append(" and o.name like ?").append(params.size() + 1);
			params.add("%" + organsDTO.getName().trim() + "%");
		}
		QueryResult<Organs> queryResult = this.getScrollData(firstIndex, pageNum, sb.toString(), params.toArray(), orderBy);
		return queryResult;
	}


	public Organs addSaveOrg(OrgansDTO organsDTO) {
		Organs organs = new Organs();
		organs.setOrganId(Utils.getString(organsDTO.getOrganId()));
		organs.setName(Utils.getString(organsDTO.getName()));
		organs.setParentId(organsDTO.getParentId());
		organs.setYearServSign(organsDTO.getYearServSign());
		organs.setTrustFundSign(organsDTO.getTrustFundSign());
		organs.setTeleNo(Utils.getString(organsDTO.getTeleNo()));
		organs.setOrgDesc(Utils.getString(organsDTO.getOrgDesc()));
		organs.setConPerName(Utils.getString(organsDTO.getConPerName()));
		organs.setConPerTeleNo(Utils.getString(organsDTO.getConPerTeleNo()));
		organs.setAreaId(organsDTO.getAreaId());
		organs.setAddress(Utils.getString(organsDTO.getAddress()));
		organs.setZip(Utils.getString(organsDTO.getZip()));
		organs.setBankName(Utils.getString(organsDTO.getBankName()));
		organs.setBankAcctNo(Utils.getString(organsDTO.getBankAcctNo()));
		organs.setBankUser(Utils.getString(organsDTO.getBankUser()));
		organs.setStatus(organsDTO.getStatus());
		organs.setSysType(0);
		organs.setParentId(Utils.getString(organsDTO.getParentId()));
		organs.setSettPeriod(organsDTO.getSettPeriod());
		organs.setLastSettTime(new Date());
		organs.setSaleMRate(organsDTO.getSaleMRate());
		/*organs.setNetPayRetRate(Utils.strToBigDecimal(organsDTO.getNetPayRetRate()));*/
		/*organs.setSysType(organsDTO.getSysType());*/
		organs.setEmContPhone(organsDTO.getEmContPhone());
		organs.setUpdateTime(new Date());
		organs.setCreateTime(new Date());
		this.save(organs);
		return organs;
	}

	/**
	 * 保存机构信息--修改
	 */
	public void editSaverOrg(Organs organs) {
		this.update(organs);
	}

	protected StringBuilder queryOutKC(StringBuilder jpql) {
		jpql.append(" and o.organId<>'99999999' ");
		return jpql;
	}
	/**
	 *@Title:validateOrgName
	 *@Description:检查数据库中是否已经存在该机构名称
	 *@Param:@param orgName
	 *@Param:@return
	 *@Return:boolean
	 *@Throws:谢
	 */

	public boolean validateOrgName(String orgName,String organId) {
		StringBuilder sb = new StringBuilder(" from Organs o where o.name = '"+orgName+"' and organId<>'"+organId+"'");
		List<Organs> orgList = em.createQuery(sb.toString()).getResultList();
		return orgList.size()>0;
	}

	/**
	 * 
	 *@Title:queryByName
	 *@Description:根据名称查询机构集合
	 *@param:@param name
	 *@param:@return
	 *@return:OrgansDaoImpl
	 *@author:井建国
	 *@thorws:
	 *@father:@see com.paySystem.ic.dao.base.OrgansDao#queryByName(java.lang.String)
	 */
	public List<Organs> queryByName(String name) {
		String sql = "select o from Organs o where o.name like '%"+ name + "% '";
		Query query = em.createQuery(sql);
		List<Organs> list = query.getResultList();
		return list;
	}
}
