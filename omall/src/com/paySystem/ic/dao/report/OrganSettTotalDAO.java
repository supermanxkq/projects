package com.paySystem.ic.dao.report;

import java.util.LinkedHashMap;
import java.util.List;
import com.paySystem.ic.bean.report.OrganSettTotal;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.report.OrganSettTotalDTO;

/**
 * @ClassName:OrganSettTotalDAO
 * @Description:机构结算报表查询dao接口
 * @date: 2014-3-17下午06:27:36
 * @author: 王月
 * @version: V1.0
 */
public interface OrganSettTotalDAO extends DAO<OrganSettTotal> {
	public static final String ORGANSETTTOTALDAO = "organSettTotalDAO";
	
	/**
	 *@Title:queryMerSettTotal
	 *@Description:机构结算报表查询
	 *@param:@param fristindex
	 *@param:@param pageNum
	 *@param:@param merSettTotalDTO
	 *@param:@param orderBy
	 *@param:@return
	 *@param:@throws Exception
	 *@return:QueryResult<MerSettTotal>
	 *@author:王月
	 *@thorws:
	 */
	
	public List<OrganSettTotalDTO> queryAll(int page, int pageNum,
			OrganSettTotalDTO organSettTotal,LinkedHashMap<String, String> orderBy) throws Exception;
    /**
     *@Title:exportCardBlacklistXls
     *@Description:机构结算报表查询的导出
     *@param:@param blackDTO
     *@param:@param title
     *@param:@param response
     *@param:@return
     *@return:String
     *@author:王月
     *@thorws:
     */
	public List<OrganSettTotalDTO> queryorganSettTotalReport(OrganSettTotalDTO organSettTotal,
			LinkedHashMap<String, String> orderBy);

    
    

}
