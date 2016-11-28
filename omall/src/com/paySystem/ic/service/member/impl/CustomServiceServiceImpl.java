/**  
 * @Title: CustomServiceServiceImpl.java
 * @Package: com.paySystem.ic.service.member.impl
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-11-13 下午03:49:01
 * @Version: V1.0  
 */
package com.paySystem.ic.service.member.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.CustomService;
import com.paySystem.ic.dao.member.CustomServiceDAO;
import com.paySystem.ic.dao.member.impl.CustomServiceDAOImpl;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.member.CustomServiceService;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.member.CustomServiceDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ProjectName:omal
 * @ClassName:CustomServiceServiceImpl
 * @Description:客服管理Service接口实现类
 * @date: 2014-11-13
 * @author: 孟凡岭
 * @version: V1.0
 */
@Service(CustomServiceService.CUSTOMSERVICESERVICE)
public class CustomServiceServiceImpl extends DaoSupport<CustomService>
		implements CustomServiceService {
	@Resource
	private CustomServiceDAO customServiceDAO = new CustomServiceDAOImpl();

	public CustomServiceDAO getCustomServiceDAO() {
		return customServiceDAO;
	}

	public void setCustomServiceDAO(CustomServiceDAO customServiceDAO) {
		this.customServiceDAO = customServiceDAO;
	}

	/**
	 *@MethodName:findByStoreId
	 *@Description:根据商户号查询出相关客服信息
	 *@param storeId
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-11-13下午04:22:36
	 */
	public List<CustomServiceDTO> findByStoreId(String storeId)
			throws Exception {
		// TODO Auto-generated method stub
		return customServiceDAO.findByStoreId(storeId);
	}

	/**
	 *@MethodName:queryResult
	 *@Description:分页查询
	 *@param first
	 *@param pageNum
	 *@param customServiceDTO
	 *@param orderby
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-11-17下午02:05:02
	 */
	public QueryResult<CustomServiceDTO> queryResult(int first, int pageNum,
			CustomServiceDTO customServiceDTO,
			LinkedHashMap<String, String> orderby) throws Exception {
		// TODO Auto-generated method stub
		return customServiceDAO.queryResult(first, pageNum, customServiceDTO,
				orderby);
	}

	/**
	 *@MethodName:update
	 *@Description:更新
	 *@param customServiceDTO
	 *@Author:孟凡岭
	 *@Date:2014-11-17下午05:42:35
	 */
	@Transactional
	public void update(CustomServiceDTO customServiceDTO) {
		// TODO Auto-generated method stub
		customServiceDAO.update(customServiceDTO);
	}

	/**
	 *@MethodName:add
	 *@Description:数据
	 *@param customServiceDTO
	 *@Author:孟凡岭
	 *@Date:2014-11-17下午05:54:06
	 */
	@Transactional
	public void add(CustomServiceDTO customServiceDTO) throws Exception {
		// TODO Auto-generated method stub
		
		customServiceDAO.add(customServiceDTO);
	}

	/**
	 *@MethodName:findById
	 *@Description:加载单条数据
	 *@param id
	 *@Author:孟凡岭
	 *@Date:2014-11-17下午06:38:55
	 */
	public CustomServiceDTO findById(long id) throws Exception{
		// TODO Auto-generated method stub
		return customServiceDAO.findById(id);
	}

	/**
	 *@MethodName:delete
	 *@Description:删除数据
	 *@param id
	 *@Author:孟凡岭
	 *@Date:2014-11-18上午09:52:38
	 */
	@Transactional
	public void delete(String id) {
		// TODO Auto-generated method stub
		customServiceDAO.deleteData(id);
	}

}
