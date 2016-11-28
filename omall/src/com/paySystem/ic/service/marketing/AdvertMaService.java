/**  
 * @Title: AdvertMaService.java
 * @Package: com.paySystem.ic.service.marketing
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-9-17 上午10:39:27
 * @Version: V1.0  
 */
package com.paySystem.ic.service.marketing;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.marketing.AdvertMa;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.marketing.AdvertMaDTO;

/**
 * @ProjectName:omall2014911
 * @ClassName:AdvertMaService
 * @Description:TODO
 * @date: 2014-9-17
 * @author: 孙晓磊
 * @version: V1.0
 */
public interface AdvertMaService extends DAO<AdvertMa> {
	/** 常量 **/
	public static final String ADVERTMASERVICE = "advertmasercvice";

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
	 *@Date:2014-9-21下午02:46:54
	 */
	public QueryResult<AdvertMaDTO> queryResult(int firstindex, int maxresult,
			AdvertMaDTO advertMaDTO, LinkedHashMap<String, String> orderby);

	/**
	 *@Title:addSave
	 *@Description:添加
	 *@Params:@param adverMaDTO
	 *@Return:void
	 *@author:孙晓磊
	 *@Date:2014-9-21下午02:47:16
	 */
	public void addSave(AdvertMaDTO adverMaDTO) throws Exception;

	/**
	 *@Title:find
	 *@Description:通过id查询
	 *@Params:@param annountId
	 *@Params:@return
	 *@Return:AdvertMaDTO
	 *@author:孙晓磊
	 *@Date:2014-9-21下午02:47:36
	 */
	public AdvertMaDTO find(Integer adverMaId) throws Exception;

	/**
	 *@Title:updateAdvertMa
	 *@Description:修改方法
	 *@Params:@param advertMaDTO
	 *@Return:void
	 *@author:孙晓磊
	 *@Date:2014-9-21下午02:48:11
	 */
	public void updateAdvertMa(AdvertMaDTO advertMaDTO) throws Exception;

	/**
	 *@Title:findAdvertMaName
	 *@Description:判断名称是否重复
	 *@Params:@param advertMaName
	 *@Params:@param method
	 *@Params:@param advertMaId
	 *@Params:@return
	 *@Return:boolean
	 *@author:孙晓磊
	 *@Date:2014-9-21下午03:19:09
	 */
	public boolean findAdvertMaName(String advertMaName, String method,
			Integer advertMaId);
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
