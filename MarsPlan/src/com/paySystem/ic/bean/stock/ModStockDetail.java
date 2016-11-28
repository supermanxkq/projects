package com.paySystem.ic.bean.stock;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.paySystem.ic.bean.card.CardNo;

/**
 * @ClassName:ModStockDetail
 * @Description:库存变动明细实体
 * @date: 2013-12-13下午06:13:20
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name="S_ModDetail")
public class ModStockDetail implements Serializable {

	private static final long serialVersionUID = -6341314863889913640L;
	/** 流水号  */
	private Integer id;
	/** 库存变动信息 */
	private ModStock modStock;
	/** 变动的卡 */
	private CardNo cardNo;
	
/*	@Id
	@SequenceGenerator(name="s_ModDetail_SEQ",sequenceName="s_ModDetail_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="s_ModDetail_SEQ")*/
	/**
	 * 添加MySql自增列支持
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "StockChangedId")
	public ModStock getModStock() {
		return modStock;
	}
	public void setModStock(ModStock modStock) {
		this.modStock = modStock;
	}
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="cardNo")
	public CardNo getCardNo() {
		return cardNo;
	}
	public void setCardNo(CardNo cardNo) {
		this.cardNo = cardNo;
	}
	
	
}
