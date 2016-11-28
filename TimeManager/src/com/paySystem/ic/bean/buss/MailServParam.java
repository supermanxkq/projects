package com.paySystem.ic.bean.buss;

import java.io.Serializable;
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
 * @ClassName:MailServParam
 * @Description:邮件服务器设置参数表
 * @date: 2014-7-4下午12:09:09
 * @author: 张亚运
 * @version: V1.0
 */
@Entity
@Table(name = "p_emailservparam")
public class MailServParam implements Serializable {

    /**
     *@Description:序列号
     */
    private static final long serialVersionUID = -1588451094237913674L;

    /**参数Id*/
    private Integer espId;

    /**发送邮件服务器地址*/
    private String espUrl;

    /**服务器端口*/
    private String espPort;

    /**邮件发送帐号*/
    private String emaccNo;

    /**邮箱密码*/
    private String empwd;

    /**回复地址*/
    private String replyUrl;

    /**使用状态*/
    private Integer status;

    /**操作人*/
    private String operator;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Integer getEspId() {
        return espId;
    }

    public void setEspId(Integer espId) {
        this.espId = espId;
    }

    @Column(length = 255)
    public String getEspUrl() {
        return espUrl;
    }

    public void setEspUrl(String espUrl) {
        this.espUrl = espUrl;
    }

    @Column(length = 20)
    public String getEspPort() {
        return espPort;
    }

    public void setEspPort(String espPort) {
        this.espPort = espPort;
    }

    @Column(length = 60)
    public String getEmaccNo() {
        return emaccNo;
    }

    public void setEmaccNo(String emaccNo) {
        this.emaccNo = emaccNo;
    }

    @Column(length = 30)
    public String getEmpwd() {
        return empwd;
    }

    public void setEmpwd(String empwd) {
        this.empwd = empwd;
    }

    @Column(length = 255)
    public String getReplyUrl() {
        return replyUrl;
    }

    public void setReplyUrl(String replyUrl) {
        this.replyUrl = replyUrl;
    }

    @Column(columnDefinition = "char(1)")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(length = 15)
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

}
