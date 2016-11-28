package com.paySystem.ic.service.message.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.message.MessParamRelation;
import com.paySystem.ic.dao.message.MessParamRelationDAO;
import com.paySystem.ic.service.message.MessParamRelationService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.message.MessageDTO;

@Service(MessParamRelationService.MESSPARAMRELATIONSERVICE)
public class MessParamRelationServiceImpl implements MessParamRelationService {

    @Resource
    MessParamRelationDAO messParamRelationDao;

    /**
     *@Title:deleMessParamRelation
     *@Description:删除参数使用关系
     *@param:@return
     *@return:
     *@author:张亚运
     */
    @Override
    public void deleteMessParamRelation(String mprId) {
        MessParamRelation mpr = messParamRelationDao.find(mprId);
        mpr.setState(9);
        messParamRelationDao.update(mpr);
    }

    /**
     *@Title:queryAll
     *@Description:参数使用关系查询
     *@param:@return
     *@return:List<MessageDTO>
     *@author:张亚运
     */
    @Override
    public QueryResult<MessageDTO> queryAll(int page, int pageNum, MessageDTO messageDto,
            LinkedHashMap<String, String> orderBy) throws Exception {
        return messParamRelationDao.queryAll(page, pageNum, messageDto, orderBy);
    }

    /**
     *@Title:saveMessParamRelation
     *@Description:保存参数使用关系
     *@param:@return
     *@return:MessParamRelation
     *@author:张亚运
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public MessParamRelation saveMessParamRelation(MessageDTO messagedto) {
        MessParamRelation mpr = new MessParamRelation();
        mpr = messParamRelationDao.saveMessParamRelation(messagedto);
        return mpr;
    }

    /**
     *@Title:updateMessParamRelation
     *@Description:修改保存参数使用关系
     *@param:@return
     *@return:ReturnDTO
     *@author:张亚运
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ReturnDTO updateMessParamRelation(MessageDTO messageDto) {
        ReturnDTO returnDto = new ReturnDTO();
        returnDto = messParamRelationDao.updateMessParamRelation(messageDto);
        return returnDto;
    }

    /**
     *@Title:queryById
     *@Description:根据ID参数使用关系
     *@param:@return
     *@return:MessageDTO
     *@author:张亚运
     */
    @Override
    public MessageDTO queryById(String mprId) {
        MessageDTO dto = new MessageDTO();
        dto = messParamRelationDao.queryById(mprId);
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
    @Override
    public boolean queryRelation(String orgMerId, String mprId) {
        return messParamRelationDao.queryRelation(orgMerId, mprId);
    }

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.message.MessParamRelationService#queryByOrgId()
	 *@MethodName: queryByOrgId
	 *@Description: 根据机构ID参数使用关系
	 *@return
	 *@Author: 廖晓远 
	 *@Date: 2014-12-10上午10:40:03
	 */
	@Override
	public MessageDTO queryByOrgId() {
		MessageDTO dto = new MessageDTO();
        dto = messParamRelationDao.queryByOrgId();
        return dto;
    }

}
