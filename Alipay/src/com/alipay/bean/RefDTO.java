package com.alipay.bean;



/**
 *   指向DTO
 *   
 * @ClassName:RefDTO
 * @Description:在实体中使用，表明该字段指向DTO中哪列字段
 * @date: 2014-7-23上午10:05:31
 * @author: 谢洪飞
 * @version: V1.0
 */
public @interface RefDTO {
	
	String refField();
	
}
