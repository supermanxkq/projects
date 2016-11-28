package com.paySystem.ic.service.software;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.software.SoftWareStyle;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.software.SoftWareStyleDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ProjectName:MarsPlan
 * @ClassName:SoftWareStyleService
 * @Description:软件分类服务类
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:Mar 9, 20168:43:33 PM
 */
public interface SoftWareStyleService extends DAO<SoftWareStyle> {
	/** 常量 */
	public static final String SOFTWARESTYLESERVICE = "SoftWareStyleService";

	/**
	 * @MethodName:queryAll
	 * @Description:查询所有的软件分类
	 * @throws Exception
	 * @author:徐凯强
	 * @return QueryResult<SoftWareStyle>
	 * @date:Mar 9, 20168:44:24 PM
	 */
	public QueryResult<SoftWareStyle> queryAll() throws Exception;

	/**
	 * @MethodName:queryAll
	 * @Description:后台获取所有的软件分类
	 * @param firstIndex
	 * @param pageNum
	 * @param softWareStyleDTO
	 * @param orderBy
	 * @return
	 * @throws Exception
	 * @author:徐凯强
	 * @return QueryResult<SoftWareStyle>
	 * @date:Mar 9, 20168:46:52 PM
	 */
	public QueryResult<SoftWareStyle> queryAll(int firstIndex, int pageNum, SoftWareStyleDTO softWareStyleDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;

	/**
	 * @MethodName:addSave
	 * @Description:软件分类添加
	 * @param softWareStyle
	 * @author:徐凯强
	 * @return void
	 * @date:Mar 9, 20168:46:03 PM
	 */
	public void addSave(SoftWareStyle softWareStyle);

	/**
	 * @MethodName:queryStyles
	 * @Description:后台添加软件时需要的阮佳妮类型集合
	 * @author:徐凯强
	 * @return List<OptionsInteger>
	 * @date:Mar 9, 20168:45:10 PM
	 */
	public List<OptionsInteger> queryStyles();

}
