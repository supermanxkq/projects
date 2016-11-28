/**  
* @Title: siteRemindInfoDaoImpl.java
* @Package: com.paySystem.ic.dao.log.impl
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-12 下午04:49:10
* @Version: V1.0  
*/
package com.paySystem.ic.dao.log.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.log.SiteRemindInfo;
import com.paySystem.ic.dao.log.SiteRemindInfoDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.web.dto.log.SiteRemindInfoDTO;
/**
 * @ProjectName:omall2014911
 * @ClassName:siteRemindInfoDaoImpl
 * @Description:TODO
 * @date: 2014-9-12
 * @author: 孙晓磊
 * @version: V1.0
 */
@Repository(SiteRemindInfoDao.SITEREMINDINFODAO)
public class SiteRemindInfoDaoImpl  extends DaoSupport<SiteRemindInfo> implements SiteRemindInfoDao {

	
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.log.SiteRemindInfoDao#queryResult()
	 *@MethodName:queryResult
	 *@Description:查询
	 *@return
	 *@throws Exception
	 *@Author:孙晓磊
	 *@Date:2014-9-16上午09:50:00
	 */
	@SuppressWarnings("unchecked")
	public List<SiteRemindInfoDTO> queryResult() throws Exception {
		String jpl = null;
		jpl = "select o from SiteRemindInfo o";
		Query query = em.createQuery(jpl);
		List<SiteRemindInfo> SiteRemindInfolist=query.getResultList();
		List<SiteRemindInfoDTO> SiteRemindInfoDTOlist=getSiteRemindInfoDTOList(SiteRemindInfolist);
		return SiteRemindInfoDTOlist;
	}

	/**
	*@Title:getSiteRemindInfoDTOList
	*@Description:通过id得到实体类
	*@Params:@param resultlist
	*@Params:@return
	*@Return:List<AnnountDTO>
	*@author:孙晓磊
	*@Date:2014-9-12下午05:03:49
	*/
	private List<SiteRemindInfoDTO> getSiteRemindInfoDTOList(	List<SiteRemindInfo> resultlist) {

		List<SiteRemindInfoDTO> siteRemindInfolist = new ArrayList<SiteRemindInfoDTO>();
		for (int i = 0; i < resultlist.size(); i++) {
			SiteRemindInfo siteRemindInfo = resultlist.get(i);
			SiteRemindInfoDTO siteRemindInfoDTO = new SiteRemindInfoDTO();
			siteRemindInfoDTO.setContId(siteRemindInfo.getContId());
			siteRemindInfoDTO.setLeterReceive(siteRemindInfo.getLeterReceive());
			siteRemindInfoDTO.setPhoReceive(siteRemindInfo.getPhoReceive());
			siteRemindInfoDTO.setEmailReceive(siteRemindInfo.getEmailReceive());
			siteRemindInfolist.add(siteRemindInfoDTO);

		}
		return siteRemindInfolist;
	
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.log.SiteRemindInfoDao#getSiteRemindInfoDTO(java.lang.Integer)
	 *@MethodName:getSiteRemindInfoDTO
	 *@Description:通过id得到dto
	 *@param siteRemindInfo
	 *@return
	 *@Author:孙晓磊
	 *@Date:2014-9-15上午10:40:14
	 */
	public SiteRemindInfoDTO getSiteRemindInfoDTO(Integer siteRemindInfoid) {
		SiteRemindInfo siteRemindInfo = find(siteRemindInfoid);
		if (siteRemindInfo == null) {
			return null;
		}
		SiteRemindInfoDTO siteRemindInfoDTO = new SiteRemindInfoDTO();
		if (siteRemindInfo.getContId() != null) {
			siteRemindInfoDTO.setContId(siteRemindInfo.getContId());
		}
		if (siteRemindInfo.getEmailReceive() != null) {
			siteRemindInfoDTO.setEmailReceive(siteRemindInfo.getEmailReceive());
		}
		if (siteRemindInfo.getLeterReceive() != null) {
			siteRemindInfoDTO.setLeterReceive(siteRemindInfo.getLeterReceive());
		}
		if (siteRemindInfo.getPhoReceive()!=null) {
			siteRemindInfoDTO.setPhoReceive(siteRemindInfo.getPhoReceive());
		}
		return siteRemindInfoDTO;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.log.SiteRemindInfoDao#updateSiteRemindInfo(com.paySystem.ic.web.dto.log.SiteRemindInfoDTO)
	 *@MethodName:updateSiteRemindInfo
	 *@Description:修改方法
	 *@param siteRemindInfoDTO
	 *@Author:孙晓磊
	 *@Date:2014-9-15上午11:06:24
	 */
	
	public void updateSiteRemindInfo(SiteRemindInfoDTO siteRemindInfoDTO) {
		SiteRemindInfo siteRemindInfo=getSiteRemindInfo(siteRemindInfoDTO);
		this.update(siteRemindInfo);
	}
	/**
	*@Title:getSiteRemindInfo
	*@Description:dto转换为实体类
	*@Params:@param siteRemindInfoDTO
	*@Params:@return
	*@Return:SiteRemindInfo
	*@author:孙晓磊
	*@Date:2014-9-16上午09:50:36
	*/
	public SiteRemindInfo getSiteRemindInfo(SiteRemindInfoDTO siteRemindInfoDTO) {
		
		SiteRemindInfo siteRemindInfo = new SiteRemindInfo();
		if (siteRemindInfoDTO.getContId() != null) {
			siteRemindInfo.setContId(siteRemindInfoDTO.getContId());
		}
		if (siteRemindInfoDTO.getEmailReceive() != null) {
			siteRemindInfo.setEmailReceive(siteRemindInfoDTO.getEmailReceive());
		}
		if (siteRemindInfoDTO.getLeterReceive() != null) {
			siteRemindInfo.setLeterReceive(siteRemindInfoDTO.getLeterReceive());
		}
		if (siteRemindInfoDTO.getPhoReceive()!=null) {
			siteRemindInfo.setPhoReceive(siteRemindInfoDTO.getPhoReceive());
		}
		return siteRemindInfo;
	}

}
