package com.paySystem.ic.dao.buss.impl;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.buss.SmsTem;
import com.paySystem.ic.dao.buss.SmsTemDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.buss.SmsTemDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:SmsTemDaoImpl
 * @Description:短信常用模版dao实现类
 * @date: 2014-7-10上午11:48:05
 * @author: 张亚运
 * @version: V1.0
 */
@Repository(SmsTemDao.SMSTEMDAO)
public class SmsTemDaoImpl extends DaoSupport<SmsTem> implements SmsTemDao {

    /**
     *@Description:添加保存短信模版
     *@param:@param smsTemDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public SmsTem saveSmsTem(SmsTemDTO smsTemDto) {
        UserSession us = Utils.getUserSession();
        SmsTem st = new SmsTem();
        st.setStemId(smsTemDto.getStemId());
        st.setStemName(smsTemDto.getStemName());
        st.setStemTitle(smsTemDto.getStemTitle());
        st.setStemContent(smsTemDto.getStemContent());
        st.setUpdateTime(getSysTime());
        st.setOperator(us.getRealName());
        this.save(st);
        return st;
    }

    /**
     *@Description:修改保存短信模版
     *@param:@param smsTemDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO updateSmsTem(SmsTemDTO smsTemDto) {
        ReturnDTO returnDto = new ReturnDTO();
        UserSession us = Utils.getUserSession();
        SmsTem st = new SmsTem();
        st.setStemId(smsTemDto.getStemId());
        st.setStemName(smsTemDto.getStemName());
        st.setStemTitle(smsTemDto.getStemTitle());
        st.setStemContent(smsTemDto.getStemContent());
        st.setUpdateTime(getSysTime());
        st.setOperator(us.getRealName());
        this.update(st);
        returnDto.setFlag(true);
        return returnDto;
    }

}
