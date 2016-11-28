package com.paySystem.ic.web.dto.member;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:MemGradeDTO
 * @Description:会员等级Dto
 * @date: 2014-7-15下午02:17:50
 * @author: 张亚运
 * @version: V1.0
 */
public class MemGradeDTO extends BaseDTO implements Serializable {

    /**
     *@Description:序列号
     */
    private static final long serialVersionUID = 188158838253376256L;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(BigDecimal lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public BigDecimal getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(BigDecimal upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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

}
