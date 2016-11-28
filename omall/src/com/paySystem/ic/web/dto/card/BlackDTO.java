package com.paySystem.ic.web.dto.card;

import java.io.Serializable;
import java.util.Date;
import com.paySystem.ic.web.dto.BaseDTO;



/**
 * @ClassName:BlackDTO
 * @Description:继承实现接口
 * @date: 2014-3-3下午08:05:01
 * @author: 王月
 * @version: V1.0
 */
public class BlackDTO extends BaseDTO implements Serializable {

private static final long serialVersionUID = 3380445115780513689L;


/**
 * 黑名单流水号
 */
private Integer blkId;

/**
 * 卡号
 */
private String cardNo;

/**
 * 锁卡的时间
 */
private Date lockTime;

/**
 * 操作员id
 */
private String operId;

/**
 * 备注
 */
private String descr;

/**
 * 版本号 赵巧鹤
 * */
private String verNo;

/**
 * 版本号 赵巧鹤
 * */
private String organId;


public Integer getBlkId() {
	return blkId;
}

public void setBlkId(Integer blkId) {
	this.blkId = blkId;
}

public String getCardNo() {
	return cardNo;
}

public void setCardNo(String cardNo) {
	this.cardNo = cardNo;
}

public Date getLockTime() {
	return lockTime;
}

public void setLockTime(Date lockTime) {
	this.lockTime = lockTime;
}

public String getOperId() {
	return operId;
}

public void setOperId(String operId) {
	this.operId = operId;
}

public String getDescr() {
	return descr;
}

public void setDescr(String descr) {
	this.descr = descr;
}

public String getVerNo() {
	return verNo;
}

public void setVerNo(String verNo) {
	this.verNo = verNo;
}

public String getOrganId() {
	return organId;
}

public void setOrganId(String organId) {
	this.organId = organId;
}

}
