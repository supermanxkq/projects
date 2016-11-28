/**  
* @Title: siteRemindInfoDao.java
* @Package: com.paySystem.ic.dao.log
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-12 下午04:48:03
* @Version: V1.0  
*/
package com.paySystem.ic.dao.log;
import java.util.List;
import com.paySystem.ic.bean.log.SiteRemindInfo;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.log.SiteRemindInfoDTO;



/**
 * @ProjectName:omall2014911
 * @ClassName:siteRemindInfoDao
 * @Description:TODO
 * @date: 2014-9-12
 * @author: 孙晓磊
 * @version: V1.0
 */
public interface SiteRemindInfoDao extends DAO<SiteRemindInfo> {
	
	/**常量**/
	public static final String SITEREMINDINFODAO = "siteRemindInfoDao";
	
	/**
	*@Title:queryResult
	*@Description:查询
	*@Params:@return
	*@Params:@throws Exception
	*@Return:List<SiteRemindInfoDTO>
	*@author:孙晓磊
	*@Date:2014-9-16上午09:49:16
	*/
	public List<SiteRemindInfoDTO> queryResult()
			throws Exception;
	/**
	*@Title:updateSiteRemindInfo
	*@Description:TODO
	*@Params:@param siteRemindInfoDTO
	*@Return:void
	*@author:孙晓磊
	*@Date:2014-9-15上午11:05:49
	*/
	public void updateSiteRemindInfo(SiteRemindInfoDTO siteRemindInfoDTO);

	/**
	*@Title: findSiteRem
	*@Description: 返回当前商户所有网站提醒
	*@Params: @return
	*@Params: @throws Exception
	*@Return: SiteRemindInfoDTO
	*@author: 廖晓远 
	*@Date: 2014-11-7下午05:40:53
	*/
	public SiteRemindInfoDTO findSiteRem() throws Exception;
}
