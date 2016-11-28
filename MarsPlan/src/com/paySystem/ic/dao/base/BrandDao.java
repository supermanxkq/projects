package com.paySystem.ic.dao.base;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.GoodBrandsDTO;

/**  
* @Title: BrandDao.java
* @Package: com.paySystem.ic.dao.base
* @Description: 商品品牌DAO接口
* @Author: Jacky
* @Date: 2014-08-01
* @Version: V1.0  
*/
public interface BrandDao {
	
	public static final String BRANDDAO = "brandDao";
	
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
	public QueryResult<GoodBrandsDTO> findByNamePrecisely(int firstPage, int pageNum,
			GoodBrandsDTO goodBrandsDTO, LinkedHashMap<String, String> orderBy)  throws Exception ;
	
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
			GoodBrandsDTO goodBrandsDTO, LinkedHashMap<String, String> orderBy)  throws Exception ;
	
	/**
	 *@Title:findByGoodBrand
	 *@Description: 根据id查询商品品牌
	 *@param: @param id 商品品牌id
	 *@Return: GoodBrandsDTO 商品品牌DTO
	 *@author: Jacky
	 *@Thorws:
	 */
	public GoodBrandsDTO findByGoodBrand(int id) throws Exception;
	
	/**
	 *@Title:saveGoodBrand
	 *@Description: 新增名称查询商品品牌
	 *@param: @param goodBrandDto 商品品牌参数
	 *@Return: void
	 *@author: Jacky
	 *@Thorws:
	 */
	public void saveGoodBrand(GoodBrandsDTO goodBrandDto) throws Exception;
	
	/**
	 *@Title:updateGoodBrand
	 *@Description: 更新商品品牌
	 *@param: @param goodBrandDto 更新商品品牌参数
	 *@Return: ReturnDTO 更新结果
	 *@author: Jacky
	 *@Thorws:
	 */
	public ReturnDTO updateGoodBrand(GoodBrandsDTO goodBrandDto) throws Exception;
	
	/**
	 *@Title:validateName
	 *@Description: 校验商品品牌名称是否存在
	 *@param: id 商品品牌id
	 *@param brandName 商品品牌名称
	 *@Return: void
	 *@author: Jacky
	 *@Thorws:
	 */
	public boolean validateName(int id,String brandName) throws Exception;
	
}
