package com.paySystem.ic.dao.base;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.SingleRelation;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.MerchantsDTO;
import com.paySystem.ic.web.dto.base.SingleRelationDTO;

/**
 * @ClassName:SingleRelationDao
 * @Description:收单关系DAO接口
 * @date: 2013-12-7下午02:25:52
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface SingleRelationDao extends DAO<SingleRelation> {

	/**
	 * 保存一条收单关系记录
	 * @param singleRelationDTO 收单关系DTO对象
	 * @return SingleRelation singleRelation
	 */
	public SingleRelation saveSingleRelation(SingleRelationDTO singleRelationDTO);
	
	/**
	 *@Title:initMainOrgSingRel
	 *@Description:初始化商户与主机构的收单关系
	 *@param:@param merchantDto
	 *@return:void
	 *@author: 谢
	 *@thorws:
	 */
	void initMainOrgSingRel(MerchantsDTO merchantDto,Merchants merchants);
	
	/**
	 * 更新一条收单记录方法
	 * @param singleRelationDTO 收单关系DTO对象
	 * @return
	 */
	public ReturnDTO updateSingleRelation(SingleRelationDTO singleRelationDTO);
	
	/**
	 *@Title:updateSingleRelation
	 *@Description:更新收单关系信息
	 *@param:@param merchantsDto
	 *@return:void
	 *@author:谢
	 *@thorws:
	 */
	void updateSingleRelation(MerchantsDTO merchantsDto);
	
	/**
	 *@Title:querySingleRelByCond
	 *@Description:根据条件查询收单关系记录
	 *@param:@param fristindex 起始
	 *@param:@param pageNum 每页显示数
	 *@param:@param terminalsDTO 页面Dto对象
	 *@param:@param orderBy 排序方式Map
	 *@param:@return
	 *@param:@throws Exception
	 *@return:QueryResult  返回QueryResult对象
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	QueryResult querySingleRelByCond(int fristindex, int pageNum,
			SingleRelationDTO singleRelationDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;
	
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
	
	
	public static final String SINGLERELATIONDAO = "singleRelationDao";
	
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
	 *@Title:querySingleRel
	 *@Description:查询收单关系
	 *@param:@return
	 *@return:SingleRelation
	 *@author:谢
	 *@thorws:
	 */
	public SingleRelation querySingleRel(String merId,String organId);
	
}
