/**  
* @Title: annountDAOImpl.java
* @Package: com.paySystem.ic.dao.marketing.impl
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-9 下午02:02:37
* @Version: V1.0  
*/
package com.paySystem.ic.dao.marketing.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.Marketing.AdvertMa;
import com.paySystem.ic.dao.marketing.AdvertMaDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.web.dto.marketing.AdvertMaDTO;


@Repository(AdvertMaDAO.ADVERTMA)
public class AdvertMaDAOImpl extends DaoSupport<AdvertMa> implements AdvertMaDAO{

	
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.marketing.AdvertMaDAO#queryResult(int, int, com.paySystem.ic.web.dto.marketing.AdvertMaDTO, java.util.LinkedHashMap)
	 *@MethodName:queryResult
	 *@Description:异步加载
	 *@param firstindex
	 *@param maxresult
	 *@param advertMaDTO
	 *@param orderby
	 *@return
	 *@Author:孙晓磊
	 *@Date:2014-9-21下午02:52:33
	 */
	public QueryResult<AdvertMaDTO> queryResult(int firstindex, int maxresult,
			AdvertMaDTO advertMaDTO, LinkedHashMap<String, String> orderby)
	   {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		if(!"-1".equals(advertMaDTO.getMediaType())&&-1!=advertMaDTO.getMediaType()){
			sql.append(" and o.mediaType = ?").append(params.size()+1);
			params.add(advertMaDTO.getMediaType());
		}
		if (StringUtils.isNotBlank(advertMaDTO.getAdvertName())) {
		sql.append(" and o.advertName like ?").append(params.size() + 1);
		params.add("%" + advertMaDTO.getAdvertName().trim() + "%");
		}
		QueryResult<AdvertMa> queryResult=null;
		try {
			queryResult = getScrollData(firstindex, maxresult,
					sql.toString(), params.toArray(), orderby);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<AdvertMaDTO> list = getAdvertMaList(queryResult
				.getResultlist());
		QueryResult<AdvertMaDTO> query = new QueryResult<AdvertMaDTO>();
		query.setResultlist(list);
		query.setTotalrecord(queryResult.getTotalrecord());
		return query;
	}
	
	/**
	*@Title:getAdvertMaList
	*@Description:实体转换
	*@Params:@param advertMaList
	*@Params:@return
	*@Return:List<AdvertMaDTO>
	*@author:孙晓磊
	*@Date:2014-9-21下午02:52:48
	*/
	public List<AdvertMaDTO> getAdvertMaList(List<AdvertMa> advertMaList)
	{
		List<AdvertMaDTO> advertmadtolist=new ArrayList<AdvertMaDTO>();
		for(int i=0;i<advertMaList.size();i++)
		{
			AdvertMa advertma=advertMaList.get(i);
			AdvertMaDTO advertmadto =new AdvertMaDTO();
			if(advertma.getAdvertName()!=null)
			{
		      advertmadto.setAdvertName(advertma.getAdvertName());
			}
			if(advertma.getPositionType()!=null)
			{
				advertmadto.setPositionTypeId(advertma.getPositionType());
			}
			if(advertma.getMediaType()!=null)
			{
				advertmadto.setMediaType(advertma.getMediaType());
			}
			if(advertma.getBeginTime()!=null)
			{
				advertmadto.setBeginTime(advertma.getBeginTime().toString());
			}
			if(advertma.getEndTime()!=null)
			{
				advertmadto.setEndTime(advertma.getEndTime().toString());
			}
			if(advertma.getAdvertId()!=null)
			{
				advertmadto.setAdvertId(advertma.getAdvertId());
			}
			advertmadtolist.add(advertmadto);
			
		}
		return advertmadtolist;
		
	}

	
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.marketing.AdvertMaDAO#addsaveAdver(com.paySystem.ic.web.dto.marketing.AdvertMaDTO)
	 *@MethodName:addsaveAdver
	 *@Description:添加方法
	 *@param adverMaDTO
	 *@Author:孙晓磊
	 *@Date:2014-9-21下午02:53:16
	 */
	public void addsaveAdver(AdvertMaDTO adverMaDTO) {
		
		AdvertMa advertma=getAdvertMa(adverMaDTO);
		this.save(advertma);
	}
	
	/**
	*@Title:getAdvertMa
	*@Description:实体转换
	*@Params:@param adverMaDTO
	*@Params:@return
	*@Return:AdvertMa
	*@author:孙晓磊
	*@Date:2014-9-21下午02:53:31
	*/
	public AdvertMa getAdvertMa(AdvertMaDTO adverMaDTO)
	{
		if(adverMaDTO==null)
		{
			return null;
		}
		AdvertMa adverMa=new AdvertMa();
		if(adverMaDTO.getAdvertId()!=null)
		{
			adverMa.setAdvertId(adverMaDTO.getAdvertId());
		}
		if(adverMaDTO.getAdvertName()!=null)
		{
			adverMa.setAdvertName(adverMaDTO.getAdvertName());
		}
		if(adverMaDTO.getMediaType()!=null)
		{
			adverMa.setMediaType(adverMaDTO.getMediaType());
		}
		if(adverMaDTO.getPositionTypeId()!=null)
		{
			adverMa.setPositionType(adverMaDTO.getPositionTypeId());
		}
		if(adverMaDTO.getBeginTime()!=null)
		{   
			Date begin;
			try {
				begin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(adverMaDTO.getBeginTime());
				adverMa.setBeginTime(begin);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(adverMaDTO.getEndTime()!=null)
		{
			  try {
				Date endtime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(adverMaDTO.getEndTime());
				adverMa.setEndTime(endtime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(adverMaDTO.getAdverCot()!=null)
		{
			adverMa.setAdverCot(adverMaDTO.getAdverCot());
		}
		if(adverMaDTO.getImageFile()!=null)
		{
			adverMa.setImagePath(adverMaDTO.getImageFileFileName());
		}
		if(adverMaDTO.getOpenType()!=null)
		{
			adverMa.setOpenType(adverMaDTO.getOpenType());
		}
		if(adverMaDTO.getContract()!=null)
		{
			adverMa.setContract(adverMaDTO.getContract());
		}
		if(adverMaDTO.getContractEmail()!=null)
		{
			adverMa.setContractEmail(adverMaDTO.getContractEmail());
		}
		if(adverMaDTO.getContractTel()!=null)
		{
			adverMa.setContractTel(adverMaDTO.getContractTel());
		}
		if(adverMaDTO.getAdvertContent()!=null)
		{
			adverMa.setAdvertContent(adverMaDTO.getAdvertContent());
		}
		if(adverMaDTO.getImageFileFileName()!=null)
		{
			adverMa.setImagePath(adverMaDTO.getImageFileFileName());
		}
		return adverMa;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.marketing.AdvertMaDAO#getAdvertMaDTO(java.lang.Integer)
	 *@MethodName:getAdvertMaDTO
	 *@Description:通过id获取dto
	 *@param annountId
	 *@return
	 *@Author:孙晓磊
	 *@Date:2014-9-21下午02:53:58
	 */
	public AdvertMaDTO getAdvertMaDTO(Integer annountId) {
		AdvertMa advertMa = find(annountId);
		if (advertMa == null) {
			return null;
		}
		AdvertMaDTO advertMaDto = new AdvertMaDTO();
		if (advertMa.getAdvertId() != null) {
			advertMaDto.setAdvertId(annountId);
		}
		if (advertMa.getAdverCot() != null) {
			advertMaDto.setAdverCot(advertMa.getAdverCot());
		}
		if (advertMa.getAdvertContent() != null) {
			advertMaDto.setAdvertContent(advertMa.getAdvertContent());
		}
		if (advertMa.getAdvertName() != null) {
			advertMaDto.setAdvertName(advertMa.getAdvertName());
			
		}
		if (advertMa.getBeginTime() != null) {
			advertMaDto.setBeginTime(advertMa.getBeginTime().toString());
			
		}if (advertMa.getContract() != null) {
			advertMaDto.setContract(advertMa.getContract());
			
		}if(advertMa.getContractEmail()!=null)
		{
			advertMaDto.setContractEmail(advertMa.getContractEmail());
		}
		if(advertMa.getContractTel()!=null)
		{
			advertMaDto.setContractTel(advertMa.getContractTel());
		}
		if(advertMa.getEndTime()!=null)
		{
			advertMaDto.setEndTime(advertMa.getEndTime().toString());
		}
		if(advertMa.getImagePath()!=null)
		{
			advertMaDto.setImageFileFileName(advertMa.getImagePath());
		}
		if(advertMa.getMediaType()!=null)
		{
			advertMaDto.setMediaType(advertMa.getMediaType());
		}
		if(advertMa.getOpenType()!=null)
		{
			advertMaDto.setOpenType(advertMa.getOpenType());
		}
		if(advertMa.getPositionType()!=null)
		{
			advertMaDto.setPositionTypeId(advertMa.getPositionType());
		}
	
		return advertMaDto;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.marketing.AdvertMaDAO#updateAdvertMa(com.paySystem.ic.web.dto.marketing.AdvertMaDTO)
	 *@MethodName:updateAdvertMa
	 *@Description:TODO
	 *@param advertDTO
	 *@Author:孙晓磊
	 *@Date:2014-9-20下午05:00:54
	 */
	public void updateAdvertMa(AdvertMaDTO advertDTO) {
		AdvertMa advert=getAdvertMa(advertDTO);
		this.update(advert);
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.marketing.AdvertMaDAO#findAdvertManame(java.lang.String)
	 *@MethodName:findAdvertManame
	 *@Description:TODO
	 *@param AdvertManame
	 *@return
	 *@Author:孙晓磊
	 *@Date:2014-9-21下午03:22:50
	 */

	@SuppressWarnings("unchecked")
	public List<AdvertMa> findAdvertManame(String AdvertManame) {
		String jpl = null;
		jpl = "select o from AdvertMa o where o.advertName =?";
		
		Query query = em.createQuery(jpl);
		query.setParameter(1, AdvertManame);
		
		return query.getResultList();
	}
}
