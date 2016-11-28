package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java1234.model.User;

/**
 * userDao
 * 
 * @author Administrator
 *
 */
public class UserDao {
	/**
	 * login
	 * 
	 * @return
	 * @throws SQLException
	 */
	public User login(Connection con, User user) throws SQLException {
		User resultUser = null;
		String sql = "select * from t_user where userName=? and password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			resultUser = new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setUserName(rs.getString("password"));
		}
		return resultUser;
	}
}
