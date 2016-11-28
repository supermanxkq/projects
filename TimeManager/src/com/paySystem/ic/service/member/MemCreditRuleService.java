package com.paySystem.ic.service.member;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.MemCreditRule;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemCreditRuleDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ClassName:MemCreditRuleService
 * @Description:会员积分规则Service
 * @date: 2014-7-22上午11:09:36
 * @author: 张亚运
 * @version: V1.0
 */
public interface MemCreditRuleService extends DAO<MemCreditRule> {

    public static final String MEMCREDITRULESERVICE = "memCreditService";

    /**
     *@Description:查询方法
     *@return:QueryResult<MemCreditRuleDTO>
     *@author: 张亚运
     * @throws Exception 
     *@throws:
     */
    QueryResult<MemCreditRuleDTO> queryAll(int page, int pageNum, MemCreditRuleDTO memCreditDto,
            LinkedHashMap<String, String> orderby) throws Exception;

    /**
     *@Description:添加保存会员积分规则
     *@return:MemCreditRule
     *@author: 张亚运
     *@throws:
     */
    public MemCreditRule saveMemCredit(MemCreditRuleDTO memCreditDto);

    /**
     *@Description:获取会员等级<optionsvalue>
     *@return:List<OptionsInteger>
     *@author: 张亚运
     *@throws:
     */
    public List<OptionsInteger> getMemGradeOption();

    /**
     *@Description:根据Id查询该规则
     *@return:MemCreditRuleDTO
     *@author: 张亚运
     *@throws:
     */
    public MemCreditRuleDTO findMemCredit(Integer creditId);

    /**
     *@Description:修改保存会员积分规则
     *@return:ReturnDTO
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO updateMemCredit(MemCreditRuleDTO memCreditDto);

    /**
     *@Description:删除积分规则（修改状态）
     *@return:ReturnDTO
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO deleteMemCredit(Integer creditId);

    /**
     *@Description:检验该会员等级积分规则是否存在
     *@return:boolean
     *@author: 张亚运
     *@throws:
     */
    public boolean validateName(Integer gradeId, Integer creditId);

}
