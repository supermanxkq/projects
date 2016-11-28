/**  
* @Title: SiteRemindInfoServiceImpl.java
* @Package: com.paySystem.ic.service.log.impl
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-13 上午10:01:55
* @Version: V1.0  
*/
package com.paySystem.ic.service.log.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.log.SiteRemindInfo;
import com.paySystem.ic.dao.log.SiteRemindInfoDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.log.SiteRemindInfoService;
import com.paySystem.ic.web.dto.log.SiteRemindInfoDTO;


/**
 * @ProjectName:omall2014911
 * @ClassName:SiteRemindInfoServiceImpl
 * @Description:TODO
 * @date: 2014-9-13
 * @author: 孙晓磊
 * @version: V1.0
 */
@Service(SiteRemindInfoService.SITEREMINDINFOSERVICE)
public class SiteRemindInfoServiceImpl extends DaoSupport<SiteRemindInfo> implements SiteRemindInfoService{

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.log.SiteRemindInfoService#queryResult()
	 *@MethodName:queryResult
	 *@Description:查询方法
	 *@return
	 *@Author:孙晓磊
	 *@Date:2014-9-13上午10:06:58
	 */
	@Resource
	private SiteRemindInfoDao siteRemindInfoDao;
	public List<SiteRemindInfoDTO> queryResult() {
		List<SiteRemindInfoDTO> siteRemindInfoDTOlist=null;
		
		try {
			siteRemindInfoDTOlist=siteRemindInfoDao.queryResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siteRemindInfoDTOlist;
	}
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.log.SiteRemindInfoService#updateSiteRemindInfo(com.paySystem.ic.web.dto.log.SiteRemindInfoDTO)
	 *@MethodName:updateSiteRemindInfo
	 *@Description:修改方法
	 *@param siteRemindInfoDTO
	 *@Author:孙晓磊
	 *@Date:2014-9-15上午11:03:54
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void updateSiteRemindInfo(SiteRemindInfoDTO siteremindInfoDTO) {
		siteRemindInfoDao.updateSiteRemindInfo(siteremindInfoDTO);
	}
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.log.SiteRemindInfoService#findSiteRem()
	 *@MethodName: findSiteRem
	 *@Description: 返回当前商户所有网站提醒
	 *@return
	 *@throws Exception
	 *@Author: 廖晓远 
	 *@Date: 2014-11-7下午05:41:39
	 */
	@Override
	public SiteRemindInfoDTO findSiteRem() throws Exception {
		return siteRemindInfoDao.findSiteRem();
	}

}
