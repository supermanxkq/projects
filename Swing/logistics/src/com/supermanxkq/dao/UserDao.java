package com.supermanxkq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.supermanxkq.model.User;
import com.supermanxkq.util.DbUtil;

/**
 * 用户dao
 * 
 * @author bolt
 *
 */
public class UserDao  {

	/**
	 * 用户登录
	 * 
	 * @param user
	 */
	public User login(Connection connection,User user) {
		User resultUser = null;
		try {
			connection = DbUtil.getCon();
			String sql = "select  * from t_user  where userName=? and passWord=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassWord());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				resultUser=new User();
				resultUser.setId(resultSet.getInt("id"));
				resultUser.setUserName(resultSet.getString("userName"));
				resultUser.setPassWord(resultSet.getString("passWord"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbUtil.closeCon(connection);
		}
		return resultUser;
	}
}
