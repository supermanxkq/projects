package com.paySystem.ic.dao.message;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.buss.SmsTem;
import com.paySystem.ic.bean.message.ShortMesSend;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.message.ShortMesSendDTO;
import com.paySystem.ic.web.dto.message.SmsMemGroupDTO;

/**
 * @ClassName:MessParamDAO
 * @Description:TODO
 * @date: 2014-3-19上午11:48:33
 * @author: 张亚运
 * @version: V1.0
 */
public interface ShortMesSendDao extends DAO<ShortMesSend> {

    public static final String SHORTMESSENDDAO = "shortMesSendDao";

    /**
     *@Description:查询方法
     *@return:QueryResult<ShortMesSend>
     *@author: 张亚运
     *@throws:
     */
    QueryResult<ShortMesSend> querySmsByCond(int firstindex, int maxresult, ShortMesSendDTO smsDTO,
            LinkedHashMap<String, String> orderby, int flag) throws Exception;

    /**
     *@Description:添加保存方法
     *@return:void
     *@author: 张亚运
     * @param groupList
     *@throws:
     */
    public ShortMesSend addSMSInfo(ShortMesSendDTO smsDTO);

    /**
     *@Description:修改保存方法
     *@return:ReturnDTO
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO update(ShortMesSendDTO smsDTO);

    /**
     *@Description:根据Id查询该条记录
     *@return:ShortMesSendDTO
     *@author: 张亚运
     *@throws:
     */
    public ShortMesSendDTO findShortMesSend(Integer smsId);

    /**
     *@Description:获取操作员姓名
     *@return:User
     *@author: 张亚运
     *@throws:
     */
    public String findUser(String username);

    /**
     *@Description:获取所有会员群组
     *@return:List<MemGroups>
     *@author: 张亚运
     *@throws:
     */
    public List<Object[]> getMemGroups();

    /**
     *@Description:获取所关联的会员群组
     *@return:List<MemberGroupsDetailDTO>
     *@author: 张亚运
     * @param smsId
     *@throws:
     */
    public List<SmsMemGroupDTO> findMemGroup(Integer smsId);

    /**
     *@Description:删除关联表中关联的会员群组
     *@return:int
     *@author: 张亚运
     *@throws:
     */
    public int deleteSmsGroup(Integer smsId);

    /**
     *@Description:获取所有短信模版
     *@return:List<SmsTem>
     *@author: 张亚运
     *@throws:
     */
    public List<SmsTem> getSmsTems();

    /**
     *@Description:获取机构名称
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String findOrgan(String merOrgId);

    /**
     *@Description:获取商户名称
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String findMerName(String merOrgId);

}
