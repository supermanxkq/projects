package com.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName:blog 
 * @ClassName:OrderUtils  
 * @Description:生成订单号工具类生成的组成为年月日时分+4位流水号
 * @date: 2015-10-16上午11:41:16
 * @author: 半仙儿
 * @version: V1.0
 * @date:2015-10-16上午11:41:16
 */
public class OrderUtils extends Thread{
	private static long orderNum = 0l;
    private static String date ;
    
    public static void main(String[] args) throws InterruptedException {
    	for (int i = 0; i < 10000; i++) {
			System.out.println(OrderUtils.getOrderNo());
			Thread.sleep(1000);
		}
    }

    /**
     * 生成订单编号
     * @return
     */
    public static synchronized String getOrderNo() {
    	//年月日时分秒
        String str = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
        if(date==null||!date.equals(str)){
        	date = str;
        	orderNum  = 0l;
        }
        orderNum ++;
        long orderNo = Long.parseLong((date)) * 10000;
        orderNo += orderNum;;
        return orderNo+"";
    }
}

