package com.paySystem.ic.service.base.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.dao.base.MerOrgFunDao;
import com.paySystem.ic.dao.base.OrgansDao;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.web.dto.base.OrgansDTO;
import com.paySystem.ic.web.ui.OptionsString;

/**
 * @ClassName:OrgansServiceImpl
 * @Description:机构信息管理Service实现类
 * @date: 2014-1-20上午09:15:25
 * @author: 谢洪飞
 * @version: V1.0
 */
@Service(OrgansService.ORGANSSERVICE)
public class OrgansServiceImpl implements OrgansService {
	
	@Resource OrgansDao organsDaoImpl;
	@Resource MerOrgFunDao merOrgFunDao;
	
	public boolean validate(String organId) {
		boolean flag = organsDaoImpl.validate(organId);
		return flag;
	}

	/**
	 * 获取机构编号
	 */
	public String getOrganId(){
		String organId = "";
		organId = organsDaoImpl.getOrganId();
		return organId;
	}
	/**
	 * 保存机构信息--新增.
	 * 1.保存机构信息
	 * 2.保存机构商户功能设置信息
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Organs addSaveOrg(OrgansDTO organsDTO) {
		/**保存机构信息 */
		Organs organs = organsDaoImpl.addSaveOrg(organsDTO);
		/**保存机构商户功能设置信息*/
		merOrgFunDao.saveMerOrgFuncSwitch(null, organs, 0);
		return organs;
	}


	/**
	 * 保存机构信息--修改
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void editSaveOrg(Organs organs) {
		organsDaoImpl.editSaverOrg(organs);
	}


	/**
	 * 获取所有发卡机构
	 */
	public List<OptionsString> getOption() {
		List<OptionsString> list = organsDaoImpl.getOption();
		return list;
	}
	
	/**
	 * 获取顶级发卡机构
	 */
	public List<OptionsString> getTopOption() {
		List<OptionsString> list = organsDaoImpl.getTopOption();
		return list;
	}
	
	/**
	 * 获取发卡机构的子机构
	 */
	public List<OptionsString> getOptionByMerId(String merId) {
		List<OptionsString> list = organsDaoImpl.getOptionByMerId(merId);
		return list;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrgan(Organs organs) throws Exception{
		organsDaoImpl.save(organs);
	}

	/**
	 * 获取顶级机构号
	 */
	public String getTopOrganId(String organId) {
		String topOrganId = organsDaoImpl.getTopOrganId(organId);
		return topOrganId;
	}

	public Organs find(String organsId) {
		Organs organ = organsDaoImpl.find(organsId);
		return organ;
	}

	public QueryResult<Organs> queryOrgByCond(int firstPage, int pageNum,
			OrgansDTO organsDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		QueryResult<Organs> queryResult = organsDaoImpl.queryOrgByCond(firstPage, pageNum, organsDTO, orderBy);
		
		return queryResult;
	}

	public Date getSysTime() {
		
		return organsDaoImpl.getSysTime();
	}

	public boolean validateOrgName(String organName,String organId) {
		
		return organsDaoImpl.validateOrgName(organName,organId);
	}


}
