package com.supermanxkq.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.supermanxkq.dao.OrderDao;
import com.supermanxkq.model.Order;
import com.supermanxkq.util.DateChooser;
import com.supermanxkq.util.DbUtil;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;

public class OrderManagerInterFrm extends JInternalFrame {
	private JTable orderListTable;
	private OrderDao orderDao = new OrderDao();
	private JScrollPane orderListJsp;
	private JTextField customerTxt;
	private JLabel label_1;
	private JTextField goodsNameTxt;
	private JLabel label_2;
	private JTextField startDateTxt;
	private JLabel label_3;
	private JTextField endDateTxt;
	private JTextField f_idTxt;
	private JTextField f_customerTxt;
	private JTextField f_goodsNameTxt;
	private JTextField f_priceTxt;
	private JTextField f_countTxt;
	private JTextField f_statusTxt;
	private JLabel label_11;
	private JTextField f_createTimeTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderManagerInterFrm frame = new OrderManagerInterFrm();
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
	public OrderManagerInterFrm() {
		//可以调整大小
		setResizable(true);
		//可以关闭
		setClosable(true);
		//最小化
		setIconifiable(true);
		//标题
		setTitle("订单列表界面");
		//窗口的大小
		setBounds(200, 100, 973, 880);
		//滚动面板
		orderListJsp = new JScrollPane();
		//设置
		orderListJsp.setBounds(53, 117, 872, 191);
		//实例化JTable
		orderListTable = new JTable();
		//鼠标点击监听事件
		orderListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				fillFormMousePressed(e);
			}
		});
		
		//设置表格
		orderListTable
				.setModel(new DefaultTableModel(new Object[][] {},
						new String[] { "\u7F16\u53F7", "\u987E\u5BA2\u540D\u79F0", "\u8D2D\u4E70\u5546\u54C1",
								"\u5355\u4EF7", "\u6570\u91CF", "\u603B\u8BA1", "\u72B6\u6001",
								"\u521B\u5EFA\u65F6\u95F4" }) {
					boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		//将表格添加到JScrollPane中
		orderListJsp.setViewportView(orderListTable);
		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		DateChooser dateChooser2 = DateChooser.getInstance("yyyy-MM-dd");

		JPanel panel = new JPanel();
		panel.setBounds(53, 18, 872, 93);
		panel.setBorder(
				new TitledBorder(null, "\u67E5\u8BE2\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel label = new JLabel("顾客名称：");

		customerTxt = new JTextField();
		customerTxt.setColumns(10);

		label_1 = new JLabel("购买商品：");

		goodsNameTxt = new JTextField();
		goodsNameTxt.setColumns(10);

		label_2 = new JLabel("创建时间：");
		startDateTxt = new JTextField();
		startDateTxt.setColumns(10);
		dateChooser1.register(startDateTxt);

		label_3 = new JLabel("至：");
		endDateTxt = new JTextField();
		endDateTxt.setColumns(10);
		dateChooser2.register(endDateTxt);

		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				serarchOrderActionPerformed(arg0);
			}
		});
		getContentPane().setLayout(null);
		button.setIcon(new ImageIcon(OrderManagerInterFrm.class.getResource("/images/search.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel.createSequentialGroup().addGap(19).addComponent(label)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(customerTxt, GroupLayout.PREFERRED_SIZE, 98,
												GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(label_1)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(goodsNameTxt, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(label_2)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(startDateTxt, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(label_3)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(endDateTxt, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE).addGap(42)
				.addComponent(button).addContainerGap(123, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(23)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label)
								.addComponent(customerTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(goodsNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2)
						.addComponent(startDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3)
						.addComponent(endDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addComponent(button))
				.addContainerGap(17, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		getContentPane().add(panel);
		getContentPane().add(orderListJsp);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(53, 324, 872, 253);
		getContentPane().add(panel_1);

		JLabel label_4 = new JLabel("编号：");

		f_idTxt = new JTextField();
		f_idTxt.setEditable(false);
		f_idTxt.setColumns(10);

		JLabel label_5 = new JLabel("顾客名称：");

		f_customerTxt = new JTextField();
		f_customerTxt.setColumns(10);

		JLabel label_6 = new JLabel("购买商品：");

		f_goodsNameTxt = new JTextField();
		f_goodsNameTxt.setColumns(10);

		JLabel label_7 = new JLabel("单价：");

		f_priceTxt = new JTextField();
		f_priceTxt.setColumns(10);

		JLabel label_8 = new JLabel("数量：");

		f_countTxt = new JTextField();
		f_countTxt.setColumns(10);

		JLabel label_9 = new JLabel("状态：");

		f_statusTxt = new JTextField();
		f_statusTxt.setColumns(10);

		JLabel label_10 = new JLabel("");

		JButton button_1 = new JButton("修改");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 修改订单信息
				updateOrderActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(OrderManagerInterFrm.class.getResource("/images/modify.png")));

		JButton button_2 = new JButton("删除");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//删除订单操作
				deleteOrderActionPerformed(e);
			}
		});
		button_2.setIcon(new ImageIcon(OrderManagerInterFrm.class.getResource("/images/delete.png")));

		label_11 = new JLabel("创建时间：");

		f_createTimeTxt = new JTextField();
		f_createTimeTxt.setEditable(false);
		f_createTimeTxt.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(26)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING).addComponent(label_9)
								.addComponent(label_4))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(f_idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(label_5).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(f_customerTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
						.addGap(27)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING,
										gl_panel_1.createSequentialGroup().addComponent(label_7).addGap(18)
												.addComponent(f_priceTxt, GroupLayout.PREFERRED_SIZE, 67,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(label_8)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(f_countTxt, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(131))
								.addGroup(gl_panel_1.createSequentialGroup()
										.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
												.addComponent(label_6).addComponent(button_2))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(f_goodsNameTxt, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(194, Short.MAX_VALUE))))
						.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(f_statusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup().addComponent(label_11)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(f_createTimeTxt, GroupLayout.PREFERRED_SIZE, 122,
														GroupLayout.PREFERRED_SIZE))
										.addComponent(label_10))
								.addContainerGap(438, Short.MAX_VALUE))))
				.addGroup(gl_panel_1.createSequentialGroup().addGap(147).addComponent(button_1).addContainerGap(909,
						Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(19)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(label_4)
								.addComponent(f_idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5)
						.addComponent(f_customerTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(f_priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addComponent(label_7).addComponent(label_8)
						.addComponent(f_countTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(31)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(label_9)
						.addComponent(f_statusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_10).addComponent(label_11)
						.addComponent(f_createTimeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_6).addComponent(f_goodsNameTxt, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE).addGroup(gl_panel_1
						.createParallelGroup(Alignment.BASELINE).addComponent(button_1).addComponent(button_2))
				.addGap(38)));
		panel_1.setLayout(gl_panel_1);
		// 初始化订单查询表格
		this.fillTable(new Order());
	}
	/**
	 * 删除订单操作事件处理
	 * @param e
	 */
	private void deleteOrderActionPerformed(ActionEvent e) {
		//获取要删除的订单的编号
		String id=f_idTxt.getText();
		Connection connection=null;
		//确认是否要删除订单
		if(JOptionPane.showConfirmDialog(null, "确定要删除该条订单吗？")==0){
			//调用orderDao中的方法进行删除
			int delNum=orderDao.delete(connection, Integer.parseInt(id));
			//根据删除后返回的结果进行提醒是否删除成功
			if(delNum==1){
				JOptionPane.showMessageDialog(null, "删除订单成功！");
				//重新填充表格
				this.fillTable(new Order());
				//重置控件内容
				resetValue();
			}else{
				JOptionPane.showMessageDialog(null, "删除订单失败！");
			}
		}
	}

	/**
	 * 修改订单信息处理事件
	 * 
	 * @param e
	 * @throws Exception
	 */
	private void updateOrderActionPerformed(ActionEvent e) {
		try {
			// 验证信息不能为空
			// 获取界面控件的信息
			String id = f_idTxt.getText();
			String customer = f_customerTxt.getText();
			String goodsName = f_goodsNameTxt.getText();
			String price = f_priceTxt.getText();
			String count = f_countTxt.getText();
			String status = f_statusTxt.getText();
			String createTime = f_createTimeTxt.getText();
			// 实例化订单对象
			Order order = new Order();
			order.setId(Integer.parseInt(id));
			order.setCustomer(customer);
			order.setGoodsName(goodsName);
			order.setPrice(Float.parseFloat(price));
			order.setCount(Integer.parseInt(count));
			order.setStatus(Integer.parseInt(status));
			order.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-02-03 19:53:00"));
			// 调用更新的方法进行更新
			Connection connection = null;
			int updateNum = orderDao.update(connection, order);
			if (updateNum == 1) {
				JOptionPane.showMessageDialog(null, "更新成功！");
				// 重新填充表格
				this.fillTable(new Order());
				//重置控件内容
				resetValue();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * 点击表格中的行事件处理
	 * 
	 * @param e
	 */
	protected void fillFormMousePressed(MouseEvent e) {
		// 获取点击的行
		int row = this.orderListTable.getSelectedRow();
		// 赋值
		this.f_idTxt.setText(orderListTable.getValueAt(row, 0) + "");
		this.f_customerTxt.setText((String) orderListTable.getValueAt(row, 1));
		this.f_goodsNameTxt.setText((String) orderListTable.getValueAt(row, 2));
		this.f_countTxt.setText(orderListTable.getValueAt(row, 4) + "");
		this.f_priceTxt.setText(orderListTable.getValueAt(row, 3) + "");
		this.f_statusTxt.setText(orderListTable.getValueAt(row, 6) + "");
		this.f_createTimeTxt.setText(orderListTable.getValueAt(row, 7) + "");
	}

	/**
	 * 查询订单事件处理
	 * 
	 * @param arg0
	 */
	private void serarchOrderActionPerformed(ActionEvent arg0) {
		String customer = customerTxt.getText();
		String goodsName = goodsNameTxt.getText();
		String startDate = startDateTxt.getText();
		String endDate = endDateTxt.getText();
		Order order = new Order();
		if (startDate != null) {
			if (endDate == null) {
				JOptionPane.showMessageDialog(null, "请选择结束时间！");
				return;
			} else {
				order.setStartDate(startDate);
				order.setEndDate(endDate);
			}
		}
		if (endDate != null) {
			if (startDate == null) {
				JOptionPane.showMessageDialog(null, "请选择开始时间！");
				return;
			} else {
				order.setStartDate(startDate);
				order.setEndDate(endDate);
			}
		}
		order.setCustomer(customer);
		order.setGoodsName(goodsName);
		this.fillTable(order);
	}

	/**
	 * 初始化订单查询表格
	 * 
	 * @param order
	 */
	private void fillTable(Order order) {
		Connection connection = null;
		try {
			DefaultTableModel dtm = (DefaultTableModel) orderListTable.getModel();
			// 表格再查询前清空
			dtm.setRowCount(0);
			connection = DbUtil.getCon();
			ResultSet resultSet = orderDao.list(connection, order);
			while (resultSet.next()) {
				Vector v = new Vector();
				v.add(resultSet.getInt("id"));
				v.add(resultSet.getString("customer"));
				v.add(resultSet.getString("goodsName"));
				v.add(resultSet.getFloat("price"));
				v.add(resultSet.getInt("count"));
				v.add(resultSet.getFloat("price") * resultSet.getInt("count"));
				v.add(resultSet.getInt("status"));
				v.add(new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("createTime")));
				// 将集合中德数据添加到表格中
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.closeCon(connection);
		}
	}
	/***
	 * 重置控件内容填写
	 */
	private void resetValue(){
		f_idTxt.setText("");
		f_customerTxt.setText("");
		f_goodsNameTxt.setText("");
		f_countTxt.setText("");
		f_priceTxt.setText("");
		f_createTimeTxt.setText("");
		f_statusTxt.setText("");
	}
}
