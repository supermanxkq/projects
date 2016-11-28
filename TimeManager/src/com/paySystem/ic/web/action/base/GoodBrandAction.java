package com.paySystem.ic.web.action.base;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.base.BrandService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.GoodBrandsDTO;
import com.paySystem.ic.web.dto.system.UserSession;


/**  
* @Title: GoodBrandAction.java
* @Package: com.paySystem.ic.web.action.base
* @Description: 商品品牌管理类
* @Author: Jacky
* @Date: 2014-08-01
* @Version: V1.0  
*/
@Controller("/base/brand")
@Scope("prototype")
public class GoodBrandAction extends BaseAction {
	private static final long serialVersionUID = 8323032828662473270L;

	@Resource
	private BrandService brandService;
	
	@Resource
	FunctionsService functionsService;
	
	private GoodBrandsDTO goodBrandsDTO = new GoodBrandsDTO();

	public GoodBrandsDTO getGoodBrandsDTO() {
		return goodBrandsDTO;
	}

	public void setGoodBrandsDTO(GoodBrandsDTO goodBrandsDTO) {
		this.goodBrandsDTO = goodBrandsDTO;
	}
	
	/**
	 *  json列表查询
	 *@Title:jsonPageList
	 *@Description: 根据列表界面条件对数据进行分页查询
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {
		LinkedHashMap<String, String> orderby
        = new LinkedHashMap<String, String>();
		if (StringUtils.isNotBlank(this.getOrderProperty())
		    && StringUtils.isNotBlank(this.getOrderDirection())) {
		
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		
		} else { orderby.put("createTime", "desc"); }
		
		QueryResult<GoodBrandsDTO> dtoResult = 
		    brandService.queryGoodBrandByName((goodBrandsDTO.getPage()-1)*pageNum, pageNum, goodBrandsDTO, orderby);
		
		List<GoodBrandsDTO> brandList = dtoResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < brandList.size(); i++) {
			GoodBrandsDTO brand = brandList.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(Utils.getString(brand.getBrandName()));
			strings.add(Utils.getString(brand.getBrandUrl()));
			String descContent = Utils.getString(brand.getBrandDesc());
			strings.add(descContent.length() > 10?descContent.substring(0, 10)+"...":descContent);
			if(brand.getIsShow() == 0) {
				strings.add("是");
			} else {
				strings.add("否");
			}
			
			String operation = "";
			if (Utils.checkPermission("sy-1703-01")) {
				operation += "<a href='base/brand!checkUI?goodBrandsDTO.brandId="
						+ brand.getBrandId()
						+ "' title='查看'>"+Globals.IMG_VIEW+"</a>&nbsp;";
			}
			if(brand.getIsDeleted() == 0) {
				if (Utils.checkPermission("sy-1703-03")) {
					operation += "<a href='base/brand!editUI?goodBrandsDTO.brandId="
							+ brand.getBrandId()
							+ "' title='编辑'>"
							+ Globals.IMG_EDIT + "</a>&nbsp;";
				}
				if (Utils.checkPermission("sy-1703-04")) {
					operation += "<a href=javascript:deleteData('base/brand!delete','"
							+ brand.getBrandId()
							+ "') title='移除'>"
							+ Globals.IMG_DELETE + "</a>&nbsp;";
				}
			}
			strings.add(operation);
			lists.add(strings);
		}

		PageView pageView = new PageView(goodBrandsDTO.getPage(), dtoResult
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}
	
	/**
	 *  列表查询
	 *@Title:list
	 *@Description: 根据列表界面条件对数据进行分页查询
	 *@param:@return
	 *@param:@throws Exception
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String list() throws Exception {
		/** 获取当前操作员信息 **/
		UserSession us = Utils.getUserSession();
		
