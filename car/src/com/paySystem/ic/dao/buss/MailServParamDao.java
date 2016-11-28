package com.paySystem.ic.dao.buss;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.buss.MailServParam;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.buss.MailServParamDTO;

/**
 * @ClassName:MailServParamDao
 * @Description:邮件服务器设置Dao接口
 * @date: 2014-7-4下午04:38:11
 * @author: 张亚运
 * @version: V1.0
 */
public interface MailServParamDao extends DAO<MailServParam> {

    public static final String MAILSERVPARAMDAO = "mailServParamDao";

    /**
     *@Description:查询方法
     *@return:QueryResult<MailServParamDTO>
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
     *@Description:将实体转换成Dto
     *@return:MailServParamDTO
     *@author: 张亚运
     *@throws:
     */
    public MailServParamDTO getDto(MailServParam mailServParam);

    /**
     *@Description:修改保存邮件服务器参数
     *@return:ReturnDTO
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO updateMailServParam(MailServParamDTO mailServParamDto);
}
