package com.paySystem.ic.service.article.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.article.Article;
import com.paySystem.ic.dao.article.ArticleDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.service.article.ArticleService;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.UploadUtil;
import com.paySystem.ic.web.dto.article.ArticleDTO;

/**
 * @ProjectName:omall
 * @ClassName:MessServParamConfigServiceImpl
 * @Description:TODO
 * @date: 2014-7-22下午02:58:05
 * @author: 徐凯强
 * @version: V1.0
 */
@Service(ArticleService.ARTICLESERVICE)
public class ArticleServiceImpl extends DaoSupport<Article> implements ArticleService {

	@Resource
	private ArticleDAO articleDAO;

	/**
	 * @Title:updateServiceList
	 * @Description:更新
	 * @param serviceListDTO
	 * @Return:void
	 * @author:徐凯强
	 * @Date:2014-7-22下午02:18:09
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateData(ArticleDTO articleDTO) {
		Article article = new Article();
		String fileName = null;
		try {
			article = (Article) EntityDtoConverter.dto2Bean(articleDTO, article);
			if (articleDTO != null) {
				if (articleDTO.getImageFile() != null && StringUtils.isNotBlank(articleDTO.getImageFileFileName())) {
					/** 上传文件 **/
					fileName = UploadUtil.uploadImg(articleDTO.getImageFile(), articleDTO.getImageFileFileName());
					article.setThumbnailPath(fileName);
				} else {
					article.setThumbnailPath(articleDTO.getThumbnailPath());
				}
			}
			this.update(article);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title:querAll
	 * @Description:分页查找
	 * @param firstIndex
	 * @param pageNum
	 * @param serviceListDTO
	 * @param orderBy
	 * @Return:QueryResult<ServiceListDTO>
	 * @author:徐凯强
	 * @throws Exception
	 * @Date:2014-7-23下午05:13:09
	 */
	public QueryResult<ArticleDTO> queryAll(int firstIndex, int pageNum, ArticleDTO articleDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return articleDAO.queryAll(firstIndex, pageNum, articleDTO, orderBy);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSaveDTO(ArticleDTO articleDTO) {
		/** 上传文件 **/
		String fileName = null;
		try {
			if (articleDTO != null) {
				if (articleDTO.getImageFile() != null && StringUtils.isNotBlank(articleDTO.getImageFileFileName())) {
					try {
						fileName = UploadUtil.uploadImg(articleDTO.getImageFile(), articleDTO.getImageFileFileName());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			Article article = new Article();
			article = (Article) EntityDtoConverter.dto2Bean(articleDTO, article);
			article.setThumbnailPath(fileName);
			this.save(article);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @MethodName:addReadTimes
	 * @Description:添加阅读次数
	 * @param articleDTO
	 * @author:徐半仙儿
	 * @date:2015-11-1下午08:43:21
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addReadTimes(ArticleDTO articleDTO) {
		try {
			Article article = new Article();
			article = (Article) EntityDtoConverter.dto2Bean(articleDTO, article);
			this.update(article);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @MethodName:queryArticleTypesAndCounts
	 * @Description:查询文章分类和分类下文章数量
	 * @author:徐半仙儿
	 * @return List<ArticleDTO>
	 * @date:2015-11-22上午12:16:22
	 */
	public List<ArticleDTO> queryArticleTypesAndCounts() {
		return articleDAO.queryArticleTypesAndCounts();
	}

	@Override
	public List<ArticleDTO> queryArticleDateAndCounts() {
		return articleDAO.queryArticleDateAndCounts();
	}

//	@Override
//	public List<ArticleDTO> queryByLucene(ArticleDTO articleDTO) {
//		return articleDAO.queryByLucene(articleDTO);
//	}
}
