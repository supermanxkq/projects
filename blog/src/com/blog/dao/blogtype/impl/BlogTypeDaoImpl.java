package com.blog.dao.blogtype.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.blog.bean.QueryResult;
import com.blog.bean.blogtype.BlogType;
import com.blog.dao.blogtype.BlogTypeDAO;
import com.blog.dao.common.DaoSupport;
import com.blog.dto.blogtype.BlogTypeDTO;
import com.blog.dto.system.UserSession;
import com.blog.util.EntityDtoConverter;
import com.blog.util.Utils;

@Repository(BlogTypeDAO.BLOGTYPEDAO)
public class BlogTypeDaoImpl extends DaoSupport<BlogType> implements
		BlogTypeDAO {

	public QueryResult<BlogTypeDTO> queryAll(int firstIndex, int pageNum,
			BlogTypeDTO blogTypeDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		QueryResult<BlogTypeDTO> queryResultDTOList = new QueryResult<BlogTypeDTO>();
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

		QueryResult<BlogType> queryResult = this.getScrollData(firstIndex,
				pageNum, wherejpql.toString(), queryParams.toArray(), orderBy);
		/** 将实体queryResult转换为DTOqueryResult */
		List<BlogType> listEntity = queryResult.getResultlist();
		List<BlogTypeDTO> listDtos = changeListToDtoList(listEntity);
		/** 赋值QueryResult<MessServParamConfigDTO> */
		queryResultDTOList.setResultlist(listDtos);
		queryResultDTOList.setTotalrecord(queryResult.getTotalrecord());
		return queryResultDTOList;
	}

	public List<BlogTypeDTO> changeListToDtoList(List<BlogType> listEntity) {
		List<BlogTypeDTO> listDtos = new ArrayList<BlogTypeDTO>();

		for (int i = 0; i < listEntity.size(); i++) {
			BlogTypeDTO blogTypeDTO = new BlogTypeDTO();
			try {
				blogTypeDTO = (BlogTypeDTO) EntityDtoConverter.bean2Dto(
						listEntity.get(i), blogTypeDTO);
				listDtos.add(blogTypeDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listDtos;
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, String> getAllTypes() {
		Map<Integer, String> sBlogTypeDTOs = new HashMap<Integer, String>();
		String jpql = "select o.name,o.id from BlogType o";
		Query query = em.createQuery(jpql);
		List<Object[]> blogTypes =query.getResultList();
		for (int i = 0; i < blogTypes.size(); i++) {
			Object[] tObjects=blogTypes.get(i);
				sBlogTypeDTOs.put(Integer.parseInt(tObjects[1].toString()), tObjects[0].toString());
		}
		return sBlogTypeDTOs;
	}
}
