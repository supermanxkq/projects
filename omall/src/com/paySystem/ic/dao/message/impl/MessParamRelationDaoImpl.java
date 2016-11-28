package com.paySystem.ic.dao.message.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.message.MessParamRelation;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.message.MessParamRelationDAO;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.message.MessageDTO;
import com.paySystem.ic.web.dto.system.UserSession;

@Repository(MessParamRelationDAO.MESSPARAMRALATIONDAO)
public class MessParamRelationDaoImpl extends DaoSupport<MessParamRelation> implements MessParamRelationDAO {

    /**
     *@Title:queryAll
     *@Description:查询参数使用关系
     *@param:@param page
     *@param:@param pageNum
     *@param:@param messageDto
     *@param:@param orderBy
     *@param:@return
     *@param:@throws Exception
     *@return:List<MessageDTO>
     *@author:张亚运
     *@thorws:
     */
    @SuppressWarnings("unchecked")
    @Override
    public QueryResult<MessageDTO> queryAll(int page, int pageNum, MessageDTO messageDto,
            LinkedHashMap<String, String> orderBy) throws Exception {
        UserSession us = Utils.getUserSession();
        List<MessageDTO> messdtolist = new ArrayList<MessageDTO>();
        List<Object> params = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder("select mp.mprid,mf.messname,mf.messtype,mf.messperiod,");
        sql.append("mf.messfee,mp.state,mp.begintime,mp.endtime,mp.proposer,mp.updatetime");
        if (us.getUserLevel() == 0) {
            sql.append(",o.name as orgname from b_messparamrelation mp");
            sql.append(" left join b_organs o on mp.orgmerid = o.organid");
        }
        else {
            sql.append(",m.mername as mername from b_messparamrelation mp");
            sql.append(" left join b_merchants m on mp.orgmerid = m.merid");
        }
        sql.append(" left join b_messfeeparam mf on mp.mfpid = mf.mfpid");
        sql.append(" where 1=1");

        if (us.getUserLevel() == 0) {
            sql.append(" and mp.parentorgid = 0");
        }
        else {
            sql.append(" and mp.parentorgid = " + us.getOrganId());
        }
        if (us.getUserLevel() == 0 && StringUtils.isNotBlank(messageDto.getOrgName())) {
            sql.append(" and o.name like ?").append(params.size() + 1);
            params.add("%" + messageDto.getOrgName().trim() + "%");
        }
        if (us.getUserLevel() == 1 && StringUtils.isNotBlank(messageDto.getMerName())) {
            sql.append(" and m.mername like ?").append(params.size() + 1);
            params.add("%" + messageDto.getMerName().trim() + "%");
        }
        if (StringUtils.isNotBlank(messageDto.getMessName())) {
            sql.append(" and mf.messname like ?").append(params.size() + 1);
            params.add("%" + messageDto.getMessName().trim() + "%");
        }

        if (messageDto.getMessType() != -1) {
            sql.append(" and mf.messtype = " + messageDto.getMessType());
        }
        if (messageDto.getState() != -1) {
            sql.append(" and mp.state = " + messageDto.getState());
        }
        if (StringUtils.isNotBlank(messageDto.getBeginTime())) {
            sql.append(" and str_to_date(date_format(mp.updatetime,'%Y-%m-%d'),'%Y-%m-%d') " + ">=str_to_date('"
                    + messageDto.getBeginTime() + "','%Y-%m-%d')");
        }
        if (StringUtils.isNotBlank(messageDto.getEndTime())) {
            sql.append(" and str_to_date(date_format(mp.updatetime,'%Y-%m-%d'),'%Y-%m-%d') " + "<=str_to_date('"
                    + messageDto.getEndTime() + "','%Y-%m-%d')");
        }
        //sql.append(" order by mp.updatetime desc");
        QueryResult<MessageDTO> queryResult = new QueryResult<MessageDTO>();
        List<Object[]> objlist = new ArrayList<Object[]>();
        Query query = queryParam(sql.toString(), params.toArray());
        queryResult.setTotalrecord(query.getResultList().size());
        query.setFirstResult(page);
        query.setMaxResults(pageNum);

        try {
            objlist = query.getResultList();
        }
        catch (Exception e) {
            e.getMessage();
        }

        MessageDTO messagedto = null;
        for (int i = 0; i < objlist.size(); i++) {
            messagedto = new MessageDTO();
            Object[] obj = objlist.get(i);
            if (obj[0] != null) {
                messagedto.setMprId(obj[0].toString());
            }
            else {
                messagedto.setMprId("");
            }
            if (obj[1] != null) {
                messagedto.setMessName(obj[1].toString());
            }
            else {
                messagedto.setMessName("");
            }
            if (obj[2] != null) {
                messagedto.setMessType(Integer.parseInt(obj[2].toString()));
            }
            else {
                messagedto.setMessType(0);
            }
            if (obj[3] != null) {
                messagedto.setMessPeriod(Integer.parseInt(obj[3].toString()));
            }
            else {
                messagedto.setMessPeriod(0);
            }
            if (obj[4] != null) {
                messagedto.setMessFee(new BigDecimal(obj[4].toString()));
            }
            else {
                messagedto.setMessFee(new BigDecimal(0.00));
            }
            messagedto.setState(Integer.parseInt(obj[5].toString()));
            messagedto.setBeginTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", (Date) obj[6]));
            messagedto.setEndTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", (Date) obj[7]));
            messagedto.setProposer(obj[8].toString());
            messagedto.setUpdateTime((Date) obj[9]);
            if (obj[10] != null) {
                messagedto.setOrgMerName(obj[10].toString());
            }
            else {
                messagedto.setOrgMerName("");
            }
            messdtolist.add(messagedto);
        }
        queryResult.setResultlist(messdtolist);
        return queryResult;
    }

