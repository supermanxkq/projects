package com.paySystem.ic.dao.report;

import java.util.LinkedHashMap;
import java.util.List;
import com.paySystem.ic.bean.report.Trades;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.report.MemConsAnalyDTO;

/**
 * @ClassName:MemConsAnalyDAO
 * @Description:会员消费分析DAO
 * @date: 2014-4-3下午03:02:29
 * @author: 张亚运
 * @version: V1.0
 */
public interface MemConsAnalyDAO extends DAO<Trades> {

	public static final String MEMCONSANALYDAO = "memConsAnalyDao";

	/**
	 *@Title:queryAll
	 *@Description:查询方法
	 *@param:@param page
	 *@param:@param pageNum
	 *@param:@param memlistDto
	 *@param:@param orderBy
	 *@param:@return
	 *@param:@throws Exception
	 *@return:List<TermConsTotalDTO>
	 *@author:张亚运
	 */
	public List<MemConsAnalyDTO> queryAll(int page, int pageNum,
			MemConsAnalyDTO memlistDto, LinkedHashMap<String, String> orderBy);
}
