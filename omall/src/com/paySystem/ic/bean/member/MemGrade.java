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
 * @ClassName:MemGrade
 * @Description:会员等级实体
 * @date: 2014-7-15上午11:25:53
 * @author: 张亚运
 * @version: V1.0
 */
@Entity
@Table(name = "m_memgrade")
public class MemGrade implements Serializable {

    /**
     *@Description:序列号
     */
    private static final long serialVersionUID = 2765229482362843787L;

    /**等级编号*/
    private Integer gradeId;

    /**等级名称*/
    private String gradeName;

    /**使用状态
     * 0：禁用
     * 1：启用
     * 9：删除
     * */
    private Integer status;

    /**等级积分下限*/
    private BigDecimal lowerLimit;

    /**等级积分上限*/
    private BigDecimal upperLimit;

    /**操作人*/
    private String operator;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    /**等级图片路径*/
    private String imgSrc;

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    @Column(length = 20)
    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    @Column(columnDefinition = "char(1)")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(columnDefinition = "DECIMAL(15,0)")
    public BigDecimal getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(BigDecimal lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    @Column(columnDefinition = "DECIMAL(15,0)")
    public BigDecimal getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(BigDecimal upperLimit) {
        this.upperLimit = upperLimit;
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

    @Column
    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

}
