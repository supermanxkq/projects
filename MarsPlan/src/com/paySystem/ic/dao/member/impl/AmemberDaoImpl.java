package com.paySystem.ic.dao.member.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.Amember;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.member.AmemberDAO;
import com.paySystem.ic.dao.member.MemberDAO;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.AmemberDTO;

@Component
public class AmemberDaoImpl extends DaoSupport<Amember> implements AmemberDAO {

	/** 创建返回值数据传送对象 */
	ReturnDTO dot = new ReturnDTO();
	@Resource
	MemberDAO memberDaoImpl;


	public Amember query(String memId) throws Exception {
		String sql = "and o.amemId=?1";
		Object[] params = { memId };
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("amemId", "desc");
		QueryResult<Amember> queryResult = getScrollData(0, 50, sql, params,
				orderby);
		List<Amember> list = queryResult.getResultlist();
		Amember amember = null;
		if(list.size()>0){
			amember = new Amember();
			amember = list.get(0);
			return amember;
		}
		//Amember amember = list.get(0);
		//System.out.println(amember.getAmemId());
		return amember;
	}

	/*
	 * <p>Title: saveAmemberDAO</p> <p>Description: 保存AmemberDTO</p>
	 * 
	 * @param AmemberDTO
	 * 
	 * @see
	 * com.paySystem.ic.dao.member.AmemberDAO#saveAmemberDAO(com.paySystem.ic
	 * .web.dto.member.AmemberDTO)
	 * 
	 * @author lily
	 * 
	 * @date 2013-12-24 下午05:51:02
	 */
	public void saveAmemberDAO(AmemberDTO AmemberDTO) {
		this.save(getAmember(AmemberDTO));
	}

	private Amember getAmember(AmemberDTO amemberDTO) {
		if (amemberDTO == null) {
			return null;
		}
		Amember amember = new Amember();
		if (amemberDTO.getAmemId() != null) {
			amember.setAmemId(amemberDTO.getAmemId());
		}
		if (amemberDTO.getMemName() != null) {
			amember.setMemName(amemberDTO.getMemName());
		}
		if (amemberDTO.getBirthday() != null) {
			amember.setBirthday(DateTimeTool.dateFormat("yyyy-MM-dd",amemberDTO.getBirthday()));
		}
		if (amemberDTO.getCareer() != null) {
			amember.setCareer(amemberDTO.getCareer());
		}
		if (amemberDTO.getCultDegree() != null) {
			amember.setCultDegree(amemberDTO.getCultDegree());
		}
		if (amemberDTO.getAddress() != null) {
			amember.setAddress(amemberDTO.getAddress());
		}
		if (amemberDTO.getResidZip() != null) {
			amember.setResidZip(amemberDTO.getResidZip());
		}
		if (amemberDTO.getPwd() != null) {
			amember.setPwd(amemberDTO.getPwd());
		}
		if (amemberDTO.getCertExTime() != null) {
			amember.setCertExTime(amemberDTO.getCertExTime());
		}
		return amember;
	}

}
