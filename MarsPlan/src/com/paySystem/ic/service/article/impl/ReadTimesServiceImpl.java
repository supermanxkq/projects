package com.paySystem.ic.service.article.impl;

import java.math.BigInteger;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.article.ReadTimes;
import com.paySystem.ic.dao.article.ReadTimesDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.service.article.ReadTimesService;

/**
 * @ProjectName:MarsPlan
 * @ClassName:ReadTimesServiceImpl
 * @Description:阅读次数
 * @date: Apr 21, 20162:29:49 PM
 * @author: bruce
 * @version: V1.0
 */
@Service(ReadTimesService.READTIMESSERVICE)
public class ReadTimesServiceImpl extends DaoSupport<ReadTimes> implements ReadTimesService {

	@Resource
	private ReadTimesDAO readtimesDao;

	@Override
	public BigInteger queryCountByArticleId(Integer articleId) {
		return readtimesDao.queryCountByArticleId(articleId);
	}

}
