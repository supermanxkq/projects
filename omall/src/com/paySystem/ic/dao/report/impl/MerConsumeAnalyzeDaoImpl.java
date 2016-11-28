package com.paySystem.ic.dao.report.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.report.Trades;
import com.paySystem.ic.dao.base.OrgansDao;
import com.paySystem.ic.dao.report.MerConsumeAnalyzeDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.report.MerConsumeAnalyzeDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/***
 * 商户消费分析报表Dao实现
 * 
 * @author 解文侠
 * 
 */

@Repository(MerConsumeAnalyzeDao.MERCONSUMEANALYZEDAO)
public class MerConsumeAnalyzeDaoImpl extends DaoSupport<Trades> implements MerConsumeAnalyzeDao {
    @Resource
    OrgansDao organsDao;

    /***
     * 商户消费分析报表查询
     * 
     * @see com.paySystem.ic.dao.report.MerConsumeAnalyzeDao#queryAll(int, int,
     *      com.paySystem.ic.web.dto.report.MerConsumeAnalyzeDTO,
     *      java.util.LinkedHashMap)
     * @Description:TODO
     * @date: 2014-4-9上午09:51:47
     * @author: 解文侠
     * @version: V1.0
     * @param page
     * @param pageNum
     * @param merConsumeAnalyzeDTo
     * @param orderBy
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<MerConsumeAnalyzeDTO> queryAll(int page, int pageNum, MerConsumeAnalyzeDTO merConsumeAnalyzeDTo,
            LinkedHashMap<String, String> orderBy) throws Exception {
        UserSession us = Utils.getUserSession();
        List<MerConsumeAnalyzeDTO> merConsumeAnalyzeList = new ArrayList<MerConsumeAnalyzeDTO>();
        StringBuilder sb = new StringBuilder();

        if (merConsumeAnalyzeDTo.getStatus() == 0) {
            sb.append("select t.merid,b.mername,to_char(t.placedtime,'yyyy') as 对应时间,"
                    + "to_char(t.placedtime,'q') as 季度,sum(t.tradeamt) as 消费金额,count(t.merid) as counts ");

        }
        if (merConsumeAnalyzeDTo.getStatus() == 1) {
            sb.append("select t.merid,b.mername,to_char(t.placedtime,'yyyy-mm') as 对应时间,"
                    + "to_char(t.placedtime,'mm') as 月份,sum(t.tradeamt) as 消费金额 ," + "count(t.merid) as counts");

        }
        if (merConsumeAnalyzeDTo.getStatus() == 2) {
            sb.append("select t.merid,b.mername,to_char(t.placedtime,'yyyy-mm-dd') as 对应时间,"
                    + "to_char(t.placedtime,'dd') as 天,sum(t.tradeamt) as 消费金额 ," + "count(t.merid) as counts ");

        }
        sb.append(" from t_trades t,b_merchants b");
        sb.append(" where b.merid=t.merid");
        if (us.getUserLevel() != 2) {
            if (StringUtils.isNotBlank(merConsumeAnalyzeDTo.getMerId())) {
                sb.append(" and b.merid = '" + merConsumeAnalyzeDTo.getMerId() + "'");
            }
        }
        if (us.getUserLevel() == 2) {// 如果登录为商户则直接按商户id查询
            sb.append(" and t.merid = " + us.getMerId());// 如果登录为商户则直接按商户id查询
        }
        if (StringUtils.isNotBlank(merConsumeAnalyzeDTo.getBeginDate())) {
            sb.append(" and t.placedtime >=to_timestamp('" + merConsumeAnalyzeDTo.getBeginDate()
                    + "','yyyy-mm-dd hh24:mi:ss')");
        }
        if (StringUtils.isNotBlank(merConsumeAnalyzeDTo.getEndDate())) {
            sb.append(" and t.placedtime <=to_timestamp('" + merConsumeAnalyzeDTo.getEndDate()
                    + "','yyyy-mm-dd hh24:mi:ss')");
        }
        if (merConsumeAnalyzeDTo.getStatus() == 0) {
            sb.append(" group by t.merid,b.mername,to_char(t.placedtime,'yyyy'),to_char(t.placedtime,'q')");
            sb.append(" order by to_char(t.placedtime,'yyyy')");
        }
        if (merConsumeAnalyzeDTo.getStatus() == 1) {
            sb.append(" group by t.merid,b.mername,to_char(t.placedtime,'yyyy-mm'),to_char(t.placedtime,'mm')");
            sb.append(" order by to_char(t.placedtime,'yyyy-mm')");
        }
        if (merConsumeAnalyzeDTo.getStatus() == 2) {
            sb.append(" group by t.merid,b.mername,to_char(t.placedtime,'yyyy-mm-dd'),to_char(t.placedtime,'dd')");
            sb.append(" order by to_char(t.placedtime,'yyyy-mm-dd')");
        }
        /*else{
        	sb.append(" group by t.merid,b.mername");
        }*/

        System.out.println(sb);
        // 交易报表条件

        List<Object[]> merConsumeList = new ArrayList<Object[]>();
        try {
            merConsumeList = em.createNativeQuery(sb.toString()).getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        MerConsumeAnalyzeDTO merConsume = null;
        for (int i = 0; i < merConsumeList.size(); i++) {
            merConsume = new MerConsumeAnalyzeDTO();
            Object[] merConsObj = merConsumeList.get(i);
            merConsume.setMerId(merConsObj[0].toString());
            merConsume.setMerRealName(merConsObj[1].toString());
            merConsume.setStringDate(merConsObj[2].toString());
            merConsume.setYearly(merConsObj[3].toString());
            merConsume.setTradeAmt((BigDecimal) merConsObj[4]);
            merConsume.setTradesNum((BigDecimal) merConsObj[5]);
            merConsumeAnalyzeList.add(merConsume);
        }
        return merConsumeAnalyzeList;
    }

    /***
     * 导出报表调用
     * 
     * @see com.paySystem.ic.dao.report.MerConsumeAnalyzeDao#queryMerConsumeAnalyzeReport(com.paySystem.ic.web.dto.report.MerConsumeAnalyzeDTO,
     *      java.util.LinkedHashMap)
     * @Description:TODO
     * @date: 2014-4-9上午09:53:40
     * @author: 解文侠
     * @version: V1.0
     * @param merConsumeAnalyze
     * @param orderBy
     * @return
     * @throws Exception
     */
    public List<MerConsumeAnalyzeDTO> queryMerConsumeAnalyzeReport(MerConsumeAnalyzeDTO merConsumeAnalyze,
            LinkedHashMap<String, String> orderBy) throws Exception {
        return queryAll(-1, -1, merConsumeAnalyze, orderBy);
    }
}
