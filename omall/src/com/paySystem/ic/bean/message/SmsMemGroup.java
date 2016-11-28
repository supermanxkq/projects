package com.paySystem.ic.bean.message;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName:SmsMemGroup
 * @Description:短信群发会员群组关联表
 * @date: 2014-8-15下午04:49:48
 * @author: 张亚运
 * @version: V1.0
 */
@Entity
@Table(name = "b_SmsMemGroup")
public class SmsMemGroup implements Serializable {

    /**
     *@Description:序列号
     */
    private static final long serialVersionUID = -2358065207076443960L;

    /** 编号 */
    private Integer id;

    /** 短信Id号  */
    private Integer smsId;

    /** 会员群组编号 */
    private Integer groupId;

    @Id
    //@SequenceGenerator(name = "B_SMG_SEQ", sequenceName = "B_SMG_SEQ", initialValue = 10, allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "B_SMG_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column
    public Integer getSmsId() {
        return smsId;
    }

    public void setSmsId(Integer smsId) {
        this.smsId = smsId;
    }

    @Column
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

}
