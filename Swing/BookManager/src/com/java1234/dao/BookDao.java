package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java1234.model.Book;
import com.java1234.model.BookType;
import com.java1234.util.StringUtil;

/**
 * 图书Dao类
 * 
 * @author Administrator
 */
public class BookDao {
	/**
	 * 图书添加
	 * 
	 * @param connection
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int add(Connection connection, Book book) throws Exception {
		String sql = "insert into t_book values(null,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, book.getBookName());
		preparedStatement.setString(2, book.getAuthor());
		preparedStatement.setString(3, book.getSex());
		preparedStatement.setFloat(4, book.getPrice());
		preparedStatement.setInt(5, book.getBookTypeId());
		preparedStatement.setString(6, book.getBookDesc());
		return preparedStatement.executeUpdate();
	}

	/**
	 * 查询书籍集合查询
	 * 
	 * @param con
	 * @param book
	 * @return
	 * @throws SQLException
	 */
	public ResultSet list(Connection con, Book book) throws SQLException {
		StringBuffer sb = new StringBuffer("select * from t_book b,t_bookType bt where b.bookTypeId=bt.id");
		if (StringUtil.isNotEmpty(book.getBookName())) {
			sb.append(" and b.bookName like '%" + book.getBookName() + "%' ");
		}
		if (StringUtil.isNotEmpty(book.getAuthor())) {
			sb.append(" and b.author like '%" + book.getAuthor() + "%' ");
		}
		if (book.getBookTypeId() != null && book.getBookTypeId() != -1) {
			sb.append(" and b.bookTypeId=" + book.getBookTypeId());
		}
		// 将第一个and替换成where
		PreparedStatement pre = con.prepareStatement(sb.toString());
		return pre.executeQuery();
	}

	/**
	 * 删除书籍
	 * @param connection
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int delete(Connection connection,String id)throws SQLException {
			String sql="delete from t_book where id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			return preparedStatement.executeUpdate();
	}
	
	
	/**
	 * 图书 信息的修改
	 * @param connection
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int update(Connection connection,Book book) throws Exception{
		String sql="update t_book set bookName=?,author=?,sex=?,price=?,bookDesc=?,bookTypeId=? where id=?";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, book.getBookName());
		preparedStatement.setString(2, book.getAuthor());
		preparedStatement.setString(3, book.getSex());
		preparedStatement.setFloat(4, book.getPrice());
		preparedStatement.setString(5, book.getBookDesc());
		preparedStatement.setInt(6, book.getBookTypeId());
		preparedStatement.setInt(7, book.getId());
		return preparedStatement.executeUpdate();
	}
	
	/**
	 * 指定图书类别下是否存在图书
	 * @param connection
	 * @param bookTypeId
	 * @return
	 * @throws Exception
	 */
	public boolean  existBookByTypeId(Connection connection,String bookTypeId)throws Exception{
		String sql="select * from t_book where bookTypeId=?";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, bookTypeId);
		ResultSet rs=preparedStatement.executeQuery();
		return rs.next();
	}
	
}
