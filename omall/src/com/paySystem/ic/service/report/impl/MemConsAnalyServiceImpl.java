package com.paySystem.ic.service.report.impl;

import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.paySystem.ic.dao.report.MemConsAnalyDAO;
import com.paySystem.ic.service.report.MemConsAnalyService;
import com.paySystem.ic.web.dto.report.MemConsAnalyDTO;

/**
 * @ClassName:MemConsAnalyServiceImpl
 * @Description:会员消费分析ServiceImpl
 * @date: 2014-4-3下午02:59:54
 * @author: 张亚运
 * @version: V1.0
 */
@Service(MemConsAnalyService.MEMCONSANALYSERVICE)
public class MemConsAnalyServiceImpl implements MemConsAnalyService {

	@Resource
	MemConsAnalyDAO memConsAnalyDao;

	/**
	 *@Title:queryAll
	 *@Description:查询ServiceImpl
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
			MemConsAnalyDTO memlistDto, LinkedHashMap<String, String> orderBy) {
		return memConsAnalyDao.queryAll(page, pageNum, memlistDto, orderBy);
	}

}
