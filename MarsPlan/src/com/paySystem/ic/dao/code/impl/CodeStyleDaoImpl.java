package com.paySystem.ic.dao.code.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.code.CodeStyle;
import com.paySystem.ic.dao.code.CodeStyleDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.web.dto.code.CodeStyleDTO;

/**
 * @ProjectName:MarsPlan
 * @ClassName:CodeStyleDaoImpl
 * @Description:代码分类Dao实现类
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:Mar 6, 20163:52:52 AM
 */
@Repository(CodeStyleDao.CODESTYLEDAO)
public class CodeStyleDaoImpl extends DaoSupport<CodeStyle> implements CodeStyleDao {

	/**
	 * @MethodName:queryAll
	 * @Description:后台查询所有代码分类
	 * @param firstIndex
	 * @param pageNum
	 * @param codeStyleDTO
	 * @param orderBy
	 * @throws Exception
	 * @author:徐凯强
	 * @date:Mar 6, 20163:55:13 AM
	 */
	public QueryResult<CodeStyle> queryAll(int firstIndex, int pageNum, CodeStyleDTO codeStyleDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		StringBuffer wherejpql = new StringBuffer();
		List<Object> queryParams = new ArrayList<Object>();
		/** 模糊查询 */
		// if (codeDTO.getName() != null && !codeDTO.getName().equals("")) {
		// wherejpql.append(" and o.name like ?");
		// queryParams.add("%" + codeDTO.getName() + "%");
		// }
		// if (codeDTO.getTypeId() != null && !codeDTO.getTypeId().equals("")) {
		// wherejpql.append(" and o.typeId=" + codeDTO.getTypeId());
		// }
		//
		// wherejpql.append(" and o.status!=9");
		// wherejpql.append(" and o.user.userId="+us.getUserId());
		// if (serviceListDTO.getServType() != null
		// && serviceListDTO.getServType() != -1) {
		// wherejpql.append(" and o.servType=?");
		// queryParams.add(serviceListDTO.getServType());
		// }

		QueryResult<CodeStyle> queryResult = this.getScrollData(firstIndex, pageNum, wherejpql.toString(),
				queryParams.toArray(), orderBy);
		return queryResult;
	}

}
