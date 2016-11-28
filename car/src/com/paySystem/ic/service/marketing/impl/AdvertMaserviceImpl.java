/**  
* @Title: AdvertMaserviceImpl.java
* @Package: com.paySystem.ic.service.marketing.impl
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-17 上午10:43:15
* @Version: V1.0  
*/
package com.paySystem.ic.service.marketing.impl;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.Marketing.AdvertMa;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.marketing.AdvertMaDAO;
import com.paySystem.ic.service.marketing.AdvertMaService;
import com.paySystem.ic.utils.UploadUtil;
import com.paySystem.ic.web.dto.marketing.AdvertMaDTO;

/**
 * @ProjectName:omall2014911
 * @ClassName:AdvertMaserviceImpl
 * @Description:TODO
 * @date: 2014-9-17
 * @author: 孙晓磊
 * @version: V1.0
 */
@Service(AdvertMaService.ADVERTMASERVICE)
public class AdvertMaserviceImpl  extends DaoSupport<AdvertMa> implements AdvertMaService{

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.marketing.AdvertMaService#queryResult(int, int, com.paySystem.ic.web.dto.marketing.AdvertMaDTO, java.util.LinkedHashMap)
	 *@MethodName:queryResult
	 *@Description:TODO
	 *@param firstindex
	 *@param maxresult
	 *@param advertMaDTO
	 *@param orderby
	 *@return
	 *@Author:孙晓磊
	 *@Date:2014-9-17上午10:46:39
	 */
	@Resource AdvertMaDAO advertma;
	public QueryResult<AdvertMaDTO> queryResult(int firstindex, int maxresult,
			AdvertMaDTO advertMaDTO, LinkedHashMap<String, String> orderby) {
		return advertma.queryResult(firstindex, maxresult, advertMaDTO, orderby);
	}
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.marketing.AdvertMaService#addSave(com.paySystem.ic.web.dto.marketing.AdvertMaDTO)
	 *@MethodName:addSave
	 *@Description:添加方法
	 *@param adverMaDTO
	 *@Author:孙晓磊
	 *@Date:2014-9-18下午02:35:41
	 */
	public void addSave(AdvertMaDTO adverMaDTO) {
		if(adverMaDTO!=null)
		{
			if(adverMaDTO.getImageFile()!=null && StringUtils.isNotBlank(adverMaDTO.getImageFileFileName())) {
				/** 上传文件 **/
				String fileName=null;
				try {
					fileName = UploadUtil.uploadImg(adverMaDTO.getImageFile(), adverMaDTO.getImageFileFileName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/** 设置文件名 **/
				adverMaDTO.setImageFileFileName(fileName );
			}
			advertma.addsaveAdver(adverMaDTO);
		}
		
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.marketing.AdvertMaService#find(java.lang.Integer)
	 *@MethodName:find
	 *@Description:通过id查询
	 *@param annountId
	 *@return
	 *@Author:孙晓磊
	 *@Date:2014-9-20下午04:31:30
	 */
	public AdvertMaDTO find(Integer annountId) {
		
		    AdvertMaDTO advertMaDTO = advertma.getAdvertMaDTO(annountId);
			
			return advertMaDTO;
		
	}
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.marketing.AdvertMaService#updateAdvertMa(com.paySystem.ic.web.dto.marketing.AdvertMaDTO)
	 *@MethodName:updateAdvertMa
	 *@Description:修改dto
	 *@param advertMaDTO
	 *@Author:孙晓磊
	 *@Date:2014-9-20下午04:54:37
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateAdvertMa(AdvertMaDTO advertMaDTO) {
		if(advertMaDTO!=null)
		{
			if(advertMaDTO.getImageFile()!=null && StringUtils.isNotBlank(advertMaDTO.getImageFileFileName())) {
				/** 上传文件 **/
				String fileName=null;
				try {
					fileName = UploadUtil.uploadImg(advertMaDTO.getImageFile(), advertMaDTO.getImageFileFileName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/** 设置文件名 **/
				advertMaDTO.setImageFileFileName(fileName );
			}
			advertma.updateAdvertMa(advertMaDTO);
		}
		
	}
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.marketing.AdvertMaService#findAdvertMaName(java.lang.String, java.lang.String, java.lang.Integer)
	 *@MethodName:findAdvertMaName
	 *@Description:TODO
	 *@param advertMaName
	 *@param method
	 *@param advertMaId
	 *@return
	 *@Author:孙晓磊
	 *@Date:2014-9-21下午03:19:33
	 */
	public boolean findAdvertMaName(String advertMaName, String method, Integer advertMaId) {
		boolean flag = false;
		List<AdvertMa> list = advertma.findAdvertManame(advertMaName);
		
		/** 如果是添加公告信息信息，只判断公告标题是否相同 */
		if (method.equals("addSave")) {
			/** 如果车牌号相同 */
			if (list.size() > 0 == true) {
				flag = true;
			}
		}
		else
		{
			/** 如果是修改公告信息，标题相同，但是公告的id不同，不提示标题重复 */
			if (list.size() > 0 && !list.get(0).getAdvertId().equals(advertMaId)) {
				flag = true;
			}
		}
		return flag;
	}
}
