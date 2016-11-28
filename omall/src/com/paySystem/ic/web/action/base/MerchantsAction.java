package com.paySystem.ic.web.action.base;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.base.SingleRelation;
import com.paySystem.ic.service.base.BailService;
import com.paySystem.ic.service.base.MerSinRelationService;
import com.paySystem.ic.service.base.MerchantsService;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.base.SingleRelationService;
import com.paySystem.ic.service.base.StoreInfoService;
import com.paySystem.ic.service.card.CardBINService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.system.AreaService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.AreaDicDTO;
import com.paySystem.ic.web.dto.base.MerchantsDTO;
import com.paySystem.ic.web.dto.base.StoreInfoDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsInteger;
import com.paySystem.ic.web.ui.OptionsString;

/**
 * @ClassName:MerchantsAction
 * @Description:商户信息管理Action类
 * @date: 2013-11-02上午10:52:32
 * @author: 谢洪飞
 * @version: V1.0
 */
@Controller("/base/merchants")
@Scope("prototype")
public class MerchantsAction extends BaseAction {
    private static final long serialVersionUID = -3051940652987671139L;

    @Resource
    MerchantsService merchantsService;

    @Resource
    OrgansService organsService;

    @Resource
    FunctionsService functionsService;

    @Resource
    AreaService areaService;

    @Resource
    CardBINService cardBinService;

    @Resource
    SingleRelationService sinService;

    @Resource
    MerSinRelationService merSinService;

    @Resource
    BailService bailService;

    /** 注入Service */
    @Resource
    private StoreInfoService storeInfoService;

    /*
     * @Resource OilTypeService oilTypeService;
     */

    private MerchantsDTO merchantsDTO = new MerchantsDTO();

    /** 实例化店铺基本设置数据传输对象 */
    private StoreInfoDTO storeInfoDTO = new StoreInfoDTO();

    public MerchantsDTO getMerchantsDTO() {
        return merchantsDTO;
    }

    public MerchantsService getMerchantsService() {
        return merchantsService;
    }

    public void setMerchantsService(MerchantsService merchantsService) {
        this.merchantsService = merchantsService;
    }

    public OrgansService getOrgansService() {
        return organsService;
    }

    public void setOrgansService(OrgansService organsService) {
        this.organsService = organsService;
    }

    public FunctionsService getFunctionsService() {
        return functionsService;
    }

    public void setFunctionsService(FunctionsService functionsService) {
        this.functionsService = functionsService;
    }

    public AreaService getAreaService() {
        return areaService;
    }

    public void setAreaService(AreaService areaService) {
        this.areaService = areaService;
    }

    public CardBINService getCardBinService() {
        return cardBinService;
    }

    public void setCardBinService(CardBINService cardBinService) {
        this.cardBinService = cardBinService;
    }

    public SingleRelationService getSinService() {
        return sinService;
    }

    public void setSinService(SingleRelationService sinService) {
        this.sinService = sinService;
    }

    public MerSinRelationService getMerSinService() {
        return merSinService;
    }

    public void setMerSinService(MerSinRelationService merSinService) {
        this.merSinService = merSinService;
    }

    public BailService getBailService() {
        return bailService;
    }

    public void setBailService(BailService bailService) {
        this.bailService = bailService;
    }

    public StoreInfoService getStoreInfoService() {
        return storeInfoService;
    }

    public void setStoreInfoService(StoreInfoService storeInfoService) {
        this.storeInfoService = storeInfoService;
    }

    public StoreInfoDTO getStoreInfoDTO() {
        return storeInfoDTO;
    }

