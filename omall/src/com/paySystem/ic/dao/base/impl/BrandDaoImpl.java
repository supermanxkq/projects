package com.paySystem.ic.dao.base.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodBrands;
import com.paySystem.ic.dao.base.BrandDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.GoodBrandsDTO;

/**  
* @Title: BrandDaoImpl.java
* @Package: com.paySystem.ic.dao.base.impl
* @Description: 商品品牌DAO实现类
* @Author: Jacky
* @Date: 2014-08-01
* @Version: V1.0  
*/
@Repository(BrandDao.BRANDDAO)
public class BrandDaoImpl extends DaoSupport<GoodBrands> implements BrandDao {

	/**
	 *@Title:findByName
	 *@Description: 根据名称模糊查询商品品牌
	 *@param:@param firstPage 开始条数
	 *@param:@param pageNum   每页显示调试
	 *@param:@param goodBrandsDTO GoodBrandsDTO对象
	 *@param:@param orderBy   排序方式
	 *@Return:GoodBrandsDTO 商品品牌对象
	 *@author: Jacky
	 *@Thorws:
	 */
	public QueryResult<GoodBrandsDTO> findByName(int firstPage, int pageNum,
			GoodBrandsDTO goodBrandsDTO, LinkedHashMap<String, String> orderBy) throws Exception {
		QueryResult<GoodBrandsDTO> dtoResult
			= new QueryResult<GoodBrandsDTO>();
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<Object>(); 
		if(StringUtils.isNotBlank(goodBrandsDTO.getBrandName())){
			sb.append(" and o.brandName like ?").append(params.size()+1);
			params.add("%"+goodBrandsDTO.getBrandName().trim()+"%");
		}
		
		QueryResult<GoodBrands> result =
				this.getScrollData(firstPage, pageNum, sb.toString(), params.toArray(),orderBy);
		
			
		dtoResult = this.dtoResult2beanResult(result);
		
		return dtoResult;
		
	}

	
	/**
	 *@Title:findByNamePrecisely
	 *@Description: 根据名称精确查询商品品牌
	 *@param:@param firstPage 开始条数
	 *@param:@param pageNum   每页显示调试
	 *@param:@param goodBrandsDTO GoodBrandsDTO对象
	 *@param:@param orderBy   排序方式
	 *@Return:GoodBrandsDTO 商品品牌对象
	 *@author: Jacky
	 *@Thorws:
	 */
	public QueryResult<GoodBrandsDTO> findByNamePrecisely(int firstPage,
			int pageNum, GoodBrandsDTO goodBrandsDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		QueryResult<GoodBrandsDTO> dtoResult
	    	= new QueryResult<GoodBrandsDTO>();
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<Object>(); 
		if(StringUtils.isNotBlank(goodBrandsDTO.getBrandName())){
			sb.append(" and o.brandName = ?").append(params.size()+1);
			params.add(goodBrandsDTO.getBrandName().trim());
		}
		
		QueryResult<GoodBrands> result =
				this.getScrollData(firstPage, pageNum, sb.toString(), params.toArray(),orderBy);
		
			
		dtoResult = this.dtoResult2beanResult(result);
		
		return dtoResult;
	}


	/**
	 *@Title:findByGoodBrand
	 *@Description: 根据id查询商品品牌
	 *@param: @param id 商品品牌id
	 *@Return: GoodBrandsDTO 商品品牌DTO
	 *@author: Jacky
	 *@Thorws:
	 */
	public GoodBrandsDTO findByGoodBrand(int id) throws Exception {
		GoodBrands goodBrand = this.find(id);
		return bean2DTO(goodBrand);
	}


	/**
	 *@Title:saveGoodBrand
	 *@Description: 新增名称查询商品品牌
	 *@param: @param goodBrandDto 商品品牌参数
	 *@Return: void
	 *@author: Jacky
	 *@Thorws:
	 */
	public void saveGoodBrand(GoodBrandsDTO goodBrandDto) throws Exception {
		GoodBrands goodBrand = this.DTO2Bean(goodBrandDto);
		this.save(goodBrand);
	}

