package com.lucene.common;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lucene.util.GenericsUtils;

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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(Object entity) {
		em.persist(entity);
	}
}
