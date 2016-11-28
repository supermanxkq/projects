package com.alipay.dao;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.bean.QueryResult;
import com.alipay.util.DateTimeTool;
import com.alipay.util.GenericsUtils;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:DaoSupport
 * @Description:实体操作辅助类DAO的实现
 * @date: 2014-7-17
 * @author: 王楠
 * @version: V1.0
 */
@SuppressWarnings("unchecked")
public abstract class DaoSupport<T> implements DAO<T> {
	@PersistenceContext
	protected EntityManager em;

	protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this
			.getClass());

	/***
	 * ----------------------------------基本方法----------------------------------
	 * -------
	 ***/
	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.common.DAO#save(java.lang.Object
	 *                        )
	 *@MethodName:save
	 *@Description:单一实体的保存方法
	 *@param entity
	 *            实体
	 *@Author:王楠
	 *@Date:2014-7-17下午06:15:16
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(Object entity) {
		em.persist(entity);
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.common.DAO#saves(java.util.List
	 *                        )
	 *@MethodName:saves
	 *@Description:多个实体的保存方法
	 *@param entitys
	 *            实体复数
	 *@Author:王楠
	 *@Date:2014-7-17下午06:15:52
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saves(List<T> entitys) {
		for (Object entity : entitys) {
			em.persist(entity);
		}
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.common.DAO#update(java.lang.Object
	 *                        )
	 *@MethodName:update
	 *@Description:修改方法
	 *@param entity
	 *@Author:王楠
	 *@Date:2014-7-17下午06:16:28
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(Object entity) {
		em.merge(entity);
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.common.DAO#delete(java.io.Serializable
	 *                        [])
	 *@MethodName:delete
	 *@Description:删除方法
	 *@param entityids
	 *            实体id
	 *@Author:王楠
	 *@Date:2014-7-17下午06:16:37
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Serializable... entityids) {
		for (Object id : entityids) {
			em.remove(em.getReference(this.entityClass, id));
		}
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.common.DAO#find(java.io.Serializable
	 *                        )
	 *@MethodName:find
	 *@Description:通过实体ID查询实体的方法
	 *@param entityId
	 *@return
	 *@Author:王楠
	 *@Date:2014-7-17下午06:17:25
	 */
	/** 查询不需要事务传播行为，为只读 */
	public T find(Serializable entityId) {
		return em.find(this.entityClass, entityId);
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.common.DAO#findByJpl(java.lang
	 *                        .String)
	 *@MethodName:findByJpl
	 *@Description:通过jpl语句查询实体的集合方法
	 *@param jpl
	 *@return
	 *@Author:王楠
	 *@Date:2014-7-17下午06:18:03
	 */
	public List<T> findByJpl(String jpl) {
		return em.createQuery(jpl).getResultList();
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.common.DAO#clear()
	 *@MethodName:clear
	 *@Description:清除方法
	 *@Author:王楠
	 *@Date:2014-7-17下午06:18:31
	 */
	public void clear() {
		em.clear();
	}

	/***
	 * -------------------------------分页方法--------------------------------------
	 * -
	 ***/
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.common.DAO#getCount()
	 *@MethodName:getCount
	 *@Description:无参获取总条数的方法
	 *@return
	 *@Author:王楠
	 *@Date:2014-7-17下午06:18:53
	 */
	public long getCount() {
		return (Long) em.createQuery(
				"select count(" + getCountField(this.entityClass) + ") from "
						+ getEntityName(this.entityClass) + " o")
				.getSingleResult();
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.common.DAO#getCount(java.lang.
	 *                        Object[], java.lang.String)
	 *@MethodName:getCount
	 *@Description:有参获取总条数的方法
	 *@param queryParams
	 *            查询参数
	 *@param wherejpql
	 *            查询语句
	 *@return
	 *@Author:王楠
	 *@Date:2014-7-17下午06:19:37
	 */
	public long getCount(Object[] queryParams, String wherejpql) {
		Query query = em.createQuery("select count("
				+ getCountField(this.entityClass)
				+ ") from "
				+ getEntityName(this.entityClass)
				+ " o "
				+ (wherejpql == null || "".equals(wherejpql.trim()) ? ""
						: "where 1=1 " + wherejpql));
		setQueryParams(query, queryParams);
		return (Long) query.getSingleResult();
	}

	/**
	 *@Title:getCountField
	 *@Description:获取需要进行统计（即：select count）的内容
	 *@Params:@param <E>
	 *@Params:@param clazz 实体Bean的Class
	 *@Params:@return
	 *@Return:String
	 *@author:王楠
	 *@Date:2014-7-17下午06:20:31
	 */
	protected static <E> String getCountField(Class<E> clazz) {
		String out = "o";
		try {
			PropertyDescriptor[] propertyDescriptors = Introspector
					.getBeanInfo(clazz).getPropertyDescriptors();
			for (PropertyDescriptor propertydesc : propertyDescriptors) {
				Method method = propertydesc.getReadMethod();
				if (method != null
						&& method.isAnnotationPresent(EmbeddedId.class)) {
					PropertyDescriptor[] ps = Introspector.getBeanInfo(
							propertydesc.getPropertyType())
							.getPropertyDescriptors();
					out = "o."
							+ propertydesc.getName()
							+ "."
							+ (!ps[1].getName().equals("class") ? ps[1]
									.getName() : ps[0].getName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	/**
	 *@Title:getEntityName
	 *@Description:获取实体的名称
	 *@Params:@param <E>
	 *@Params:@param clazz 实体Bean的Class
	 *@Params:@return
	 *@Return:String
	 *@author:王楠
	 *@Date:2014-7-17下午06:34:36
	 */
	protected static <E> String getEntityName(Class<E> clazz) {
		String entityname = clazz.getSimpleName();
		// annotation---注解
		Entity entity = clazz.getAnnotation(Entity.class);
		if (entity.name() != null && !"".equals(entity.name().trim())) {
			entityname = entity.name();
		}
		return entityname;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.common.DAO#getScrollData(int,
	 *                        int)
	 *@MethodName:getScrollData
	 *@Description:获得分页信息
	 *@param firstindex
	 *            起始页
	 *@param maxresult
	 *            页中最大值
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-7-17下午06:35:12
	 */
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult)
			throws Exception {
		return getScrollData(firstindex, maxresult, null, null, null);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData() throws Exception {
		return getScrollData(-1, -1);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult,
			LinkedHashMap<String, String> orderby) throws Exception {
		return getScrollData(firstindex, maxresult, null, null, orderby);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams) throws Exception {
		return getScrollData(firstindex, maxresult, wherejpql, queryParams,
				null);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) throws Exception {
		QueryResult qr = new QueryResult<T>();
		String entityname = getEntityName(this.entityClass);

		Query query = em.createQuery("select o from "
				+ entityname
				+ " o "
				+ (wherejpql == null || "".equals(wherejpql.trim()) ? ""
						: "where 1=1 " + wherejpql) + buildOrderby(orderby));
		System.out.println("select o from "
				+ entityname
				+ " o "
				+ (wherejpql == null || "".equals(wherejpql.trim()) ? ""
						: "where 1=1 " + wherejpql) + buildOrderby(orderby));
		setQueryParams(query, queryParams);
		/** 获取所有的记录 **/
		if (firstindex != -1 && maxresult != -1) {
			query.setFirstResult(firstindex).setMaxResults(maxresult);
		}
		try {
			qr.setResultlist(query.getResultList());
		} catch (Exception e) {
			e.printStackTrace();
		}

		query = em.createQuery("select count("
				+ getCountField(this.entityClass)
				+ ") from "
				+ entityname
				+ " o "
				+ (wherejpql == null || "".equals(wherejpql.trim()) ? ""
						: "where 1=1 " + wherejpql));
		setQueryParams(query, queryParams);
		qr.setTotalrecord((Long) query.getSingleResult());
		return qr;
	}

	/**
	 *@Title:setQueryParams
	 *@Description:设置参数
	 *@Params:@param query
	 *@Params:@param queryParams 参数数组
	 *@Return:void
	 *@author:王楠
	 *@Date:2014-7-17下午06:36:28
	 */
	protected static void setQueryParams(Query query, Object[] queryParams) {
		if (queryParams != null && queryParams.length > 0) {
			for (int i = 0; i < queryParams.length; i++) {
				query.setParameter(i + 1, queryParams[i]);
			}
		}
	}

	/**
	 *@Title:buildOrderby
	 *@Description:封装order by语句，建立排序语句 order by o.id desc, o.date desc
	 *@Params:@param orderby 排序
	 *@Params:@return
	 *@Return:String
	 *@author:王楠
	 *@Date:2014-7-17下午06:36:53
	 */
	protected static String buildOrderby(LinkedHashMap<String, String> orderby) {
		StringBuffer orderbyql = new StringBuffer("");
		if (orderby != null && orderby.size() > 0) {
			orderbyql.append(" order by ");
			for (String key : orderby.keySet()) {
				orderbyql.append("o.").append(key).append(" ").append(
						orderby.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length() - 1);// 删除最后一个字符
		}
		return orderbyql.toString();
	}

	// 获取自动序列的下一个序列
	@SuppressWarnings("deprecation")
	public Long getSeqVal(String seq, String valType, Session sess) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select " + seq + "." + valType + " from dual";
			Connection conn = sess.connection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			Long lseq = rs.getLong(1);
			stmt.close();
			rs.close();
			return lseq;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.common.DAO#getSysTime()
	 *@MethodName:getSysTime
	 *@Description:获取当前系统时间
	 *@return
	 *@Author:王楠
	 *@Date:2014-7-17下午06:37:21
	 */
	public Date getSysTime() {
		/*
		 * Object object =em.createNativeQuery(
		 * "select to_char(sysdate,'yyyy-MM-dd HH24:mi:ss') from dual"
		 * ).getSingleResult(); Date date = DateTimeTool.dateFormat("",
		 * String.valueOf(object)); return date;
		 */
		Object object = em.createNativeQuery("select current_timestamp()")
				.getSingleResult();
		Date date = DateTimeTool.dateFormat("", String.valueOf(object));
		return date;
	}

	/*
	 * public Date getMySqlSysTime(){ Object object =
	 * em.createNativeQuery("select current_timestamp()").getSingleResult();
	 * Date date = DateTimeTool.dateFormat("", String.valueOf(object)); return
	 * date; }
	 */

}
