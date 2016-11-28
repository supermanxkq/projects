/**  
* @Title: SiteRemindInfoService.java
* @Package: com.paySystem.ic.service.log
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-13 上午10:00:55
* @Version: V1.0  
*/
package com.paySystem.ic.service.log;

import java.util.List;
import com.paySystem.ic.bean.log.SiteRemindInfo;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.log.SiteRemindInfoDTO;



/**
 * @ProjectName:omall2014911
 * @ClassName:SiteRemindInfoService
 * @Description:TODO
 * @date: 2014-9-13
 * @author: 孙晓磊
 * @version: V1.0
 */
public interface SiteRemindInfoService extends DAO<SiteRemindInfo> {
	public static final String SITEREMINDINFOSERVICE = "siteRemindInfoService";

	/**
	*@Title:queryResult
	*@Description:查询方法
	*@Params:@return
	*@Return:List<SiteRemindInfoDTO>
	*@author:孙晓磊
	*@Date:2014-9-13上午10:02:09
	*/
	public List<SiteRemindInfoDTO> queryResult();
	/**
	*@Title:updateSiteRemindInfo
	*@Description:修改方法
	*@Params:@param siteRemindInfoDTO
	*@Return:void
	*@author:孙晓磊
	*@Date:2014-9-15下午02:34:43
	*/
	public void updateSiteRemindInfo(SiteRemindInfoDTO siteRemindInfoDTO);
	
	/**
	*@Title: findSiteRem
	*@Description: 返回当前商户所有网站提醒
	*@Params: @return
	*@Params: @throws Exception
	*@Return: SiteRemindInfoDTO
	*@author: 廖晓远 
	*@Date: 2014-11-7下午05:41:24
	*/
	public SiteRemindInfoDTO findSiteRem() throws Exception;

}
