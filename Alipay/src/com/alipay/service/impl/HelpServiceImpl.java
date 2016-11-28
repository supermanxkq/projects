package com.alipay.service.impl;

import org.springframework.stereotype.Service;

import com.alipay.bean.Order;
import com.alipay.dao.DaoSupport;
import com.alipay.service.HelpService;

@Service(HelpService.HELPSERVICE)
public class HelpServiceImpl extends DaoSupport<Order> implements
		HelpService {
}
