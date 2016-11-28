package com.blog.service.code.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.blog.bean.QueryResult;
import com.blog.bean.code.Code;
import com.blog.dao.code.CodeDAO;
import com.blog.dao.common.DaoSupport;
import com.blog.dto.code.CodeDTO;
import com.blog.service.code.CodeService;

@Service(CodeService.CODESERVICE)
public class CodeServiceImpl extends DaoSupport<Code> implements
		CodeService {

	@Resource
	private CodeDAO  codeDao;


	public QueryResult<Code> queryAll(int firstIndex, int pageNum,
			CodeDTO codeDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		return codeDao.queryAll(firstIndex, pageNum, codeDTO, orderBy);
	}

}
