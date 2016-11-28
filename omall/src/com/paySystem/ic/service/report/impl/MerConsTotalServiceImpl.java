package com.paySystem.ic.service.report.impl;

import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.paySystem.ic.dao.report.MerConsTotalDao;
import com.paySystem.ic.service.report.MerConsTotalService;
import com.paySystem.ic.web.dto.report.TermConsTotalDTO;

/**
 * @ClassName:MerConsTotalServiceImpl
 * @Description:TODO商户消费统计ServiceImpl
 * @date: 2014-2-27上午11:51:47
 * @author: 张亚运
 * @version: V1.0
 */


@Service(MerConsTotalService.MERCONSTOTALSERVICE)
public class MerConsTotalServiceImpl implements MerConsTotalService {

	
	
	@Resource MerConsTotalDao merConsTotalDao;
	/**
	 *@Title:queryAll
	 *@Description:商户消费汇总查询Service
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:张亚运
	 */
	public List<TermConsTotalDTO> queryAll(int page, int pageNum,
			TermConsTotalDTO merConsTotal,
			LinkedHashMap<String, String> orderBy) throws Exception {
		
		
		return merConsTotalDao.queryAll(page, pageNum, merConsTotal, orderBy);
	}


	/**
	 *@Title:queryMerConsTotalReport
	 *@Description:导出报表Service
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:张亚运
	 */
	public List<TermConsTotalDTO> queryMerConsTotalReport(
			TermConsTotalDTO merConsTotal,
			LinkedHashMap<String, String> orderBy) {
		// TODO Auto-generated method stub
		return merConsTotalDao.queryMerConsTotalReport(merConsTotal, orderBy);
	}
	

}