    public void setStoreInfoDTO(StoreInfoDTO storeInfoDTO) {
        this.storeInfoDTO = storeInfoDTO;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setMerchantsDTO(MerchantsDTO merchantsDTO) {
        this.merchantsDTO = merchantsDTO;
    }

    /**
     * 列表页面
     * 
     * @version 2011-9-8 下午08:50:23
     * @return
     * @throws Exception
     */
    public String list() throws Exception {

        UserSession us = Utils.getUserSession();
        this.getRequest().setAttribute("condValues", OptionsValue.MERCHANT_STATUS);// 状态

        if (us.getUserLevel() == 2) {
            merchantsDTO.setMerId(us.getMerId());
            // 加载店铺数据
            loadStoreInfo();
            return this.editUI();
        }
        merchantsDTO.setStatus(1);
        return "list";
    }

    /**
     * 异步获取列表数据
     * 
     * @version 2011-9-8 下午08:51:04
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String jsonPageList() throws Exception {
        merchantsDTO.setHelpSign(0);
        /* this.getSession().setAttribute(Globals.QUERY_PARM, merchantsDTO); */
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        if (StringUtils.isNotBlank(this.getOrderProperty()) && StringUtils.isNotBlank(this.getOrderDirection())) {
            orderby.put(this.getOrderProperty(), this.getOrderDirection());
        }
        else {
            orderby.put("updateTime", "desc");
        }

        QueryResult queryResult = merchantsService.queryMerByCond((merchantsDTO.getPage() - 1) * pageNum, pageNum,
                merchantsDTO, orderby);
        List<Merchants> list = queryResult.getResultlist();

        List<List<String>> lists = new ArrayList<List<String>>();

        for (int i = 0; i < list.size(); i++) {
            Merchants merchants = list.get(i);
            List<String> strings = new ArrayList<String>();
            strings.add(String.valueOf(i + 1));
            strings.add(Utils.getString(merchants.getMerId()));
            strings.add(Utils.getString(merchants.getMerName()));
            if (merchants.getOrgans() != null) {
                strings.add(merchants.getOrgans().getName());
            }
            else {
                strings.add("");
            }
            strings.add(Utils.getOptionsIntegerName(OptionsValue.MERCHANT_STATUS, merchants.getStatus()));
            strings.add(DateTimeTool.dateFormat("", merchants.getUpdateTime()));
            String operation = "";
            operation += "<a href=base/merchants!checkUI?merchantsDTO.merId=" + merchants.getMerId() + " title='查看'>"
                    + Globals.IMG_VIEW + "</a>&nbsp;";
            if (merchants.getStatus() != 9) {
                if (Utils.checkPermission("sy-1201-03")) {
                    operation += "<a href=base/merchants!editUI?merchantsDTO.merId=" + merchants.getMerId()
                            + " title='修改'>" + Globals.IMG_EDIT + "</a>&nbsp;";
                }
                if (Utils.checkPermission("sy-1201-04")) {
                    operation += "<a href=javascript:deleteData('base/merchants!delete','" + merchants.getMerId()
                            + "') title='删除'>" + Globals.IMG_DELETE + "</a>&nbsp;";
                }
            }
            else {
                if (Utils.checkPermission("sy-1201-01")) {
                    operation += "<a href=base/merchants!checkUI?merchantsDTO.merId=" + merchants.getMerId()
                            + " title='查看'>" + Globals.IMG_VIEW + "</a>&nbsp;";
                }
            }

            strings.add(operation);
            lists.add(strings);
        }

        PageView pageView = new PageView(merchantsDTO.getPage(), queryResult.getTotalrecord());
        ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
        return Utils.printInfo(listInfoDTO);
    }

