package com.supermanxkq.view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AboutInterFrm extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutInterFrm frame = new AboutInterFrm();
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
	public AboutInterFrm() {
		getContentPane().setForeground(Color.WHITE);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("关于徐半仙儿");
		setBounds(100, 100, 450, 300);

		JLabel lblv = new JLabel("配件销售管理系统V1.0");
		lblv.setIcon(new ImageIcon(AboutInterFrm.class
				.getResource("/images/car_towtruck_transportation_vehicle_yellow_64px_198_easyicon.net.png")));

		JLabel label = new JLabel("开发者：徐半仙儿");

		JLabel label_1 = new JLabel("2016-01-29");

		JButton btnhttpwwwxukaiqiangcom = new JButton("更多精彩访问：http://www.xukaiqiang.com");
		btnhttpwwwxukaiqiangcom.setForeground(Color.RED);
		btnhttpwwwxukaiqiangcom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new java.net.URI("http://www.xukaiqiang.com"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnhttpwwwxukaiqiangcom.setBackground(Color.LIGHT_GRAY);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(199, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(label_1)
								.addComponent(label))
						.addGap(139))
				.addGroup(Alignment.LEADING,
						groupLayout.createSequentialGroup().addGap(120).addComponent(lblv).addContainerGap(205,
								Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(91, Short.MAX_VALUE)
						.addComponent(btnhttpwwwxukaiqiangcom).addGap(76)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(60).addComponent(lblv)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(label).addGap(18)
						.addComponent(label_1).addGap(18).addComponent(btnhttpwwwxukaiqiangcom)
						.addContainerGap(52, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);
		
	}
}
