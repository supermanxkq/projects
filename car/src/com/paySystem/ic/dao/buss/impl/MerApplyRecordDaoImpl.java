package com.paySystem.ic.dao.buss.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.buss.MerApplyRecord;
import com.paySystem.ic.dao.buss.MerApplyRecordDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.web.dto.buss.MerApplyRecordDTO;

/**
 * 活动管理Dao实现类
 * 
 * @ClassName:MerApplyRecordDaoImpl
 * @Description:活动管理Dao实现类
 * @date: 2014-8-21上午10:49:02
 * @author: 赵瑞群
 * @version: V1.0
 */
@Repository(MerApplyRecordDao.MERAPPLYRECORDDAO)
public class MerApplyRecordDaoImpl extends DaoSupport<MerApplyRecord> implements MerApplyRecordDao {

	
	
	/**
	 *  活动申请记录保存方法
	 *  
	 *@Title:saveMerApplyRecord
	 *@Description: 保存活动申请记录
	 *@param:@param merApplyRecordDTO 活动申请记录
	 *@Return:void  
	 *@author:      赵瑞群
	 *@Thorws:
	 */
	public Integer saveMerApplyRecord(MerApplyRecordDTO merApplyRecordDTO) {
		
		MerApplyRecord merApplyRecord = new MerApplyRecord();
		BeanUtils.copyProperties(merApplyRecordDTO, merApplyRecord,new String[]{"descr"});
		if(merApplyRecordDTO.getDescr()!=null){
			try {
				merApplyRecord.setDescr(merApplyRecordDTO.getDescr().getBytes("UTF8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if(merApplyRecordDTO.getRecordId()==null){
			
			merApplyRecord.setCreateTime(new Date());
			merApplyRecord.setUpdateTime(new Date());
			
			this.save(merApplyRecord);
			return merApplyRecord.getRecordId();
		}else{
			
			merApplyRecord.setUpdateTime(new Date());
			this.update(merApplyRecord);
			return merApplyRecord.getProid();
		}
		
	    
	}




	/**
	 *  获取活动申请记录
	 *  
	 *@Title:findById
	 *@Description:根据Id获取活动申请记录Dto对象
	 *@param:@param 
	 *@param:@return
	 *@return:MerApplyRecordDTO 活动申请记录DTO对象
	 *@author:赵瑞群
	 *@Thorws:
	 */
	public MerApplyRecordDTO findById(Integer proid) {
		
		MerApplyRecord merApplyRecord = new MerApplyRecord();
		
		MerApplyRecordDTO merApplyRecordDTO = new MerApplyRecordDTO();
		/**根据ID获取活动申请记录实体对象*/
		merApplyRecord = this.find(proid);
		/**Bean2DTO*/
		BeanUtils.copyProperties(merApplyRecord,merApplyRecordDTO,new String[]{"descr"});
		
		try {
			merApplyRecordDTO.setDescr(new String(merApplyRecord.getDescr(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return merApplyRecordDTO;
	}

	/**
	 *  获取活动申请记录
	 *  
	 *@Title:findById
	 *@Description:根据proid merid获取活动申请记录Dto对象
	 *@param:@param 
	 *@param:@return
	 *@return:MerApplyRecordDTO 活动申请记录DTO对象
	 *@author:赵瑞群
	 *@Thorws:
	 */

	public MerApplyRecordDTO findByProIdAndMerIdNew(Integer proid, String merId) {
		MerApplyRecord merApplyRecord = new MerApplyRecord();
		
		MerApplyRecordDTO merApplyRecordDTO = new MerApplyRecordDTO();
		/**根据ID获取活动管理实体对象*/
		Query  query = em.createQuery("from MerApplyRecord mer where mer.proid=? and mer.merid=? order by mer.createTime desc ");
		query.setParameter(1, proid);
		query.setParameter(2, merId);
		List merList = query.getResultList();
		if(merList.size()>0){
			merApplyRecord = (MerApplyRecord)merList.get(0);
			/**Bean2DTO*/
			BeanUtils.copyProperties(merApplyRecord,merApplyRecordDTO,new String[]{"descr"});
			if(merApplyRecord.getDescr()!=null){
				try {
					merApplyRecordDTO.setDescr(new String(merApplyRecord.getDescr(),"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return merApplyRecordDTO;
		}else{
			return null;
		}
		
	}	
	
	/**
	 *   审核通过
	 *@Title:passAudit
	 *@Description:
	 *@param:@param recordIds
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public void passAudit(String recordIds) throws Exception {
		String[] record = recordIds.split(",");
		for(int i=0;i<record.length;i++){
			MerApplyRecord merApplyRecord = this.find(Integer.parseInt(record[i]));
			merApplyRecord.setStatus("2");
			this.update(merApplyRecord);
		}
		
	}

	/**
	 *   审核拒绝  sign 1 拒绝 2 取消
	 *@Title:passAudit
	 *@Description:
	 *@param:@param recordIds
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */

	public void refuseAudit(String recordIds,MerApplyRecordDTO merApplyRecordDTO) throws Exception {
		
		String[] record = recordIds.split(",");
		String sign = merApplyRecordDTO.getStatus();
		for(int i=0;i<record.length;i++){
			MerApplyRecord merApplyRecord = this.find(Integer.parseInt(record[i]));
			
			
			
			if(merApplyRecordDTO.getDescr()!=null){
				try {
					merApplyRecord.setDescr(merApplyRecordDTO.getDescr().getBytes("UTF8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			
			if(sign.equals("1")){
				merApplyRecord.setStatus("3");
			}else if(sign.equals("2")){
				merApplyRecord.setStatus("9");
			}
			
			this.update(merApplyRecord);
			
		 }
			
			
	 }
	




	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.buss.MerApplyRecordDao#passAllAudit(int)
	 *@MethodName:passAllAudit
	 *@Description:全部审核通过
	 *@param proid
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-8-26下午10:26:06
	 */
	
	public void passAllAudit(int proid) throws Exception {
		String sql = "update b_merapplyrecord record  set record.status='2' where record.status='1' and record.proid=? ";
		Query query = em.createNativeQuery(sql);
		query.setParameter(1,proid);
		
		query.executeUpdate();
		
	}




	
		

}
