package com.blog.service.blogtype.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.blog.bean.QueryResult;
import com.blog.bean.article.Article;
import com.blog.bean.blogtype.BlogType;
import com.blog.dao.blogtype.BlogTypeDAO;
import com.blog.dao.common.DaoSupport;
import com.blog.dto.blogtype.BlogTypeDTO;
import com.blog.service.blogtype.BlogTypeService;
import com.blog.util.EntityDtoConverter;

/**
 * @ProjectName:omall
 * @ClassName:MessServParamConfigServiceImpl
 * @Description:TODO
 * @date: 2014-7-22下午02:58:05
 * @author: 徐凯强
 * @version: V1.0
 */
@Service(BlogTypeService.BLOGTYPESERVICE)
public class BlogTypeServiceImpl extends DaoSupport<BlogType> implements
		BlogTypeService {

	@Resource
	private BlogTypeDAO blogTypeDAO;

	/**
	 *@Title:updateServiceList
	 *@Description:更新
	 *@param serviceListDTO
	 *@Return:void
	 *@author:徐凯强
	 *@Date:2014-7-22下午02:18:09
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateServiceList(BlogTypeDTO blogTypeDTO) {
		try {
			Article article = new Article();
			article = (Article) EntityDtoConverter.dto2Bean(blogTypeDTO,
					article);
			this.update(article);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 *@Title:querAll
	 *@Description:分页查找
	 *@param firstIndex
	 *@param pageNum
	 *@param serviceListDTO
	 *@param orderBy
	 *@Return:QueryResult<ServiceListDTO>
	 *@author:徐凯强
	 * @throws Exception
	 *@Date:2014-7-23下午05:13:09
	 */
	public QueryResult<BlogTypeDTO> queryAll(int firstIndex, int pageNum,
			BlogTypeDTO blogTypeDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		return blogTypeDAO.queryAll(firstIndex, pageNum, blogTypeDTO, orderBy);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSaveDTO(BlogTypeDTO blogTypeDTO) {
		try {
			BlogType blogType = new BlogType();
			blogType = (BlogType) EntityDtoConverter.dto2Bean(blogTypeDTO,
					blogType);
			this.save(blogType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Map<Integer,String> getAllTypes(){
		return blogTypeDAO.getAllTypes();
	}
}
