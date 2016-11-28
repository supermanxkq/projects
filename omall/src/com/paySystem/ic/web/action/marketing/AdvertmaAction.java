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
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.marketing.AdvertMa;
import com.paySystem.ic.service.marketing.AdvertMaService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.marketing.AdvertMaDTO;

/**
 * @ProjectName:omall20140905
 * @ClassName:annountAction 
 * @Description:全站公告管理action
 * @date: 2014-9-9
 * @author: 孙晓磊
 * @version: V1.0
 */
@Controller("/marketing/advertma")
@Scope("prototype")
public class AdvertmaAction extends BaseAction {

	private static final long serialVersionUID = -3051940652987671139L;
	AdvertMaDTO advertmaDTO = new AdvertMaDTO();

	public AdvertMaDTO getAdvertmaDTO() {
		return advertmaDTO;
	}

	public void setAdvertmaDTO(AdvertMaDTO advertmaDTO) {
		this.advertmaDTO = advertmaDTO;
	}

	@Resource
	AdvertMaService advertmasercvice;
	@Resource
	FunctionsService functionservice;
	/**
	 *@Title:list
	 *@Description:列表页面
	 *@Params:@return
	 *@Return:String
	 *@author:孙晓磊
	 *@Date:2014-9-9下午01:58:00
	 */
	public String list() {
		this.getRequest().setAttribute("mediaTypeList",
				OptionsValue.Advertma_mediaType);
		return "list";
	}

