package com.paySystem.ic.dao.buss;

import com.paySystem.ic.bean.buss.SmsTem;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.buss.SmsTemDTO;

/**
 * @ClassName:SmsTemDao
 * @Description:短信常用模版dao
 * @date: 2014-7-10上午11:38:54
 * @author: 张亚运
 * @version: V1.0
 */
public interface SmsTemDao extends DAO<SmsTem> {

    public final static String SMSTEMDAO = "smsTemDao";

    /**
     *@Description:保存短信常用模版
     *@return:SmsTem
     *@author: 张亚运
     *@throws:
     */
    public SmsTem saveSmsTem(SmsTemDTO smsTemDto);

    /**
     *@Description:修改保存短信常用模版
     *@return:ReturnDTO
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO updateSmsTem(SmsTemDTO smsTemDto);

}
