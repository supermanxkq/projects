package com.supermanxkq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.supermanxkq.model.Order;
import com.supermanxkq.util.DbUtil;

/**
 * 
 * 订单dao
 * 
 * @author bolt
 *
 */
public class OrderDao {

	/**
	 * 获取数据库中订单的结果集
	 * 
	 * @param order
	 * @return
	 * @throws SQLException
	 */
	public ResultSet list(Connection connection,Order order) throws SQLException {
		connection=DbUtil.getCon();
		StringBuffer sql =new StringBuffer("select * from t_orders where 1=1");
		if(order.getCustomer()!=null&&!order.getCustomer().trim().equals("")){
			sql.append(" and customer like '%"+order.getCustomer()+"%'");
		}
		if(order.getGoodsName()!=null&&!order.getGoodsName().trim().equals("")){
			sql.append(" and goodsName like '%"+order.getGoodsName()+"%'");
		}
		if(order.getStartDate()!=null&&!order.getStartDate().trim().equals("")&&order.getEndDate()!=null&&!order.getEndDate().trim().equals("")){
			sql.append(" and createTime>='"+order.getStartDate()+" 00:00:00' and createTime<='"+order.getEndDate()+" 23:59:59'");
		}
		sql.append(" order by createTime desc limit 0,100");
		PreparedStatement preparedStatement=connection.prepareStatement(sql.toString());
		System.out.println(sql.toString());
		return preparedStatement.executeQuery();
	}
	/**
	 * 添加新的订单
	 * @param connection
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public int add(Connection connection,Order order)throws Exception{
		connection=DbUtil.getCon();
		String sql ="insert into t_orders values(null,?,?,?,?,?,curdate())";
		PreparedStatement preparedStatement=connection.prepareStatement(sql.toString());
		preparedStatement.setString(1, order.getCustomer());
		preparedStatement.setString(2, order.getGoodsName());
		preparedStatement.setInt(3, order.getCount());
		preparedStatement.setFloat(4, order.getPrice());
		preparedStatement.setInt(5, order.getStatus());
		return preparedStatement.executeUpdate();
	}
	
	/***
	 * 更新订单信息
	 * @param connection
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public int update(Connection connection,Order order) throws Exception{
		  connection=DbUtil.getCon();
		  String sql="update t_orders set customer=?,goodsName=?,count=?,price=?,status=?,createTime=? where id=?";
		  PreparedStatement preparedStatement=connection.prepareStatement(sql);
		  preparedStatement.setString(1, order.getCustomer());
		  preparedStatement.setString(2, order.getGoodsName());
		  preparedStatement.setInt(3, order.getCount());
		  preparedStatement.setFloat(4, order.getPrice());
		  preparedStatement.setInt(5, order.getStatus());
		  preparedStatement.setString(6, "2016-02-03 18:34:00");
		  //preparedStatement.setDate(6, order.getCreateTime());
		  preparedStatement.setInt(7, order.getId());
		return preparedStatement.executeUpdate();
	}
	/**
	 * 删除订单操作
	 * @param connection
	 * @param id
	 * @return
	 */
	public int delete(Connection connection,Integer id){
		try{
		connection=DbUtil.getCon();
		String sql="delete from t_orders where id=?";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		return preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
}
