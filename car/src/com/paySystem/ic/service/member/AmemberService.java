package com.paySystem.ic.service.member;
import com.paySystem.ic.bean.member.Amember;

public interface AmemberService {
	public static final String AMEMBERSERVICE = "amemberService";

	/***
	 * 获取会员辅助表实体
	 * 
	 * */
	public Amember find(String amemId);
	/**
	 * 根据条件查询会员辅表
	 * @throws Exception 
	 */
	public Amember query(String memId) throws Exception;
}
