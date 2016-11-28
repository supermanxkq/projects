package com.paySystem.ic.service.buss.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.buss.MailServParam;
import com.paySystem.ic.dao.buss.MailServParamDao;
import com.paySystem.ic.service.buss.MailServParamService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.buss.MailServParamDTO;

/**
 * @ClassName:MailServParamServiceImpl
 * @Description:邮件服务器设置Service实现
 * @date: 2014-7-4下午04:34:47
 * @author: 张亚运
 * @version: V1.0
 */
@Service(MailServParamService.MAILSERVPARAMSERVICE)
public class MailServParamServiceImpl implements MailServParamService {

    @Resource
    MailServParamDao mailServParamDao;

    /**
     *@Description:查询方法
     *@param:@param page
     *@param:@param pageNum
     *@param:@param mailServDto
     *@param:@param orderBy
     *@param:@return
     *@param:@throws Exception
     *@author: 张亚运
     *@throws:
     */
    public QueryResult<MailServParamDTO> queryAll(int page, int pageNum, MailServParamDTO mailServDto,
            LinkedHashMap<String, String> orderBy) throws Exception {
        return mailServParamDao.queryAll(page, pageNum, mailServDto, orderBy);
    }

    /**
     *@Description:添加保存邮件服务器参数
     *@param:@param mailServParamDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public MailServParam saveMailServParam(MailServParamDTO mailServParamDto) {
        return mailServParamDao.saveMailServParam(mailServParamDto);
    }

    /**
     *@Description:根据id查询该条记录
     *@param:@param espId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public MailServParamDTO find(Integer espId) {
        MailServParam msp = mailServParamDao.find(espId);
        MailServParamDTO mspDto = mailServParamDao.getDto(msp);
        return mspDto;
    }

    /**
     *@Description:修改保存邮件服务器参数
     *@param:@param mailServParamDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO updateMailServParam(MailServParamDTO mailServParamDto) {
        ReturnDTO returnDto = new ReturnDTO();
        returnDto = mailServParamDao.updateMailServParam(mailServParamDto);
        return returnDto;
    }

    /**
     *@Description:删除邮件服务器参数（使用状态改为9）
     *@param:@param espId
     *@author: 张亚运
     *@throws:
     */
    public void deleteMailServParam(Integer espId) {
        MailServParam msp = mailServParamDao.find(espId);
        msp.setStatus(9);
        mailServParamDao.update(msp);
    }

}
