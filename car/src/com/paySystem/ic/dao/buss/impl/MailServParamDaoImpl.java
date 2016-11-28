package com.paySystem.ic.dao.buss.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.buss.MailServParam;
import com.paySystem.ic.dao.buss.MailServParamDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.buss.MailServParamDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:MailServParamDaoImpl
 * @Description:邮件服务设置Dao实现
 * @date: 2014-7-4下午04:45:48
 * @author: 张亚运
 * @version: V1.0
 */
@Repository(MailServParamDao.MAILSERVPARAMDAO)
public class MailServParamDaoImpl extends DaoSupport<MailServParam> implements MailServParamDao {

    /**
     *@Description:查询方法
     *@param:@param page
     *@param:@param pageNum
     *@param:@param goodsFamilyDto
     *@param:@param orderBy
     *@param:@return
     *@param:@throws Exception
     *@author: 张亚运
     *@throws:
     */
    public QueryResult<MailServParamDTO> queryAll(int page, int pageNum, MailServParamDTO mailServDto,
            LinkedHashMap<String, String> orderBy) throws Exception {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();

        if (StringUtils.isNotBlank(mailServDto.getEspUrl())) {
            sql.append(" and o.espUrl like ?").append(params.size() + 1);
            params.add("%" + mailServDto.getEspUrl().trim() + "%");
        }
        if (mailServDto.getStatus() != 2) {
            sql.append(" and o.status = " + mailServDto.getStatus());
        }

        QueryResult<MailServParamDTO> queryResult = new QueryResult<MailServParamDTO>();

        /**将查询出的实体转换为Dto*/
        QueryResult<MailServParam> result = new QueryResult<MailServParam>();
        result = getScrollData(page, pageNum, sql.toString(), params.toArray(), orderBy);
        List<MailServParamDTO> list = new ArrayList<MailServParamDTO>();
        for (int i = 0; i < result.getResultlist().size(); i++) {
            MailServParam msp = new MailServParam();
            msp = result.getResultlist().get(i);
            MailServParamDTO msDto = getDto(msp);
            list.add(msDto);
        }

        queryResult.setTotalrecord(result.getTotalrecord());
        queryResult.setResultlist(list);
        return queryResult;
    }

    /**
     *@Description:添加保存邮件服务器参数
     *@param:@param mailServParamDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public MailServParam saveMailServParam(MailServParamDTO mailServParamDto) {
        UserSession us = Utils.getUserSession();
        MailServParam msp = new MailServParam();
        msp.setEspUrl(mailServParamDto.getEspUrl());
        msp.setEspPort(mailServParamDto.getEspPort());
        msp.setEmaccNo(mailServParamDto.getEmaccNo());
        msp.setEmpwd(mailServParamDto.getEmpwd());
        msp.setReplyUrl(mailServParamDto.getReplyUrl());
        msp.setOperator(us.getRealName());
        msp.setCreateTime(this.getSysTime());
        msp.setStatus(mailServParamDto.getStatus());
        this.save(msp);
        return msp;
    }

    /**
     *@Description:修改保存邮件服务器参数
     *@param:@param mailServParamDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO updateMailServParam(MailServParamDTO mailServParamDto) {
        UserSession us = Utils.getUserSession();
        ReturnDTO returnDto = new ReturnDTO();
        MailServParam msp = this.find(mailServParamDto.getEspId());
        if (msp != null) {
            msp.setEspId(mailServParamDto.getEspId());
            msp.setEspUrl(mailServParamDto.getEspUrl());
            msp.setEspPort(mailServParamDto.getEspPort());
            msp.setEmaccNo(mailServParamDto.getEmaccNo());
            msp.setEmpwd(mailServParamDto.getEmpwd());
            msp.setReplyUrl(mailServParamDto.getReplyUrl());
            msp.setOperator(us.getRealName());
            //msp.setCreateTime(mailServParamDto.getCreateTime());
            msp.setStatus(mailServParamDto.getStatus());
            msp.setUpdateTime(this.getSysTime());
            this.update(msp);
            returnDto.setFlag(true);
        }
        return returnDto;
    }

    /**
     *@Description:实体转换Dto
     *@return:MailServParamDTO
     *@author: 张亚运
     *@throws:
     */
    public MailServParamDTO getDto(MailServParam mailServParam) {
        MailServParamDTO dto = new MailServParamDTO();
        dto.setEspId(mailServParam.getEspId());
        dto.setEspUrl(mailServParam.getEspUrl());
        dto.setEspPort(mailServParam.getEspPort());
        dto.setEmaccNo(mailServParam.getEmaccNo());
        dto.setEmpwd(mailServParam.getEmpwd());
        dto.setStatus(mailServParam.getStatus());
        dto.setOperator(mailServParam.getOperator());
        dto.setCreateTime(mailServParam.getCreateTime());
        dto.setReplyUrl(mailServParam.getReplyUrl());
        dto.setUpdateTime(mailServParam.getUpdateTime());
        return dto;
    }

}
