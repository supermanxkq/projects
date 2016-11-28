package com.paySystem.ic.service.code.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.code.CodeStyle;
import com.paySystem.ic.dao.code.CodeStyleDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.service.code.CodeStyleService;
import com.paySystem.ic.web.dto.code.CodeStyleDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ProjectName:MarsPlan
 * @ClassName:CodStyleServiceImpl
 * @Description:代码分类服务类实现
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:Mar 6, 20163:48:08 AM
 */
@Service(CodeStyleService.CODESTYLESERVICE)
public class CodeStyleServiceImpl extends DaoSupport<CodeStyle> implements CodeStyleService {

	@Resource
	private CodeStyleDao codeStyleDao;

	/**
	 * @MethodName:queryAll
	 * @Description:前台获取所有代码分类
	 * @throws Exception
	 * @author:徐凯强
	 * @date:Mar 6, 20163:48:32 AM
	 */
	public QueryResult<CodeStyle> queryAll() throws Exception {
		return this.getScrollData();
	}

	/**
	 * @MethodName:queryAll
	 * @Description:后台获取所有代码分类
	 * @param firstIndex
	 * @param pageNum
	 * @param codeStyleDTO
	 * @param orderBy
	 * @throws Exception
	 * @author:徐凯强
	 * @date:Mar 6, 20163:48:54 AM
	 */
	@Override
	public QueryResult<CodeStyle> queryAll(int firstIndex, int pageNum, CodeStyleDTO codeStyleDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return codeStyleDao.queryAll(firstIndex, pageNum, codeStyleDTO, orderBy);
	}

	/**
	 * @MethodName:addSave
	 * @Description:后台添加分类信息
	 * @param codeStyle
	 * @author:徐凯强
	 * @date:Mar 6, 20164:51:13 AM
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSave(CodeStyle codeStyle) {
		this.save(codeStyle);
	}

	/**
	 * @MethodName:queryStyles
	 * @Description:后台添加代码时候的代码类型
	 * @author:徐凯强
	 * @date:Mar 6, 20165:51:33 AM
	 */
	@Override
	public List<OptionsInteger> queryStyles() {
		List<OptionsInteger> list = new ArrayList<>();
		try {
			StringBuffer wherejpql = new StringBuffer();
			List<Object> queryParams = new ArrayList<>();
			QueryResult<CodeStyle> queryResult = this.getScrollData(-1, -1, wherejpql.toString(),
					queryParams.toArray());
			for (CodeStyle codeStyle : queryResult.getResultlist()) {
				list.add(new OptionsInteger(codeStyle.getId(), codeStyle.getName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
