package com.paySystem.ic.web.action.base;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.service.base.StoreInfoService;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.base.StoreInfoDTO;

/***
 * 
 * @ClassName:StoreInfoAction
 * @Description:店铺基本设置action
 * @date: 2014-9-23下午05:06:16
 * @author: 徐凯强
 * @version: V1.0
 */
@Controller("/base/storeInfo")
@Scope("prototype")
public class StoreInfoAction extends BaseAction implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 注入Service */
	@Resource
	private StoreInfoService storeInfoService;

	/** 实例化店铺基本设置数据传输对象 */
	private StoreInfoDTO storeInfoDTO = new StoreInfoDTO();

	public StoreInfoDTO getStoreInfoDTO() {
		return storeInfoDTO;
	}

	public void setStoreInfoDTO(StoreInfoDTO storeInfoDTO) {
		this.storeInfoDTO = storeInfoDTO;
	}

	/****
	 *@Title:list
	 *@Description:异步获取数据库中店铺基本设置信息
	 *@return:String返回字符串到Struts.xml文件中
	 *@author:徐凯强
	 */
	public String list() {
		storeInfoDTO = storeInfoService.findById(1);
		/**如果数据库中没有记录，为添加页面，如果有，为修改页面*/
		if (storeInfoDTO.getStoreId() == null) {
			/** 结算方式 */
			this.getRequest()
					.setAttribute("settWayValue", OptionsValue.SETTWAY);
			/** 经营类型 */
			this.getRequest().setAttribute("businTypeValue",
					OptionsValue.BUSINTYPE);
			/** 主要货源 */
			this.getRequest().setAttribute("mainProductValue",
					OptionsValue.MAINPRODUCT);
			/**
			 * 是否有实体店 0：有 1：无
			 */
			this.getRequest().setAttribute("isStoreOrNotValue",
					OptionsValue.VISIBLE_STATUS);
			/**
			 * 是否有工厂或仓库 0：有 1：无
			 */
			this.getRequest().setAttribute("isFactOrNotValue",
					OptionsValue.VISIBLE_STATUS);
			/**设置默认值*/
			storeInfoDTO.setMainProduct('0');
			storeInfoDTO.setIsFactOrNot('0');
			storeInfoDTO.setIsStoreOrNot('0');
			storeInfoDTO.setBusinType('0');
			this.setMethod("addSave");
		} else {
			this.setMethod("editSave");
			/** 结算方式 */
			this.getRequest()
					.setAttribute("settWayValue", OptionsValue.SETTWAY);
			/** 经营类型 */
			this.getRequest().setAttribute("businTypeValue",
					OptionsValue.BUSINTYPE);
			/** 主要货源 */
			this.getRequest().setAttribute("mainProductValue",
					OptionsValue.MAINPRODUCT);
			/**
			 * 是否有实体店 0：有 1：无
			 */
			this.getRequest().setAttribute("isStoreOrNotValue",
					OptionsValue.VISIBLE_STATUS);
			/**
			 * 是否有工厂或仓库 0：有 1：无
			 */
			this.getRequest().setAttribute("isFactOrNotValue",
					OptionsValue.VISIBLE_STATUS);
		}
		return "list";
	}

	/**
	 *@Title:addSave
	 *@Description:添加店铺设置信息
	 *@Return:String返回字符串到struts.xml文件中
	 *@author:徐凯强
	 *@Date:2014-9-23下午06:00:20
	 */
	public String addSave() {
		storeInfoService.addSave(storeInfoDTO);
		this.getRequest().setAttribute("url", "base/storeInfo!list");
		this.getRequest().setAttribute("result", "添加成功！");
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
		storeInfoService.updateStoreInfo(storeInfoDTO);
		this.getRequest().setAttribute("url", "base/storeInfo!list");
		this.getRequest().setAttribute("result", "修改成功！");
		return "success";
	}
}