    /**
     * 商户帮助--查询商户信息
     * 
     * @return string
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String mercJsonPageList() throws Exception {
        merchantsDTO.setHelpSign(1);
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        if (StringUtils.isNotBlank(this.getOrderProperty()) && StringUtils.isNotBlank(this.getOrderDirection())) {
            orderby.put(this.getOrderProperty(), this.getOrderDirection());
        }
        else {
            orderby.put("updateTime", "desc");
        }
        QueryResult queryResult = merchantsService.queryMerByCond((merchantsDTO.getPage() - 1) * pageNum, pageNum,
                merchantsDTO, orderby);

        List<Merchants> list = queryResult.getResultlist();
        List<List<String>> lists = new ArrayList<List<String>>();

        for (int i = 0; i < list.size(); i++) {
            Merchants merchants = list.get(i);
            List<String> strings = new ArrayList<String>();
            strings.add(String.valueOf(i + 1));
            strings.add(Utils.getString(merchants.getMerId()));
            strings.add(Utils.getString(merchants.getMerName()));
            if (merchants.getOrgans() != null) {
                strings.add(merchants.getOrgans().getName());
            }
            else {
                strings.add("");
            }
            strings.add(Utils.getOptionsIntegerName(OptionsValue.MERCHANT_STATUS, merchants.getStatus()));
            strings.add(DateTimeTool.dateFormat("", merchants.getUpdateTime()));
            String operation = "<a href=javascript:secMerc('" + merchants.getMerId() + "','"
                    + merchants.getOrgans().getOrganId() + "','" + merchants.getMerName() + "','"
                    + merchants.getOrgans().getName() + "') title='选择'>[选择]</a>&nbsp;";
            strings.add(operation);
            lists.add(strings);
        }

        PageView pageView = new PageView(merchantsDTO.getPage(), queryResult.getTotalrecord());
        ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView, "mercQuery"));
        return Utils.printInfo(listInfoDTO);
    }

    /**
     * 添加页面
     * 
     * @return
     */
    public String addUI() {
        this.setMethod("addSave");
        UserSession us = Utils.getUserSession();
        merchantsDTO.setMerId(merchantsService.getMerId());
        // merchantsDTO.setOrganId(us.getOrganId());
        merchantsDTO.setOrganName(us.getOrganName());
        merchantsDTO.setStatus(1);
        merchantsDTO.setCosmSign(0);
        merchantsDTO.setCpscSign(0);
        merchantsDTO.setCpsmSign(0);
        merchantsDTO.setMsscSign(0);
        merchantsDTO.setIsSalePointStr(Utils.getSelectHtml(OptionsValue.VISIBLE_STATUS, "merchantsDTO.isSalePoints",
                "isSalePoints", false));
        this.getRequest().setAttribute("organsValues", organsService.getOption());// 机构
        this.getRequest().setAttribute("areaValues", OptionsValue.AREA_VALUE);
        this.getRequest().setAttribute("statusValues", OptionsValue.MERCHANT_STATUS);// 状态
        this.getRequest().setAttribute("visibleValues", OptionsValue.VISIBLE_STATUS);// 是否
        this.getRequest().setAttribute("settlementValues", OptionsValue.SETTLEMENT_STATUS);// 结算方式
        this.getRequest().setAttribute("areaValues", OptionsValue.AREA_VALUE);
        this.getRequest().setAttribute("settelCountWayValues", OptionsValue.SETTLE_COUNT_WAY);
        this.getRequest().setAttribute("convIsOrNot", OptionsValue.VISIBLE_STATUS_CONV);
        List<AreaDicDTO> areaDicDTOs = storeInfoService.findProvinces();
        List<OptionsInteger> areaDicDTOsValue = new ArrayList<OptionsInteger>();
        /** 省 */
        for (int i = 0; i < areaDicDTOs.size(); i++) {
            areaDicDTOsValue.add(new OptionsInteger(areaDicDTOs.get(i).getFcode(), areaDicDTOs.get(i).getFname()));
        }
        // 合作方式
        this.getRequest().setAttribute("coopWay", OptionsValue.COOP_WAY);
        this.getRequest().setAttribute("areaDicDTOsValue", areaDicDTOsValue);
        /** 结算方式 */
        this.getRequest().setAttribute("settWayValue", OptionsValue.SETTWAY);
        /** 经营类型 */
        this.getRequest().setAttribute("businTypeValue", OptionsValue.BUSINTYPE);
        /** 主要货源 */
        this.getRequest().setAttribute("mainProductValue", OptionsValue.MAINPRODUCT);
        /**
         * 是否有实体店 0：有 1：无
         */
        this.getRequest().setAttribute("isStoreOrNotValue", OptionsValue.VISIBLE_STATUS);
        /**
         * 是否有工厂或仓库 0：有 1：无
         */
        this.getRequest().setAttribute("isFactOrNotValue", OptionsValue.VISIBLE_STATUS);

        // 获取所有油品信息
        /* List<OilType> oilTypes = oilTypeService.getAllOilTypes(); */
        List<MerchantsDTO> saleOils = new ArrayList<MerchantsDTO>();
        /*
         * for(OilType oilType : oilTypes){ MerchantsDTO oilTypeDto = new
         * MerchantsDTO(); oilTypeDto.setOilType(oilType.getOilTypeId());
         * oilTypeDto.setOilTypeName(oilType.getOilTypeName());
         * saleOils.add(oilTypeDto); }
         */
        this.getRequest().setAttribute("saleOils", saleOils);

        return INPUT;
    }

