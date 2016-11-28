package com.paySystem.ic.dao.base.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.MerScoMo;
import com.paySystem.ic.dao.base.MerScoMoDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.web.dto.base.MerScoMoDTO;

/**
 * @ClassName:MerScoMoDaoImpl
 * @Description:商户评分模型Dao实现类
 * @date: 2014-9-23
 * @author: 张军磊
 * @version: V1.0
 */
@Repository(MerScoMoDao.MERSCOMODAO)
public class MerScoMoDaoImpl extends DaoSupport<MerScoMo> implements
		MerScoMoDao {

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.base.MerScoMoDao#deleteByMerScoMoId
	 *                        (java.lang.Integer)
	 *@MethodName:deleteByMerScoMoId
	 *@Description:根据ID删除商户评分模型
	 *@param merScoMoId
	 *@return
	 *@throws Exception
	 *@Author:张军磊
	 *@Date:2014-9-30上午09:59:16
	 */
	public MerScoMoDTO deleteByMerScoMoId(Integer merScoMoId) throws Exception {

		// 根据Id获取物流实体对象
		MerScoMo merScoMo = this.find(merScoMoId);

		// 更改状态为"9"删除状态
		merScoMo.setStatus(9);

		// 更新修改内容，实现逻辑删除操作
		this.update(merScoMo);

		// Bean2DTO
		MerScoMoDTO merScoMoDTODto = this.bean2DTO(merScoMo);

		return merScoMoDTODto;

	}

	/**
	 *@Title:saveMerScoMo
	 *@Description: 保存商户评分模型业务
	 *@param:@param merScoMo 商户评分模型业务参数实体对象
	 *@Return:void
	 *@author: 张军磊
	 *@Thorws:
	 */
	@SuppressWarnings("unchecked")
	public void saveMerScoMo(MerScoMo merScoMo) {
		this.save(merScoMo);

	}

	/**
	 * 
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.base.MerScoMoDao#updateMerScoMo
	 *                        (com.paySystem.ic.bean.base.MerScoMo)
	 *@MethodName:updateMerScoMo
	 *@Description: 更新商户评分模型业务参数信息
	 *@param merScoMo
	 *@Author:张军磊
	 *@Date:2014-9-30上午10:05:07
	 */
	public void updateMerScoMo(MerScoMo merScoMo) {
		this.update(merScoMo);

	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.base.MerScoMoDao#queryMerScoMoByCond
	 *                        (int, int,
	 *                        com.paySystem.ic.web.dto.base.MerScoMoDTO,
	 *                        java.util.LinkedHashMap) /**
	 *@Title:queryMerScoMoByCond
	 *@Description:根据条件查询商户评分模型记录
	 *@param:@param fristindex 起始
	 *@param:@param pageNum 每页显示数
	 *@param:@param merScoMoDTO 页面Dto对象
	 *@param:@param orderBy 排序方式Map
	 *@param:@return
	 *@param:@throws Exception
	 *@return:QueryResult 返回QueryResult对象
	 *@thorws:
	 */

	public QueryResult<MerScoMoDTO> queryMerScoMoByCond(int fristindex,
			int pageNum, MerScoMoDTO merScoMoDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		QueryResult<MerScoMoDTO> dtoResult = new QueryResult<MerScoMoDTO>();
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		//sb.append(" and o.status = 1");

		// 根据分数查询
		if (merScoMoDTO.getMinSco() != null && merScoMoDTO.getMaxSco() != null) {
			if (merScoMoDTO.getMinSco() > merScoMoDTO.getMaxSco()) {

				sb.append("  and o.minSco >= '" + merScoMoDTO.getMaxSco()
						+ "' and o.maxSco <=  '" + merScoMoDTO.getMinSco()
						+ "'");

			} else {

				sb.append("  and o.minSco >= '" + merScoMoDTO.getMinSco()
						+ "' and o.maxSco <=  '" + merScoMoDTO.getMaxSco()
						+ "'");

			}
		}
		// 根据名称查询
		if (StringUtils.isNotBlank(merScoMoDTO.getMoName())) {
			sb.append(" and o.moName like ?").append(params.size() + 1);
			params.add("%" + merScoMoDTO.getMoName().trim() + "%");
		}

		// 执行查询，获取QueryResult信息
		QueryResult<MerScoMo> result = getScrollData(fristindex, pageNum, sb
				.toString(), params.toArray(), orderBy);

		dtoResult = this.dtoResult2beanResult(result);

		return dtoResult;

	}

	/**
	 * BeanResult转 实体DTOResult
	 * 
	 *@Title:dtoResult2beanResult
	 *@Description:BeanResult转 实体DTOResult
	 *@param:@param result 实体BeanResult
	 *@param:@return
	 *@return:QueryResult<MerScoMoDTO> DTO类型Result信息
	 *@author: 张军磊
	 *@Thorws:
	 */
	private QueryResult<MerScoMoDTO> dtoResult2beanResult(
			QueryResult<MerScoMo> result) {

		QueryResult<MerScoMoDTO> dtoResult = new QueryResult<MerScoMoDTO>();
		List<MerScoMoDTO> dtoList = new ArrayList<MerScoMoDTO>();

		if (result != null) {

			// 遍历实体Result信息，获取支付参数实体对象
			for (MerScoMo merScoMo : result.getResultlist()) {

				// 将商户评分模型实体对象转为商户评分模型DTO对象
				MerScoMoDTO merScoMoDTO = new MerScoMoDTO();
				merScoMoDTO = bean2DTO(merScoMo);
				// 添加到DTO对象集合中
				dtoList.add(merScoMoDTO);

			}

		}
		// 组装DtoResult信息 DTO对象集合及总条数
		dtoResult.setResultlist(dtoList);
		dtoResult.setTotalrecord(result.getTotalrecord());

		return dtoResult;
	}

	/**
	 * 实体Bean 转 DTO
	 * 
	 *@Title:bean2DTO
	 *@Description: 实体Bean 转 DTO
	 *@param:@param merScoMo 商户评分模型对象
	 *@param:@return
	 *@return:LogisticsDTO 商户评分模型DTO对象
	 *@author:张军磊
	 *@Thorws:
	 */
	private MerScoMoDTO bean2DTO(MerScoMo merScoMo) {

		MerScoMoDTO merScoMoDTO = new MerScoMoDTO();

		if (merScoMo != null) {

			merScoMoDTO.setMerScoMoId(merScoMo.getMerScoMoId());
			merScoMoDTO.setMoName(merScoMo.getMoName());
			merScoMoDTO.setMaxSco(merScoMo.getMaxSco());
			merScoMoDTO.setMinSco(merScoMo.getMinSco());
			merScoMoDTO.setOperatorId(merScoMo.getOperatorId());
			merScoMoDTO.setShowPic(merScoMo.getShowPic());
			merScoMoDTO.setUpdateTime(merScoMo.getUpdateTime());
			merScoMoDTO.setStatus(merScoMo.getStatus());
		}

		return merScoMoDTO;
	}

	/**
	 * 
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.base.MerScoMoDao#findById(java
	 *                        .lang.Integer)
	 *@MethodName:findById
	 *@Description:根据ID查询商户评分模型DTO对象
	 *@param merScoMoId
	 *@return MerScoMoDTO
	 *@Author:张军磊
	 *@Date:2014-9-30上午10:10:08
	 */
	public MerScoMoDTO findById(Integer merScoMoId) {

		MerScoMo merScoMo = new MerScoMo();

		MerScoMoDTO merScoMoDTO = new MerScoMoDTO();
		// 根据ID获取物流公司实体对象
		merScoMo = this.find(merScoMoId);
		// Bean2DTO
		merScoMoDTO = this.bean2DTO(merScoMo);

		return merScoMoDTO;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.base.MerScoMoDao#findmerScoMoName
	 *                        (java.lang.String)
	 *@MethodName:findmerScoMoName
	 *@Description:根据商户评分模型名称查询实体对象
	 *@param MoName
	 *@return
	 *@Author:张军磊
	 *@Date:2014-9-30上午10:11:33
	 */
	@SuppressWarnings("unchecked")
	public List<MerScoMo> findmerScoMoName(String MoName) {
		String sql = "select o from MerScoMo o where  o.moName =?";

		Query query = em.createQuery(sql);
		query.setParameter(1, MoName);

		return query.getResultList();
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.base.MerScoMoDao#findMaxSco(java
	 *                        .lang.Integer)
	 *@MethodName:findMaxSco
	 *@Description:根据评分查询实体对象
	 *@param maxsco
	 *@return
	 *@Author:张军磊
	 *@Date:2014-9-30上午10:14:10
	 */
	public List<MerScoMo> findMaxSco(Integer maxsco) {
		String sql = "select o from MerScoMo o where o.minSco <="+maxsco+" and o.maxSco>="+maxsco+"and status=1";
		Query query = em.createQuery(sql);

		return query.getResultList();
	}

}
