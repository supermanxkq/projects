package cn.goodym.bean;

import org.apache.log4j.Logger;

public class BaseLog {
	/** ȡ����־��¼��Logger */
	public static Logger logger = Logger.getLogger(BaseLog.class);	
	
	public static void log(String log){
		//�����̨����־�ļ��������־����
		logger.info(log);		
	}
}
