package com.blog.service.link;

import java.util.LinkedHashMap;

import com.blog.bean.QueryResult;
import com.blog.bean.links.Link;
import com.blog.dao.common.DAO;
import com.blog.dto.article.ArticleDTO;
import com.blog.dto.link.LinkDTO;

/**
 * @ProjectName:blog
 * @ClassName:LinkService
 * @Description:链接服务类
 * @date: 2015-7-17下午09:29:36
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-7-17下午09:29:36
 */
public interface LinkService extends DAO<Link> {
	/** 常量 */
	public static final String LINKSERVICE = "LinkService";

	/**
	 *@MethodName:queryAll
	 *@Description:查询所有的链接信息
	 *@throws Exception
	 *@author:徐半仙儿
	 *@return QueryResult<LinkDTO>
	 *@date:2015-7-17下午09:30:24
	 */
	public QueryResult<LinkDTO> queryAll()
			throws Exception;

	public void addSaveDTO(ArticleDTO articleDTO);
}
