package com.paySystem.ic.dao.message.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.buss.SmsTem;
import com.paySystem.ic.bean.message.ShortMesSend;
import com.paySystem.ic.bean.message.SmsMemGroup;
import com.paySystem.ic.dao.base.MerchantsDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.message.ShortMesSendDao;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemberDTO;
import com.paySystem.ic.web.dto.message.ShortMesSendDTO;
import com.paySystem.ic.web.dto.message.SmsMemGroupDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @Description:短信参数Dao的实现类
 * @author: 张国法
 */
@Repository(ShortMesSendDao.SHORTMESSENDDAO)
public class ShortMesSendDaoImpl extends DaoSupport<ShortMesSend> implements ShortMesSendDao {

    ShortMesSend shortMesSend = new ShortMesSend();

    ShortMesSendDTO smsDto = new ShortMesSendDTO();

    MemberDTO memberDTO = new MemberDTO();

    /** 创建返回值数据传送对象 */
    ReturnDTO dot = new ReturnDTO();

    // @Resource
    // SMSService smsServiceImpl;
    @Resource
    MerchantsDao merchantsDaoImpl;

    /**
     *@Description:查询方法
     *@param:@param page
     *@param:@param pageNum
     *@param:@param smsDto
     *@param:@param orderby
     *@param:@param flag
     *@param:@return
     *@param:@throws Exception
     *@author: 张亚运
     *@throws:
     */
    public QueryResult<ShortMesSend> querySmsByCond(int page, int pageNum, ShortMesSendDTO smsDto,
            LinkedHashMap<String, String> orderby, int flag) throws Exception {

        StringBuilder sql = new StringBuilder(); // 封装查询where条件
        List<Object> params = new ArrayList<Object>();
        // 获取UserSession
        UserSession us = Utils.getUserSession();
        /**
         * 限定不同级别操作员的限制条件
         */
        switch (us.getUserLevel()) {
        case 0:
            break;
        case 1:
            sql.append(" and o.merOrgId ='" + us.getOrganId() + "'");
            break;
        case 2:
            sql.append(" and o.merOrgId ='" + us.getMerId() + "'");
            break;
        }

        /** 判断页面条件 */
        /**
         * if (StringUtils.isNotBlank(smsDto.getBeginDate())) {// 判断起始时间
         * sql.append(" and str_to_date(o.createTime,'%Y-%m-%d') " + ">='" +
         * smsDto.getBeginDate() + "'"); }
         * 
         * if (StringUtils.isNotBlank(smsDto.getEndDate())) {// 检索结束时间
         * sql.append(" and str_to_date(o.createTime,'%Y-%m-%d') " + "<='" +
         * smsDto.getEndDate() + "'"); }
         **/
        if (null != smsDto.getSmsType() && smsDto.getSmsType() != -1) {// 检索发送人群
            sql.append(" and o.smsType = " + smsDto.getSmsType());
        }

        if (smsDto.getSmsStatus() != -1) {// 检索状态
            sql.append(" and o.smsStatus = " + smsDto.getSmsStatus());
        }

        if (StringUtils.isNotBlank(smsDto.getSmsTitle())) {// 检索标题
            sql.append(" and o.smsTitle like ?").append(params.size() + 1);
            params.add("%" + smsDto.getSmsTitle().trim() + "%");
        }

        QueryResult<ShortMesSend> queryResult = getScrollData(page, pageNum, sql.toString(), params.toArray(), orderby);

        return queryResult;
    }