    /**
     *@Title:updateMessParamRelation
     *@Description:保存修改后的数据	 
     *@param:@return
     *@return:ReturnDTO
     *@author:张亚运
     *@thorws:
     */
    @Override
    public ReturnDTO updateMessParamRelation(MessageDTO messagedto) {
        ReturnDTO returnDTO = new ReturnDTO();
        //获得要更新的数据
        MessParamRelation mpr = this.find(messagedto.getMprId());
        UserSession us = Utils.getUserSession();
        if (mpr != null) {
            mpr.setMprId(messagedto.getMprId());
            mpr.setMfpId(messagedto.getMfpId());

            if (us.getUserLevel().equals(0)) {
                mpr.setParentOrgId("0");
                mpr.setOrgMerId(messagedto.getOrgId());
            }
            else {
                mpr.setParentOrgId(us.getOrganId());
                mpr.setOrgMerId(messagedto.getMerId());
            }
            mpr.setState(messagedto.getState());
            mpr.setBeginTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", messagedto.getBeginTime()));
            mpr.setEndTime(DateTimeTool.nMonthsAfter(messagedto.getUseLives(), mpr.getBeginTime()));
            mpr.setProposer(us.getUserName());
            if (messagedto.getMfpDesc() != null) {
                mpr.setMprDesc(messagedto.getMfpDesc());
            }
            mpr.setUpdateTime(this.getSysTime());
            this.update(mpr);
            returnDTO.setFlag(true);
        }
        return returnDTO;
    }

    /**
     *@Title:createMprID
     *@Description:生成ID	 
     *@param:@return
     *@return:messParamRelationId
     *@author:张亚运
     *@thorws:
     */
    public String createMprID() {
        String messParamRelationId = Utils.createSerialNum(em, "mprId", "MessParamRelation", 8, 0, null, null, null);
        return messParamRelationId;
    }

    /**
     *@Title:saveMessParamRelation
     *@Description:保存参数使用信息方法	 
     *@param:@return
     *@return:
     *@author:张亚运
     *@thorws:
     */
    @Override
    public MessParamRelation saveMessParamRelation(MessageDTO messagedto) {
        UserSession us = Utils.getUserSession();
        String mprId = this.createMprID();
        MessParamRelation mpr = new MessParamRelation();

        /**封装实体*/
        mpr.setMprId(mprId);
        mpr.setMfpId(messagedto.getMfpId());
        if (us.getUserLevel().equals(0)) {
            mpr.setParentOrgId("0");
            mpr.setOrgMerId(messagedto.getOrgId());
        }
        else {
            mpr.setParentOrgId(us.getOrganId());
            mpr.setOrgMerId(messagedto.getMerId());
        }
        mpr.setState(messagedto.getState());
        mpr.setBeginTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", messagedto.getBeginTime()));
        mpr.setEndTime(DateTimeTool.nMonthsAfter(messagedto.getUseLives(), mpr.getBeginTime()));
        mpr.setProposer(us.getUserName());
        mpr.setMprDesc(messagedto.getMfpDesc());
        mpr.setUpdateTime(this.getSysTime());
        this.save(mpr);
        return null;
    }

    /**
     *@Title:queryById
     *@Description:根据ID查询参数使用信息	 
     *@param:@return
     *@return:MessageDTO
     *@author:张亚运
     *@thorws:
     */
    @Override
    public MessageDTO queryById(String mprId) {
        UserSession us = Utils.getUserSession();
        MessageDTO dto = new MessageDTO();
        StringBuilder sb = new StringBuilder("select mp.mprid,mp.mfpid,mf.messname,mp.orgmerid,mp.state,");
        sb.append("mp.begintime,mp.endtime,mp.mprdesc");
        if (us.getUserLevel() == 0) {
            sb.append(",o.name as orgname from b_messparamrelation mp");
            sb.append(" left join b_organs o on mp.orgmerid = o.organid");
        }
        else {
            sb.append(",m.mername as mername from b_messparamrelation mp");
            sb.append(" left join b_merchants m on mp.orgmerid = m.merid");
        }
        sb.append(" left join b_messfeeparam mf on mp.mfpid = mf.mfpid");
        sb.append(" where 1=1 and mp.mprid=" + mprId);
        Object[] obj = (Object[]) em.createNativeQuery(sb.toString()).getSingleResult();
        if (obj != null) {
            dto.setMprId(obj[0].toString());
            if (obj[1] != null) {
                dto.setMfpId(obj[1].toString());
            }
            else {
                dto.setMfpId("");
            }
            if (obj[2] != null) {
                dto.setMessName(obj[2].toString());
            }
            else {
                dto.setMessName("");
            }
            if (obj[3] != null) {
                dto.setOrgId(obj[3].toString());
                dto.setMerId(obj[3].toString());
            }
            else {
                dto.setOrgId("");
                dto.setMerId("");
            }
            dto.setState(Integer.parseInt(obj[4].toString()));
            dto.setBeginTime(obj[5].toString());
            dto.setEndTime(obj[6].toString());
            if (obj[7] != null) {
                dto.setMfpDesc(obj[7].toString());
            }
            if (obj[8] != null) {
                dto.setOrgName(obj[8].toString());
                dto.setMerName(obj[8].toString());
            }
            else {
                dto.setOrgName("");
                dto.setMerName("");
            }

        }
        return dto;
    }

