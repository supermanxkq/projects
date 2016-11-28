package com.paySystem.ic.service.base.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.constants.ErrorMsg;
import com.paySystem.ic.dao.base.BrandDao;
import com.paySystem.ic.service.base.BrandService;
import com.paySystem.ic.utils.UploadUtil;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.GoodBrandsDTO;

/**  
* @Title: BrandServiceImpl.java
* @Package: com.paySystem.ic.service.base
* @Description: 商品品牌服务接口
* @Author: Jacky
* @Date: 2014-08-01
* @Version: V1.0  
*/
@Service(BrandService.BRANDSERVICE)
public class BrandServiceImpl implements BrandService {
	
	/**
	 * 30M
	 */
	private static final long MAX_SIZE = 3000000;
	
	@Resource
	private BrandDao brandDao;

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
	public QueryResult<GoodBrandsDTO> queryGoodBrandByName(int firstPage,
			int pageNum, GoodBrandsDTO goodBrandsDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return brandDao.findByName(firstPage, pageNum, goodBrandsDTO, orderBy);
	}

	/**
	 * @Title: checkSameName
	 * @Description:重名校验
	 * @param brandName 商品品牌名称
	 * @return boolean 校验结果true表示通过，false表示不通过
	 * @author:  Jacky
	 * @throws Exception
	 */
	public boolean checkSameName(String brandName) throws Exception {
		GoodBrandsDTO goodBrandsDTO = new GoodBrandsDTO();
		goodBrandsDTO.setBrandName(brandName);
		QueryResult<GoodBrandsDTO> queryResult = brandDao.findByNamePrecisely(0, 1, goodBrandsDTO, null);
		return CollectionUtils.isEmpty(queryResult.getResultlist())?true:false;
	}

	/**
	 *@Title:saveGoodBrand
	 *@Description: 保存商品品牌信息
	 *@param:@param goodBrandDto 商品品牌参数DTO对象
	 *@Return: String  错误信息
	 *@author:  Jacky
	 *@Thorws: Exception
	 */
	public String saveGoodBrand(GoodBrandsDTO goodBrandDto) throws Exception {
		/** 先查询是否存在 **/
		QueryResult<GoodBrandsDTO> queryResult = brandDao.findByNamePrecisely(0, 1, goodBrandDto, null);
		/** 是否存在 **/
		if(CollectionUtils.isEmpty(queryResult.getResultlist())) {
			if(goodBrandDto.getBrandLogo()!=null && StringUtils.isNotBlank(goodBrandDto.getBrandLogoFileName())) {
				/** 上传文件 **/
				String fileName = UploadUtil.uploadImg(goodBrandDto.getBrandLogo(), goodBrandDto.getBrandLogoFileName());
				/** 设置文件名 **/
				goodBrandDto.setBrandLogoFileName(fileName);
			}
			/** 保存商品品牌信息 **/
			brandDao.saveGoodBrand(goodBrandDto);
		} else {
			/** 返回错误信息 **/
			return ErrorMsg.RECORD_EXISTED;
		}
		return null;
	}
	
	/**
	 *@Title:updateGoodBrand
	 *@Description: 更新商品品牌信息
	 *@param:@param goodBrandDto 商品品牌参数DTO对象
	 *@Return: ReturnDTO 更新结果集	
	 *@author:  Jacky
	 *@Thorws: Exception
	 */
	public ReturnDTO updateGoodBrand(GoodBrandsDTO goodBrandDto)
			throws Exception {
		
		if(goodBrandDto.getBrandLogo()!=null && StringUtils.isNotBlank(goodBrandDto.getBrandLogoFileName())) {
			/** 上传文件 **/
			String fileName = UploadUtil.uploadImg(goodBrandDto.getBrandLogo(), goodBrandDto.getBrandLogoFileName());
			/** 设置文件名 **/
			goodBrandDto.setBrandLogoFileName(fileName);
		}
		/** 更新结果 **/
		return brandDao.updateGoodBrand(goodBrandDto);
	}

	/**
	 * @Title: findGoodBrandById
	 * @Description:根据id查询商品品牌
	 * @param id 主键id
	 * @return GoodBrandsDTO 返回DTO
	 * @author:  Jacky
	 * @throws Exception
	 */
	public GoodBrandsDTO findGoodBrandById(int id) throws Exception {
		return brandDao.findByGoodBrand(id);
	}

	/**
	 *@Title:validateGoodBrand
	 *@Description: 校验保存商品品牌信息
	 * @param goodBrandDto dto对象信息
	 * @return String 校验错误信息
	 * @throws Exception
	 */
	public String validateGoodBrand(GoodBrandsDTO goodBrandDto)
			throws Exception {
		if(StringUtils.isBlank(goodBrandDto.getBrandName())) {
			return ErrorMsg.BRAND_NAME_NEEDED;
		}
		if(goodBrandDto.getBrandName().length() > 60) {
			return ErrorMsg.BRAND_NAME_TOO_LONG;
		}
		if(StringUtils.isBlank(goodBrandDto.getOperator())) {
			return ErrorMsg.AUTH_FAILED;
		}
		if(goodBrandDto.getBrandLogo()!=null && goodBrandDto.getBrandLogo().length() > MAX_SIZE) {
			return ErrorMsg.FILE_EXECEDE;
		}
		if(StringUtils.isNotBlank(goodBrandDto.getBrandUrl()) && goodBrandDto.getBrandUrl().length() > 100) {
			return ErrorMsg.BRAND_URL_TOO_LONG;
		}
		if(StringUtils.isNotBlank(goodBrandDto.getBrandDesc()) && goodBrandDto.getBrandDesc().length() > 255) {
			return ErrorMsg.BRAND_DESC_TOO_LONG;
		}
		if(goodBrandDto.getIsShow() !=0 && goodBrandDto.getIsShow()!=1) {
			return ErrorMsg.PARAM_ERROR;
		}
		return null;
	}

	/**
	 *@Title:validateParam
	 *@Description: 校验改商品品牌名称是否存在
	 * @param id 商品品牌id
	 * @param brandName 商品品牌名称
	 * @return boolean true表示通过，false表示不通过
	 * @throws Exception
	 */
	public boolean validateName(int id, String brandName) throws Exception {
		return brandDao.validateName(id,brandName);
	}

}