    /**
     *@Description:添加新的短信
     *@param:@param smsDto
     *@author: 张亚运
     *@throws:
     */
    public ShortMesSend addSMSInfo(ShortMesSendDTO smsDto) {
        UserSession us = Utils.getUserSession();

        ShortMesSend s = new ShortMesSend();

        try {
            s.setSmsTitle(smsDto.getSmsTitle());
            s.setSmsContent(smsDto.getSmsContent());
            s.setSmsType(smsDto.getSmsType());
            s.setNum(smsDto.getNum());
            s.setTotalPrice(smsDto.getTotalPrice());
            if (us.getUserLevel() == 2) {
                s.setMerOrgId(us.getMerId());
            }
            else {
                s.setMerOrgId(us.getOrganId());
            }
            s.setSmsStatus(0);
            s.setCreateTime(this.getSysTime());
            s.setOperator(us.getUserName());
            s.setUserType(smsDto.getUserType());
            this.save(s);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     *@Description:修改方法
     *@param:@param smsDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO update(ShortMesSendDTO smsDto) {
        UserSession us = Utils.getUserSession();
        ReturnDTO dto = new ReturnDTO();
        // ShortMesSend sms=smsDao.find(smsDto.getSmsId());
        ShortMesSend sms = this.find(smsDto.getSmsId());
        if (smsDto != null) {
            if (0 == smsDto.getSmsStatus()) {
                // 修改
                shortMesSend.setSmsStatus(sms.getSmsStatus());
                smsDto.setNum(smsDto.getNum());
                smsDto.setTotalPrice(smsDto.getTotalPrice());
                shortMesSend.setSmsStatus(0);

            }
            else if (2 == smsDto.getSmsStatus()) {
                // 审核不通过
                shortMesSend.setSmsStatus(2);
                shortMesSend.setAuditTime(this.getSysTime());
            }
            else if (1 == smsDto.getSmsStatus()) {
                // 审核通过
                shortMesSend.setSmsStatus(1);
                shortMesSend.setAuditTime(this.getSysTime());
            }
            else if (9 == smsDto.getSmsStatus()) {
                // 删除
                shortMesSend.setSmsStatus(9);
            }

            shortMesSend.setCreateTime(sms.getCreateTime());
            shortMesSend.setSmsId(smsDto.getSmsId());
            shortMesSend.setSmsTitle(smsDto.getSmsTitle());
            shortMesSend.setSmsContent(smsDto.getSmsContent());
            shortMesSend.setSmsType(smsDto.getSmsType());
            shortMesSend.setTotalPrice(smsDto.getTotalPrice());
            shortMesSend.setNum(smsDto.getNum());
            shortMesSend.setMerOrgId(smsDto.getMerOrgId());
            shortMesSend.setOperator(us.getUserName());
            shortMesSend.setUserType(smsDto.getUserType());

            super.update(shortMesSend);
            dto.setFlag(true);
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
    public ShortMesSendDTO findShortMesSend(Integer smsId) {
        ShortMesSendDTO smsDto = getsmsDto(this.find(smsId));
        return smsDto;
    }

    /**
     *@Description:实体转Dto
     *@return:ShortMesSendDTO
     *@author: 张亚运
     *@throws:
     */
    protected ShortMesSendDTO getsmsDto(ShortMesSend sms) {
        ShortMesSendDTO smsDto = new ShortMesSendDTO();
        if (sms != null) {
            smsDto.setSmsId(sms.getSmsId());
            smsDto.setSmsTitle(sms.getSmsTitle());
            smsDto.setSmsContent(sms.getSmsContent());
            smsDto.setSmsType(sms.getSmsType());
            smsDto.setSmsStatus(sms.getSmsStatus());
            smsDto.setCreateTime(sms.getCreateTime());
            smsDto.setAuditTime(sms.getAuditTime());
            smsDto.setNum(sms.getNum());
            smsDto.setTotalPrice(sms.getTotalPrice());
            smsDto.setOperator(sms.getOperator());
        }

        return smsDto;
    }

    @Override
    public String findUser(String username) {
        String sql = "select u.realName from s_user u where u.userName=?1";
        String realName = (String) em.createNativeQuery(sql).setParameter(1, username).getSingleResult();
        return realName;
    }

    /**
     *@Description:获取所有会员群组
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> getMemGroups() {
        UserSession us = Utils.getUserSession();
        List<Object[]> memGroups = new ArrayList<Object[]>();
        String sql = "";
        if (us.getUserLevel() == 0) {
            	sql = "select o.groupId,o.groupName from M_MemGroups o where o.status=1 and o.merId is null";
           
        }else {
        	sql = "select o.groupId,o.groupName from M_MemGroups o where o.status=1 and o.merId ='"+us.getMerId()+"'";
        }
        try {
            memGroups = em
                    .createNativeQuery(sql).getResultList();
        }
        catch (Exception e) {
            e.getMessage();
        }
        return memGroups;
    }

    /**
     *@Description:获取所关联的会员群组
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SmsMemGroupDTO> findMemGroup(Integer smsId) {

        List<SmsMemGroup> memGroups = new ArrayList<SmsMemGroup>();
        List<SmsMemGroupDTO> smgList = new ArrayList<SmsMemGroupDTO>();
        try {
            memGroups = em.createQuery("from SmsMemGroup o where smsId = ?1").setParameter(1, smsId).getResultList();
            for (int i = 0; i < memGroups.size(); i++) {
                SmsMemGroupDTO smgDto = new SmsMemGroupDTO();
                smgDto = (SmsMemGroupDTO) EntityDtoConverter.bean2Dto(memGroups.get(i), smgDto);
                smgList.add(smgDto);
            }
        }
        catch (Exception e) {
            e.getMessage();
        }

        return smgList;
    }

    /**
     *@Description:删除所关联的会员群组
     *@param:@param smsId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    @Override
    public int deleteSmsGroup(Integer smsId) {
        int i = 0;
        i = em.createNativeQuery("delete from b_smsmemgroup where smsId=?1").setParameter(1, smsId).executeUpdate();
        return i;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SmsTem> getSmsTems() {
        List<SmsTem> smsTems = new ArrayList<SmsTem>();
        try {
            smsTems = em.createQuery("select o from SmsTem o where 1=1").getResultList();
        }
        catch (Exception e) {
            e.getMessage();
        }
        return smsTems;
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
        String sql = "select o.name from b_organs o where o.organId=?1";
        String organName = (String) em.createNativeQuery(sql).setParameter(1, merOrgId).getSingleResult();
        return organName;
    }

    /**
     *@Description:获取商户名称
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String findMerName(String merOrgId) {
        String sql = "select m.merName from b_merchants m where m.merId=?1";
        String merName = (String) em.createNativeQuery(sql).setParameter(1, merOrgId).getSingleResult();
        return merName;
    }
}
