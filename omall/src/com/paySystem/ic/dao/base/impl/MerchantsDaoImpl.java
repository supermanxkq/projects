package com.paySystem.ic.dao.base.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.member.MemCardConn;
import com.paySystem.ic.dao.base.MerchantsDao;
import com.paySystem.ic.dao.base.OrgansDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.member.MemCardConnDAO;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.MerchantsDTO;
import com.paySystem.ic.web.dto.base.StoreInfoDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsString;

/**
 * @ClassName:MerchantsDaoImpl
 * @Description:商户Dao实现类
 * @date: 2013-12-7上午10:09:12
 * @author: 谢洪飞
 * @version: V1.0
 */
@Repository(MerchantsDao.MERCHANTSDAO)
public class MerchantsDaoImpl extends DaoSupport<Merchants> implements MerchantsDao {

    // 机构service 后续换 dao
    @Resource
    OrgansDao organsDao;

    @Resource
    MemCardConnDAO memCardConnDao;

    @SuppressWarnings("unchecked")
    public String getMerId() {
        /*
         * String merId = ""; List<String> string =
         * em.createQuery("select o.merId from Merchants o order by o.merId desc"
         * ).setMaxResults(1).getResultList();
         * if(string==null||string.isEmpty()){ merId = "0000001"; }else{ merId =
         * string.get(0); merId = merId.substring(10, 15); Long newmerId =
         * Long.valueOf(merId)+1; merId = newmerId.toString(); }
         * while(merId.length()<5){ merId = "0" + merId; } merId = "00000001" +
         * merId; while(this.validate(merId)){ merId = merId.substring(10, 15);
         * Long newmerId = Long.valueOf(merId)+1; merId = newmerId.toString();
         * while(merId.length()<5){ merId = "0" + merId; } merId = "00000001" +
         * merId; }
         */

        String merId = Utils.createSerialNum(em, "merId", "Merchants", 15, 8, "1", null, null);
        String id = merId;
        if (merId.length() < 15) {
            String preid = id.substring(0, 8);
            merId = merId.substring(8, merId.length());
            while (merId.length() < 7) {
                merId = "0" + merId;
            }
            merId = preid + merId;
        }
        return merId;
    }

    @SuppressWarnings("unchecked")
    public List<OptionsString> getOption() {
        List<Merchants> merchantsList = em.createQuery("from Merchants o where o.status = 1 order by merId asc")
                .getResultList();
        List<OptionsString> list = new ArrayList<OptionsString>();
        for (Merchants merchants : merchantsList) {
            list.add(new OptionsString(merchants.getMerId(), merchants.getMerName() + "("
                    + merchants.getOrgans().getName() + ")"));
        }
        return list;
    }

    public List<OptionsString> getOptionByOrganId(String organId) {
        String sql = "";
        Organs organs = organsDao.findById(organId);
        if (organs.getParentId().equals("0")) {
            sql = "from Merchants o where o.status = 1 and (o.organs.organId = '" + organId
                    + "' or o.organs.parentId = '" + organId + "') order by merId asc";
        }
        else {
            sql = "from Merchants o where o.status = 1 and o.organs.organId = '" + organId + "' order by merId asc";
        }

        List<Merchants> merchantsList = em.createQuery(sql).getResultList();
        List<OptionsString> list = new ArrayList<OptionsString>();
        for (Merchants merchants : merchantsList) {
            list.add(new OptionsString(merchants.getMerId(), merchants.getMerName()));
        }
        return list;
    }

