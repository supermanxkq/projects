package com.paySystem.ic.service.stock;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.MessServParamConfigDTO;

/**
 * @ProjectName:omall
 * @ClassName:MessServParamConfigDao
 * @Description:短信服务器配置，添加、查看、更新记录服务类
 * @date: 2014-7-22下午02:07:51
 * @author: 徐凯强
 * @version: V1.0
 */
public interface MessServParamConfigService {
	/**常量*/
	public static final String MESSSERVPARAMCONFIGSERVICE = "MessServParamConfigService";

	/**
	 *@Title:saveMessServParamConfig
	 *@Description:保存
	 *@param messServParamConfigDTO
	 *@Return:void
	 *@author:徐凯强
	 *@Date:2014-7-22下午02:10:41
	 */
	public void saveMessServParamConfig(
			MessServParamConfigDTO messServParamConfigDTO)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException;

	/**
	 *@Title:updateMessServParamConfig
	 *@Description:更新短信服务器配置信息
	 *@param messServParamConfigDTO
	 *@Return:void
	 *@author:徐凯强
	 *@Date:2014-7-22下午02:18:09
	 */
	public void updateMessServParamConfig(
			MessServParamConfigDTO messServParamConfigDTO)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException;

	/**
	 *@Title:queryAll
	 *@Description:分页查找所有的短信服务器配置信息列表
	 *@param firstIndex
	 *@param pageNum
	 *@param messServParamConfigDTO
	 *@param orderBy
	 *@Return:QueryResult<MessServParamConfigDTO>
	 *@author:徐凯强
	 * @throws Exception
	 *@Date:2014-7-23下午05:13:09
	 */
	public QueryResult<MessServParamConfigDTO> queryAll(int firstIndex,
			int pageNum, MessServParamConfigDTO messServParamConfigDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;

	/**
	 *@Title:findConfigDTO
	 *@Description:根据主键查询
	 *@param messServParamConfigDTO
	 *@Return:MessServParamConfigDTO
	 *@author:徐凯强
	 *@Date:2014-7-24上午09:19:56
	 */
	public MessServParamConfigDTO findConfigDTO(
			MessServParamConfigDTO messServParamConfigDTO);

	/**
	 *@Title:testShortMessServConfig
	 *@Description:发送普通短信服务器测试短信
	 *@param messServParamConfigDTO
	 *@Return:ReturnDTO
	 *@author:徐凯强
	 *@Date:2014-7-25下午02:49:03
	 */
	public ReturnDTO testShortMessServConfig(
			MessServParamConfigDTO messServParamConfigDTO);

	/**
	 *@Title:testValidateMessServCon
	 *@Description:发送验证码短信服务器
	 *@param messServParamConfigDTO
	 *@Return:ReturnDTO
	 *@author:徐凯强
	 *@Date:2014-7-29下午05:03:57
	 */
	public ReturnDTO testValidateMessServCon(
			MessServParamConfigDTO messServParamConfigDTO);

	/**
	 *@Title:changeStatus
	 *@Description:改变其他服务器的状态
	 *@Return:ReturnDTO
	 *@author:徐凯强
	 *@Date:2014-7-29上午10:37:39
	 */
	public ReturnDTO changeStatus();

	/**
	 *@Title:changeShortMessStatus
	 *@Description:改变其他普通服务器的状态
	 *@Return:ReturnDTO
	 *@author:徐凯强
	 *@Date:2014-7-29上午10:37:39
	 */
	public ReturnDTO changeShortMessStatus();

	/**
	 *@Title:changeValidateMessStatus
	 *@Description:改变其他验证码服务器的状态
	 *@Return:ReturnDTO
	 *@author:徐凯强
	 *@Date:2014-7-29上午10:37:39
	 */
	public ReturnDTO changeValidateMessStatus();

	/**
	 *@Title:sendMessageByParamType
	 *@Description:外部调用发送短信
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-8-5下午02:39:46
	 */
	public ReturnDTO sendMessageByParamType(
			MessServParamConfigDTO messServParamConfigDTO);

}
