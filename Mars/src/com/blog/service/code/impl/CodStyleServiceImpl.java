package com.blog.service.code.impl;

import org.springframework.stereotype.Service;

import com.blog.bean.QueryResult;
import com.blog.bean.code.CodeStyle;
import com.blog.dao.common.DaoSupport;
import com.blog.service.code.CodeStyleService;

@Service(CodeStyleService.CODESTYLESERVICE)
public class CodStyleServiceImpl extends DaoSupport<CodeStyle> implements CodeStyleService {

	public QueryResult<CodeStyle> queryAll() throws Exception {
		return this.getScrollData();
	}

}
