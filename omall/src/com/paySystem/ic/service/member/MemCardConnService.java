package com.paySystem.ic.service.member;

import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemCardConnDTO;

/**
 * @ProjectName:omall
 * @ClassName:MemCardConnService
 * @Description:会员与卡关联表Service接口
 * @date: 2014-11-24
 * @author: 毛智东
 * @version: V1.0
 */
public interface MemCardConnService {

	public static final String MEMCARDCONNSERVICE = "memCardConnService";

	/**
	 * 
	 *@Title:findByMemId
	 *@Description:根据会员编号查找对象
	 *@Params:@param memId
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:MemCardConnDTO
	 *@author:毛智东
	 *@Date:2014-11-24下午03:10:47
	 */
	public MemCardConnDTO findByMemId(Integer memId) throws Exception;

	/**
	 * 
	 *@Title:findById
	 *@Description:根据id查找对象
	 *@Params:@param connId
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:MemCardConnDTO
	 *@author:毛智东
	 *@Date:2014-11-24下午03:25:00
	 */
	public MemCardConnDTO findById(Integer connId) throws Exception;

	/**
	 * 
	 *@Title:add
	 *@Description:添加方法
	 *@Params:@param memCardConnDTO
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:ReturnDTO
	 *@author:毛智东
	 *@Date:2014-11-24下午03:26:01
	 */
	public ReturnDTO add(MemCardConnDTO memCardConnDTO) throws Exception;

}
