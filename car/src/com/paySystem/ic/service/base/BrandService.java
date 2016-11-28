package com.paySystem.ic.service.base;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.GoodBrandsDTO;

/**  
* @Title: BrandService.java
* @Package: com.paySystem.ic.service.base
* @Description: 商品品牌服务接口
* @Author: Jacky
* @Date: 2014-08-01
* @Version: V1.0  
*/
public interface BrandService {
	
	public static final String BRANDSERVICE = "brandService";
	
	/**
	 * @Title:queryGoodBrandByName
	 * @Description:根据条件查询商品品牌
	 * @param firstPage 开始条数
	 * @param pageNum 每页显示条数
	 * @param goodBrandsDTO GoodBrandsDTO对象
	 * @param orderBy 排序方式
	 * @return List<GoodBrandsDTO> 返回DTO集合
	 * @author:  Jacky
	 * @throws Exception
	 */
	public QueryResult<GoodBrandsDTO> queryGoodBrandByName(int firstPage,int pageNum,
			GoodBrandsDTO goodBrandsDTO,LinkedHashMap<String,String> orderBy) throws Exception;
	
	/**
	 * @Title: findGoodBrandById
	 * @Description:根据id查询商品品牌
	 * @param id 主键id
	 * @return GoodBrandsDTO 返回DTO
	 * @author:  Jacky
	 * @throws Exception
	 */
	public GoodBrandsDTO findGoodBrandById(int id) throws Exception;
	
	/**
	 * @Title: checkSameName
	 * @Description:重名校验
	 * @param brandName 商品品牌名称
	 * @return boolean 校验结果true表示通过，false表示不通过
	 * @author:  Jacky
	 * @throws Exception
	 */
	public boolean checkSameName(String brandName) throws Exception;
	
	
	/**
	 *@Title:saveGoodBrand
	 *@Description: 保存商品品牌信息
	 *@param:@param goodBrandDto 商品品牌参数DTO对象
	 *@Return: String  错误信息
	 *@author:  Jacky
	 *@Thorws: Exception
	 */
	public String saveGoodBrand(GoodBrandsDTO goodBrandDto) throws Exception;
	
	/**
	 *@Title:updateGoodBrand
	 *@Description: 更新商品品牌信息
	 *@param:@param goodBrandDto 商品品牌参数DTO对象
	 *@Return: ReturnDTO 更新结果集	
	 *@author:  Jacky
	 *@Thorws: Exception
	 */
	public ReturnDTO updateGoodBrand(GoodBrandsDTO goodBrandDto) throws Exception;
	
	/**
	 *@Title:validateGoodBrand
	 *@Description: 校验保存商品品牌信息
	 * @param goodBrandDto dto对象信息
	 * @return String 校验错误信息
	 * @throws Exception
	 */
	public String validateGoodBrand(GoodBrandsDTO goodBrandDto) throws Exception ;
	
	/**
	 *@Title:validateParam
	 *@Description: 校验改商品品牌名称是否存在
	 * @param id 商品品牌id
	 * @param brandName 商品品牌名称
	 * @return boolean true表示通过，false表示不通过
	 * @throws Exception
	 */
	public boolean validateName(int id,String brandName) throws Exception;
	
}
