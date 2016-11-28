package com.paySystem.ic.dao.log.impl;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.log.SiteRemind;
import com.paySystem.ic.dao.log.SiteRemindDao;
import com.paySystem.ic.service.common.DaoSupport;

/**
 * @ProjectName: omall_back
 * @ClassName: SiteRemindDaoImpl
 * @Description: 网站提醒商户详细表接口实现
 * @date: 2014-11-10
 * @author: 廖晓远 
 * @version: V1.0
 */
@Repository(SiteRemindDao.SITEREMINDDAO)
public class SiteRemindDaoImpl  extends DaoSupport<SiteRemind> implements SiteRemindDao {

}
