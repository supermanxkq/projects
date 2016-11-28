package com.paySystem.ic.dao.goods;

import com.paySystem.ic.bean.goods.PlatHandleRecord;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.goods.PlatHandleRecordDTO;

/**
 * 
 * @ProjectName:omall
 * @ClassName:PlatHandleRecordDAO
 * @Description:平台处理商品记录
 * @date: 2014-8-26
 * @author:Jacky
 * @version: V1.0
 */
public interface PlatHandleRecordDAO extends DAO<PlatHandleRecord>{
	public static final String PLATHANDLE = "platHandleRecordDAO";
	
	/**
	 * 
	 *@Title:save
	 *@Description:保存
	 *@Params:@param platHandleDTO
	 *@Params:@throws Exception
	 *@Return:void
	 *@author:Jacky
	 *@Date:2014-8-26下午09:03:16
	 */
	void save(PlatHandleRecordDTO platHandleDTO) throws Exception;
	
	/**
	 * 
	 *@Title:findPlatHandleRecordDTO
	 *@Description:保存
	 *@Params:@param goodsId 商品id
	 *@Params:@throws Exception
	 *@Return:void
	 *@author:Jacky
	 *@Date:2014-8-26下午09:03:16
	 */
	PlatHandleRecordDTO findPlatHandleRecordDTO(Long goodsId) throws Exception;
	
}
