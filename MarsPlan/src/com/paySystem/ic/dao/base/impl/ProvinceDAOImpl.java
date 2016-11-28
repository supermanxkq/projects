package com.paySystem.ic.dao.base.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.base.AreaBean;
import com.paySystem.ic.bean.base.City;
import com.paySystem.ic.bean.base.Province;
import com.paySystem.ic.dao.base.ProvinceDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.web.dto.base.AreaBeanDTO;
import com.paySystem.ic.web.dto.base.CityDTO;
import com.paySystem.ic.web.dto.base.ProvinceDTO;

/**
 * @ClassName: ProvinceDAOImpl.java
 * @Description:省 DAO
 * @date: 2014-10-10下午03:03:53
 * @author: Jacky
 * @version: V1.0
 */
@Repository(ProvinceDAO.PROVINCEDAO)
public class ProvinceDAOImpl extends DaoSupport<Province> implements ProvinceDAO {

	/**
	 * 查询所有的省
	 *@Title: queryAllProvince
	 *@Description: 查询所有的省
	 *@return: List<Province> 省列表
	 *@author: Jacky
	 *@Thorws:
	 */
	@SuppressWarnings("unchecked")
	public List<ProvinceDTO> queryAllProvince() throws Exception {
		List<Province> provList = em.createQuery("from Province o ").getResultList();
		return getDTOList(provList);
	}

	
	/**
	 * 根据省编码查询下属市
	 *@Title: queryAllCityByCode
	 *@Description: 根据省编码查询下属市
	 *@param code 省编码
	 *@return: List<CityDTO> 省列表
	 *@author: Jacky
	 *@Thorws:
	 */
	@SuppressWarnings("unchecked")
	public List<CityDTO> queryAllCityByCode(String code) throws Exception {
		List<City> list = em.createQuery("from City o where o.provinceCode = ?").setParameter(1, code).getResultList();
		return getDTOFromCity(list);
	}
	
	
	/**
	 * 根据省编码查询下属区域
	 *@Title: queryAllAreaByCode
	 *@Description: 根据市编码查询下属区域
	 *@param code 市编码
	 *@return: List<AreaBeanDTO> 区域列表
	 *@author: Jacky
	 *@Thorws:
	 */
	@SuppressWarnings("unchecked")
	public List<AreaBeanDTO> queryAllAreaByCode(String code) throws Exception {
		List<AreaBean> list = em.createQuery("from AreaBean o where o.cityCode = ?").setParameter(1, code).getResultList();
		return getDTOFromArea(list);
	}

	/**
	 * bean到dto转换
	 *@Title: getDTOFromArea
	 *@Description: 所有的区域
	 *@return: List<AreaBeanDTO> 区域DTO列表
	 *@author: Jacky
	 *@Thorws:
	 */
	private List<AreaBeanDTO> getDTOFromArea(List<AreaBean> list) {
		List<AreaBeanDTO> areaList = new ArrayList<AreaBeanDTO>();
		if(CollectionUtils.isNotEmpty(list)) {
			for(AreaBean ab : list) {
				AreaBeanDTO abd = new AreaBeanDTO();
				BeanUtils.copyProperties(ab, abd);
				areaList.add(abd);
			}
		}
		return areaList;
	}

	/**
	 * bean到dto转换
	 *@Title: getDTOFromCity
	 *@Description: 所有的市
	 *@return: List<CityDTO> 省DTO列表
	 *@author: Jacky
	 *@Thorws:
	 */
	private List<CityDTO> getDTOFromCity(List<City> list) {
		List<CityDTO> cityDTO = new ArrayList<CityDTO>();
		if(CollectionUtils.isNotEmpty(list)) {
			for(City c : list) {
				CityDTO cd = new CityDTO();
				BeanUtils.copyProperties(c, cd);
				cityDTO.add(cd);
			}
		}
		return cityDTO;
	}

	/**
	 * bean到dto转换
	 *@Title: getDTOList
	 *@Description: 查询所有的省
	 *@return: List<ProvinceDTO> 省DTO列表
	 *@author: Jacky
	 *@Thorws:
	 */
	private List<ProvinceDTO> getDTOList(List<Province> provList) {
		List<ProvinceDTO> pList = new ArrayList<ProvinceDTO>();
		if(CollectionUtils.isNotEmpty(provList)) {
			for(Province p : provList) {
				ProvinceDTO pt = new ProvinceDTO();
				BeanUtils.copyProperties(p, pt);
				pList.add(pt);
			}
		}
		return pList;
	}
	
}
