package com.paySystem.ic.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class GeoJdbc {
	/**
	 * 功能：连接MYSQL数据库 
	 */ 
	public Map<String, String> readConfig(String fileName) {
		Map<String, String> map = new HashMap<String, String>();
		Properties prop = new Properties();
		try {
			prop.load(getClass().getResourceAsStream(fileName));
			map.put("url", prop.getProperty("geourl").toString());
			map.put("driver", prop.getProperty("geodriverClassName").toString());
			return map;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			String url = readConfig("/jdbc.properties").get("url");
			String drive = readConfig("/jdbc.properties").get("driver");
			Class.forName(drive);

			conn = DriverManager.getConnection(url);
			// System.out.println("连接成功");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 功能：查询product表中的所有数据 时间：2010-7-29
	 */

	public List<Object[]> query(String sql, Object[] arg) {
		List<Object[]> list = new ArrayList<Object[]>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = this.getConnection(); // 连接数据库
		try {
			psmt = conn.prepareStatement(sql);
			if (arg != null) {
				for (int i = 0; i < arg.length; i++) {
					psmt.setObject(i + 1, arg[i]);
				}
			}
			rs = psmt.executeQuery(); // rs获取查询结果
			ResultSetMetaData rsmd = rs.getMetaData(); // 用ResultSetMetaData对象获取rs对象的列的编号、类型和属性
			int columnNum = rsmd.getColumnCount(); // 获取结果集列数
			// 将查询结果存在数组
			while (rs.next()) {
				Object[] temp = new Object[columnNum];
				for (int i = 0; i < columnNum; i++) {
					temp[i] = rs.getObject(i + 1); // rs.getObject从1开始
				}
				list.add(temp);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.colse(conn, psmt, rs);
		}
		return null;
	}

	/**
	 * 功能：对产品表增删改查 时间：2010-7-29
	 */
	public boolean excuteSQL(String sql, Object[] arg) {
		PreparedStatement psmt = null;
		Connection conn = this.getConnection();
		conn = this.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			if (arg != null) {
				for (int i = 0; i < arg.length; i++) {
					psmt.setObject(i + 1, arg[i]);
				}
			}
			psmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.colse(conn, psmt);
		}
		return false;
	}

	/**
	 * 功能：关闭数据库连接,关闭SQL对象,关闭结果集对象 时间：2010-7-29
	 */
	public void colse(Connection conn, PreparedStatement psmt, ResultSet rs) {
		this.colse(psmt);
		this.colse(rs);
		this.colse(conn);
	}

	/**
	 * 功能：关闭数据库连接,关闭SQL对象
	 */

	public void colse(Connection conn, PreparedStatement psmt) {
		this.colse(psmt);
		this.colse(conn);
	}

	/**
	 * 功能：关闭数据库连接 时间：2010-7-29
	 */
	public void colse(Connection conn) {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能：关闭SQL对象 时间：2010-7-29
	 */
	public void colse(PreparedStatement psmt) {
		try {
			if (psmt != null)
				psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能：关闭数据库连接,关闭SQL对象,关闭结果集对象 时间：2010-7-29
	 */
	public void colse(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		GeoJdbc jdbc = new GeoJdbc();
		jdbc.getConnection();
	}

}
