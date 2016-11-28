package com.paySystem.ic.service.message;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.message.MessFeeParam;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.message.MessageDTO;



/**
 * @ClassName:MessFeeParamService
 * @Description:短信参数Service
 * @date: 2014-3-20上午09:14:34
 * @author: 张亚运
 * @version: V1.0
 */
public interface MessFeeParamService {
	
	public static final String MESSFEEPARAMSERVICE = "messFeeParamService";
		
	/**
	 *@Title:queryAll
	 *@Description:查询短信参数
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
	 *@Description:保存短信参数
	 *@param:@param messagedto
	 *@param:@return
	 *@return:MessFeeParam
	 *@author:
	 *@thorws:
	 */
	public MessFeeParam saveMessFeeParam(MessageDTO messagedto);	
	
	/**
	 *@Title:updateMessFeeParam
	 *@Description:修改保存短信参数
	 *@param:@param messageDto
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:张亚运
	 *@thorws:
	 */
	public ReturnDTO updateMessFeeParam(MessageDTO messageDto);	
	
	/**
	 *@Title:deleteMessFeeParam
	 *@Description:删除短信参数
	 *@param:@param mfpId
	 *@return:void
	 *@author:
	 *@thorws:
	 */
	public void deleteMessFeeParam(String mfpId);	
	
	/**
	 *@Title:find
	 *@Description:根据id查询要修改的数据
	 *@param:@param mfpId
	 *@param:@return
	 *@return:MessFeeParam
	 *@author:
	 *@thorws:
	 */
	public MessFeeParam find(String mfpId);
	
	/**
	 *@Title:queryMessName
	 *@Description:查询该参数名称是否存在
	 *@param:@param messName
	 *@param:@return
	 *@return:boolean
	 *@author:张亚运
	 *@thorws:
	 */
	public boolean queryMessName(String messName);
}
