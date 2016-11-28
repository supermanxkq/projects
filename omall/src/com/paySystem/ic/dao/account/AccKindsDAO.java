package com.paySystem.ic.dao.account;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.account.AccKinds;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.account.AccKindsDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

public interface AccKindsDAO extends DAO<AccKinds> {
	public static final String ACCKINDSDAO = "accKindsDAO";

	public QueryResult<AccKindsDTO> queryAll(int firstindex, int maxresult,
			AccKindsDTO accKindsDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;

	public void saveAccKinds(AccKindsDTO accKindsDTO) throws Exception;

	public AccKindsDTO findAccKinds(Integer kindId) throws Exception;

	public void updateAccKinds(AccKindsDTO accKindsDTO) throws Exception;

	public List<AccKinds> queryAll();

	/**
	 * 
	 *@Title:查出帐户类型的名字和ID
	 *@TODO:TODO
	 *@data:2014-4-8
	 *@return:List<OptionsInteger>
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<OptionsInteger> findType();
}
