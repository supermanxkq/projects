package com.paySystem.ic.service.base;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.SingleRelation;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.SingleRelationDTO;

public interface SingleRelationService {
	
	
	public static final String SINRELSERVICE = "singleRelationService";
	
	/**
	 * 保存一条收单关系记录方法
	 * @param singleRelationDTO 收单关系DTO对象
	 * @return SingleRelation singleRelation
	 */
	public SingleRelation saveSingleRelation(SingleRelationDTO singleRelationDTO) throws Exception;
	
	
	/**
	 *@Title:find
	 *@Description:根据收单编号查询收单关系
	 *@param:@param sinRelId
	 *@param:@return
	 *@return:SingleRelation
	 *@thorws:
	 */
	SingleRelation find(String sinRelId);
	/**
	 * 更新一条收单记录方法
	 * @param singleRelationDTO 收单关系DTO对象
	 * @return
	 */
	public ReturnDTO updateSingleRelation(SingleRelationDTO singleRelationDTO) throws Exception;
	
	/**
	 *@Title:deleteSingleRelation
	 *@Description:删除收单关系（逻辑删除，更改收单关系启用状态）
	 *@param:@param sinRelId
	 *@return:void
	 *@thorws:
	 */
	void deleteSingleRelation(String sinRelId);
	
	/**
	 *@Title:querySingleRelByCond
	 *@Description:按照条件查询收单关系信息
	 *@param:@param fristindex
	 *@param:@param pageNum
	 *@param:@param singleRelationDTO
	 *@param:@param orderBy
	 *@param:@return
	 *@param:@throws Exception
	 *@return:QueryResult
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public QueryResult querySingleRelByCond(int fristindex, int pageNum,
			SingleRelationDTO singleRelationDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;
	/**
	 *@Title:findByMerIdBinId
	 *@Description:根据商户号与卡BIN号查找收单关系
	 *@param:@param merId
	 *@param:@param binId
	 *@param:@return
	 *@return:SingleRelation
	 *@author:谢
	 *@thorws:
	 */
	public SingleRelation findByMerIdBinId(String merId);

	/**
	 *@Title:checkExsisSinRel
	 *@Description:检查是否已经存在该收单关系
	 *@param:@param singleRelationDTO
	 *@param:@return
	 *@return:boolean
	 *@author: 谢
	 *@thorws:
	 */
	public boolean checkExsisSinRel(SingleRelationDTO singleRelationDTO);
	
	/**
	 *@Title:checkExsisCardBin
	 *@Description:根据机构ID查询是否存在卡BIN
	 *@param:@param organId
	 *@param:@return
	 *@return:boolean
	 *@author: 谢
	 *@thorws:
	 */
	public boolean checkExsisCardBin(String organId);
}
