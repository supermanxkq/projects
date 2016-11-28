package com.paySystem.ic.bean.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName:LevelModelDetail
 * @Description:商户等级明细
 * @date: 2014-5-23下午08:32:35
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name="b_lmdetail")
public class LevelModelDetail implements Serializable{

	private static final long serialVersionUID = 1L;

	/** 明细ID */
	private Integer ledId;
	
	/** 项目编号 */
	private Integer proNo;
	
	/**区间值起始*/
	private String interValBegin;
	
	/**区间值结束*/ 
	/*private String interValEnd;*/
	
	/**得分*/
	private Integer score;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getLedId() {
		return ledId;
	}

	public void setLedId(Integer ledId) {
		this.ledId = ledId;
	}

	@Column
	public Integer getProNo() {
		return proNo;
	}

	public void setProNo(Integer proNo) {
		this.proNo = proNo;
	}


	@Column
	public Integer getScore() {
		return score;
	}


	public void setScore(Integer score) {
		this.score = score;
	}
	
	
	@Column(length=20)
	public String getInterValBegin() {
		return interValBegin;
	}

	public void setInterValBegin(String interValBegin) {
		this.interValBegin = interValBegin;
	}

	
	
	

	
	
}
