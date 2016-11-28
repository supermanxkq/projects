/**  
* @Title: annountAction.java
* @Package: com.paySystem.ic.web.action.marketing
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-9 上午10:39:57
* @Version: V1.0  
*/
package com.paySystem.ic.web.action.marketing;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.marketing.AnnountService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.marketing.AnnountDTO;


/**
 * @ProjectName:omall20140905
 * @ClassName:annountAction 全站公告管理action
 * @Description:TODO
 * @date: 2014-9-9
 * @author: 孙晓磊
 * @version: V1.0
 */
@Controller("/marketing/annount")
@Scope("prototype") 
public class annountAction extends BaseAction {

	private static final long serialVersionUID = -3051940652987671139L;
	/**valueStack中的值**/
	AnnountDTO annountDTO=new AnnountDTO();
	
	public AnnountDTO getAnnountDTO() {
		return annountDTO;
	}

	public void setAnnountDTO(AnnountDTO annountDTO) {
		this.annountDTO = annountDTO;
	}
	@Resource
	private AnnountService annountService;
	@Resource
	FunctionsService functionsService;

	/**
	*@Title:list
	*@Description:TODO
	*@Params:@return
	*@Return:String
	*@author:孙晓磊
	*@Date:2014-9-9下午01:58:00
	*/
	public String list(){
		annountDTO.setCreateTime(DateTimeTool.dateFormat("yyyy-MM-dd", DateTimeTool.nDaysAgo(7, new Date())));
		annountDTO.setStopTime(DateTimeTool.dateFormat("yyyy-MM-dd",new Date()));
		this.getRequest().setAttribute("statusList",OptionsValue.Announ_STATUS);
		
		return "list";
	}
	 /**
	*@Title:jsonPageList
	*@Description:TODO 异步加载时的查询方法
	*@Params:@return
	*@Return:String
	*@author:孙晓磊
	*@Date:2014-9-9下午01:58:40
	*/
	@SuppressWarnings("unchecked")
    public String jsonPageList() {

        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        /**判断排序参数是否有值*/
        if (StringUtils.isNotBlank(this.getOrderProperty()) && StringUtils.isNotBlank(this.getOrderDirection())) {
            orderby.put(this.getOrderProperty(), this.getOrderDirection());
        }
        else {
            /**如果页面没有要求排序方式，则设置按照分类编号排序*/
            orderby.put("createTime", "desc");
        }
        
        List<AnnountDTO> listDto = new ArrayList<AnnountDTO>();
        QueryResult<AnnountDTO> result=null;
		try {
			result = annountService.queryResult((annountDTO.getPage()-1)*pageNum, pageNum, annountDTO, orderby);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        listDto = result.getResultlist();
        
        List<List<String>> lists = new ArrayList<List<String>>();
        for (int i = 0; i < listDto.size(); i++) {
            /**向页面赋值*/
        	AnnountDTO dto = listDto.get(i);
            List<String> strings = new ArrayList<String>();
            strings.add(String.valueOf(i + 1));
            strings.add(Utils.getString(dto.getAnnounId()));
            strings.add(Utils.getString(dto.getAnnounTitle()));
            strings.add(Utils.getString(dto.getAuthor()));
            strings.add(Utils.getOptionsIntegerName(OptionsValue.Announ_STATUS,
            		dto.getStatus()));
            strings.add(Utils.getOptionsIntegerName(OptionsValue.Announ_IsTop,
            		dto.getIsTop()));
            dto.setReportTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",
    				DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", dto.getReportTime())));
            strings.add(dto.getReportTime());
            String operation = "";
            if(dto.getStatus()==0)
            {

	            if (Utils.checkPermission("sy-7101-03")) {
	                operation += "<a href =marketing/annount!editUI?annountDTO.AnnounId=" + dto.getAnnounId()
	                        + " title='修改'>" + Globals.IMG_EDIT + "</a>&nbsp;";
	            }
	            /**判断权限在相关操作栏显示相关操作，若状态为删除状态则不显示*/
	            if (Utils.checkPermission("sy-7101-04")) {
	                operation += "<a href=javascript:deleteData('marketing/annount!delete','" + dto.getAnnounId()
	                        + "') title='删除'>" + Globals.IMG_DELETE + "</a>&nbsp;";
	            }
	            if (Utils.checkPermission("sy-7101-01")) {
	                operation += "<a href =marketing/annount!checkUI?annountDTO.AnnounId=" + dto.getAnnounId()
	                        + " title='查看'>" + Globals.IMG_VIEW + "</a>&nbsp;";
	            }
	            if(Utils.checkPermission("sy-7101-05"))
	            {
	            	operation += "<a href=javascript:reprotAnnount('marketing/annount!reportAnnount','" + dto.getAnnounId()
                    + "') title='发布'>" + Globals.IMG_AUDIT + "</a>&nbsp;";
	            	
	            }
            }else if(dto.getStatus()==1){
            	 if (Utils.checkPermission("sy-7101-03")) {
 	                operation += "<a href =marketing/annount!editUI?annountDTO.AnnounId=" + dto.getAnnounId()
 	                        + " title='修改'>" + Globals.IMG_EDIT + "</a>&nbsp;";
 	            }
 	            /**判断权限在相关操作栏显示相关操作，若状态为删除状态则不显示*/
 	            if (Utils.checkPermission("sy-7101-04")) {
 	                operation += "<a href=javascript:deleteData('marketing/annount!delete','" + dto.getAnnounId()
 	                        + "') title='删除'>" + Globals.IMG_DELETE + "</a>&nbsp;";
 	            }
 	            if (Utils.checkPermission("sy-7101-01")) {
 	                operation += "<a href =marketing/annount!checkUI?annountDTO.AnnounId=" + dto.getAnnounId()
 	                        + " title='查看'>" + Globals.IMG_VIEW + "</a>&nbsp;";
 	            }
 	            if(Utils.checkPermission("sy-7101-06"))
 	            {
 	            	operation += "<a href=javascript:CancerReprotAnnount('marketing/annount!cancerReportAnnount','" + dto.getAnnounId()
                     + "') title='取消发布'>" + Globals.IMG_AUDIT + "</a>&nbsp;";
 	            	
 	            }
            	
            }else if(dto.getStatus()==9){
            	if (Utils.checkPermission("sy-7101-01")) {
                    operation += "<a href =marketing/annount!checkUI?annountDTO.AnnounId=" + dto.getAnnounId()
                            + " title='查看'>" + Globals.IMG_VIEW + "</a>&nbsp;";
                }
            	
            }
            strings.add(operation);
            lists.add(strings);
        }
        PageView pageView = new PageView(annountDTO.getPage(), result.getTotalrecord());
        ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
        return Utils.printInfo(listInfoDTO);

    }
	/**
	*@Title:addUI
	*@Description:激发添加按钮式触发的函数
	*@Params:@return
	*@Return:String
	*@author:孙晓磊
	*@Date:2014-9-9下午04:38:06
	*/
	public String addUI() {
		this.setMethod("addSave");
		this.getRequest().setAttribute("statusList",OptionsValue.AddAnnoun_STATUS);
		this.getRequest().setAttribute("isTop",OptionsValue.Announ_IsTop);
		return INPUT;
	}
	
	/**
	*@Title:addSave
	*@Description:保存的方法
	*@Params:@return
	*@Params:@throws Exception
	*@Return:String
	*@author:孙晓磊
	*@Date:2014-9-9下午05:11:34
	*/
	public String addSave() throws Exception {
		annountDTO.setCreateTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",new Date(System.currentTimeMillis())));
		
		if(annountDTO.getStatus()==1)
		{
		annountDTO.setReportTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",new Date(System.currentTimeMillis())));
		}
		annountService.addSaveAnnount(annountDTO);
		/** 添加功能日志信息 */
		functionsService.saveFunction("全站公告管理", 1, "增加公告：" + annountDTO.getAnnounId());
		this.getRequest().setAttribute("result",
				this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url", "marketing/annount!list");
		return SUCCESS;
	}
	/**
	*@Title:checkAnnounTitle
	*@Description:检查标题是否重复
	*@Params:@return
	*@Return:String
	*@author:孙晓磊
	*@Date:2014-9-10上午09:19:56
	*/
	public String checkAnnounTitle() {
		ReturnDTO dto = new ReturnDTO();
		/** 获取传递过来的Json数据 */
		Integer announId = annountDTO.getAnnounId();
		String  announTitle = annountDTO.getAnnounTitle();
		String method = this.getMethod();
		/** 设置返回标志信息 */
		dto.setFlag(annountService.findAnnounTitle(announTitle, method, announId));
		return Utils.printInfo(dto);
	}
	/**
	*@Title:checkUI
	*@Description:点击查看按钮触发的函数
	*@Params:@return
	*@Return:String
	*@author:孙晓磊
	*@Date:2014-9-10下午02:29:04
	*/
	public String checkUI() {
		this.setMethod("checkDetail");
		this.getRequest().setAttribute("statusList",OptionsValue.Announ_STATUS);
		this.getRequest().setAttribute("isTop",OptionsValue.Announ_IsTop);
		annountDTO = annountService.find(annountDTO.getAnnounId());
		if (annountDTO != null) {
			return INPUT;
		}
		return ERROR;
	}
	/**
	*@Title:editUI
	*@Description:点击修改后执行的方法
	*@Params:@return
	*@Return:String
	*@author:孙晓磊
	*@Date:2014-9-10下午03:50:18
	*/
	public String editUI() {
		/** 默认请求的方法 */
		this.setMethod("editSave");
		this.getRequest().setAttribute("statusList",OptionsValue.Announ_UpdateSTATUS);
		this.getRequest().setAttribute("isTop",OptionsValue.Announ_IsTop);
		annountDTO = annountService.find(annountDTO.getAnnounId());
		if (annountDTO != null) {
			return INPUT;
		}
		return ERROR;
	}
	/**
	*@Title:editSave
	*@Description:点击保存后执行的方法
	*@Params:@return
	*@Params:@throws Exception
	*@Return:String
	*@author:孙晓磊
	*@Date:2014-9-10下午03:58:22
	*/
	public String editSave() throws Exception {
		if (annountDTO != null) {
			if(annountDTO.getStatus()==1)
			{
			annountDTO.setReportTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",new Date(System.currentTimeMillis())));
				
			}
			annountService.updateAnnount(annountDTO);
			functionsService.saveFunction("全站公告管理", 2, "修改公告信息："
					+ annountDTO.getAnnounId());
			this.getRequest().setAttribute("result",
					this.getText("operation.success.notice"));
			this.getRequest().setAttribute("url", "marketing/annount!list");
			return SUCCESS;
			}
		return ERROR;
	}
	/**
	*@Title:reportAnnount
	*@Description:点击发布后执行的方法
	*@Params:@return
	*@Params:@throws Exception
	*@Return:String
	*@author:孙晓磊
	*@Date:2014-9-11上午11:01:52
	*/
	public String reportAnnount() throws Exception {
		
		try {
			annountDTO = annountService.find(Integer.parseInt(this.getId()));
			annountDTO.setReportTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",new Date(System.currentTimeMillis())));
			annountDTO.setStatus(1);
			annountService.updateAnnount(annountDTO);
			functionsService.saveFunction("全站公告管理", 3, "发布公告信息："
				+ annountDTO.getAnnounId());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}
/**
*@Title:reportAnnount
*@Description:取消发布
*@Params:@return
*@Params:@throws Exception
*@Return:String
*@author:孙晓磊
*@Date:2014-9-11上午11:22:08
*/
   public String cancerReportAnnount() throws Exception {
		
		try {
			annountDTO = annountService.find(Integer.parseInt(this.getId()));
			annountDTO.setStatus(0);
			annountService.updateAnnount(annountDTO);
			functionsService.saveFunction("全站公告管理", 3, "取消发布公告信息："
				+ annountDTO.getAnnounId());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	
  }

	/**
	*@Title:delete
	*@Description:删除
	*@Params:@return
	*@Return:String
	*@author:孙晓磊
	*@Date:2014-9-10下午06:41:27
	*/
	public String delete() {
		try {
			annountDTO = annountService.find(Integer.parseInt(this.getId()));
			annountDTO.setStatus(9);
			annountService.updateAnnount(annountDTO);
			functionsService.saveFunction("全站公告管理", 3, "删除公告信息："
				+ annountDTO.getAnnounId());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}

}
