package com.paySystem.ic.dao.lucene.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.lucene.document.DateTools.Resolution;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.search.Search;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.lucene.LuceneDAO;
import com.paySystem.ic.utils.DateTimeTool;

/***
 * @ProjectName:MarsPlan
 * @ClassName:LuceneDaoImpl
 * @Description:索引数据库操作实现查询索引数据源集合
 * @date: Apr 16, 20163:00:23 PM
 * @author: bruce
 * @version: V1.0
 */
@Repository(LuceneDAO.LUCENEDAO)
public class LuceneDaoImpl extends DaoSupport<Search> implements LuceneDAO {

	/**
	 * @MethodName:queryForList
	 * @Description:查询索引源数据集合
	 * @param statementName要检索的MapperSId
	 * @author:bruce
	 * @return List查询结果集合
	 * @date:Apr 16, 20163:00:58 PM
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List queryForList(String statementName) {
		String jpql = "select * from " + statementName;
		Query query = this.em.createNativeQuery(jpql);
		List resultList = new ArrayList<>();
		List<Object[]> list = query.getResultList();
		if (statementName.equals("t_article")) {
			for (int i = 0; i < list.size(); i++) {
				Object[] result = list.get(i);
				Search search = new Search();
				search.setId(result[0].toString());
				search.setTitle(result[9].toString());
				search.setContent(result[2].toString());
				search.setReadTimes(result[5].toString());
				search.setCreateDate(DateTimeTool.doDateFormatStringyymmddhhmmss((Date)result[3]));
				search.setUrl("article/article!findArticleById?articleDTO.id="+result[0].toString());
				resultList.add(search);
			}
		}
		if (statementName.equals("t_code")) {
			for (int i = 0; i < list.size(); i++) {
				Object[] result = list.get(i);
				Search search = new Search();
				search.setId(result[0].toString());
				search.setTitle(result[4].toString());
				search.setContent(result[3].toString());
				search.setCreateDate(DateTimeTool.doDateFormatStringyymmddhhmmss((Date)result[2]));
				search.setUrl("code/code!findCodeById?codeDTO.id="+result[0].toString());
				resultList.add(search);
			}
		}
		if (statementName.equals("t_software")) {
			for (int i = 0; i < list.size(); i++) {
				Object[] result = list.get(i);
				Search search = new Search();
				search.setId(result[0].toString());
				search.setTitle(result[4].toString());
				search.setContent(result[3].toString());
				search.setCreateDate(DateTimeTool.doDateFormatStringyymmddhhmmss((Date)result[2]));
				search.setUrl("software/software!findSoftWareById?softWareDTO.id="+result[0].toString());
				resultList.add(search);
			}
		}
		return resultList;
	}

}
