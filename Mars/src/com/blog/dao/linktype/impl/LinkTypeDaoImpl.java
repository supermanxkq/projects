package com.blog.dao.linktype.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.blog.bean.QueryResult;
import com.blog.bean.links.Link;
import com.blog.bean.links.LinkType;
import com.blog.dao.common.DaoSupport;
import com.blog.dao.link.LinkDao;
import com.blog.dao.linktype.LinkTypeDao;
import com.blog.dto.link.LinkDTO;
import com.blog.dto.link.LinkTypeDTO;
import com.blog.util.EntityDtoConverter;

@Repository(LinkTypeDao.LINKTYPEDAO)
public class LinkTypeDaoImpl extends DaoSupport<LinkType> implements LinkTypeDao {

	/**
	 *@MethodName:queryAll
	 *@Description:查找所有的链接信息
	 *@throws Exception
	 *@author:徐半仙儿
	 *@date:2015-7-17下午09:38:57
	 */
	public QueryResult<LinkTypeDTO> queryAll()
			throws Exception {
		QueryResult<LinkTypeDTO> queryResultDTOList = new QueryResult<LinkTypeDTO>();
		// UserSession us = Utils.getUserSession();
		/** 模糊查询 */
		/** 根据博客的标题进行模糊查询 */
		// if (articleDTO.getTitle() != null &&
		// !articleDTO.getTitle().equals("")) {
		// wherejpql.append(" and o.title like ?");
		// queryParams.add("%" + articleDTO.getTitle() + "%");
		// }
		// /**根据博客的分类进行查询*/
		// if (articleDTO.getBlogType()!=
		// null&&articleDTO.getBlogType().getBlogTypeId()!=-1) {
		// wherejpql.append(" and o.blogType.blogTypeId="+articleDTO.getBlogType().getBlogTypeId());
		// }
		//		
		// wherejpql.append(" and o.status!=9");
		// wherejpql.append(" and o.user.userId="+us.getUserId());
		// if (serviceListDTO.getServType() != null
		// && serviceListDTO.getServType() != -1) {
		// wherejpql.append(" and o.servType=?");
		// queryParams.add(serviceListDTO.getServType());
		// }

		QueryResult<LinkType> queryResult = this.getScrollData();
		/** 将实体queryResult转换为DTOqueryResult */
		List<LinkType> listEntity = queryResult.getResultlist();
		// 实体集合转换为DTO集合
		List<LinkTypeDTO> listDtos = changeListToDtoList(listEntity);
		queryResultDTOList.setResultlist(listDtos);
		queryResultDTOList.setTotalrecord(queryResult.getTotalrecord());
		return queryResultDTOList;
	}

	/**
	 *@MethodName:changeListToDtoList
	 *@Description:实体集合转换为DTO集合
	 *@param listEntity
	 *@author:徐半仙儿
	 *@return List<LinkDTO>
	 *@date:2015-7-17下午09:40:40
	 */
	public List<LinkTypeDTO> changeListToDtoList(List<LinkType> listEntity) {
		List<LinkTypeDTO> listDtos = new ArrayList<LinkTypeDTO>();

		for (int i = 0; i < listEntity.size(); i++) {
			LinkTypeDTO linkTypeDTO = new LinkTypeDTO();
			try {
				linkTypeDTO = (LinkTypeDTO) EntityDtoConverter.bean2Dto(listEntity
						.get(i), linkTypeDTO);
				listDtos.add(linkTypeDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listDtos;
	}
}
