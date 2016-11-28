package com.blog.service.log.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.blog.bean.log.LogBean;
import com.blog.dao.log.LogDao;
import com.blog.service.log.LogService;
import com.blog.service.profile.ProfileService;
@Service(LogService.LOGSERVICE)
public class LogServiceImpl implements LogService {
	@Resource
	private LogDao logDao;


	
	public LogBean getLatestLog(String sessionId, String ip)
			throws Exception {
		return logDao.getLatestLog(sessionId, ip);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveLog(LogBean logBean) throws Exception {
		logDao.saveLog(logBean);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateLog(String id, long stayTime) throws Exception {
		logDao.updateLog(id, stayTime);
	}


	public int getPV(Timestamp startTime, Timestamp endTime) throws Exception {
		return logDao.getPV(startTime, endTime);
	}


	public int getUV(Timestamp startTime, Timestamp endTime) throws Exception {
		return logDao.getUV(startTime, endTime);
	}

}
