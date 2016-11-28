package com.paySystem.ic.dao.base;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.MerStoreApply;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.MerStoreApplyDTO;

/**
 * 
 * @ProjectName: backomall
 * @ClassName: MerStoreApplyDao
 * @Description: 商户入驻申请的的DAO层接口
 * @date: 2014-11-21 上午09:50:40
 * @author: 郭营
 * @version: V1.0
 */
public interface MerStoreApplyDao extends DAO<MerStoreApply> {
	public static final String MERSTOREAPPLYDAO = "merStoreApplyDao";

	/**
	 * 
	 *@Title: queryAll
	 *@Description: 查询商户入驻申请的数据
	 *@Params: @param page 起始页
	 *@Params: @param pageNum 终止页
	 *@Params: @param merStoreApplyDTO
	 *@Params: @param orderBy 排序方式
	 *@Params: @return
	 *@Params: @throws Exception
	 *@Return: QueryResult<MerStoreApplyDTO>
	 *@author: 郭营
	 *@Date: 2014-11-21上午09:23:12
	 */
	QueryResult<MerStoreApplyDTO> queryAll(int page, int pageNum,
			MerStoreApplyDTO merStoreApplyDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;

	/**
	 * 
	 *@Title: findById
	 *@Description: 通过ID查询商户入驻申请
	 *@Params: @param applyId 主键ID
	 *@Params: @return
	 *@Params: @throws Exception
	 *@Return: MerStoreApplyDTO
	 *@author: 郭营
	 *@Date: 2014-11-21上午09:49:09
	 */

	public MerStoreApplyDTO findById(Integer applyId) throws Exception;

	/**
	 * 
	 *@Title: saveMerStoreApply
	 *@Description: 批量审核商户入驻申请
	 *@Params: @param merStoreApplyDTO
	 *@Params: @throws Exception
	 *@Return: void
	 *@author: 郭营
	 *@Date: 2014-11-21上午09:49:55
	 */
	public void saveMerStoreApply(MerStoreApplyDTO merStoreApplyDTO)
			throws Exception;

}
