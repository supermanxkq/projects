package com.paySystem.ic.service.collection.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.dao.collection.CollectionDAO;
import com.paySystem.ic.service.collection.CollectionService;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.collection.MainTaskDTO;

/**
 * @ProjectName:TimeManager
 * @ClassName:CollectionServiceImpl
 * @Description:收集箱服务实现类
 * @date: 2014-10-18下午11:15:34
 * @author: 徐凯强
 * @version: V1.0
 */
@Service(CollectionService.COLLECTIONSERVICE)
public class CollectionServiceImpl  implements
		CollectionService {

	/** 注入CollectionDAO */
	@Resource
	private CollectionDAO collectionDAO;

	/**
	 *@Title:findAll
	 *@Description:查询所有的主任务
	 *@param firstindex分页的首个参数
	 *@param pageNum每页有多少条数据
	 *@param mainTaskDTO主任务数据传输对象
	 *@param orderBy排序参数
	 *@Return:QueryResult<MainTaskDTO>主任务记录和总条数集合
	 *@author:徐凯强
	 *@Date:2014-10-12下午04:15:49
	 */
	public QueryResult<MainTaskDTO> findAll(int firstindex, int pageNum,
			MainTaskDTO mainTaskDTO, LinkedHashMap<String, String> orderBy) {
		/** 要返回的集合 */
		QueryResult<MainTaskDTO> aQueryResultDTO = collectionDAO.findAll(
				firstindex, pageNum, mainTaskDTO, orderBy);
		return aQueryResultDTO;
	}

	/**
	 *@Title:addSave
	 *@Description:保存主任务
	 *@param mainTaskDTO数据传输对象
	 *@Return:void返回值
	 *@author:徐凯强
	 *@Date:2014-10-18下午11:19:25
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSave(MainTaskDTO mainTaskDTO) {
		collectionDAO.addSave(mainTaskDTO);
	}
	

	/**
	 *@Title:updateMainTask
	 *@Description:更新
	 *@param mainTaskDTO主任务数据传输对象
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-10-18下午10:41:05
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateMainTask(MainTaskDTO mainTaskDTO) {
		collectionDAO.updateMainTask(mainTaskDTO);
	}
	/**
	 *@Title:findMainTaskDTO
	 *@Description:根据主键查询
	 *@Return:serviceListDTO
	 *@author:徐凯强
	 *@Date:2014-7-24上午09:19:56
	 */
	public MainTaskDTO findMainTaskDTO(Integer servId) {
		MainTaskDTO mainTaskDTO = new MainTaskDTO();
		try {
			mainTaskDTO = (MainTaskDTO) EntityDtoConverter.bean2Dto(
					collectionDAO.find(servId), mainTaskDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mainTaskDTO;
	}
	/**
	 *@MethodName:queryAll 
	 *@Description:查询所有的主任务
	 *@author:徐凯强
	 *@return List<MainTaskDTO>主任务数据传输对象
	 *@date:2014-12-24下午04:19:57
	 */
	@Override
	public List<MainTaskDTO> queryAll() {
		return collectionDAO.queryAll();
	}
	/**
	 *@MethodName:sendShortMessage 
	 *@Description:发送短信
	 *@param mainTaskDTO数据传输对象
	 *@author:徐凯强
	 *@return ReturnDTO返回对象DTO
	 *@date:2014-12-25下午06:38:26
	 */
	public ReturnDTO sendShortMessage(MainTaskDTO mainTaskDTO){
		return collectionDAO.sendShortMessage(mainTaskDTO);
	}
}
