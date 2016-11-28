package com.paySystem.ic.web.dto.payInterfaceConfig;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall
 * @ClassName:PayInterConfDTO
 * @Description:支付配置接口实体的DTO
 * @date: 2014-9-19
 * @author: 王楠
 * @version: V1.0
 */
public class PayInterConfDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 84527839465992412L;

	/**接口Id*/
	private Integer psId;
	/**接口名称*/
	private String psName;
	/**支付机构名称*/
	private String payOrgName;
	/**接口地址*/
	private String psUrl;
	/**接口类型
	 *0：标准双接口
     *1：担保交易接口
     *2：即时到帐交易接口
	 */
	private Integer psType;
	/**支付手续费率*/
	private BigDecimal psRate;
	/**账户号*/
	private String psAccount;
	/**口令*/
	private String psPwd;
	/**密钥*/
	private String secretKey;
	/**是否支持货到付款   0:是  1：否*/
	private Integer isNotArrivPay;
	/**是否支持在线支付    0：是   1：否*/
	private  Integer isNotOnLinePay;
	/**支持交易货币
	 *0:人民币
	 *1：港币
	 *2：澳币
	 *3：美金
	 *4：英镑
	 *5：日元
     */
	private Integer currency;
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;
	/**状态     0：启用   1：禁用   9：删除*/
	private Integer status;
	/**支付方式描述*/
	private String descr;
	public Integer getPsId() {
		return psId;
	}
	public void setPsId(Integer psId) {
		this.psId = psId;
	}
	public String getPsName() {
		return psName;
	}
	public void setPsName(String psName) {
		this.psName = psName;
	}
	public String getPayOrgName() {
		return payOrgName;
	}
	public void setPayOrgName(String payOrgName) {
		this.payOrgName = payOrgName;
	}
	public String getPsUrl() {
		return psUrl;
	}
	public void setPsUrl(String psUrl) {
		this.psUrl = psUrl;
	}
	public Integer getPsType() {
		return psType;
	}
	public void setPsType(Integer psType) {
		this.psType = psType;
	}
	public BigDecimal getPsRate() {
		return psRate;
	}
	public void setPsRate(BigDecimal psRate) {
		this.psRate = psRate;
	}
	public String getPsAccount() {
		return psAccount;
	}
	public void setPsAccount(String psAccount) {
		this.psAccount = psAccount;
	}
	public String getPsPwd() {
		return psPwd;
	}
	public void setPsPwd(String psPwd) {
		this.psPwd = psPwd;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public Integer getIsNotArrivPay() {
		return isNotArrivPay;
	}
	public void setIsNotArrivPay(Integer isNotArrivPay) {
		this.isNotArrivPay = isNotArrivPay;
	}
	public Integer getIsNotOnLinePay() {
		return isNotOnLinePay;
	}
	public void setIsNotOnLinePay(Integer isNotOnLinePay) {
		this.isNotOnLinePay = isNotOnLinePay;
	}
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
}
