package com.paySystem.ic.service.article.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.article.ArticleType;
import com.paySystem.ic.bean.code.CodeStyle;
import com.paySystem.ic.dao.article.ArticleTypeDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.service.article.ArticleTypeService;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.article.ArticleTypeDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ProjectName:omall
 * @ClassName:MessServParamConfigServiceImpl
 * @Description:TODO
 * @date: 2014-7-22下午02:58:05
 * @author: 徐凯强
 * @version: V1.0
 */
@Service(ArticleTypeService.ARTICLETYPESERVICE)
public class ArticleTypeServiceImpl extends DaoSupport<ArticleType> implements ArticleTypeService {

	@Resource
	private ArticleTypeDAO articleTypeDAO;

	public QueryResult<ArticleType> queryAll() throws Exception {
		return this.getScrollData();
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
	public QueryResult<ArticleTypeDTO> queryAll(int firstIndex, int pageNum, ArticleTypeDTO articleTypeDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return articleTypeDAO.queryAll(firstIndex, pageNum, articleTypeDTO, orderBy);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSaveDTO(ArticleTypeDTO articleTypeDTO) {
		try {
			ArticleType articleType = new ArticleType();
			articleType = (ArticleType) EntityDtoConverter.dto2Bean(articleTypeDTO, articleType);
			this.save(articleType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @MethodName:getAllTypes
	 * @Description:获取所有的类型
	 * @author:徐凯强
	 * @date:Mar 10, 20169:45:28 PM
	 */
	public List<OptionsInteger> getAllTypes() {
		List<OptionsInteger> list = new ArrayList<>();
		try {
			StringBuffer wherejpql = new StringBuffer();
			List<Object> queryParams = new ArrayList<>();
			QueryResult<ArticleType> queryResult = this.getScrollData(-1, -1, wherejpql.toString(),
					queryParams.toArray());
			for (ArticleType articleType : queryResult.getResultlist()) {
				list.add(new OptionsInteger(articleType.getId(), articleType.getName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
