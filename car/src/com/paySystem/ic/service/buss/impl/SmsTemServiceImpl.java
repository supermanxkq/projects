package com.paySystem.ic.service.buss.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.buss.SmsTem;
import com.paySystem.ic.dao.buss.SmsTemDao;
import com.paySystem.ic.service.buss.SmsTemService;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.buss.SmsTemDTO;

/**
 * @ClassName:SmsTemServiceImpl
 * @Description:TODO
 * @date: 2014-7-10下午02:11:42
 * @author: 张亚运
 * @version: V1.0
 */
@Repository(SmsTemService.SMSTEMSERVICE)
public class SmsTemServiceImpl extends DaoSupport<SmsTem> implements SmsTemService {

    @Resource
    SmsTemDao smsTemDao;

    /**
     *@Description:添加保存短信常用模版
     *@param:@param smsTemDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public SmsTem saveSmsTem(SmsTemDTO smsTemDto) {
        SmsTem st = new SmsTem();
        st = smsTemDao.saveSmsTem(smsTemDto);
        return st;
    }

    /**
     *@Description:修改保存短信常用模版
     *@param:@param smsTemDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO updateSmsTem(SmsTemDTO smsTemDto) {
        ReturnDTO returnDto = new ReturnDTO();
        returnDto = smsTemDao.updateSmsTem(smsTemDto);
        return returnDto;
    }

    /**
     *@Description:根据Id查询该条记录
     *@param:@param sTemId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public SmsTemDTO find(Integer sTemId) {
        SmsTem st = smsTemDao.find(sTemId);
        SmsTemDTO stDto = getDto(st);
        return stDto;
    }

    /**
     *@Description:将实体转换为Dto
     *@return:SmsTemDTO
     *@author: 张亚运
     *@throws:
     */
    public SmsTemDTO getDto(SmsTem st) {
        SmsTemDTO dto = new SmsTemDTO();
        if (st != null) {
            dto.setStemId(st.getStemId());
            dto.setStemName(st.getStemName());
            dto.setStemTitle(st.getStemTitle());
            dto.setStemContent(st.getStemContent());
        }
        return dto;
    }

}
