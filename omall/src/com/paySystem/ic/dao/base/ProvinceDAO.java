package com.paySystem.ic.dao.base;

import java.util.List;

import com.paySystem.ic.web.dto.base.AreaBeanDTO;
import com.paySystem.ic.web.dto.base.CityDTO;
import com.paySystem.ic.web.dto.base.ProvinceDTO;

/**
 * @ClassName:ProvinceDAO.java
 * @Description:省 DAO
 * @date: 2014-10-10下午03:03:53
 * @author: Jacky
 * @version: V1.0
 */
public interface ProvinceDAO {
	public static final String PROVINCEDAO = "provinceDAO";
	
	/**
	 * 查询所有的省
	 *@Title: queryAllProvince
	 *@Description: 查询所有的省
	 *@return: List<Province> 省列表
	 *@author: Jacky
	 *@Thorws:
	 */
	public List<ProvinceDTO> queryAllProvince() throws Exception ;
	
	/**
	 * 根据省编码查询下属市
	 *@Title: queryAllCityByCode
	 *@Description: 根据省编码查询下属市
	 *@param code 省编码
	 *@return: List<CityDTO> 省列表
	 *@author: Jacky
	 *@Thorws:
	 */
	public List<CityDTO> queryAllCityByCode(String code) throws Exception ;
	
	/**
	 * 根据省编码查询下属区域
	 *@Title: queryAllAreaByCode
	 *@Description: 根据市编码查询下属区域
	 *@param code 市编码
	 *@return: List<AreaBeanDTO> 区域列表
	 *@author: Jacky
	 *@Thorws:
	 */
	public List<AreaBeanDTO> queryAllAreaByCode(String code) throws Exception ;
	
}
