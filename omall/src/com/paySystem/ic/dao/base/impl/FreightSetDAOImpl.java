package com.paySystem.ic.dao.base.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import com.paySystem.ic.bean.base.FreightSet;
import com.paySystem.ic.dao.base.FreightSetDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.base.FreightSetDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ProjectName: omall_back
 * @ClassName: FreightSetDAOImpl
 * @Description: 运费设置
 * @date: 2014-11-14
 * @author: 廖晓远 
 * @version: V1.0
 */
@Repository(FreightSetDAO.FREIGHTSETDAO)
public class FreightSetDAOImpl extends DaoSupport<FreightSet> implements FreightSetDAO {

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.FreightSetDAO#findFreightSet()
	 *@MethodName: findFreightSet
	 *@Description: 查询运费设置
	 *@return
	 *@throws Exception
	 *@Author: 廖晓远 
	 *@Date: 2014-11-14下午03:18:46
	 */
	@SuppressWarnings("unchecked")
	@Override
	public FreightSetDTO findFreightSet() throws Exception {
		UserSession us =Utils.getUserSession();
		FreightSetDTO freightSetDTO = new FreightSetDTO();
		String merId = "";
		merId = us.getMerId();
		String sql = "from FreightSet o where o.merId ='"+merId+"'";
		Query query = em.createQuery(sql);
		List<FreightSet> list = query.getResultList();
		if(list.size()>0){
			FreightSet freightSet = list.get(0);
			freightSetDTO = (FreightSetDTO) EntityDtoConverter.bean2Dto(freightSet, new FreightSetDTO());
		}else{
			freightSetDTO =getSysFreightSet();
		}
		return freightSetDTO;
	}
	
	/**
	*@Title: getSysFreightSet
	*@Description: 获取商城运费
	*@Params: @return
	*@Params: @throws Exception
	*@Return: FreightSetDTO
	*@author: 廖晓远 
	*@Date: 2014-11-24下午05:12:57
	*/
	@SuppressWarnings("unchecked")
	private FreightSetDTO getSysFreightSet()throws Exception{
		UserSession us =Utils.getUserSession();
		FreightSetDTO freightSetDTO = new FreightSetDTO();
		String sql = "from FreightSet o where o.isSys =1";
		Query query = em.createQuery(sql);
		List<FreightSet> list = query.getResultList();
		if(list.size()>0){
			FreightSet freightSet = list.get(0);
			if(us.getUserLevel()!=0){
				freightSet.setFsId(null);
			}
			freightSetDTO = (FreightSetDTO) EntityDtoConverter.bean2Dto(freightSet, new FreightSetDTO());
		}
		return freightSetDTO;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.FreightSetDAO#updateOrSave(com.paySystem.ic.web.dto.base.FreightSetDTO)
	 *@MethodName: updateOrSave
	 *@Description: 保存运费设置
	 *@param freightSetDTO
	 * @throws Exception 
	 *@Author: 廖晓远 
	 *@Date: 2014-11-14下午03:18:46
	 */
	@Override
	public void updateOrSave(FreightSetDTO freightSetDTO) throws Exception {
		Integer isSys = 0;//是否商城默认
		FreightSet freightSet = (FreightSet) EntityDtoConverter.dto2Bean(freightSetDTO, new FreightSet());
		UserSession us =Utils.getUserSession();
		String merId = "";
		if(us.getUserLevel()!=0){
			merId = us.getMerId();
		}else{
			isSys = 1;
		}
		freightSet.setIsSys(isSys);
		freightSet.setMerId(merId);
		freightSet.setUpdateTime(getSysTime());
		if(null!=freightSet.getFsId()){
			this.update(freightSet);
		}else{
			this.save(freightSet);
		}
		
	}


}