    public Merchants saveMerchant(MerchantsDTO merchantsDTO) {
        Merchants merchants = new Merchants();
        UserSession us = Utils.getUserSession();
        /** 封装商户信息 */
        merchants.setMerId(Utils.getString(merchantsDTO.getMerId()));
        
        merchants.setMemId(merchantsDTO.getMemId());
        
        merchants.setMerName(Utils.getString(merchantsDTO.getMerName()));
        // 状态
        merchants.setStatus(merchantsDTO.getStatus());
        // 合作方式
        merchants.setCoopWay(merchantsDTO.getCoopWay());
        // 手续费率
        merchants.setRakeRate(merchantsDTO.getRakeRate());
        // 法人代表
        merchants.setConPerName(Utils.getString(merchantsDTO.getConPerName()));
        // 手机号码
        merchants.setConPerTeleNo(Utils.getString(merchantsDTO.getConPerTeleNo()));
        // 固话号码
        merchants.setTeleNo(Utils.getString(merchantsDTO.getTeleNo()));
        // 所在地区
        merchants.setAreaId(merchantsDTO.getAreaId());
        // 地址
        merchants.setAddress(Utils.getString(merchantsDTO.getAddress()));
        // 是否代理商标志
        merchants.setAgentSign(merchantsDTO.getAgentSign());
        // 代理商折扣率
        // merchants.setAgentDiscRate(merchantsDTO.getAgentDiscRate());
        // 充值限额
        merchants.setTranLimitSign(merchantsDTO.getTranLimitSign());
        // 撤销原因
        // merchants.setRevorkReason(Utils.getString(merchantsDTO.getRevorkReason()));
        merchants.setConNo(Utils.getString(merchantsDTO.getConNo()));
        // 邮编
        merchants.setZip(Utils.getString(merchantsDTO.getZip()));
        // 经销商标志
        merchants.setAgentSign(merchantsDTO.getAgentSign());
        // 上级经销商
        merchants.setPreMerId(merchantsDTO.getPreMerId());
        // 是否充值限额
        merchants.setTranLimitSign(merchantsDTO.getTranLimitSign());
        // 合同号
        merchants.setConNo(merchantsDTO.getConNo());
        // 结算周期
        merchants.setSettPeriod(merchantsDTO.getSettPeriod());
        // 开户银行
        merchants.setBankName(Utils.getString(merchantsDTO.getBankName()));
        // 开户账户
        merchants.setBankAccountNo(Utils.getString(merchantsDTO.getBankAccountNo()));
        // 开户名称
        merchants.setBankUser(Utils.getString(merchantsDTO.getBankUser()));
        // 是否开票
        merchants.setInvSign(merchantsDTO.getInvSign());
        // 是否私户
        merchants.setPrivateSign(merchantsDTO.getPrivateSign());

        /*
         * merchants.setLastSettTime(this.getSysTime());
         * 
         * merchants.setUpdateTime(this.getSysTime());
         * merchants.setCreateTime(this.getSysTime());
         */

        merchants.setLastSettTime(this.getSysTime());
        merchants.setUpdateTime(this.getSysTime());
        merchants.setCreateTime(this.getSysTime());
        merchants.setConPerTeleNo(merchantsDTO.getConPerTeleNo());
        // 发卡机构
        switch (us.getUserLevel()) {
        case 0:
            merchants.setOrgans(merchantsDTO.getOrgans());
            break;
        case 1:
        case 2:
            Organs organs = organsDao.findById(us.getOrganId());
            if ("0".equals(organs.getParentId()) || organs.getParentId() == "0") {
                merchants.setOrgans(organs);
            }
            else {
                merchants.setOrgans(organsDao.findById(organs.getParentId()));
            }
            break;

        default:
            break;
        }

        if (merchantsDTO.getPreMerId() != null) {
            merchants.setPreMerId(merchantsDTO.getPreMerId());
        }

        /** 获取商户平台卡号，并保存 */
        MemCardConn memCardConn = memCardConnDao.find(merchantsDTO.getMemId());
        if (null != null) {
            merchants.setAccNo(memCardConn.getCardNo());
        }
        /** 保存商户信息 */
        this.save(merchants);
        return merchants;
    }

    public ReturnDTO updateMerchant(MerchantsDTO merchantsDTO) throws Exception {
        ReturnDTO dto = new ReturnDTO();
        Merchants merchants = this.find(merchantsDTO.getMerId());

        /* ↑ 修改商户限额表中信息 ↑ */
        if (merchants != null) {
            merchants.setTeleNo(Utils.getString(merchantsDTO.getTeleNo()));
            merchants.setConPerName(Utils.getString(merchantsDTO.getConPerName()));
            merchants.setConPerTeleNo(Utils.getString(merchantsDTO.getConPerTeleNo()));
            merchants.setBankName(Utils.getString(merchantsDTO.getBankName()));
            merchants.setBankAccountNo(Utils.getString(merchantsDTO.getBankAccountNo()));
            merchants.setBankUser(Utils.getString(merchantsDTO.getBankUser()));
            merchants.setUpdateTime(this.getSysTime());
            merchants.setAddress(Utils.getString(merchantsDTO.getAddress()));
            merchants.setZip(Utils.getString(merchantsDTO.getZip()));
            merchants.setRakeRate(merchantsDTO.getRakeRate());
            UserSession us = Utils.getUserSession();

            if (us.getUserLevel() != 2) {
                merchants.setMerName(Utils.getString(merchantsDTO.getMerName()));
                merchants.setCoopWay(merchantsDTO.getCoopWay());// 合作方式
                merchants.setAreaId(merchantsDTO.getAreaId());
                merchants.setStatus(merchantsDTO.getStatus());
                merchants.setAgentSign(merchantsDTO.getAgentSign());
                merchants.setAgentDiscRate(merchantsDTO.getAcquirerRate());
                merchants.setTranLimitSign(merchantsDTO.getTranLimitSign());
                merchants.setRevorkReason(Utils.getString(merchantsDTO.getRevorkReason()));
                merchants.setConNo(merchantsDTO.getConNo());
                merchants.setInvSign(merchantsDTO.getInvSign());
                merchants.setPrivateSign(merchantsDTO.getPrivateSign());
                merchants.setSettPeriod(merchantsDTO.getSettPeriod());
                if (merchantsDTO.getPreMerId() != null && !merchantsDTO.getPreMerId().equals("-1")) {
                    if (merchantsDTO.getPreMerId().equals(merchants.getMerId())) {
                        dto.setMsg("上级商户不能选择自己的商户");
                        return dto;
                    }
                    merchants.setPreMerId(merchantsDTO.getPreMerId());
                }
                // 收单关系
                String organId = "";
                if (merchants.getOrgans().getParentId().equals("0")) {
                    organId = merchants.getOrgans().getOrganId();
                }
                else {
                    organId = merchants.getOrgans().getParentId();
                }
            }
            this.update(merchants);

            dto.setFlag(true);
        }
        return dto;
    }

