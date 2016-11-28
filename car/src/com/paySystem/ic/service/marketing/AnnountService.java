/**  
* @Title: AnnountService.java
* @Package: com.paySystem.ic.service.marketing
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-9 下午03:03:30
* @Version: V1.0  
*/
package com.paySystem.ic.service.marketing;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.Marketing.Annount;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.marketing.AnnountDTO;

/**
 * @ProjectName:omall20140905
 * @ClassName:AnnountService 全站公告管理service接口
 * @Description:TODO
 * @date: 2014-9-9
 * @author: 孙晓磊
 * @version: V1.0
 */
public interface AnnountService extends DAO<Annount>  {
	
	public static final String ANNOUNTSERVICE = "annountService";
	
	/**
	*@Title:queryResult
	*@Description:TODO查询方法
	*@Params:@param page 当前页
	*@Params:@param pageNum 每页显示的数据条数
	*@Params:@param annountDTO全站公告管理DTO
	*@Params:@param orderby 条件
	*@Params:@return
	*@Params:@throws Exception
	*@Return:QueryResult<AnnountDTO>
	*@author:孙晓磊
	*@Date:2014-9-9下午03:08:56
	*/
	QueryResult<AnnountDTO> queryResult(int page, int pageNum,
			AnnountDTO annountDTO, LinkedHashMap<String, String> orderby)
			throws Exception;
	/**
	*@Title:addSaveAnnount 
	*@Description:保存公告信息
	*@Params:@param annountDTO 全站公告管理DTO
	*@Return:void
	*@author:孙晓磊
	*@Date:2014-9-10上午09:23:52
	*/
	public void addSaveAnnount(AnnountDTO annountDTO);
	
	/**
	*@Title:findAnnounTitle
	*@Description:查询标题
	*@Params:@param announTitle 公告标题
	*@Params:@param method 方法名称
	*@Params:@param announTitleId 公告ID
	*@Params:@return
	*@Return:boolean
	*@author:孙晓磊
	*@Date:2014-9-10上午09:24:48
	*/
	public boolean findAnnounTitle(String announTitle, String method, Integer announTitleId);
	/**
	*@Title:find
	*@Description:TODO 查询方法
	*@Params:@param annountId 公告ID
	*@Params:@return
	*@Return:AnnountDTO 全站公告DTO
	*@author:孙晓磊
	*@Date:2014-9-10下午02:34:46
	*/
	public AnnountDTO find(Integer annountId);
	/**
	*@Title:updateOilcar
	*@Description:修改方法
	*@Params:@param annount	全站公告管理实体类
	*@Return:void
	*@author:孙晓磊
	*@Date:2014-9-10下午04:00:17
	*/
	public void updateAnnount(AnnountDTO annount);

}
