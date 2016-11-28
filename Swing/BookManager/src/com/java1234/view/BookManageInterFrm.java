package com.java1234.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.java1234.dao.BookDao;
import com.java1234.dao.BookTypeDao;
import com.java1234.model.Book;
import com.java1234.model.BookType;
import com.java1234.util.DbUtil;
import com.java1234.util.StringUtil;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookManageInterFrm extends JInternalFrame {
	private JTable bookTable;
	private JTextField s_bookNameTxt;
	private JTextField s_author;
	private DbUtil dbUtil = new DbUtil();
	private BookDao bookDao = new BookDao();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private JComboBox s_bookTypeJcb;
	private JTextField idTxt;
	private JTextField bookNameTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField priceTxt;
	private JTextField authorTxt;
	private JRadioButton manJrb;
	private JRadioButton femaleJrb;
	private JTextArea bookDescTxt;
	private JComboBox bookTypeJcb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookManageInterFrm frame = new BookManageInterFrm();
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
	public BookManageInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("图书管理");
		setBounds(100, 100, 793, 767);

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u641C\u7D22\u6761\u4EF6",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 757,
										Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)).addContainerGap()));
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(
										groupLayout.createSequentialGroup().addContainerGap()
												.addComponent(panel, GroupLayout.PREFERRED_SIZE, 94,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 199,
														GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));

		JLabel label_2 = new JLabel("编号：");

		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setColumns(10);

		JLabel label_3 = new JLabel("名称：");

		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);

		JLabel label_4 = new JLabel("作者性别：");

		manJrb = new JRadioButton("男");
		buttonGroup.add(manJrb);
		manJrb.setSelected(true);

		femaleJrb = new JRadioButton("女");
		buttonGroup.add(femaleJrb);

		JLabel label_5 = new JLabel("价格：");

		priceTxt = new JTextField();
		priceTxt.setColumns(10);

		JLabel label_6 = new JLabel("作者：");

		authorTxt = new JTextField();
		authorTxt.setColumns(10);

		JLabel label_7 = new JLabel("类别：");

		bookTypeJcb = new JComboBox();

		JLabel label_8 = new JLabel("描述：");

		bookDescTxt = new JTextArea();

		JButton btnNewButton = new JButton("修改");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookUpdateActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(BookManageInterFrm.class.getResource("/images/modify.png")));

		JButton button_1 = new JButton("删除");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bookDeleteActionPerformed(evt);
			}
		});
		button_1.setIcon(new ImageIcon(BookManageInterFrm.class.getResource("/images/delete.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup().addGap(28)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup().addComponent(label_8)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(bookDescTxt,
														GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
										.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
												.addComponent(label_5).addComponent(label_2))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
												.addGroup(gl_panel_1.createSequentialGroup()
														.addComponent(idTxt, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(29).addComponent(label_3))
												.addGroup(gl_panel_1.createSequentialGroup()
														.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(label_6)))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel_1.createSequentialGroup()
														.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(label_4)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(manJrb)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(femaleJrb))
												.addGroup(gl_panel_1.createSequentialGroup()
														.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(label_7)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, 103,
																GroupLayout.PREFERRED_SIZE))))))
						.addGroup(gl_panel_1.createSequentialGroup().addGap(106).addComponent(btnNewButton).addGap(60)
								.addComponent(button_1)))
						.addContainerGap(201, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(20)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(label_2)
								.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addComponent(label_4).addComponent(manJrb)
						.addComponent(femaleJrb))
				.addGap(35)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(label_5)
						.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_6)
						.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_7).addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(28)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(label_8)
						.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
						.addComponent(button_1)).addGap(25)));
		panel_1.setLayout(gl_panel_1);

		JLabel lblNewLabel = new JLabel("图书名称：");

		s_bookNameTxt = new JTextField();
		s_bookNameTxt.setColumns(10);

		JLabel label = new JLabel("作者：");

		s_author = new JTextField();
		s_author.setColumns(10);

		JLabel label_1 = new JLabel("类别：");

		s_bookTypeJcb = new JComboBox();

		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookSearchActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(BookManageInterFrm.class.getResource("/images/search.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel.createSequentialGroup().addContainerGap(132, Short.MAX_VALUE).addComponent(lblNewLabel)
						.addGap(18)
						.addComponent(s_bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(label).addGap(18)
						.addComponent(s_author, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(36).addComponent(label_1).addGap(18)
						.addComponent(s_bookTypeJcb, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addGap(73).addComponent(button).addGap(33)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(button)
								.addComponent(s_bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(s_author, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(s_bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addComponent(lblNewLabel))
				.addContainerGap(35, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		bookTable = new JTable();
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				bookTableMousePressed(arg0);

			}
		});
		scrollPane.setViewportView(bookTable);
		bookTable.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "\u7F16\u53F7", "\u56FE\u4E66\u540D\u79F0",
						"\u56FE\u4E66\u4F5C\u8005", "\u6027\u522B", "\u4EF7\u683C", "\u63CF\u8FF0", "\u7C7B\u522B" }) {
					boolean[] columnEditables = new boolean[] { false, true, false, false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		getContentPane().setLayout(groupLayout);
		// 初始化下拉框
		fillBookType("search");
		// 初始化表单编辑的下拉框
		fillBookType("modify");
		// 初始化表格
		fillTable(new Book());
		// 设置文本域边框
		bookDescTxt.setBorder(new LineBorder(new java.awt.Color(127, 157, 185), 1, false));
	}

	/**
	 * 图书删除事件处理
	 * 
	 * @param evt
	 */
	private void bookDeleteActionPerformed(ActionEvent evt) {
		String id = idTxt.getText();
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要删除的记录！");
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
		if (n == 0) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				int deleteNum = bookDao.delete(con, id);
				if (deleteNum == 1) {
					JOptionPane.showMessageDialog(null, "删除成功！");
					this.resetValue();
					// 重新刷新数据
					this.fillTable(new Book());
				} else {
					JOptionPane.showMessageDialog(null, "删除失败！");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "删除失败！");
			} finally {
				try {
					dbUtil.closeCon(con);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 图书修改事件处理
	 * 
	 * @param e
	 */
	private void bookUpdateActionPerformed(ActionEvent e) {
		String id = idTxt.getText();
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的记录！");
			return;
		}
		String bookName = this.bookNameTxt.getText();
		String author = this.authorTxt.getText();
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
		BookType bookType = (BookType) bookTypeJcb.getSelectedItem();
		int bookTypeId = bookType.getId();
		Book book = new Book(Integer.parseInt(id), bookName, author, sex, Float.parseFloat(price), bookTypeId,
				bookDesc);
		Connection connection = null;
		try {
			connection = dbUtil.getCon();
			int updateNum = bookDao.update(connection, book);
			if (updateNum == 1) {
				JOptionPane.showMessageDialog(null, "图书修改成功！");
				resetValue();
				this.fillTable(new Book());
			} else {
				JOptionPane.showMessageDialog(null, "图书修改失败！");

			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "图书修改失败！");
			e1.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(connection);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 重置表单
	 */
	private void resetValue() {
		this.idTxt.setText("");
		bookNameTxt.setText("");
		authorTxt.setText("");
		priceTxt.setText("");
		manJrb.setSelected(true);
		bookDescTxt.setText("");
		// 如果下拉列表中有数据的时候
		if (bookTypeJcb.getItemCount() > 0) {
			bookTypeJcb.setSelectedIndex(0);
		}
	}

	/**
	 * 
	 * 表格行点击处理
	 * 
	 * @param arg0
	 */
	private void bookTableMousePressed(MouseEvent arg0) {
		int row = this.bookTable.getSelectedRow();
		this.idTxt.setText((String) bookTable.getValueAt(row, 0));
		this.bookNameTxt.setText((String) bookTable.getValueAt(row, 1));
		this.authorTxt.setText((String) bookTable.getValueAt(row, 2));
		String sex = (String) bookTable.getValueAt(row, 3);
		if ("男".equals(sex)) {
			this.manJrb.setSelected(true);
		} else if ("女".equals(sex)) {
			this.femaleJrb.setSelected(true);
		}
		this.priceTxt.setText((Float) bookTable.getValueAt(row, 4) + "");
		this.bookDescTxt.setText((String) bookTable.getValueAt(row, 5));
		String bookTypeName = (String) this.bookTable.getValueAt(row, 6);
		int n = this.bookTypeJcb.getItemCount();
		for (int i = 0; i < n; i++) {
			BookType item = (BookType) this.bookTypeJcb.getItemAt(i);
			if (item.getBookTypeName().equals(bookTypeName)) {
				this.bookTypeJcb.setSelectedIndex(i);
			}
		}
	}

	/**
	 * 图书查询事件处理
	 * 
	 * @param e
	 */
	private void bookSearchActionPerformed(ActionEvent evt) {
		String bookName = this.s_bookNameTxt.getText();
		String author = this.s_author.getText();
		BookType bookType = (BookType) this.s_bookTypeJcb.getSelectedItem();
		int bookTypeId = bookType.getId();
		Book book = new Book(bookName, author, bookTypeId);
		this.fillTable(book);
	}

	/**
	 * 初始化下拉框
	 * 
	 * @param type下拉框类型
	 */
	private void fillBookType(String type) {
		Connection connection = null;
		BookType bookType = null;
		try {
			connection = dbUtil.getCon();
			ResultSet rs = bookTypeDao.list(connection, new BookType());
			if ("search".equals(type)) {
				bookType = new BookType();
				bookType.setBookTypeName("请选择");
				bookType.setId(-1);
				this.s_bookTypeJcb.addItem(bookType);
			}
			while (rs.next()) {
				bookType = new BookType();
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				bookType.setId(rs.getInt("id"));
				if ("search".equals(type)) {
					this.s_bookTypeJcb.addItem(bookType);
				} else if ("modify".equals(type)) {
					this.bookTypeJcb.addItem(bookType);
				}
			}
		} catch (Exception e) {
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
	 * 初始化表格数据
	 * 
	 * @param book
	 */
	private void fillTable(Book book) {
		DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
		// 表格再查询前清空
		dtm.setRowCount(0);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = bookDao.list(con, book);
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("bookName"));
				v.add(rs.getString("author"));
				v.add(rs.getString("sex"));
				v.add(rs.getFloat("price"));
				v.add(rs.getString("bookDesc"));
				v.add(rs.getString("bookTypeName"));
				// 将集合中德数据添加到表格中
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
