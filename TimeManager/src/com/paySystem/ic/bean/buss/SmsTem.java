package com.paySystem.ic.bean.buss;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @ClassName:SmsTem
 * @Description:短信常用模版设置
 * @date: 2014-7-9下午04:04:17
 * @author: 张亚运
 * @version: V1.0
 */
@Entity
@Table(name = "b_smstem")
public class SmsTem implements Serializable {

    /**
     *@Description:序列号
     */
    private static final long serialVersionUID = 1392637242385951697L;

    /**短信模版Id*/
    private Integer stemId;

    /**短信模版名称*/
    private String stemName;

    /**短信模版主题*/
    private String stemTitle;

    /**短信模版内容*/
    private String stemContent;

    /**更新时间*/
    private Date updateTime;

    /**操作人*/
    private String operator;

    @Id
    /**@GeneratedValue(strategy = GenerationType.IDENTITY)*/
    @Column
    public Integer getStemId() {
        return stemId;
    }

    public void setStemId(Integer stemId) {
        this.stemId = stemId;
    }

    @Column(length = 50)
    public String getStemName() {
        return stemName;
    }

    public void setStemName(String stemName) {
        this.stemName = stemName;
    }

    @Column(length = 200)
    public String getStemTitle() {
        return stemTitle;
    }

    public void setStemTitle(String stemTitle) {
        this.stemTitle = stemTitle;
    }

    @Column(length = 350)
    public String getStemContent() {
        return stemContent;
    }

    public void setStemContent(String stemContent) {
        this.stemContent = stemContent;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(length = 20)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

}
