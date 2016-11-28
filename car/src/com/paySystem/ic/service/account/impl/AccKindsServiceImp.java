package com.paySystem.ic.service.account.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.dao.account.AccKindsDAO;
import com.paySystem.ic.service.account.AccKindsService;
import com.paySystem.ic.web.dto.account.AccKindsDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ClassName: AccKindsServiceImp
 * @Description: 账户类型管理service实现类
 * @author lily
 * @date 2013-12-13 下午03:11:33
 * @version V1.0
 */
@Service(AccKindsService.ACCKINDSSERVICE)
public class AccKindsServiceImp implements AccKindsService {
	@Resource
	AccKindsDAO accKindsDAO;

	public QueryResult<AccKindsDTO> queryAll(int firstindex, int maxresult,
			AccKindsDTO accKindsDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		QueryResult<AccKindsDTO> qr = accKindsDAO.queryAll(firstindex,
				maxresult, accKindsDTO, orderBy);
		return qr;
	}

	/**
	 * <p>
	 * Title: deleteAccKinds
	 * </p>
	 * <p>
	 * Description: 删除账户类型，仅逻辑删除
	 * </p>
	 * 
	 * @param id
	 * @see com.paySystem.ic.service.account.AccKindsService#deleteAccKinds(java.lang.String)
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteAccKinds(String id) throws Exception {
		// accKindsDAO.deleteAccKinds(id);

		AccKindsDTO accKindsDTO = accKindsDAO.findAccKinds(Integer.valueOf(id));
		accKindsDTO.setStatus(2);
		accKindsDAO.updateAccKinds(accKindsDTO);

	}

	/**
	 * <p>
	 * Title: find
	 * </p>
	 * <p>
	 * Description: 根据ID查询账户类型
	 * </p>
	 * 
	 * @param kindId
	 * @return
	 * @see com.paySystem.ic.service.account.AccKindsService#find(java.lang.Integer)
	 */
	public AccKindsDTO findAccKinds(Integer kindId) throws Exception {
		AccKindsDTO accKindsDTO = accKindsDAO.findAccKinds(kindId);
		return accKindsDTO;
	}

	/**
	 * <p>
	 * Title: saveAccKinds
	 * </p>
	 * <p>
	 * Description: 新增账户类型
	 * </p>
	 * 
	 * @param accKindsDTO
	 * @see com.paySystem.ic.service.account.AccKindsService#saveAccKinds(com.paySystem.ic.web.dto.account.AccKindsDTO)
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveAccKinds(AccKindsDTO accKindsDTO) throws Exception {
		accKindsDAO.saveAccKinds(accKindsDTO);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(AccKindsDTO accKindsDTO) throws Exception {
		accKindsDAO.updateAccKinds(accKindsDTO);
	}

	/**
	 * 
	 *@Title:findType
	 *@TODO:查出帐户类型的名字和ID
	 *@param:@return
	 *@author:孟凡岭
	 *@thorws:
	 */
	@Transactional
	public List<OptionsInteger> findType() {
		// TODO Auto-generated method stub
		return accKindsDAO.findType();
	}
}
