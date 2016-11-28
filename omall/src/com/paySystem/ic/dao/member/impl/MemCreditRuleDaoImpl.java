package com.paySystem.ic.dao.member.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.MemCreditRule;
import com.paySystem.ic.bean.member.MemGrade;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.member.MemCreditRuleDao;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemCreditRuleDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ClassName:MemCreditRuleDaoImpl
 * @Description:会员积分规则接口实现类
 * @date: 2014-7-22上午11:01:04
 * @author: 张亚运
 * @version: V1.0
 */
@Repository(MemCreditRuleDao.MEMCREDITRULEDAO)
public class MemCreditRuleDaoImpl extends DaoSupport<MemCreditRule> implements MemCreditRuleDao {

    /**
     *@Description:查询方法
     *@param:@param page
     *@param:@param pageNum
     *@param:@param memCreditDto
     *@param:@param orderby
     *@param:@return
     *@author: 张亚运
     * @throws Exception 
     *@throws:
     */
    @SuppressWarnings("unchecked")
    public QueryResult<MemCreditRuleDTO> queryAll(int page, int pageNum, MemCreditRuleDTO memCreditDto,
            LinkedHashMap<String, String> orderby) throws Exception {
        //List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append("select m.creditId,m.consPoints,m.gradeId,g.gradeName,m.gradePoints,m.upperLimit,");
        sql.append("m.loginPoints,m.pointsRate,m.status,m.createTime");
        sql.append(" from M_MemCreditRule m,M_MemGrade g where m.gradeId = g.gradeId");
        /**判断等级名称*/
        if (StringUtils.isNotBlank(memCreditDto.getGradeName())) {
            //sql.append(" and g.gradeName like %cancat(" + memCreditDto.getGradeName() + ")%");
            //sql.append(" and concat(g.gradeName,'') like '%" + memCreditDto.getGradeName() + "%'");
            sql.append(" and g.gradeName like concat('" + memCreditDto.getGradeName() + "','%')");
            //.append(params.size() + 1);
            //params.add("%" + memCreditDto.getGradeName() + "%");
        }
        /**判断使用状态*/
        if (memCreditDto.getStatus() != 2) {
            sql.append(" and m.status = " + memCreditDto.getStatus());
            //.append(params.size() + 1);
            //params.add(memCreditDto.getStatus());
        }

        QueryResult<MemCreditRuleDTO> queryResult = new QueryResult<MemCreditRuleDTO>();

        /**将查询出的实体转换为Dto*/
        //QueryResult<MemCreditRule> result = new QueryResult<MemCreditRule>();
        System.out.println(sql.toString());
        Query query = em.createNativeQuery(sql.toString());
        queryResult.setTotalrecord(query.getResultList().size());
        query.setFirstResult(page);
        query.setMaxResults(pageNum);

        //result = getScrollData(page, pageNum, sql.toString(), params.toArray(), orderby);
        List<MemCreditRuleDTO> list = new ArrayList<MemCreditRuleDTO>();
        List<Object[]> objList = new ArrayList<Object[]>();
        objList = query.getResultList();
        for (int i = 0; i < objList.size(); i++) {
            MemCreditRuleDTO mcrDto = new MemCreditRuleDTO();
            Object[] obj = objList.get(i);
            mcrDto.setCreditId(Integer.valueOf(obj[0].toString()));
            //mcrDto.setConsPoints(new BigDecimal(obj[1].toString()));
            mcrDto.setGradeId(Integer.valueOf(obj[2].toString()));
            mcrDto.setGradeName(obj[3].toString());
            mcrDto.setGradePoints(new BigDecimal(obj[4].toString()));
            mcrDto.setUpperLimit(new BigDecimal(obj[5].toString()));
            mcrDto.setLoginPoints(new BigDecimal(obj[6].toString()));
            mcrDto.setPointsRate(new BigDecimal(obj[7].toString()));
            mcrDto.setStatus(Integer.valueOf(obj[8].toString()));
            mcrDto.setCreateTime(obj[9].toString());
            list.add(mcrDto);
        }

        //queryResult.setTotalrecord(result.getTotalrecord());
        queryResult.setResultlist(list);
        return queryResult;
    }

