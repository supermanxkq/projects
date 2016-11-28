/**  
 * @Title: CustomServiceDAOImpl.java
 * @Package: com.paySystem.ic.dao.member.impl
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-11-13 下午03:51:37
 * @Version: V1.0  
 */
package com.paySystem.ic.dao.member.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.CustomService;
import com.paySystem.ic.dao.member.CustomServiceDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.member.CustomServiceDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ProjectName:omal
 * @ClassName:CustomServiceDAOImpl
 * @Description:TODO
 * @date: 2014-11-13
 * @author: 孟凡岭
 * @version: V1.0
 */
@Repository(CustomServiceDAO.CUSTOMSERVICEDAO)
public class CustomServiceDAOImpl extends DaoSupport<CustomService> implements
		CustomServiceDAO {

	/**
	 *@MethodName:findByStoreId
	 *@Description:根据商户号查询出相关客服信息
	 *@param storeId
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-11-13下午04:23:13
	 */
	@Override
	public List<CustomServiceDTO> findByStoreId(String storeId)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = "from CustomService o where o.storeId='" + storeId + "'";
		List<CustomService> list = this.findByJpl(sql);
		List<CustomServiceDTO> dtoList = new ArrayList<CustomServiceDTO>();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				CustomServiceDTO c = (CustomServiceDTO) EntityDtoConverter
						.bean2Dto(list.get(i), new CustomServiceDTO());
				dtoList.add(c);
			}
		}
		return dtoList;
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
	 *@Date:2014-11-17下午02:06:27
	 */
	public QueryResult<CustomServiceDTO> queryResult(int first, int pageNum,
			CustomServiceDTO customServiceDTO,
			LinkedHashMap<String, String> orderby) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder(); // 封装查询where条件
		List<Object> params = new ArrayList<Object>();
		UserSession us = Utils.getUserSession();
		if (us.getUserLevel() != 0) {
			sql.append(" and o.merId=?");
			params.add(us.getMerId());
		}else{
			sql.append(" and o.merId='0'");
		}
		if(StringUtils.isNotBlank(customServiceDTO.getName())){
			sql.append(" and o.name like ?");
			params.add("%"+customServiceDTO.getName()+"%");
		}
		if(StringUtils.isNotBlank(customServiceDTO.getQq())){
			sql.append(" and o.qq like ?");
			params.add("%"+customServiceDTO.getQq()+"%");
		}
		QueryResult<CustomService> queryResult = this.getScrollData(first,
				pageNum, sql.toString(), params.toArray(), orderby);
		List<CustomServiceDTO> dtoList = getDtoList(queryResult.getResultlist());
		QueryResult<CustomServiceDTO> query = new QueryResult<CustomServiceDTO>();
		query.setResultlist(dtoList);
		query.setTotalrecord(queryResult.getTotalrecord());
		return query;
	}

	/**
	 *@Title:getDtoList
	 *@Description:dto转List
	 *@Params:@param resultlist
	 *@Params:@return
	 *@Return:List<CustomServiceDTO>
	 *@author:孟凡岭
	 *@Date:2014-11-17下午04:15:42
	 */
	private List<CustomServiceDTO> getDtoList(List<CustomService> resultlist)
			throws Exception {
		// TODO Auto-generated method stub
		List<CustomServiceDTO> dtoList = new ArrayList<CustomServiceDTO>();
		if (resultlist != null) {
			for (int i = 0; i < resultlist.size(); i++) {
				dtoList.add((CustomServiceDTO) EntityDtoConverter.bean2Dto(
						resultlist.get(i), new CustomServiceDTO()));
			}
		}
		
		return dtoList;
	}

	/**
	 *@MethodName:update
	 *@Description:更新
	 *@param customServiceDTO
	 *@Author:孟凡岭
	 *@Date:2014-11-17下午05:44:18
	 */
	public void update(CustomServiceDTO customServiceDTO) {
		// TODO Auto-generated method stub
		CustomService c = this.find(customServiceDTO.getId());
		UserSession us = Utils.getUserSession();
		if(us.getUserLevel()==0){
			c.setMerId(String.valueOf(us.getUserLevel()));
		}else{
			c.setMerId(us.getMerId());
		}
		c.setStoreId(c.getMerId());
		c.setName(customServiceDTO.getName());
		c.setQq(customServiceDTO.getQq());
		this.update(c);
	}

	/**
	 *@MethodName:add
	 *@Description:添加数据
	 *@param customServiceDTO
	 *@Author:孟凡岭
	 *@Date:2014-11-17下午05:56:30
	 */
	public void add(CustomServiceDTO customServiceDTO) throws Exception {
		// TODO Auto-generated method stub
		UserSession us = Utils.getUserSession();
		if (us.getUserLevel() == 0) {
			customServiceDTO.setMerId(String.valueOf(0));
		} else {
			customServiceDTO.setMerId(us.getMerId());
		}
		customServiceDTO.setStoreId(customServiceDTO.getMerId());
		customServiceDTO.setCreateTime(this.getSysTime());
		this.save(EntityDtoConverter.dto2Bean(customServiceDTO,
				new CustomService()));
	}

	/**
	 *@MethodName:findById
	 *@Description:加载单条数据
	 *@param id
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-11-17下午06:41:22
	 */
	public CustomServiceDTO findById(long id) throws Exception {
		// TODO Auto-generated method stub
		return (CustomServiceDTO) EntityDtoConverter.bean2Dto(this.find(id),
				new CustomServiceDTO());
	}

	/**
	 *@MethodName:deleteData
	 *@Description:删除数据
	 *@param id
	 *@Author:孟凡岭
	 *@Date:2014-11-18上午09:53:50
	 */
	public void deleteData(String id) {
		// TODO Auto-generated method stub
		this.delete(Long.valueOf(id));
	}

}
