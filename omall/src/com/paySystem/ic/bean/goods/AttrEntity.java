package com.paySystem.ic.bean.goods;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 属性值枚举表bean
 * @ClassName: AttrEntity
 * @Description: 属性值枚举类
 * @date: 2014-08-06 上午02:29:36
 * @author: Jacky
 * @version: V1.0
 */
@Entity
@Table(name="B_AttrEn")
public class AttrEntity implements Serializable {
	private static final long serialVersionUID = 2025590262398757450L;
	
	/**属性枚举ID*/
	private int attrEnId;
	
	/**属性枚举名称*/
	private String attrEnName;
	
	/**属性编号*/
	private int attrId;
	/**状态  0启用1停用*/
	private Integer status; 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public int getAttrEnId() {
		return attrEnId;
	}

	public void setAttrEnId(int attrEnId) {
		this.attrEnId = attrEnId;
	}

	@Column(length=60,nullable=false)
	public String getAttrEnName() {
		return attrEnName;
	}

	public void setAttrEnName(String attrEnName) {
		this.attrEnName = attrEnName;
	}
    @Column(columnDefinition = "char(1)")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(length=8,nullable=false)
	public int getAttrId() {
		return attrId;
	}

	public void setAttrId(int attrId) {
		this.attrId = attrId;
	}
	
}
