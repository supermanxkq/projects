/**  
 * @Title: GoodsFamilyAdvertRelationService.java
 * @Package: com.paySystem.ic.service.base
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-12-3 上午10:10:43
 * @Version: V1.0  
 */
package com.paySystem.ic.service.base;

import com.paySystem.ic.web.dto.base.GoodsFamilyAdvertRelationDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyAttrRelaDTO;

/**
 * @ProjectName:omallBackstage
 * @ClassName:GoodsFamilyAdvertRelationService
 * @Description:TODO
 * @date: 2014-12-3
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface GoodsFamilyAdvertRelationService {
	public static final String GOODSFAMILYADVERTRELATIONSERVICE = "goodsFamilyAdvertRelationService";
	/**
	 * 
	*@Title:findByFamilyId
	*@Description:通过商品分类ID查询
	*@Params:@param familyId
	*@Params:@return
	*@Return:GoodsFamilyAttrRelaDTO
	*@author:孟凡岭
	*@Date:2014-12-3上午09:36:58
	 */
	public GoodsFamilyAdvertRelationDTO findByFamilyId(Integer familyId) throws Exception;
}
