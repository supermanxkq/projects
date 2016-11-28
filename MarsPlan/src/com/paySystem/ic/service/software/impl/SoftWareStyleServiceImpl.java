package com.paySystem.ic.service.software.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.software.SoftWareStyle;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.software.SoftWareStyleDao;
import com.paySystem.ic.service.software.SoftWareStyleService;
import com.paySystem.ic.web.dto.software.SoftWareStyleDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ProjectName:MarsPlan 
 * @ClassName:SoftWareStyleServiceImpl  
 * @Description:软件分类实现类
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:Mar 9, 20168:49:34 PM
 */
@Service(SoftWareStyleService.SOFTWARESTYLESERVICE)
public class SoftWareStyleServiceImpl extends DaoSupport<SoftWareStyle> implements SoftWareStyleService {

	@Resource
	private SoftWareStyleDao softWareStyleDao;

	/**
	 *@MethodName:queryAll 
	 *@Description:前台获取所有的软件分类
	 *@return
	 *@throws Exception 
	 *@author:徐凯强
	 *@return QueryResult<SoftWareStyle>
	 *@date:Mar 9, 20168:49:48 PM
	 */
	public QueryResult<SoftWareStyle> queryAll() throws Exception {
		return this.getScrollData();
	}

	/**
	 *@MethodName:queryAll 
	 *@Description:后台获取所有的代码分类
	 *@param firstIndex
	 *@param pageNum
	 *@param softWareStyleDTO
	 *@param orderBy
	 *@return
	 *@throws Exception 
	 *@author:徐凯强
	 *@return QueryResult<SoftWareStyle>
	 *@date:Mar 9, 20168:50:19 PM
	 */
	@Override
	public QueryResult<SoftWareStyle> queryAll(int firstIndex, int pageNum, SoftWareStyleDTO softWareStyleDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return softWareStyleDao.queryAll(firstIndex, pageNum, softWareStyleDTO, orderBy);
	}

	/**
	 *@MethodName:addSave 
	 *@Description:后台添加分类信息
	 *@param softWareStyle 
	 *@author:徐凯强
	 *@return void
	 *@date:Mar 9, 20168:50:37 PM
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSave(SoftWareStyle softWareStyle) {
		this.save(softWareStyle);
	}

	/**
	 *@MethodName:queryStyles 
	 *@Description:后台添加软件时需要的软件类型
	 *@return 
	 *@author:徐凯强
	 *@return List<OptionsInteger>
	 *@date:Mar 9, 20168:50:56 PM
	 */
	@Override
	public List<OptionsInteger> queryStyles() {
		List<OptionsInteger> list = new ArrayList<>();
		try {
			StringBuffer wherejpql = new StringBuffer();
			List<Object> queryParams = new ArrayList<>();
			QueryResult<SoftWareStyle> queryResult = this.getScrollData(-1, -1, wherejpql.toString(),
					queryParams.toArray());
			for (SoftWareStyle softWareStyle : queryResult.getResultlist()) {
				list.add(new OptionsInteger(softWareStyle.getId(), softWareStyle.getName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
