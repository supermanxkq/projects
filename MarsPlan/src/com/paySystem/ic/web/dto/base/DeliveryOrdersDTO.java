package com.paySystem.ic.web.dto.base;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:DeliveryOrdersDTO
 * @Description:发货信息实体DTO
 * @date: 2014-10-10下午03:03:53
 * @author: Jacky
 * @version: V1.0
 */
public class DeliveryOrdersDTO extends BaseDTO {
	
	/**
	 * 自增id
	 */
	private long doId;
	
	/**
	 * 发货状态
	 *  0：未发货；
		1：发货中； 
		2：已发货
	 */
	private Integer status;
	
	/**
	 * 商户merId
	 */
	private String merId;
	
	/**
	 * 买家
	 */
	private String memId;
	
	/**
	 * 收货人姓名
	 */
	private String memName;
	
	/**
	 * 收货人电话
	 */
	private String memTele;
	
	/**
	 * 收货地址
	 */
	private String address;
	
	/**
	 * 订单号
	 */
	private String orderId;
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 商品价格
	 */
	private BigDecimal price;
	
	/**
	 * 商品数量
	 */
	private Integer qty;
	
	/**
	 * 下单时间
	 */
	private Date createTime;
	
	/**
	 * 开始时间
	 */
	private Date startTime;
	
	/**
	 * 结束时间
	 */
	private Date endTime;
	
	/**
	 * 订单号列表
	 */
	private List<String> orderIds = new ArrayList<String>();
	
	/**
	 * 排序
	 */
	private LinkedHashMap<String,String> orderBy;
	
	/**
	 * 格式化日期
	 */
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	/** 被激活的tab**/
	private int activeTab = 0;
	
	/** 总页数**/
	private long totalPage = 0;
	
	/** 订单字符串逗号分隔**/
	private String orderIdStr;
	
	/** 父亲级别code**/
	private String parentCode;
	
	/** 类型**/
	private int type;
	
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getOrderIdStr() {
		return orderIdStr;
	}

	public void setOrderIdStr(String orderIdStr) {
		this.orderIdStr = orderIdStr;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public int getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(int activeTab) {
		this.activeTab = activeTab;
	}

	public LinkedHashMap<String, String> getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(LinkedHashMap<String, String> orderBy) {
		this.orderBy = orderBy;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public long getDoId() {
		return doId;
	}

	public void setDoId(long doId) {
		this.doId = doId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemTele() {
		return memTele;
	}

	public void setMemTele(String memTele) {
		this.memTele = memTele;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public String getPrice2Scale() {
		if(null != price) {
			price.setScale(2,BigDecimal.ROUND_HALF_UP);
			return price.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
		}
		return null;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Date getCreateTime() {
		return createTime;
	}
	
	public String getCreateTimeStr() {
		if(null != createTime) {
			return sdf.format(createTime);
		}
		return null;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<String> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(List<String> orderIds) {
		this.orderIds = orderIds;
	}
	
}
