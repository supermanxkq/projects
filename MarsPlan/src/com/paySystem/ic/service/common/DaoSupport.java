package com.paySystem.ic.service.common;

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

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.GenericsUtils;

@SuppressWarnings("unchecked")
public abstract class DaoSupport<T> implements DAO<T> {
	@PersistenceContext
	protected EntityManager em;
 
	protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());

	// ----------------------------------基本方法-----------------------------------------//
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(Object entity) {
		em.persist(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saves(List<T> entitys){
		for (Object entity : entitys) {
			em.persist(entity);
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(Object entity) {
		
		em.merge(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Serializable... entityids) {
		for (Object id : entityids) {
			em.remove(em.getReference(this.entityClass, id));
		}
	}

	// 查询不需要事务传播行为，为只读
	public T find(Serializable entityId) {
		return em.find(this.entityClass, entityId);
	}

	public List<T> findByJpl(String jpl){
		return em.createQuery(jpl).getResultList();
	}
	
	public void clear() {
		em.clear();
	}

	// -------------------------------分页方法---------------------------------------//
	public long getCount() {
		return (Long) em.createQuery("select count(" + getCountField(this.entityClass) + ") from " + getEntityName(this.entityClass) + " o").getSingleResult();
	}

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

	protected static <E> String getCountField(Class<E> clazz) {
		String out = "o";
		try {
			PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
			for (PropertyDescriptor propertydesc : propertyDescriptors) {
				Method method = propertydesc.getReadMethod();
				if (method != null&& method.isAnnotationPresent(EmbeddedId.class)) {
					PropertyDescriptor[] ps = Introspector.getBeanInfo(propertydesc.getPropertyType()).getPropertyDescriptors();
					out = "o." + propertydesc.getName() + "." + (!ps[1].getName().equals("class") ? ps[1].getName() : ps[0].getName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	/**
	 * 获取实体的名称
	 * 
	 * @param <E>
	 * @param clazz
	 *            实体类
	 * @return
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

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult) throws Exception{
		return getScrollData(firstindex, maxresult, null, null, null);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData() throws Exception{
		return getScrollData(-1, -1);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult,
			LinkedHashMap<String, String> orderby) throws Exception{
		return getScrollData(firstindex, maxresult, null, null, orderby);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult,String wherejpql, Object[] queryParams) throws Exception{
		return getScrollData(firstindex, maxresult, wherejpql, queryParams,null);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult,String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby) throws Exception{
		QueryResult qr = new QueryResult<T>();
		String entityname = getEntityName(this.entityClass);

		Query query = em.createQuery("select o from "+ entityname + " o "
				+ (wherejpql == null || "".equals(wherejpql.trim()) ? "": "where 1=1 " + wherejpql) + buildOrderby(orderby));

		setQueryParams(query, queryParams);

		/** 获取所有的记录 **/
		if (firstindex != -1 && maxresult != -1) {
			query.setFirstResult(firstindex).setMaxResults(maxresult);
		}

		qr.setResultlist(query.getResultList());
		query = em.createQuery("select count("+ getCountField(this.entityClass)+ ") from "+ entityname+ " o "
				+ (wherejpql == null || "".equals(wherejpql.trim()) ? "": "where 1=1 " + wherejpql));
		setQueryParams(query, queryParams);
		System.out.println("select count("+ getCountField(this.entityClass)+ ") from "+ entityname+ " o "
				+ (wherejpql == null || "".equals(wherejpql.trim()) ? "": "where 1=1 " + wherejpql));
		qr.setTotalrecord((Long) query.getSingleResult());
		return qr;
	}

	/**
	 * 设置参数
	 * 
	 * @param query
	 * @param queryParams
	 *            参数数组
	 */
	protected static void setQueryParams(Query query, Object[] queryParams) {
		if (queryParams != null && queryParams.length > 0) {
			for (int i = 0; i < queryParams.length; i++) {
				query.setParameter(i + 1, queryParams[i]);
			}
		}
	}

	/**
	 * 封装order by语句，建立排序语句 order by o.id desc, o.date desc
	 * 
	 * @param orderby
	 *            <key, value>,如<id, desc>,<date,asc>
	 * @return
	 */
	protected static String buildOrderby(LinkedHashMap<String, String> orderby) {
		StringBuffer orderbyql = new StringBuffer("");
		if (orderby != null && orderby.size() > 0) {
			orderbyql.append(" order by ");
			for (String key : orderby.keySet()) {
				orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
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
	
	public Date getSysTime(){
		/*
		 *   Oracle  获取当前时间 
		 * Object object = em.createNativeQuery("select to_char(sysdate,'yyyy-MM-dd HH24:mi:ss') from dual").getSingleResult();*/
		
		/*
		 * MySql 获取当前时间
		 */
		Object object = em.createNativeQuery("select current_timestamp()").getSingleResult();
		Date date = DateTimeTool.dateFormat("", String.valueOf(object));
		return date;	
	}
	
}