    public boolean validate(String merId) {
        long count = (Long) em.createQuery("select count(o) from Merchants o where o.merId=?1").setParameter(1, merId)
                .getSingleResult();
        return count > 0;
    }

    public boolean validateName(String nameChinese, String merId) {
        long count = (Long) em.createQuery(
                "select count(o) from Merchants o where o.merName ='" + nameChinese + "' and o.merId <> '" + merId
                        + "'").getSingleResult();
        return count > 0;
    }

    @SuppressWarnings("unchecked")
    public QueryResult queryMerByCond(int firstindex, int maxresult, MerchantsDTO merchantsDTO,
            LinkedHashMap<String, String> orderby) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();

        UserSession us = Utils.getUserSession();
        switch (us.getUserLevel()) {
        case 1:
            if (!us.getPreOrganId().equals("0")) {
                sql.append(" and (o.organs.parentId = '" + us.getOrganId() + "' or o.organs.organId = '"
                        + us.getOrganId() + "')");
            }
            else {
                sql.append(" and o.organs.organId = '" + us.getOrganId() + "'");
            }
            break;
        case 2:
            sql.append(" and o.merId = '" + us.getMerId() + "'");
            break;
        }
        if (merchantsDTO.getHelpSign() == 1) {
            sql.append(" and o.status in ('0','1')");
        }
        else if (merchantsDTO.getStatus() != -1) {
            sql.append(" and o.status =" + merchantsDTO.getStatus());
        }
        // 商户领卡:如果商户所属机构号不为空，则：
        if (merchantsDTO.getStockSsOrganId() != null && !"".equals(merchantsDTO.getStockSsOrganId())) {
            sql.append(" and o.organs.organId = '" + merchantsDTO.getStockSsOrganId() + "'");
        }

        /** 判断过滤条件，如果无过滤条查询全部数据 */
        if (StringUtils.isNotBlank(merchantsDTO.getMerId())) {
            sql.append(" and o.merId like ?").append(params.size() + 1);
            params.add("%" + merchantsDTO.getMerId().trim() + "%");
        }
        if (StringUtils.isNotBlank(merchantsDTO.getMerName())) {
            sql.append(" and o.merName like ?").append(params.size() + 1);
            params.add("%" + merchantsDTO.getMerName().trim() + "%");
        }
        QueryResult queryResult = getScrollData(firstindex, maxresult, sql.toString(), params.toArray(), orderby);

