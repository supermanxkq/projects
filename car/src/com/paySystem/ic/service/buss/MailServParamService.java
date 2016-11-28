package com.paySystem.ic.service.buss;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.buss.MailServParam;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.buss.MailServParamDTO;

/**
 * @ClassName:MailServParamService
 * @Description:邮件服务器设置Service
 * @date: 2014-7-4下午04:29:04
 * @author: 张亚运
 * @version: V1.0
 */
public interface MailServParamService {

    public static final String MAILSERVPARAMSERVICE = "mailServParamService";

    /**
     *@Description:查询方法
     *@return:QueryResult<MailServParam>
     *@author: 张亚运
     *@throws:
     */
    public QueryResult<MailServParamDTO> queryAll(int page, int pageNum, MailServParamDTO mailServDto,
            LinkedHashMap<String, String> orderBy) throws Exception;

    /**
     *@Description:添加保存邮件服务器参数
     *@return:MailServParam
     *@author: 张亚运
     *@throws:
     */
    public MailServParam saveMailServParam(MailServParamDTO mailServParamDto);

    /**
     *@Description:根据id查询该条记录
     *@return:MailServParamDTO
     *@author: 张亚运
     *@throws:
     */
    public MailServParamDTO find(Integer espId);

    /**
     *@Description:修改保存邮件服务器参数
     *@return:ReturnDTO
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO updateMailServParam(MailServParamDTO mailServParamDto);

    /**
     *@Description:删除邮件服务器参数（使用状态改为9）
     *@return:void
     *@author: 张亚运
     *@throws:
     */
    public void deleteMailServParam(Integer espId);

}
