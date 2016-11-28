package com.paySystem.ic.service.order.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.order.Orders;
import com.paySystem.ic.bean.order.ReMoReason;
import com.paySystem.ic.bean.order.ReMoney;
import com.paySystem.ic.dao.order.OrdersDAO;
import com.paySystem.ic.dao.order.ReMoneyDao;
import com.paySystem.ic.dao.order.ReMoneyReasonDao;
import com.paySystem.ic.service.order.ReMoneyReasonService;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.order.ReMoReasonDTO;

/**
 * @ProjectName: omproject
 * @ClassName: ReMoneyReasonServiceImpl
 * @Description: 退款Service实现
 * @date: 2014-11-12 下午05:40:22
 * @author: 王少辉
 * @version: V1.0
 */
@Service(ReMoneyReasonService.REMONEYREASONSERVICE)
public class ReMoneyReasonServiceImpl implements ReMoneyReasonService {

	/**
	 * 退款申请Dao
	 */
	@Resource
	private ReMoneyReasonDao reMoneyResonDao;
	
	/**
	 * 退款记录Dao
	 */
	@Resource
	private ReMoneyDao reMoneyDao;
	
	@Resource
	private OrdersDAO ordersDAO;

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.service.order.ReMoneyService#findById
	 *                        (int)
	 *@MethodName: findById
	 *@Description: 根据主键ID查询退款详细
	 *@Params: id 主键ID
	 *@Return: ReMoReasonDTO 返回退款详细
	 *@throws: Exception 抛出异常
	 *@Author: 王少辉
	 *@Date: 2014-11-12 下午06:05:23
	 */
	@Override
	public ReMoReasonDTO findById(int id) throws Exception {
		return reMoneyResonDao.findById(id);
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.service.order.ReMoneyService#queryAll
	 *                        (int, int,
	 *                        com.paySystem.ic.web.dto.order.ReMoReasonDTO,
	 *                        java.util.LinkedHashMap)
	 *@MethodName: queryAll
	 *@Description: 分页查询退款信息
	 *@Params: firstindex 分页信息第一条记录索引
	 *@Params: maxresult 分页信息每页记录数
	 *@Params: tankerTradesDTO 保存分页信息
	 *@Params: orderby 排序条件
	 *@Return: QueryResult<ReMoReasonDTO> 返回分页退款信息
	 *@throws: Exception 抛出异常
	 *@Author: 王少辉
	 *@Date: 2014-11-12 下午06:05:23
	 */
	@Override
	public QueryResult<ReMoReasonDTO> queryAll(int firstindex, int maxresult,
			ReMoReasonDTO reMoReasonDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		return reMoneyResonDao.queryAll(firstindex, maxresult, reMoReasonDTO,
				orderBy);
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.order.ReMoneyReasonService#auditSuccess()
	 *@MethodName: auditSuccess
	 *@Description: 审核通过，更新申请表审核状态并在申请记录表添加审核记录
	 *@Params: reMoReasonDTO 申请信息
	 *@Return: void
	 *@Author: 王少辉
	 *@Date: 2014-12-2 下午04:41:28
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void auditSuccess(ReMoReasonDTO reMoReasonDTO) throws Exception {
		// 添加审核记录
		ReMoney rm = new ReMoney();
		rm.setReMoReId(reMoReasonDTO.getReMoReId());
		rm.setOrderId(reMoReasonDTO.getOrderId());
		rm.setReturnMoney(reMoReasonDTO.getReturnMoney());
		rm.setReMoSta(2);
		rm.setActuReturnMoney(reMoReasonDTO.getActuReturnMoney());
		reMoneyDao.addReMoney(rm);
		
		// 审核通过，更新申请表审核状态
		reMoReasonDTO.setStatus(1);
		reMoReasonDTO.setUpdateTime(new Date());
		ReMoReason bean = new ReMoReason();
		bean = (ReMoReason) EntityDtoConverter.dto2Bean(reMoReasonDTO, bean);
		reMoneyResonDao.update(bean);
		
		// 审核通过，修改订单表状态
		Orders order = ordersDAO.find(reMoReasonDTO.getOrderId());
		if (null != order) {
			order.setStatus(9);
			ordersDAO.update(order);
		}
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.order.ReMoneyReasonService#auditFailure(com.paySystem.ic.web.dto.order.ReMoReasonDTO)
	 *@MethodName: auditFailure
	 *@Description: 审核拒绝，更新申请表审核状态并在申请记录表添加审核记录
	 *@Params: reMoReasonDTO 申请信息
	 *@Return: void
	 *@Author: 王少辉
	 *@Date: 2014-12-2 下午04:52:14
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void auditFailure(ReMoReasonDTO reMoReasonDTO) throws Exception {
		// 添加审核记录
		ReMoney rm = new ReMoney();
		rm.setReMoReId(reMoReasonDTO.getReMoReId());
		rm.setOrderId(reMoReasonDTO.getOrderId());
		rm.setReturnMoney(reMoReasonDTO.getReturnMoney());
		rm.setReMoSta(1);
		reMoneyDao.addReMoney(rm);
		
		// 更新申请表审核状态
		reMoReasonDTO.setStatus(2);
		reMoReasonDTO.setUpdateTime(new Date());
		ReMoReason bean = new ReMoReason();
		bean = (ReMoReason) EntityDtoConverter.dto2Bean(reMoReasonDTO, bean);
		reMoneyResonDao.update(bean);
	}

}
