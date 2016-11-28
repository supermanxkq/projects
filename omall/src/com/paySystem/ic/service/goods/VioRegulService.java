/**  
* @Title: VioRegulService.java
* @Package: com.paySystem.ic.service.goods
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-11-21 下午04:16:06
* @Version: V1.0  
*/
package com.paySystem.ic.service.goods;

import java.util.List;

import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.web.ui.OptionsInteger;


/**
 * @ProjectName:omallBackstage
 * @ClassName:VioRegulService
 * @Description:TODO
 * @date: 2014-11-21
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface VioRegulService {
	public static final String VIOREGULSERVICE="vioRegulService";

	/**
	*@Title:find
	*@Description:TODO
	*@Params:@return
	*@Return:List<OptionsInteger>
	*@author:孟凡岭
	*@Date:2014-11-21下午04:19:33
	*/
	List<OptionsInteger> find() throws Exception;
}
