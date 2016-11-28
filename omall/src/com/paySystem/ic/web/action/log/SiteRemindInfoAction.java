package com.paySystem.ic.web.action.log;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.paySystem.ic.service.log.SiteRemindInfoService;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.log.SiteRemindInfoDTO;
/**
 * 功能日志
 * @version 2013-9-8 下午02:08:10
 */
/**
 * @ProjectName:omall2014911
 * @ClassName:SiteRemindInfoAction
 * @Description:网站提醒action
 * @date: 2014-9-15
 * @author: 孙晓磊
 * @version: V1.0
 */
@Controller("/log/siteRemindInfo")
@Scope("prototype")
public class SiteRemindInfoAction extends BaseAction {
	private static final long serialVersionUID = -3051940652987671139L;
	@Resource SiteRemindInfoService siteRemindInfoService;
	 
	private SiteRemindInfoDTO siteRemindInfoDTO = new SiteRemindInfoDTO();
     
	public SiteRemindInfoDTO getSiteRemindInfoDTO() {
		return siteRemindInfoDTO;
	}

	public void setSiteRemindInfoDTO(SiteRemindInfoDTO siteRemindInfoDTO) {
		this.siteRemindInfoDTO = siteRemindInfoDTO;
	}

	/**
	 * 列表页面
	 * @version 2013-9-8 下午08:50:23
	 * @return
	 * @throws Exception 
	 */
	public String list() throws Exception {
		siteRemindInfoDTO = siteRemindInfoService.findSiteRem();
		return "list";
		
	}

	/**
	*@Title:editUI
	*@Description:修改方法
	*@Params:@return
	*@Return:String
	*@author:孙晓磊
	*@Date:2014-9-15下午04:06:39
	*/
	public String editUI() {
		List<SiteRemindInfoDTO> list = siteRemindInfoDTO.getChildrens();
		for(SiteRemindInfoDTO s : list){
			siteRemindInfoService.updateSiteRemindInfo(s);
		}
		return "list";
	}
}
