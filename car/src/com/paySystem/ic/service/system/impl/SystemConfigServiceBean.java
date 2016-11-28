package com.paySystem.ic.service.system.impl;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.system.SystemConfig;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.system.SystemConfigService;

@Service
public class SystemConfigServiceBean extends DaoSupport<SystemConfig> implements SystemConfigService {

}
