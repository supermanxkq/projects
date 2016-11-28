package com.paySystem.ic.dao.software.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.software.SoftWareStyle;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.software.SoftWareStyleDao;
import com.paySystem.ic.web.dto.software.SoftWareStyleDTO;

/**
 * @ProjectName:MarsPlan
 * @ClassName:SoftWareStyleDaoImpl
 * @Description:软件分类Dao实现类
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:Mar 9, 20168:34:49 PM
 */
@Repository(SoftWareStyleDao.SOFTWARESTYLEDAO)
public class SoftWareStyleDaoImpl extends DaoSupport<SoftWareStyle> implements SoftWareStyleDao {

	/**
	 * @MethodName:queryAll
	 * @Description:后台查询所有的软件分类
	 * @param firstIndex
	 * @param pageNum
	 * @param softWareStyleDTO
	 * @param orderBy
	 * @throws Exception
	 * @author:徐凯强
	 * @return QueryResult<SoftWareStyle>
	 * @date:Mar 9, 20168:36:19 PM
	 */
	public QueryResult<SoftWareStyle> queryAll(int firstIndex, int pageNum, SoftWareStyleDTO softWareStyleDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		StringBuffer wherejpql = new StringBuffer();
		List<Object> queryParams = new ArrayList<Object>();

		QueryResult<SoftWareStyle> queryResult = this.getScrollData(firstIndex, pageNum, wherejpql.toString(),
				queryParams.toArray(), orderBy);
		return queryResult;
	}

}
