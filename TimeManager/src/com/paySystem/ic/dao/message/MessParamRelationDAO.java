package com.paySystem.ic.dao.message;


import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.message.MessParamRelation;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.message.MessageDTO;

/**
 * @ClassName:MessParamRelationDAO
 * @Description:TODO
 * @date: 2014-3-26下午05:43:01
 * @author: 张亚运
 * @version: V1.0
 */
public interface MessParamRelationDAO extends DAO<MessParamRelation>{
	
	
	public static final String MESSPARAMRALATIONDAO= "messParamRelationDao";
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
	List<MessageDTO> queryAll(int page, int pageNum,
			MessageDTO messageDto,LinkedHashMap<String, String> orderBy)
			throws Exception;	
	
	/**
	 *@Title:queryById
	 *@Description:根据id查询需要修改的信息
	 *@param:@param mprId
	 *@param:@return
	 *@return:MessageDTO
	 *@author:张亚运
	 *@thorws:
	 */
	public MessageDTO queryById(String mprId);
	
	/**
	 *@Title:saveMessParamRelation
	 *@Description:保存使用关系方法
	 *@param:@param messagedto
	 *@param:@return
	 *@return:MessParamRelation
	 *@author:张亚运
	 *@thorws:
	 */
	public MessParamRelation saveMessParamRelation(MessageDTO messagedto);	
		
	/**
	 *@Title:updateMessParamRelation
	 *@Description:跟新修改后的保存方法
	 *@param:@param messagedto
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:张亚运
	 *@thorws:
	 */
	public ReturnDTO updateMessParamRelation(MessageDTO messagedto);
			
	/**
	 *@Title:queryRelation
	 *@Description:查询关系是否存在
	 *@param:@param orgMerId
	 *@param:@return
	 *@return:boolean
	 *@author:张亚运
	 *@thorws:
	 */
	public boolean queryRelation(String orgMerId);
}
