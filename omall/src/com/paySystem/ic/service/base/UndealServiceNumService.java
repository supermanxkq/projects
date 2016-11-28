package com.paySystem.ic.service.base;

import com.paySystem.ic.bean.base.UndealServiceNum;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.base.UndealServiceNumDTO;

/**
 * @ProjectName:omall
 * @ClassName:UndealServiceNumService
 * @Description:商城待处理业务查看的Service接口
 * @date: 2014-10-22
 * @author: 王楠
 * @version: V1.0
 */
public interface UndealServiceNumService extends DAO<UndealServiceNum>{
	
	public static final String UNDEALSERVICENUMSERVICE="undealServiceNumService";
	
	/**
	*@Title:findList
	*@Description:查询数据的方法
	*@Params:@return
	*@Params:@throws Exception
	*@Return:UndealServiceNumDTO
	*@author:王楠
	*@Date:2014-10-22下午05:58:00
	*/
	public UndealServiceNumDTO findList(String merId)throws Exception;
	
	/**
	*@Title:findTotalData
	*@Description:用于机构登陆时查找数据的方法
	*@Params:@param organId
	*@Params:@return
	*@Params:@throws Exception
	*@Return:UndealServiceNum 待处理的业务统计实体
	*@author:王楠
	*@Date:2014-11-25上午11:36:29
	*/
	public UndealServiceNumDTO findTotalData(String organId)throws Exception;

	/**
	*@Title:findByOrgMerId
	*@Description:通过机构和商户号查询，初始化用
	*@Params:@param sECURITYKEY
	*@Params:@return
	*@Return:UndealServiceNum
	*@author:孟凡岭
	*@Date:2014-12-13下午03:01:28
	*/
	public UndealServiceNum findByOrgMerId(String orgMerId);

}
