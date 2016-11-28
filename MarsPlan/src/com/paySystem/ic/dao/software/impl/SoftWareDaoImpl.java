package com.paySystem.ic.dao.software.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.software.SoftWare;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.software.SoftWareDao;
import com.paySystem.ic.web.dto.software.SoftWareDTO;

@Repository(SoftWareDao.SOFTWAREDAO)
public class SoftWareDaoImpl extends DaoSupport<SoftWare> implements SoftWareDao {

	public QueryResult<SoftWare> queryAll(int firstIndex, int pageNum, SoftWareDTO softWareDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		StringBuffer wherejpql = new StringBuffer();
		List<Object> queryParams = new ArrayList<Object>();
		/** 模糊查询 */
		if (softWareDTO.getName() != null && !softWareDTO.getName().equals("")) {
			wherejpql.append(" and o.name like ?");
			queryParams.add("%" + softWareDTO.getName() + "%");
		}
		if (softWareDTO.getTypeId() != null && !softWareDTO.getTypeId().equals("") && softWareDTO.getTypeId() != -1) {
			wherejpql.append(" and o.typeId=" + softWareDTO.getTypeId());
		}

		QueryResult<SoftWare> queryResult = this.getScrollData(firstIndex, pageNum, wherejpql.toString(),
				queryParams.toArray(), orderBy);
		return queryResult;
	}

}