    /**
     * 添加操作
     * 
     * @version 2011-9-13 下午05:30:30
     * @return
     */
    public String addSave() throws Exception {

        if (merchantsService.validate(merchantsDTO.getMerId())) {
            this.getRequest().setAttribute("result", this.getText("exist.no.notice"));
            this.getRequest().setAttribute("url", "base/merchants!list");
            return ERROR;
        }

        if (merchantsService.validateName(merchantsDTO.getMerName(), merchantsDTO.getMerId())) {
            this.getRequest().setAttribute("result", "商户名称已存在");
            this.getRequest().setAttribute("url", "base/merchants!list");
            return ERROR;
        }

        Merchants merchants = merchantsService.saveMerchant(merchantsDTO);

        functionsService.saveFunction("商户管理", 1, "增加商户：" + merchants.getMerId());

        this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
        this.getRequest().setAttribute("url", "base/merchants!list");
        return SUCCESS;

    }

    /**
     * 进入修改页面
     * 
     * @version 2011-9-10 下午06:20:30
     * @return
     */
    public String editUI() throws Exception {
        loadStoreInfo();
        this.setMethod("editSave");
        MerchantsDTO merDto = new MerchantsDTO();

        // 1.获取商户业务参数
        merDto = merchantsService.findMerFromBussParam(merchantsDTO.getMerId());

        // 2.获取商户信息
        Merchants merchants = merchantsService.find(merchantsDTO.getMerId());

        // 3.获取保证金信息
        if (merchants != null) {
            merchantsDTO = getMerDto(merchants, merDto);
            String organId = "";
            if (merchants.getOrgans().getParentId().equals("0")) {
                organId = merchants.getOrgans().getOrganId();
            }
            else {
                organId = merchants.getOrgans().getParentId();
            }

            merchantsDTO.setCosmSign(merDto.getCosmSign());
            merchantsDTO.setCpscSign(merDto.getCpscSign());
            merchantsDTO.setCpsmSign(merDto.getCpsmSign());
            merchantsDTO.setMsscSign(merDto.getMsscSign());
            if (merDto.getExitsCount() == null || "".equals(merDto.getExitsCount())) {
                merchantsDTO.setExitsCount(0);
            }
            else {
                merchantsDTO.setExitsCount(merDto.getExitsCount());
            }

            if (merDto.getSalingCount() == null || "".equals(merDto.getSalingCount())) {
                merchantsDTO.setSalingCount(0);
            }
            else {
                merchantsDTO.setSalingCount(merDto.getSalingCount());
            }

            merchantsDTO.setGnoPrefix(merDto.getGnoPrefix());

            this.getRequest().setAttribute("organsValues", organsService.getOption());// 机构
            this.getRequest().setAttribute("statusValues", OptionsValue.MERCHANT_STATUS);// 状态
            this.getRequest().setAttribute("visibleValues", OptionsValue.VISIBLE_STATUS);// 是否
            this.getRequest().setAttribute("settlementValues", OptionsValue.SETTLEMENT_STATUS);// 结算方式
            this.getRequest().setAttribute("areaValues", OptionsValue.AREA_VALUE);
            this.getRequest().setAttribute("settelCountWayValues", OptionsValue.SETTLE_COUNT_WAY);
            this.getRequest().setAttribute("convIsOrNot", OptionsValue.VISIBLE_STATUS_CONV);
            // 合作方式
            this.getRequest().setAttribute("coopWay", OptionsValue.COOP_WAY);
            UserSession us = Utils.getUserSession();
            /*switch (us.getUserLevel()) {
            case 0:
            	this.getRequest().setAttribute("preMerValues",
            			merchantsService.getOption());
            	break;
            case 1:
            	this.getRequest().setAttribute("preMerValues",
            			merchantsService.getOptionByOrganId(us.getOrganId()));
            	break;
            }*/

            List<MerchantsDTO> saleOils = new ArrayList<MerchantsDTO>();
            this.getRequest().setAttribute("saleOils", saleOils);

            return INPUT;
        }
        return ERROR;
    }

