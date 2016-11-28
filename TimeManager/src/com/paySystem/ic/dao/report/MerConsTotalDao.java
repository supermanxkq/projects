package com.paySystem.ic.dao.report;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.report.Trades;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.report.TermConsTotalDTO;

/**
 * @ClassName:MerConsTotalDao
 * @Description:商户消费汇总Dao
 * @date: 2014-3-4上午09:18:44
 * @author: 张亚运
 * @version: V1.0
 */
public interface MerConsTotalDao extends DAO<Trades> {
	
	
	public static final String MERCONSTOTALDAO= "merConsTotalDao";
	
	
	/**
	 *@Title:queryAll
	 *@Description:商户消费汇总查询
	 *@param:@return
	 *@param:@throws Exception
	 *@return:List<MerConsTotalDTO>
	 *@author:张亚运
	 *@thorws:
	 */
	public List<TermConsTotalDTO> queryAll(int page, int pageNum,
			TermConsTotalDTO merConsTotal,LinkedHashMap<String, String> orderBy)
			throws Exception;

	
	/**
	 *@Title:queryMerConsTotalReport
	 *@Description:导出报表调用
	 *@param:@param merConsTotalDTO
	 *@param:@param orderBy按xx排序
	 *@author:张亚运
	 *@thorws:
	 */
	public List<TermConsTotalDTO> queryMerConsTotalReport(TermConsTotalDTO merConsTotal,
			LinkedHashMap<String, String>orderBy);


}


