/**  
 * @Title: annountDAOImpl.java
 * @Package: com.paySystem.ic.dao.marketing.impl
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-9-9 下午02:02:37
 * @Version: V1.0  
 */
package com.paySystem.ic.dao.marketing.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.persistence.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.marketing.Annount;
import com.paySystem.ic.dao.marketing.AnnountDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.web.dto.marketing.AnnountDTO;

/**
 * @ProjectName:omall20140905
 * @ClassName:annountDAOImpl
 * @Description:全站公告管理DAO实现类
 * @date: 2014-9-9
 * @author: 孙晓磊
 * @version: V1.0
 */
@Repository(AnnountDAO.ANNOUNTDAO)
public class AnnountDAOImpl extends DaoSupport<Annount> implements AnnountDAO {

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.marketing.annountDAO#queryResult
	 *                        (int, int,
	 *                        com.paySystem.ic.web.dto.marketing.AnnountDTO,
	 *                        java.util.LinkedHashMap)
	 *@MethodName:queryResult
	 *@Description:查询方法
	 *@param firstindex
	 *            第一个索引
	 *@param maxresult
	 *            最后一个索引
	 *@param annountDTO
	 *@param orderby
	 *@return
	 *@throws Exception
	 *@Author:孙晓磊
	 *@Date:2014-9-9下午02:45:33
	 */
	public QueryResult<AnnountDTO> queryResult(int firstindex, int maxresult,
			AnnountDTO annountDTO, LinkedHashMap<String, String> orderby)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		if (!"-1".equals(annountDTO.getStatus())
				&& -1 != annountDTO.getStatus()) {
			sql.append(" and o.status = ?").append(params.size() + 1);
			params.add(annountDTO.getStatus());
		}
		if (StringUtils.isNotBlank(annountDTO.getAnnounTitle())) {
			sql.append(" and o.announTitle like ?").append(params.size() + 1);
			params.add("%" + annountDTO.getAnnounTitle().trim() + "%");
		}

		if (StringUtils.isNotBlank(annountDTO.getAuthor())) {
			sql.append(" and o.author like ?").append(params.size() + 1);
			params.add("%" + annountDTO.getAuthor().trim() + "%");
		}

