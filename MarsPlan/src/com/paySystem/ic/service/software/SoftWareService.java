package com.paySystem.ic.service.software;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.software.SoftWare;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.software.SoftWareDTO;

/**
 * @ProjectName:MarsPlan
 * @ClassName:SoftWareService
 * @Description:软件服务类
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:Mar 9, 20168:52:31 PM
 */
public interface SoftWareService extends DAO<SoftWare> {
	/** 常量 */
	public static final String SOFTWARESERVICE = "SoftWareService";

	/**
	 * @MethodName:queryAll
	 * @Description:前台查询所有的软件集合
	 * @param firstIndex
	 * @param pageNum
	 * @param softWareDTO
	 * @param orderBy
	 * @return
	 * @throws Exception
	 * @author:徐凯强
	 * @return QueryResult<SoftWare>
	 * @date:Mar 9, 20168:53:02 PM
	 */
	public QueryResult<SoftWare> queryAll(int firstIndex, int pageNum, SoftWareDTO softWareDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;

	/**
	 * @MethodName:addSave
	 * @Description:后台添加软件信息
	 * @param softWareDTO
	 * @author:徐凯强
	 * @return void
	 * @date:Mar 9, 20168:53:29 PM
	 */
	public void addSave(SoftWareDTO softWareDTO);

	/**
	 * @MethodName:updateData
	 * @Description:后台更新软件库信息
	 * @param softWareDTO
	 * @author:徐凯强
	 * @return void
	 * @date:Mar 9, 20168:53:50 PM
	 */
	public void updateData(SoftWareDTO softWareDTO);
}
