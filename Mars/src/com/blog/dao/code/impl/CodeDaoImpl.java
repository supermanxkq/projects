package com.blog.dao.code.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.blog.bean.QueryResult;
import com.blog.bean.code.Code;
import com.blog.dao.code.CodeDAO;
import com.blog.dao.common.DaoSupport;
import com.blog.dto.code.CodeDTO;

@Repository(CodeDAO.CODEDAO)
public class CodeDaoImpl extends DaoSupport<Code> implements CodeDAO {

	public QueryResult<Code> queryAll(int firstIndex, int pageNum,
			CodeDTO codeDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		StringBuffer wherejpql = new StringBuffer();
		List<Object> queryParams = new ArrayList<Object>();
		/** 模糊查询 */
		/** 根据博客的标题进行模糊查询 */
		if (codeDTO.getName() != null && !codeDTO.getName().equals("")) {
			wherejpql.append(" and o.name like ?");
			queryParams.add("%" + codeDTO.getName() + "%");
		}
		/** 根据博客的分类进行查询 */
		 if (codeDTO.getTypeId() != null
		 && !codeDTO.getTypeId().equals("")) {
		 wherejpql.append(" and o.typeId="+codeDTO.getTypeId());
		 }
//
//		wherejpql.append(" and o.status!=9");
		// wherejpql.append(" and o.user.userId="+us.getUserId());
		// if (serviceListDTO.getServType() != null
		// && serviceListDTO.getServType() != -1) {
		// wherejpql.append(" and o.servType=?");
		// queryParams.add(serviceListDTO.getServType());
		// }
		
		QueryResult<Code> queryResult = this.getScrollData(firstIndex,
				pageNum, wherejpql.toString(), queryParams.toArray(), orderBy);
		return queryResult;
	}

}
