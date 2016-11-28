package com.paySystem.ic.dao.member;

import com.paySystem.ic.bean.member.MemCardConn;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.member.MemCardConnDTO;

/**
 * @ProjectName:omall
 * @ClassName:MemCardConnDAO
 * @Description:会员与卡关联表DAO接口
 * @date: 2014-11-24
 * @author: 毛智东
 * @version: V1.0
 */
public interface MemCardConnDAO extends DAO<MemCardConn> {

	public static final String MEMCARDCONNDAO = "memCardConnDao";

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


}