    /**
     *@Title:queryRelation
     *@Description:查询关系是否存在
     *@param:@param orgMerId
     *@param:@return
     *@return:boolean
     *@author:张亚运
     *@thorws:
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean queryRelation(String orgMerId, String mprId) {
        List<MessParamRelation> mpr = null;
        if (mprId.equals("")) {
            StringBuilder sql = new StringBuilder(" select m from MessParamRelation m where m.state = 1"
                    + " and m.orgMerId = '" + orgMerId + "'");
            Query query = em.createQuery(sql.toString());
            mpr = query.getResultList();
        }
        else {
            StringBuilder sql = new StringBuilder(" select m from MessParamRelation m where m.state = 1"
                    + " and m.orgMerId = '" + orgMerId + "' and m.mprId <> '" + mprId + "'");
            Query query = em.createQuery(sql.toString());
            mpr = query.getResultList();
        }
        return mpr.size() < 1;
    }

    /**
     *@Description:设置参数查询方法
     *@return:Query
     *@author: 张亚运
     *@throws:
     */
    public Query queryParam(String wherejpql, Object[] queryParams) throws Exception {
        Query query = em.createNativeQuery(wherejpql);
        if (queryParams != null && queryParams.length > 0) {
            for (int i = 0; i < queryParams.length; i++) {
                query.setParameter(i + 1, queryParams[i]);
            }
        }
        return query;
    }
    
    /**
     *@Title:queryById
     *@Description:根据机构ID查询参数使用信息	 
     *@param:@return
     *@return:MessageDTO
     *@author:张亚运
     *@thorws:
     */
    @SuppressWarnings("unchecked")
	@Override
    public MessageDTO queryByOrgId() {
        UserSession us = Utils.getUserSession();
        MessageDTO dto = new MessageDTO();
        StringBuilder sb = new StringBuilder("select mp.mprid,mp.mfpid,mf.messFee,mp.orgmerid,mp.state,");
        sb.append("mp.begintime,mp.endtime,mp.mprdesc");
        if (us.getUserLevel() == 0) {
            sb.append(",o.name as orgname, mf.messType from b_messparamrelation mp");
            sb.append(" left join b_organs o on mp.orgmerid = o.organid");
        }
        else {
            sb.append(",m.mername as mername from b_messparamrelation mp");
            sb.append(" left join b_merchants m on mp.orgmerid = m.merid");
        }
        sb.append(" left join b_messfeeparam mf on mp.mfpid = mf.mfpid");
        sb.append(" where 1=1 and mp.orgMerId=" + us.getOrganId());
        List<Object[]> list =  em.createNativeQuery(sb.toString()).getResultList();
        Object[] obj = null;
        if(list.size()>0)
        	obj = list.get(0);
        if (obj != null) {
            dto.setMprId(obj[0].toString());
            if (obj[1] != null) {
                dto.setMfpId(obj[1].toString());
            }
            else {
                dto.setMfpId("");
            }
            if (obj[2] != null) {
                dto.setMessFee(new BigDecimal(obj[2].toString()));
            }
            else {
                dto.setMessFee(new BigDecimal("0"));
            }
            if (obj[3] != null) {
                dto.setOrgId(obj[3].toString());
                dto.setMerId(obj[3].toString());
            }
            else {
                dto.setOrgId("");
                dto.setMerId("");
            }
            dto.setState(Integer.parseInt(obj[4].toString()));
            dto.setBeginTime(obj[5].toString());
            dto.setEndTime(obj[6].toString());
            if (obj[7] != null) {
                dto.setMfpDesc(obj[7].toString());
            }
            if (obj[8] != null) {
                dto.setOrgName(obj[8].toString());
                dto.setMerName(obj[8].toString());
            }
            else {
                dto.setOrgName("");
                dto.setMerName("");
            }
            
            if (obj[9] != null) {
                dto.setMessType(Integer.parseInt(obj[9].toString()));
            }

        }
        return dto;
    }
}
