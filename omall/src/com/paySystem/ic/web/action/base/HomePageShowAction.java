package com.paySystem.ic.web.action.base;

import javax.annotation.Resource;

import org.apache.cxf.security.LoginSecurityContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.base.StoreInfo;
import com.paySystem.ic.service.base.HomePageShowService;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.base.HomePageShowDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ProjectName:omall
 * @ClassName:HomePageShowAction
 * @Description:首页内容展示的Action
 * @date: 2014-11-21
 * @author: 王楠
 * @version: V1.0
 */
@Controller("/homePage/homePageShow")
@Scope("prototype")
public class HomePageShowAction extends BaseAction{

	private static final long serialVersionUID = 7625627608538459262L;
	
	@Resource
	HomePageShowService homePageShowService;
	
	private HomePageShowDTO homePageShowDTO=new HomePageShowDTO();

	public HomePageShowDTO getHomePageShowDTO() {
		return homePageShowDTO;
	}

	public void setHomePageShowDTO(HomePageShowDTO homePageShowDTO) {
		this.homePageShowDTO = homePageShowDTO;
	}
	
	/**
	*@Title:list
	*@Description:显示信息的方法
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-11-21下午02:50:06
	*/
	public void list(){
         UserSession us=Utils.getUserSession();
         String merId=us.getMerId();
         String organId=us.getOrganId();
         if(us.getUserLevel()==2){
        	 try {
				StoreInfo sto=homePageShowService.findByStoId(merId);
				this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
				this.getRequest().setAttribute("url","homePage/homePageShow!list");
				this.getRequest().setAttribute("sto", sto);
				Utils.printInfo(sto);
			} catch (Exception e) {
				e.printStackTrace();
			}
         }else{
        	 try {
				Organs org=homePageShowService.findByOrgId(organId);
				this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
				this.getRequest().setAttribute("url","homePage/homePageShow!list");
				this.getRequest().setAttribute("org", org);
				Utils.printInfo(org);
			} catch (Exception e) {
				e.printStackTrace();
			}
         }
	}

}
