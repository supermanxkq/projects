package com.paySystem.ic.service.base;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.base.MerScoMoDTO;

/**
 * @ProjectName:omall
 * @ClassName:MerScoMoService
 * @Description:商品评分模型service接口
 * @date: 2014-9-30
 * @author: 张军磊
 * @version: V1.0
 */
public interface MerScoMoService {

	public static final String MERSCOMOSERVICE = "merScoMoService";

	/**
	 *@Title:saveMerScoMo
	 *@Description: 保存商户评分模型业务
	 *@param:@param merScoMo 商户评分模型业务参数实体对象
	 *@Return:void
	 *@author: 张军磊
	 *@Thorws:
	 */
	public void saveMerScoMo(MerScoMoDTO merScoMoDTO);

	/**
	 *@Title:updateMerScoMo
	 *@Description: 更新商户评分模型业务参数信息
	 *@param:@param merScoMo 商户评分模型业务参数实体对象
	 *@Return:void
	 *@author: 张军磊
	 *@Thorws:
	 */
	public void updateMerScoMo(MerScoMoDTO merScoMoDTO);

	/**
	 *@Title:queryLogisticsByCond
	 *@Description:根据条件查询商户评分模型列表
	 *@param:@param firstPage 开始条数
	 *@param:@param pageNum 每页显示调试
	 *@param:@param merScoMoDTO MerScoMoDTO对象
	 *@param:@param orderBy 排序方式
	 *@param:@return
	 *@return:List<LogisticsDTO> 返回DTO集合
	 *@author: 张军磊
	 * @throws Exception
	 *@Thorws:
	 */
	public QueryResult<MerScoMoDTO> queryMerScoMoByCond(int firstPage,
			int pageNum, MerScoMoDTO merScoMoDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;

	/**
	 *@Title:findById
	 *@Description:根据ID查询商品评分模型信息
	 *@Params:@param merScoMoId
	 *@Params:@return
	 *@Return:MerScoMoDTO
	 *@author:张军磊
	 *@Date:2014-9-30上午10:18:42
	 */
	public MerScoMoDTO findById(Integer merScoMoId);

	/**
	 *@Title:deleteByMerScoMoId
	 *@Description:根据ID删除商品评分模型信息
	 *@Params:@param merScoMoId
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:MerScoMoDTO
	 *@author:张军磊
	 *@Date:2014-9-30上午10:19:15
	 */
	public MerScoMoDTO deleteByMerScoMoId(Integer merScoMoId) throws Exception;

	/**
	 *@Title:findmerScoMoName
	 *@Description:判断商户评分模型名称是否一样
	 *@Params:@param moName
	 *@Params:@param method
	 *@Params:@param merScoMoId
	 *@Params:@return
	 *@Return:boolean
	 *@author:张军磊
	 *@Date:2014-9-30上午10:20:45
	 */
	public boolean findmerScoMoName(String moName, String method,
			Integer merScoMoId);

	/**
	 *@Title:findMaxSco
	 *@Description:判断商户评分是否已经有集合包含
	 *@Params:@param maxSco
	 *@Params:@return
	 *@Return:boolean
	 *@author:张军磊
	 *@Date:2014-9-30上午10:22:39
	 */
	public boolean findMaxSco(Integer maxSco ,String method,
			Integer merScoMoId);

}
