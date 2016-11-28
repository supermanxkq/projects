package com.blog.service.linktype.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.blog.bean.QueryResult;
import com.blog.bean.article.Article;
import com.blog.bean.links.Link;
import com.blog.bean.links.LinkType;
import com.blog.dao.common.DaoSupport;
import com.blog.dao.link.LinkDao;
import com.blog.dao.linktype.LinkTypeDao;
import com.blog.dto.article.ArticleDTO;
import com.blog.dto.link.LinkDTO;
import com.blog.dto.link.LinkTypeDTO;
import com.blog.service.link.LinkService;
import com.blog.service.linktype.LinkTypeService;
import com.blog.util.EntityDtoConverter;

/**
 * @ProjectName:blog
 * @ClassName:LinkServiceImpl
 * @Description:链接服务类的实现
 * @date: 2015-7-17下午09:35:52
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-7-17下午09:35:52
 */
@Service(LinkTypeService.LINKTYPESERVICE)
public class LinkTypeServiceImpl extends DaoSupport<LinkType> implements LinkTypeService {

	/** 注入资源信息 */
	@Resource
	private LinkTypeDao linkTypeDao;


	/**
	 *@MethodName:queryAll
	 *@Description:查询所有的链接信息
	 *@throws Exception
	 *@author:徐半仙儿
	 *@return QueryResult<LinkDTO>
	 *@date:2015-7-17下午09:30:24
	 */
	public QueryResult<LinkTypeDTO> queryAll()
			throws Exception {
		return linkTypeDao.queryAll();

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSaveDTO(LinkTypeDTO linkTypeDTO) {
		try {
			LinkType linkType = new LinkType();
			linkType = (LinkType) EntityDtoConverter
					.dto2Bean(linkTypeDTO, linkType);
			this.save(linkType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
