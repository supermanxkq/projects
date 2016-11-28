package com.paySystem.ic.service.message;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.buss.SmsTem;
import com.paySystem.ic.bean.message.ShortMesSend;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemGroupsDTO;
import com.paySystem.ic.web.dto.message.ShortMesSendDTO;
import com.paySystem.ic.web.dto.message.SmsMemGroupDTO;

/**
 * @Description:短信参数Service
 * @author: 张国法
 */
public interface ShortMesSendService extends DAO<ShortMesSend> {

    public static final String SHORTMESSENDSERVICE = "shortMesSendService";

    /**
     *@Title:queryStockByCond
     *@Description:根据条件查询总部入库信息Service方法
     */

    QueryResult<ShortMesSend> querySmsByCond(int firstindex, int maxresult, ShortMesSendDTO smsDto,
            LinkedHashMap<String, String> orderby) throws Exception;

    /**
     *@Description:保存方法
     *@return:void
     *@author: 张亚运
     * @param groupList
     *@throws:
     */
    public void addSMSInfo(ShortMesSendDTO smsDto, List<MemGroupsDTO> groupList);

    /**
     *@Description:根据Id查询该条记录
     *@return:ShortMesSend
     *@author: 张亚运
     *@throws:
     */
    public ShortMesSend find(Integer smsId);

    /**
     *@Description:修改方法
     *@return:ReturnDTO
     *@author: 张亚运
     * @param groupList
     *@throws:
     */
    public ReturnDTO update(ShortMesSendDTO smsDTO, List<MemGroupsDTO> groupList);

    /**
     *@Description:TODO
     *@return:ShortMesSendDTO
     *@author: 张亚运
     *@throws:
     */
    public ShortMesSendDTO findShortMesSend(Integer stockId);

    /**
     *@Description:查询操作员姓名
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
     *@throws:
     */
    public List<SmsMemGroupDTO> findMemGroup(Integer smsId);

    /**
     *@Description:获取所有短信模版
     *@return:List<Object[]>
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
