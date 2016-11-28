package com.blog.service.help;

import com.blog.bean.help.Order;
import com.blog.dao.common.DAO;

public interface HelpService extends DAO<Order> {
	/** 常量 */
	public static final String HELPSERVICE = "HelpService";

}
