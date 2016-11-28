package com.paySystem.ic.dao.order;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.buss.Promotion;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.order.OrderAndGoodsQueryDTO;


/**
 * @ClassName:OrderDao
 * @Description:订单管理Dao
 * @date: 2014-10-10上午10:46:46
 * @author: 赵瑞群
 * @version: V1.0
 */
public interface OrderDao extends DAO<Promotion> {
	 
	
	/**
	 *@Title:queryPromotionByCond
	 *@Description:根据条件查询订单管理信息列表
	 *@param:@param firstPage 开始条数
	 *@param:@param pageNum   每页显示调试
	 *@param:@param promotionDTO PromotionDTO对象
	 *@param:@param orderBy   排序方式
	 *@param:@return
	 *@return:List<PaySerParamDTO> 返回DTO集合
	 *@author:  赵瑞群
	 *@Thorws:
	 */
	public QueryResult<List> queryOrderList(int firstPage, int pageNum,
			OrderAndGoodsQueryDTO orderAndGoodsQueryDTO, LinkedHashMap<String, String> orderBy)throws Exception;
	
	
	public static final String ORDERDAO ="orderDao";

	
}