	/**
	 *@Title:jsonPageList
	 *@Description:异步加载
	 *@Params:@return
	 *@Return:String
	 *@author:孙晓磊
	 *@Date:2014-9-21下午02:43:21
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() {

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		/** 判断排序参数是否有值 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			/** 如果页面没有要求排序方式，则设置按照分类编号排序 */
			orderby.put("advertName", "desc");
		}

		List<AdvertMaDTO> listDto = new ArrayList<AdvertMaDTO>();
		QueryResult<AdvertMaDTO> result = null;
		try {
			result = advertmasercvice.queryResult((advertmaDTO.getPage() - 1)
					* pageNum, pageNum, advertmaDTO, orderby);
		} catch (Exception e) {
			e.printStackTrace();
		}
		listDto = result.getResultlist();

		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < listDto.size(); i++) {
			/** 向页面赋值 */
			AdvertMaDTO dto = listDto.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			strings.add(Utils.getString(dto.getAdvertName()));
			strings.add(Utils.getOptionsIntegerName(
					OptionsValue.Advertma_location, dto.getPositionTypeId()));
			strings.add(Utils.getOptionsIntegerName(
					OptionsValue.Advertma_mediaType, dto.getMediaType()));
			//dto.setBeginTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",
					//DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", dto.getBeginTime())));
			strings.add(dto.getBeginTime().toString());
			//dto.setEndTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",
					//DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", dto.getEndTime())));
			strings.add(dto.getEndTime().toString());
			String operation = "";
			if (dto.getMediaType() != 9) {

				if (Utils.checkPermission("sy-7201-03")) {
					operation += "<a href =marketing/advertma!editUI?advertmaDTO.advertId="
							+ dto.getAdvertId()
							+ " title='修改'>"
							+ Globals.IMG_EDIT + "</a>&nbsp;";
				}
				/** 判断权限在相关操作栏显示相关操作，若状态为删除状态则不显示 */
				if (Utils.checkPermission("sy-7201-04")) {
					operation += "<a href=javascript:deleteData('marketing/advertma!delete','"
							+ dto.getAdvertId()
							+ "') title='删除'>"
							+ Globals.IMG_DELETE + "</a>&nbsp;";
				}
				if (Utils.checkPermission("sy-7201-01")) {
					operation += "<a href =marketing/advertma!checkUI?advertmaDTO.advertId="
							+ dto.getAdvertId()
							+ " title='查看'>"
							+ Globals.IMG_VIEW + "</a>&nbsp;";
				}
			} else if (dto.getMediaType() == 9) {
				if (Utils.checkPermission("sy-7201-01")) {
					operation += "<a href =marketing/advertma!checkUI?advertmaDTO.advertId="
							+ dto.getAdvertId()
							+ " title='查看'>"
							+ Globals.IMG_VIEW + "</a>&nbsp;";
				}
			}
			strings.add(operation);
			lists.add(strings);
		}
		PageView pageView = new PageView(advertmaDTO.getPage(), result
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);

	}

	/**
	 *@Title:addUI
	 *@Description:添加
	 *@Params:@return
	 *@Return:String
	 *@author:孙晓磊
	 *@Date:2014-9-21下午02:43:57
	 */
	public String addUI() {
		this.setMethod("addSave");
		this.getRequest().setAttribute("mediaTypeList",
				OptionsValue.Advertma_addmediaType);
		this.getRequest().setAttribute("mediaLocationList",
				OptionsValue.Advertma_location);
		this.getRequest().setAttribute("mediaAdvertContentsList",
				OptionsValue.advertContents);
		this.getRequest().setAttribute("mediashowList",
				OptionsValue.Advertma_SHOW);
		return "set";
	}

	/**
	 *@Title:addSave
	 *@Description:添加数据
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:String
	 *@author:孙晓磊
	 *@Date:2014-9-21下午02:44:14
	 */
	public String addSave() throws Exception {
		advertmasercvice.addSave(advertmaDTO);
		/** 添加功能日志信息 */
		functionservice.saveFunction("全站广告管理", 3, "添加广告"
				+ advertmaDTO.getAdvertId());
		this.getRequest().setAttribute("result",
				this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url", "marketing/advertma!list");
		return SUCCESS;
	}

	/**
	 *@Title:delete
	 *@Description:删除
	 *@Params:@return
	 *@Return:String
	 *@author:孙晓磊
	 *@Date:2014-9-21下午02:44:27
	 */
	public String delete() {
		try {

			advertmaDTO = advertmasercvice.find(Integer.parseInt(this.getId()));
			advertmaDTO.setMediaType(9);
			advertmasercvice.updateAdvertMa(advertmaDTO);
			functionservice.saveFunction("全站公告管理", 3, "删除公告信息："
					+ advertmaDTO.getAdvertId());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}

	/**
	 *@Title:checkUI
	 *@Description查看
	 *@Params:@return
	 *@Return:String
	 *@author:孙晓磊
	 *@Date:2014-9-21下午02:44:50
	 */
	public String checkUI() throws Exception{
		this.setMethod("checkDetail");
		this.getRequest().setAttribute("mediaTypeList",
				OptionsValue.Advertma_addmediaType);
		this.getRequest().setAttribute("mediaLocationList",
				OptionsValue.Advertma_location);
		this.getRequest().setAttribute("mediaAdvertContentsList",
				OptionsValue.advertContents);
		this.getRequest().setAttribute("mediashowList",
				OptionsValue.Advertma_SHOW);
		advertmaDTO = advertmasercvice.find(advertmaDTO.getAdvertId());
		advertmaDTO.setImagePath(advertmaDTO.getImagePath().replace("\\","/"));
		if (advertmaDTO != null) {
			return "set";
		}
		return ERROR;
	}

	/**
	 *@Title:editUI
	 *@Description:修改
	 *@Params:@return
	 *@Return:String
	 *@author:孙晓磊
	 *@Date:2014-9-21下午02:45:05
	 */
	public String editUI()throws Exception {
		/** 默认请求的方法 */
		this.setMethod("editSave");
		this.getRequest().setAttribute("mediaTypeList",
				OptionsValue.Advertma_addmediaType);
		this.getRequest().setAttribute("mediaLocationList",
				OptionsValue.Advertma_location);
		this.getRequest().setAttribute("mediaAdvertContentsList",
				OptionsValue.advertContents);
		this.getRequest().setAttribute("mediashowList",
				OptionsValue.Advertma_SHOW);
		advertmaDTO = advertmasercvice.find(advertmaDTO.getAdvertId());
		advertmaDTO.setImagePath(advertmaDTO.getImagePath().replace("\\","/"));
		if (advertmaDTO != null) {
			return "set";
		}
		return ERROR;
	}

	/**
	 *@Title:editSave
	 *@Description:修改数据
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:String
	 *@author:孙晓磊
	 *@Date:2014-9-21下午02:45:35
	 */
	public String editSave() throws Exception {
		if (advertmaDTO != null) {
			advertmaDTO.setImagePath(advertmaDTO.getImagePath().replace("/","\\"));
			advertmasercvice.updateAdvertMa(advertmaDTO);
			functionservice.saveFunction("全站公告管理", 2, "修改公告信息："
					+ advertmaDTO.getAdvertId());
			this.getRequest().setAttribute("result",
					this.getText("operation.success.notice"));
			this.getRequest().setAttribute("url", "marketing/advertma!list");
			return SUCCESS;
		}
		return ERROR;
	}

	/**
	 *@Title:checkadvertName
	 *@Description:查询广告名称是否重复
	 *@Params:@return
	 *@Return:String
	 *@author:孙晓磊
	 *@Date:2014-9-24下午02:19:59
	 */
	public String checkadvertName() {
		ReturnDTO dto = new ReturnDTO();
		/** 获取传递过来的Json数据 */
		Integer advertId = advertmaDTO.getAdvertId();
		String advertName = advertmaDTO.getAdvertName();
		String method = this.getMethod();
		/** 设置返回标志信息 */
		dto.setFlag(advertmasercvice.findAdvertMaName(advertName, method,
				advertId));
		return Utils.printInfo(dto);
	}
	
	/**
	*@Title:findAdvertMaIsUse
	*@Description:查询正在使用的广告信息
	*@Params:@return
	*@Params:@throws Exception
	*@Return:String
	*@author:张军磊
	*@Date:2014-12-11下午12:35:56
	*/
	public String findAdvertMaIsUse() throws Exception{
		
		ReturnDTO dto = new ReturnDTO();
		List<AdvertMa> advertMaList=advertmasercvice.findAdvertMaIsUse();
		if(advertMaList!=null){			
			dto.setTotal(advertMaList.size());	
		}else{			
			dto.setTotal(0);	
		}		
		return Utils.printInfo(dto);
	}

}
