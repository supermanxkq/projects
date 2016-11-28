package com.paySystem.ic.dao.base;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.MerScoMo;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.MerScoMoDTO;

/**
 * @ClassName:MerScoMoDao
 * @Description:商户评分模型Dao接口
 * @date: 2013-9-23
 * @author: 张军磊
 * @version: V1.0
 */
public interface MerScoMoDao extends DAO<MerScoMo> {

	public static final String MERSCOMODAO = "merScoMoDao";

	/**
	 *@Title:saveMerScoMo
	 *@Description: 保存商户评分模型业务
	 *@param:@param merScoMo 商户评分模型业务参数实体对象
	 *@Return:void
	 *@author: 张军磊
	 *@Thorws:
	 */
	public void saveMerScoMo(MerScoMo merScoMo);

	/**
	 *@Title:updateMerScoMo
	 *@Description: 更新商户评分模型业务参数信息
	 *@param:@param merScoMo 商户评分模型业务参数实体对象
	 *@Return:void
	 *@author: 张军磊
	 *@Thorws:
	 */
	public void updateMerScoMo(MerScoMo merScoMo);

	/**
	 *@Title:queryMerScoMoByCond
	 *@Description:根据条件查询商户评分模型记录
	 *@param:@param fristindex 起始
	 *@param:@param pageNum 每页显示数
	 *@param:@param merScoMoDTO 页面Dto对象
	 *@param:@param orderBy 排序方式Map
	 *@param:@return
	 *@param:@throws Exception
	 *@return:QueryResult 返回QueryResult对象
	 *@author: 张军磊
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	QueryResult<MerScoMoDTO> queryMerScoMoByCond(int fristindex, int pageNum,
			MerScoMoDTO merScoMoDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;

	/**
	 * 
	 *@Title:deleteByMerScoMoId
	 *@Description:删除商户评分模型
	 *@Params:@param merScoMoId
	 *@Return:MerScoMoDTO 商户评分模型 对象
	 *@author:张军磊
	 *@Date:2014-9-24
	 */
	public MerScoMoDTO deleteByMerScoMoId(Integer merScoMoId) throws Exception;

	/**
	 * 根据Id获取商户评分模型信息
	 * 
	 * @Title:findById
	 *@Description:根据Id获取商户评分模型信息Dto对象
	 *@param:@param merScoMoId
	 *@param:@return
	 *@return:MerScoMoDTO
	 *@author:张军磊
	 *@Thorws:
	 */
	public MerScoMoDTO findById(Integer merScoMoId);

	/**
	 * @Title:findmerScoMoName
	 *@Description: 根据MoName查询实体对象
	 *@Params:@param MoName
	 *@Params:@return
	 *@Return:List<MerScoMo>
	 *@author:张军磊
	 *@Date:2014-9-30上午09:53:49
	 */
	public List<MerScoMo> findmerScoMoName(String MoName);

	/** 
	 * @Title:findMaxSco
	 *@Description: 根据分数查询评分模型实体对象
	 *@Params:@param maxsco
	 *@Params:@return
	 *@Return:List<MerScoMo>
	 *@author:张军磊
	 *@Date:2014-9-30上午09:57:36
	 */
	public List<MerScoMo> findMaxSco(Integer maxsco);

}
