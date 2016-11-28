package com.blog.service.link.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.blog.bean.QueryResult;
import com.blog.bean.article.Article;
import com.blog.bean.links.Link;
import com.blog.dao.common.DaoSupport;
import com.blog.dao.link.LinkDao;
import com.blog.dto.article.ArticleDTO;
import com.blog.dto.link.LinkDTO;
import com.blog.service.link.LinkService;
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
@Service(LinkService.LINKSERVICE)
public class LinkServiceImpl extends DaoSupport<Link> implements LinkService {

	/** 注入资源信息 */
	@Resource
	private LinkDao linkDao;

	/**
	 *@Title:updateServiceList
	 *@Description:更新
	 *@param serviceListDTO
	 *@Return:void
	 *@author:徐凯强
	 *@Date:2014-7-22下午02:18:09
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateServiceList(ArticleDTO articleDTO) {
		try {
			Article article = new Article();
			article = (Article) EntityDtoConverter
					.dto2Bean(articleDTO, article);
			this.update(article);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 *@MethodName:queryAll
	 *@Description:查询所有的链接信息
	 *@throws Exception
	 *@author:徐半仙儿
	 *@return QueryResult<LinkDTO>
	 *@date:2015-7-17下午09:30:24
	 */
	public QueryResult<LinkDTO> queryAll()
			throws Exception {
		return linkDao.queryAll();

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSaveDTO(ArticleDTO articleDTO) {
		try {
			Article article = new Article();
			article = (Article) EntityDtoConverter
					.dto2Bean(articleDTO, article);
			// 发表时间
			article.setContentDate(new Date());
			this.save(article);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
