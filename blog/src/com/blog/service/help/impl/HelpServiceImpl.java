package com.blog.service.help.impl;

import org.springframework.stereotype.Service;

import com.blog.bean.help.Order;
import com.blog.dao.common.DaoSupport;
import com.blog.service.help.HelpService;

@Service(HelpService.HELPSERVICE)
public class HelpServiceImpl extends DaoSupport<Order> implements
		HelpService {
}
