package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java1234.model.BookType;
import com.java1234.util.StringUtil;

/**
 * 
 * BookTypeDao
 * 
 * @author bolt
 *
 */
public class BookTypeDao {
	/**
	 * add BookType
	 * 
	 * @param con
	 * @param bookType
	 * @return
	 * @throws SQLException
	 */
	public int add(Connection con, BookType bookType) throws SQLException {
		String sql = "insert into t_bookType values(null,?,?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, bookType.getBookTypeName());
		pre.setString(2, bookType.getBookTypeDesc());
		return pre.executeUpdate();
	}

	/**
	 * 查询所有的图书类别
	 * 
	 * @param con
	 * @param bookType
	 * @return
	 * @throws SQLException
	 */
	public ResultSet list(Connection con, BookType bookType) throws SQLException {
		StringBuffer sb = new StringBuffer("select * from t_bookType");
		if (StringUtil.isNotEmpty(bookType.getBookTypeName())) {
			sb.append(" and bookTypeName like '%" + bookType.getBookTypeName() + "%' ");
		}
		// 将第一个and替换成where
		PreparedStatement pre = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pre.executeQuery();
	}

	/**
	 * 删除图书类别
	 * 
	 * @param connection
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection connection, String id) throws Exception {
		String sql = "delete from t_bookType where id=?";
		PreparedStatement pre = connection.prepareStatement(sql);
		pre.setString(1, id);
		return pre.executeUpdate();
	}

	/**
	 * 更新图书类别
	 * @param nnection
	 * @return
	 */
	public int update(Connection connection, BookType bookType) throws Exception {
		String sql = "update t_bookType set bookTypeName=?,bookTypeDesc=? where id=?";
		PreparedStatement pre = connection.prepareStatement(sql);
		pre.setString(1, bookType.getBookTypeName());
		pre.setString(2, bookType.getBookTypeDesc());
		pre.setInt(3, bookType.getId());
		return pre.executeUpdate();
	}
}
