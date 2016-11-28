package com.paySystem.ic.service.report.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.report.ITrades;
import com.paySystem.ic.dao.report.ITradesDAO;
import com.paySystem.ic.service.report.ITradesService;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.report.ITradesDTO;

/**
 * @ProjectName:omallproject
 * @ClassName:ITradesServiceImpl
 * @Description:TODO
 * @date: 2014-11-13
 * @author: 毛智东
 * @version: V1.0
 */
@Service(ITradesService.ITRADESSERVICE)
public class ITradesServiceImpl implements ITradesService {

	@Resource
	ITradesDAO iTradesDAO;

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.service.report.ITradesService#findById
	 *                        (java.lang.Integer)
	 *@MethodName:findById
	 *@Description:根据主键查询dto
	 *@Params:@param tradesId 主键：流水号
	 *@Params:@return
	 *@Params:@throws Exception 抛出异常
	 *@Return:ITradesDTO 返回dto
	 *@author:毛智东
	 *@Date:2014-11-13上午09:42:20
	 */
	@Override
	public ITradesDTO findById(Integer tradesId) throws Exception {
		ITradesDTO dto = new ITradesDTO();
		ITrades bean = iTradesDAO.find(tradesId);
		dto = (ITradesDTO) EntityDtoConverter.bean2Dto(bean, dto);
		return dto;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.service.report.ITradesService#queryAll
	 *                        (int, int,
	 *                        com.paySystem.ic.web.dto.report.ITradesDTO,
	 *                        java.util.LinkedHashMap)
	 *@MethodName:queryAll
	 *@Description:分页查询消费积分
	 *@param： firstindex 当前页索引
	 *@Params:@param maxresult 每页记录数
	 *@Params:@param bTradesDTO 参数保存dto
	 *@Params:@param orderby 排序
	 *@return
	 *@Params:@throws Exception 抛出异常
	 *@Author:毛智东
	 *@Date:2014-11-13上午09:42:20
	 */
	@Override
	public QueryResult<ITradesDTO> queryAll(int firstindex, int maxresult,
			ITradesDTO iTradesDTO, LinkedHashMap<String, String> orderby)
			throws Exception {
		return iTradesDAO.queryAll(firstindex, maxresult, iTradesDTO, orderby);
	}

}
