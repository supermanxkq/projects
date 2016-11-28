package com.java1234.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.java1234.dao.BookDao;
import com.java1234.dao.BookTypeDao;
import com.java1234.model.BookType;
import com.java1234.util.DbUtil;
import com.java1234.util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookTypeManageInterFrm extends JInternalFrame {
	private JTable bookTypeTable;
	private DbUtil dbUtil = new DbUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private BookDao bookDao = new BookDao();
	private JTextField s_bookTypeNameTxt;
	private JTextField idTxt;
	private JTextField bookTypeNameTxt;
	private JTextArea bookTypeDescTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeManageInterFrm frame = new BookTypeManageInterFrm();
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
	public BookTypeManageInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("图书类别管理");
		setBounds(100, 100, 522, 518);

		JScrollPane scrollPane = new JScrollPane();

		JLabel label = new JLabel("图书类别名称：");

		s_bookTypeNameTxt = new JTextField();
		s_bookTypeNameTxt.setColumns(10);

		JButton btnNewButton = new JButton("查询");
		btnNewButton.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/search.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeSearchActionPerformed(e);
			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JButton btnNewButton_1 = new JButton("修改");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookTypeUpdateActionEvent(arg0);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/modify.png")));

		JButton button = new JButton("删除");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeDeleteActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/delete.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING,
										groupLayout.createSequentialGroup().addGap(147).addComponent(btnNewButton_1)
												.addGap(83).addComponent(button).addContainerGap(122, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGap(46).addComponent(label)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(s_bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 139,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
								.addComponent(btnNewButton).addGap(60)).addGroup(
										Alignment.LEADING,
										groupLayout.createSequentialGroup().addContainerGap()
												.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 448,
														Short.MAX_VALUE)
												.addContainerGap())
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(17)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label)
								.addComponent(s_bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton))
						.addGap(18)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						.addGap(56).addComponent(panel, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(button)
								.addComponent(btnNewButton_1))
						.addContainerGap(23, Short.MAX_VALUE)));

		JLabel label_1 = new JLabel("编号：");

		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setColumns(10);

		JLabel label_2 = new JLabel("图书类别名称：");

		bookTypeNameTxt = new JTextField();
		bookTypeNameTxt.setColumns(10);

		JLabel label_3 = new JLabel("描述：");

		bookTypeDescTxt = new JTextArea();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(label_3)
								.addComponent(label_1))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(label_2).addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 127,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label_1)
								.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2).addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(27)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(label_3)
						.addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(25, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		bookTypeTable = new JTable();
		bookTypeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				bookTypeTableMousePressed(arg0);
			}
		});
		bookTypeTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "\u7F16\u53F7",
				"\u56FE\u4E66\u7C7B\u522B\u540D\u79F0", "\u56FE\u4E66\u7C7B\u522B\u63CF\u8FF0" }) {
			boolean[] columnEditables = new boolean[] { true, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTypeTable.getColumnModel().getColumn(0).setPreferredWidth(63);
		bookTypeTable.getColumnModel().getColumn(1).setPreferredWidth(109);
		scrollPane.setViewportView(bookTypeTable);
		getContentPane().setLayout(groupLayout);
		// 初始化填充表格
		this.fillTable(new BookType());
		// 设置文本域边框
		bookTypeDescTxt.setBorder(new LineBorder(new java.awt.Color(127, 157, 185), 1, false));
	}

	/**
	 * 图书类别删除事件处理
	 * 
	 * @param e
	 */
	private void bookTypeDeleteActionPerformed(ActionEvent e) {
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
				boolean  flag=bookDao.existBookByTypeId(con, id);
				if(flag){
					JOptionPane.showMessageDialog(null, "当前图书类别下有图书，不能删除此类别！");
					return;
				}
				int deleteNum = bookTypeDao.delete(con, id);
				if (deleteNum == 1) {
					JOptionPane.showMessageDialog(null, "删除成功！");
					this.resetValue();
					// 重新刷新数据
					this.fillTable(new BookType());
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
	 * 图书类别修改事件处理
	 * 
	 * @param arg0
	 */
	private void bookTypeUpdateActionEvent(ActionEvent arg0) {
		String id = idTxt.getText();
		String bookTypeName = bookTypeNameTxt.getText();
		String bookTypeDesc = bookTypeDescTxt.getText();
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的记录！");
			return;
		}
		if (StringUtil.isEmpty(bookTypeName)) {
			JOptionPane.showMessageDialog(null, "图书类别名称不能为空！");
			return;
		}
		BookType bookType = new BookType(Integer.parseInt(id), bookTypeName, bookTypeDesc);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int n = bookTypeDao.update(con, bookType);
			if (n == 1) {
				JOptionPane.showMessageDialog(null, "修改成功！");
				this.resetValue();
				// 重新刷新数据
				this.fillTable(new BookType());
			} else {
				JOptionPane.showMessageDialog(null, "修改失败！");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "修改失败！");
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * 鼠标点击表哥行事件处理
	 * 
	 * @param arg0
	 */
	private void bookTypeTableMousePressed(MouseEvent evt) {
		int row = bookTypeTable.getSelectedRow();
		// 获取第row行第1列的值
		idTxt.setText((String) bookTypeTable.getValueAt(row, 0));
		bookTypeNameTxt.setText((String) bookTypeTable.getValueAt(row, 1));
		bookTypeDescTxt.setText((String) bookTypeTable.getValueAt(row, 2));
	}

	/**
	 * 图书类别搜索事件处理
	 * 
	 * @param e
	 */
	private void bookTypeSearchActionPerformed(ActionEvent e) {
		String s_bookTypeName = this.s_bookTypeNameTxt.getText();
		BookType bookType = new BookType();
		bookType.setBookTypeName(s_bookTypeName);
		this.fillTable(bookType);
	}

	/**
	 * 初始化填充表格
	 * 
	 * @param bookType
	 */
	private void fillTable(BookType bookType) {
		DefaultTableModel dtm = (DefaultTableModel) bookTypeTable.getModel();
		// 表格再查询前清空
		dtm.setRowCount(0);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = bookTypeDao.list(con, bookType);
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("bookTypeName"));
				v.add(rs.getString("bookTypeDesc"));
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

	/**
	 * reset forms
	 */
	private void resetValue() {
		this.idTxt.setText("");
		this.bookTypeNameTxt.setText("");
		this.bookTypeDescTxt.setText("");
	}
}
