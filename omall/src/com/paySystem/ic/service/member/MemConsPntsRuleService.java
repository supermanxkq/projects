package com.paySystem.ic.service.member;

import com.paySystem.ic.bean.member.MemConsPntsRule;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemConsPntsRuleDTO;

/**
 * @ProjectName:omall
 * @ClassName:MemConsPntsRuleService
 * @Description:会员消费积分规则Service接口
 * @date: 2014-10-17
 * @author: 王楠
 * @version: V1.0
 */
public interface MemConsPntsRuleService extends DAO<MemConsPntsRule>{

	public static final String MEMCONSPNTSRULESERVICE="memConsPntsRuleService";
	
	/**
	*@Title:addSave
	*@Description:保存会员消费积分规则信息
	*@Params:@param memConsPntsRuleDTO 会员消费积分规则信息DTO
	*@Return:void
	*@author:王楠
	*@Date:2014-10-17下午05:58:39
	*/
	public void addSave(MemConsPntsRuleDTO memConsPntsRuleDTO);
	
	/**
	*@Title:findList
	*@Description:查找数据的方法
	*@Params:@return
	*@Params:@throws Exception
	*@Return:MemConsPntsRuleDTO
	*@author:王楠
	*@Date:2014-10-17下午06:09:18
	*/
	public MemConsPntsRuleDTO findList()throws Exception;
	
	/**
	*@Title:updateMemConsPntsRule
	*@Description:修改会员消费积分规则信息
	*@Params:@param memConsPntsRuleDTO
	*@Params:@return
	*@Return:ReturnDTO
	*@author:王楠
	*@Date:2014-10-20下午04:07:03
	*/
	public ReturnDTO updateMemConsPntsRule(MemConsPntsRuleDTO memConsPntsRuleDTO);
}
