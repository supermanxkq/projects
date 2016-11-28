package com.paySystem.ic.web.dto.report;

import java.math.BigDecimal;

import com.paySystem.ic.web.dto.BaseDTO;


public class MemConsAnalyDTO extends BaseDTO{
	/**会员id  */
	private String memId;
	/**会员名称  */
	private String memName;
	/**商户id  */
	private String merId;
	/**机构id*/
	private String orgId;
	/**机构商户名称  */
	private String orgMerName;
	/**账户类型
	 *0 现金     1 积分 
	 */
	private Integer acctypeId;
	/**选择排名  */
	private Integer rank;
	/**排名方式
	 *0 金额     1 次数 
	 */
	private Integer rankSign;
	/**起始时间  */
	private String beginDate;
	/**结束时间  */
	private String endDate;
	/**消费金额  */
	private BigDecimal consAmt;
	/**消费次数*/
	private Integer consCount;
	/**统计方式
	 * 0  全部    1 按机构统计   2 按商户统计
	 * */
	private Integer countType;
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgMerName() {
		return orgMerName;
	}
	public void setOrgMerName(String orgMerName) {
		this.orgMerName = orgMerName;
	}
	public Integer getAcctypeId() {
		return acctypeId;
	}
	public void setAcctypeId(Integer acctypeId) {
		this.acctypeId = acctypeId;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getRankSign() {
		return rankSign;
	}
	public void setRankSign(Integer rankSign) {
		this.rankSign = rankSign;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public BigDecimal getConsAmt() {
		return consAmt;
	}
	public void setConsAmt(BigDecimal consAmt) {
		this.consAmt = consAmt;
	}
	public Integer getConsCount() {
		return consCount;
	}
	public void setConsCount(Integer consCount) {
		this.consCount = consCount;
	}
	public Integer getCountType() {
		return countType;
	}
	public void setCountType(Integer countType) {
		this.countType = countType;
	}

}
