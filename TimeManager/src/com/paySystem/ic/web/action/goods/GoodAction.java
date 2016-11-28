package com.paySystem.ic.web.action.goods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.goods.VioRegul;
import com.paySystem.ic.service.base.GoodsFamilyService;
import com.paySystem.ic.service.base.GoodsInfoService;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
import com.paySystem.ic.web.dto.base.KeyValue;
import com.paySystem.ic.web.dto.buss.MerPromotionDTO;
import com.paySystem.ic.web.dto.buss.PromotionDTO;
import com.paySystem.ic.web.dto.goods.DynamicAttrDTO;
import com.paySystem.ic.web.dto.goods.FormatInfoDTO;
import com.paySystem.ic.web.dto.goods.GoodsDTO;
import com.paySystem.ic.web.dto.goods.GoodsFormatNameDTO;
import com.paySystem.ic.web.dto.goods.GoodsTypeDTO;
import com.paySystem.ic.web.dto.goods.MerUnruleDTO;
import com.paySystem.ic.web.dto.goods.PlatHandleRecordDTO;
import com.paySystem.ic.web.dto.goods.TypeAttrDTO;
import com.paySystem.ic.web.dto.goods.UnruleTypeDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsInteger;

/**  
* @Title: GoodBrandAction.java
* @Package: com.paySystem.ic.web.action.base
* @Description: 商品品牌管理类
* @Author: Jacky
* @Date: 2014-08-01
* @Version: V1.0  
*/
@Controller("/base/goods")
@Scope("prototype")
public class GoodAction extends BaseAction {
	private static final long serialVersionUID = 4566492141509678748L;
	
	@Resource
	private GoodsInfoService goodsInfoService;
	
	@Resource
	private GoodsFamilyService goodsFamilyService;
	
	private GoodsDTO goodsDTO = new GoodsDTO();
	
	private PlatHandleRecordDTO platHandleDTO = new PlatHandleRecordDTO();
	
	private UnruleTypeDTO unruleTypeDTO;
	
	private static  Pattern pattern = Pattern.compile("^formatId_[0-9]+$");
	
	/**
	 *  列表查询
	 *@Title:list
	 *@Description: 根据列表界面条件对数据进行分页查询
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
		}
		getRequest().setAttribute("userLevel", us.getUserLevel());
		getRequest().setAttribute("upoffList", OptionsValue.UPOFFSALE);
		getRequest().setAttribute("goodsSta", OptionsValue.GOODSTA);
		getRequest().setAttribute("goodsIllSta", OptionsValue.GOODILLSTA);
		
		return "list";
	}
	
	/**
	 *  批量下架商品
	 *@Title: batchOfflineGood
	 *@Description: 批量下架商品
	 *@param:@throws Exception
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String batchOfflineGood() throws Exception {
		if(CollectionUtils.isNotEmpty(goodsDTO.getGoodsCheckId())) {
			goodsInfoService.batchOfflineGoods(goodsDTO.getGoodsCheckId());
		}
		this.getRequest().setAttribute("result", "批量下架商品成功!");
		this.getRequest().setAttribute("url", "base/goods!list");
		return 	SUCCESS;
	}
	
	/**
	 *  批量上架商品
	 *@Title: batchUpLineGood
	 *@Description:  批量上架商品
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String batchUpLineGood() throws Exception {
		if(CollectionUtils.isNotEmpty(goodsDTO.getGoodsCheckId())) {
			goodsInfoService.batchOnlieGoods(goodsDTO.getGoodsCheckId());
		}
		this.getRequest().setAttribute("result", "批量上架商品成功!");
		this.getRequest().setAttribute("url", "base/goods!list");
		return SUCCESS;
	}
	
	/**
	 *  查看违规处理商品信息
	 *@Title: showDealGood
	 *@Description: 查看违规处理商品信息
	 *@param:@throws Exception
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String showDealGood() throws Exception {
		/** 获取当前操作员信息 **/
		UserSession us = Utils.getUserSession();
		/** 只有操作员才能处理 **/
		switch (us.getUserLevel()) {
			case 0:
				break;
			default:
				return "intercepthtml";
		}
		GoodsDTO goodsDTOs = goodsInfoService.findGoodsDTOById(goodsDTO.getGoodsId());
		if(null == goodsDTOs) return ERROR;
		
