package com.paySystem.ic.service.report;

import java.util.LinkedHashMap;
import java.util.List;
import com.paySystem.ic.web.dto.report.MemConsAnalyDTO;

/**
 * @ClassName:MemConsAnalyService
 * @Description:会员消费分析Service
 * @date: 2014-4-3下午02:54:25
 * @author: 张亚运
 * @version: V1.0
 */
public interface MemConsAnalyService {

	public static final String MEMCONSANALYSERVICE = "memConsAnalyService";

	/**
	 *@Title:queryAll
	 *@Description:查询方法Service
	 *@param:@param page
	 *@param:@param pageNum
	 *@param:@param merConsTotal
	 *@param:@param orderBy
	 *@param:@return
	 *@param:@throws Exception
	 *@return:List<TermConsTotalDTO>
	 *@author:张亚运
	 *@thorws:
	 */
	public List<MemConsAnalyDTO> queryAll(int page, int pageNum,
			MemConsAnalyDTO memlistDto, LinkedHashMap<String, String> orderBy);
}
