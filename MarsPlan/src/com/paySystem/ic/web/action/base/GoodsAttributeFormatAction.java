package com.paySystem.ic.web.action.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.base.GoodsAttributeFormatService;
import com.paySystem.ic.service.base.GoodsFamilyService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.GoodsAttributeDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyAttrRelaDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyGroupRelaDTO;
import com.paySystem.ic.web.dto.base.GoodsFormatDTO;
import com.paySystem.ic.web.dto.base.GoodsFormatGroupDTO;
import com.paySystem.ic.web.dto.base.GoodsFormatGroupRelaDTO;
import com.paySystem.ic.web.dto.goods.GoodsTypeDTO;
import com.paySystem.ic.web.dto.system.UserSession;


/**  
 * @Title: GoodsAttributeFormatAction.java
 * @Package: com.paySystem.ic.web.action.base
 * @Description: 商品属性规格Action
 * @Author: yanwuyang 
 * @Date: 2014-8-21 下午2:49:36
 * @Version: V1.0  
 */
@Controller("/base/goodsattributeformat")
@Scope("prototype")
public class GoodsAttributeFormatAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 规格属性Service*/
	@Resource
	private GoodsAttributeFormatService attributeFormatService;
	
	/** 商品分类Service*/
	@Resource
	private GoodsFamilyService familyService;
	
	/**日志service*/
	@Resource
	FunctionsService functionsService;
	
	/** 商品类型*/
	private GoodsFamilyDTO familyDTO;
	
	/** 商品属性 */
	private GoodsAttributeDTO attributeDTO;
	
	/** 集合收集页面上的数据*/
	private List<GoodsFamilyAttrRelaDTO> familyAttrRelaDTOs;
	
	/** 规格分组DTO*/
	private GoodsFormatGroupDTO formatGroupDTO;
	
	/** 商品类型规格分组关联*/
	private List<GoodsFormatGroupRelaDTO> formatGroupRelaDTOs;
	
	/** 规格*/
	private GoodsFormatDTO formatDTO;
	
	/** 规格与分组关联*/
	private GoodsFormatGroupRelaDTO formatGroupRelaDTO;
	
	/** 规格类型关联*/
	private List<GoodsFamilyGroupRelaDTO> familyGroupRelaDTOs;
	
	/** 标记*/
	private String flag;
	
	/**
	 * 
	 *@Title:list
	 *@Description:打开list页面
	 *@Params:@return
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-7-20上午10:56:19
	 */
	public String list() {
		/** 获取当前操作员信息*/
		UserSession us = Utils.getUserSession();
		/**
		 * 根据操作员不同级别进行界面跳转
		 * 
		 * us.getUserLevel 0 : 平台操作员 1 : 机构操作员 2 : 商户操作员 操作员为平台操作员，进行界面正常跳转；
		 * 操作员为机构或商户操作员，进行拦截提示其不拥有该权限；
		 */
		switch (us.getUserLevel()) {
		case 0:
			break;
		case 1:
			return "intercepthtml";
		case 2:
			return "intercepthtml";
		}
		return "list";
	}
	
	/**
	 * 
	 *@Title:jsonPageList
	 *@Description:获取页面数据列表
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-8-21下午3:23:43
	 */
	public String jsonPageList() throws Exception {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		QueryResult<List> qr = attributeFormatService.getAssociatedFamily(
				(familyDTO.getPage() - 1) * pageNum, pageNum,
				familyDTO, orderby);
		List<List<String>> lists = new ArrayList<List<String>>();
		String attrNames;
		List familyIds = qr.getResultlist();
		for (int i = 0; i < familyIds.size(); i++) {
			List<String> strings = new ArrayList<String>();
			Object[] o = (Object[])familyIds.get(i);
			Integer familyId = (Integer)o[0];
			String familyName = (String)o[1];
			char status = (Character)o[2];
			List<GoodsAttributeDTO> attributeDTOs = attributeFormatService.getAttributeByFamilyId(familyId,"list");
			attrNames="";
			/** 遍历DTO对象集合并生成要输出到界面的信息*/
			for (int j = 0;j < attributeDTOs.size(); j++) {
				GoodsAttributeDTO dto = attributeDTOs.get(j);
				if(!attrNames.equals("")){
					attrNames+=",";
				}
				attrNames+= dto.getAttrName();
			}
			strings.add((i+1)+"");
			strings.add(familyName);
			strings.add(attrNames);
			String statusStr = "启用";
			if (status== '0') {
				statusStr = "禁用";
			} else if (status == '9') {
				statusStr = "删除";
			}
			strings.add(statusStr);
			String operation = "";
			
			if (Utils.checkPermission("sy-1704-04")) {
				operation += "<a href=javascript:showFormatByFamilyId('base/goodsattributeformat!getFormatByFamilyId','"+familyId+"')"
						+ " title='相关规格'>"
						+ Globals.IMG_VIEW
						+ "</a>&nbsp;";
			}
			
			/**如果本条记录状态为"删除"状态，则操作员只可以对本条记录进行查看操作*/
			if (Utils.checkPermission("sy-1704-01")) {
				operation += "<a href=base/goodsattributeformat!checkUI?familyDTO.familyId="
						+ familyId
						+ " title='查看'>"
						+ Globals.IMG_VIEW
						+ "</a>&nbsp;";
			}
			if(status!=9){
				/**检查是否有机构修改权限，如有则显示"修改"按钮*/
				if (Utils.checkPermission("sy-1704-03")) {
					operation += "<a href=base/goodsattributeformat!editUI?familyDTO.familyId="
							+ familyId
							+ " title='修改'>"
							+ Globals.IMG_EDIT
							+ "</a>&nbsp;";

				}
			}
			strings.add(operation);
			lists.add(strings);
			
		}
		PageView pageView = new PageView(familyDTO.getPage(),qr.getTotalrecord());

		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		/**向前台输出查询数据结果集字符串*/
		return Utils.printInfo(listInfoDTO);
	}
	
	/**
	 * 
	 *@Title:addUI
	 *@Description:打开属性规格管理新增界面
	 *@Params:@return
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-8-21下午3:24:47
	 */
	public String addUI() throws Exception {
		this.setMethod("addSave");
		this.getRequest().setAttribute("groups", getFormatGroupJson());
		this.getRequest().setAttribute("status", OptionsValue.STATUS_ENABLE_DISABLE);
		//获取类型的第一级分类
		familyDTO = attributeFormatService.getFirstFamily();
		return INPUT;
	}
	
	/**
	 * 
	 *@Title:addSave
	 *@Description:保存关联
	 *@Params:@return
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-8-24上午10:57:38
	 */
	public String addSave(){
		List<GoodsFamilyGroupRelaDTO> groupRelaDTOs = new ArrayList<GoodsFamilyGroupRelaDTO>();
		List<GoodsFamilyAttrRelaDTO> attrRelaDTOs = new ArrayList<GoodsFamilyAttrRelaDTO>();
		for (int i = 0; familyAttrRelaDTOs!=null && i < familyAttrRelaDTOs.size(); i++) {
			if(familyAttrRelaDTOs.get(i)!=null){
				GoodsFamilyAttrRelaDTO dto=familyAttrRelaDTOs.get(i);
				dto.setFamilyId(familyDTO.getFamilyId());
				attrRelaDTOs.add(dto);
			}
		}
		for (int i = 0;familyGroupRelaDTOs!=null && i < familyGroupRelaDTOs.size(); i++) {
			if(familyGroupRelaDTOs.get(i)!=null){
				GoodsFamilyGroupRelaDTO dto = familyGroupRelaDTOs.get(i);
				dto.setFamilyId(familyDTO.getFamilyId());
				groupRelaDTOs.add(dto);
			}
		}
		attributeFormatService.saveAssociation(familyDTO.getFamilyId(), groupRelaDTOs, attrRelaDTOs);
		/** 2.保存操作日志信息*/
		functionsService.saveFunction("商品分类属性规格管理", 1, "设置分类与属性规格关联");
		/** 保存成功提示内容*/
		this.getRequest().setAttribute("result",
				this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url", "base/goodsattributeformat!list");
		return SUCCESS;
	}
	
	/**
	 * 
	 *@Title:editUI
	 *@Description:打开编辑页面
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-8-24下午05:16:08
	 */
	public String editUI() throws Exception{
		this.setMethod("editSave");
		this.getRequest().setAttribute("familyId", familyDTO.getFamilyId());
		this.getRequest().setAttribute("groups", getFormatGroupJson());
		this.getRequest().setAttribute("status", OptionsValue.STATUS_ENABLE_DISABLE);
		return INPUT;
	}
	
	/**
	 * 
	 *@Title:editSave
	 *@Description:修改
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-8-24下午06:34:28
	 */
	public String editSave() throws Exception{
		return addSave();
		
	}
	/**
	 * 
	 *@Title:delete
	 *@Description:删除
	 *@Params:@return
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-8-24下午05:22:31
	 */
	public String delete() {
		try {
			attributeFormatService.updateStautsByFamilyId(Integer.parseInt(this.id),9);
			functionsService.saveFunction("商品属性管理", 3, "删除商品属性规格：" + this.id);
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}
	

	/**
	 * 
	 *@Title:checkUI
	 *@Description:查看
	 *@Params:@return
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-8-24下午05:27:06
	 */
	public String checkUI() throws Exception {
		this.setMethod("checkUI");
		this.getRequest().setAttribute("familyId", familyDTO.getFamilyId());
		this.getRequest().setAttribute("groups", getFormatGroupJson());
		this.getRequest().setAttribute("status", OptionsValue.STATUS_ENABLE_DISABLE);
		return INPUT;
	}

	/**
	 * 
	 *@Title:getAssociationByFamilyId
	 *@Description:根据分类获取属性规格
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-8-24下午12:55:10
	 */
	public String getAssociationByFamilyId() throws Exception{
		String type="edit";
		if(flag.equals("checkUI")){
			 type="list";
		}
		HashMap zMap = new HashMap();
		GoodsFamilyDTO gfamilyDTO= familyService.find(familyDTO.getFamilyId());
		zMap.put("status", gfamilyDTO.getStatus());
		zMap.put("preFlag", gfamilyDTO.getPreFlag());
		List<GoodsAttributeDTO> attributeDTOs = attributeFormatService.getAttributeByFamilyId(familyDTO.getFamilyId(),type);
		if(attributeDTOs==null || attributeDTOs.size()==0){
			return Utils.printInfo(zMap);
		}
		List<GoodsFormatDTO> formatDTOs = attributeFormatService.getFormatByFamilyId(familyDTO.getFamilyId(),type);
		zMap.put("attributes", attributeDTOs);
		ArrayList groupIds = new ArrayList();
		List<GoodsFormatGroupRelaDTO> dtoResult = attributeFormatService.getAllGoodsFormatGroupRela();
		zMap.put("groupIds", groupIds);
		if(formatDTOs==null){
			return Utils.printInfo(zMap);
		}
		for (int j = 0;  j < formatDTOs.size(); j++) {
			GoodsFormatDTO formatDTO = formatDTOs.get(j);
			for (int i = 0; i < dtoResult.size(); i++) {
				GoodsFormatGroupRelaDTO dto = dtoResult.get(i);
				int formatId = dto.getFormatId();
				int groupId = dto.getFgroupId();
				if((int)formatDTO.getFormatId()==formatId){
					ArrayList<GoodsFormatDTO> formatList = (ArrayList<GoodsFormatDTO>)zMap.get(groupId);
					if(formatList==null){
						formatList = new ArrayList<GoodsFormatDTO>();
						zMap.put(groupId, formatList);
						groupIds.add(groupId);
					}
					formatList.add(formatDTO);
					break;
				}
			}
		}
		return Utils.printInfo(zMap);
	}
	
	/**
	 * 
	 *@Title:showFormatByFamilyId
	 *@Description:显示分类对应的规格
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-8-24下午07:03:59
	 */
	public String getFormatByFamilyId() throws Exception{
		List<GoodsFormatDTO> formatDTOs = attributeFormatService.getFormatByFamilyId(familyDTO.getFamilyId(),"list");
		HashMap zMap = new HashMap();
		if(formatDTOs==null){
			return Utils.printInfo(zMap);
		}
		List<GoodsFormatGroupRelaDTO> dtoResult = attributeFormatService.getAllGoodsFormatGroupRela();
		ArrayList groupIds = new ArrayList();
		zMap.put("groupIds", groupIds);
		for (int j = 0;  j < formatDTOs.size(); j++) {
			GoodsFormatDTO formatDTO = formatDTOs.get(j);
			for (int i = 0; i < dtoResult.size(); i++) {
				GoodsFormatGroupRelaDTO dto = dtoResult.get(i);
				int formatId = dto.getFormatId();
				String groupName = dto.getFgroupName();
				if((int)formatDTO.getFormatId()==formatId){
					ArrayList<GoodsFormatDTO> formatList = (ArrayList<GoodsFormatDTO>)zMap.get(groupName);
					if(formatList==null){
						formatList = new ArrayList<GoodsFormatDTO>();
						zMap.put(groupName, formatList);
						groupIds.add(groupName);
					}
					formatList.add(formatDTO);
					break;
				}
			}
		}
		return Utils.printInfo(zMap);
	}
	
	
	/**
	 * 
	 *@Title:saveAttribute
	 *@Description:保存商品属性
	 *@Params:@return
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-8-21下午09:37:24
	 */
	public String saveAttribute(){
		ReturnDTO data = new ReturnDTO();
		attributeFormatService.saveAttribute(attributeDTO) ;
		/** 2.保存操作日志信息*/
		functionsService.saveFunction("商品分类属性规格管理", 1, "增加属性：");
		data.setFlag(true);
		return Utils.printInfo(data);
	}
	
	/**
	 * 
	 *@Title:saveFormatGroup
	 *@Description:保存规格分组名称
	 *@Params:@return
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-8-22下午3:28:49
	 */
	public String saveFormatGroup(){
		ReturnDTO data = new ReturnDTO();
		attributeFormatService.saveFormatGroup(formatGroupDTO);
		/** 2.保存操作日志信息*/
		functionsService.saveFunction("商品分类属性规格管理", 1, "增加规格分组：");
		data.setFlag(true);
		return Utils.printInfo(data);
	}
	
	/**
	 * 
	 *@Title:getAllAttributes
	 *@Description:获取所有属性
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-8-22下午3:28:31
	 */
	public String getAllAttributes() throws Exception{
		QueryResult<GoodsAttributeDTO> dtoResult = attributeFormatService.getAllAttributes();
		List<GoodsAttributeDTO> payList = dtoResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		/** 遍历DTO对象集合并生成要输出到界面的信息*/
		for (int i = 0; i < payList.size(); i++) {
			List<String> strings = new ArrayList<String>();
			GoodsAttributeDTO dto = payList.get(i);
			strings.add(dto.getAttrId().toString());
			strings.add(dto.getAttrName());
			lists.add(strings);
		}
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists,null);
		/** 向前台输出查询数据结果集字符串*/
		return Utils.printInfo(listInfoDTO);
	}
	
	/**
	 * 
	 *@Title:getAllFormatGroups
	 *@Description:获取所有的规格
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-8-22下午3:46:09
	 */
	public String getAllFormatGroups() throws Exception{
		QueryResult<GoodsFormatGroupDTO> dtoResult = attributeFormatService.getAllFormatGruops();
		List<GoodsFormatGroupDTO> payList = dtoResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		/** 遍历DTO对象集合并生成要输出到界面的信息*/
		for (int i = 0; i < payList.size(); i++) {
			List<String> strings = new ArrayList<String>();
			GoodsFormatGroupDTO dto = payList.get(i);
			strings.add(dto.getFgroupId().toString());
			strings.add(dto.getFgroupName());
			lists.add(strings);
		}
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists,null);
		/** 向前台输出查询数据结果集字符串*/
		return Utils.printInfo(listInfoDTO);
	}
	
	/**
	 * 
	 *@Title:getFormatGroupJson
	 *@Description:获取规格json数据
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-8-22下午3:57:35
	 */
	private String getFormatGroupJson() throws Exception{
		QueryResult<GoodsFormatGroupDTO> dtoResult = attributeFormatService.getAllFormatGruops();
		List<GoodsFormatGroupDTO> payList = dtoResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		/** 遍历DTO对象集合并生成要输出到界面的信息*/
		for (int i = 0; i < payList.size(); i++) {
			List<String> strings = new ArrayList<String>();
			GoodsFormatGroupDTO dto = payList.get(i);
			strings.add(dto.getFgroupId().toString());
			strings.add(dto.getFgroupName());
			lists.add(strings);
		}
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists,null);
		Gson gson = new Gson();
		String result = gson.toJson(listInfoDTO);
		return result;
	}
	
	
	/**
	 * 
	 *@Title:saveFormat
	 *@Description:保存规格
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-8-22下午6:04:25
	 */
	public String saveFormat() throws Exception{
		ReturnDTO data = new ReturnDTO();
		attributeFormatService.saveForamt(formatDTO,formatGroupRelaDTO);
		/** 2.保存操作日志信息*/
		functionsService.saveFunction("商品分类属性规格管理", 1, "增加规格");
		data.setFlag(true);
		return Utils.printInfo(data);
	}

	/**
	 * 
	 *@Title:getFormatsByGroup
	 *@Description:根据分组ID获取规格
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-8-22下午10:35:18
	 */
	public String getFormatsByGroup() throws Exception{
		List list = attributeFormatService.getFormatsByGroup(formatGroupRelaDTO.getFgroupId());
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			List<String> strings = new ArrayList<String>();
			Object[] o = (Object[])list.get(i);
			strings.add(o[0].toString());
			strings.add((String)o[1]);
			lists.add(strings);
		}
		/** 遍历DTO对象集合并生成要输出到界面的信息*/
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists,null);
		/** 向前台输出查询数据结果集字符串*/
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 *  商品分类查询
	 *@Title: getTypeListAjax
	 *@Description: 查询所有商品分类列表
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String getTypeListAjax() throws Exception {
		/** 查询带父子关系的商品分类列表  **/
		List<GoodsTypeDTO> goodTypeList = familyService.getFamilyCategoryList();
		GoodsTypeDTO goodTypeDTO = new GoodsTypeDTO();
		/** -1表示全部分类 **/
		goodTypeDTO.setId(-1);
		goodTypeDTO.setText("所有分类");
		goodTypeDTO.setChildren(goodTypeList);
		/** 满足jqueryui-combotree格式要求 **/
		List<GoodsTypeDTO> treeList = new ArrayList<GoodsTypeDTO>();
		treeList.add(goodTypeDTO);
		return Utils.printInfo(goodTypeList);
	}
	
	
	/**
	 * 
	 *@Title:checkAttributeName
	 *@Description:校验属性名称是否存在
	 *@Params:@param name
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:boolean
	 *@author:yanwuyang
	 *@Date:2014-8-24下午09:58:20
	 */
	public String checkAttributeName() throws Exception{
		ReturnDTO data = new ReturnDTO();
		boolean flag = attributeFormatService.checkAttributeName(attributeDTO.getAttrName());
		data.setFlag(flag);
		return Utils.printInfo(data);
	}
	

	/**
	 * 
	 *@Title:checFormatName
	 *@Description:校验规格名称
	 *@Params:@param name
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:boolean
	 *@author:yanwuyang
	 *@Date:2014-8-24下午09:58:38
	 */
	public String checkFormatName() throws Exception{
		ReturnDTO data = new ReturnDTO();
		boolean flag = attributeFormatService.checkFormatName(formatDTO.getFormatName(),formatGroupDTO.getFgroupId());
		data.setFlag(flag);
		return Utils.printInfo(data);
	}
	

	/**
	 * 
	 *@Title:checkFormatGroupName
	 *@Description:校验分组名称是否存在
	 *@Params:@param name
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:boolean
	 *@author:yanwuyang
	 *@Date:2014-8-24下午09:58:53
	 */
	public String checkFormatGroupName() throws Exception{
		ReturnDTO data = new ReturnDTO();
		boolean flag = attributeFormatService.checkFormatGroupName(formatGroupDTO.getFgroupName());
		data.setFlag(flag);
		return Utils.printInfo(data);
	}
	
	public GoodsFamilyDTO getFamilyDTO() {
		return familyDTO;
	}

	public void setFamilyDTO(GoodsFamilyDTO familyDTO) {
		this.familyDTO = familyDTO;
	}

	public GoodsAttributeDTO getAttributeDTO() {
		return attributeDTO;
	}

	public void setAttributeDTO(GoodsAttributeDTO attributeDTO) {
		this.attributeDTO = attributeDTO;
	}

	public List<GoodsFamilyAttrRelaDTO> getFamilyAttrRelaDTOs() {
		return familyAttrRelaDTOs;
	}

	public void setFamilyAttrRelaDTOs(
			List<GoodsFamilyAttrRelaDTO> familyAttrRelaDTOs) {
		this.familyAttrRelaDTOs = familyAttrRelaDTOs;
	}

	public GoodsFormatGroupDTO getFormatGroupDTO() {
		return formatGroupDTO;
	}

	public void setFormatGroupDTO(GoodsFormatGroupDTO formatGroupDTO) {
		this.formatGroupDTO = formatGroupDTO;
	}

	public List<GoodsFormatGroupRelaDTO> getFormatGroupRelaDTOs() {
		return formatGroupRelaDTOs;
	}

	public void setFormatGroupRelaDTOs(
			List<GoodsFormatGroupRelaDTO> formatGroupRelaDTOs) {
		this.formatGroupRelaDTOs = formatGroupRelaDTOs;
	}

	public GoodsFormatDTO getFormatDTO() {
		return formatDTO;
	}

	public void setFormatDTO(GoodsFormatDTO formatDTO) {
		this.formatDTO = formatDTO;
	}

	public GoodsFormatGroupRelaDTO getFormatGroupRelaDTO() {
		return formatGroupRelaDTO;
	}

	public void setFormatGroupRelaDTO(GoodsFormatGroupRelaDTO formatGroupRelaDTO) {
		this.formatGroupRelaDTO = formatGroupRelaDTO;
	}

	public List<GoodsFamilyGroupRelaDTO> getFamilyGroupRelaDTOs() {
		return familyGroupRelaDTOs;
	}

	public void setFamilyGroupRelaDTOs(
			List<GoodsFamilyGroupRelaDTO> familyGroupRelaDTOs) {
		this.familyGroupRelaDTOs = familyGroupRelaDTOs;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	
}