        return queryResult;
    }

    /**
     *@Title:getAllMerchants
     *@Description:获取所有商户信息
     *@param:@return
     *@return:List<Merchants>
     *@author:
     *@thorws:
     */
    public List<Merchants> getAllMerchants() {
        List<Merchants> merList = this.findByJpl(" from Merchants o ");
        return merList;
    }

    /**
     *@Title:getMerchantsNeedToSett
     *@Description:获取需要结算的商户信息:如果上次结算日期与系统时间相差的天数大于一个结算周期
     *@param:@return
     *@return:List<Merchants> 需要结算的商户集合
     *@author:谢
     *@thorws:
     */
    public List<Merchants> getMerchantsNeedToSett() {

        List<Merchants> allMerList = getAllMerchants();
        List<Merchants> needToSettMerList = new ArrayList<Merchants>();

        for (Merchants merchants : allMerList) {// 判断是否需要结算
            Date lastSettDate = merchants.getLastSettTime();
            int diffDay = DateTimeTool.getDiffDay(lastSettDate, getSysTime()).intValue();
            System.out.println(diffDay);
            if (diffDay >= merchants.getSettPeriod()) {
                needToSettMerList.add(merchants);
            }
        }
        return needToSettMerList;
    }

    /**
     *@Title:batchUpdateMerchants
     *@Description:批量更新
     *@param:@param merchantsList 商户集合
     *@return:void
     *@author: 谢
     *@thorws:
     */
    public void batchUpdateMersLastSettDate(List<Merchants> merchantsList) {

        for (Merchants merchants : merchantsList) {
            merchants.setLastSettTime(DateTimeTool.nDaysAfter(merchants.getSettPeriod(), merchants.getLastSettTime()));
            this.update(merchants);
        }
    }

    /**
     *@Title:getMerByOrganId
     *@Description:根据机构查询所有下属商户信息
     *@param:@return
     *@return:List<Merchants> 下属商户信息集合
     *@author: 谢
     *@thorws:
     */
    public List<Merchants> getMerByOrganId(String organId) {
        StringBuilder sb = new StringBuilder(" from Merchants o where o.organs.organId='" + organId + "'");
        List<Merchants> merList = em.createQuery(sb.toString()).getResultList();

        return merList;
    }

    /**
     * 获取上级经销商
     * 
     * @Title:getPreMerchants
     *@Description:
     *@param:@return
     *@return:List<OptionsString>
     *@author:谢洪飞
     *@thorws:
     */
    public List<OptionsString> getPreMerchants() {

        StringBuilder sb = new StringBuilder(" from Merchants o where o.agentSign = 1 and o.status = 1 ");
        List<Merchants> merList = em.createQuery(sb.toString()).getResultList();
        List<OptionsString> list = new ArrayList<OptionsString>();
        for (Merchants merchants : merList) {
            list.add(new OptionsString(merchants.getMerId(), merchants.getMerName()));
        }
        return list;

    }

    /**
     *@Title:preMerHelpList
     *@Description:获取上级经销商信息集合
     *@param:@param firstindex
     *@param:@param pageNum
     *@param:@param merchantsDTO
     *@param:@param orderby
     *@param:@return
     *@param:@throws Exception
     *@return:List<Merchants>
     *@author:
     *@thorws:
     */
    public QueryResult<Merchants> preMerHelpList(int firstindex, int pageNum, MerchantsDTO merchantsDTO,
            LinkedHashMap<String, String> orderby) throws Exception {
        StringBuilder sb = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        // 获取经销商状态为0：新开户 或1：正常的信息
        sb.append(" and o.status in (0,1)");
        // 获取是否经销商标志为 1：是的虚拟商户
        sb.append(" and o.agentSign = 1");
        /** 判断过滤条件，如果无过滤条查询全部数据 */
        if (StringUtils.isNotBlank(merchantsDTO.getMerId())) {
            sb.append(" and o.merId like ?").append(params.size() + 1);
            params.add("%" + merchantsDTO.getMerId().trim() + "%");
        }
        if (StringUtils.isNotBlank(merchantsDTO.getMerName())) {
            sb.append(" and o.merName like ?").append(params.size() + 1);
            params.add("%" + merchantsDTO.getMerName().trim() + "%");
        }
        QueryResult queryResult = getScrollData(firstindex, pageNum, sb.toString(), params.toArray(), orderby);

        /* List<Merchants> merList = queryResult.getResultlist(); */
        return queryResult;
    }

    /**
     * 
     *@Title:queryByName
     *@Description:根据名字查询一个集合
     *@param:@param name
     *@param:@return
     *@return:MerchantsDaoImpl
     *@author:井建国
     *@thorws:
     *@father:@see 
     *              com.paySystem.ic.dao.base.MerchantsDao#queryByName(java.lang.
     *              String)
     */
    @SuppressWarnings("unchecked")
    public List<Merchants> queryByName(String name) {
        String sql = "select o from Merchants o where o.merName like ?";
        Query query = em.createQuery(sql);
        query.setParameter(1, "%" + name + "%");
        List<Merchants> list = query.getResultList();
        return list;
    }

    /**
     *@MethodName:updateIsBankCard
     *@Description:更新商户是否支持银行卡
     *@param storeInfoDTO
     *@Author:孟凡岭
     *@Date:2014-12-5上午10:44:52
     */
    @Override
    public void updateIsBankCard(StoreInfoDTO storeInfoDTO) {
        // TODO Auto-generated method stub
        String sql = "update b_merchants set isBankCard=? where merId=?";
        Query query = this.em.createNativeQuery(sql);
        query.setParameter(1, storeInfoDTO.getIsBankCard());
        query.setParameter(2, storeInfoDTO.getStoreId());
        query.executeUpdate();
    }

}
