package com.paySystem.ic.service.report;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.web.dto.report.MessConsTotalDTO;
import com.paySystem.ic.web.dto.report.TermConsTotalDTO;

/**
 * @ClassName:MerConsTotalService
 * @Description:短信费用统计Service接口
 * @date: 2014-2-27上午11:36:50
 * @author: 张国法
 * @version: V1.0
 */

public interface MessTotalService {

    public static final String MESSCONSTOTALSERVICE = "messConsTotalService";

    public List<MessConsTotalDTO> queryAll(int page, int pageNum, MessConsTotalDTO messConsTotalDTO,
            LinkedHashMap<String, String> orderBy) throws Exception;

	/**
	 *@Title:queryMerConsTotalReport
	 *@Description:导出Excel调用
	 *@param:@param merConsTotal
	 *@param:@param orderBy
	 *@param:@return
	 *@return:List<TermConsTotalDTO>
	 *@author:张国法
	 *@thorws:
	 */
	public List<MessConsTotalDTO> queryMessConsTotalReport(MessConsTotalDTO merConsTotal,
			LinkedHashMap<String, String>orderBy);

}
