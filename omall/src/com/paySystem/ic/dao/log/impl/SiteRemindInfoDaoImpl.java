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

import javax.annotation.Resource;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.log.SiteRemind;
import com.paySystem.ic.bean.log.SiteRemindInfo;
import com.paySystem.ic.dao.log.SiteRemindDao;
import com.paySystem.ic.dao.log.SiteRemindInfoDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.log.SiteRemindInfoDTO;
import com.paySystem.ic.web.dto.system.UserSession;
/**
 * @ProjectName:omall2014911
 * @ClassName:siteRemindInfoDaoImpl
 * @Description:网站提醒
 * @date: 2014-9-12
 * @author: 孙晓磊
 * @version: V1.0
 */
@Repository(SiteRemindInfoDao.SITEREMINDINFODAO)
public class SiteRemindInfoDaoImpl  extends DaoSupport<SiteRemindInfo> implements SiteRemindInfoDao {

	@Resource
	SiteRemindDao siteRemindDao;
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
		UserSession us = Utils.getUserSession();
		String sql = "select l.contId,l.infoContent,l.parentId,r.remSign,r.emailReceive,r.leterReceive,r.phoReceive " +
				"from L_SITEREMINDINFO l left join (select * from L_SITEREMINDINFODETAIL where merId="+us.getMerId()+" ) " +
						"as r on l.contId=r.contId ";
		Query query = em.createNativeQuery(sql);
		List<SiteRemindInfoDTO> siteRemindInfoDTOlist = query.getResultList();
		return siteRemindInfoDTOlist;
	}

	/**
	*@Title: findSiteRem
	*@Description: 返回当前商户所有网站提醒
	*@Params: @throws Exception
	*@Return: SiteRemindInfoDTO
	*@author: 廖晓远 
	*@Date: 2014-11-7下午05:15:57
	*/
	public SiteRemindInfoDTO findSiteRem() throws Exception {
		SiteRemindInfoDTO siteRemindInfoDTO = new SiteRemindInfoDTO();
		//获取基本级别提醒
		List<SiteRemindInfoDTO> list = new ArrayList<SiteRemindInfoDTO>();
		for(SiteRemindInfoDTO s : getSiteRemList(0,null)){
			list.add(s);
			for(SiteRemindInfoDTO s1 : getSiteRemList(1,s.getContId())){
				list.add(s1);
			}
		}
		siteRemindInfoDTO.setChildrens(list);
		return siteRemindInfoDTO;
	}
	
	/**
	*@Title: getSiteRemList
	*@Description: 查询某一级别网站提醒
	*@Params: @param level 级别
	*@Params: @return
	*@Return: List<SiteRemindInfoDTO>
	*@author: 廖晓远 
	*@Date: 2014-11-7下午05:18:19
	*/
	@SuppressWarnings("unchecked")
	private List<SiteRemindInfoDTO> getSiteRemList(int level,Integer prantId){
		UserSession us = Utils.getUserSession();
		String merId = "";
		if(us.getUserLevel()==0){
			merId = us.getOrganId();
		}else{
			merId = us.getMerId();
		}
		String sql = "select l.contId,l.infoContent,l.parentId,r.remSign,r.emailReceive,r.leterReceive,r.phoReceive,r.detailId " +
		"from L_SITEREMINDINFO l left join (select * from L_SITEREMINDINFODETAIL where merId="+merId+" ) " +
				"as r on l.contId=r.contId ";
		if(level==0){
			sql+="where l.parentId is NULL";
		}else if(level==1){
			sql+="where l.parentId ="+prantId;
		}
		Query query = em.createNativeQuery(sql);
		List<SiteRemindInfoDTO> siteRemindInfoDTOlist = getSiteRemindInfoDTOList(query.getResultList());
		return siteRemindInfoDTOlist;
		
	}
	
	/**
	*@Title: getSiteRemindInfoDTOList
	*@Description: Object转DTO
	*@Params: @param list
	*@Params: @return
	*@Return: List<SiteRemindInfoDTO>
	*@author: 廖晓远 
	*@Date: 2014-11-7下午06:17:59
	*/
	private List<SiteRemindInfoDTO> getSiteRemindInfoDTOList(List<Object[]> list) {
		List<SiteRemindInfoDTO> siteRemindInfolist = new ArrayList<SiteRemindInfoDTO>();
		for (int i = 0; i < list.size(); i++) {
			Object[] object = list.get(i);
			SiteRemindInfoDTO siteRemindInfoDTO = new SiteRemindInfoDTO();
			siteRemindInfoDTO.setContId(Integer.valueOf(object[0]+""));
			siteRemindInfoDTO.setInfoContent((String)object[1]);
			siteRemindInfoDTO.setParentId((String)object[2]);
			if(null!=object[3])
				siteRemindInfoDTO.setRemSign(Integer.valueOf(object[3]+""));
			else
				siteRemindInfoDTO.setRemSign(0);
			if(null!=object[4])
				siteRemindInfoDTO.setEmailReceive(Integer.valueOf(object[4]+""));
			else
				siteRemindInfoDTO.setEmailReceive(0);
			if(null!=object[5])
				siteRemindInfoDTO.setLeterReceive(Integer.valueOf(object[5]+""));
			else
				siteRemindInfoDTO.setLeterReceive(0);
			if(null!=object[6])
				siteRemindInfoDTO.setPhoReceive(Integer.valueOf(object[6]+""));
			else
				siteRemindInfoDTO.setPhoReceive(0);
			if(null!=object[7])
			siteRemindInfoDTO.setDetailId(Integer.valueOf(object[7]+""));
			siteRemindInfolist.add(siteRemindInfoDTO);
		}
		return siteRemindInfolist;
	
	}
	

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.log.SiteRemindInfoDao#updateSiteRemindInfo(com.paySystem.ic.web.dto.log.SiteRemindInfoDTO)
	 *@MethodName: updateSiteRemindInfo
	 *@Description: 保存网站提醒商户详细信息
	 *@param siteRemindInfoDTO
	 *@Author: 廖晓远 
	 *@Date: 2014-11-10下午02:26:02
	 */
	public void updateSiteRemindInfo(SiteRemindInfoDTO siteRemindInfoDTO) {
		if(null!=siteRemindInfoDTO.getParentId()){
			UserSession us =Utils.getUserSession();
			String merId = "";
			if(us.getUserLevel()==0){
				merId = us.getOrganId();
			}else{
				merId = us.getMerId();
			}
			Integer remSign = 0; //卖家提醒
			SiteRemind siteRemind = new SiteRemind();
			siteRemind.setContId(siteRemindInfoDTO.getContId());
			siteRemind.setDetailId(siteRemindInfoDTO.getDetailId());
			siteRemind.setEmailReceive(siteRemindInfoDTO.getEmailReceive());
			siteRemind.setLeterReceive(siteRemindInfoDTO.getLeterReceive());
			siteRemind.setMerId(merId);
			siteRemind.setPhoReceive(siteRemindInfoDTO.getPhoReceive());
			siteRemind.setRemSign(remSign);
			if(null!=siteRemindInfoDTO.getDetailId()){
				siteRemindDao.update(siteRemind);
			}else{
				siteRemindDao.save(siteRemind);
			}
		}
		
	}

}
