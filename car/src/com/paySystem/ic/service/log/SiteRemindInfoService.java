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
	*@Title:find
	*@Description:通过id查询dto
	*@Params:@param SiteRemindInfoId 
	*@Params:@return
	*@Return:SiteRemindInfoDTO
	*@author:孙晓磊
	*@Date:2014-9-15上午11:03:10
	*/
	public SiteRemindInfoDTO find(Integer SiteRemindInfoId);
	/**
	*@Title:updateSiteRemindInfo
	*@Description:修改方法
	*@Params:@param siteRemindInfoDTO
	*@Return:void
	*@author:孙晓磊
	*@Date:2014-9-15下午02:34:43
	*/
	public void updateSiteRemindInfo(SiteRemindInfoDTO siteRemindInfoDTO);

}
