package com.paySystem.ic.dao.member;


import org.springframework.stereotype.Component;
import com.paySystem.ic.bean.member.Amember;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.member.AmemberDTO;

/***
 * 会员辅助实体类
 * 
 * @author 井建国
 *
 */

@Component
public interface AmemberDAO extends DAO<Amember>{
	public static final String MEMBERSERVICE = "memberService";

	/**
	 * 根据条件查询会员辅表
	 * @throws Exception 
	 */
	public Amember query(String memId) throws Exception;
	/**
	* @Title: saveAmemberDAO
	* @Description: 保存AmemerDTO
	* @param @param setAmemberDTO 
	* @return void
	* @throws
	* @author lily
	* @date 2013-12-24 下午05:44:51
	 */
	public void saveAmemberDAO(AmemberDTO AmemberDTO);
	
}