		MerUnruleDTO merUnRuleDTO = goodsInfoService.findLatestUnRule(goodsDTO.getGoodsId());
		if(null != merUnRuleDTO) {
			platHandleDTO.setDeductScore(merUnRuleDTO.getDeductScore());
			platHandleDTO.setGoodPunishType(merUnRuleDTO.getUnRuleBehald());
			platHandleDTO.setPhType(merUnRuleDTO.getPhType());
			platHandleDTO.setVioRugleId(String.valueOf(merUnRuleDTO.getUnruleWay()));
			PlatHandleRecordDTO recordDTO = goodsInfoService.queryLastedRecord(goodsDTO.getGoodsId());
			if(null != recordDTO) {
				platHandleDTO.setPhReason(recordDTO.getPhReason());
				platHandleDTO.setPhDescr(recordDTO.getPhDescr());
			}
			/** 违规案例**/
			List<VioRegul> list = goodsInfoService.getVioRegulByTypeId(merUnRuleDTO.getUnRuleBehald());
			getRequest().setAttribute("case1", list);
		}	else {
			getRequest().setAttribute("case1", new ArrayList<VioRegul>());
		}
		
		/** 违规类型 **/
		getRequest().setAttribute("illegalType", getAllUnruleTypes());
		/** 处罚方式**/
		getRequest().setAttribute("punishMethod", OptionsValue.PUNISHMETHOD);
		
		this.setMethod("dealGoods");
		
