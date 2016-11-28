package com.paySystem.ic.web.dto.message;

import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @Description: 短信信息
 * @author: 张国法
 * @version: V1.0
 */

public class ShortMesSendDTO extends BaseDTO {

    /** 短信Id号  */
    private Integer smsId;

    /** 短信名称 */
    private String smsTitle;

    /** 短信内容 */
    private String smsContent;

    private Integer userType;

    /** 申请商户机构编号 */
    private String merOrgId;

    /** 申请商户机构编号 */
    private String merOrgName;

    /** 创建时间 */
    private Date createTime;

    private Date auditTime;

    private Integer smsType;

    private BigDecimal totalPrice;

    private Integer num;

    private Integer smsStatus;

    private String operator;

    private String createDate;

    private String auditDate;

    private String groupId;

    private String groupName;

    private String memGroupStatus;

    public Integer getSmsId() {
        return smsId;
    }

    public void setSmsId(Integer smsId) {
        this.smsId = smsId;
    }

    public String getSmsTitle() {
        return smsTitle;
    }

    public void setSmsTitle(String smsTitle) {
        this.smsTitle = smsTitle;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public String getMerOrgId() {
        return merOrgId;
    }

    public void setMerOrgId(String merOrgId) {
        this.merOrgId = merOrgId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getSmsType() {
        return smsType;
    }

    public void setSmsType(Integer smsType) {
        this.smsType = smsType;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(Integer smsStatus) {
        this.smsStatus = smsStatus;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMemGroupStatus() {
        return memGroupStatus;
    }

    public void setMemGroupStatus(String memGroupStatus) {
        this.memGroupStatus = memGroupStatus;
    }

    public String getMerOrgName() {
        return merOrgName;
    }

    public void setMerOrgName(String merOrgName) {
        this.merOrgName = merOrgName;
    }

}
