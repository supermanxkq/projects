package com.paySystem.ic.bean.member;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @ClassName:MemCreditRule
 * @Description:会员积分规则表实体
 * @date: 2014-7-22上午10:07:35
 * @author: 张亚运
 * @version: V1.0
 */
@Entity
@Table(name = "m_memcreditrule")
public class MemCreditRule implements Serializable {

    /**
     *@Description:序列号
     */
    private static final long serialVersionUID = 2682079760609033759L;

    /**积分规则Id*/
    private Integer creditId;

    /**会员等级Id*/
    private Integer gradeId;

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
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Integer getCreditId() {
        return creditId;
    }

    public void setCreditId(Integer creditId) {
        this.creditId = creditId;
    }

    @Column
    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    @Column(columnDefinition = "DECIMAL(13,2)")
    public BigDecimal getConsPoints() {
        return consPoints;
    }

    public void setConsPoints(BigDecimal consPoints) {
        this.consPoints = consPoints;
    }

    @Column(columnDefinition = "DECIMAL(13,2)")
    public BigDecimal getGradePoints() {
        return gradePoints;
    }

    public void setGradePoints(BigDecimal gradePoints) {
        this.gradePoints = gradePoints;
    }

    @Column(columnDefinition = "DECIMAL(10,2)")
    public BigDecimal getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(BigDecimal upperLimit) {
        this.upperLimit = upperLimit;
    }

    @Column(columnDefinition = "char(1)")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(length = 20)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(columnDefinition = "DECIMAL(5,4)")
    public BigDecimal getPointsRate() {
        return pointsRate;
    }

    public void setPointsRate(BigDecimal pointsRate) {
        this.pointsRate = pointsRate;
    }

    @Column(columnDefinition = "DECIMAL(8,2)")
    public BigDecimal getLoginPoints() {
        return loginPoints;
    }

    public void setLoginPoints(BigDecimal loginPoints) {
        this.loginPoints = loginPoints;
    }

}