		if (StringUtils.isNotBlank(annountDTO.getCreateTime())) {
			sql.append(" and str_to_date(o.createTime,'%Y-%m-%d') "
					+ ">=str_to_date('" + annountDTO.getCreateTime()
					+ "','%Y-%m-%d')");
		}
		if (StringUtils.isNotBlank(annountDTO.getStopTime())) {
			sql.append(" and str_to_date(o.createTime,'%Y-%m-%d') "
					+ "<=str_to_date('" + annountDTO.getStopTime()
					+ "','%Y-%m-%d')");
		}
		QueryResult<Annount> queryResult = getScrollData(firstindex, maxresult,
				sql.toString(), params.toArray(), orderby);
		List<AnnountDTO> list = getAnnountDTOList(queryResult.getResultlist());
		QueryResult<AnnountDTO> query = new QueryResult<AnnountDTO>();
		query.setResultlist(list);
		query.setTotalrecord(queryResult.getTotalrecord());
		return query;
	}

	/**
	 *@Title:getAnnountDTOList
	 *@Description:TODO
	 *@Params:@param list 公告的list集合
	 *@Params:@return
	 *@Return:List<AnnountDTO>
	 *@author:孙晓磊
	 *@Date:2014-9-9下午02:46:36
	 */
	private List<AnnountDTO> getAnnountDTOList(List<Annount> list) {
		List<AnnountDTO> AnnountDTOlist = new ArrayList<AnnountDTO>();
		for (int i = 0; i < list.size(); i++) {
			Annount annount = list.get(i);
			AnnountDTO annountDTO = new AnnountDTO();
			annountDTO.setAnnounId(annount.getAnnounId());
			annountDTO.setAnnounTitle(annount.getAnnounTitle());
			annountDTO.setAuthor(annount.getAuthor());
			annountDTO.setReportTime(DateTimeTool.dateFormat(
					"yyyy-MM-dd HH:mm:ss", annount.getReportTime()));
			annountDTO.setStatus(annount.getStatus());
			annountDTO.setIsTop(annount.getIsTop());
			AnnountDTOlist.add(annountDTO);

		}
		return AnnountDTOlist;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.marketing.AnnountDAO#addSaveAnnount
	 *                        (com.paySystem.ic.web.dto.marketing.AnnountDTO)
	 *@MethodName:addSaveAnnount
	 *@Description:保存方法
	 *@param annountDTO
	 *@return
	 *@throws Exception
	 *@Author:孙晓磊
	 *@Date:2014-9-9下午05:24:32
	 */
	public Annount addSaveAnnount(AnnountDTO annountDTO) throws Exception {
		Annount annount = getAnnount(annountDTO);
		this.save(annount);
		return annount;

	}

	/**
	 *@Title:getAnnount
	 *@Description:将DTO转换为bean
	 *@Params:@param annountDTO
	 *@Params:@return
	 *@Return:Annount
	 *@author:孙晓磊
	 *@Date:2014-9-11下午03:50:45
	 */
	private Annount getAnnount(AnnountDTO annountDTO) {
		if (annountDTO == null) {
			return null;
		}
		Annount annount = new Annount();
		if (annountDTO.getAnnounId() != null) {
			annount.setAnnounId(annountDTO.getAnnounId());
		}
		if (annountDTO.getAnnounTitle() != null) {
			annount.setAnnounTitle(annountDTO.getAnnounTitle());
		}
		if (annountDTO.getAuthor() != null) {
			annount.setAuthor(annountDTO.getAuthor());
		}
		if (annountDTO.getAnnounContent() != null) {
			annount.setAnnounContent(annountDTO.getAnnounContent());
		}
		if (annountDTO.getReportTime() != null) {
			annount.setReportTime(DateTimeTool.dateFormat(
					"yyyy-MM-dd HH:mm:ss", annountDTO.getReportTime()));
		}
		if (annountDTO.getStatus() != null) {
			annount.setStatus(annountDTO.getStatus());
		}
		if (annountDTO.getIsTop() != null) {
			annount.setIsTop(annountDTO.getIsTop());
		}
		if (annountDTO.getCreateTime() != null) {
			annount.setCreateTime(DateTimeTool.dateFormat(
					"yyyy-MM-dd HH:mm:ss", annountDTO.getCreateTime()));
		}
		return annount;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.marketing.AnnountDAO#findAnnounTitle
	 *                        (java.lang.String)
	 *@MethodName:findAnnounTitle
	 *@Description:查询标题
	 *@param announTitle
	 *@return
	 *@Author:孙晓磊
	 *@Date:2014-9-10上午09:33:48
	 */
	@SuppressWarnings("unchecked")
	public List<Annount> findAnnounTitle(String announTitle) {

		String jpl = null;
		jpl = "select o from Annount o where o.announTitle =?";

		Query query = em.createQuery(jpl);
		query.setParameter(1, announTitle);

		return query.getResultList();

	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.marketing.AnnountDAO#getAnnountDTO
	 *                        (com.paySystem.ic.bean.Marketing.Annount)
	 *@MethodName:getAnnountDTO
	 *@Description:通过ID得到DTO
	 *@param annount
	 *@return
	 *@Author:孙晓磊
	 *@Date:2014-9-10下午02:55:10
	 */
	public AnnountDTO getAnnountDTO(Integer annountId) {
		Annount annount = find(annountId);
		if (annount == null) {
			return null;
		}
		AnnountDTO annountDTO = new AnnountDTO();
		if (annount.getAnnounId() != null) {
			annountDTO.setAnnounId(annount.getAnnounId());
		}
		if (annount.getAnnounTitle() != null) {
			annountDTO.setAnnounTitle(annount.getAnnounTitle());
		}
		if (annount.getAuthor() != null) {
			annountDTO.setAuthor(annount.getAuthor());
		}
		if (annount.getAnnounContent() != null) {
			annountDTO.setAnnounContent(annount.getAnnounContent());

		}
		if (annount.getReportTime() != null) {
			annountDTO.setReportTime(annount.getReportTime().toString());

		}
		if (annount.getIsTop() != null) {
			annountDTO.setIsTop(annount.getIsTop());

		}
		if (annount.getCreateTime() != null) {
			annountDTO.setCreateTime(annount.getCreateTime().toString());
		}
		if (annount.getStatus() != null) {
			annountDTO.setStatus(annount.getStatus());
		}
		return annountDTO;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.marketing.AnnountDAO#updateAnnount
	 *                        (com.paySystem.ic.web.dto.marketing.AnnountDTO)
	 *@MethodName:updateAnnount
	 *@Description:修改方法
	 *@param annountDTO
	 *@Author:孙晓磊
	 *@Date:2014-9-10下午04:07:06
	 */
	public void updateAnnount(AnnountDTO annountDTO) {

		Annount annount = getAnnount(annountDTO);
		this.update(annount);
	}

}