		/**
		 * 根据操作员不同级别进行界面跳转
		 * 
		 *    us.getUserLevel
		 *    0 : 平台操作员
		 *    1 : 机构操作员
		 *    2 : 商户操作员
		 *    操作员为平台操作员，进行界面正常跳转；
		 *    操作员为机构或商户操作员，进行拦截提示其不拥有该权限；
		 */
		switch (us.getUserLevel()) {
		case 0:
			break;
		case 1:
			return "intercepthtml";
		case 2:
			return "intercepthtml";
		}
		getRequest().setAttribute("showList", OptionsValue.isShowList);
		return "list";
	}
	
	/**
	 *  添加界面
	 *@Title: addUI
	 *@Description: 添加界面
	 *@return:String
	 *@author: Jacky
	 */
	public String addUI() {
		this.setMethod("addSave");
		getRequest().setAttribute("showList", OptionsValue.isShowList);
		return INPUT;
	}
	
	/**
	 * 查看商品品牌信息
	 *@Title: checkUI
	 *@Description: 查看商品品牌信息
	 *@return:String
	 *@author: Jacky
	 */
	public String checkUI() {
		this.setMethod("checkUI");
		if(goodBrandsDTO.getBrandId() < 1) {
			return ERROR;
		}
		try {
			goodBrandsDTO = brandService.findGoodBrandById(goodBrandsDTO.getBrandId());
			if (goodBrandsDTO != null) {
				getRequest().setAttribute("showList", OptionsValue.isShowList);
			}
		} catch (Exception e) {
			log.error("查询商品品牌信息出错！id="+goodBrandsDTO.getBrandId(),e);
			return ERROR;
		}
		return INPUT;
	}
	
	/**
	*  编辑页面
	 *@Title:editUI
	 *@Description: 进入编辑页面
	 *@param:@return
	 *@param:@throws Exception
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String editUI() throws Exception {
		
		this.setMethod("editSave");
		
		if(goodBrandsDTO.getBrandId() < 1) {
			this.getRequest().setAttribute("result", "参数无效id不能小于0");
			this.getRequest().setAttribute("url", "base/brand!list");
			return ERROR;
		}
		
		goodBrandsDTO = brandService.findGoodBrandById(Integer.valueOf(goodBrandsDTO.getBrandId()));

		if (goodBrandsDTO != null) {
			getRequest().setAttribute("showList", OptionsValue.isShowList);
			return INPUT;
		}
		return ERROR;
	}
	
	/**
	 *    新增保存
	 *    保存商品品牌信息
	 *@Title:addSave
	 *@Description:保存商品品牌新增内容
	 *@param:@return
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String addSave() {
		
		try {
			UserSession us = Utils.getUserSession();
			goodBrandsDTO.setOperator(us.getUserName());
			String validateMsg = brandService.validateGoodBrand(goodBrandsDTO);
			if(StringUtils.isNotBlank(validateMsg)) {
				this.getRequest().setAttribute("result", validateMsg);
				this.getRequest().setAttribute("url", "base/brand!addUI");
				return ERROR;
			}
			
			String errorMsg = brandService.saveGoodBrand(goodBrandsDTO);
			if(StringUtils.isNotBlank(errorMsg)) {
				this.getRequest().setAttribute("result", errorMsg);
				this.getRequest().setAttribute("url", "base/brand!list");
				return ERROR;
			}
			functionsService
			.saveFunction("商品品牌管理", 1, "增加商品品牌：" + goodBrandsDTO.getBrandId());
		} catch (Exception e) {
			this.getRequest().setAttribute("result", "保存商品品牌信息出错啦!");
			this.getRequest().setAttribute("url", "base/brand!list");
			return ERROR;
		}
		this.getRequest().setAttribute("result", "保存商品品牌信息成功!");
		this.getRequest().setAttribute("url", "base/brand!list");
		return SUCCESS;
	} 
	
	/**
	 *  编辑保存商品品牌信息
	 *@Title: editSave
	 *@Description:编辑保存商品品牌信息
	 *@param:@return
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String editSave() {
		
		try {
			if(brandService.validateName(goodBrandsDTO.getBrandId(), goodBrandsDTO.getBrandName())) {
				this.getRequest().setAttribute("result", "商品品牌名称已存在！");
				this.getRequest().setAttribute("url", "base/brand!list");
				return ERROR;
			}
			UserSession us = Utils.getUserSession();
			goodBrandsDTO.setOperator(us.getUserName());
			
			String validateMsg = brandService.validateGoodBrand(goodBrandsDTO);
			
			if(StringUtils.isNotBlank(validateMsg)) {
				this.getRequest().setAttribute("result", validateMsg);
				this.getRequest().setAttribute("url", "base/brand!list");
				return ERROR;
			}
			
			ReturnDTO returnDto = brandService.updateGoodBrand(goodBrandsDTO);
			if (returnDto.getFlag()) {
				functionsService.saveFunction("商品品牌管理", 2, "修改商品品牌："
						+ goodBrandsDTO.getBrandId());
				this.getRequest().setAttribute("result",this.getText("修改商品品牌信息成功!"));
				this.getRequest().setAttribute("url", "base/brand!list");
				return SUCCESS;
			} else {
				this.getRequest().setAttribute("result", returnDto.getMsg());
				this.getRequest().setAttribute("url", "base/brand!list");
				return ERROR;
			}
		} catch (Exception e) {
			this.getRequest().setAttribute("result", "修改商品品牌信息出错啦!");
			this.getRequest().setAttribute("url", "base/brand!list");
			return ERROR;
		}
		
	}
	
	/**
	 *  删除商品品牌
	 *@Title: delete
	 *@Description:删除商品品牌
	 *@param:@return
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String delete() {
		try {
			GoodBrandsDTO goodBrandTto = brandService.findGoodBrandById(Integer.valueOf(this.getId()));
			goodBrandTto.setIsDeleted((short)1);
			ReturnDTO returnDto = brandService.updateGoodBrand(goodBrandTto);
			if(returnDto.getFlag()) {
				functionsService.saveFunction("商品品牌管理", 3, "删除商品品牌："
						+ goodBrandTto.getBrandId());
				this.ajaxResult = "ajaxsuccess";
			} else {
				this.ajaxResult = "ajaxfailure";
				this.msgResult = returnDto.getMsg();
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}
	
	/**
	 *  商品品牌重名校验
	 *@Title: checkSameName
	 *@Description:商品品牌重名校验
	 *@param:@return
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String checkSameName() {
		ReturnDTO data = new ReturnDTO();
		boolean flag = false ;
		try {
			flag = brandService.checkSameName(goodBrandsDTO.getBrandName());
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		data.setFlag(flag);
		return Utils.printInfo(data);
	}
	
}
