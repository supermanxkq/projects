/**  
 * @Title: CustomServiceDAO.java
 * @Package: com.paySystem.ic.dao.member
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-11-13 下午03:50:33
 * @Version: V1.0  
 */
package com.paySystem.ic.dao.member;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.member.CustomServiceDTO;

/**
 * @ProjectName:omal
 * @ClassName:CustomServiceDAO
 * @Description:客服管理dao接口
 * @date: 2014-11-13
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface CustomServiceDAO {
	public static final String CUSTOMSERVICEDAO = "customServiceDAO";

	/**
	 *@Title:findByStoreId
	 *@Description:根据商户号查询出相关客服信息
	 *@Params:@param storeId
	 *@Params:@return
	 *@Return:List<CustomServiceDTO>
	 *@author:孟凡岭
	 *@Date:2014-11-13下午04:23:03
	 */
	List<CustomServiceDTO> findByStoreId(String storeId) throws Exception;

	/**
	*@Title:queryResult
	*@Description:分页查询
	*@Params:@param first
	*@Params:@param pageNum
	*@Params:@param customServiceDTO
	*@Params:@param orderby
	*@Params:@return
	*@Return:QueryResult<CustomServiceDTO>
	*@author:孟凡岭
	*@Date:2014-11-17下午02:06:01
	*/
	QueryResult<CustomServiceDTO> queryResult(int first, int pageNum,
			CustomServiceDTO customServiceDTO,
			LinkedHashMap<String, String> orderby) throws Exception;

	/**
	*@Title:update
	*@Description:更新
	*@Params:@param customServiceDTO
	*@Return:void
	*@author:孟凡岭
	*@Date:2014-11-17下午05:44:05
	*/
	void update(CustomServiceDTO customServiceDTO);

	/**
	*@Title:add
	*@Description:添加数据
	*@Params:@param customServiceDTO
	*@Return:void
	*@author:孟凡岭
	*@Date:2014-11-17下午05:56:07
	*/
	void add(CustomServiceDTO customServiceDTO) throws Exception;

	/**
	*@Title:findById
	*@Description:加载单条数据
	*@Params:@param id
	*@Return:CustomServiceDTO
	*@author:孟凡岭
	*@Date:2014-11-17下午06:39:16
	*/
	CustomServiceDTO findById(long id) throws Exception;

	/**
	*@Title:deleteData
	*@Description:删除数据
	*@Params:@param id
	*@Return:void
	*@author:孟凡岭
	*@Date:2014-11-18上午09:53:39
	*/
	void deleteData(String id);
}
