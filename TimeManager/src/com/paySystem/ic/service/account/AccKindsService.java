package com.paySystem.ic.service.account;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.account.AccKindsDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

public interface AccKindsService {

	public static String ACCKINDSSERVICE = "accKindsService";

	public QueryResult<AccKindsDTO> queryAll(int i, int pageNum,
			AccKindsDTO accKindsDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;

	// public boolean validate(Integer kindId);

	public void saveAccKinds(AccKindsDTO accKindsDTO) throws Exception;

	public AccKindsDTO findAccKinds(Integer kindId) throws Exception;

	public void deleteAccKinds(String id) throws Exception;

	public void update(AccKindsDTO accKindsDTO) throws Exception;

	/**
	 * 
	 *@Title:findType
	 *@TODO:查出帐户类型的名字和ID
	 *@data:2014-4-8
	 *@return:List<OptionsInteger>
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<OptionsInteger> findType();

}
