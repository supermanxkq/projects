package com.paySystem.ic.dao.member;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.MemCreditRule;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemCreditRuleDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ClassName:MemCreditRuleDao
 * @Description:会员积分规则接口
 * @date: 2014-7-22上午10:59:40
 * @author: 张亚运
 * @version: V1.0
 */
public interface MemCreditRuleDao extends DAO<MemCreditRule> {

    public final static String MEMCREDITRULEDAO = "memCreditDao";

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
     *@Description:获取会员等级<OptionsValue>
     *@return:List<OptionsInteger>
     *@author: 张亚运
     *@throws:
     */
    public List<OptionsInteger> getGradeOption();

    /**
     *@Description:修改保存积分规则
     *@return:ReturnDTO
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO updateMemCredit(MemCreditRuleDTO memCreditDto);

    /**
     *@Description:检验该会员等级的积分规则是否存在
     *@return:boolean
     *@author: 张亚运
     *@throws:
     */
    public boolean validateNeme(Integer gradeId, Integer creditId);

}
