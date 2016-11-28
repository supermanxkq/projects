package com.paySystem.ic.service.message;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.message.MessParamRelation;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.message.MessageDTO;

/**
 * @ClassName:MessParamRelationService
 * @Description:参数使用关系Service
 * @date: 2014-3-26下午06:41:02
 * @author: 张亚运
 * @version: V1.0
 */
public interface MessParamRelationService {

    public static final String MESSPARAMRELATIONSERVICE = "messParamRelationService";

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
    QueryResult<MessageDTO> queryAll(int page, int pageNum, MessageDTO messageDto, LinkedHashMap<String, String> orderBy)
            throws Exception;

    /**
     *@Title:queryById
     *@Description:根据id查询使用关系
     *@param:@param mprId
     *@param:@return
     *@return:MessageDTO
     *@author:张亚运
     *@thorws:
     */
    public MessageDTO queryById(String mprId);

    /**
     *@Title:saveMessParamRelation
     *@Description:保存参数使用关系
     *@param:@param messagedto
     *@param:@return
     *@return:MessFeeParam
     *@author:张亚运
     *@thorws:
     */
    public MessParamRelation saveMessParamRelation(MessageDTO messagedto);

    /**
     *@Title:updateMessParamRelation
     *@Description:修改参数保存方法
     *@param:@param messageDto
     *@param:@return
     *@return:ReturnDTO
     *@author:张亚运
     *@thorws:
     */
    public ReturnDTO updateMessParamRelation(MessageDTO messageDto);

    /**
     *@Title:deleteMessParamRelation
     *@Description:逻辑删除使用关系
     *@param:@param mprId
     *@return:void
     *@author:张亚运
     *@thorws:
     */
    public void deleteMessParamRelation(String mprId);

    /**
     *@Title:queryRelation
     *@Description:查询关系是否存在
     *@param:@param orgMerId
     *@param:@return
     *@return:boolean
     *@author:张亚运
     * @param mprId 
     *@thorws:
     */
    public boolean queryRelation(String orgMerId, String mprId);
    
    /**
	*@Title: queryByOrgId
	*@Description: 通过机构ID查询信息
	*@Params: @return
	*@Return: MessageDTO
	*@author: 廖晓远 
	*@Date: 2014-12-10上午10:37:26
	*/
    public MessageDTO queryByOrgId();

}
