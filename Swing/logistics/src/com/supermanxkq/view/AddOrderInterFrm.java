package com.supermanxkq.view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.mysql.jdbc.Util;
import com.supermanxkq.dao.OrderDao;
import com.supermanxkq.model.Goods;
import com.supermanxkq.model.Order;
import com.supermanxkq.util.DbUtil;

import java.awt.event.ActionListener;
import java.io.UTFDataFormatException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AddOrderInterFrm extends JInternalFrame {
	private JTextField customerTxt;
	private JTextField priceTxt;
	private JTextField countTxt;
	private JTextField statusTxt;
	private OrderDao orderDao=new OrderDao();
	private JComboBox goodsNamejmb;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddOrderInterFrm frame = new AddOrderInterFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddOrderInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("添加订单");
		setBounds(100, 100, 450, 300);

		JLabel label = new JLabel("购买商品：");

		JLabel label_1 = new JLabel("顾客：");

		customerTxt = new JTextField();
		customerTxt.setColumns(10);

		JLabel label_2 = new JLabel("单价：");

		priceTxt = new JTextField();
		priceTxt.setColumns(10);

		JLabel label_3 = new JLabel("数量：");

		countTxt = new JTextField();
		countTxt.setColumns(10);

		JLabel label_4 = new JLabel("状态：");

		statusTxt = new JTextField();
		statusTxt.setColumns(10);

		JButton button = new JButton("添加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addOrderActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(AddOrderInterFrm.class.getResource("/images/add.png")));

		JButton button_1 = new JButton("重置");
		button_1.setIcon(new ImageIcon(AddOrderInterFrm.class.getResource("/images/reset.png")));
		
		 goodsNamejmb = new JComboBox();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_2)
								.addComponent(label_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(customerTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(label)
								.addComponent(label_3))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(goodsNamejmb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(18))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(countTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(statusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(241, Short.MAX_VALUE))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(98)
					.addComponent(button)
					.addGap(53)
					.addComponent(button_1)
					.addContainerGap(256, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(label_1)
						.addComponent(customerTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(goodsNamejmb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3)
						.addComponent(countTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(statusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1)
						.addComponent(button))
					.addContainerGap(10, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		//初始化购买商品
		queryGoods();
	}

	/**
	 * 
	 * 添加订单到数据库
	 * 
	 * @param e
	 */
	private void addOrderActionPerformed(ActionEvent e) {
		Connection connection = null;
		try {
			connection = DbUtil.getCon();
			Order order = new Order();
			order.setCustomer(customerTxt.getText());
			Goods goods=(Goods) goodsNamejmb.getSelectedItem();
			order.setGoodsName(goods.getGoodsName());
			order.setCount(Integer.parseInt(countTxt.getText()));
			order.setPrice(Float.parseFloat(priceTxt.getText()));
			order.setStatus(Integer.parseInt(statusTxt.getText()));
			order.setCreateTime(new Date());
			int addNum=orderDao.add(connection, order);
			if(addNum==1){
				JOptionPane.showMessageDialog(null, "订单添加成功！");
			}else{
				JOptionPane.showMessageDialog(null, "订单添加失败！");
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "订单添加失败！");
			e2.printStackTrace();
		} finally {
			DbUtil.closeCon(connection);
		}
	}
	/**
	 * 初始化商品名称
	 * @param connection
	 * @return
	 */
	private void queryGoods(){
		try{
		Connection connection=DbUtil.getCon();
		String sql="select id,goodsName from t_goods";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		ResultSet resultSet= preparedStatement.executeQuery();
		while(resultSet.next()){
			Goods goods =new Goods();
			goods.setId(resultSet.getInt("id"));
			goods.setGoodsName(resultSet.getString("goodsName"));
			goodsNamejmb.addItem(goods);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
