package com.java1234.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.java1234.dao.BookDao;
import com.java1234.dao.BookTypeDao;
import com.java1234.model.Book;
import com.java1234.model.BookType;
import com.java1234.util.DbUtil;
import com.java1234.util.StringUtil;

public class BookAddInterFrm extends JInternalFrame {
	private JTextField bookNameTxt;
	private JTextField author;
	private JTextField priceTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private DbUtil dbUtil = new DbUtil();
	private BookDao bookDao = new BookDao();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private JComboBox bookTypeCmb = null;
	private JTextArea bookDescTxt = null;
	private JRadioButton manJrb = null;
	private JRadioButton femaleJrb = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookAddInterFrm frame = new BookAddInterFrm();
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
	public BookAddInterFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("图书添加");
		setBounds(100, 100, 450, 425);

		JLabel label = new JLabel("图书名称：");

		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);

		JLabel label_1 = new JLabel("作者：");

		author = new JTextField();
		author.setColumns(10);

		JLabel label_2 = new JLabel("性别：");

		JLabel label_3 = new JLabel("价格：");

		priceTxt = new JTextField();
		priceTxt.setColumns(10);

		manJrb = new JRadioButton("男");
		buttonGroup.add(manJrb);
		manJrb.setSelected(true);

		femaleJrb = new JRadioButton("女");
		buttonGroup.add(femaleJrb);

		bookTypeCmb = new JComboBox();

		JLabel label_4 = new JLabel("类别：");

		JLabel label_5 = new JLabel("图书描述：");

		bookDescTxt = new JTextArea();

		JButton button = new JButton("添加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 图书添加事件
				bookAddActionPerformed(arg0);
			}
		});
		button.setIcon(new ImageIcon(BookAddInterFrm.class.getResource("/images/add.png")));

		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetValueActionPerformed(arg0);
			}
		});
		button_1.setIcon(new ImageIcon(BookAddInterFrm.class.getResource("/images/reset.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGap(43)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(bookDescTxt,
										GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup().addComponent(label)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(label_4).addComponent(label_2))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(bookTypeCmb, Alignment.LEADING, 0,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGroup(Alignment.LEADING,
																groupLayout.createSequentialGroup().addComponent(manJrb)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(femaleJrb)))))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup().addComponent(label_1)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(author, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup().addComponent(label_3)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
								.addComponent(label_5)))
						.addGroup(groupLayout.createSequentialGroup().addGap(61).addComponent(button).addGap(56)
								.addComponent(button_1)))
				.addContainerGap(22, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(29)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label)
								.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1).addComponent(author, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(30)
				.addGroup(
						groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label_2).addComponent(label_3)
								.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(manJrb).addComponent(femaleJrb))
						.addGap(26)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(bookTypeCmb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_4))
						.addGap(37).addComponent(label_5).addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(button)
								.addComponent(button_1))
						.addContainerGap(34, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);
		// 设置文本域边框
		bookDescTxt.setBorder(new LineBorder(new java.awt.Color(127, 157, 185), 1, false));
		// 初始化图书类别下拉框
		fillBookType();
	}

	/**
	 * 重置事件处理
	 * 
	 * @param arg0
	 */
	protected void resetValueActionPerformed(ActionEvent arg0) {
		resetValue();
	}

	/**
	 * 图书添加事件处理
	 * 
	 * @param arg0
	 */
	protected void bookAddActionPerformed(ActionEvent arg0) {
		String bookName = this.bookNameTxt.getText();
		String author = this.author.getText();
		String price = this.priceTxt.getText();
		String bookDesc = this.bookDescTxt.getText();
		if (StringUtil.isEmpty(bookName)) {
			JOptionPane.showMessageDialog(null, "图书名称不能为空！");
			return;
		}
		if (StringUtil.isEmpty(author)) {
			JOptionPane.showMessageDialog(null, "作者不能为空！");
			return;
		}
		if (StringUtil.isEmpty(price)) {
			JOptionPane.showMessageDialog(null, "图书价格不能为空！");
			return;
		}

		String sex = "";
		if (manJrb.isSelected()) {
			sex = "男";
		} else if (femaleJrb.isSelected()) {
			sex = "女";
		}
		BookType bookType = (BookType) bookTypeCmb.getSelectedItem();
		int bookTypeId = bookType.getId();
		Book book = new Book(bookName, author, sex, Float.parseFloat(price), bookTypeId, bookDesc);
		Connection connection = null;
		try {
			connection = dbUtil.getCon();
			int addNum = bookDao.add(connection, book);
			if (addNum == 1) {
				JOptionPane.showMessageDialog(null, "图书添加成功！");
				resetValue();
			} else {
				JOptionPane.showMessageDialog(null, "图书添加失败！");
				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "图书添加失败！");
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(connection);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 重置表单
	 */
	private void resetValue() {
		bookNameTxt.setText("");
		author.setText("");
		priceTxt.setText("");
		manJrb.setSelected(true);
		bookDescTxt.setText("");
		// 如果下拉列表中有数据的时候
		if (bookTypeCmb.getItemCount() > 0) {
			bookTypeCmb.setSelectedIndex(0);
		}
	}

	/**
	 * 初始化图书类别下拉框
	 */
	private void fillBookType() {
		Connection connection = null;
		BookType bookType = null;
		try {
			connection = dbUtil.getCon();
			ResultSet rs = bookTypeDao.list(connection, new BookType());
			while (rs.next()) {
				bookType = new BookType();
				bookType.setId(rs.getInt("id"));
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				this.bookTypeCmb.addItem(bookType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}
