package com.paySystem.ic.web.dto.buss;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:MailServParamDTO
 * @Description:邮件服务器设置参数Dto
 * @date: 2014-7-4下午05:03:18
 * @author: 张亚运
 * @version: V1.0
 */
public class MailServParamDTO extends BaseDTO implements Serializable {

    /**
     *@Description:序列号
     */
    private static final long serialVersionUID = 1294147952843260336L;

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

    /**测试邮箱地址*/
    private String testEmail;

    /**操作人*/
    private String operator;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    /**使用状态*/
    private Integer status;

    public Integer getEspId() {
        return espId;
    }

    public void setEspId(Integer espId) {
        this.espId = espId;
    }

    public String getEspUrl() {
        return espUrl;
    }

    public void setEspUrl(String espUrl) {
        this.espUrl = espUrl;
    }

    public String getEspPort() {
        return espPort;
    }

    public void setEspPort(String espPort) {
        this.espPort = espPort;
    }

    public String getEmaccNo() {
        return emaccNo;
    }

    public void setEmaccNo(String emaccNo) {
        this.emaccNo = emaccNo;
    }

    public String getEmpwd() {
        return empwd;
    }

    public void setEmpwd(String empwd) {
        this.empwd = empwd;
    }

    public String getReplyUrl() {
        return replyUrl;
    }

    public void setReplyUrl(String replyUrl) {
        this.replyUrl = replyUrl;
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

    public String getTestEmail() {
        return testEmail;
    }

    public void setTestEmail(String testEmail) {
        this.testEmail = testEmail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
