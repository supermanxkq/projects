package com.paySystem.ic.dao.collection.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.collection.MainTask;
import com.paySystem.ic.dao.collection.CollectionDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.collection.MainTaskDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ProjectName:TimeManager
 * @ClassName:CollectionDAOImpl
 * @Description:收集箱Dao实现类
 * @date: 2014-10-18下午10:44:25
 * @author: 徐凯强
 * @version: V1.0
 */
@Repository(CollectionDAO.COLLECTIONDAO)
public class CollectionDAOImpl extends DaoSupport<MainTask> implements
		CollectionDAO {
	/**
	 *@Title:findAll
	 *@Description:查询数据库中所有的主任务
	 *@param firstindex分页的首个参数
	 *@param pageNum每页有多少条数据
	 *@param mainTaskDTO主任务数据传输对象
	 *@param orderBy排序参数
	 *@Return:QueryResult<MainTaskDTO>主任务记录和总条数集合
	 *@author:徐凯强
	 *@Date:2014-10-18下午04:15:49
	 */
	public QueryResult<MainTaskDTO> findAll(int firstindex, int pageNum,
			MainTaskDTO mainTaskDTO, LinkedHashMap<String, String> orderBy) {
		/** jpql语句参数值 */
		List<Object> params = new ArrayList<Object>();
		/** 要返回的集合 */
		QueryResult<MainTaskDTO> gQueryResultDTO = new QueryResult<MainTaskDTO>();
		/** 接收结果的集合 */
		QueryResult<MainTask> gQueryResultEntity = new QueryResult<MainTask>();
		/** 要执行的SQL语句 */
		StringBuffer stringBuffer = new StringBuffer();
		/** 判断属性名称是否为空，如果不为空，进行查询 */
		stringBuffer.append(" and o.status!=0");
		stringBuffer.append(" and o.userName='"
				+ Utils.getUserSession().getUserName() + "'");

		/** 如果是今日待办请求该方法 */
		if (mainTaskDTO.getFlag() != null && mainTaskDTO.getFlag() == 1) {
			String nowDate = DateTimeTool.dateFormat("yyyy-MM-dd",
					new Date());
			stringBuffer.append(" and o.byDate like '%" + nowDate + "%'");
		}

		try {
			/** 获取数据库中所有属性记录 */
			gQueryResultEntity = this.getScrollData(firstindex, pageNum,
					stringBuffer.toString(), params.toArray(), orderBy);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/** 将实体转换为DTO */
		List<MainTaskDTO> mainTaskDTOs = new ArrayList<MainTaskDTO>();
		for (int i = 0; i < gQueryResultEntity.getResultlist().size(); i++) {
			MainTaskDTO mainTaskDTO2 = new MainTaskDTO();
			try {
				mainTaskDTO2 = (MainTaskDTO) EntityDtoConverter
						.bean2Dto(gQueryResultEntity.getResultlist().get(i),
								mainTaskDTO2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			mainTaskDTOs.add(mainTaskDTO2);
		}
		gQueryResultDTO.setResultlist(mainTaskDTOs);
		gQueryResultDTO.setTotalrecord(gQueryResultEntity.getTotalrecord());
		return gQueryResultDTO;
	}

	/**
	 *@Title:addSave
	 *@Description:保存主任务值记录
	 *@param mainTaskDTO数据传输对象
	 *@Return:void返回值
	 *@author:徐凯强
	 *@Date:2014-10-18下午04:15:49
	 */
	public void addSave(MainTaskDTO mainTaskDTO) {
		MainTask mainTask = new MainTask();
		mainTaskDTO.setUserName(Utils.getUserSession().getUserName());
		try {
			this.save(EntityDtoConverter.dto2Bean(mainTaskDTO, mainTask));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *@Title:updateMainTask
	 *@Description:更新
	 *@param mainTaskDTO主任务数据传输对象
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-7-22下午02:18:09
	 */
	public void updateMainTask(MainTaskDTO mainTaskDTO) {
		MainTask mainTask = new MainTask();
		UserSession us = null;
		try {
			us = Utils.getUserSession();
		} catch (Exception e) {
		}

		if (us != null) {
			mainTaskDTO.setUserName(Utils.getUserSession().getUserName());
		}
		try {
			this.update(EntityDtoConverter.dto2Bean(mainTaskDTO, mainTask));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *@MethodName:queryAll
	 *@Description:查询所有的主任务
	 *@author:徐凯强
	 *@return List<MainTaskDTO>主任务数据传输对象
	 *@date:2014-12-24下午04:19:57
	 */
	@SuppressWarnings("unchecked")
	public List<MainTaskDTO> queryAll() {
		/** 要返回的DTO集合 */
		List<MainTaskDTO> mainTaskDTOs = new ArrayList<MainTaskDTO>();
		/** 查询语句 */
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("select o from MainTask o where o.status=1");
		/** 查询数据库中所有的主任务 */
		List<MainTask> mainTasks = this.em.createQuery(stringBuffer.toString())
				.getResultList();
		/** 实体集合转换成DTO集合 */
		for (MainTask mainTask : mainTasks) {
			try {
				MainTaskDTO mainTaskDTO = new MainTaskDTO();
				mainTaskDTO = (MainTaskDTO) EntityDtoConverter.bean2Dto(
						mainTask, mainTaskDTO);
				mainTaskDTOs.add(mainTaskDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mainTaskDTOs;
	}

	/**
	 *@MethodName:sendShortMessage
	 *@Description:发送短信
	 *@param mainTaskDTO数据传输对象
	 *@author:徐凯强
	 *@return ReturnDTO返回对象DTO
	 *@date:2014-12-25下午06:38:26
	 */
	public ReturnDTO sendShortMessage(MainTaskDTO mainTaskDTO) {
		/** 获取服务商url、账户号、账户号值、口令、口令值、手机号码、服务器url值 */
		/** 拼接字符串到发送短信的方法中 */
		String result = null;

		StringBuffer requestParameters = new StringBuffer();
		String urlString = "http://3.ibtf.sinaapp.com/f.php";
		/** 账户 -账户名 */
		requestParameters.append("phone=15001164424");
		/** 口令 -口令值 */
		requestParameters.append("&pwd=0808xukaiqiang..");
		/** 内容-内容值 */
		String content = null;
		StringBuffer contentUnEncoding = new StringBuffer();
		contentUnEncoding.append("【任务名称】：" + mainTaskDTO.getMainTaskName());
		if (mainTaskDTO.getDescr() != null
				&& !mainTaskDTO.getDescr().equals("")) {
			contentUnEncoding.append("  【任务描述】：" + mainTaskDTO.getDescr());
		}
		try {
			content = java.net.URLEncoder.encode(contentUnEncoding.toString(),
					"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		requestParameters.append("&msg=" + content);
		/** 手机-手机值 */
		requestParameters.append("&to=" + mainTaskDTO.getToPhone());
		/** 类型-类型值 */
		requestParameters.append("&type=0");
		if (urlString.startsWith("http://")) {
			try {
				// Send data
				String urlStr = "http://3.ibtf.sinaapp.com/f.php";
				if (requestParameters != null && requestParameters.length() > 0) {
					urlStr += "?" + requestParameters;
				}
				URL url = new URL(urlStr);
				URLConnection conn = url.openConnection();

				// Get the response
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "UTF-8"));
				StringBuffer sb = new StringBuffer();
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				rd.close();
				result = sb.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ReturnDTO returnDTO = new ReturnDTO();
		return returnDTO;
	}
}
