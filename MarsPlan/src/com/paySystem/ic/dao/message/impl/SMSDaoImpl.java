package com.paySystem.ic.dao.message.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.app.ShortMesSend;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.message.SMSDao;
import com.paySystem.ic.service.message.SMSService;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemberDTO;
import com.paySystem.ic.web.dto.message.ShortMesSendDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @Description:短信参数Dao的实现类
 * @author: 张国法
 */
@Repository(SMSDao.SMSDAO)
public class SMSDaoImpl extends DaoSupport<ShortMesSend> implements SMSDao {

	ShortMesSend shortMesSend = new ShortMesSend();

	ShortMesSendDTO smsDto=new ShortMesSendDTO();
	
	MemberDTO memberDTO = new MemberDTO();
	
	/** 创建返回值数据传送对象 */
	ReturnDTO dot = new ReturnDTO();
	@Resource
	SMSService smsServiceImpl;
	
	
	public QueryResult<ShortMesSend> querySmsByCond(int page, int pageNum, ShortMesSendDTO smsDto,
			LinkedHashMap<String, String> orderby, int flag) throws Exception {
		
		StringBuilder sql = new StringBuilder(); // 封装查询where条件
		List<Object> params = new ArrayList<Object>();
		// 获取UserSession
		UserSession us = Utils.getUserSession();
		/**
		 * 限定不同级别操作员的限制条件
		 */
/*		switch (us.getUserLevel()) {
		case 0:
			break;
		case 1:
			sql.append(" and ( o.inId ='" + us.getOrganId() + "' or o.outId='"+ us.getOrganId() + "') ");
			break;
		}*/

		
		/** 判断页面条件 */
		if (StringUtils.isNotBlank(smsDto.getBeginDate())) {// 判断起始时间
			sql.append(" and str_to_date(o.createTime,'%Y-%m-%d') " +
					">='"+smsDto.getBeginDate()+"'");
		}
		
		if (StringUtils.isNotBlank(smsDto.getEndDate())) {// 检索结束时间
			sql.append(" and str_to_date(o.createTime,'%Y-%m-%d') " +
					"<='"+smsDto.getEndDate()+"'");
		}
		if (smsDto.getSmsType() != -1) {// 检索发送人群
			sql.append(" and o.smsType = " + smsDto.getSmsType());
		}
		if (smsDto.getSmsStatus() != -1) {// 检索状态
			sql.append(" and o.smsStatus = " + smsDto.getSmsStatus());
		}
//		if (StringUtils.isNotBlank(smsDto.getSmsTitle())) {
//			sql.append(" and o.smsTitle like ?"+smsDto.getSmsTitle());
//		}

		if (StringUtils.isNotBlank(smsDto.getSmsTitle())) {// 检索标题
			sql.append(" and o.smsTitle like ?").append(params.size() + 1);
			params.add("%" + smsDto.getSmsTitle().trim() + "%");
		}
		
//		
//		sql.append(" and o.flag = " + flag + " ");
		
//		if (StringUtils.isNotBlank(stockAdjustmentDTO.getStockId())) {
//			sql.append(" and o.id like ?").append(params.size() + 1);
//			params.add("%" + stockAdjustmentDTO.getId() + "%");
//		}
//		if (smsDto.getSmsType() != -1) {
//			sql.append(" and o.status = ?").append(params.size() + 1);
//			params.add(smsDto.getSmsType());
//		}
		QueryResult<ShortMesSend> queryResult = getScrollData(page, pageNum, sql.toString(), params.toArray(), orderby);

		return queryResult;
	}
	
	//添加新的短信
	public void addSMSInfo(ShortMesSendDTO smsDto) {
		UserSession us=Utils.getUserSession();
		
		ShortMesSend s=new ShortMesSend();
		
		
		try {
			s.setSmsTitle(smsDto.getSmsTitle());
			s.setSmsContent(smsDto.getSmsContent());
			s.setSmsType(smsDto.getSmsType());
			s.setNum(smsDto.getNum());
			s.setTotalPrice(smsDto.getTotalPrice());
			s.setOilId(us.getOrganId());
			s.setSmsStatus(0);
			Date now = new Date(); 
			s.setCreateTime(now);
			super.save(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//修改方法

	public ReturnDTO update(ShortMesSendDTO smsDto) {
		UserSession us=Utils.getUserSession();
		ReturnDTO dto = new ReturnDTO();
		//ShortMesSend sms=smsDao.find(smsDto.getSmsId());
		ShortMesSend sms = this.find(smsDto.getSmsId());
		if (smsDto != null) {
			if(0==smsDto.getSmsStatus()){
				//修改
				Date now = new Date(); 
				shortMesSend.setUpdateTime(now);
				shortMesSend.setSmsStatus(sms.getSmsStatus());
				smsDto.setNum(smsDto.getNum());
				smsDto.setTotalPrice(smsDto.getTotalPrice());
				shortMesSend.setSmsStatus(0);
				
			}else if(2==smsDto.getSmsStatus()){
				//审核不通过
				shortMesSend.setSmsStatus(2);
			}else if(1==smsDto.getSmsStatus()){
				//审核通过
				shortMesSend.setSmsStatus(1);
			}else if(3==smsDto.getSmsStatus()){
				//删除
				shortMesSend.setSmsStatus(3);
			}
			//shortMesSend.setSmsStatus(sms.getSmsStatus());
			shortMesSend.setCreateTime(sms.getCreateTime());
			shortMesSend.setSmsId(smsDto.getSmsId());
			shortMesSend.setSmsTitle(smsDto.getSmsTitle());
			shortMesSend.setSmsContent(smsDto.getSmsContent());
			shortMesSend.setSmsType(smsDto.getSmsType());
			shortMesSend.setTotalPrice(smsDto.getTotalPrice());
			shortMesSend.setNum(smsDto.getNum());
			shortMesSend.setOilId(smsDto.getOilId());
//			if(smsDto.getStatus()==null){
//				shortMesSend.setStatus(0);
//			}else{
//				shortMesSend.setStatus(smsDto.getStatus());
//			}
			
			super.update(shortMesSend);
			dto.setFlag(true);
		}

		return dto;
	}
	
	public ShortMesSendDTO findShortMesSend(Integer smsId) {
		ShortMesSendDTO smsDto = getsmsDto(this.find(smsId));
		return smsDto;
	}
	
	protected ShortMesSendDTO getsmsDto(ShortMesSend sms) {
		ShortMesSendDTO smsDto = new ShortMesSendDTO();
		if(sms!=null){
			smsDto.setSmsId(sms.getSmsId());
			smsDto.setSmsTitle(sms.getSmsTitle());
			smsDto.setSmsContent(sms.getSmsContent());
	    	smsDto.setSmsType(sms.getSmsType());
			smsDto.setSmsStatus(sms.getSmsStatus());
			smsDto.setCreateTime(sms.getCreateTime());
			smsDto.setUpdateTime(sms.getUpdateTime());
			smsDto.setNum(sms.getNum());
			smsDto.setTotalPrice(sms.getTotalPrice());
		}

		return smsDto;
	}
}
