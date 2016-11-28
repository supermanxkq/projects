package com.paySystem.ic.dao.goods.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.goods.MerUnrule;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.goods.MerUnruleDAO;
import com.paySystem.ic.web.dto.goods.MerUnruleDTO;

/**
 * 
 * @ProjectName:omall
 * @ClassName:MerUnruleDAOImpl
 * @Description:违规类型dao实现类
 * @date: 2014-8-26
 * @author:Jacky
 * @version: V1.0
 */
@Repository(MerUnruleDAO.MERUNRULE)
public class MerUnruleDAOImpl extends DaoSupport<MerUnrule> implements MerUnruleDAO {
	
	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.MerUnruleDAO#save(com.paySystem.ic.bean.goods.MerUnrule)
	 *@MethodName:save
	 *@Description:保存
	 *@param merUnrule 违规对象
	 *@throws Exception
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:17:35
	 */
	public void save(MerUnruleDTO merUnrule) throws Exception {
		super.save(dto2Bean(merUnrule));
	}
	
	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.MerUnruleDAO#findMerUnruleDTOListByGoodsId(Long)
	 *@MethodName: findMerUnruleDTOListByGoodsId
	 *@Description: 根据商品id查询违规记录
	 *@param Long 商品id
	 *@throws Exception
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:17:35
	 */
	@SuppressWarnings("unchecked")
	public List<MerUnruleDTO> findMerUnruleDTOListByGoodsId(Long goodsId) throws Exception {
		List<MerUnrule> resultList = em.createQuery("from MerUnrule o where o.goodsId = ? order by o.punishTime desc").setParameter(1, goodsId).getResultList();
		return batchCopy(resultList);
	}

	/**
	 *@MethodName: dto2Bean
	 *@Description: dto转bean
	 *@param MerUnruleDTO 违规dto
	 *@throws Exception
	 *@return MerUnrule 实体
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:17:35
	 */
	private MerUnrule dto2Bean(MerUnruleDTO merUnrule) {
		MerUnrule mu = new MerUnrule();
		BeanUtils.copyProperties(merUnrule, mu);
		return mu;
	}
	
	/**
	 *@MethodName: batchCopy
	 *@Description: 批量bean转dto
	 *@param List<MerUnrule> 违规实体list
	 *@throws Exception
	 *@return List<MerUnruleDTO>  违规dto
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:17:35
	 */
	public  List<MerUnruleDTO> batchCopy(List<MerUnrule> merUnRuleList) {
		if(CollectionUtils.isNotEmpty(merUnRuleList)) {
			List<MerUnruleDTO> dtoList = new ArrayList<MerUnruleDTO>();
			for(MerUnrule merun : merUnRuleList) {
				MerUnruleDTO dto = new MerUnruleDTO();
				BeanUtils.copyProperties(merun,dto);
				dtoList.add(dto);
			}
			return dtoList;
		}
		return null;
	}
	
}
