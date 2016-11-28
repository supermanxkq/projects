package com.alipay.service;

import com.alipay.bean.Order;
import com.alipay.dao.DAO;

public interface HelpService extends DAO<Order> {
	/** 常量 */
	public static final String HELPSERVICE = "HelpService";

}
