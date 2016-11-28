package com.paySystem.ic.service.base.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.base.UndealServiceNum;
import com.paySystem.ic.dao.base.UndealServiceNumDAO;
import com.paySystem.ic.service.base.UndealServiceNumService;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.web.dto.base.UndealServiceNumDTO;

/**
 * @ProjectName:omall
 * @ClassName:UndealServiceNumServiceImpl
 * @Description:商城未处理业务查看的Service实现类
 * @date: 2014-10-22
 * @author: 王楠
 * @version: V1.0
 */
@Service(UndealServiceNumService.UNDEALSERVICENUMSERVICE)
public class UndealServiceNumServiceImpl extends DaoSupport<UndealServiceNum>
                                        implements UndealServiceNumService{
	
	@Resource
	UndealServiceNumDAO undealServiceNumDAO;

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.base.UndealServiceNumService#findList()
	 *@MethodName:findList
	 *@Description:查询数据的方法
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-10-22下午06:01:01
	 */
	public UndealServiceNumDTO findList(String merId) throws Exception {
		return undealServiceNumDAO.findList(merId);
	}
	
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.base.UndealServiceNumService#findTotalData(java.lang.String)
	 *@MethodName:findTotalData
	 *@Description:用于机构登陆时查找数据的方法
	 *@param organId 机构编号
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-11-25上午11:36:57
	 */
	public UndealServiceNumDTO findTotalData(String organId) throws Exception {
		return undealServiceNumDAO.findTotalData(organId);
	}

	/**
	 *@MethodName:findByOrgMerId
	 *@Description:通过机构和商户号查询，初始化用
	 *@param sECURITYKEY
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-12-13下午03:02:00
	 */
	public UndealServiceNum findByOrgMerId(String orgMerId) {
		// TODO Auto-generated method stub
		return undealServiceNumDAO.findByOrgMerId(orgMerId);
	}

}
