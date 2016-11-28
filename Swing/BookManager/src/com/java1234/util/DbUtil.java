package com.java1234.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Administrator DBUtil
 */
public class DbUtil {
	// url
	private String dbUrl = "jdbc:mysql://localhost:3306/db_book";
	// username
	private String dbUserName = "root";
	// password
	private String dbPassword = "root";
	// driver
	private String jdbcName = "com.mysql.jdbc.Driver";

	/**
	 * getCon
	 * 
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() throws Exception {
		Class.forName(jdbcName);
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}

	/**
	 * closeCon
	 * 
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception {
		if (con != null) {
			con.close();
		}
	}

	/**
	 * test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		DbUtil dbUtil = new DbUtil();
		try {
			System.out.println(dbUtil.getCon());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
