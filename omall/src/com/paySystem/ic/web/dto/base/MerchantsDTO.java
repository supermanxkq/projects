package com.paySystem.ic.web.dto.base;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.web.dto.BaseDTO;

public class MerchantsDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -1324112849666702193L;

    /** 商户号 */
    private String merId;

    /** 商户名 */
    private String merName;

    /*	*//** 刷红卡的手续费率 */
    /*
     * private BigDecimal rakeRate = new BigDecimal(100);
     */
    /** 区域号 */
    private String areaId;

    /** 商户状态0-新开户1-正常2-锁定 3撤销 9删除 */
    private Integer status;

    /** 代理商标志0-非代理商1-代理商 */
    private Integer agentSign;

    /** 代理商扣率 */
    private BigDecimal agentDiscRate;

    /** 充值限额标志0: 无充值上限1: 有充值上限 */
    private Integer tranLimitSign;

    /** 撤销标志 */
    private Integer revorkSign;

    /** 撤销原因 */
    private String revorkReason;

    /** 合同号 */
    private String conNo;

    /** 联系人姓名 */
    private String conPerName;

    /** 营业电话号码 */
    private String teleNo;

    /** 联系人电话号码 */
    private String conPerTeleNo;

    /** 商户开户银行 */
    private String bankName;

    /** 商户开户账号 */
    private String bankAccountNo;

    /** 开户人 */
    private String bankUser;

    /** 更新日期 */
    private Date updateTime;

    /** 地址 */
    private String address;

    /** 邮编 */
    private String zip;

    /** 创建时间 */
    private Date createTime;

    /** 商户所属机构 */
    private Organs organs;

    /** 结算周期: 以天为单位 */
    private Integer settPeriod;

    /** 上次结算时间 */
    private Date lastSettTime;

    /** 是否开票 0否1是 */
    private Integer invSign;

    /** 是否私户 0否1是 */
    private Integer privateSign;

    /** 上级商户号 */
    private String preMerId;

    /** 商户介绍 */
    private String intro;

    /** 商户结算方式0：按消费金额结算1：按交易笔数结算2: 无需结算 */
    private Integer settlementWay;

    /** 单笔手续费(按交易笔数结算) */
    private BigDecimal singleFee = new BigDecimal(0.00);

    /** 手续费率 */
    private BigDecimal rakeRate = new BigDecimal(0.00);

    /** 手续费上限 */
    private BigDecimal feeLimit = new BigDecimal(0.00);

    /** 服务平台分成比率 */
    private BigDecimal platformRate = new BigDecimal(0.00);

    /** 收单机构分成比率 */
    private BigDecimal acquirerRate = new BigDecimal(0.00);

    /** 发卡机构分成比率 */
    private BigDecimal organRate = new BigDecimal(10.00);

    /** 计算结算金额方式 0按实际金额 1按原金额 */
    private Integer settelCountWay = 0;

    private List<String> cardBins;

    /** 允许消费的卡BIN号 */
    private String cardBin;

    private String cardName;

    private String cardBinStatus;

    /** 每月消费上限 */
    private String limitTranAmount = "0";

    /** 是否选中卡状态 */
    private List<String> cardBinStatuss;

    /** 单卡每月消费上限 */
    private List<String> limitTranAmounts;

    /** 是否赠送积分 0否1是 */
    private Integer isSalePoint = 1;

    /** 是否赠送积分 0否1是 */
    private List<Integer> isSalePoints;

    private String isSalePointStr;

    private String organId;

    private String organName;

    private Integer helpSign;

    /** 会员编号 */
    private Integer memId;

    /***********************************
     * 
     * 石油平台支付管理系统增加内容
     * 
     * *
     ********************************/

    /** 合作方式 **/
    private Integer coopWay;

    /** 是否开启推荐人标志 */
    private Integer refSign;

    /** 商户等级 */
    private Integer merLevel;

    /** 保证金金额 */
    private BigDecimal bailAmt;

    /** 购油限额 */
    private BigDecimal buyLimitAmt;

    /** 上级经销商名称 */
    private String preMerName;

    /** 合作状态 */
    private Integer coopStatus;

    /** 油品类型集合 */
    private List<Integer> oilTypes;

    /** 油品类型 */
    private Integer oilType;

    /** 油品类型名称 */
    private String oilTypeName;

    /** 售油价集合 */
    private List<BigDecimal> saleAmts;

    /** 售油价 */
    private BigDecimal saleAmt = new BigDecimal(0.00);

    /** 储备量集合 */
    private List<BigDecimal> reserves;

    /** 储备量 */
    private BigDecimal reserve = new BigDecimal(0.00);;

    private String oilTypeStatus;

    /** 售油油品类型选中状态 */
    private List<String> oilTypeStatuss;

    // 商户领卡：商户所属机构
    private String stockSsOrganId;

    /******************************************
     * * 电商平台增加内容 商户业务参数 * *
     ******************************************/

    /** 商品货号前缀 */
    private String gnoPrefix;

    /** 客户下单是否给商户发送信息 */
    private Integer cosmSign;

    /** 客户付款是否给商户发送信息 */
    private Integer cpsmSign;

    /** 客户付款是否给客户发送信息 */
    private Integer cpscSign;

    /** 商户发货是否给客户发送信息 */
    private Integer msscSign;

    /** 店铺已有商品数量 */
    private Integer exitsCount = 0;

    /** 店铺已上架商品数量 */
    private Integer salingCount = 0;

    // icp 备案证书附件
    private File icpFile;

    // icp备案号
    private String icpNo;

    // icp附件地址
    private String icpFilePath;

    /** 是否支持银行卡，1：支持，0：不支持 **/
    private Integer isBankCard;

    /**平台帐号*/
    private String accNo;

    public String getIcpNo() {
        return icpNo;
    }

    public void setIcpNo(String icpNo) {
        this.icpNo = icpNo;
    }

    public String getStockSsOrganId() {
        return stockSsOrganId;
    }

    public void setStockSsOrganId(String stockSsOrganId) {
        this.stockSsOrganId = stockSsOrganId;
    }

    public String getOilTypeStatus() {
        return oilTypeStatus;
    }

    public void setOilTypeStatus(String oilTypeStatus) {
        this.oilTypeStatus = oilTypeStatus;
    }

    public List<String> getOilTypeStatuss() {
        return oilTypeStatuss;
    }

    public void setOilTypeStatuss(List<String> oilTypeStatuss) {
        this.oilTypeStatuss = oilTypeStatuss;
    }

    public String getOilTypeName() {
        return oilTypeName;
    }

    public void setOilTypeName(String oilTypeName) {
        this.oilTypeName = oilTypeName;
    }

    public List<Integer> getOilTypes() {
        return oilTypes;
    }

    public void setOilTypes(List<Integer> oilTypes) {
        this.oilTypes = oilTypes;
    }

    public Integer getOilType() {
        return oilType;
    }

    public void setOilType(Integer oilType) {
        this.oilType = oilType;
    }

    public List<BigDecimal> getSaleAmts() {
        return saleAmts;
    }

    public void setSaleAmts(List<BigDecimal> saleAmts) {
        this.saleAmts = saleAmts;
    }

    public BigDecimal getSaleAmt() {
        return saleAmt;
    }

    public void setSaleAmt(BigDecimal saleAmt) {
        this.saleAmt = saleAmt;
    }

    public List<BigDecimal> getReserves() {
        return reserves;
    }

    public void setReserves(List<BigDecimal> reserves) {
        this.reserves = reserves;
    }

    public BigDecimal getReserve() {
        return reserve;
    }

    public void setReserve(BigDecimal reserve) {
        this.reserve = reserve;
    }

    public Integer getCoopStatus() {
        return coopStatus;
    }

    public void setCoopStatus(Integer coopStatus) {
        this.coopStatus = coopStatus;
    }

    public String getPreMerName() {
        return preMerName;
    }

    public void setPreMerName(String preMerName) {
        this.preMerName = preMerName;
    }

    public BigDecimal getBuyLimitAmt() {
        return buyLimitAmt;
    }

    public void setBuyLimitAmt(BigDecimal buyLimitAmt) {
        this.buyLimitAmt = buyLimitAmt;
    }

    public BigDecimal getBailAmt() {
        return bailAmt;
    }

    public void setBailAmt(BigDecimal bailAmt) {
        this.bailAmt = bailAmt;
    }

    public Integer getCoopWay() {
        return coopWay;
    }

    public void setCoopWay(Integer coopWay) {
        this.coopWay = coopWay;
    }

    public Integer getRefSign() {
        return refSign;
    }

    public void setRefSign(Integer refSign) {
        this.refSign = refSign;
    }

    public Integer getMerLevel() {
        return merLevel;
    }

    public void setMerLevel(Integer merLevel) {
        this.merLevel = merLevel;
    }

    public Integer getHelpSign() {
        return helpSign;
    }

    public void setHelpSign(Integer helpSign) {
        this.helpSign = helpSign;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public Integer getAgentSign() {
        return agentSign;
    }

    public void setAgentSign(Integer agentSign) {
        this.agentSign = agentSign;
    }

    public BigDecimal getAgentDiscRate() {
        return agentDiscRate;
    }

    public void setAgentDiscRate(BigDecimal agentDiscRate) {
        this.agentDiscRate = agentDiscRate;
    }

    public Integer getTranLimitSign() {
        return tranLimitSign;
    }

    public void setTranLimitSign(Integer tranLimitSign) {
        this.tranLimitSign = tranLimitSign;
    }

    public Integer getRevorkSign() {
        return revorkSign;
    }

    public void setRevorkSign(Integer revorkSign) {
        this.revorkSign = revorkSign;
    }

    public String getConNo() {
        return conNo;
    }

    public void setConNo(String conNo) {
        this.conNo = conNo;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Organs getOrgans() {
        return organs;
    }

    public void setOrgans(Organs organs) {
        this.organs = organs;
    }

    public Integer getSettPeriod() {
        return settPeriod;
    }

    public void setSettPeriod(Integer settPeriod) {
        this.settPeriod = settPeriod;
    }

    public Date getLastSettTime() {
        return lastSettTime;
    }

    public void setLastSettTime(Date lastSettTime) {
        this.lastSettTime = lastSettTime;
    }

    public Integer getInvSign() {
        return invSign;
    }

    public void setInvSign(Integer invSign) {
        this.invSign = invSign;
    }

    public Integer getPrivateSign() {
        return privateSign;
    }

    public void setPrivateSign(Integer privateSign) {
        this.privateSign = privateSign;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRevorkReason() {
        return revorkReason;
    }

    public void setRevorkReason(String revorkReason) {
        this.revorkReason = revorkReason;
    }

    public String getTeleNo() {
        return teleNo;
    }

    public void setTeleNo(String teleNo) {
        this.teleNo = teleNo;
    }

    public String getConPerName() {
        return conPerName;
    }

    public void setConPerName(String conPerName) {
        this.conPerName = conPerName;
    }

    public String getConPerTeleNo() {
        return conPerTeleNo;
    }

    public void setConPerTeleNo(String conPerTeleNo) {
        this.conPerTeleNo = conPerTeleNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankUser() {
        return bankUser;
    }

    public void setBankUser(String bankUser) {
        this.bankUser = bankUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPreMerId() {
        return preMerId;
    }

    public void setPreMerId(String preMerId) {
        this.preMerId = preMerId;
    }

    public Integer getSettlementWay() {
        return settlementWay;
    }

    public void setSettlementWay(Integer settlementWay) {
        this.settlementWay = settlementWay;
    }

    public BigDecimal getSingleFee() {
        return singleFee;
    }

    public void setSingleFee(BigDecimal singleFee) {
        this.singleFee = singleFee;
    }

    public BigDecimal getRakeRate() {
        return rakeRate;
    }

    public void setRakeRate(BigDecimal rakeRate) {
        this.rakeRate = rakeRate;
    }

    public BigDecimal getFeeLimit() {
        return feeLimit;
    }

    public void setFeeLimit(BigDecimal feeLimit) {
        this.feeLimit = feeLimit;
    }

    public BigDecimal getPlatformRate() {
        return platformRate;
    }

    public void setPlatformRate(BigDecimal platformRate) {
        this.platformRate = platformRate;
    }

    public BigDecimal getAcquirerRate() {
        return acquirerRate;
    }

    public void setAcquirerRate(BigDecimal acquirerRate) {
        this.acquirerRate = acquirerRate;
    }

    public BigDecimal getOrganRate() {
        return organRate;
    }

    public void setOrganRate(BigDecimal organRate) {
        this.organRate = organRate;
    }

    public Integer getSettelCountWay() {
        return settelCountWay;
    }

    public void setSettelCountWay(Integer settelCountWay) {
        this.settelCountWay = settelCountWay;
    }

    public List<String> getCardBins() {
        return cardBins;
    }

    public void setCardBins(List<String> cardBins) {
        this.cardBins = cardBins;
    }

    public String getCardBin() {
        return cardBin;
    }

    public void setCardBin(String cardBin) {
        this.cardBin = cardBin;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardBinStatus() {
        return cardBinStatus;
    }

    public void setCardBinStatus(String cardBinStatus) {
        this.cardBinStatus = cardBinStatus;
    }

    public String getLimitTranAmount() {
        return limitTranAmount;
    }

    public void setLimitTranAmount(String limitTranAmount) {
        this.limitTranAmount = limitTranAmount;
    }

    public List<String> getCardBinStatuss() {
        return cardBinStatuss;
    }

    public void setCardBinStatuss(List<String> cardBinStatuss) {
        this.cardBinStatuss = cardBinStatuss;
    }

    public List<String> getLimitTranAmounts() {
        return limitTranAmounts;
    }

    public void setLimitTranAmounts(List<String> limitTranAmounts) {
        this.limitTranAmounts = limitTranAmounts;
    }

    public Integer getIsSalePoint() {
        return isSalePoint;
    }

    public void setIsSalePoint(Integer isSalePoint) {
        this.isSalePoint = isSalePoint;
    }

    public List<Integer> getIsSalePoints() {
        return isSalePoints;
    }

    public void setIsSalePoints(List<Integer> isSalePoints) {
        this.isSalePoints = isSalePoints;
    }

    public String getIsSalePointStr() {
        return isSalePointStr;
    }

    public void setIsSalePointStr(String isSalePointStr) {
        this.isSalePointStr = isSalePointStr;
    }

    public Integer getCosmSign() {
        return cosmSign;
    }

    public void setCosmSign(Integer cosmSign) {
        this.cosmSign = cosmSign;
    }

    public Integer getCpsmSign() {
        return cpsmSign;
    }

    public void setCpsmSign(Integer cpsmSign) {
        this.cpsmSign = cpsmSign;
    }

    public Integer getCpscSign() {
        return cpscSign;
    }

    public void setCpscSign(Integer cpscSign) {
        this.cpscSign = cpscSign;
    }

    public Integer getMsscSign() {
        return msscSign;
    }

    public void setMsscSign(Integer msscSign) {
        this.msscSign = msscSign;
    }

    public Integer getExitsCount() {
        return exitsCount;
    }

    public void setExitsCount(Integer exitsCount) {
        this.exitsCount = exitsCount;
    }

    public Integer getSalingCount() {
        return salingCount;
    }

    public void setSalingCount(Integer salingCount) {
        this.salingCount = salingCount;
    }

    public String getGnoPrefix() {
        return gnoPrefix;
    }

    public void setGnoPrefix(String gnoPrefix) {
        this.gnoPrefix = gnoPrefix;
    }

    public File getIcpFile() {
        return icpFile;
    }

    public void setIcpFile(File icpFile) {
        this.icpFile = icpFile;
    }

    public String getIcpFilePath() {
        return icpFilePath;
    }

    public void setIcpFilePath(String icpFilePath) {
        this.icpFilePath = icpFilePath;
    }

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }

    public Integer getIsBankCard() {
        return isBankCard;
    }

    public void setIsBankCard(Integer isBankCard) {
        this.isBankCard = isBankCard;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

}
