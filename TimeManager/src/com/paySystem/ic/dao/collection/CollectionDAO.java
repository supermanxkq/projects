package com.paySystem.ic.dao.collection;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.collection.MainTask;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.collection.MainTaskDTO;

/**
 * @ProjectName:TimeManager
 * @ClassName:CollectionDAO
 * @Description:收集箱DAO
 * @date: 2014-10-18下午10:38:22
 * @author: 徐凯强
 * @version: V1.0
 */
public interface CollectionDAO extends DAO<MainTask> {

	/** 常量 */
	public static final String COLLECTIONDAO = "CollectionDAO";

	/**
	 *@Title:findAll
	 *@Description:查询所有的主任务
	 *@param firstindex分页的首个参数
	 *@param pageNum每页有多少条数据
	 *@param mainTaskDTO主任务记录数据传输对象
	 *@param orderBy排序参数
	 *@Return:QueryResult<MainTaskDTO>主任务记录和总条数集合
	 *@author:徐凯强
	 *@Date:2014-10-18下午10:40:25
	 */
	public QueryResult<MainTaskDTO> findAll(int firstindex, int pageNum,
			MainTaskDTO mainTaskDTO, LinkedHashMap<String, String> orderBy);

	/**
	 *@Title:addSave
	 *@Description:保存主任务
	 *@param mainTaskDTO主任务数据传输对象
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-10-18下午10:41:05
	 */
	public void addSave(MainTaskDTO mainTaskDTO);

	/**
	 *@Title:updateMainTask
	 *@Description:更新
	 *@param mainTaskDTO主任务数据传输对象
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-10-18下午10:41:05
	 */
	public void updateMainTask(MainTaskDTO mainTaskDTO);

	/**
	 *@MethodName:queryAll
	 *@Description:查询所有的主任务
	 *@author:徐凯强
	 *@return List<MainTaskDTO>主任务数据传输对象
	 *@date:2014-12-24下午04:19:57
	 */
	public List<MainTaskDTO> queryAll();

	/**
	 *@MethodName:sendShortMessage
	 *@Description:发送短信
	 *@param mainTaskDTO数据传输对象
	 *@author:徐凯强
	 *@return ReturnDTO返回对象DTO
	 *@date:2014-12-25下午06:38:26
	 */
	public ReturnDTO sendShortMessage(MainTaskDTO mainTaskDTO);
}
