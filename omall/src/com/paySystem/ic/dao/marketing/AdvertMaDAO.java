/**  
 * @Title: AdvertMaDAO.java
 * @Package: com.paySystem.ic.dao.marketing
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-9-17 上午10:31:59
 * @Version: V1.0  
 */
package com.paySystem.ic.dao.marketing;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.marketing.AdvertMa;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.marketing.AdvertMaDTO;

/**
 * @ProjectName:omall2014911
 * @ClassName:AdvertMaDAO
 * @Description:TODO
 * @date: 2014-9-17
 * @author: 孙晓磊
 * @version: V1.0
 */
public interface AdvertMaDAO extends DAO<AdvertMa> {
	/**
	 * 常量
	 */
	public static final String ADVERTMA = "advertma";

	/**
	 *@Title:queryResult
	 *@Description:异步加载
	 *@Params:@param firstindex
	 *@Params:@param maxresult
	 *@Params:@param advertMaDTO
	 *@Params:@param orderby
	 *@Params:@return
	 *@Return:QueryResult<AdvertMaDTO>
	 *@author:孙晓磊
	 *@Date:2014-9-21下午02:50:38
	 */
	public QueryResult<AdvertMaDTO> queryResult(int firstindex, int maxresult,
			AdvertMaDTO advertMaDTO, LinkedHashMap<String, String> orderby);

	/**
	 *@Title:addsaveAdver
	 *@Description:添加方法
	 *@Params:@param adverMaDTO
	 *@Return:void
	 *@author:孙晓磊
	 *@Date:2014-9-21下午02:50:52
	 */
	public void addsaveAdver(AdvertMaDTO adverMaDTO)throws Exception;

	/**
	 *@Title:getAdvertMaDTO
	 *@Description:通过id获取dto
	 *@Params:@param annountId
	 *@Params:@return
	 *@Return:AdvertMaDTO
	 *@author:孙晓磊
	 *@Date:2014-9-21下午02:51:22
	 */
	public AdvertMaDTO getAdvertMaDTO(Integer annountId) throws Exception;

	/**
	 *@Title:updateAdvertMa
	 *@Description:修改
	 *@Params:@param advertDTO
	 *@Return:void
	 *@author:孙晓磊
	 *@Date:2014-9-21下午02:52:01
	 */
	public void updateAdvertMa(AdvertMaDTO advertDTO) throws Exception;

	/**
	 *@Title:findAnnounTitle
	 *@Description:TODO
	 *@Params:@param announTitle
	 *@Params:@return
	 *@Return:List<Annount>
	 *@author:孙晓磊
	 *@Date:2014-9-21下午03:21:35
	 */
	public List<AdvertMa> findAdvertManame(String AdvertManame);
	
	/**
	*@Title:findAdvertMaIsUse
	*@Description:查询正在使用的广告
	*@Params:@return
	*@Params:@throws Exception
	*@Return:List<AdvertMaDTO>
	*@author:张军磊
	*@Date:2014-12-11上午11:22:52
	*/
	public List<AdvertMa> findAdvertMaIsUse() throws Exception;
	
}
