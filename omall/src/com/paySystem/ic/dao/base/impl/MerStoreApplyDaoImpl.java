package com.paySystem.ic.dao.base.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.MerStoreApply;
import com.paySystem.ic.dao.base.MerStoreApplyDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.base.MerStoreApplyDTO;
/**
 * 
 * @ProjectName: backomall
 * @ClassName: MerStoreApplyDao
 * @Description: 商户入驻申请的的DAO层接口的实现
 * @date: 2014-11-21 上午09:50:40
 * @author: 郭营
 * @version: V1.0
 */
@Repository(MerStoreApplyDao.MERSTOREAPPLYDAO)
public class MerStoreApplyDaoImpl extends DaoSupport<MerStoreApply> implements MerStoreApplyDao {


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
	@Override
	public QueryResult<MerStoreApplyDTO> queryAll(int page, int pageNum,
			MerStoreApplyDTO merStoreApplyDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {

		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		if (StringUtils.isNotBlank(merStoreApplyDTO.getCardNo())) {
			sb.append(" and o.cardNo like ?").append(params.size() + 1);
			params.add("%" + merStoreApplyDTO.getCardNo().trim() + "%");
		}
		
		if (StringUtils.isNotBlank(merStoreApplyDTO.getPhone())) {
			sb.append(" and phone like ?").append(params.size() + 1);
			params.add("%" + merStoreApplyDTO.getPhone().trim() + "%");
		}
		if (StringUtils.isNotBlank(merStoreApplyDTO.getEmail())) {
			sb.append(" and email like ?").append(params.size() + 1);
			params.add("%" + merStoreApplyDTO.getEmail().trim() + "%");
		}
		
			sb.append(" and o.status = 0");
		
		QueryResult<MerStoreApply> queryResult = this.getScrollData(page,
				pageNum, sb.toString(), params.toArray(), orderBy);
		List<MerStoreApply> list = queryResult.getResultlist();
		List<MerStoreApplyDTO> dtoList = new ArrayList<MerStoreApplyDTO>();

		for (MerStoreApply merStoreApply : list) {
			MerStoreApplyDTO msdto = (MerStoreApplyDTO) (EntityDtoConverter
					.bean2Dto(merStoreApply, new MerStoreApplyDTO()));
			msdto.setPage(merStoreApplyDTO.getPage());
			dtoList.add(msdto);
		}
		QueryResult<MerStoreApplyDTO> queryResultDTO = new QueryResult<MerStoreApplyDTO>();
		queryResultDTO.setResultlist(dtoList);
		queryResultDTO.setTotalrecord(queryResult.getTotalrecord());

		return queryResultDTO;
	
		
	}

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

	@Override
	public MerStoreApplyDTO findById(Integer applyId) throws Exception  {
		return (MerStoreApplyDTO) EntityDtoConverter.bean2Dto(this.find(applyId), new MerStoreApplyDTO());
	}
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
	@Override
	public void saveMerStoreApply(MerStoreApplyDTO merStoreApplyDTO)
			throws Exception {
		this.update(EntityDtoConverter.dto2Bean(merStoreApplyDTO, new MerStoreApply()));
	}

}
