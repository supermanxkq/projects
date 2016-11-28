package com.paySystem.ic.bean.message;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @ClassName:AppUser
 * @Description:App端需要 短信信息
 * @date: 2014-5-26下午03:53:23
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name = "b_sms")
public class ShortMesSend implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 短信Id号  */
    private Integer smsId;

    /** 短信名称 */
    private String smsTitle;

    /** 短信内容 */
    private String smsContent;

    /** 短信费用 */
    private BigDecimal totalPrice;

    /** 短信条数 */
    private Integer num;

    /** 申请商户机构编号 */
    private String merOrgId;

    /** 申请者
     * 0：平台
     * 1：机构
     * 2：商户
     * */
    private Integer userType;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间/审核时间 */
    private Date auditTime;

    /** 目标人群 */
    private Integer smsType;

    /** 状态 */
    private Integer smsStatus;

    /** 操作人*/
    private String operator;

    @Id
    //@SequenceGenerator(name = "b_Sms_SEQ", sequenceName = "b_Sms_SEQ", initialValue = 10, allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "b_Sms_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Integer getSmsId() {
        return smsId;
    }

    public void setSmsId(Integer smsId) {
        this.smsId = smsId;
    }

    @Column(length = 30)
    public String getSmsTitle() {
        return smsTitle;
    }

    public void setSmsTitle(String smsTitle) {
        this.smsTitle = smsTitle;
    }

    @Column(length = 200)
    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    @Column(length = 15)
    public String getMerOrgId() {
        return merOrgId;
    }

    public void setMerOrgId(String merOrgId) {
        this.merOrgId = merOrgId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(columnDefinition = "char(1)")
    public Integer getSmsType() {
        return smsType;
    }

    public void setSmsType(Integer smsType) {
        this.smsType = smsType;
    }

    @Column(columnDefinition = "DECIMAL(10,2)")
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Column(columnDefinition = "char(6)")
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    @Column(columnDefinition = "char(1)")
    public Integer getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(Integer smsStatus) {
        this.smsStatus = smsStatus;
    }

    @Column(length = 20)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Column(columnDefinition = "char(1)")
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

}
