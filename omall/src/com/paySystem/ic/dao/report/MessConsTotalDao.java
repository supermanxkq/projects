package com.paySystem.ic.dao.report;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.report.MessConsTotal;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.report.MessConsTotalDTO;
import com.paySystem.ic.web.dto.report.TermConsTotalDTO;

/**
 * @ClassName:MerConsTotalDao
 * @Description:短信费用汇总Dao
 * @date: 2014-11-18上午09:18:44
 * @author: 张国法
 * @version: V1.0
 */
public interface MessConsTotalDao extends DAO<MessConsTotal> {
	
	
	public static final String MESSCONSTOTALDAO= "messConsTotalDao";
	
	
	/**
	 *@Title:queryAll
	 *@Description:短信费用汇总查询
	 *@param:@return
	 *@param:@throws Exception
	 *@return:List<MerConsTotalDTO>
	 *@author:张国法
	 *@thorws:
	 */
	public List<MessConsTotalDTO> queryAll(int page, int pageNum,
			MessConsTotalDTO merConsTotal,LinkedHashMap<String, String> orderBy)
			throws Exception;

	
	/**
	 *@Title:queryMerConsTotalReport
	 *@Description:导出报表Service
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:张国法
	 */
	public List<MessConsTotalDTO> queryMessConsTotalReport(
			MessConsTotalDTO messConsTotalDTO,
			LinkedHashMap<String, String> orderBy);

}


