package com.paySystem.ic.service.stock;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.stock.ServiceListDTO;

/**
 * @ProjectName:omall
 * @ClassName:ServiceListService
 * @Description:服务清单服务类
 * @date: 2014-7-22下午03:00:46
 * @author: 徐凯强
 * @version: V1.0
 */
public interface ServiceListService {
	/**常量*/
	public static final String SERVICELISTSERVICE = "ServiceListService";
	/**
	 *@Title:saveServiceList
	 *@Description:保存
	 *@param serviceListDTO
	 *@Return:void
	 *@author:徐凯强
	 *@Date:2014-7-22下午02:10:41
	 */
	public void saveServiceList(ServiceListDTO serviceListDTO);

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
	 * @throws Exception
	 *@Date:2014-7-23下午05:13:09
	 */
	public QueryResult<ServiceListDTO> queryAll(int firstIndex, int pageNum,
			ServiceListDTO serviceListDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;

	/**
	 *@Title:findServiceListDTO
	 *@Description:根据主键查询
	 *@param id
	 *@Return:serviceListDTO
	 *@author:徐凯强
	 *@Date:2014-7-24上午09:19:56
	 */
	public ServiceListDTO findServiceListDTO(Integer id);
}