		goodsDTO = goodsDTOs;
		return "dealpage";
	}
	
	/**
	 * 获取所有违规类型
	 *@Title: getAllUnruleTypes
	 *@Description: 获取所有违规类型
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	private  List<OptionsInteger> getAllUnruleTypes() throws Exception{
		List<OptionsInteger> unruleTypes = new ArrayList<OptionsInteger>();
		QueryResult<UnruleTypeDTO> queryResult=goodsInfoService.getAllUnruleTypes();
		List<UnruleTypeDTO> typeDTOs=queryResult.getResultlist();
		for (int i = 0; i < typeDTOs.size(); i++) {
			UnruleTypeDTO typeDTO = typeDTOs.get(i);
			OptionsInteger integer = new OptionsInteger(typeDTO.getUnruleTypeId(),typeDTO.getUnruleTypeName());
			unruleTypes.add(integer);
		}
		return unruleTypes;
	}
	
	/**
	 *  根据typeId获取违规案例
	 *@Title: getVioRegulByTypeId
	 *@Description: 根据typeId获取违规案例
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String getVioRegulByTypeId(){
		List<VioRegul> list = goodsInfoService.getVioRegulByTypeId(unruleTypeDTO.getUnruleTypeId());
		/** 遍历DTO对象集合并生成要输出到界面的信息*/
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			List<String> strings = new ArrayList<String>();
			VioRegul dto = list.get(i);
			strings.add(String.valueOf(dto.getUnruleId()));
			strings.add(dto.getUnruleWay());
			lists.add(strings);
		}
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists,null);
		/** 向前台输出查询数据结果集字符串*/
		return Utils.printInfo(listInfoDTO);
	}
	
	/**
	 *  处理商品
	 *@Title: dealGoods
	 *@Description: 处理商品
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String dealGoods() throws Exception {
		UserSession us = Utils.getUserSession();
		if(us.getUserLevel() != 0) {
			this.getRequest().setAttribute("result", "您没有权限处理该商品!");
			this.getRequest().setAttribute("url", "base/goods!list");
			return ERROR;
		}
		platHandleDTO.setOperatorId(us.getUserName());
		String error = goodsInfoService.dealGoodUnRule(platHandleDTO);
		if(StringUtils.isBlank(error)) {
			this.getRequest().setAttribute("result", "商品处理成功!");
			this.getRequest().setAttribute("url", "base/goods!list");
			return SUCCESS;
		} else {
			this.getRequest().setAttribute("result", "商品处理失败!");
			this.getRequest().setAttribute("url", "base/goods!list");
			return ERROR;
		}
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
		UserSession us = Utils.getUserSession();
		/** 如果是商户就设置userId **/
		if(us.getUserLevel() == 2) {
			goodsDTO.setCurrentMerId(us.getMerId());
		}
		LinkedHashMap<String, String> orderby
        = new LinkedHashMap<String, String>();
		if (StringUtils.isNotBlank(this.getOrderProperty())
		    && StringUtils.isNotBlank(this.getOrderDirection())) {
		
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		
		} else { orderby.put("updateDate", "desc"); }
		
		QueryResult<GoodsDTO> dtoResult = 
			goodsInfoService.queryGoodsByQueryInfo((goodsDTO.getPage()-1)*pageNum, pageNum, goodsDTO, orderby);
		
		List<GoodsDTO> goodsList = dtoResult.getResultlist();
		
		List<List<String>> lists = new ArrayList<List<String>>();
		/** 为了性能，批量匹配商品分类 **/
		Set<Integer> typeSet = new HashSet<Integer>();
		for (int i = 0; i < goodsList.size(); i++) {
			GoodsDTO goods = goodsList.get(i);
			List<String> strings = new ArrayList<String>();
			String status = "";
			if(goods.getGoodSaleSta() == 0) {
				status="up";
			} else if(goods.getGoodSaleSta() == 1) {
				status="off";
			} else {
				strings.add(" ");
			}
			strings.add("<input type='checkbox' name='goodsDTO.goodsCheckId' up-off='"+status+"' value='"+goods.getGoodsId()+"'/>");
			strings.add(Utils.getString(goods.getGoodsId()));
			strings.add(Utils.getString(goods.getGoodsItem()));
			strings.add(goods.getGoodsName());
			strings.add(Utils.getString(goods.getTypeId()));
			typeSet.add(goods.getTypeId());
			if(goods.getGoodSaleSta() == 0) {
				strings.add("上架");
			} else if(goods.getGoodSaleSta() == 1) {
				strings.add("下架");
			} else {
				strings.add(" ");
			}
			strings.add(goods.getUserName());
			/** 商户显示的是处理状态 **/
			if(us.getUserLevel() == 2) { 
				strings.add(getGoodsStatus(goods.getUnRuleMaSta()));
			} else {
				strings.add(getPunishMethod(goods.getGoodsSta()));
			}
			String operation = "";
			if (Utils.checkPermission("sy-1705-01")) {
				operation += "<a href='base/goods!checkUI?goodsDTO.goodsId="
						+ goods.getGoodsId()
						+ "' title='查看'>查看</a>&nbsp;";
			}
			if (goods.getGoodsSta() == 0 && us.getUserLevel() ==0 && goods.getGoodsSta() == 0) {
				operation += "<a href='base/goods!showDealGood?goodsDTO.goodsId="
						+ goods.getGoodsId()
						+ "' title='处理'>处理</a>&nbsp;";
			}
			if (us.getUserLevel() == 2 && Utils.checkPermission("sy-1705-03") && goods.getGoodsSta() == 0) {
				operation += "<a href='base/goods!editUI?goodsDTO.goodsId="
						+ goods.getGoodsId()
						+ "' title='编辑'>编辑</a>&nbsp;";
			}
			if (us.getUserLevel() == 2 && Utils.checkPermission("sy-1705-04") && (goods.getGoodsSta() == 0 || goods.getGoodsSta()==1)) {
				operation += "<a href=\"javascript:deleteData('base/goods!delete?goodsDTO.goodsId="
						+ goods.getGoodsId()
						+ "')\" title='删除'>删除</a>&nbsp;";
			}
			if (us.getUserLevel() == 2 && Utils.checkPermission("sy-1705-02")) {
				operation += "<a href='base/goods!copy?goodsDTO.goodsId="
						+ goods.getGoodsId()
						+ "' title='复制'>复制</a>&nbsp;";
			}
			strings.add(operation);
			lists.add(strings);
		}
		
		/** 渲染商品分类名称  **/
		renderCategoryValue(lists,typeSet);
		PageView pageView = new PageView(goodsDTO.getPage(), dtoResult
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}
	
	/**
	 *  checkUI 查看
	 *@Title: checkUI
	 *@Description: checkUI 查看
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String checkUI() throws Exception {
		this.setMethod("checkUI");
		GoodsDTO goodsDTOs = goodsInfoService.findGoodsDetail(goodsDTO.getGoodsId());
		UserSession us = Utils.getUserSession();
		if(null == goodsDTOs || (us.getUserLevel()!=0 && !goodsDTOs.getMerId().equals(us.getMerId()))) {
			this.getRequest().setAttribute("result", "商品信息不存在!");
			this.getRequest().setAttribute("url", "base/goods!list");
			return ERROR;
		}
		goodsDTOs.setGoodsId(null);
		getRequest().setAttribute("goodsSta", OptionsValue.GOODSTA);
		List<PromotionDTO> promotionList = goodsInfoService.findPromotionList(us);
		getRequest().setAttribute("promotionList", promotionList);
		goodsDTO = goodsDTOs;
		return "edit";
	}
	
	/**
	 *  复制商品
	 *@Title: copy
	 *@Description: 复制商品信息
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String copy() throws Exception {
		this.setMethod("addSave");
		GoodsDTO goodsDTOs = goodsInfoService.findGoodsDetail(goodsDTO.getGoodsId());
		UserSession us = Utils.getUserSession();
		if(null == goodsDTOs || (us.getUserLevel()!=0 && !goodsDTOs.getMerId().equals(us.getMerId()))) {
			this.getRequest().setAttribute("result", "商品信息不存在!");
			this.getRequest().setAttribute("url", "base/goods!list");
			return ERROR;
		}
		goodsDTOs.setGoodsId(null);
		goodsDTOs.setGoodsName("");
		/** 如果分类被禁用 **/
		GoodsFamilyDTO goodsFamilyDTO = goodsFamilyService.find(goodsDTOs.getTypeId());
		/** 分类被禁用和删除时，界面上首先动态属性要空白、规格要空白、商品分类树形默认没有选中任何值 **/
		if(goodsFamilyDTO.getStatus()!=null && (goodsFamilyDTO.getStatus() == 0 || goodsFamilyDTO.getStatus() == 9)) {
			goodsDTOs.setTypeId(null);
		}
		List<PromotionDTO> promotionList = goodsInfoService.findPromotionList(us);
		getRequest().setAttribute("promotionList", promotionList);
		goodsDTO = goodsDTOs;
		return "edit";
	}
	
	/**
	 *  根据id来查询处罚方式
	 *@Title: getPunishMethod
	 *@Description: 根据id来查询处罚方式
	 *@return:String 处罚方式文本
	 *@author: Jacky
	 *@Thorws:
	 */
	private String getPunishMethod(Integer id) {
		List<OptionsInteger> list = OptionsValue.GOODSTA;
		for(OptionsInteger oi : list) {
			if(null != id && oi.getKey() == id.intValue()) return oi.getValue();
		}
		return "";
	}
	
	/**
	 *  根据id来查询处理状态
	 *@Title: getGoodsStatus
	 *@Description: 根据id来查询处理状态
	 *@return:String 处理状态文案
	 *@author: Jacky
	 *@Thorws:
	 */
	private String getGoodsStatus(Integer id) {
		if(null == id) return "";
		if(id == 0) {
			return "";
		} else if(id == 1) {
			return "已处理";
		} else if(id == 2) {
			return "未处理";
		} else {
			return "";
		}
	}
	
	/**
	 * 渲染商品分类值
	 *@Title: renderCategoryValue
	 *@Description: 渲染商品分类值
	 *@param lists ajax内容容器
	 *@param typeSet 商品分类集合
	 *@author: Jacky
	 */
	private void renderCategoryValue(List<List<String>> lists,Set<Integer> typeSet) {
		Map<String, String> familyNameMap = goodsFamilyService.batchQueryFamilyCategoryMap(typeSet);
		for(List<String> resultList:lists) {
			String key = resultList.get(4);
			/** 在添加list时候，typeId被添加到第5个位置 **/
			String typeName = familyNameMap.get(key);
			if(StringUtils.isNotBlank(typeName)) {
				resultList.set(4, typeName);
			} else {
				/** 如果商品类型不存在就滞空  **/
				resultList.set(4, "");
			}
		}
	}
	
	/**
	 * 添加界面
	 *@Title: addUI
	 *@Description: 添加界面
	 *@return:String
	 *@author: Jacky
	 */
	public String addUI() throws Exception {
		this.setMethod("addSave");
		UserSession us = Utils.getUserSession();
		List<PromotionDTO> promotionList = goodsInfoService.findPromotionList(us);
		getRequest().setAttribute("promotionList", promotionList);
		return INPUT;
	}
	
	/**
	 * 编辑界面
	 *@Title: editUI
	 *@Description: 编辑界面
	 *@return:String
	 *@author: Jacky
	 */
	public String editUI() throws Exception {
		this.setMethod("editSave");
		getRequest().setAttribute("goodsSta", OptionsValue.GOODSTA);
		GoodsDTO goodsDTOs = goodsInfoService.findGoodsDetail(goodsDTO.getGoodsId());
		UserSession us = Utils.getUserSession();
		if(null == goodsDTOs || (us.getUserLevel()!=0 && !goodsDTOs.getMerId().equals(us.getMerId()))) {
			this.getRequest().setAttribute("result", "商品信息不存在!");
			this.getRequest().setAttribute("url", "base/goods!list");
			return ERROR;
		}
		/** 如果分类被禁用 **/
		GoodsFamilyDTO goodsFamilyDTO = goodsFamilyService.find(goodsDTOs.getTypeId());
		/** 分类被禁用和删除时，界面上首先动态属性要空白、规格要空白、商品分类树形默认没有选中任何值 **/
		if(goodsFamilyDTO.getStatus()!=null && (goodsFamilyDTO.getStatus() == 0 || goodsFamilyDTO.getStatus() == 9)) {
			goodsDTOs.setTypeId(null);
		}
		List<PromotionDTO> promotionList = goodsInfoService.findPromotionList(us);
		getRequest().setAttribute("promotionList", promotionList);
		goodsDTO = goodsDTOs;
		return "edit";
	}
	
	/**
	* 商品信息 编辑页面
	 *@Title:editUI
	 *@Description: 进入编辑页面
	 *@param:@return
	 *@param:@throws Exception
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String addSave() throws Exception {
		UserSession us = Utils.getUserSession();
		goodsDTO.setCurrentMerId(us.getMerId());
		goodsDTO.setUserName(us.getUserName());
		getDynamicAttr(goodsDTO);
		String error = goodsInfoService.saveGoodInfo(goodsDTO);
		if(StringUtils.isBlank(error)) {
			this.getRequest().setAttribute("result", "商品信息保存成功!");
			this.getRequest().setAttribute("url", "base/goods!list");
		} else {
			this.getRequest().setAttribute("result", error);
			this.getRequest().setAttribute("url", "base/goods!list");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 编辑保存
	 *@Title:editUI
	 *@Description: 编辑保存
	 *@param:@throws Exception
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String editSave() throws Exception {
		UserSession us = Utils.getUserSession();
		goodsDTO.setCurrentMerId(us.getMerId());
		goodsDTO.setUserName(us.getUserName());
		/** 拼接动态属性 **/
		getDynamicAttr(goodsDTO);
		String error = goodsInfoService.updateGoodInfo(goodsDTO);
		if(StringUtils.isBlank(error)) {
			this.getRequest().setAttribute("result", "商品信息保存成功!");
			this.getRequest().setAttribute("url", "base/goods!list");
		} else {
			this.getRequest().setAttribute("result", error);
			this.getRequest().setAttribute("url", "base/goods!list");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 获取动态属性
	 *@Title: getDynamicAttr
	 *@Description: 获取动态属性
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	@SuppressWarnings("unchecked")
	private void getDynamicAttr(GoodsDTO goodsDTO) {
		/** 获取动态的列数目 **/
		String count = this.getRequest().getParameter("dynamicCount");
		List<String[]> attrsList = new ArrayList<String[]>();
		if(StringUtils.isNotBlank(count) && NumberUtils.isNumber(count)) {
			int countIn = Integer.valueOf(count);
			/** 获取界面动态属性的name **/
			Enumeration<String> paramNames = this.getRequest().getParameterNames();
			Map<Integer,String> map = new HashMap<Integer,String>();
			while(paramNames.hasMoreElements()) {
				String paramName = paramNames.nextElement();
				String p[] = paramName.split("_");
				if(paramName.indexOf("dynamicAttr_")!=-1 && p.length == 4) {
					map.put(Integer.valueOf(p[2]), paramName);
				} 
			}
			/** 根据动态属性数目来获得属性对应的值 并且拼接成 key_value的字符串，方便后面使用**/
			for(int i=0;i<countIn;i++) {
				if(map.get(i) != null) {
					String name = map.get(i);
					String[] dynamicAtts = this.getRequest().getParameterValues(name);
					if(null != dynamicAtts) {
						String names[] = name.split("_");
						for(int j=0;j<dynamicAtts.length;j++) {
							/** 拼装成 key_value**/
							dynamicAtts[j] = names[3]+"_"+dynamicAtts[j];
						}
					}
					attrsList.add(dynamicAtts);
				}
			}
		}
		/** 商品价格，库存等等 **/
		String[] goodsPrics = this.getRequest().getParameterValues("dynamicGoodsPrice");
		String[] marketGoodPrice = this.getRequest().getParameterValues("dynamicMarketGoodsPrice");
		String[] dynamicStockNo = this.getRequest().getParameterValues("dynamicStockNo");
		String[] dynamicPicUrl =  this.getRequest().getParameterValues("dynamicPicUrl");
		/** 合法性校验 **/
		if(null != goodsPrics && null != marketGoodPrice && null != dynamicStockNo && null != dynamicPicUrl && goodsPrics.length == marketGoodPrice.length && marketGoodPrice.length == dynamicStockNo.length && dynamicPicUrl.length == dynamicStockNo.length) {
			List<DynamicAttrDTO> dynamicAtt = new ArrayList<DynamicAttrDTO>();
			for(int i=0;i<goodsPrics.length;i++) {
				DynamicAttrDTO dat = new DynamicAttrDTO();
				if(attrsList.size() > 0) {
					/** 存储attrId_value值**/
					for(int j=0; j < attrsList.size(); j++ ) {
						String[] attArray = attrsList.get(j);
						String attNameValue = attArray[i];
						String[] nameValue = attNameValue.split("_");
						if(nameValue.length  == 2) {
							KeyValue kv = new KeyValue();
							kv.setKey(Integer.parseInt(nameValue[0]));
							kv.setValue(nameValue[1]);
							dat.getAttrNameValueList().add(kv);
						}
					}
				}
				dat.setGoodsPrice(new BigDecimal(goodsPrics[i]));
				if(StringUtils.isNotBlank(dynamicPicUrl[i])) {
					dat.setImgUrl(dynamicPicUrl[i]);
				} else {
					dat.setImgUrl(null);
				}
				dat.setMarketGoodsPrice(new BigDecimal(marketGoodPrice[i]));
				if(!NumberUtils.isNumber(dynamicStockNo[i])) throw new RuntimeException("库存数必须为数字!");
				dat.setStockNo(dynamicStockNo[i]);
				dynamicAtt.add(dat);
			}
			goodsDTO.setDynamicAttr(dynamicAtt);
		}
		List<FormatInfoDTO> formatInfoList = new ArrayList<FormatInfoDTO>();
		Enumeration<String> paramNames = this.getRequest().getParameterNames();
		while(paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			if(pattern.matcher(paramName).matches()) {
				String value = this.getRequest().getParameter(paramName);
				String names[] = paramName.split("_");
				/** 如果不等于2，就不符合逻辑 ，或者有人修改了界面表单**/
				if(names.length != 2) throw new RuntimeException("系统错误!");
				FormatInfoDTO formatInfoDto = new FormatInfoDTO();
				formatInfoDto.setFormatId(Long.valueOf(names[1]));
				formatInfoDto.setFormatValue(value);
				formatInfoList.add(formatInfoDto);
			} 
		}
		goodsDTO.setFormatDynamic(formatInfoList);
	}
	
	public String delete() throws Exception {
		UserSession us = Utils.getUserSession();
		String error = goodsInfoService.deleteGoodInfoById(us.getUserName(), goodsDTO.getGoodsId());
		if(StringUtils.isBlank(error)) {
			this.ajaxResult = "ajaxsuccess";
		} else {
			this.ajaxResult = "ajaxfailure";
            this.msgResult = error;
		}
		return ajaxResult;
	}
	
	/**
	 * 异步上传图片
	 *@Title: ajaxUploadPic
	 *@Description: 异步上传图片
	 *@param:@return
	 *@param:@throws Exception
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String ajaxUploadPic() throws Exception {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String url = goodsInfoService.uploadPic(goodsDTO);
		if(StringUtils.isNotBlank(url)) {
			resultMap.put("success", true);
			resultMap.put("url", url);
		} else {
			resultMap.put("success", false);
		}
		return Utils.printInfoWithoutType(resultMap);
	}
	
	/**
	 * 异步获取分类关联属性
	 *@Title: ajaxQueryAttr
	 *@Description: 异步获取分类属性
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String ajaxQueryAttr() throws Exception {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<TypeAttrDTO> typeAttrList = goodsInfoService.findAttrByTypeId(goodsDTO.getTypeId());
		resultMap.put("attList", typeAttrList);
		return Utils.printInfoWithoutType(resultMap);
	}
	
	/**
	 * 异步获取规格分组
	 *@Title: ajaxQueryFormatGroup
	 *@Description: 异步获取规格分组
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String ajaxQueryFormatGroup() throws Exception {
		List<GoodsFormatNameDTO> formatGroupList = goodsInfoService.findGoodsFormatGroup(goodsDTO.getTypeId());
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("formatGroup", formatGroupList);
		return Utils.printInfoWithoutType(resultMap);
	}
	
	/**
	 * 异步获取活动列表
	 *@Title: ajaxQueryPromotionByID
	 *@Description: 异步获取活动列表
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String ajaxQueryPromotionByID() throws Exception {
		MerPromotionDTO promotionDTO = goodsInfoService.findMerPromotionDTOByProId(goodsDTO.getProId());
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("promotion", promotionDTO);
		return Utils.printInfoWithoutType(resultMap);
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
		List<GoodsTypeDTO> goodTypeList = goodsFamilyService.getFamilyCategoryList();
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

	public void setGoodsDTO(GoodsDTO goodsDTO) {
		this.goodsDTO = goodsDTO;
	}

	public GoodsDTO getGoodsDTO() {
		return goodsDTO;
	}

	public PlatHandleRecordDTO getPlatHandleDTO() {
		return platHandleDTO;
	}

	public void setPlatHandleDTO(PlatHandleRecordDTO platHandleDTO) {
		this.platHandleDTO = platHandleDTO;
	}

	public UnruleTypeDTO getUnruleTypeDTO() {
		return unruleTypeDTO;
	}

	public void setUnruleTypeDTO(UnruleTypeDTO unruleTypeDTO) {
		this.unruleTypeDTO = unruleTypeDTO;
	}
	
}
