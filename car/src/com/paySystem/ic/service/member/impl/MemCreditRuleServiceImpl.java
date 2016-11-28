package com.paySystem.ic.service.member.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.MemCreditRule;
import com.paySystem.ic.dao.member.MemCreditRuleDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.member.MemCreditRuleService;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemCreditRuleDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ClassName:MemCreditRuleServiceImpl
 * @Description:会员积分规则
 * @date: 2014-7-22上午11:14:48
 * @author: 张亚运
 * @version: V1.0
 */
@Service(MemCreditRuleService.MEMCREDITRULESERVICE)
public class MemCreditRuleServiceImpl extends DaoSupport<MemCreditRule> implements MemCreditRuleService {

    @Resource
    MemCreditRuleDao memCreditDao;

    /**
     *@Description:查询方法
     *@param:@param page
     *@param:@param pageNum
     *@param:@param memCreditDto
     *@param:@param orderby
     *@param:@return
     *@author: 张亚运
     * @throws Exception 
     *@throws:
     */
    public QueryResult<MemCreditRuleDTO> queryAll(int page, int pageNum, MemCreditRuleDTO memCreditDto,
            LinkedHashMap<String, String> orderby) throws Exception {
        return memCreditDao.queryAll(page, pageNum, memCreditDto, orderby);
    }

    /**
     *@Description:添加保存会员规则
     *@param:@param memCreditDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public MemCreditRule saveMemCredit(MemCreditRuleDTO memCreditDto) {
        MemCreditRule memCredit = memCreditDao.saveMemCredit(memCreditDto);
        return memCredit;
    }

    /**
     *@Description:获取会员等级<optionsvalue>
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public List<OptionsInteger> getMemGradeOption() {
        List<OptionsInteger> list = memCreditDao.getGradeOption();
        return list;
    }

    /**
     *@Description:根据积分规则Id查询该条记录
     *@param:@param creditId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public MemCreditRuleDTO findMemCredit(Integer creditId) {
        MemCreditRuleDTO mcrDto = new MemCreditRuleDTO();
        try {
            mcrDto = (MemCreditRuleDTO) EntityDtoConverter.bean2Dto(memCreditDao.find(creditId), mcrDto);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return mcrDto;
    }

    /**
     *@Description:修改保存积分规则
     *@param:@param memCreditDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO updateMemCredit(MemCreditRuleDTO memCreditDto) {
        ReturnDTO returnDto = new ReturnDTO();
        returnDto = memCreditDao.updateMemCredit(memCreditDto);
        return returnDto;
    }

    /**
     *@Description:删除积分规则（修改状态）
     *@param:@param creditId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO deleteMemCredit(Integer creditId) {
        ReturnDTO returnDto = new ReturnDTO();
        MemCreditRule mcr = memCreditDao.find(creditId);
        if (mcr != null) {
            mcr.setStatus(9);
            memCreditDao.update(mcr);
            returnDto.setFlag(true);
        }
        return returnDto;
    }

    /**
     *@Description:检验该会员等级的积分规则是否存在
     *@param:@param gradeId
     *@param:@param creditId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public boolean validateName(Integer gradeId, Integer creditId) {
        return memCreditDao.validateNeme(gradeId, creditId);
    }
}
