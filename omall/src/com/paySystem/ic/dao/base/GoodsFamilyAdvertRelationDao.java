package com.paySystem.ic.dao.base;

import com.paySystem.ic.bean.base.GoodsFamilyAdvertRelation;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.GoodsFamilyAdvertRelationDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
/**
 *  商品分类广告关联Dao接口
 * 
 * @author 谢
 *
 */
public interface GoodsFamilyAdvertRelationDao extends DAO<GoodsFamilyAdvertRelation> {

	public GoodsFamilyAdvertRelation saveRelation(GoodsFamilyDTO goodFamilyDTO)  throws Exception;
	
	public static final String GOODADVREL = "goodsFamilyAdvertRelationDao";
	/**
	 * 
	* @Title: deleteByFamilyId 
	* @Description: 根据商品分类ID删除楼层数据
	* @return void    返回类型 
	* @throws
	 */
	public void deleteByFamilyId(Integer familyId);
	/**
	 * 
	* @Title: findByFamilyId 
	* @Description: 根据familyId查询楼层数据
	* @return GoodsFamilyAdvertRelation    返回类型 
	* @throws
	 */
	public GoodsFamilyAdvertRelationDTO findByFamilyId(Integer familyId) throws Exception;
	
}
