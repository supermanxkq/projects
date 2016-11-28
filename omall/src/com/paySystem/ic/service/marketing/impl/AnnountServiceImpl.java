/**  
 * @Title: AnnountServiceImpl.java
 * @Package: com.paySystem.ic.service.marketing.impl
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-9-9 下午03:05:15
 * @Version: V1.0  
 */
package com.paySystem.ic.service.marketing.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.marketing.Annount;
import com.paySystem.ic.dao.marketing.AnnountDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.marketing.AnnountService;
import com.paySystem.ic.web.dto.marketing.AnnountDTO;

/**
 * @ProjectName:omall20140905
 * @ClassName:AnnountServiceImpl
 * @Description:全站公告管理service实现类
 * @date: 2014-9-9
 * @author: 孙晓磊
 * @version: V1.0
 */
@Service(AnnountService.ANNOUNTSERVICE)
public class AnnountServiceImpl extends DaoSupport<Annount> implements
		AnnountService {

	@Resource
	private AnnountDAO annountDao;

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.service.marketing.AnnountService#queryResult
	 *                        (int, int,
	 *                        com.paySystem.ic.web.dto.marketing.AnnountDTO,
	 *                        java.util.LinkedHashMap)
	 *@MethodName:queryResult
	 *@Description:查询方法
	 *@param page
	 *            当前页
	 *@param pageNum
	 *            每页显示的条数
	 *@param annountDTO
	 *            全站公告管理 DTO
	 *@param orderby
	 *            条件
	 *@return
	 *@throws Exception
	 *@Author:孙晓磊
	 *@Date:2014-9-9下午03:13:59
	 */
	@SuppressWarnings("unchecked")
	public QueryResult<AnnountDTO> queryResult(int page, int pageNum,
			AnnountDTO annountDTO, LinkedHashMap<String, String> orderby)
			throws Exception {
		return annountDao.queryResult(page, pageNum, annountDTO, orderby);
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.service.marketing.AnnountService#addSaveAnnount
	 *                        (com.paySystem.ic.web.dto.marketing.AnnountDTO)
	 *@MethodName:addSaveAnnount
	 *@Description:保存方法
	 *@param annountDTO
	 *            全站公告管理DTO
	 *@Author:孙晓磊
	 *@Date:2014-9-9下午05:15:39
	 */
	public void addSaveAnnount(AnnountDTO annountDTO) {

		try {
			annountDao.addSaveAnnount(annountDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.service.marketing.AnnountService#findAnnounTitle
	 *                        (java.lang.String, java.lang.String,
	 *                        java.lang.Integer)
	 *@MethodName:findAnnounTitle
	 *@Description:查询方法
	 *@param announTitle
	 *            公告标题
	 *@param method
	 *            方法名称
	 *@param announTitleId
	 *            公告ID
	 *@return
	 *@Author:孙晓磊
	 *@Date:2014-9-10上午09:25:15
	 */
	public boolean findAnnounTitle(String announTitle, String method,
			Integer announTitleId) {
		boolean flag = false;
		List<Annount> list = annountDao.findAnnounTitle(announTitle);

		/** 如果是添加公告信息信息，只判断公告标题是否相同 */
		if (method.equals("addSave")) {
			/** 如果车牌号相同 */
			if (list.size() > 0 == true) {
				flag = true;
			}
		} else {
			/** 如果是修改公告信息，标题相同，但是公告的id不同，不提示标题重复 */
			if (list.size() > 0
					&& !list.get(0).getAnnounId().equals(announTitleId)) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.service.marketing.AnnountService#find
	 *                        (java.lang.Integer)
	 *@MethodName:find
	 *@Description:查询方法
	 *@param annountId
	 *            公告ID
	 *@return
	 *@Author:孙晓磊
	 *@Date:2014-9-10下午02:32:19
	 */
	public AnnountDTO find(Integer annountId) {
		AnnountDTO annountDTO = annountDao.getAnnountDTO(annountId);

		return annountDTO;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.service.marketing.AnnountService#updateAnnount
	 *                        (com.paySystem.ic.web.dto.marketing.AnnountDTO)
	 *@MethodName:updateAnnount
	 *@Description:修改方法
	 *@param annount
	 *            公告的DTO
	 *@Author:孙晓磊
	 *@Date:2014-9-10下午04:01:18
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateAnnount(AnnountDTO annountDTO) {

		annountDao.updateAnnount(annountDTO);

	}

}
