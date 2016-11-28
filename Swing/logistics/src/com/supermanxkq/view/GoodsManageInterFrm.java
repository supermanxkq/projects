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

import com.supermanxkq.dao.GoodsDao;
import com.supermanxkq.dao.OrderDao;
import com.supermanxkq.model.Goods;
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
import javax.swing.JTextArea;

public class GoodsManageInterFrm extends JInternalFrame {
	private JTable goodsListTable;
	private GoodsDao goodsDao = new GoodsDao();
	private JScrollPane orderListJsp;
	private JTextField goodsNameTxt;
	private JLabel label_1;
	private JTextField merchantTxt;
	private JTextField f_idTxt;
	private JTextField f_goodsNameText;
	private JTextField f_statusTxt;
	private JTextField f_modelTxt;
	private JTextField f_merchantTxt;
	private JTextField f_priceTxt;
	private JLabel label_11;
	private JTextField f_countTxt;
	private JTextField f_purchaseDateTxt;
	private JTextArea f_goodsDescTxt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodsManageInterFrm frame = new GoodsManageInterFrm();
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
	public GoodsManageInterFrm() {
		//可以调整大小
		setResizable(true);
		//可以关闭
		setClosable(true);
		//最小化
		setIconifiable(true);
		//标题
		setTitle("商品管理界面");
		//窗口的大小
		setBounds(200, 100, 973, 880);
		//滚动面板
		orderListJsp = new JScrollPane();
		//设置
		orderListJsp.setBounds(53, 117, 872, 191);
		//实例化JTable
		goodsListTable = new JTable();
		//鼠标点击监听事件
		goodsListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				fillFormMousePressed(e);
			}
		});
		
		//设置表格
		goodsListTable
				.setModel(new DefaultTableModel(new Object[][] {},
						new String[] { "编号", "商品名称", "型号","商家",
								"单价", "数量", "总计", "状态",
								"进货日期","描述" }) {
					boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false,false,false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		//将表格添加到JScrollPane中
		orderListJsp.setViewportView(goodsListTable);
		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		DateChooser dateChooser2 = DateChooser.getInstance("yyyy-MM-dd");

		JPanel panel = new JPanel();
		panel.setBounds(53, 18, 872, 93);
		panel.setBorder(
				new TitledBorder(null, "\u67E5\u8BE2\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel label = new JLabel("商品名称：");

		goodsNameTxt = new JTextField();
		goodsNameTxt.setColumns(10);

		label_1 = new JLabel("商家：");

		merchantTxt = new JTextField();
		merchantTxt.setColumns(10);

		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				serarchGoodsActionPerformed(arg0);
			}
		});
		getContentPane().setLayout(null);
		button.setIcon(new ImageIcon(GoodsManageInterFrm.class.getResource("/images/search.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(goodsNameTxt, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(merchantTxt, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addComponent(button)
					.addContainerGap(429, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(goodsNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(merchantTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().add(panel);
		getContentPane().add(orderListJsp);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(53, 324, 872, 310);
		getContentPane().add(panel_1);

		JLabel label_4 = new JLabel("编号：");

		f_idTxt = new JTextField();
		f_idTxt.setEditable(false);
		f_idTxt.setColumns(10);

		JLabel label_5 = new JLabel("商品名称：");

		f_goodsNameText = new JTextField();
		f_goodsNameText.setColumns(10);

		JLabel label_6 = new JLabel("状态：");

		f_statusTxt = new JTextField();
		f_statusTxt.setColumns(10);

		JLabel label_7 = new JLabel("型号：");

		f_modelTxt = new JTextField();
		f_modelTxt.setColumns(10);

		JLabel label_8 = new JLabel("商家：");

		f_merchantTxt = new JTextField();
		f_merchantTxt.setColumns(10);

		JLabel label_9 = new JLabel("单价：");

		f_priceTxt = new JTextField();
		f_priceTxt.setColumns(10);

		JLabel label_10 = new JLabel("");

		JButton button_1 = new JButton("修改");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 修改商品信息
				updateGoodsActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(GoodsManageInterFrm.class.getResource("/images/modify.png")));

		JButton button_2 = new JButton("删除");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//删除商品操作
				deleteGoodsActionPerformed(e);
			}
		});
		button_2.setIcon(new ImageIcon(GoodsManageInterFrm.class.getResource("/images/delete.png")));

		label_11 = new JLabel("数量：");

		f_countTxt = new JTextField();
		f_countTxt.setColumns(10);
		
		JLabel label_2 = new JLabel("进货日期：");
		
		f_purchaseDateTxt = new JTextField();
		f_purchaseDateTxt.setColumns(10);
		
		 f_goodsDescTxt = new JTextArea();
		
		JLabel label_3 = new JLabel("描述：");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_9)
								.addComponent(label_4))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(f_idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(label_5)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(f_goodsNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(27)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(label_7)
											.addGap(18)
											.addComponent(f_modelTxt, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(label_8)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(f_merchantTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(131))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGap(19)
											.addComponent(label_6)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(f_statusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addContainerGap(201, Short.MAX_VALUE))))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(f_priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(label_11)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(f_countTxt, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
										.addComponent(label_10))
									.addContainerGap(464, Short.MAX_VALUE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(68)
									.addComponent(button_1)
									.addGap(229)
									.addComponent(button_2)
									.addContainerGap())))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_3)
								.addComponent(label_2))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(f_purchaseDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(f_goodsDescTxt, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE))
							.addGap(439))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(f_idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5)
						.addComponent(f_goodsNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(f_modelTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_7)
						.addComponent(label_8)
						.addComponent(f_merchantTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_9)
						.addComponent(f_priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_10)
						.addComponent(label_11)
						.addComponent(f_countTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_6)
						.addComponent(f_statusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(f_purchaseDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(f_goodsDescTxt, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1)
						.addComponent(button_2))
					.addGap(14))
		);
		panel_1.setLayout(gl_panel_1);
		// 初始化商品查询表格
		this.fillTable(new Goods());
	}
	/**
	 * 搜索商品事件处理
	 * @param arg0
	 */
	private  void serarchGoodsActionPerformed(ActionEvent arg0) {
		String goodsName=goodsNameTxt.getText();
		String merchant=merchantTxt.getText();
		Goods goods=new Goods();
		goods.setGoodsName(goodsName);
		goods.setMerchant(merchant);
		this.fillTable(goods);
	}

	/**
	 * 删除商品操作事件处理
	 * @param e
	 */
	private void deleteGoodsActionPerformed(ActionEvent e) {
		//获取要删除的商品的编号
		String id=f_idTxt.getText();
		Connection connection=null;
		//确认是否要删除订单
		if(JOptionPane.showConfirmDialog(null, "确定要删除该编号为"+id+"商品吗？")==0){
			//调用goodsDao中的方法进行删除
			int delNum=goodsDao.delete(connection, Integer.parseInt(id));
			//根据删除后返回的结果进行提醒是否删除成功
			if(delNum==1){
				JOptionPane.showMessageDialog(null, "删除商品成功！");
				//重新填充表格
				this.fillTable(new Goods());
				//重置控件内容
				resetValue();
			}else{
				JOptionPane.showMessageDialog(null, "删除商品失败！");
			}
		}
	}

	/**
	 * 修改商品信息处理事件
	 * 
	 * @param e
	 * @throws Exception
	 */
	private void updateGoodsActionPerformed(ActionEvent e) {
		try {
			// 验证信息不能为空
			// 获取界面控件的信息
			String id = f_idTxt.getText();
			String goodsName = f_goodsNameText.getText();
			String model = f_modelTxt.getText();
			String merchant = f_merchantTxt.getText();
			String price = f_priceTxt.getText();
			String count = f_countTxt.getText();
			String status = f_statusTxt.getText();
			String purchaseDate=f_purchaseDateTxt.getText();
			String goodsDesc=f_goodsDescTxt.getText();
			// 实例化商品对象
			Goods goods = new Goods();
			goods.setId(Integer.parseInt(id));
			goods.setGoodsName(goodsName);
			goods.setModel(model);
			goods.setMerchant(merchant);
			goods.setPrice(Float.parseFloat(price));
			goods.setCount(Integer.parseInt(count));
			goods.setStatus(Integer.parseInt(status));
			goods.setPurchaseDate(new Date());
			goods.setGoodsDesc(goodsDesc);
			// 调用更新的方法进行更新
			Connection connection = null;
			int updateNum = goodsDao.update(connection, goods);
			if (updateNum == 1) {
				JOptionPane.showMessageDialog(null, "更新成功！");
				// 重新填充表格
				this.fillTable(new Goods());
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
		int row = this.goodsListTable.getSelectedRow();
		// 赋值
		this.f_idTxt.setText(goodsListTable.getValueAt(row, 0) + "");
		this.f_goodsNameText.setText((String) goodsListTable.getValueAt(row, 1));
		this.f_modelTxt.setText((String) goodsListTable.getValueAt(row, 2));
		this.f_merchantTxt.setText(goodsListTable.getValueAt(row, 3) + "");
		this.f_priceTxt.setText(goodsListTable.getValueAt(row, 4) + "");
		this.f_countTxt.setText(goodsListTable.getValueAt(row, 5) + "");
		this.f_statusTxt.setText(goodsListTable.getValueAt(row, 7) + "");
		this.f_purchaseDateTxt.setText(goodsListTable.getValueAt(row, 8) + "");
		this.f_goodsDescTxt.setText(goodsListTable.getValueAt(row, 9) + "");
	}

	/**
	 * 初始化订单查询表格
	 * 
	 * @param order
	 */
	private void fillTable(Goods goods) {
		Connection connection = null;
		try {
			DefaultTableModel dtm = (DefaultTableModel) goodsListTable.getModel();
			// 表格再查询前清空
			dtm.setRowCount(0);
			connection = DbUtil.getCon();
			ResultSet resultSet = goodsDao.list(connection, goods);
			while (resultSet.next()) {
				Vector v = new Vector();
				v.add(resultSet.getInt("id"));
				v.add(resultSet.getString("goodsName"));
				v.add(resultSet.getString("model"));
				v.add(resultSet.getString("merchant"));
				v.add(resultSet.getFloat("price"));
				v.add(resultSet.getInt("count"));
				v.add(resultSet.getFloat("price") * resultSet.getInt("count"));
				v.add(resultSet.getInt("status"));
				v.add(new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("purchaseDate")));
				v.add(resultSet.getString("goodsDesc"));
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
		f_goodsNameText.setText("");
		f_statusTxt.setText("");
		f_merchantTxt.setText("");
		f_modelTxt.setText("");
		f_countTxt.setText("");
		f_priceTxt.setText("");
		f_purchaseDateTxt.setText("");
		f_goodsDescTxt.setText("");
	}
}
