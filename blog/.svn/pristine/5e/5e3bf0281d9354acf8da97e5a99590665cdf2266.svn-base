package com.blog.service.linktype;

import com.blog.bean.QueryResult;
import com.blog.bean.links.LinkType;
import com.blog.dao.common.DAO;
import com.blog.dto.link.LinkTypeDTO;

/**
 * @ProjectName:blog
 * @ClassName:LinkTypeService
 * @Description:链接类型服务类
 * @date: 2015-7-17下午09:29:36
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-7-17下午09:29:36
 */
public interface LinkTypeService extends DAO<LinkType> {
	/** 常量 */
	public static final String LINKTYPESERVICE = "LinkTypeService";

	/**
	 *@MethodName:queryAll
	 *@Description:查询所有的链接类型信息
	 *@throws Exception
	 *@author:徐半仙儿
	 *@return QueryResult<LinkDTO>
	 *@date:2015-7-17下午09:30:24
	 */
	public QueryResult<LinkTypeDTO> queryAll()
			throws Exception;

	/**
	 *@MethodName:addSaveDTO 
	 *@Description:添加类型
	 *@param linkTypeDTO 
	 *@author:徐半仙儿
	 *@return void
	 *@date:2015-7-18下午02:13:58
	 */
	public void addSaveDTO(LinkTypeDTO linkTypeDTO);
}
