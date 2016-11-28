package com.paySystem.ic.dao.message;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.message.MessFeeParam;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.message.MessageDTO;

/**
 * @ClassName:MessParamDAO
 * @Description:TODO
 * @date: 2014-3-19上午11:48:33
 * @author: 张亚运
 * @version: V1.0
 */
public interface MessFeeParamDAO extends DAO<MessFeeParam> {
	
	public static final String MESSFEEPARAMDAO= "messFeeParamDao";
	 
	/**
	 *@Title:queryAll
	 *@Description:查询短信参数方法
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
	List<MessageDTO> queryAll(int page, int pageNum,
			MessageDTO messageDto,LinkedHashMap<String, String> orderBy)
			throws Exception;

	/**
	 *@Title:saveMessFeeParam
	 *@Description:添加保存参数方法
	 *@param:@param messagedto
	 *@param:@return
	 *@return:MessageDTO
	 *@author:张亚运
	 *@thorws:
	 */
	public MessFeeParam saveMessFeeParam(MessageDTO messagedto);
		
	/**
	 *@Title:updateMessFeeParam
	 *@Description:更新参数方法（修改）
	 *@param:@param messagedto
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:张亚运
	 *@thorws:
	 */
	public ReturnDTO updateMessFeeParam(MessageDTO messagedto);
	
	/**
	 *@Title:queryMessName
	 *@Description:查询参数名称是否存在
	 *@param:@param orgMerId
	 *@param:@return
	 *@return:boolean
	 *@author:张亚运
	 *@thorws:
	 */
	public boolean queryMessName(String messName);
	
	
}
