package com.paySystem.ic.dao.member.impl;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.member.MemConsPntsRule;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.member.MemConsPntsRuleDAO;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemConsPntsRuleDTO;

/**
 * @ProjectName:omall
 * @ClassName:MemConsPntsRuleDAOImpl
 * @Description:会员消费积分规则的DAO接口的实现类
 * @date: 2014-10-17
 * @author: 王楠
 * @version: V1.0
 */
@Repository(MemConsPntsRuleDAO.MEMCONSPNTSRULEDAO)
public class MemConsPntsRuleDAOImpl extends DaoSupport<MemConsPntsRule>
                                           implements MemConsPntsRuleDAO{
	
	/**
	*@Title:getMemConsPntsRuleDTO
	*@Description:将实体转换成DTO
	*@Params:@param memConsPntsRule 会员消费积分规则实体
	*@Params:@return
	*@Params:@throws Exception
	*@Return:MemConsPntsRuleDTO
	*@author:王楠
	*@Date:2014-10-17下午05:28:18
	*/
	public MemConsPntsRuleDTO getMemConsPntsRuleDTO(MemConsPntsRule memConsPntsRule)throws Exception{
		MemConsPntsRuleDTO memConsPntsRuleDTO=new MemConsPntsRuleDTO();
		memConsPntsRuleDTO=(MemConsPntsRuleDTO)EntityDtoConverter.bean2Dto(memConsPntsRule, memConsPntsRuleDTO);
		return memConsPntsRuleDTO;
	}
	
	/**
	*@Title:getMemConsPntsRule
	*@Description:将DTO转换成实体
	*@Params:@param memConsPntsRuleDTO
	*@Params:@return
	*@Params:@throws Exception
	*@Return:MemConsPntsRule
	*@author:王楠
	*@Date:2014-10-17下午05:33:12
	*/
	public MemConsPntsRule getMemConsPntsRule(MemConsPntsRuleDTO memConsPntsRuleDTO) throws Exception{
		MemConsPntsRule memConsPntsRule=new MemConsPntsRule();
		memConsPntsRule=(MemConsPntsRule)EntityDtoConverter.dto2Bean(memConsPntsRuleDTO, memConsPntsRule);
		return memConsPntsRule;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.member.MemConsPntsRuleDAO#saveMemConsPntsRule(com.paySystem.ic.web.dto.member.MemConsPntsRuleDTO)
	 *@MethodName:saveMemConsPntsRule
	 *@Description:添加会员消费积分规则信息
	 *@param memConsPntsRuleDTO
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-10-17下午05:26:01
	 */
	public void saveMemConsPntsRule(MemConsPntsRuleDTO memConsPntsRuleDTO)
			throws Exception {
		MemConsPntsRule memConsPntsRule=this.getMemConsPntsRule(memConsPntsRuleDTO);
		this.save(memConsPntsRule);
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.member.MemConsPntsRuleDAO#findList()
	 *@MethodName:findList
	 *@Description:查找数据的方法
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-10-17下午06:10:03
	 */
	public MemConsPntsRuleDTO findList() throws Exception {
		MemConsPntsRuleDTO memConsPntsRuleDTO=new MemConsPntsRuleDTO();
		MemConsPntsRule memConsPntsRule=(MemConsPntsRule)em.createQuery("from MemConsPntsRule ").getResultList().get(0);
		memConsPntsRuleDTO=getMemConsPntsRuleDTO(memConsPntsRule);
		return memConsPntsRuleDTO;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.member.MemConsPntsRuleDAO#updateMemConsPntsRule(com.paySystem.ic.web.dto.member.MemConsPntsRuleDTO)
	 *@MethodName:updateMemConsPntsRule
	 *@Description:修改会员消费积分规则信息
	 *@param memConsPntsRuleDTO 会员消费积分规则实体DTO
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-10-20下午03:38:04
	 */
	public ReturnDTO updateMemConsPntsRule(MemConsPntsRuleDTO memConsPntsRuleDTO)
			throws Exception {
		ReturnDTO returnDTO=new ReturnDTO();
		MemConsPntsRule memConsPntsRule=this.getMemConsPntsRule(memConsPntsRuleDTO);
		this.update(memConsPntsRule);
		returnDTO.setFlag(true);
		return returnDTO;
	}

}
