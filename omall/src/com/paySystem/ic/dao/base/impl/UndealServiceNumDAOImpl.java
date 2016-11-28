package com.paySystem.ic.dao.base.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.paySystem.ic.bean.base.UndealServiceNum;
import com.paySystem.ic.dao.base.UndealServiceNumDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.base.UndealServiceNumDTO;
import com.paySystem.ic.web.dto.system.UserSession;

@Repository(UndealServiceNumDAO.UNDEALSERVICENUMDAO)
public class UndealServiceNumDAOImpl extends DaoSupport<UndealServiceNum>
		implements UndealServiceNumDAO {
	/**
	 *@Title:getUndealServiceNumDTO
	 *@Description:将实体转换成DTO的方法
	 *@Params:@param undealServiceNum
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:UndealServiceNumDTO
	 *@author:王楠
	 *@Date:2014-10-22下午04:59:12
	 */
	public UndealServiceNumDTO getUndealServiceNumDTO(
			UndealServiceNum undealServiceNum) throws Exception {
		UndealServiceNumDTO undealServiceNumDTO = new UndealServiceNumDTO();
		undealServiceNumDTO = (UndealServiceNumDTO) EntityDtoConverter
				.bean2Dto(undealServiceNum, undealServiceNumDTO);
		return undealServiceNumDTO;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.base.UndealServiceDAO#findList
	 *                        ()
	 *@MethodName:findList
	 *@Description:查询数据的方法
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-10-22下午04:55:09
	 */

	public UndealServiceNumDTO findList(String orgMerId) throws Exception {
		UndealServiceNumDTO undealServiceNumDTO = new UndealServiceNumDTO();
		UserSession us = Utils.getUserSession();
		StringBuilder sql = new StringBuilder(
				"from UndealServiceNum o where o.orgMerId = '" + us.getMerId()
						+ "'");
		UndealServiceNum undealServiceNum = (UndealServiceNum) em.createQuery(
				sql.toString()).getSingleResult();
		undealServiceNumDTO = getUndealServiceNumDTO(undealServiceNum);
		undealServiceNumDTO = setZeroMethod(undealServiceNumDTO);
		return undealServiceNumDTO;
	}

	/**
	 *@Title:setZeroMethod
	 *@Description:封装的私有方法
	 *@Params:@param undealServiceNumDTO
	 *@Params:@return
	 *@Return:UndealServiceNumDTO
	 *@author:王楠
	 *@Date:2014-11-26下午04:34:04
	 */
	private UndealServiceNumDTO setZeroMethod(
			UndealServiceNumDTO undealServiceNumDTO) {
		if (undealServiceNumDTO.getComplaintsNum() == null
				|| undealServiceNumDTO.getComplaintsNum().equals("")) {
			undealServiceNumDTO.setComplaintsNum(0);
		}
		if (undealServiceNumDTO.getMerCheckNum() == null
				|| undealServiceNumDTO.getMerCheckNum().equals("")) {
			undealServiceNumDTO.setMerCheckNum(0);
		}
		if (undealServiceNumDTO.getNoDelivNum() == null
				|| undealServiceNumDTO.getNoDelivNum().equals("")) {
			undealServiceNumDTO.setNoDelivNum(0);
		}
		if (undealServiceNumDTO.getNoPaidNum() == null
				|| undealServiceNumDTO.getNoPaidNum().equals("")) {
			undealServiceNumDTO.setNoPaidNum(0);
		}
		if (undealServiceNumDTO.getReturnNum() == null
				|| undealServiceNumDTO.getReturnNum().equals("")) {
			undealServiceNumDTO.setReturnNum(0);
		}
		if (undealServiceNumDTO.getUnReadNum() == null
				|| undealServiceNumDTO.getUnReadNum().equals("")) {
			undealServiceNumDTO.setUnReadNum(0);
		}
		return undealServiceNumDTO;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.base.UndealServiceNumDAO#maidtainData
	 *                        (java.lang.String,
	 *                        com.paySystem.ic.bean.base.UndealServiceNum)
	 *@MethodName:maidtainData
	 *@Description:用于维护待处理业务统计信息的方法
	 *@param merId
	 *            商户编号
	 *@param undealServiceNum
	 *            待处理业务统计
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-11-24下午05:50:35
	 */
	@SuppressWarnings("unchecked")
	@Override
		public UndealServiceNum maintainData(String orgMerId,
			UndealServiceNum undealServiceNum, Integer sign) throws Exception {
		StringBuilder sql = new StringBuilder(
				" from UndealServiceNum o where o.orgMerId ='" + orgMerId + "'");
		UndealServiceNum undeal = null;
		List<UndealServiceNum> list = (List<UndealServiceNum>) em.createQuery(
				sql.toString()).getResultList();
		if (list.size() > 0) {
			if (undealServiceNum.getComplaintsNum() != null) {
				undeal = list.get(0);
				/** sign是针对订单数量是加是减的标志，0代表对应的订单数增加，1代表订单数减 */
				if (sign == 0) {
					undeal.setComplaintsNum(undeal.getComplaintsNum()
							+ undealServiceNum.getComplaintsNum());
				} else {
					undeal.setComplaintsNum(undeal.getComplaintsNum()
							- undealServiceNum.getComplaintsNum());
				}
			}
			if (undealServiceNum.getMerCheckNum() != null) {
				if (sign == 0) {
					undeal.setMerCheckNum(undeal.getMerCheckNum()
							+ undealServiceNum.getMerCheckNum());
				} else {
					undeal.setMerCheckNum(undeal.getMerCheckNum()
							- undealServiceNum.getMerCheckNum());
				}
			}
			if (undealServiceNum.getNoDelivNum() != null) {
				if (sign == 0) {
					undeal.setNoDelivNum(undeal.getNoDelivNum()
							+ undealServiceNum.getNoDelivNum());
				} else {
					undeal.setNoDelivNum(undeal.getNoDelivNum()
							- undealServiceNum.getNoDelivNum());
				}
			}
			if (undealServiceNum.getNoPaidNum() != null) {
				if (sign == 0) {
					undeal.setNoPaidNum(undeal.getNoPaidNum()
							+ undealServiceNum.getNoPaidNum());
				} else {
					undeal.setNoPaidNum(undeal.getNoPaidNum()
							- undealServiceNum.getNoPaidNum());
				}

			}
			if (undealServiceNum.getReturnNum() != null) {
				if (sign == 0) {
					undeal.setReturnNum(undeal.getReturnNum()
							+ undealServiceNum.getReturnNum());
				} else {
					undeal.setReturnNum(undeal.getReturnNum()
							- undealServiceNum.getReturnNum());
				}

			}
			if (undealServiceNum.getUnReadNum() != null) {
				if (sign == 0) {
					undeal.setUnReadNum(undeal.getUnReadNum()
							+ undealServiceNum.getUnReadNum());
				} else {
					undeal.setUnReadNum(undeal.getUnReadNum()
							+ undealServiceNum.getUnReadNum());
				}

			}
			this.update(undeal);
		}
		return undeal;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.base.UndealServiceNumDAO#findTotalData
	 *                        (java.lang.String)
	 *@MethodName:findTotalData
	 *@Description:用于机构登陆时查找数据的方法
	 *@param organId
	 *            机构编号
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-11-25上午11:35:49
	 */
	public UndealServiceNumDTO findTotalData(String organId) throws Exception {
		UndealServiceNumDTO undealServiceNumDTO = new UndealServiceNumDTO();
		UserSession us = Utils.getUserSession();
		StringBuilder sql = new StringBuilder(
				" select count(merCheckNum),count(complaintsNum) "
						+ " from B_UnDealServiceNum u ,b_merchants m where u.orgMerId=m.merId "
						+ " and m.organId='" + us.getOrganId() + "'");
		Object[] obj = (Object[]) em.createNativeQuery(sql.toString())
				.getSingleResult();
		undealServiceNumDTO = new UndealServiceNumDTO();
		undealServiceNumDTO.setMerCheckNum(Integer.valueOf(obj[0].toString()));
		undealServiceNumDTO
				.setComplaintsNum(Integer.valueOf(obj[1].toString()));
		undealServiceNumDTO = setZeroMethod(undealServiceNumDTO);
		return undealServiceNumDTO;
	}

	/**
	 *@MethodName:findByOrgMerId
	 *@Description:通过机构和商户号查询，初始化用
	 *@param orgMerId
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-12-13下午03:03:32
	 */
	@Override
	public UndealServiceNum findByOrgMerId(String orgMerId) {
		// TODO Auto-generated method stub
		String sql = "from UndealServiceNum  where orgMerId=?";
		List<UndealServiceNum> list = this.em.createQuery(sql).setParameter(1,
				orgMerId).getResultList();
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
