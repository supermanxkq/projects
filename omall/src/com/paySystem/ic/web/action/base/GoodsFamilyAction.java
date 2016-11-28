package com.paySystem.ic.web.action.base;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsFamily;
import com.paySystem.ic.bean.base.GoodsFamilyAdvertRelation;
import com.paySystem.ic.bean.base.GoodsFamilyAttrRela;
import com.paySystem.ic.service.base.GoodsFamilyAdvertRelationService;
import com.paySystem.ic.service.base.GoodsFamilyAttrRelaService;
import com.paySystem.ic.service.base.GoodsFamilyRateRelService;
import com.paySystem.ic.service.base.GoodsFamilyService;
import com.paySystem.ic.service.base.StoreInfoService;
import com.paySystem.ic.service.base.impl.GoodsFamilyAdvertRelationServiceImpl;
import com.paySystem.ic.service.base.impl.GoodsFamilyAttrRelaServiceImpl;
import com.paySystem.ic.service.base.impl.GoodsFamilyRateRelServiceImpl;
import com.paySystem.ic.service.base.impl.StoreInfoServiceImpl;
import com.paySystem.ic.service.buss.PromotionService;
import com.paySystem.ic.service.buss.impl.PromotionServiceImpl;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.UploadUtil;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyAdvertRelationDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyAttrRelaDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ClassName:GoodsFamilyAction
 * @Description:商品分类管理Action
 * @date: 2014-6-26下午03:38:04
 * @author: 张亚运
 * @version: V1.0
 */
@Controller("/base/goodsfamily")
@Scope("prototype")
public class GoodsFamilyAction extends BaseAction {

	/**
	 * 版本序列号
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	GoodsFamilyService goodsfamilyService;

	@Resource
	FunctionsService functionsService;
	@Resource
	GoodsFamilyAttrRelaService goodsFamilyAttrRelaService = new GoodsFamilyAttrRelaServiceImpl();
	@Resource
	GoodsFamilyAdvertRelationService goodsFamilyAdvertRelationService = new GoodsFamilyAdvertRelationServiceImpl();
	@Resource
	private StoreInfoService storeInfoService = new StoreInfoServiceImpl();
	@Resource
	private PromotionService promotionService = new PromotionServiceImpl();
	@Resource
	private GoodsFamilyRateRelService goodsFamilyRateRelService=new GoodsFamilyRateRelServiceImpl();
	/** 上传的文件 **/
	private File upload;

	/** 上传文件的类型 **/
	private String uploadContentType;

	/** 上传文件名 **/
	private String uploadFileName;

	/** 父节点id **/
	private Integer parentId;
	/** 广告类型 **/
	private Integer type;

	public GoodsFamilyRateRelService getGoodsFamilyRateRelService() {
		return goodsFamilyRateRelService;
	}

	public void setGoodsFamilyRateRelService(
			GoodsFamilyRateRelService goodsFamilyRateRelService) {
		this.goodsFamilyRateRelService = goodsFamilyRateRelService;
	}

	public PromotionService getPromotionService() {
		return promotionService;
	}

