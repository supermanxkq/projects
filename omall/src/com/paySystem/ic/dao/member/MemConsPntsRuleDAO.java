package com.paySystem.ic.dao.member;

import com.paySystem.ic.bean.member.MemConsPntsRule;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemConsPntsRuleDTO;

/**
 * @ProjectName:omall
 * @ClassName:MemConsPntsRuleDAO
 * @Description:会员消费积分规则DAO接口
 * @date: 2014-10-17
 * @author: 王楠
 * @version: V1.0
 */
public interface MemConsPntsRuleDAO extends DAO<MemConsPntsRule>{

	public static final String MEMCONSPNTSRULEDAO="memConsPntsRuleDAO";
	
	/**
	*@Title:saveMemConsPntsRule
	*@Description:添加会员消费积分规则信息
	*@Params:@param memConsPntsRuleDTO
	*@Params:@throws Exception
	*@Return:void
	*@author:王楠
	*@Date:2014-10-17下午05:06:53
	*/
	public void saveMemConsPntsRule(MemConsPntsRuleDTO memConsPntsRuleDTO)throws Exception;
	
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
	*@Params:@throws Exception
	*@Return:ReturnDTO
	*@author:王楠
	*@Date:2014-10-20下午03:27:32
	*/
	public ReturnDTO updateMemConsPntsRule(MemConsPntsRuleDTO memConsPntsRuleDTO)throws Exception;
}
