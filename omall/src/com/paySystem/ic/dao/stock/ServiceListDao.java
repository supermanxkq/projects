package com.paySystem.ic.dao.stock;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.stock.ServiceList;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.stock.ServiceListDTO;

/**
 * @ProjectName:omall
 * @ClassName:MessServParamConfigDao
 * @Description:短信服务器配置，添加、查看、更新记录接口
 * @date: 2014-7-22下午02:07:51
 * @author: 徐凯强
 * @version: V1.0
 */
public interface ServiceListDao extends DAO<ServiceList> {

	/**常量*/
	public static final String SERVICELISTDAO = "ServiceListDao";
	/**
	 *@Title:saveServiceList
	 *@Description:保存
	 *@param serviceListDTO
	 *@Return:void
	 *@author:徐凯强
	 *@Date:2014-7-22下午02:10:41
	 */
	public void saveServiceList(ServiceListDTO serviceListDTO)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException;

	/**
	 *@Title:updateServiceList
	 *@Description:更新
	 *@param serviceListDTO
	 *@Return:void
	 *@author:徐凯强
	 *@Date:2014-7-22下午02:18:09
	 */
	public void updateServiceList(ServiceListDTO serviceListDTO);

	/**
	 *@Title:queryAll
	 *@Description:分页查找
	 *@param firstIndex
	 *@param pageNum
	 *@param serviceListDTO
	 *@param orderBy
	 *@Return:QueryResult<ServiceListDTO>
	 *@author:徐凯强
	 *@Date:2014-7-23下午05:13:09
	 */
	public QueryResult<ServiceListDTO> queryAll(int firstIndex, int pageNum,
			ServiceListDTO serviceListDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;

	/**
	*@Title:findServiceByName
	*@Description:根据标题名称查询数据
	*@Params:@param name
	*@Params:@return
	*@Return:List<ServiceList>
	*@author:张军磊
	*@Date:2014-12-1下午05:31:31
	*/
	public List<ServiceList> findServiceByName(String name);

}
