package com.paySystem.ic.dao.report;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.report.Trades;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.report.MerConsumeAnalyzeDTO;

/***
 * 商户消费分析报表DAo接口
 * 
 * @author 解文侠
 * 
 */
public interface MerConsumeAnalyzeDao extends DAO<Trades> {

	public static final String MERCONSUMEANALYZEDAO = "merConsumeAnalyzeDao";

	/***
	 * 商户消费分析报表查询
	 * 
	 * @Title:queryAll
	 *@param:@param page
	 *@param:@param pageNum
	 *@param:@param merConsumeAnalyzeDTo
	 *@param:@param orderBy
	 *@param:@return
	 *@param:@throws Exception
	 *@return:List<MerConsumeAnalyzeDTO>
	 *@author:解文侠
	 *@thorws:
	 */
	public List<MerConsumeAnalyzeDTO> queryAll(int page, int pageNum,
			MerConsumeAnalyzeDTO merConsumeAnalyzeDTo,
			LinkedHashMap<String, String> orderBy) throws Exception;

	/***
	 * 导出报表调用
	 * 
	 * @Title:queryMerConsumeAnalyzeReport
	 *@param:@param merConsumeAnalyzeDTo
	 *@param:@param orderBy
	 *@param:@return
	 *@return:List<MerConsumeAnalyzeDTO>
	 *@author:解文侠
	 *@thorws:
	 */
	public List<MerConsumeAnalyzeDTO> queryMerConsumeAnalyzeReport(
			MerConsumeAnalyzeDTO merConsumeAnalyze,
			LinkedHashMap<String, String> orderBy) throws Exception;
}
