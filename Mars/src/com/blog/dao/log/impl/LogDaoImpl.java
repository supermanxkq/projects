package com.blog.dao.log.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.blog.bean.log.LogBean;
import com.blog.dao.common.DaoSupport;
import com.blog.dao.log.LogDao;
import com.blog.util.DateTimeTool;
@Repository(LogDao.LOGDAO)
public class LogDaoImpl extends DaoSupport<LogBean> implements LogDao {
	
	private Logger logger = Logger.getLogger(LogDaoImpl.class);
	
	

	public void saveLog(LogBean logBean) throws Exception {
		this.save(logBean);
		logger.info("保存成功！");
	}
	
	@SuppressWarnings("unchecked")
	public LogBean getLatestLog(String sessionId, String ip)
			throws Exception {
		String jpql = "select *  from t_log    where  sessionId = '"+sessionId+"' and  ip = '"+ip+"' order by  access_time desc limit 0,1;";
		Query query =this.em.createNativeQuery(jpql);
		List<Object[]> list = query.getResultList();
		LogBean logBean =null;
		if(list.size()>0){
				 logBean = new LogBean();
				logBean.setId(list.get(0)[0].toString());
				logBean.setAccessTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", list.get(0)[1].toString()));
				logBean.setSessionId(list.get(0)[4].toString());
				logBean.setIp(list.get(0)[2].toString());
				logBean.setPage(list.get(0)[3].toString());
				logBean.setStayTime(Integer.parseInt(list.get(0)[5].toString()));
		}
		return logBean;
	}

	public void updateLog(String id, long stayTime) throws Exception {
		String jpql = "update t_log set stayTime = "+stayTime+" where id = '"+id+"'";
		Query query =this.em.createNativeQuery(jpql);
		query.executeUpdate();
	}

	public int getPV(Timestamp startTime, Timestamp endTime) throws Exception {
		String jpql = "select count(*) from t_log where access_time >= '"+startTime+"' and access_time < '"+endTime+"'";
		logger.info(jpql);
		Query query =this.em.createNativeQuery(jpql);
		return Integer.parseInt(query.getSingleResult().toString());
	}

	public int getUV(Timestamp startTime, Timestamp endTime) throws Exception {
		String jpql = "select count(ip) from( " +
				"select distinct ip from t_log where access_time >= '"+startTime+"' and access_time < '"+endTime+"') log_temp ";
		logger.info(jpql);
		Query query =this.em.createNativeQuery(jpql);
		return Integer.parseInt(query.getSingleResult().toString());
	}

}
