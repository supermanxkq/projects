package com.paySystem.ic.service.member;

import java.util.LinkedHashMap;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.Cards;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemCardDTO;

public interface MemCardService {
	public static final String MEMCARDSERVICE = "memCardService";
	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            实体id
	 */
	public void save(MemCardDTO MemCardDTO);
	/**
	 * 更新实体
	 * 
	 * @param entity
	 *            实体id
	 * @throws Exception 
	 */
	public ReturnDTO update(MemCardDTO MemCardDTO) throws Exception;
	public QueryResult<Cards> queryResult(int fristindex, int pageNum,
			MemCardDTO memCardDTO, LinkedHashMap<String, String> orderBy)
			throws Exception ;
	
		
}
