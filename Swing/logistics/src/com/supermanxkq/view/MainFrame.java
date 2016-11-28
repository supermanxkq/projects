package com.supermanxkq.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JDesktopPane mainJdp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class
				.getResource("/images/car_towtruck_transportation_vehicle_yellow_64px_198_easyicon.net.png")));
		setTitle("配件销售主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 491);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("基本数据管理");
		menu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/base.png")));
		menuBar.add(menu);

		JMenu menu_2 = new JMenu("订单管理");
		menu_2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/edit.png")));
		menu.add(menu_2);

		JMenuItem menuItem = new JMenuItem("添加订单");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addOrderActionPerformed(e);
			}
		});
		menuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/add.png")));
		menu_2.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("订单维护");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrderManagerInterFrm orderManagerInterFrm=new OrderManagerInterFrm();
				orderManagerInterFrm.setVisible(true);
				mainJdp.add(orderManagerInterFrm);
			}
		});
		menuItem_1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/modify.png")));
		menu_2.add(menuItem_1);

		JMenu menu_3 = new JMenu("商品管理");
		menu_3.setIcon(new ImageIcon(MainFrame.class.getResource("/images/bookTypeManager.png")));
		menu.add(menu_3);

		JMenuItem menuItem_2 = new JMenuItem("添加商品");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//添加商品事件
				addGoodsActionPerformed(e);
			}
		});
		menuItem_2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/add.png")));
		menu_3.add(menuItem_2);

		JMenuItem menuItem_3 = new JMenuItem("商品维护");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//创建商品interFrm
				GoodsManageInterFrm goodsManageInterFrm=new GoodsManageInterFrm();
				goodsManageInterFrm.setVisible(true);
				mainJdp.add(goodsManageInterFrm);
			}
		});
		menuItem_3.setIcon(new ImageIcon(MainFrame.class.getResource("/images/modify.png")));
		menu_3.add(menuItem_3);

		JMenuItem menuItem_4 = new JMenuItem("安全退出");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(null, "是否要退出系统？");
				if (result == 0) {
					dispose();
				}
			}
		});
		menuItem_4.setIcon(new ImageIcon(MainFrame.class.getResource("/images/exit.png")));
		menu.add(menuItem_4);

		JMenu menu_1 = new JMenu("关于我们");
		menu_1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/about.png")));
		menuBar.add(menu_1);

		JMenuItem menuItem_5 = new JMenuItem("关于我");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AboutInterFrm aboutInterFrm = new AboutInterFrm();
				aboutInterFrm.setVisible(true);
				mainJdp.add(aboutInterFrm);
			}
		});
		menuItem_5.setIcon(new ImageIcon(MainFrame.class.getResource("/images/me.png")));
		menu_1.add(menuItem_5);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		mainJdp = new JDesktopPane();
		mainJdp.setBackground(Color.WHITE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap().addComponent(mainJdp, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(mainJdp,
				GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE));
		contentPane.setLayout(gl_contentPane);
		// 设置jframe最大化
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	/**
	 * 添加商品事件处理
	 * @param e
	 */
	private  void addGoodsActionPerformed(ActionEvent e) {
			//创建interFrame
		AddGoodsInterFrm addGoodsInterFrm=new AddGoodsInterFrm();
			//设置显示
		addGoodsInterFrm.setVisible(true);
			//添加到manFrm中
		mainJdp.add(addGoodsInterFrm);
	}

	/**
	 * 
	 * 订单添加事件处理
	 * 
	 * @param e
	 */
	private void addOrderActionPerformed(ActionEvent e) {
		// 显示添加订单的窗口
		AddOrderInterFrm addOrderInterFrm = new AddOrderInterFrm();
		addOrderInterFrm.setVisible(true);
		mainJdp.add(addOrderInterFrm);
	}
}