    public void loadStoreInfo() {
        if (Utils.getUserSession().getUserLevel() != 2) {
            storeInfoDTO = storeInfoService.findById(merchantsDTO.getMerId());
        }
        else {
            storeInfoDTO = storeInfoService.findById(Utils.getUserSession().getMerId());
        }
        if (storeInfoDTO.getImageFileFileName() != null) {
            storeInfoDTO.setImageFileFileName(storeInfoDTO.getImageFileFileName().replace("\\", "/"));
        }
        /** 获取数据库表中的所有的省，然后使用request对象传到页面中 */
        List<AreaDicDTO> areaDicDTOs = storeInfoService.findProvinces();
        List<OptionsInteger> areaDicDTOsValue = new ArrayList<OptionsInteger>();
        /** 省 */
        for (int i = 0; i < areaDicDTOs.size(); i++) {
            areaDicDTOsValue.add(new OptionsInteger(areaDicDTOs.get(i).getFcode(), areaDicDTOs.get(i).getFname()));
        }
        this.getRequest().setAttribute("areaDicDTOsValue", areaDicDTOsValue);
        /** 结算方式 */
        this.getRequest().setAttribute("settWayValue", OptionsValue.SETTWAY);
        /** 经营类型 */
        this.getRequest().setAttribute("businTypeValue", OptionsValue.BUSINTYPE);
        /** 主要货源 */
        this.getRequest().setAttribute("mainProductValue", OptionsValue.MAINPRODUCT);
        /**
         * 是否有实体店 0：有 1：无
         */
        this.getRequest().setAttribute("isStoreOrNotValue", OptionsValue.VISIBLE_STATUS);
        /**
         * 是否有工厂或仓库 0：有 1：无
         */
        this.getRequest().setAttribute("isFactOrNotValue", OptionsValue.VISIBLE_STATUS);
        /**是否支持银行卡**/
        this.getRequest().setAttribute("isBankCard", OptionsValue.ISBANKCARD);
        /** 如果数据库中没有记录，为添加页面，如果有，为修改页面 */
        if (storeInfoDTO.getStoreId() == null) {
            /** 设置默认值 */
            storeInfoDTO.setMainProduct('0');
            storeInfoDTO.setIsFactOrNot('0');
            storeInfoDTO.setIsStoreOrNot('0');
            storeInfoDTO.setBusinType('0');
            this.setMethod("addSave");
        }
        else {
            this.setMethod("editSave");
        }
    }

