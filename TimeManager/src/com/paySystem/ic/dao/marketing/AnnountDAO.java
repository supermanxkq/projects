/**  
* @Title: annountDAO.java
* @Package: com.paySystem.ic.dao.marketing
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-9 下午02:00:54
* @Version: V1.0  
*/
package com.paySystem.ic.dao.marketing;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.Marketing.Annount;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.marketing.AnnountDTO;

/**
 * @ProjectName:omall20140905
 * @ClassName:annountDAO
 * @Description:全站公告管理DAO接口
 * @date: 2014-9-9
 * @author: 孙晓磊
 * @version: V1.0
 */

public interface AnnountDAO extends DAO<Annount>  {
	/**常量**/
	public static final String ANNOUNTDAO = "annountDao";
	
	/**
	*@Title:queryMerByCond
	*@Description:查询方法
	*@Params:@param firstindex 第一个索引
	*@Params:@param maxresult 最后的索引
	*@Params:@param annountDTO 公告的DTO
	*@Params:@param orderby 条件
	*@Params:@return
	*@Params:@throws Exception
	*@Return:QueryResult<Annount>
	*@author:孙晓磊
	*@Date:2014-9-9下午02:07:36
	*/
	public QueryResult<AnnountDTO> queryResult(int firstindex, int maxresult,
			AnnountDTO annountDTO, LinkedHashMap<String, String> orderby)
			throws Exception;
	
	/**
	*@Title:addSaveAnnount
	*@Description:保存
	*@Params:@param annountDTO 公告的DTO
	*@Params:@return
	*@Params:@throws Exception
	*@Return:Annount
	*@author:孙晓磊
	*@Date:2014-9-9下午05:23:11
	*/
	public Annount addSaveAnnount(AnnountDTO annountDTO) throws Exception ;
	
	/**
	*@Title:findAnnounTitle
	*@Description:查询标题
	*@Params:@param announTitle 标题名
	*@Params:@return
	*@Return:List<Annount>
	*@author:孙晓磊
	*@Date:2014-9-10上午09:32:58
	*/
	public List<Annount> findAnnounTitle(String announTitle);
	/**
	*@Title:getAnnountDTO
	*@Description:通过id得到dto
	*@Params:@param annountId 公告的DTO
	*@Params:@return
	*@Return:AnnountDTO
	*@author:孙晓磊
	*@Date:2014-9-10下午02:54:41
	*/
	public AnnountDTO getAnnountDTO(Integer annountId) ;
	
	/**
	*@Title:updateAnnount
	*@Description:修改方法
	*@Params:@param annountDTO
	*@Return:void
	*@author:孙晓磊
	*@Date:2014-9-11下午03:46:07
	*/
	public void updateAnnount(AnnountDTO annountDTO);
}
