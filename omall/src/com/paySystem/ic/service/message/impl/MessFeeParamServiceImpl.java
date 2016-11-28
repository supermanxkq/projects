package com.paySystem.ic.service.message.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.message.MessFeeParam;
import com.paySystem.ic.dao.message.MessFeeParamDAO;
import com.paySystem.ic.service.message.MessFeeParamService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.message.MessageDTO;

@Service(MessFeeParamService.MESSFEEPARAMSERVICE)
public class MessFeeParamServiceImpl implements MessFeeParamService {

    @Resource
    MessFeeParamDAO messFeeParamDao;

    /**
     *@Title:queryAll
     *@Description:短信参数查询Service
     *@param:@return
     *@return:List<MessageDTO>
     *@author:张亚运
     */
    @Override
    public QueryResult<MessageDTO> queryAll(int page, int pageNum, MessageDTO messageDto,
            LinkedHashMap<String, String> orderBy) throws Exception {
        return messFeeParamDao.queryAll(page, pageNum, messageDto, orderBy);
    }

    /**
     *@Title:deleteMessFeeParam
     *@Description:删除短信参数
     *@param:@return
     *@return:
     *@author:张亚运
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteMessFeeParam(String mfpId) {
        MessFeeParam mfp = messFeeParamDao.find(mfpId);
        mfp.setUseSign(9);
        messFeeParamDao.update(mfp);
    }

    /**
     *@Title:find
     *@Description:查询要修改的短信参数
     *@param:@return
     *@return:MessFeeParam
     *@author:张亚运
     */
    @Override
    public MessFeeParam find(String mfpId) {
        MessFeeParam mfp = messFeeParamDao.find(mfpId);
        return mfp;
    }

    /**
     *@Title:updateMessFeeParam
     *@Description:修改保存短信参数
     *@param:@return
     *@return:ReturnDTO
     *@author:张亚运
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ReturnDTO updateMessFeeParam(MessageDTO messagedto) {
        ReturnDTO returnDto = new ReturnDTO();
        returnDto = messFeeParamDao.updateMessFeeParam(messagedto);
        return returnDto;
    }

    /**
     *@Title:saveMessFeeParam
     *@Description:保存短信参数
     *@param:@return
     *@return:MessFeeParam
     *@author:张亚运
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public MessFeeParam saveMessFeeParam(MessageDTO messagedto) {
        MessFeeParam mfp = new MessFeeParam();
        mfp = messFeeParamDao.saveMessFeeParam(messagedto);
        return mfp;
    }

    @Override
    public boolean queryMessName(String messName, String mfpId) {
        return messFeeParamDao.queryMessName(messName, mfpId);
    }

}