    /**
     *@Description:根据会员等级id获取会员等级
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    private String getGradeName(Integer gradeId) {
        String gradeName = null;
        if (gradeId != null) {
            gradeName = (String) em.createNativeQuery(
                    "select m.gradeName from m_memgrade m where m.gradeId =" + gradeId).getSingleResult();
        }
        return gradeName;
    }

    /**
     *@Description:生成积分规则Id
     *@return:Integer
     *@author: 张亚运
     *@throws:
     */
    @SuppressWarnings("unchecked")
    public Integer getCreditId() {
        Integer creditId = 0;
        String sql = "select m.creditId from m_memcreditrule m order by m.creditId desc";
        List<Object[]> objlist = new ArrayList<Object[]>();
        objlist = em.createNativeQuery(sql.toString()).getResultList();

        if (objlist.size() != 0) {
            Object obj = objlist.get(0);
            creditId = Integer.parseInt(obj.toString());
            creditId = creditId + 1;
        }
        else {
            creditId = 10000000;
        }
        return creditId;
    }

    /**
     *@Description:添加保存会员积分规则
     *@param:@param memCreditDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public synchronized MemCreditRule saveMemCredit(MemCreditRuleDTO memCreditDto) {
        MemCreditRule memCredit = new MemCreditRule();
        UserSession us = Utils.getUserSession();
        if (memCreditDto != null) {
            memCredit.setCreditId(this.getCreditId());
            memCredit.setGradeId(memCreditDto.getGradeId());
            memCredit.setConsPoints(memCreditDto.getConsPoints());
            memCredit.setLoginPoints(memCreditDto.getLoginPoints());
            memCredit.setPointsRate(memCreditDto.getPointsRate());
            memCredit.setGradePoints(memCreditDto.getGradePoints());
            memCredit.setUpperLimit(memCreditDto.getUpperLimit());
            memCredit.setStatus(memCreditDto.getStatus());
            memCredit.setCreateTime(this.getSysTime());
            memCredit.setOperator(us.getUserName());
            memCredit.setUpdateTime(this.getSysTime());
            this.save(memCredit);
        }
        return memCredit;
    }

    /**
     *@Description:获取会员等级<OptionsValue>
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    @SuppressWarnings("unchecked")
    public List<OptionsInteger> getGradeOption() {
        List<MemGrade> mgList = em.createQuery("from MemGrade o where o.status = 1 order by o.gradeId asc")
                .getResultList();
        List<OptionsInteger> list = new ArrayList<OptionsInteger>();
        for (MemGrade mg : mgList) {
            list.add(new OptionsInteger(mg.getGradeId(), mg.getGradeName()));
        }
        return list;
    }

    /**
     *@Description:修改保存积分规则
     *@param:@param memCreditDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO updateMemCredit(MemCreditRuleDTO memCreditDto) {
        UserSession us = Utils.getUserSession();
        ReturnDTO returnDto = new ReturnDTO();
        MemCreditRule mdr = this.find(memCreditDto.getCreditId());
        if (mdr != null) {
            mdr.setGradeId(memCreditDto.getGradeId());
            //mdr.setConsPoints(memCreditDto.getConsPoints());
            mdr.setLoginPoints(memCreditDto.getLoginPoints());
            mdr.setPointsRate(memCreditDto.getPointsRate());
            mdr.setGradePoints(memCreditDto.getGradePoints());
            mdr.setUpperLimit(memCreditDto.getUpperLimit());
            mdr.setStatus(memCreditDto.getStatus());
            mdr.setOperator(us.getUserName());
            mdr.setUpdateTime(this.getSysTime());
            returnDto.setFlag(true);
        }
        return returnDto;
    }

    /**
     *@Description:检验该会员等级的积分规则是否存在
     *@param:@param gradeId
     *@param:@param creditId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public boolean validateNeme(Integer gradeId, Integer creditId) {
        long count;
        if (creditId == null) {
            count = (Long) em.createQuery("select count(m) from MemCreditRule m where m.gradeId = ?1").setParameter(1,
                    gradeId).getSingleResult();
        }
        else {
            count = (Long) em.createQuery(
                    "select count(m) from MemCreditRule m where m.gradeId = ?1 and m.creditId<>?2").setParameter(1,
                    gradeId).setParameter(2, creditId).getSingleResult();
        }
        return count > 0;
    }

}
