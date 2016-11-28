package com.paySystem.ic.dao.message.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.message.MessFeeParam;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.message.MessFeeParamDAO;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.message.MessageDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:MessFeeParamDaoImpl
 * @Description:短信参数Dao的实现类
 * @date: 2014-3-19下午06:20:14
 * @author: 张亚运
 * @version: V1.0
 */
@Repository(MessFeeParamDAO.MESSFEEPARAMDAO)
public class MessFeeParamDaoImpl extends DaoSupport<MessFeeParam> implements MessFeeParamDAO {

    /**
     *@Title:queryAll
     *@Description:查询短信参数记录
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

    @Override
    public QueryResult<MessageDTO> queryAll(int page, int pageNum, MessageDTO messageDto,
            LinkedHashMap<String, String> orderBy) throws Exception {
        UserSession us = Utils.getUserSession();
        QueryResult<MessageDTO> queryList = new QueryResult<MessageDTO>();

        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        if (us.getUserLevel() == 0) {
            sql.append(" and o.orgMerSign = 0");
        }
        else {
            sql.append(" and o.orgMerSign = 1");
        }
        switch (messageDto.getHelpSign()) {
        case 0:
            if (StringUtils.isNotBlank(messageDto.getMessName())) {
                sql.append(" and o.messName like ?").append(params.size() + 1);
                params.add("%" + messageDto.getMessName().trim() + "%");
            }
            if (messageDto.getMessType() != -1) {
                sql.append(" and o.messType = " + messageDto.getMessType());
            }
            if (messageDto.getUseSign() != -1) {
                sql.append(" and o.useSign = " + messageDto.getUseSign());
            }
            if (StringUtils.isNotBlank(messageDto.getBeginTime())) {
                sql.append(" and str_to_date(date_format(o.updateTime,'%Y-%m-%d'),'%Y-%m-%d') " + ">=str_to_date('"
                        + messageDto.getBeginTime() + "','%Y-%m-%d')");
            }
            if (StringUtils.isNotBlank(messageDto.getEndTime())) {
                sql.append(" and str_to_date(date_format(o.updateTime,'%Y-%m-%d'),'%Y-%m-%d') " + "<=str_to_date('"
                        + messageDto.getEndTime() + "','%Y-%m-%d')");
            }
            break;
        case 1:
            if (StringUtils.isNotBlank(messageDto.getMfpId())) {
                sql.append(" and o.mfpId like ?").append(params.size() + 1);
                params.add("%" + messageDto.getMfpId().trim() + "%");
            }
            if (StringUtils.isNotBlank(messageDto.getMessName())) {
                sql.append(" and o.messName like ?").append(params.size() + 1);
                params.add("%" + messageDto.getMessName().trim() + "%");
            }
            sql.append(" and o.useSign = 1");
            break;
        }

        QueryResult<MessFeeParam> queryResult = getScrollData(page, pageNum, sql.toString(), params.toArray(), orderBy);
        List<MessFeeParam> listEntity = queryResult.getResultlist();
        List<MessageDTO> listDtos = new ArrayList<MessageDTO>();
        for (int i = 0; i < listEntity.size(); i++) {
            MessageDTO messageDTO = new MessageDTO();
            try {
                messageDTO = (MessageDTO) EntityDtoConverter.bean2Dto(listEntity.get(i), messageDTO);
                listDtos.add(messageDTO);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        /** 赋值QueryResult<MessageDTO>*/
        queryList.setResultlist(listDtos);
        queryList.setTotalrecord(queryResult.getTotalrecord());
        return queryList;
    }

    /**
     *@Title:createMfpID
     *@Description:新建ID
     *@param:@return
     *@return:ID
     *@author:张亚运
     *@thorws:
     */
    public String createMfpID() {
        String messFeeParamId = Utils.createSerialNum(em, "mfpId", "MessFeeParam", 8, 0, null, null, null);
        return messFeeParamId;
    }

    /**
     *@Title:saveMessFeeparam
     *@Description:保存参数信息
     *@param:@param MessageDTO
     *@param:@return
     *@return:null
     *@author:张亚运
     *@thorws:
     */
    @Override
    public MessFeeParam saveMessFeeParam(MessageDTO messagedto) {
        UserSession us = Utils.getUserSession();
        String mfpId = this.createMfpID();
        MessFeeParam mfp = new MessFeeParam();
        /**封装实体 singleRelation*/
        mfp.setMfpId(mfpId);
        mfp.setMessName(messagedto.getMessName());
        mfp.setMessType(messagedto.getMessType());
        mfp.setMessFee(messagedto.getMessFee());
        mfp.setMessPeriod(messagedto.getMessPeriod());
        mfp.setUseSign(messagedto.getUseSign());
        if (us.getUserLevel() == 0) {
            mfp.setOrgMerSign(0);
        }
        else {
            mfp.setOrgMerSign(1);
        }
        mfp.setProposer(us.getUserName());
        mfp.setUpdateTime(this.getSysTime());
        mfp.setMfpDesc(messagedto.getMfpDesc());
        this.save(mfp);

        return null;
    }

    /**
     *@Title:updateMessFeeParam
     *@Description:更新修改后的数据
     *@param:@param MessageDTO
     *@param:@return
     *@return:ReturnDTO
     *@author:张亚运
     *@thorws:
     */
    @Override
    public ReturnDTO updateMessFeeParam(MessageDTO messagedto) {
        ReturnDTO returnDTO = new ReturnDTO();
        //获得要更新的数据
        MessFeeParam mfp = this.find(messagedto.getMfpId());

        UserSession us = Utils.getUserSession();
        if (mfp != null) {
            mfp.setMfpId(messagedto.getMfpId());
            mfp.setMessName(messagedto.getMessName());
            mfp.setMessType(messagedto.getMessType());
            if (messagedto.getMessType() == 0) {
                mfp.setMessFee(messagedto.getSingleFee());
                mfp.setMessPeriod(messagedto.getMiniPeriod());
            }
            if (messagedto.getMessType() == 1) {
                mfp.setMessFee(messagedto.getMessFee());
                mfp.setMessPeriod(messagedto.getMessPeriod());
            }
            mfp.setUseSign(messagedto.getUseSign());
            if (us.getUserLevel() == 0) {
                mfp.setOrgMerSign(0);
            }
            else {
                mfp.setOrgMerSign(1);
            }
            mfp.setProposer(us.getUserName());
            mfp.setUpdateTime(this.getSysTime());
            mfp.setMfpDesc(messagedto.getMfpDesc());
            this.update(mfp);
            returnDTO.setFlag(true);
        }
        return returnDTO;
    }

    /**
     *@Title:queryMessName
     *@Description:查询关系是否存在
     *@param:@param messName
     *@param:@return
     *@return:boolean
     *@author:张亚运
     *@thorws:
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean queryMessName(String messName, String mfpId) {
        List<MessFeeParam> mfp = null;
        if (mfpId.equals("")) {
            StringBuilder sql = new StringBuilder(" select m from MessFeeParam m where m.messName = ?1");
            Query query = em.createQuery(sql.toString()).setParameter(1, messName);
            mfp = query.getResultList();
        }
        else {
            StringBuilder sql = new StringBuilder(
                    " select m from MessFeeParam m where m.messName = ?1 and m.mfpId<>?2 ");
            Query query = em.createQuery(sql.toString()).setParameter(1, messName).setParameter(2, mfpId);
            mfp = query.getResultList();
        }
        return mfp.size() < 1;
    }

}
