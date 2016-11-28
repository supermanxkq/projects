package com.paySystem.ic.web.dto.buss;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:SmsTemDTO
 * @Description:短信常用模版Dto
 * @date: 2014-7-14上午09:42:06
 * @author: 张亚运
 * @version: V1.0
 */
public class SmsTemDTO extends BaseDTO implements Serializable {

    /**
     *@Description:序列号
     */
    private static final long serialVersionUID = 8145904281768423352L;

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

    public Integer getStemId() {
        return stemId;
    }

    public void setStemId(Integer stemId) {
        this.stemId = stemId;
    }

    public String getStemName() {
        return stemName;
    }

    public void setStemName(String stemName) {
        this.stemName = stemName;
    }

    public String getStemTitle() {
        return stemTitle;
    }

    public void setStemTitle(String stemTitle) {
        this.stemTitle = stemTitle;
    }

    public String getStemContent() {
        return stemContent;
    }

    public void setStemContent(String stemContent) {
        this.stemContent = stemContent;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

}