	/**
	 *@Title:updateGoodBrand
	 *@Description: 更新商品品牌
	 *@param: @param goodBrandDto 更新商品品牌参数
	 *@Return: ReturnDTO 更新结果
	 *@author: Jacky
	 *@Thorws:
	 */
	public ReturnDTO updateGoodBrand(GoodBrandsDTO goodBrandDto)
			throws Exception {
		ReturnDTO dto = new ReturnDTO();
		GoodBrands goodBrands = this.find(goodBrandDto.getBrandId());
		if(null != goodBrands) {
			GoodBrands newBrand = DTO2Bean(goodBrandDto);
			/** 如果不删除 应该把原来的图片url赋值进去 **/
			if(goodBrandDto.getDeleteLogo() == 0 && StringUtils.isBlank(goodBrandDto.getBrandLogoFileName())) {
				newBrand.setBrandLogo(goodBrands.getBrandLogo());
			}
			this.update(newBrand);
			dto.setFlag(true);
		} else {
			dto.setFlag(false);
			dto.setMsg("商品品牌信息不存在！");
		}
		return dto;
	}


	/**
	 *@Title:validateName
	 *@Description: 校验商品品牌名称是否存在
	 *@param: id 商品品牌id
	 *@param brandName 商品品牌名称
	 *@Return: void
	 *@author: Jacky
	 *@Thorws:
	 */
	public boolean validateName(int id, String brandName) throws Exception {
		long count = (Long) em.createQuery("select count(o) from GoodBrands o where o.brandName ='"+brandName.trim()+"' and o.brandId <> '"+id+"'").getSingleResult();
		return count > 0;
	}

	/**
	 *  DTO 转   实体Bean 
	 *@Title:DTO2Bean
	 *@Description: DTO 转   实体Bean 
	 *@param:@param goodBrandDto 商品品牌Dto
	 *@return:GoodBrandsDTO 商品品牌DTO对象
	 *@author:Jacky
	 */
	private GoodBrands DTO2Bean(GoodBrandsDTO goodBrandDto) {
		GoodBrands goodBrands = new GoodBrands();
		if(null != goodBrandDto) {
			goodBrandDto.setBrandName(goodBrandDto.getBrandName().trim());
			/** 属性拷贝推荐使用 **/
			BeanUtils.copyProperties(goodBrandDto, goodBrands,new String[]{"brandLogo","brandLogoFileName","deleteLogo"});
			goodBrands.setCreateTime(new Date());
			goodBrands.setBrandLogo(goodBrandDto.getBrandLogoFileName());
		}
		return goodBrands;
	}
	
	/**
	 * BeanResult转 实体DTOResult
	 * 
	 *@Title:dtoResult2beanResult
	 *@Description:BeanResult转 实体DTOResult
	 *@param:@param result
	 *               实体BeanResult
	 *@param:@return
	 *@return:QueryResult<GoodBrandsDTO>
	 *              DTO类型Result信息
	 *@author: Jacky
	 *@Thorws:
	 */
	private QueryResult<GoodBrandsDTO> dtoResult2beanResult (QueryResult<GoodBrands> result) {
		QueryResult<GoodBrandsDTO> dtoResult 
        	= new QueryResult<GoodBrandsDTO>();
		List<GoodBrandsDTO> dtoList
		        = new ArrayList<GoodBrandsDTO>();
		
		if(result != null){
			/** 遍历实体Result信息，获取支付参数实体对象 **/
			for (GoodBrands goodBrand : result.getResultlist()){
				/**将商品拍拍实体对象转为商品品牌DTO对象**/
				GoodBrandsDTO brandDto = new GoodBrandsDTO();
				brandDto = bean2DTO(goodBrand); 
				/**添加到DTO对象集合中**/
				dtoList.add(brandDto);
			}
		
		}
		/**组装DtoResult信息  DTO对象集合及总条数**/
		dtoResult.setResultlist(dtoList);
		dtoResult.setTotalrecord(result.getTotalrecord());
		
		return dtoResult;
	}
	
	/**
	 *   实体Bean 转 DTO
	 *   
	 *@Title:bean2DTO
	 *@Description: 实体Bean 转 DTO
	 *@param:@param goodBrand 商品品牌实体对象
	 *@param:@return
	 *@return:GoodBrandsDTO 商品品牌DTO对象
	 *@author:Jacky
	 */
	private GoodBrandsDTO bean2DTO(GoodBrands goodBrand) {
		
		GoodBrandsDTO goodBrandsDTO = new GoodBrandsDTO();
		
		if(null != goodBrand) {
			BeanUtils.copyProperties(goodBrand, goodBrandsDTO,new String[]{"brandLogo"});
			goodBrandsDTO.setBrandLogoFileName(goodBrand.getBrandLogo());
		}
		
		return goodBrandsDTO;
	}
	
}
