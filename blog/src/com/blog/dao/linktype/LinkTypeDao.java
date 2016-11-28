package com.blog.dao.linktype;

import java.util.LinkedHashMap;

import com.blog.bean.QueryResult;
import com.blog.dto.link.LinkDTO;
import com.blog.dto.link.LinkTypeDTO;

/**
 * @ProjectName:blog 
 * @ClassName:LinkDao  
 * @Description:链接数据操作接口
 * @date: 2015-7-17下午09:58:46
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-7-17下午09:58:46
 */
public interface LinkTypeDao {
	/** 常量 */
	public static final String LINKTYPEDAO = "LinkTypeDao";

	/**
	 *@MethodName:queryAll
	 *@Description:查询所有的链接信息
	 *@throws Exception
	 *@author:徐半仙儿
	 *@return QueryResult<LinkDTO>
	 *@date:2015-7-17下午09:30:24
	 */
	public QueryResult<LinkTypeDTO> queryAll()
			throws Exception;

}
