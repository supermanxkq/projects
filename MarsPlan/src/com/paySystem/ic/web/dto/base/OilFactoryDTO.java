package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:OilFactoryDTO
 * @Description:油厂信息DTO
 * @date: 2014-5-20上午11:36:20
 * @author: 张亚运
 * @version: V1.0
 */
public class OilFactoryDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 油厂编号 */
	private String oilFactId;

	/** 油厂名 */
	private String oilFactName;

	/** 商户油品关联id */
	private Integer moId;

	/** 区域号 */
	private String areaId;

	/**
	 * 油厂使用状态 0：禁用 1：正常 9：删除
	 */
	private Integer status;

	/** 合同号 */
	private String conNo;

	/** 电话号 */
	private String teleNo;

	/** 联系人 */
	private String conPerName;

	/** 联系人电话 */
	private String conPerTeleNo;

	/** 开户银行 */
	private String bankName;

	/** 开户帐号 */
	private String bankAccNo;

	/** 开户人 */
	private String bankUser;

	/** 更新时间 */
	private String updateTime;

	/** 创建时间 */
	private Date createTime;

	/** 开始时间 */
	private String beginTime;

	/** 结束时间 */
	private String endTime;

	/** 地址 */
	private String address;

	/** 邮编 */
	private String zip;

	/**
	 * 结算时间 （以天为单位）
	 **/
	private Integer settPeriod;

	/** 上次结算时间 */
	private String lastSettTime;

	/**
	 * 是否开票 0：否 1：是
	 **/
	private Integer invSign;

	/** 保证金 */
	private BigDecimal bailAmt;

	/** 购油比率 */
	private BigDecimal buyOilRate;

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
	private BigDecimal reserve = new BigDecimal(0.00);

	/** 日产量集合 */
	private List<BigDecimal> oilDailys;

	/** 日产量 */
	private BigDecimal oilDaily = new BigDecimal(0.00);

	private String oilTypeStatus;
	/** 售油油品类型选中状态 */
	private List<String> oilTypeStatuss;

	public String getOilFactId() {
		return oilFactId;
	}

	public void setOilFactId(String oilFactId) {
		this.oilFactId = oilFactId;
	}

	public String getOilFactName() {
		return oilFactName;
	}

	public void setOilFactName(String oilFactName) {
		this.oilFactName = oilFactName;
	}

	public Integer getMoId() {
		return moId;
	}

	public void setMoId(Integer moId) {
		this.moId = moId;
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

	public String getConNo() {
		return conNo;
	}

	public void setConNo(String conNo) {
		this.conNo = conNo;
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

	public String getBankAccNo() {
		return bankAccNo;
	}

	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}

	public String getBankUser() {
		return bankUser;
	}

	public void setBankUser(String bankUser) {
		this.bankUser = bankUser;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public Integer getSettPeriod() {
		return settPeriod;
	}

	public void setSettPeriod(Integer settPeriod) {
		this.settPeriod = settPeriod;
	}

	public String getLastSettTime() {
		return lastSettTime;
	}

	public void setLastSettTime(String lastSettTime) {
		this.lastSettTime = lastSettTime;
	}

	public Integer getInvSign() {
		return invSign;
	}

	public void setInvSign(Integer invSign) {
		this.invSign = invSign;
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

	public String getOilTypeName() {
		return oilTypeName;
	}

	public void setOilTypeName(String oilTypeName) {
		this.oilTypeName = oilTypeName;
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

	public List<BigDecimal> getOilDailys() {
		return oilDailys;
	}

	public void setOilDailys(List<BigDecimal> oilDailys) {
		this.oilDailys = oilDailys;
	}

	public BigDecimal getOilDaily() {
		return oilDaily;
	}

	public void setOilDaily(BigDecimal oilDaily) {
		this.oilDaily = oilDaily;
	}

	public BigDecimal getBailAmt() {
		return bailAmt;
	}

	public void setBailAmt(BigDecimal bailAmt) {
		this.bailAmt = bailAmt;
	}

	public BigDecimal getBuyOilRate() {
		return buyOilRate;
	}

	public void setBuyOilRate(BigDecimal buyOilRate) {
		this.buyOilRate = buyOilRate;
	}

}
