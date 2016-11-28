package com.lucene.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lucene.bean.Search;
import com.lucene.common.DaoSupport;
import com.lucene.dao.LuceneDAO;

@Repository(LuceneDAO.LUCENEDAO)
public class LuceneDaoImpl extends DaoSupport<Search> implements LuceneDAO {

	/**
	 * 查询索引源数据集合
	 * 
	 * @Title:queryForList
	 * @Description: 查询索引源数据集合
	 * @param:@param statementName
	 *                   要检索的Mapper Id
	 * @param:@return
	 * @return:List 查询结果集合
	 * @author: 谢洪飞
	 * @Thorws:
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List queryForList(String statementName) {
		String jpql = "select * from  t_article";
		Query query = this.em.createNativeQuery(jpql);
		List resultList = new ArrayList<>();
		List<Object[]> list = query.getResultList();
		for (int i = 0; i < list.size(); i++) {
			Object[] result = list.get(i);
			Search search = new Search();
			search.setId(result[0].toString());
			search.setTitle(result[9].toString());
			search.setContent(result[2].toString());
			resultList.add(search);
		}
		return resultList;
	}

}
