package com.paySystem.ic.service.message.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.buss.SmsTem;
import com.paySystem.ic.bean.message.ShortMesSend;
import com.paySystem.ic.bean.message.SmsMemGroup;
import com.paySystem.ic.dao.message.ShortMesSendDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.message.ShortMesSendService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemGroupsDTO;
import com.paySystem.ic.web.dto.message.ShortMesSendDTO;
import com.paySystem.ic.web.dto.message.SmsMemGroupDTO;

/**
 * @ClassName:StockAdjustmentServiceImpl
 * @Description:库存微调信息Service实现类
 * @author: 张国法
 */
@Service(ShortMesSendService.SHORTMESSENDSERVICE)
public class ShortMesSendServiceImpl extends DaoSupport<ShortMesSend> implements ShortMesSendService {

    public static Logger log = Logger.getLogger(ShortMesSendService.class);

    @Resource
    ShortMesSendDao shortMesSendDao;

    /**
     *@Description:查询方法
     *@param:@param firstindex
     *@param:@param maxresult
     *@param:@param smsDTO
     *@param:@param orderby
     *@param:@return
     *@param:@throws Exception
     *@author: 张亚运
     *@throws:
     */
    public QueryResult<ShortMesSend> querySmsByCond(int firstindex, int maxresult, ShortMesSendDTO smsDTO,
            LinkedHashMap<String, String> orderby) throws Exception {
        return shortMesSendDao.querySmsByCond(firstindex, maxresult, smsDTO, orderby, 0);
    }

    /**
     *@Description:添加方法
     *@param:@param smsDTO
     *@author: 张亚运
     *@throws:
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void addSMSInfo(ShortMesSendDTO smsDTO, List<MemGroupsDTO> groupList) {
        ShortMesSend sms = shortMesSendDao.addSMSInfo(smsDTO);
        for (int i = 0; i < groupList.size(); i++) {
            SmsMemGroup smg = new SmsMemGroup();
            smg.setSmsId(sms.getSmsId());
            smg.setGroupId(groupList.get(i).getGroupId());
            this.save(smg);
        }
    }

    /**
     *@Description:修改更新
     *@param:@param smsDTO
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ReturnDTO update(ShortMesSendDTO smsDTO, List<MemGroupsDTO> groupList) {
        ReturnDTO dto = new ReturnDTO();
        try {
            shortMesSendDao.update(smsDTO);
            if (groupList.size() > 0) {
                shortMesSendDao.deleteSmsGroup(smsDTO.getSmsId());
                for (int i = 0; i < groupList.size(); i++) {
                    SmsMemGroup smg = new SmsMemGroup();
                    smg.setSmsId(smsDTO.getSmsId());
                    smg.setGroupId(groupList.get(i).getGroupId());
                    this.save(smg);
                }
            }
            dto.setFlag(true);

        }
        catch (Exception e) {
            e.printStackTrace();
            dto.setFlag(false);
        }
        return dto;
    }

    /**
     *@Description:根据Id查询该条记录
     *@param:@param smsId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public ShortMesSend find(Integer smsId) {

        return shortMesSendDao.find(smsId);
    }

    /**
     *@Description:根据Id查询记录
     *@param:@param smsId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public ShortMesSendDTO findShortMesSend(Integer smsId) {
        ShortMesSendDTO smsDto = null;
        smsDto = shortMesSendDao.findShortMesSend(smsId);
        return smsDto;
    }

    /**
     *@Description:获取操作人
     *@param:@param username
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    @Override
    public String findUser(String username) {
        String realName = shortMesSendDao.findUser(username);
        return realName;
    }

    /**
     *@Description:获取所有的会员群组
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    @Override
    public List<Object[]> getMemGroups() {
        List<Object[]> list = shortMesSendDao.getMemGroups();
        return list;
    }

    /**
     *@Description:获取关联的会员群组
     *@param:@param smsId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    @Override
    public List<SmsMemGroupDTO> findMemGroup(Integer smsId) {

        List<SmsMemGroupDTO> memGroups = shortMesSendDao.findMemGroup(smsId);
        return memGroups;
    }

    /**
     *@Description:获取所有短信模版
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    @Override
    public List<SmsTem> getSmsTems() {
        return shortMesSendDao.getSmsTems();
    }

    /**
     *@Description:获取机构名称
     *@param:@param merOrgId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    @Override
    public String findOrgan(String merOrgId) {
        String organName = shortMesSendDao.findOrgan(merOrgId);
        return organName;
    }

    /**
     *@Description:获取商户名称
     *@param:@param merOrgId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    @Override
    public String findMerName(String merOrgId) {
        String merName = shortMesSendDao.findMerName(merOrgId);
        return merName;
    }
}
