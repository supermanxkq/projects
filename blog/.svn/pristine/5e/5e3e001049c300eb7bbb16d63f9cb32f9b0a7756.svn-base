package com.blog.dao.timeline.impl;

import java.io.Serializable;

import java.sql.Connection;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.blog.dao.timeline.TimeLineDAO;

import java.sql.CallableStatement;
import java.sql.Types;

public class TimeLineDAOImpl extends HibernateDaoSupport implements TimeLineDAO {

	@Resource
	HibernateTemplate hibernateTemplate;

	public int countAll(String clazz) {
		final String hql = "select count(*) from " + clazz + " as a";
		Long count = (Long) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql);
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});
		return count.intValue();
	}

	public int countQuery(String hql) {
		final String counthql = hql;
		Long count = (Long) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(counthql);
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});
		return count.intValue();
	}

	public int countQuery2(String hql) {
		final String counthql = hql;
		Long count = (Long) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(counthql);
						query.setMaxResults(1);
						return query.uniqueResult();
					}
				});
		return count.intValue();
	}

	public void delById(Class clazz, Serializable id) {
		hibernateTemplate.delete(hibernateTemplate.load(clazz, id));
	}

	public List listAll(String clazz) {
		return hibernateTemplate.find("from " + clazz
				+ " as a order by a.id desc");
	}

	public List listAll(String clazz, int pageNo, int pageSize) {
		final int pNo = pageNo;
		final int pSize = pageSize;
		final String hql = "from " + clazz + " as a order by a.id desc";
		List list = hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql);
				query.setMaxResults(pSize);
				query.setFirstResult((pNo - 1) * pSize);
				List result = query.list();
				if (!Hibernate.isInitialized(result))
					Hibernate.initialize(result);
				return result;
			}
		});
		return list;
	}

	public Object loadById(Class clazz, Serializable id) {
		return hibernateTemplate.get(clazz, id);
	}

	public Object loadObject(String hql) {
		final String hql1 = hql;
		Object obj = null;
		List list = hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql1);
				return query.list();
			}
		});
		if (list.size() > 0)
			obj = list.get(0);
		return obj;
	}

	public List query(String hql) {
		final String hql1 = hql;
		return hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql1);
				return query.list();
			}
		});
	}

	public List query(String hql, int pageNo, int pageSize) {
		final int pNo = pageNo;
		final int pSize = pageSize;
		final String hql1 = hql;
		return hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql1);
				query.setMaxResults(pSize);
				query.setFirstResult((pNo - 1) * pSize);
				List result = query.list();
				if (!Hibernate.isInitialized(result))
					Hibernate.initialize(result);
				return result;
			}
		});
	}

	public void saveOrUpdate(Object obj) {
		hibernateTemplate.saveOrUpdate(obj);
	}

	public int update(String hql) {
		final String hql1 = hql;
		return ((Integer) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql1);
				return query.executeUpdate();
			}
		})).intValue();
	}

	public Connection getConnection() {
		return hibernateTemplate.getSessionFactory().getCurrentSession()
				.connection();
	}

	public String getIpToAddress(double ip) {
		final double ip1 = ip;
		return ((String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						String address = "δ֪����";
						try {
							Connection conn = session.connection();
							CallableStatement stmt = conn
									.prepareCall("{call pro_iptoaddress(?,?)}");
							stmt.setDouble(1, ip1);
							stmt.registerOutParameter(2, Types.VARCHAR);
							stmt.execute();
							address = stmt.getString(2);
							if (address == null || address.length() < 1)
								address = "δ֪����";
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						return address;
					}
				})).toString();
	}

	public boolean updateClicks(int clicks, int id) {
		final int clicks1 = clicks;
		final int id1 = id;
		return false;
	}

	/** ԭ��̬SQL����������� */
	public int SqlUpdate(String sql) {
		final String sql1 = sql;
		return ((Integer) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql1);
				return query.executeUpdate();
			}
		})).intValue();
	}

	public int countSqlQuery(String sql) {
		final String countsql = sql;
		Long count = (Long) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				SQLQuery query = session.createSQLQuery(countsql);
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});
		return count.intValue();
	}

	public List SqlQueryList(String sql) {
		final String sql1 = sql;
		return hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql1);
				return query.list();
			}
		});
	}

	public List SqlQueryList(String sql, int pageNo, int pageSize) {
		final int pNo = pageNo;
		final int pSize = pageSize;
		final String sql1 = sql;
		return hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql1);
				query.setMaxResults(pSize);
				query.setFirstResult((pNo - 1) * pSize);
				List result = query.list();
				if (!Hibernate.isInitialized(result))
					Hibernate.initialize(result);
				return result;
			}
		});
	}

}