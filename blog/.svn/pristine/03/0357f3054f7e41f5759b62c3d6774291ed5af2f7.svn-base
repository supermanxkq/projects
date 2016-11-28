package com.blog.dao.timeline;

import java.sql.Connection;
import java.util.*;
import java.io.Serializable;

public interface TimeLineDAO {
	public static final String TIMELINEDAO = "TimeLineDAO";

	public Object loadById(Class clazz, Serializable id);

	public Object loadObject(String hql);

	public void delById(Class clazz, Serializable id);

	public void saveOrUpdate(Object obj);

	public List listAll(String clazz);

	public List listAll(String clazz, int pageNo, int pageSize);

	public int countAll(String clazz);

	public List query(String hql);

	public List query(String hql, int pageNo, int pageSize);

	public int countQuery(String hql);

	public int countQuery2(String hql);

	public int update(String hql);

	public Connection getConnection();

	public String getIpToAddress(double ip);

	public boolean updateClicks(int clicks, int id);

	public int SqlUpdate(String Sql);

	public int countSqlQuery(String sql);

	public List SqlQueryList(String sql);

	public List SqlQueryList(String sql, int pageNo, int pageSize);
}
