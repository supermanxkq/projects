package com.supermanxkq.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * 数据库操作工具类
 * 
 * @author Administrator
 *
 */
public class DbUtil {
	// 驱动
	public static final String driverClass = "com.mysql.jdbc.Driver";
	// 连接
	public static final String url = "jdbc:mysql://localhost:3306/logistics";
	// 用户名
	public static final String userName = "root";
	// 密码
	public static final String passWord = "root";

	/**
	 * 获取数据库的连接
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getCon()  {
		Connection connection=null;
		try {
			Class.forName(driverClass);
			 try {
				connection = DriverManager.getConnection(url, userName, passWord);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	/**
	 * 关闭数据库连接
	 * @param connection
	 * @throws SQLException
	 */
	public static void closeCon(Connection connection){
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	/**
	 * 测试
	 * 
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DbUtil dbUtil = new DbUtil();
		System.out.println(dbUtil.getCon());
	}
}
