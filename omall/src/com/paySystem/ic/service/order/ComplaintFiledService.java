package com.paySystem.ic.service.order;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.order.ComplaintFiled;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.order.ComplaintFiledDTO;
import com.paySystem.ic.web.dto.order.ComplaintHandleDTO;

/**
 * @ProjectName:omallBackstage
 * @ClassName:ComplaintFileService
 * @Description:投诉后台处理Service接口
 * @date: 2014-11-19
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface ComplaintFiledService {
	public static final String COMPLAINTFILEDSERVICE="complaintFiledService";

	/**
	*@Title:queryResult
	*@Description:分页查询
	*@Params:@param i
	*@Params:@param pageNum
	*@Params:@param complaintFiledDTO
	*@Params:@param orderby
	*@Params:@return
	*@Return:QueryResult<ComplaintFiledDTO>
	*@author:孟凡岭
	*@Date:2014-11-19上午11:58:27
	*/
	QueryResult<ComplaintFiledDTO> queryResult(int i, int pageNum,
			ComplaintFiledDTO complaintFiledDTO,
			LinkedHashMap<String, String> orderby) throws Exception;

	/**
	*@Title:loadData
	*@Description:查询详情
	*@Params:@param complaintFiledDTO
	*@Params:@return
	*@Return:ComplaintHandleDTO
	*@author:孟凡岭
	*@Date:2014-11-20上午11:57:06
	*/
	ComplaintHandleDTO loadComplaint(ComplaintFiledDTO complaintFiledDTO) throws Exception;

	/**
	*@Title:updateCom
	*@Description:处理投诉
	*@Params:@param complaintHandleDTO
	*@Params:@return
	*@Return:boolean
	*@author:孟凡岭
	*@Date:2014-11-21下午02:42:01
	*/
	boolean updateCom(ComplaintHandleDTO complaintHandleDTO) throws Exception;

	/**
	*@Title:appoint
	*@Description:指派给商家处理
	*@Params:@param filedId
	*@Return:void
	*@author:孟凡岭
	*@Date:2014-11-24上午11:27:55
	*/
	void appoint(Integer filedId) throws Exception ;

	/**
	*@Title:businessAppeal
	*@Description:TODO
	*@Params:@param complaintHandleDTO
	*@Params:@return
	*@Return:boolean
	*@author:孟凡岭
	*@Date:2014-11-24下午12:06:55
	*/
	boolean businessAppeal(ComplaintHandleDTO complaintHandleDTO) throws Exception;
}