	public void setPromotionService(PromotionService promotionService) {
		this.promotionService = promotionService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	private GoodsFamilyDTO goodsfamilyDto = new GoodsFamilyDTO();

	public GoodsFamilyDTO getGoodsfamilyDto() {
		return goodsfamilyDto;
	}

	public void setGoodsfamilyDto(GoodsFamilyDTO goodsfamilyDto) {
		this.goodsfamilyDto = goodsfamilyDto;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public GoodsFamilyService getGoodsfamilyService() {
		return goodsfamilyService;
	}

	public void setGoodsfamilyService(GoodsFamilyService goodsfamilyService) {
		this.goodsfamilyService = goodsfamilyService;
	}

	public FunctionsService getFunctionsService() {
		return functionsService;
	}

	public void setFunctionsService(FunctionsService functionsService) {
		this.functionsService = functionsService;
	}

	public GoodsFamilyAttrRelaService getGoodsFamilyAttrRelaService() {
		return goodsFamilyAttrRelaService;
	}

	public void setGoodsFamilyAttrRelaService(
			GoodsFamilyAttrRelaService goodsFamilyAttrRelaService) {
		this.goodsFamilyAttrRelaService = goodsFamilyAttrRelaService;
	}

	public GoodsFamilyAdvertRelationService getGoodsFamilyAdvertRelationService() {
		return goodsFamilyAdvertRelationService;
	}

	public void setGoodsFamilyAdvertRelationService(
			GoodsFamilyAdvertRelationService goodsFamilyAdvertRelationService) {
		this.goodsFamilyAdvertRelationService = goodsFamilyAdvertRelationService;
	}

	public StoreInfoService getStoreInfoService() {
		return storeInfoService;
	}

	public void setStoreInfoService(StoreInfoService storeInfoService) {
		this.storeInfoService = storeInfoService;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 *@Description:进入List页面
	 *@return:String
	 *@author: 张亚运
	 *@throws:
	 */
	public String list() {
		// this.getRequest().setAttribute("statusValues",
		// OptionsValue.STATE_STATUS);
		this.getRequest().setAttribute("statusValues",
				OptionsValue.STATE_STATUS);
		return "list";
	}

	/**
	 *@Description:加载页面数据
	 *@return:String
	 *@author: 张亚运
	 *@throws:
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() {

		UserSession us = Utils.getUserSession();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		/** 判断排序参数是否有值 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			/** 如果页面没有要求排序方式，则设置按照分类编号排序 */
			orderby.put("parentId", "asc");
		}

		List<GoodsFamilyDTO> listDto = new ArrayList<GoodsFamilyDTO>();
		QueryResult<GoodsFamilyDTO> result = null;
		try {
			result = goodsfamilyService.queryAll((goodsfamilyDto.getPage() - 1)
					* pageNum, pageNum, goodsfamilyDto, orderby);
			listDto = result.getResultlist();
		} catch (Exception e) {
			e.getMessage();
		}
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < listDto.size(); i++) {
			/** 向页面赋值 */
			GoodsFamilyDTO dto = listDto.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			strings.add(Utils.getString(dto.getFamilyName()));
			strings.add(Utils.getString(dto.getNodeLevel() + "级分类"));
			strings.add(Utils.getOptionsIntegerName(
					OptionsValue.VISIBLE_STATUS_CONV, dto.getDefaultShow()));
			strings.add(Utils.getOptionsIntegerName(
					OptionsValue.GoodsFamily_STATUS, dto.getFamilyWay()));
			strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUS,
					dto.getStatus()));
			strings.add(DateTimeTool.dateFormat("", dto.getCreateTime()));
			String operation = "";

			if (us.getUserLevel() == 0) {

				/** 判断权限在相关操作栏显示相关操作，若状态为删除状态则不显示 */
				if (dto.getStatus() != 9) {
					if (Utils.checkPermission("sy-1701-01")) {
						operation += "<a href =base/goodsfamily!checkUI?goodsfamilyDto.familyId="
								+ dto.getFamilyId()
								+ " title='查看'>"
								+ Globals.IMG_VIEW + "</a>&nbsp;";
					}
					if (Utils.checkPermission("sy-1701-03")) {
						operation += "<a href =base/goodsfamily!editUI?goodsfamilyDto.familyId="
								+ dto.getFamilyId()
								+ " title='修改'>"
								+ Globals.IMG_EDIT + "</a>&nbsp;";
					}
					if (Utils.checkPermission("sy-1701-04")) {
						operation += "<a href=javascript:deleteData('base/goodsfamily!delete','"
								+ dto.getFamilyId()
								+ "') title='删除'>"
								+ Globals.IMG_DELETE + "</a>&nbsp;";
					}
				} else {
					operation += "<a href =base/goodsfamily!checkUI?goodsfamilyDto.familyId="
							+ dto.getFamilyId()
							+ " title='查看'>"
							+ Globals.IMG_VIEW + "</a>&nbsp;";

				}

			}