    /**
     * 修改模块操作
     * 
     * @version 2013-11-10 下午03:59:08
     * @return
     */
    public String editSave() {

        if (merchantsService.validateName(merchantsDTO.getMerName(), merchantsDTO.getMerId())) {
            this.getRequest().setAttribute("result", "商户名称已存在");
            this.getRequest().setAttribute("url", "base/merchants!list");
            return ERROR;
        }
        try {
            ReturnDTO dto = merchantsService.updateMerchant(merchantsDTO);
            if (dto.getFlag()) {
                functionsService.saveFunction("商户管理", 2, "修改商户：" + merchantsDTO.getMerId());
                this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
                this.getRequest().setAttribute("url", "base/merchants!list");
                return SUCCESS;
            }
            else {
                this.getRequest().setAttribute("result", dto.getMsg());
                this.getRequest().setAttribute("url", "base/merchants!list");
                return ERROR;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    /**
     *@Title:checkUI
     *@Description:查看禁用信息
     *@param:@return
     *@return:String
     *@author:谢
     *@thorws:
     */
    public String checkUI() {
        this.setMethod("checkUI");
        MerchantsDTO merDto = new MerchantsDTO();
        merDto = merchantsService.findMerFromBussParam(merchantsDTO.getMerId());
        Merchants merchants = merchantsService.find(merchantsDTO.getMerId());
        if (merchants != null) {
            merchantsDTO = getMerDto(merchants, merDto);
            String organId = "";
            if (merchants.getOrgans().getParentId().equals("0")) {
                organId = merchants.getOrgans().getOrganId();
            }
            else {
                organId = merchants.getOrgans().getParentId();
            }
            this.getRequest().setAttribute("organsValues", organsService.getOption());// 机构
            this.getRequest().setAttribute("statusValues", OptionsValue.MERCHANT_STATUS);// 状态
            this.getRequest().setAttribute("visibleValues", OptionsValue.VISIBLE_STATUS);// 是否
            this.getRequest().setAttribute("settlementValues", OptionsValue.SETTLEMENT_STATUS);// 结算方式
            this.getRequest().setAttribute("areaValues", OptionsValue.AREA_VALUE);
            this.getRequest().setAttribute("settelCountWayValues", OptionsValue.SETTLE_COUNT_WAY);
            this.getRequest().setAttribute("convIsOrNot", OptionsValue.VISIBLE_STATUS_CONV);
            UserSession us = Utils.getUserSession();
            // 合作方式
            this.getRequest().setAttribute("coopWay", OptionsValue.COOP_WAY);
            /*switch (us.getUserLevel()) {
            case 0:
                this.getRequest().setAttribute("preMerValues", merchantsService.getOption());
                break;
            case 1:
                this.getRequest().setAttribute("preMerValues", merchantsService.getOptionByOrganId(us.getOrganId()));
                break;
            }*/
            if (merDto.getExitsCount() == null || "".equals(merDto.getExitsCount())) {
                merchantsDTO.setExitsCount(0);
            }
            else {
                merchantsDTO.setExitsCount(merDto.getExitsCount());
            }

            if (merDto.getSalingCount() == null || "".equals(merDto.getSalingCount())) {
                merchantsDTO.setSalingCount(0);
            }
            else {
                merchantsDTO.setSalingCount(merDto.getSalingCount());
            }
            /** 添加收单关系信息 */
            SingleRelation singleRelation = sinService.findByMerIdBinId(merchants.getMerId());
            if (singleRelation != null) {
                merchantsDTO.setSettlementWay(singleRelation.getMehodOfSett());// 商户结算方式
                merchantsDTO.setSingleFee(singleRelation.getAsinTranRateFee());// 设置单笔手续费
                merchantsDTO.setRakeRate(singleRelation.getRateFee()); // 手续费率
                merchantsDTO.setFeeLimit(singleRelation.getFeeTopLimit()); // 手续费上限
                merchantsDTO.setPlatformRate(singleRelation.getServPlatformRatio());// 服务平台比率
                merchantsDTO.setAcquirerRate(singleRelation.getAcquirerRate()); // 收单机构分成比率
                merchantsDTO.setOrganRate(singleRelation.getOrganRate()); // 发卡机构分成比率
                merchantsDTO.setSettelCountWay(singleRelation.getCountSettType());
            }

            /** 允许消费的卡BIN信息 */
            List<MerchantsDTO> cardBinDtos = new ArrayList<MerchantsDTO>();
            List<OptionsString> list = cardBinService.getOptionByOrganId(organId);
            /*
             * for (OptionsString optionsString : list) { MerchantsDTO
             * cardBinDto = new MerchantsDTO();
             * cardBinDto.setCardBin(optionsString.getKey());
             * cardBinDto.setCardName(optionsString.getValue());
             * cardBinDto.setIsSalePoint(0); List<MerSinRelation> merSinRelList
             * = merSinService .queryMerBinByMerId(merchantsDTO.getMerId(),
             * optionsString.getKey()); MerSinRelation merSinRelation = null; if
             * (merSinRelList.size() > 0) { merSinRelation =
             * merSinRelList.get(0); cardBinDto
             * .setIsSalePoint(merSinRelation.getGivePointSign());
             * cardBinDto.setCardBinStatus("1"); } cardBinDtos.add(cardBinDto);
             * }
             */

            this.getRequest().setAttribute("cardBinDtos", cardBinDtos);

            return INPUT;
        }
        return ERROR;
    }

    public String ajaxCardBins() {
        List<OptionsString> list = null;
        Organs organs = organsService.find(merchantsDTO.getOrganId());
        if (organs.getParentId().equals("0")) {
            list = cardBinService.getOptionByOrganId(organs.getOrganId());
        }
        else {
            list = cardBinService.getOptionByOrganId(organs.getParentId());
        }
        return Utils.printInfo(list);
    }

    /**
     * 删除操作
     * 
     * @version 2011-9-10 下午04:23:43
     */
    public String delete() {

        try {
            Merchants merchants = merchantsService.find(this.getId());
            merchants.setStatus(9);
            merchantsService.update(merchants);
            // merchantsService.delete(this.getId());
            functionsService.saveFunction("商户管理", 3, "删除商户：" + merchants.getMerId());
            this.ajaxResult = "ajaxsuccess";
        }
        catch (Exception e) {
            e.printStackTrace();
            this.ajaxResult = "ajaxfailure";
            this.msgResult = e.getMessage();
        }
        return this.ajaxResult;
    }

    /**
     *@Title:preMerHelpList
     *@Description:经销商帮助选择
     *@param:@return
     *@return:String
     *@author:
     * @throws Exception
     *@thorws:
     */
    public String preMerchantsHelpList() throws Exception {

        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        if (StringUtils.isNotBlank(this.getOrderProperty()) && StringUtils.isNotBlank(this.getOrderDirection())) {
            orderby.put(this.getOrderProperty(), this.getOrderDirection());
        }
        else {
            orderby.put("updateTime", "desc");
        }
        QueryResult<Merchants> queryResult = merchantsService.preMerHelpList((merchantsDTO.getPage() - 1) * pageNum,
                pageNum, merchantsDTO, orderby);

        List<Merchants> list = queryResult.getResultlist();
        List<List<String>> lists = new ArrayList<List<String>>();

        for (int i = 0; i < list.size(); i++) {
            Merchants merchants = list.get(i);
            List<String> strings = new ArrayList<String>();
            strings.add(String.valueOf(i + 1));
            strings.add(Utils.getString(merchants.getMerId()));
            strings.add(Utils.getString(merchants.getMerName()));
            if (merchants.getOrgans() != null) {
                strings.add(merchants.getOrgans().getName());
            }
            else {
                strings.add("");
            }
            strings.add(Utils.getOptionsIntegerName(OptionsValue.MERCHANT_STATUS, merchants.getStatus()));
            strings.add(DateTimeTool.dateFormat("", merchants.getUpdateTime()));
            String operation = "<a href=javascript:" + "secPreMer('" + merchants.getMerId() + "'," + "'"
                    + merchants.getMerName() + "')" + " title='选择'>[选择]</a>&nbsp;";
            strings.add(operation);
            lists.add(strings);
        }

        PageView pageView = new PageView(merchantsDTO.getPage(), queryResult.getTotalrecord());
        ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView, "mercQuery"));
        return Utils.printInfo(listInfoDTO);

    }

    /**
     * 商户帮助查询数据方法
     * 
     * @return
     * @throws Exception
     */

    public String orgJsonPageList() throws Exception {
        this.getSession().setAttribute(Globals.QUERY_PARM, merchantsDTO);
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        if (StringUtils.isNotBlank(this.getOrderProperty()) && StringUtils.isNotBlank(this.getOrderDirection())) {
            orderby.put(this.getOrderProperty(), this.getOrderDirection());
        }
        else {
            orderby.put("updateTime", "desc");
        }
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        QueryResult queryResult = merchantsService.queryMerByCond((merchantsDTO.getPage() - 1) * pageNum, pageNum,
                merchantsDTO, orderby);
        List<Merchants> list = queryResult.getResultlist();

        List<List<String>> lists = new ArrayList<List<String>>();
        for (int i = 0; i < list.size(); i++) {
            Merchants merchants = list.get(i);
            List<String> strings = new ArrayList<String>();
            strings.add(String.valueOf(i + 1));
            strings.add(Utils.getString(merchants.getMerId()));
            strings.add(Utils.getString(merchants.getMerName()));
            String operation = "<a href=javascript:secMer('" + merchants.getMerId() + "','" + merchants.getMerName()
                    + "') title='选择'>[选择]</a>&nbsp;";
            strings.add(operation);
            lists.add(strings);
        }

        PageView pageView = new PageView(merchantsDTO.getPage(), queryResult.getTotalrecord());
        ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
        return Utils.printInfo(listInfoDTO);
    }

    private MerchantsDTO getMerDto(Merchants merchants, MerchantsDTO merchantsDTO) {

        UserSession us = Utils.getUserSession();

        merchantsDTO.setMerId(Utils.getString(merchants.getMerId()));
        merchantsDTO.setMerName(Utils.getString(merchants.getMerName()));
        merchantsDTO.setOrganId(merchants.getOrgans().getOrganId());
        merchantsDTO.setOrganName(merchants.getOrgans().getName());
        merchantsDTO.setCoopWay(merchants.getCoopWay());
        merchantsDTO.setRakeRate(merchants.getRakeRate());
        // 获取上级经销商信息
        Merchants preMer = new Merchants();
        if (merchants.getPreMerId() != null && merchants.getPreMerId() != "" && (!"".equals(merchants.getPreMerId()))) {
            preMer = merchantsService.find(merchants.getPreMerId());
            merchantsDTO.setPreMerName(preMer.getMerName());
        }
        merchantsDTO.setAreaId(merchants.getAreaId());
        merchantsDTO.setStatus(merchants.getStatus());
        merchantsDTO.setAgentSign(merchants.getAgentSign());
        merchantsDTO
                .setAgentDiscRate((us.getUserLevel() == 2 && merchants.getAgentDiscRate() == null) ? new BigDecimal(0)
                        : merchants.getAgentDiscRate());
        merchantsDTO.setTranLimitSign(merchants.getTranLimitSign());
        merchantsDTO.setRevorkReason(Utils.getString(merchants.getRevorkReason()));
        merchantsDTO.setConNo(Utils.getString((us.getUserLevel() == 2 && merchants.getConNo() == null) ? " 暂无信息"
                : merchants.getConNo()));
        merchantsDTO.setTeleNo(Utils.getString(merchants.getTeleNo()));
        merchantsDTO.setConPerName(Utils.getString(merchants.getConPerName()));
        merchantsDTO.setConPerTeleNo(Utils.getString(merchants.getConPerTeleNo()));
        merchantsDTO.setBankName(Utils.getString(merchants.getBankName()));
        merchantsDTO.setBankAccountNo(Utils.getString(merchants.getBankAccountNo()));
        merchantsDTO.setBankUser(Utils.getString(merchants.getBankUser()));
        merchantsDTO.setUpdateTime(merchants.getUpdateTime());
        merchantsDTO.setAddress(Utils.getString(merchants.getAddress()));
        merchantsDTO.setZip(Utils.getString(merchants.getZip()));
        merchantsDTO.setSettPeriod(merchants.getSettPeriod());
        merchantsDTO.setLastSettTime(merchants.getLastSettTime());
        merchantsDTO.setInvSign(merchants.getInvSign());
        merchantsDTO.setPrivateSign(merchants.getPrivateSign());
        merchantsDTO.setPreMerId(merchants.getPreMerId());
        if (merchants.getOrgans() != null) {
            merchantsDTO.setOrganId(merchants.getOrgans().getOrganId());
        }
        return merchantsDTO;
    }

    /**
     *@Title:getEditMerDTO
     *@Description:获取待更改商户信息
     *@param:@param oilPrices
     *@param:@param merOilRel
     *@param:@param merchantsDTO2
     *@param:@return
     *@return:MerchantsDTO
     *@author:
     *@thorws:
     */
    /*
     * private List<MerchantsDTO> getEditMerDTO(List<OilPrice> oilPrices,
     * List<MerOilRel> merOilRel, MerchantsDTO merchantsDTO) {
     * List<MerchantsDTO> saleOils = new ArrayList<MerchantsDTO>(); //获取所有油品信息
     * List<OilType> oilTypes = oilTypeService.getAllOilTypes(); List<Integer>
     * oilTypeIds = new ArrayList<Integer>(); List<Integer> compareIds = new
     * ArrayList<Integer>(); //获取所有OilTypeId for(OilType oilType : oilTypes){
     * oilTypeIds.add(oilType.getOilTypeId()); }
     * 
     * for(int i = 0 ; i < merOilRel.size() ; i++ ){ MerchantsDTO merDto = new
     * MerchantsDTO(); merDto.setOilType(merOilRel.get(i).getOilTypeId());
     * for(int j = 0 ; j < oilPrices.size() ; j ++){ //如果油品ID相同，则放入
     * if(merOilRel.get(i).getOilTypeId()
     * .equals(oilPrices.get(j).getOilTypeId())){ //油品选中状态
     * merDto.setOilTypeStatus("1"); //油品类型ID
     * merDto.setOilType(merOilRel.get(i).getOilTypeId()); //售油价格
     * merDto.setSaleAmt(oilPrices.get(i).getSalePrice()); //获取油品类型名称 String
     * oilTypeName = merchantsService.queryOilTypeById(oilPrices.get(i)
     * .getOilTypeId()).getOilTypeName();
     * 
     * merDto.setOilTypeName(oilTypeName); //储备量
     * merDto.setReserve(merOilRel.get(i).getOilStorage()); //放入比较集合
     * compareIds.add(merOilRel.get(i).getOilTypeId()); } }
     * saleOils.add(merDto); } oilTypeIds.removeAll(compareIds); for(int z = 0 ;
     * z < oilTypeIds.size(); z++){ MerchantsDTO merDTO = new MerchantsDTO();
     * merDTO.setOilTypeStatus("0"); merDTO.setOilType(oilTypeIds.get(z));
     * //获取油品类型名称 String oilTypeName =
     * merchantsService.queryOilTypeById(oilTypeIds.get(z)).getOilTypeName();
     * merDTO.setOilTypeName(oilTypeName); saleOils.add(merDTO); }
     * 
     * 
     * return saleOils; }
     */

}
