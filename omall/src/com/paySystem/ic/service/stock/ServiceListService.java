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
	 *@param firstIndex分页的首个参数
	 *@param pageNum当前页有多少条数据
	 *@param serviceListDTO服务清单数据传输对象
	 *@param orderBy排序对象
	 *@Return:QueryResult<ServiceListDTO>服务清单数据传输对象
	 *@author:徐凯强
	 * @throws Exception抛出异常信息
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

	/**
	*@Title:findName
	*@Description:检验标题名称是否重复
	*@Params:@param name 标题名
	*@Params:@param method 方法名
	*@Params:@param serviceId 服务ID
	*@Params:@return
	*@Return:Boolean
	*@author:张军磊
	*@Date:2014-12-1下午04:54:11
	*/
	public Boolean findName(String name, String method, Integer serviceId);
}
