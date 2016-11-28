package com.paySystem.ic.service.buss;

import com.paySystem.ic.bean.buss.SmsTem;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.buss.SmsTemDTO;

/**
 * @ClassName:SmsTemService
 * @Description:短信常用模版设置Service
 * @date: 2014-7-10下午02:08:49
 * @author: 张亚运
 * @version: V1.0
 */
public interface SmsTemService {

    public final static String SMSTEMSERVICE = "smsTemService";

    /**
     *@Description：添加保存短信常用模版
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

    /**
     *@Description:根据Id查询该条记录
     *@return:SmsTemDTO
     *@author: 张亚运
     *@throws:
     */
    public SmsTemDTO find(Integer sTemId);

}
