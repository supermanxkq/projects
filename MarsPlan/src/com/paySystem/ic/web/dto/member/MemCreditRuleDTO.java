package com.paySystem.ic.web.dto.member;

import java.io.Serializable;
import java.math.BigDecimal;

import com.paySystem.ic.web.dto.BaseDTO;

public class MemCreditRuleDTO extends BaseDTO implements Serializable {

    /**
     *@Description:序列号
     */
    private static final long serialVersionUID = 7052447360852952212L;

    /**积分规则Id*/
    private Integer creditId;

    /**会员等级Id*/
    private Integer gradeId;

    /**会员等级名称*/
    private String gradeName;

    /**消费积分*/
    private BigDecimal consPoints;

    /**等级积分*/
    private BigDecimal gradePoints;

    /**登录领取*/
    private BigDecimal loginPoints;

    /**连续登录赠送积分比例*/
    private BigDecimal pointsRate;

    /**每日积分上限*/
    private BigDecimal upperLimit;

    /**使用状态*/
    private Integer status;

    /**操作人*/
    private String operator;

    /**创建时间*/
    private String createTime;

    /**更新时间*/
    private String updateTime;

    public Integer getCreditId() {
        return creditId;
    }

    public void setCreditId(Integer creditId) {
        this.creditId = creditId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public BigDecimal getConsPoints() {
        return consPoints;
    }

    public void setConsPoints(BigDecimal consPoints) {
        this.consPoints = consPoints;
    }

    public BigDecimal getGradePoints() {
        return gradePoints;
    }

    public void setGradePoints(BigDecimal gradePoints) {
        this.gradePoints = gradePoints;
    }

    public BigDecimal getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(BigDecimal upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getLoginPoints() {
        return loginPoints;
    }

    public void setLoginPoints(BigDecimal loginPoints) {
        this.loginPoints = loginPoints;
    }

    public BigDecimal getPointsRate() {
        return pointsRate;
    }

    public void setPointsRate(BigDecimal pointsRate) {
        this.pointsRate = pointsRate;
    }

}
