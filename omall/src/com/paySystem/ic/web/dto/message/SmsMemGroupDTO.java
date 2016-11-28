package com.paySystem.ic.web.dto.message;

import com.paySystem.ic.web.dto.BaseDTO;

public class SmsMemGroupDTO extends BaseDTO {

    /** 编号 */
    private Integer id;

    /** 短信Id号  */
    private Integer smsId;

    /** 会员群组编号 */
    private Integer groupId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSmsId() {
        return smsId;
    }

    public void setSmsId(Integer smsId) {
        this.smsId = smsId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

}
