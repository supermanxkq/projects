/**  
* @Title: GoodsFamilyRateRelDao.java
* @Package: com.paySystem.ic.dao.base
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-12-10 上午09:12:16
* @Version: V1.0  
*/
package com.paySystem.ic.dao.base;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.paySystem.ic.bean.base.GoodsFamilyRateRel;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;

/**
 * @ProjectName:omallBackstage
 * @ClassName:GoodsFamilyRateRelDao
 * @Description:商品分类 & 手续费率Dao接口
 * @date: 2014-12-10
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface GoodsFamilyRateRelDao extends DAO<GoodsFamilyRateRel>{
	public static final String GOODSFAMILYRATERELDAO="goodsFamilyRateRelDao";

	/**
	 * 
	*@Title:findByFamilyId
	*@Description:通过分类编号查询
	*@Params:@param familyId
	*@Params:@return
	*@Return:Map<Integer,GoodsFamilyRateRel>
	*@author:孟凡岭
	*@Date:2014-12-10上午09:57:56
	 */
	Map<Integer, GoodsFamilyRateRel> findByFamilyId(Integer familyId);

	/**
	*@Title:deleteByFamilyId
	*@Description:通过分类编号删除数据
	*@Params:@param familyId
	*@Return:void
	*@author:孟凡岭
	*@Date:2014-12-10上午10:08:49
	*/
	void deleteByFamilyId(Integer familyId);

	/**
	*@Title:updateByFamilyId
	*@Description:更新数据
	*@Params:@param goodsfamilyDto
	*@Return:void
	*@author:孟凡岭
	 * @param map 
	*@Date:2014-12-10上午10:12:43
	*/
	void updateByFamilyId(GoodsFamilyDTO goodsfamilyDto, Map<Integer, GoodsFamilyRateRel> map);
}