			strings.add(operation);
			lists.add(strings);
		}
		PageView pageView = new PageView(goodsfamilyDto.getPage(), result
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);

	}

	/**
	 *@Description:添加按钮方法
	 *@return:String
	 *@author: 张亚运
	 *@throws:
	 */
	public String addUI() {

		this.setMethod("addSave");
		goodsfamilyDto.setCreateFloorSign(0);
		goodsfamilyDto.setShowAdvertSign(0);

		this.getRequest().setAttribute("statusValues",
				OptionsValue.STATE_STATUSD);
		this.getRequest().setAttribute("familySign",
				OptionsValue.GoodsFamily_STATUS);
		this.getRequest().setAttribute("visibleValues",
				OptionsValue.VISIBLE_STATUS_CONV);
		this.getRequest().setAttribute("advertContents",
				OptionsValue.advertContents);
		return INPUT;
	}

	/**
	 *@Description:添加保存方法
	 *@return:String
	 *@author: 张亚运
	 * @throws Exception
	 * @throws Exception
	 *@throws:
	 */
	public String addSave() throws Exception {

		/** 所属分类为顶级分类则节点等级为1级，否则则为选择分类的节点等级+1 */
		if (goodsfamilyDto.getParentId() != 0) {
			goodsfamilyDto.setNodeLevel(goodsfamilyService
					.getNodeLevel(goodsfamilyDto.getParentId()) + 1);
		} else {
			goodsfamilyDto.setNodeLevel(1);
		}
		/** 上传文件 **/
		File file = getUpload();

		/** 上传文件类型 **/
		@SuppressWarnings("unused")
		String fileType = getUploadContentType();

		/** 上传文件名字 **/
		String fileName = getUploadFileName();

		/** 如果上传文件非null **/
		if (file != null) {

			/** 保存的文件名称，使用UUID+后缀进行保存 **/
			@SuppressWarnings("unused")
			String realName = UUID.randomUUID().toString() + getExt(fileName);

			/** 调用上传方法，返回上传后的地址 **/
			String imageUrl = UploadUtil.uploadImg(file, fileName);

			/** 图片地址 **/
			goodsfamilyDto.setPicPath(imageUrl);
		}
		GoodsFamily goodsFamily = goodsfamilyService
				.saveGoodsFamily(goodsfamilyDto);
		functionsService.saveFunction("商品分类管理", 1, "添加商品分类："
				+ goodsFamily.getFamilyId());
		this.getRequest().setAttribute("result",
				this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url", "base/goodsfamily!list");

		return SUCCESS;
	}

	/**
	 *@Description:修改按钮方法
	 *@return:String
	 *@author: 张亚运
	 * @throws Exception
	 *@throws:
	 */
	public String editUI() throws Exception {
		this.setMethod("editSave");
		goodsfamilyDto = goodsfamilyService.find(goodsfamilyDto.getFamilyId());
		if (goodsfamilyDto.getParentId() == 0) {
			goodsfamilyDto.setPFamilyName("顶级分类");
			goodsfamilyDto.setParentLevel(1);
		} else {
			GoodsFamilyDTO pdto = goodsfamilyService.find(goodsfamilyDto
					.getParentId());
			goodsfamilyDto.setAdvertContentSign(pdto.getAdvertContentSign());
			goodsfamilyDto.setPFamilyName(pdto.getFamilyName());
			goodsfamilyDto.setParentLevel(pdto.getNodeLevel());
		}
		if (goodsfamilyDto.getPicPath() != null
				&& goodsfamilyDto.getPicPath() != "") {
			String picPathString = goodsfamilyDto.getPicPath();
			goodsfamilyDto.setPicPath(picPathString.replace('\\', '/'));
		}
		if (goodsfamilyDto.getShowAdvertSign() != null
				&& goodsfamilyDto.getShowAdvertSign() == 1) {
			GoodsFamilyAdvertRelationDTO g = goodsFamilyAdvertRelationService
					.findByFamilyId(goodsfamilyDto.getFamilyId());
			goodsfamilyDto.setAdvertContentSign(g.getAdvertContentSign());
			goodsfamilyDto.setObjectId(g.getObjectId());
			goodsfamilyDto.setAdvertPicPath(g.getAdvertPicPath().replace("\\",
					"/"));
		}
		// List<OptionsInteger> preFamilyValues = new
		// ArrayList<OptionsInteger>();
		// preFamilyValues.add(new OptionsInteger(0, "顶级分类"));
		// preFamilyValues.addAll(goodsfamilyService.getFamilyOption());
		// this.getRequest().setAttribute("preFamilyValues", preFamilyValues);
		this.getRequest().setAttribute("statusValues",
				OptionsValue.STATE_STATUSD);
		/** 商品分类形式 */
		this.getRequest().setAttribute("familySign",
				OptionsValue.GoodsFamily_STATUS);
		this.getRequest().setAttribute("visibleValues",
				OptionsValue.VISIBLE_STATUS_CONV);
		this.getRequest().setAttribute("advertContents",
				OptionsValue.advertContents);
		/** 手续费类型 **/
		this.getRequest().setAttribute("chargeRateType",
				OptionsValue.CHARGERATETYPE);
		/** 手续费类型 **/
		this.getRequest().setAttribute("chargeRateType", OptionsValue.CHARGERATETYPE);
		
		return INPUT;
	}

	/**
	 *@Description:修改保存方法
	 *@return:String
	 *@author: 张亚运
	 * @throws Exception
	 *@throws:
	 */
	public String editSave() throws Exception {

		/** 所属分类为顶级分类则节点等级为1级，否则则为选择分类的节点等级+1 */
		if (goodsfamilyDto.getParentId() != 0) {
			goodsfamilyDto.setNodeLevel(goodsfamilyService
					.getNodeLevel(goodsfamilyDto.getParentId()) + 1);
		} else {
			goodsfamilyDto.setNodeLevel(1);
		}

		/** 上传文件 **/
		File file = getUpload();

		/** 上传文件类型 **/
		@SuppressWarnings("unused")
		String fileType = getUploadContentType();

		/** 上传文件名字 **/
		String fileName = getUploadFileName();

		/** 如果上传文件非null **/
		if (file != null) {

			/** 保存的文件名称，使用UUID+后缀进行保存 **/
			@SuppressWarnings("unused")
			String realName = UUID.randomUUID().toString() + getExt(fileName);

			/** 调用上传方法，返回上传后的地址 **/
			String imageUrl = UploadUtil.uploadImg(file, fileName);

			/** 图片地址 **/
			goodsfamilyDto.setPicPath(imageUrl);
		}

		try {
			ReturnDTO dto = goodsfamilyService
					.updateGoodsFamily(goodsfamilyDto);
			if (dto.getFlag()) {
				functionsService.saveFunction("商品分类管理", 2, "商品信息修改："
						+ goodsfamilyDto.getFamilyId());
				this.getRequest().setAttribute("result",
						this.getText("operation.success.notice"));
				this.getRequest().setAttribute("url", "base/goodsfamily!list");
				return SUCCESS;
			} else {
				this.getRequest().setAttribute("result", dto.getMsg());
				this.getRequest().setAttribute("url", "base/goodsfamily!list");
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	/**
	 *@Description:删除更新方法
	 *@return:String
	 *@author: 张亚运
	 *@throws:
	 */
	public String delete() {
		try {
			goodsfamilyService.deleteGoodsFamily(Integer.valueOf(this.getId()));
			functionsService.saveFunction("商品分类管理", 3, "删除商品分类："
					+ goodsfamilyDto.getFamilyId());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}

	public String checkUI() throws Exception {
		editUI();
		this.setMethod("checkUI");
		/*
		 * goodsfamilyDto =
		 * goodsfamilyService.find(goodsfamilyDto.getFamilyId()); if
		 * (goodsfamilyDto.getParentId() == 0) {
		 * goodsfamilyDto.setPFamilyName("顶级分类"); } else { GoodsFamilyDTO pdto =
		 * goodsfamilyService.find(goodsfamilyDto .getParentId());
		 * goodsfamilyDto.setPFamilyName(pdto.getFamilyName()); } if
		 * (goodsfamilyDto.getPicPath() != null && goodsfamilyDto.getPicPath()
		 * != "") { String picPathString = goodsfamilyDto.getPicPath();
		 * 
		 * goodsfamilyDto.setPicPath(picPathString.replace('\\', '/'));
		 * 
		 * } // List<OptionsInteger> preFamilyValues = new //
		 * ArrayList<OptionsInteger>(); // preFamilyValues.add(new
		 * OptionsInteger(0, "顶级分类")); //
		 * preFamilyValues.addAll(goodsfamilyService.getFamilyOption()); //
		 * this.getRequest().setAttribute("preFamilyValues", preFamilyValues);
		 * this.getRequest().setAttribute("statusValues",
		 * OptionsValue.STATE_STATUS); /** 商品分类形式
		 * this.getRequest().setAttribute("familySign",
		 * OptionsValue.GoodsFamily_STATUS);
		 * this.getRequest().setAttribute("visibleValues",
		 * OptionsValue.VISIBLE_STATUS_CONV);
		 */
		return INPUT;
	}

	/**
	 *@Description:检验分类名称是否存在
	 *@return:String
	 *@author: 张亚运
	 *@throws:
	 */
	public String checkName() {
		ReturnDTO dto = new ReturnDTO();
		if (goodsfamilyService.validateName(goodsfamilyDto.getFamilyName(),
				goodsfamilyDto.getFamilyId())) {
			dto.setFlag(true);
		}
		return Utils.printInfo(dto);
	}

	/**
	 * 
	 *@Title:getExt
	 *@Description:得到上传文件名
	 *@Params:@param fileName
	 *@Params:@return
	 *@Return:String
	 *@author:毛智东
	 *@Date:2014-11-6下午02:19:08
	 */
	public static String getExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 
	 *@Title:ajaxFamily
	 *@Description:ajax加载分类属性
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:String
	 *@author:毛智东
	 *@Date:2014-11-7上午09:56:45
	 */
	public String ajaxFamily() throws Exception {
		List<GoodsFamilyDTO> list = goodsfamilyService
				.getFamilyByParent(goodsfamilyDto.getParentId() == null ? 0
						: goodsfamilyDto.getParentId());
		return Utils.printInfo(list);
	}

	public void ajaxObject() {
		boolean flag = false;

		if (type == 0) {// 商品广告
			flag = goodsfamilyService.ajaxObject(this.id);
		} else if (type == 1) {// 店铺广告
			flag = storeInfoService.ajaxObject(this.id);
		} else if (type == 2) {// 品牌广告，暂无
			flag = false;
		} else if (type == 3) {// 活动广告
			flag = promotionService.ajaxObject(id);
		}
		Utils.printInfo(flag);
	}
}
