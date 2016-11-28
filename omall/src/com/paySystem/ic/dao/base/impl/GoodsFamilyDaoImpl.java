package com.paySystem.ic.dao.base.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsFamily;
import com.paySystem.ic.dao.base.GoodsFamilyDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ClassName:GoodsFamilyDaoImpl
 * @Description:商品分类接口实现类
 * @date: 2014-6-26下午04:27:09
 * @author: 张亚运
 * @version: V1.0
 */
@Repository(GoodsFamilyDao.GOODSFSMILYDAO)
public class GoodsFamilyDaoImpl extends DaoSupport<GoodsFamily> implements
		GoodsFamilyDao {

	public QueryResult<GoodsFamilyDTO> queryAll(int page, int pageNum,
			GoodsFamilyDTO goodsFamilyDto, LinkedHashMap<String, String> orderBy)
			throws Exception {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();

		/** 判断商品分类名称 */
		if (StringUtils.isNotBlank(goodsFamilyDto.getFamilyName())) {
			sql.append(" and o.familyName like ?").append(params.size() + 1);
			params.add("%" + goodsFamilyDto.getFamilyName().trim() + "%");
		}
		/** 判断关键字 */
		if (StringUtils.isNotBlank(goodsFamilyDto.getKeyWords())) {
			sql.append(" and o.keyWords like ?").append(params.size() + 1);
			params.add("%" + goodsFamilyDto.getKeyWords().trim() + "%");
		}

		sql.append(" and o.status = ?").append(params.size() + 1);
		params.add(goodsFamilyDto.getStatus());

		sql.append(" order by o.nodeLevel,o.parentId asc");

		QueryResult<GoodsFamilyDTO> queryResult = new QueryResult<GoodsFamilyDTO>();

		/** 将查询出的实体转换为Dto */
		QueryResult<GoodsFamily> result = new QueryResult<GoodsFamily>();
		result = getScrollData(page, pageNum, sql.toString(), params.toArray(),
				orderBy);
		List<GoodsFamilyDTO> list = new ArrayList<GoodsFamilyDTO>();
		for (int i = 0; i < result.getResultlist().size(); i++) {
			GoodsFamily gf = new GoodsFamily();
			gf = result.getResultlist().get(i);
			GoodsFamilyDTO gfDto = getDto(gf);
			list.add(gfDto);
		}

		queryResult.setTotalrecord(result.getTotalrecord());
		queryResult.setResultlist(list);
		return queryResult;
	}

	/**
	 *@Description:添加保存商品分类信息
	 *@param:@param goodsFamilyDto
	 *@param:@return
	 *@author: 张亚运
	 *@throws:
	 */
	public GoodsFamily saveGoodsFamily(GoodsFamilyDTO goodsFamilyDto) {

		UserSession us = Utils.getUserSession();
		GoodsFamily goodsfamily = new GoodsFamily();
		if (goodsFamilyDto != null) {
			goodsfamily.setParentId(goodsFamilyDto.getParentId());
			goodsfamily.setFamilyName(goodsFamilyDto.getFamilyName());
			goodsfamily.setKeyWords(goodsFamilyDto.getKeyWords());
			goodsfamily.setNodeLevel(goodsFamilyDto.getNodeLevel());
			goodsfamily.setPicPath(goodsFamilyDto.getPicPath());
			goodsfamily.setDefaultShow(goodsFamilyDto.getDefaultShow());
			goodsfamily.setFamilyWay(goodsFamilyDto.getFamilyWay());
			goodsfamily.setStatus(goodsFamilyDto.getStatus());
			goodsfamily.setCreateFloorSign(goodsFamilyDto.getCreateFloorSign());
			// 是否展示广告信息
			goodsfamily
					.setShowAdvertSign(goodsFamilyDto.getCreateFloorSign() == 0 ? new Integer(
							0)
							: goodsFamilyDto.getShowAdvertSign());
			goodsfamily.setCreateTime(this.getSysTime());
			goodsfamily.setOperator(us.getUserName());
			if (goodsFamilyDto.getNodeLevel() == 2) {
				goodsfamily.setChargeRate(goodsFamilyDto.getChargeRate());
				goodsfamily.setChargeRateType(goodsFamilyDto
						.getChargeRateType());
			}
			goodsfamily.setChargeRate(goodsFamilyDto.getChargeRate());
			goodsfamily.setChargeRateType(goodsFamilyDto.getChargeRateType());
			goodsfamily.setOrderSort(goodsFamilyDto.getOrderSort());
			this.save(goodsfamily);
			// this.em.persist(goodsfamily);

		}

		return goodsfamily;
	}

	/**
	 *@Description:修改保存商品分类信息
	 *@param:@param goodsFamilyDto
	 *@author: 张亚运
	 *@throws:
	 */
	public ReturnDTO updateGoodsFamily(GoodsFamilyDTO goodsFamilyDto)
			throws Exception {

		ReturnDTO returnDTO = new ReturnDTO();
		/** 获得要更新的数据 */
		GoodsFamily goodsfamily = this.find(goodsFamilyDto.getFamilyId());
		String picPath = goodsfamily.getPicPath();
		UserSession us = Utils.getUserSession();
		if (goodsfamily != null) {
			goodsfamily.setFamilyId(goodsFamilyDto.getFamilyId());
			goodsfamily.setParentId(goodsFamilyDto.getParentId());
			goodsfamily.setFamilyName(goodsFamilyDto.getFamilyName());
			goodsfamily.setKeyWords(goodsFamilyDto.getKeyWords());
			goodsfamily.setNodeLevel(goodsFamilyDto.getNodeLevel());
			goodsfamily.setDefaultShow(goodsFamilyDto.getDefaultShow());
			goodsfamily.setFamilyWay(goodsFamilyDto.getFamilyWay());
			goodsfamily.setStatus(goodsFamilyDto.getStatus());
			goodsfamily.setOperator(us.getUserName());
			goodsfamily.setPreFlag(goodsFamilyDto.getPreFlag());
			goodsfamily.setCreateFloorSign(goodsFamilyDto.getCreateFloorSign());
			// goodsfamily = (GoodsFamily) EntityDtoConverter.dto2Bean(
			// goodsFamilyDto, goodsfamily);
			goodsfamily.setOperator(us.getUserName());
			if (goodsFamilyDto.getPicPath() == null) {
				goodsfamily.setPicPath(picPath);
			} else {
				goodsfamily.setPicPath(goodsFamilyDto.getPicPath());
			}
			goodsfamily.setShowAdvertSign(goodsFamilyDto.getShowAdvertSign());
			goodsfamily.setChargeRate(goodsFamilyDto.getChargeRate());
			goodsfamily.setChargeRateType(goodsFamilyDto.getChargeRateType());
			goodsfamily.setOrderSort(goodsFamilyDto.getOrderSort());
		}
		this.update(goodsfamily);
		returnDTO.setFlag(true);
		return returnDTO;
	}

	/**
	 *@Description:将实体转换Dto
	 *@return:GoodsFamilyDTO
	 *@author: 张亚运
	 *@throws:
	 */
	public GoodsFamilyDTO getDto(GoodsFamily goodsFamily) {
		GoodsFamilyDTO dto = new GoodsFamilyDTO();
		dto.setFamilyId(goodsFamily.getFamilyId());
		dto.setFamilyName(goodsFamily.getFamilyName());
		dto.setFamilyWay(goodsFamily.getFamilyWay());
		dto.setCreateTime(goodsFamily.getCreateTime());
		dto.setDefaultShow(goodsFamily.getDefaultShow());
		dto.setPicPath(goodsFamily.getPicPath());
		dto.setKeyWords(goodsFamily.getKeyWords());
		dto.setNodeLevel(goodsFamily.getNodeLevel());
		dto.setOperator(goodsFamily.getOperator());
		dto.setParentId(goodsFamily.getParentId());
		dto.setStatus(goodsFamily.getStatus());
		dto.setPreFlag(goodsFamily.getPreFlag());
		return dto;
	}

	/**
	 *@Description:检验商品分类名称是否存在
	 *@param:@param familyName
	 *@param:@param familyId
	 *@param:@return
	 *@author: 张亚运
	 *@throws:
	 */
	public boolean validateName(String familyName, Integer familyId) {
		long count;
		if (familyId == null) {
			count = (Long) em
					.createQuery(
							"select count(o) from GoodsFamily o where o.familyName = ?1")
					.setParameter(1, familyName).getSingleResult();
		} else {
			count = (Long) em
					.createQuery(
							"select count(o) from GoodsFamily o where o.familyName = ?1 and o.familyId<>?2")
					.setParameter(1, familyName).setParameter(2, familyId)
					.getSingleResult();
		}
		return count > 0;
	}

	/**
	 *@Description:获取所属分类
	 *@param:@return
	 *@author: 张亚运
	 *@throws:
	 */
	@SuppressWarnings("unchecked")
	public List<OptionsInteger> getFamilyOption() {
		List<GoodsFamily> gfList = em
				.createQuery(
						"from GoodsFamily o where o.status = 1 order by o.familyId asc")
				.getResultList();
		List<OptionsInteger> list = new ArrayList<OptionsInteger>();
		for (GoodsFamily gf : gfList) {
			for (int i = 1; i < gf.getNodeLevel(); i++) {
				String sb = "..";
				gf.setFamilyName(sb + gf.getFamilyName());
			}
			list.add(new OptionsInteger(gf.getFamilyId(), gf.getFamilyName()));
		}
		return list;
	}

	/**
	 *@Description:获取纯洁的所属分类
	 *@return:List<GoodsFamily>
	 *@author: Jacky
	 *@throws:
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsFamily> getPureFamilyOption() {
		return em
				.createQuery(
						"from GoodsFamily o where o.status = 1 order by o.familyId asc")
				.getResultList();
	}

	/**
	 *@Description:批量获取分类列表
	 *@return:List<GoodsFamily>
	 *@author: Jacky
	 *@throws:
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsFamily> batchQueryGoodsFamily(Set<Integer> familyId) {
		String sql = getBatchSQL(familyId);
		return em.createQuery(sql).getResultList();
	}

	/**
	 *@Description: 拼接批量查询分类sql
	 *@return: String sql
	 *@author: Jacky
	 *@throws:
	 */
	private String getBatchSQL(Set<Integer> familyId) {
		StringBuilder sb = new StringBuilder(
				"from GoodsFamily o where o.status = 1 ");
		if (familyId.size() > 0) {
			sb.append(" and o.familyId in(");
			int i = 0;
			for (Integer family : familyId) {
				i++;
				sb.append(family);
				if (i < familyId.size()) {
					sb.append(",");
				}
			}
			sb.append(")");
		}
		return sb.toString();
	}

	public Integer getNodeLevel(Integer parentId) {
		Object nodeLevel = em
				.createNativeQuery(
						"select o.nodeLevel from b_goodsfamily o where o.familyId = ?1")
				.setParameter(1, parentId).getSingleResult();
		Integer NodeLevel = Integer.valueOf(nodeLevel.toString());
		return NodeLevel;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.base.GoodsFamilyDao#getFamilyByParent
	 *                        (int)
	 *@MethodName:getFamilyByParent
	 *@Description:根据父id得到下面的子分类
	 *@param parentId
	 *@return
	 *@throws Exception
	 *@Author:毛智东
	 *@Date:2014-11-7上午09:45:15
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsFamilyDTO> getFamilyByParent(int parentId)
			throws Exception {
		List<GoodsFamilyDTO> lists = new ArrayList<GoodsFamilyDTO>();
		// String sql = "from GoodsFamily o where o.parentId=?";
		String sql = "from GoodsFamily o where o.parentId=? and o.status = 1";
		Query query = em.createQuery(sql);
		query.setParameter(1, parentId);
		List<GoodsFamily> list = query.getResultList();
		for (GoodsFamily gf : list) {
			GoodsFamilyDTO dto = new GoodsFamilyDTO();
			dto = (GoodsFamilyDTO) EntityDtoConverter.bean2Dto(gf, dto);
			lists.add(dto);
		}
		return lists;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.base.GoodsFamilyDao#saveExGoodsFamily
	 *                        (com.paySystem.ic.web.dto.base.GoodsFamilyDTO)
	 *@MethodName:saveExGoodsFamily
	 *@Description:保存商品分类并返回保存后的DTO对象
	 *@param goodsFamilyDTO
	 *@return
	 *@throws Exception
	 *@Author:张军磊
	 *@Date:2014-11-20下午05:12:12
	 */
	public GoodsFamilyDTO saveExGoodsFamily(GoodsFamilyDTO goodsFamilyDTO)
			throws Exception {

		GoodsFamily goodsFamily = new GoodsFamily();
		EntityDtoConverter.dto2Bean(goodsFamilyDTO, goodsFamily);
		this.save(goodsFamily);
		GoodsFamilyDTO reGoodsFamilyDTO = new GoodsFamilyDTO();
		EntityDtoConverter.bean2Dto(goodsFamily, reGoodsFamilyDTO);
		return reGoodsFamilyDTO;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.base.GoodsFamilyDao#findGoodsFamilyByName
	 *                        (java.lang.String)
	 *@MethodName:findGoodsFamilyByName
	 *@Description:根据商品分类名称查询商品分类信息
	 *@param familyName
	 *@return
	 *@throws Exception
	 *@Author:张军磊
	 *@Date:2014-11-20下午05:27:10
	 */
	public List<GoodsFamilyDTO> findGoodsFamilyByName(String familyName)
			throws Exception {
		List<GoodsFamilyDTO> lists = new ArrayList<GoodsFamilyDTO>();
		// String sql = "from GoodsFamily o where o.parentId=?";
		String sql = "from GoodsFamily o where o.familyName=? and o.status = 1";
		Query query = em.createQuery(sql);
		query.setParameter(1, familyName);
		List<GoodsFamily> list = query.getResultList();
		for (GoodsFamily gf : list) {
			GoodsFamilyDTO dto = new GoodsFamilyDTO();
			dto = (GoodsFamilyDTO) EntityDtoConverter.bean2Dto(gf, dto);
			lists.add(dto);
		}
		return lists;
	}

	/**
	 * 根据商品分类名称获取分类信息
	 * 
	 *@Title:findByFamilyName
	 *@Description:根据商品分类名称获取分类信息
	 *@param:@param familyName
	 *@param:@return
	 *@return:List<GoodsFamilyAdvertRelation>
	 *@author:谢洪飞
	 *@Thorws:
	 */
	public List<GoodsFamily> findByFamilyName(String familyName) {

		// String sql = "from GoodsFamily o where o.parentId=?";
		String sql = "from GoodsFamily o where o.familyName=? ";
		Query query = em.createQuery(sql);
		query.setParameter(1, familyName);
		List<GoodsFamily> list = query.getResultList();
		return list;
	}

	/**
	 *@MethodName:updateOrderSort
	 *@Description:通过主键和排序号将大于这个分类名的排序号向后移
	 *@param familyName
	 *@param orderSort
	 *@Author:孟凡岭
	 *@Date:2014-12-2下午03:24:26
	 */
	public void updateOrderSort(Integer familyId, Integer parentId,
			Integer orderSort) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb
				.append("update  b_GoodsFamily b set b.orderSort=b.orderSort+1 where 1=1 ");
		sb
				.append(" and b.orderSort>=?1 and b.parentId=?2  and b.familyId<>?3 and b.orderSort is not null");
		Query query = this.em.createNativeQuery(sb.toString());
		query.setParameter(1, orderSort);
		query.setParameter(2, parentId);
		query.setParameter(3, familyId);
		query.executeUpdate();
	}

	/**
	 *@MethodName:ajaxObject
	 *@Description:验证广告对象
	 *@param id
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-12-8上午11:26:35
	 */
	public boolean ajaxObject(String id) {
		// TODO Auto-generated method stub
		GoodsFamily g= this.find(Integer.valueOf(id));
		if(g!=null){
			return true;
		}else{
			return false;
		}
	}

}
