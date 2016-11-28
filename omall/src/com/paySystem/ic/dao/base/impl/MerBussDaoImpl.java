package com.paySystem.ic.dao.base.impl;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.base.MerBussParam;
import com.paySystem.ic.dao.base.MerBussDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.base.MerchantsDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * 
 * @ClassName:MerBussDaoImpl
 * @Description:TODO
 * @date: 2014-7-4上午11:06:17
 * @author: 谢洪飞
 * @version: V1.0
 */
@Repository(MerBussDao.MERBUSSDAO)
public class MerBussDaoImpl extends DaoSupport<MerBussParam> implements MerBussDao {

    public void saveMerBuss(MerchantsDTO merDto) {

        MerBussParam merBussParam = getMerBussParam(merDto);

        this.save(merBussParam);
    }

    /**
     *@Title:getMerBussParam
     *@Description: 根据商户Dto对象获取 商户业务参数实体对象
     *@param:@param merDto 商户Dto对象
     *@return:MerBussParam 商户业务参数对象
     *@author: 谢洪飞
     *@Thorws:
     */
    private MerBussParam getMerBussParam(MerchantsDTO merDto) {

        MerBussParam merParam = this.find(merDto.getMerId()) == null ? new MerBussParam() : this
                .find(merDto.getMerId());
        UserSession us = Utils.getUserSession();

        if (merDto != null) {

            if (us.getUserLevel() != 2) {
                merParam.setCosmSign(merDto.getCosmSign());
                merParam.setCpscSign(merDto.getCpscSign());
                merParam.setCpsmSign(merDto.getCpsmSign());
                merParam.setMsscSign(merDto.getMsscSign());
            }

            merParam.setGnoPrefix(merDto.getGnoPrefix());
            merParam.setMerId(merDto.getMerId());
            merParam.setIcpFilePath(merDto.getIcpFilePath());
            merParam.setIcpNo(merDto.getIcpNo());
        }
        return merParam;
    }

    /**
     *@Title:findMerBuss
     *@Description:根据业务参数ID获取商户业务参数
     *@param:@param merId
     *@Return:MerchantsDTO merchantsDto
     *@author: 谢洪飞
     *@Thorws:
     */
    public MerchantsDTO findMerBuss(String merId) {

        MerBussParam merParam = this.find(merId);
        MerchantsDTO merDto = getMerDto(merParam);

        return merDto;
    }

    /**
     *@Title:getMerDto
     *@Description: 获取MerchantsDTO 对象
     *@param:@param merParam MerBussParam对象
     *@param:@return
     *@return:MerchantsDTO 商户Dto对象
     *@author: 谢洪飞
     *@Thorws:
     */
    private MerchantsDTO getMerDto(MerBussParam merParam) {

        MerchantsDTO merDto = new MerchantsDTO();
        if (null != merParam) {
            merDto.setMerId(merParam.getMerId());
            merDto.setCosmSign(merParam.getCosmSign());
            merDto.setCpscSign(merParam.getCpscSign());
            merDto.setCpsmSign(merParam.getCpsmSign());
            merDto.setMsscSign(merParam.getMsscSign());
            merDto.setExitsCount(merParam.getExitsCount());
            merDto.setSalingCount(merParam.getSalingCount());
            merDto.setGnoPrefix(merParam.getGnoPrefix());
            merDto.setIcpNo(merParam.getIcpNo());
            merDto.setIcpFilePath(merParam.getIcpFilePath());
        }
        return merDto;
    }

    /**
     *@Title:updateMerBuss
     *@Description: 更新商户业务参数信息
     *@param:@param merDto
     *@Return:void
     *@author: 谢洪飞
     *@Thorws:
     */
    public void updateMerBuss(MerchantsDTO merDto) {
        MerBussParam merParam = new MerBussParam();

        merParam = this.getMerBussParam(merDto);
        this.update(merParam);
    }

}
