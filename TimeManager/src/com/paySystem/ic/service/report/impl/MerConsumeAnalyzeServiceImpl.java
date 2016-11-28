package com.paySystem.ic.service.report.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.dao.report.MerConsumeAnalyzeDao;
import com.paySystem.ic.service.report.MerConsumeAnalyzeService;
import com.paySystem.ic.web.dto.report.MerConsumeAnalyzeDTO;

@Service(MerConsumeAnalyzeService.MERCONSUMEANALYSERVICE)
public class MerConsumeAnalyzeServiceImpl implements MerConsumeAnalyzeService {

	@Resource
	MerConsumeAnalyzeDao merConsumeAnalyzeDao;

	/***
	 * 商户消费分析报表查询
	 * 
	 * @see com.paySystem.ic.service.report.MerConsumeAnalyzeService#queryAll(int,
	 *      int, com.paySystem.ic.web.dto.report.MerConsumeAnalyzeDTO,
	 *      java.util.LinkedHashMap)
	 * @Description:TODO
	 * @date: 2014-4-9上午10:06:57
	 * @author: 解文侠
	 * @version: V1.0
	 * @param page
	 * @param pageNum
	 * @param merConsumeAnalyzeDTo
	 * @param orderBy
	 * @return
	 * @throws Exception
	 */
	public List<MerConsumeAnalyzeDTO> queryAll(int page, int pageNum,
			MerConsumeAnalyzeDTO merConsumeAnalyzeDTo,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return merConsumeAnalyzeDao.queryAll(page, pageNum,
				merConsumeAnalyzeDTo, orderBy);
	}

	/***
	 * 导出报表调用
	 * 
	 * @see com.paySystem.ic.service.report.MerConsumeAnalyzeService#queryMerConsumeAnalyzeReport(com.paySystem.ic.web.dto.report.MerConsumeAnalyzeDTO,
	 *      java.util.LinkedHashMap)
	 * @Description:TODO
	 * @date: 2014-4-9上午10:07:09
	 * @author: 解文侠
	 * @version: V1.0
	 * @param merConsumeAnalyze
	 * @param orderBy
	 * @return
	 * @throws Exception
	 */
	public List<MerConsumeAnalyzeDTO> queryMerConsumeAnalyzeReport(
			MerConsumeAnalyzeDTO merConsumeAnalyze,
			LinkedHashMap<String, String> orderBy) throws Exception {
		// TODO Auto-generated method stub
		return merConsumeAnalyzeDao.queryMerConsumeAnalyzeReport(
				merConsumeAnalyze, orderBy);
	}

}
