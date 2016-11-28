package com.paySystem.ic.dao.order;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.order.ComplaintFiled;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.order.ComplaintFiledDTO;
import com.paySystem.ic.web.dto.order.ComplaintHandleDTO;

/**
 * @ProjectName:omallBackstage
 * @ClassName:ComplaintFileDao
 * @Description:投诉后台处理dao接口
 * @date: 2014-11-19
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface ComplaintFiledDao extends DAO<ComplaintFiled> {
	public static final String COMPLAINTFILEDDAO="complaintFiledDao";

	/**
	*@Title:queryResult
	*@Description:分页查询
	*@Params:@param first
	*@Params:@param pageNum
	*@Params:@param complaintFiledDTO
	*@Params:@param orderby
	*@Params:@return
	*@Return:QueryResult<ComplaintFiledDTO>
	*@author:孟凡岭
	*@Date:2014-11-19下午12:00:03
	*/
	QueryResult<ComplaintFiledDTO> queryResult(int first, int pageNum,
			ComplaintFiledDTO complaintFiledDTO,
			LinkedHashMap<String, String> orderby) throws Exception;

	/**
	*@Title:loadData
	*@Description:查询详情
	*@Params:@param complaintFiledDTO
	*@Params:@return
	*@Return:ComplaintHandleDTO
	*@author:孟凡岭
	*@Date:2014-11-20上午11:57:51
	*/
	ComplaintHandleDTO loadComplaint(ComplaintFiledDTO complaintFiledDTO) throws Exception;
}
