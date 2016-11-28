package com.supermanxkq.dao;

import java.security.acl.Permission;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.supermanxkq.model.Goods;
import com.supermanxkq.util.DbUtil;

public class GoodsDao {
	/**
	 * 添加商品
	 */
	public  int addGoods(Connection connection,Goods goods) throws Exception{
		connection=DbUtil.getCon();
		String sql="insert into t_goods values(null,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, goods.getGoodsName());
		preparedStatement.setFloat(2, goods.getPrice());
		preparedStatement.setInt(3, goods.getCount());
		preparedStatement.setString(4, goods.getModel());
		preparedStatement.setString(5, goods.getMerchant());
		preparedStatement.setInt(6, goods.getStatus());
		preparedStatement.setString(7, "2016-02-04 23:22:00");
		preparedStatement.setString(8, goods.getGoodsDesc());
		return preparedStatement.executeUpdate();
	}
	
	/**
	 * 查询所有的商品信息
	 * @param connection
	 * @param goods
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection connection,Goods goods) throws Exception{
		connection=DbUtil.getCon();
		StringBuffer sql=new StringBuffer("select * from t_goods where 1=1");
		if(goods.getGoodsName()!=null&&!goods.getGoodsName().trim().equals("")){
			sql.append(" and goodsName like '%"+goods.getGoodsName()+"%'");
		}
		if(goods.getMerchant()!=null&&!goods.getMerchant().trim().equals("")){
			sql.append(" and merchant like '%"+goods.getMerchant()+"%'");
		}
		PreparedStatement preparedStatement=connection.prepareStatement(sql.toString());
		return preparedStatement.executeQuery();
	}
	/**
	 * 删除
	 * @param connection
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection connection,int id) {
		try{
		connection=DbUtil.getCon();
		String sql="delete from t_goods where id=?";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		return preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	
	/***
	 * 更新商品信息
	 * @param connection
	 * @param goods
	 * @return
	 */
	public int update(Connection connection,Goods goods){
		try {
			connection=DbUtil.getCon();
			String sql="update t_goods set goodsName=?,price=?,count=?,model=?,merchant=?,status=?,purchaseDate=?,goodsDesc=? where id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, goods.getGoodsName());
			preparedStatement.setFloat(2, goods.getPrice());
			preparedStatement.setInt(3, goods.getCount());
			preparedStatement.setString(4, goods.getModel());
			preparedStatement.setString(5, goods.getMerchant());
			preparedStatement.setInt(6, goods.getStatus());
			preparedStatement.setString(7, "2016-04-22 23:23:23");
			preparedStatement.setString(8, goods.getGoodsDesc());
			preparedStatement.setInt(9, goods.getId());
			int updateNum=preparedStatement.executeUpdate();
			return updateNum;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
