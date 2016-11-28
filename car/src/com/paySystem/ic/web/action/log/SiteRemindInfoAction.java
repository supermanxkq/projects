package com.paySystem.ic.web.action.log;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.service.log.SiteRemindInfoService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.log.SiteRemindInfoDTO;
import com.paySystem.ic.web.dto.system.UserSession;
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
	@Resource FunctionsService functionsService;
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
	 */
	public String list() {
		
		
	   UserSession us = Utils.getUserSession();
		if(us.getUserLevel()!=2){	
			return "intercepthtml";
		}
			
			return "list";
		
	}

	/**
	 * 异步获取列表数据
	 * @version 2013-9-8 下午08:51:04
	 * @return
	 * @throws Exception
	 */

	public String jsonPageList() throws Exception {
		
	       
	        List<SiteRemindInfoDTO> result=null;
	        List<List<String>> lists=new ArrayList<List<String>>();;
			try {
				result = siteRemindInfoService.queryResult();
				for(int i=0;i<result.size();i++)
				{
					List<String> strings=new ArrayList<String>();
					SiteRemindInfoDTO siteRemindInfoDTO=result.get(i);
					strings.add(siteRemindInfoDTO.getContId().toString());
					strings.add(siteRemindInfoDTO.getEmailReceive().toString());
					strings.add(siteRemindInfoDTO.getLeterReceive().toString());
					strings.add(siteRemindInfoDTO.getPhoReceive().toString());
					lists.add(strings);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return Utils.printInfo(lists);
 
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
	  try {
		    String groupcheckIds=siteRemindInfoDTO.getGroupcheckIds();
			String nocheckIds=siteRemindInfoDTO.getNocheckIds();
			String []id=groupcheckIds.split(";");
			String []noid=nocheckIds.split(";");
			int j=0;
			for(int i=1;i<id.length;i++)
			{   
				int k=Integer.parseInt(id[i])-1;
				j=k/3;
				SiteRemindInfoDTO siteRemindInfoDTO=siteRemindInfoService.find(j+1);
				if(k==0)
				{
					siteRemindInfoDTO.setEmailReceive(1);
				}else{
					if(k%3==0)
					{
						siteRemindInfoDTO.setEmailReceive(1);
					}
					if(k%3==1)
					{
						siteRemindInfoDTO.setLeterReceive(1);
					}
					if(k%3==2)
					{
						siteRemindInfoDTO.setPhoReceive(1);
					}
				}
				siteRemindInfoService.updateSiteRemindInfo(siteRemindInfoDTO);
			
			}
			int m=0;
			for(int i=1;i<noid.length;i++)
			{
				int k=Integer.parseInt(noid[i])-1;
				m=k/3;
				SiteRemindInfoDTO siteRemindInfoDTO=siteRemindInfoService.find(m+1);
				if(k==0)
				{
					siteRemindInfoDTO.setEmailReceive(0);
				}else{
					if(k%3==0)
					{
						siteRemindInfoDTO.setEmailReceive(0);
					}
					if(k%3==1)
					{
						siteRemindInfoDTO.setLeterReceive(0);
					}
					if(k%3==2)
					{
						siteRemindInfoDTO.setPhoReceive(0);
					}
				}
				siteRemindInfoService.updateSiteRemindInfo(siteRemindInfoDTO);
			   
			}
		   this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
		
	    
	
	}
}
