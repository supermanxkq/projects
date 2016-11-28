package com.paySystem.ic.dao.article.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.article.ArticleType;
import com.paySystem.ic.dao.article.ArticleTypeDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.article.ArticleTypeDTO;
import com.paySystem.ic.web.dto.system.UserSession;

@Repository(ArticleTypeDAO.ARTICLETYPEDAO)
public class ArticleTypeDaoImpl extends DaoSupport<ArticleType> implements ArticleTypeDAO {

	public QueryResult<ArticleTypeDTO> queryAll(int firstIndex, int pageNum, ArticleTypeDTO articleTypeDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		QueryResult<ArticleTypeDTO> queryResultDTOList = new QueryResult<ArticleTypeDTO>();
		StringBuffer wherejpql = new StringBuffer();
		List<Object> queryParams = new ArrayList<Object>();
		UserSession us = Utils.getUserSession();
		/** 模糊查询 */
		// if (BlogTypeDTO.getTitle() != null &&
		// !BlogTypeDTO.getTitle().equals("")) {
		// wherejpql.append(" and o.title like ?");
		// queryParams.add("%" + BlogTypeDTO.getTitle() + "%");
		// }

		// wherejpql.append(" and o.status!=9");
		// wherejpql.append(" and o.user.userId="+us.getUserId());
		// if (BlogTypeDTO.getGuest() != null && BlogTypeDTO.getGuest() != "") {
		// wherejpql.append(" and o.guest like ?");
		// queryParams.add("%" + BlogTypeDTO.getGuest() + "%");
		// }
		// if (serviceListDTO.getServType() != null
		// && serviceListDTO.getServType() != -1) {
		// wherejpql.append(" and o.servType=?");
		// queryParams.add(serviceListDTO.getServType());
		// }

		QueryResult<ArticleType> queryResult = this.getScrollData(firstIndex, pageNum, wherejpql.toString(),
				queryParams.toArray(), orderBy);
		/** 将实体queryResult转换为DTOqueryResult */
		List<ArticleType> listEntity = queryResult.getResultlist();
		List<ArticleTypeDTO> listDtos = new ArrayList<>();
		for (ArticleType articleType : listEntity) {
			try {
				ArticleTypeDTO articleTypeDTO2 = new ArticleTypeDTO();
				articleTypeDTO2 = (ArticleTypeDTO) EntityDtoConverter.bean2Dto(articleType, articleTypeDTO2);
				listDtos.add(articleTypeDTO2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/** 赋值QueryResult<MessServParamConfigDTO> */
		queryResultDTOList.setResultlist(listDtos);
		queryResultDTOList.setTotalrecord(queryResult.getTotalrecord());
		return queryResultDTOList;

	}

	@SuppressWarnings("unchecked")
	public Map<Integer, String> getAllTypes() {
		Map<Integer, String> sBlogTypeDTOs = new HashMap<Integer, String>();
		String jpql = "select o.name,o.id from BlogType o";
		Query query = em.createQuery(jpql);
		List<Object[]> blogTypes = query.getResultList();
		for (int i = 0; i < blogTypes.size(); i++) {
			Object[] tObjects = blogTypes.get(i);
			sBlogTypeDTOs.put(Integer.parseInt(tObjects[1].toString()), tObjects[0].toString());
		}
		return sBlogTypeDTOs;
	}
}
