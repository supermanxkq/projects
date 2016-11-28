package com.lucene.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.lucene.service.LuceneService;

/**
 *  定时任务调用
 *  更   新   索   引
 * 
 * @ClassName:UpdateIndex
 * @Description:创建或更新索引实现类
 * @date: 2014-10-23上午11:19:56
 * @author: 谢洪飞
 * @version: V1.0
 */
public class UpdateIndex {

	@Autowired
	LuceneService luceneService; 
	public static Logger logger = Logger.getLogger(UpdateIndex.class);
	
	/**
	 *   更新索引实现方法
	 *     1.删除原有索引
	 *     2.创建新索引内容
	 * 
	 *@Title:update
	 *@Description:更新索引
	 *@param:
	 *@Return:void
	 *@author:谢洪飞
	 *@Thorws:
	 */
	public void update(){
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try
		 {
			//------  开始删除索引  --------
			logger.info("|--- 时间  ---|"+simpleDateFormat.format(new Date())+":开始删除原有索引..");
			//删除索引方法
			luceneService.emptyIndex();
			
			logger.info("|--- 时间  ---|"+simpleDateFormat.format(new Date())+":删除原有索引成功!");
			
			
			//------ 开始创建新索引  --------
			logger.info("|--- 时间  ---|"+simpleDateFormat.format(new Date())+":开始创建新的索引..");
			
			//创建新索引方法
			luceneService.buildIndex();
			//完成创建索引
			logger.info("|--- 时间  ---|"+simpleDateFormat.format(new Date())+":创建新索引完成!");
		  } 
		catch (Exception e)
		  {
			logger.info("|--- 时间  ---|"+simpleDateFormat.format(new Date())+":更新索引库发生异常,更新失败!");
			logger.info("|--- 异常信息  ---|");
			e.printStackTrace();
		  }
	}
}
