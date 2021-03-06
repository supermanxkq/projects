package com.paySystem.ic.service.base;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsFamily;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
import com.paySystem.ic.web.dto.goods.GoodsTypeDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ClassName:GoodsFamilyService
 * @Description:商品分类Service
 * @date: 2014-6-26下午03:31:19
 * @author: 张亚运
 * @version: V1.0
 */

public interface GoodsFamilyService {

	public static final String GOODSFAMILYSERVICE = "goodsFamilyService";

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
	 *@Description:添加保存商品分类信息
	 *@return:GoodsFamily
	 *@author: 张亚运
	 *@throws:
	 */
	public GoodsFamily saveGoodsFamily(GoodsFamilyDTO goodsFamilyDto) throws Exception ;

	/**
	 *@Description:删除商品分类信息（更新状态）
	 *@return:void
	 *@author: 张亚运
	 *@throws:
	 */
	public void deleteGoodsFamily(Integer familyId);

	/**
	 *@Description:根据编号查询出该条信息
	 *@return:GoodsFamilyDTO
	 *@author: 张亚运
	 *@throws:
	 */
	public GoodsFamilyDTO find(Integer familyId);

	/**
	 *@Description:修改保存商品分类信息
	 *@return:ReturnDTO
	 *@author: 张亚运
	 *@throws:
	 */
	public ReturnDTO updateGoodsFamily(GoodsFamilyDTO goodsfamilyDto)  throws Exception;

	/**
	 *@Description:判断名字是否存在
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
	 *@Description:获取商品分类包含父子关系的列表
	 *@return:List<OptionsString>
	 *@author: Jacky
	 *@throws:
	 */
	public List<GoodsTypeDTO> getFamilyCategoryList();

	/**
	 *@Description: 批量获取分类对应的类型名称
	 *@param typeIds
	 *            批量类型id
	 *@return:List<OptionsString>
	 *@author: Jacky
	 *@throws:
	 */
	public Map<String, String> batchQueryFamilyCategoryMap(Set<Integer> typeIds);

	/**
	 *@Description:根据选择的分类获取节点等级
	 *@return:Integer
	 *@author: 张亚运
	 *@throws:
	 */
	public Integer getNodeLevel(Integer parentId);

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
	public GoodsFamilyDTO saveExGoodsFamily(GoodsFamilyDTO goodsFamilyDTO)throws Exception;
	/**
	*@Title:findGoodsFamilyByName
	*@Description:根据商品分类名称查询商品分类信息
	*@Params:@param familyName
	*@Params:@return
	*@Return:List<GoodsFamilyDTO>
	*@author:张军磊
	*@Date:2014-11-20下午05:25:16
	*/
	public List<GoodsFamilyDTO> findGoodsFamilyByName(String familyName)throws Exception;

	/**
	*@Title:ajaxObject
	*@Description:验证广告对象
	*@Params:@param id
	*@Return:void
	*@author:孟凡岭
	*@Date:2014-12-8上午11:25:12
	*/
	public boolean ajaxObject(String id);
}
