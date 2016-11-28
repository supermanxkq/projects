package com.blog.service.timeline.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.bean.timeline.HymArticle;
import com.blog.bean.timeline.HymYear;
import com.blog.dao.common.DaoSupport;
import com.blog.service.timeline.TimeLineService;
import com.blog.util.DateTimeTool;

/**
 * @ProjectName:omall
 * @ClassName:MessServParamConfigServiceImpl
 * @Description:TODO
 * @date: 2014-7-22下午02:58:05
 * @author: 徐凯强
 * @version: V1.0
 */
@Service(TimeLineService.TIMELINESERVICE)
public class TimeLineServiceImpl extends DaoSupport implements
		TimeLineService {

	@Override
	public List SqlQueryList(String sql) {
		return null;
	}

	@Override
	public List SqlQueryList(String sql, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int SqlUpdate(String Sql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countAll(String clazz) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countQuery(String hql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countQuery2(String hql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countSqlQuery(String sql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delById(Class clazz, Serializable id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIpToAddress(double ip) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List listAll(String clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List listAll(String clazz, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object loadById(Class clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object loadObject(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List query() {
		List list=new ArrayList();
		String jpql="select * from hym_year as a where 1=1 order by a.id desc";
		List<Object[]> objects=this.em.createNativeQuery(jpql).getResultList();
		for (int i = 0; i < objects.size(); i++) {
			Object[] obj=objects.get(i);
			HymYear hymYear=new HymYear();
			hymYear.setId(Integer.parseInt(obj[0].toString()));
			hymYear.setYear(obj[1].toString());
			list.add(hymYear);
		}
		return list;
	}
	public List queryArticle(String jpql){
		List list=new ArrayList();
		List<Object[]> objects=this.em.createNativeQuery(jpql).getResultList();
		for (int i = 0; i < objects.size(); i++) {
			Object[] obj=objects.get(i);
			HymArticle hymArticle=new HymArticle();
			hymArticle.setId(Integer.parseInt(obj[0].toString()));
			hymArticle.setTitle(obj[1].toString());
			hymArticle.setContent(obj[2].toString());
			hymArticle.setCdate(DateTimeTool.dateFormat("", obj[3].toString()));
			hymArticle.setColor(Integer.parseInt(obj[4].toString()));
			hymArticle.setYearid(Integer.parseInt(obj[5].toString()));
			
			list.add(hymArticle);
		}
		return list;
	}
	@Override
	public List query(String hql, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int update(String hql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateClicks(int clicks, int id) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Resource
//	private ArticleDAO articleDAO;

}
