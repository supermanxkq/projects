package com.blog.web.action.profile;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.bean.QueryResult;
import com.blog.dto.profile.ProfileDTO;
import com.blog.service.profile.ProfileService;
import com.blog.web.action.BaseAction;

/***
 * 
 * @ClassName:StoreInfoAction
 * @Description:店铺基本设置action
 * @date: 2014-9-23下午05:06:16
 * @author: 徐凯强
 * @version: V1.0
 */
@Controller("/profile/profile")
@Scope("prototype")
public class ProfileAction extends BaseAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Resource
	private ProfileService profileService;
	private ProfileDTO profileDTO=new ProfileDTO();

	public String list() {
		QueryResult<ProfileDTO> prQueryResult = profileService.findProfiles();
		profileDTO=prQueryResult.getResultlist().get(0);
		return "list";
		// if (Utils.getUserSession().getUserLevel() != 0) {
		// storeInfoDTO = storeInfoService.findById(Utils.getUserSession()
		// .getMerId());
		// if (storeInfoDTO.getImageFileFileName() != null) {
		// storeInfoDTO.setImageFileFileName(storeInfoDTO.getImageFileFileName().replace("\\",
		// "/"));
		// }
		/** 获取数据库表中的所有的省，然后使用request对象传到页面中 */
		// List<AreaDicDTO> areaDicDTOs = storeInfoService.findProvinces();
		// List<OptionsInteger> areaDicDTOsValue = new
		// ArrayList<OptionsInteger>();
		/** 省 */
		// for (int i = 0; i < areaDicDTOs.size(); i++) {
		// areaDicDTOsValue.add(new OptionsInteger(areaDicDTOs.get(i)
		// .getFcode(), areaDicDTOs.get(i).getFname()));
		// }
		// this.getRequest()
		// .setAttribute("areaDicDTOsValue", areaDicDTOsValue);
		// /** 结算方式 */
		// this.getRequest()
		// .setAttribute("settWayValue", OptionsValue.SETTWAY);
		// /** 经营类型 */
		// this.getRequest().setAttribute("businTypeValue",
		// OptionsValue.BUSINTYPE);
		// /** 主要货源 */
		// this.getRequest().setAttribute("mainProductValue",
		// OptionsValue.MAINPRODUCT);
		// /**
		// * 是否有实体店 0：有 1：无
		// */
		// this.getRequest().setAttribute("isStoreOrNotValue",
		// OptionsValue.VISIBLE_STATUS);
		// /**
		// * 是否有工厂或仓库 0：有 1：无
		// */
		// this.getRequest().setAttribute("isFactOrNotValue",
		// OptionsValue.VISIBLE_STATUS);
		// /**是否支持银行卡**/
		// this.getRequest().setAttribute("isBankCard",
		// OptionsValue.ISBANKCARD);
		// /** 如果数据库中没有记录，为添加页面，如果有，为修改页面 */
		// if (storeInfoDTO.getStoreId() == null) {
		// /** 设置默认值 */
		// storeInfoDTO.setMainProduct('0');
		// storeInfoDTO.setIsFactOrNot('0');
		// storeInfoDTO.setIsStoreOrNot('0');
		// storeInfoDTO.setBusinType('0');
		// if (storeInfoDTO.getImageFileFileName() != null) {
		// storeInfoDTO.setImageFileFileName(storeInfoDTO.getImageFileFileName().replace("\\",
		// "/"));
		// }
		// this.setMethod("addSave");
		// } else {
		// this.setMethod("editSave");
		// }
		// return "list";
		// } else {
		// return "intercepthtml";
		// }
	}

	/**
	 *@Title:addSave
	 *@Description:添加店铺设置信息
	 *@Return:String返回字符串到struts.xml文件中
	 *@author:徐凯强
	 *@Date:2014-9-23下午06:00:20
	 */
	public String addSave() {
		// storeInfoService.addSave(storeInfoDTO);
		// if(type.equals("tab")){
		// this.getRequest().setAttribute("result",
		// this.getText("添加成功！"));
		// this.getRequest().setAttribute("url", "base/merchants!list");
		// }else{
		// this.getRequest().setAttribute("url", "base/storeInfo!list");
		// this.getRequest().setAttribute("result", "添加成功！");
		// }
		return "success";
	}

	/**
	 *@Title:editSave
	 *@Description:修改信息
	 *@Return:String返回字符串到Struts.xml文件
	 *@author:徐凯强
	 *@Date:2014-9-23下午06:59:07
	 */
	public String editSave() {
		// storeInfoService.updateStoreInfo(storeInfoDTO);

		// if(type.equals("tab")){
		// this.getRequest().setAttribute("result",
		// this.getText("修改成功！"));
		// this.getRequest().setAttribute("url", "base/merchants!list");
		// }else{
		// this.getRequest().setAttribute("url", "base/storeInfo!list");
		// this.getRequest().setAttribute("result", "修改成功！");
		// }
		return "success";
	}

	public ProfileDTO getProfileDTO() {
		return profileDTO;
	}

	public void setProfileDTO(ProfileDTO profileDTO) {
		this.profileDTO = profileDTO;
	}

	/**
	 *@Title:findCityByProvinceId
	 *@Description:根据省查找城市
	 *@Return:String返回字符串到Struts.xml文件
	 *@author:徐凯强
	 *@Date:2014-11-4上午11:44:33
	 */
	// public String findCityByProvinceId() {
	// List<AreaDicDTO> areaDicDTOs = storeInfoService
	// .findCityByProvinceId(areaDicDTO);
	// List<OptionsInteger> areaDicDTOsValue = new ArrayList<OptionsInteger>();
	// for (int i = 0; i < areaDicDTOs.size(); i++) {
	// areaDicDTOsValue.add(new OptionsInteger(areaDicDTOs.get(i)
	// .getFcode(), areaDicDTOs.get(i).getFname()));
	// }
	// returnDTO.setObj(areaDicDTOsValue);
	// return Utils.printInfo(returnDTO);
	// }

	/**
	 *@Title:chooseTypes
	 *@Description:选择商品类型
	 *@Return:String返回字符串到struts.xml文件中
	 *@author:徐凯强
	 *@Date:2014-11-4下午04:06:03
	 */
	// public String chooseTypes() {
	// areaDicDTO.setFcode(110000);
	// List<GoodsFamilyDTO> goodsFamilyDTOs = storeInfoService.chooseTypes();
	// List<OptionsInteger> goodsFamilyDTOsValue = new
	// ArrayList<OptionsInteger>();
	// for (int i = 0; i < goodsFamilyDTOs.size(); i++) {
	// goodsFamilyDTOsValue.add(new OptionsInteger(goodsFamilyDTOs.get(i)
	// .getFamilyId(), goodsFamilyDTOs.get(i).getFamilyName()));
	// }
	// returnDTO.setObj(goodsFamilyDTOsValue);
	// return Utils.printInfo(returnDTO);
	// }

	/**
	 *@Title:insertRel
	 *@Description:插入商品类型和店铺关系id
	 *@Return:String返回字符串结果到struts.xml
	 *@author:徐凯强
	 *@Date:2014-11-6上午11:06:50
	 */
	// public String insertRel() {
	// try {
	// storeInfoService.insertRel(storeGtypeRelDTO);
	// } catch (Exception e) {
	// return "ajaxfailure";
	// }
	// return "ajaxsuccess";
	// }

	/**
	 *@Title:deleteAll
	 *@Description:删除所有已选分类
	 *@Return:String返回字符串结果到struts.xml
	 *@author:徐凯强
	 *@Date:2014-11-6下午12:51:31
	 */
	// public String deleteAll() {
	// try {
	// storeInfoService.deleteAll();
	// } catch (Exception e) {
	// return "ajaxfailure";
	// }
	// return "ajaxsuccess";
	// }

	/**
	 *@Title:findCheckedTypes
	 *@Description:查找已选类型
	 *@Return:String返回字符串结果到struts.xml
	 *@author:徐凯强
	 *@Date:2014-11-6下午01:18:00
	 */
	// public String findCheckedTypes() {
	// List<GoodsFamilyDTO> goodsFamilyDTOs = storeInfoService
	// .findCheckedTypes();
	// List<OptionsInteger> goodsFamilyDTOsValue = new
	// ArrayList<OptionsInteger>();
	// for (int i = 0; i < goodsFamilyDTOs.size(); i++) {
	// goodsFamilyDTOsValue.add(new OptionsInteger(goodsFamilyDTOs.get(i)
	// .getFamilyId(), goodsFamilyDTOs.get(i).getFamilyName()));
	// }
	// returnDTO.setObj(goodsFamilyDTOsValue);
	// return Utils.printInfo(returnDTO);
	// }

	/**
	 *@Title:deleteById
	 *@Description:根据编号删除
	 *@Return:String返回字符串结果到struts.xml
	 *@author:徐凯强
	 *@Date:2014-11-6下午01:18:00
	 */
	// public String deleteById() {
	// try {
	// storeInfoService.deleteById(storeGtypeRelDTO);
	// } catch (Exception e) {
	// return "ajaxfailure";
	// }
	// return "ajaxsuccess";
	// }
	
}
