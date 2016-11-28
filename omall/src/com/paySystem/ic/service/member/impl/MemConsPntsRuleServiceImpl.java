package com.paySystem.ic.service.member.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.member.MemConsPntsRule;
import com.paySystem.ic.dao.member.MemConsPntsRuleDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.member.MemConsPntsRuleService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemConsPntsRuleDTO;

/**
 * @ProjectName:omall
 * @ClassName:MemConsPntsRuleServiceImpl
 * @Description:会员消费积分规则信息的Service接口实现类
 * @date: 2014-10-17
 * @author: 王楠
 * @version: V1.0
 */
@Service(MemConsPntsRuleService.MEMCONSPNTSRULESERVICE)
public class MemConsPntsRuleServiceImpl extends DaoSupport<MemConsPntsRule>
                                             implements MemConsPntsRuleService{
	@Resource
	MemConsPntsRuleDAO memConsPntsRuleDAO;

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.member.MemConsPntsRuleService#addSave(com.paySystem.ic.web.dto.member.MemConsPntsRuleDTO)
	 *@MethodName:addSave
	 *@Description:保存会员消费积分规则信息
	 *@param memConsPntsRuleDTO 会员消费积分规则信息的DTO
	 *@Author:王楠
	 *@Date:2014-10-17下午06:01:09
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSave(MemConsPntsRuleDTO memConsPntsRuleDTO) {
		try {
			memConsPntsRuleDAO.saveMemConsPntsRule(memConsPntsRuleDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.member.MemConsPntsRuleService#findList()
	 *@MethodName:findList
	 *@Description:查询数据的方法
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-10-17下午06:13:10
	 */
	public MemConsPntsRuleDTO findList() throws Exception {
		return memConsPntsRuleDAO.findList();
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.member.MemConsPntsRuleService#updateMemConsPntsRule(com.paySystem.ic.web.dto.member.MemConsPntsRuleDTO)
	 *@MethodName:updateMemConsPntsRule
	 *@Description:修改会员消费积分规则信息
	 *@param memConsPntsRuleDTO
	 *@return
	 *@Author:王楠
	 *@Date:2014-10-20下午04:07:47
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO updateMemConsPntsRule(MemConsPntsRuleDTO memConsPntsRuleDTO) {
        ReturnDTO returnDTO=new ReturnDTO();
        try {
			returnDTO=memConsPntsRuleDAO.updateMemConsPntsRule(memConsPntsRuleDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnDTO;
	}

}
