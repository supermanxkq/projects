package com.supermanxkq.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.supermanxkq.dao.UserDao;
import com.supermanxkq.model.User;
import com.supermanxkq.util.DbUtil;
import com.supermanxkq.util.StringUtil;

import java.awt.Toolkit;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class LogOnFrm extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passWordTxt;
	private UserDao userDao=new UserDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogOnFrm frame = new LogOnFrm();
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
	public LogOnFrm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LogOnFrm.class.getResource("/images/car_towtruck_transportation_vehicle_yellow_64px_198_easyicon.net.png")));
		// 改变系统默认字体`````
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
		setResizable(false);
		setTitle("管理员登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("配件销售管理系统");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 23));
		lblNewLabel.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/car_towtruck_transportation_vehicle_yellow_64px_198_easyicon.net.png")));

		JLabel label = new JLabel("用户名：");
		label.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/userName.png")));

		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);

		JLabel label_1 = new JLabel("密码：");
		label_1.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/password.png")));

		JButton button = new JButton("登录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginActionPerformed(arg0);
			}
		});
		button.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/login.png")));

		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetValueActionPerformed(arg0);
			}
		});
		button_1.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/reset.png")));
		
		passWordTxt = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(81)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addComponent(button)
							.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
							.addComponent(button_1)
							.addGap(25))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_1)
								.addComponent(label))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(passWordTxt, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
								.addComponent(userNameTxt, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))))
					.addGap(93))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(passWordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		// 设置登录窗口居中
		this.setLocationRelativeTo(null);
	}

	/**
	 * 
	 * 登录处理事件
	 * @param arg0
	 */
	private void loginActionPerformed(ActionEvent arg0) {
		String userName=this.userNameTxt.getText();
		String passWord=new String(this.passWordTxt.getPassword());
		if(StringUtil.isEmpty(userName)){
			JOptionPane.showMessageDialog(null, "请输入用户名！");
			return;
		}
		if(StringUtil.isEmpty(passWord)){
			JOptionPane.showMessageDialog(null, "请输入密码！");
			return;
		}
		
		 Connection connection=DbUtil.getCon();
		User user=new User(userName, passWord);
		User resultUser=userDao.login(connection,user);
		if(resultUser!=null){
			//销毁当前窗口
			dispose();
			MainFrame mainFrm=new MainFrame();
			mainFrm.setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null, "用户名或密码错误！");
		}
	}

	/**
	 * 重置用户名密码处理事件
	 * @param arg0
	 */
	private void resetValueActionPerformed(ActionEvent arg0) {
		this.userNameTxt.setText("");
		this.passWordTxt.setText("");
	}
}
