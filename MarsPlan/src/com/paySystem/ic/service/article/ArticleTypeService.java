package com.paySystem.ic.service.article;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.article.ArticleType;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.article.ArticleTypeDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

public interface ArticleTypeService extends DAO<ArticleType> {

	/** 常量 */
	public static final String ARTICLETYPESERVICE = "ArticleTypeService";

	public QueryResult<ArticleType> queryAll() throws Exception;

	public QueryResult<ArticleTypeDTO> queryAll(int firstIndex, int pageNum, ArticleTypeDTO articleTypeDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;

	public void addSaveDTO(ArticleTypeDTO articleTypeDTO);

	public List<OptionsInteger> getAllTypes();
}
