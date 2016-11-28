package com.blog.dao.profile;

import com.blog.bean.QueryResult;
import com.blog.bean.profile.Profile;
import com.blog.dao.common.DAO;
import com.blog.dto.profile.ProfileDTO;

//package com.blog.dao.profile;
//
//import java.util.LinkedHashMap;
//
//import com.paySystem.ic.bean.QueryResult;
//import com.paySystem.ic.bean.stock.ModStock;
//import com.paySystem.ic.bean.stock.StockInfo;
//import com.paySystem.ic.dao.common.DAO;
//import com.paySystem.ic.web.dto.ReturnDTO;
//import com.paySystem.ic.web.dto.stock.ModStockDTO;
//import com.paySystem.ic.web.dto.stock.StockInfoDTO;
//
///**
// *@ClassName:StockInfoDao
// *@Description:库存信息DAO接口
// *@Date:2013-12-15下午04:40:25
// *@Author:谢工
// *@Version: V1.0
// */
public interface ProfileDao extends DAO<Profile> {
	public static final String PROFILEDAO="ProfileDao";
	
	
	public QueryResult<ProfileDTO> findProfiles();
//
//	/**
//	 *@Title:checkExistStockInfo
//	 *@Description:根据机构编号/商户编号查看是否已经存在
//	 *@Param:@param deptId1 -第一个机构/商户号
//	 *@Param:@param deptId2 -第二个机构/商户号
//	 *@param:@param flag -参数标志： 0-deptId1机构号，deptId2机构号 1-deptId1机构号，deptId2商户号
//	 *               2-deptId1商户号，deptId2商户号
//	 *@Param:@return
//	 *@Return:boolean
//	 *@author 谢
//	 *@Throws:
//	 */
//	boolean checkExistStockInfo(String deptId1, String deptId2,
//			String cardBinNo, int flag);
//
//	/**
//	 *@Title:saveStockInfo
//	 *@Description:新增库存信息
//	 *@Param:@param modStockDTO
//	 *@param:@param flag 0-机构，1-商户
//	 *@param:@param outinSign 0-出库，1-入库
//	 *@Param:@return
//	 *@Return:ReturnDTO
//	 *@Throws:
//	 */
//	ReturnDTO saveStockInfo(ModStock modStock) throws Exception;
//
//	public static final String STOCKINFODAO = "stockInfoDao";
//
//	/**
//	 * @Title: updateStockByBinForSingleSale
//	 * @Description: 为单张收卡功能提供修改库存信息的方法接口
//	 * @param @param substring
//	 * @return void
//	 * @throws
//	 * @author lily
//	 * @date 2013-12-24 下午06:22:52
//	 */
//	public void updateStockByBinForSingleSale(String bin);
//
//	/**
//	 * @Title: findByBin
//	 * @Description: 根据卡BIN查询库存信息的方法接口
//	 * @param @param bin
//	 * @param @return
//	 * @return StockInfoDTO
//	 * @throws
//	 * @author lily
//	 * @date 2013-12-24 下午06:25:50
//	 */
//	public StockInfoDTO findByBin(String bin);
//	/**
//	 * @Title: updateStockInfoDTO
//	 * @Description: 通过StockInfoDTO保存StockInfo实体的方法接口
//	 * @param @param stockInfoDTO
//	 * @return void
//	 * @throws
//	 * @author lily
//	 * @date 2013-12-24 下午06:59:29
//	 */
//	public void updateStockInfoDTO(StockInfoDTO stockInfoDTO);
//
//	/**
//	 * 
//	 *@Title:findByCardBin
//	 *@TODO:通过卡bin查找实体
//	 *@data:2014-4-12
//	 *@return:StockInfo
//	 *@author:孟凡岭
//	 *@thorws:
//	 */
//	StockInfo findByCardBin(String cardBin);
//	
//	/**
//	 *@Title:merStockSureIn
//	 *@Description:商户领卡：确认领卡--库存信息维护
//	 *@param:@param modStockDTO 库存变动DTO
//	 *@return: void
//	 *@author:谢洪飞
//	 */
//	public void merStockSureIn(ModStockDTO modStockDTO);
//
//	
//	/**
//	 *@Title:
//	 *       queryStockByCond
//	 *@Description:
//	 *       根据条件获取库存信息
//	 *@param:
//	 *       @param firstindex 起始记录数
//	 *@param:
//	 *       @param maxresult 每页最大条数
//	 *@param:
//	 *       @param stockInfoDTO 库存信息DTO
//	 *@param:
//	 *       @param orderby 排序
//	 *@param:
//	 *       @return 
//	 *@param:
//	 *       @throws Exception
//	 *@return:
//	 *       QueryResult<StockInfo> 查询结果
//	 *@author:
//	 *       谢洪飞
//	 */
//	QueryResult<StockInfo> queryStockByCond(int firstindex,
//			int maxresult, StockInfoDTO stockInfoDTO,
//			LinkedHashMap<String, String> orderby) throws Exception;
//	
//	/**
//	 *@Title:queryStockInfoById
//	 *@Description:根据Id获取StockInfoDTO信息
//	 *@param:@param stockInfoId
//	 *@param:@return
//	 *@return:StockInfoDTO
//	 *@author:谢洪飞
//	 *@Thorws:
//	 */
//	StockInfoDTO queryStockInfoById(Integer stockInfoId);
}
