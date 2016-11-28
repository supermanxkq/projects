package com.paySystem.ic.dao.base;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.hibernate.classic.Validatable;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsFamily;
import com.paySystem.ic.bean.base.GoodsFamilyAdvertRelation;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ClassName:GoodsFamilyDao
 * @Description:商品分类接口
 * @date: 2014-6-26下午03:29:12
 * @author: 张亚运
 * @version: V1.0
 */
public interface GoodsFamilyDao extends DAO<GoodsFamily> {

	public static final String GOODSFSMILYDAO = "goodsFamilyDao";

	/**
	 *@Description:查询商品分类信息
	 *@return:QueryResult<GoodsFamily>
	 *@author: 张亚运
	 *@throws:
	 */
	public QueryResult<GoodsFamilyDTO> queryAll(int page, int pageNum,
			GoodsFamilyDTO goodsFamilyDto, LinkedHashMap<String, String> orderBy)
			throws Exception;

	/**
	 *@Description:保存商品分类信息
	 *@return:GoodsFamily
	 *@author: 张亚运
	 *@throws:
	 */
	public GoodsFamily saveGoodsFamily(GoodsFamilyDTO goodsFamilyDto);

	/**
	 *@Description:修改保存商品分类信息
	 *@return:void
	 *@author: 张亚运
	 *@throws:
	 */
	public ReturnDTO updateGoodsFamily(GoodsFamilyDTO goodsFamilyDto)  throws Exception;

	/**
	 *@Description:检验商品分类名称是否存在
	 *@return:boolean
	 *@author: 张亚运
	 * @param sign
	 *@throws:
	 */
	public boolean validateName(String familyName, Integer familyId);

	/**
	 *@Description:获取所属分类
	 *@return:List<OptionsString>
	 *@author: 张亚运
	 *@throws:
	 */
	public List<OptionsInteger> getFamilyOption();

	/**
	 *@Description:获取纯洁的所属分类
	 *@return:List<GoodsFamily>
	 *@author: Jacky
	 *@throws:
	 */
	public List<GoodsFamily> getPureFamilyOption();

	/**
	 *@Description:根据所选分类获取该节点等级
	 *@return:Integer
	 *@author: 张亚运
	 *@throws:
	 */
	public Integer getNodeLevel(Integer parentId);

	/**
	 *@Description:批量获取分类列表
	 *@return:List<GoodsFamily>
	 *@author: Jacky
	 *@throws:
	 */
	public List<GoodsFamily> batchQueryGoodsFamily(Set<Integer> familyId);

	/**
	 * 
	 *@Title:getFamilyByParent
	 *@Description:根据父id得到下面的子分类
	 *@Params:@param parentId
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:List<GoodsFamilyDTO>
	 *@author:毛智东
	 *@Date:2014-11-7上午09:44:28
	 */
	public List<GoodsFamilyDTO> getFamilyByParent(int parentId)
			throws Exception;

	/**
	 *@Title:saveExGoodsFamily
	 *@Description:保存商品分类，并将保存的Dto 返回
	 *@Params:@param goodsFamilyDTO
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:GoodsFamilyDTO
	 *@author:张军磊
	 *@Date:2014-11-20下午05:00:26
	 */
	public GoodsFamilyDTO saveExGoodsFamily(GoodsFamilyDTO goodsFamilyDTO)
			throws Exception;

	/**
	 *@Title:findGoodsFamilyByName
	 *@Description:根据商品分类名称查询商品分类信息
	 *@Params:@param familyName
	 *@Params:@return
	 *@Return:List<GoodsFamilyDTO>
	 *@author:张军磊
	 *@Date:2014-11-20下午05:25:16
	 */
	public List<GoodsFamilyDTO> findGoodsFamilyByName(String familyName)
			throws Exception;

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
	public List<GoodsFamily> findByFamilyName(String familyName);

	/**
	 *@Title:updateOrderSort
	 *@Description:通过主键和排序号将大于这个分类名的排序号向后移
	 *@Params:@param familyName
	 *@Params:@param orderSort
	 *@Return:void
	 *@author:孟凡岭
	 * @param familyId 
	 *@Date:2014-12-2下午03:22:43
	 */
	public void updateOrderSort(Integer familyId, Integer parentId,Integer orderSort)  throws Exception;

	/**
	*@Title:ajaxObject
	*@Description:验证广告对象
	*@Params:@param id
	*@Params:@return
	*@Return:boolean
	*@author:孟凡岭
	*@Date:2014-12-8上午11:26:19
	*/
	public boolean ajaxObject(String id);

}
