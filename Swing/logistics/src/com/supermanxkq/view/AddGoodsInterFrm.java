package com.supermanxkq.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.supermanxkq.dao.GoodsDao;
import com.supermanxkq.model.Goods;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;
import java.awt.event.ActionEvent;

public class AddGoodsInterFrm extends JInternalFrame {
	private JTextField goodsNameTxt;
	private JTextField countTxt;
	private JTextField priceTxt;
	private JTextField merchantTxt;
	private JLabel label_4;
	private JTextField modelTxt;
	private JLabel label_5;
	private JTextField purchaseDateTxt;
	private JLabel label_6;
	private JTextArea goodsDescTxt;
	private JComboBox statusCmb;
	private GoodsDao goodsDao=new GoodsDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddGoodsInterFrm frame = new AddGoodsInterFrm();
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
	public AddGoodsInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("添加商品");
		setBounds(100, 100, 652, 435);
		
		JLabel label = new JLabel("商品名称：");
		
		goodsNameTxt = new JTextField();
		goodsNameTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("数量：");
		
		countTxt = new JTextField();
		countTxt.setColumns(10);
		
		JLabel label_2 = new JLabel("单价：");
		
		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		
		JLabel label_3 = new JLabel("厂商：");
		
		merchantTxt = new JTextField();
		merchantTxt.setColumns(10);
		
		label_4 = new JLabel("型号：");
		
		modelTxt = new JTextField();
		modelTxt.setColumns(10);
		
		label_5 = new JLabel("进货日期：");
		
		purchaseDateTxt = new JTextField();
		purchaseDateTxt.setColumns(10);
		
		label_6 = new JLabel("状态：");
		
		 statusCmb = new JComboBox();
		
		JLabel label_7 = new JLabel("描述：");
		
		 goodsDescTxt = new JTextArea();
		
		JButton button = new JButton("添加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//添加商品
				addGoodsActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(AddGoodsInterFrm.class.getResource("/images/add.png")));
		
		JButton button_1 = new JButton("重置");
		button_1.setIcon(new ImageIcon(AddGoodsInterFrm.class.getResource("/images/reset.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(label_2)
						.addComponent(label_1)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(label_7)
							.addComponent(label_6)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(goodsDescTxt, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(goodsNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
							.addComponent(label_4)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(modelTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(countTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
							.addComponent(label_3)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(merchantTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(statusCmb, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(priceTxt, Alignment.LEADING))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_5)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(purchaseDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(163))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(124)
					.addComponent(button)
					.addGap(100)
					.addComponent(button_1)
					.addContainerGap(288, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(goodsNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(modelTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(countTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(merchantTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5)
						.addComponent(purchaseDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_6)
						.addComponent(statusCmb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_7)
						.addComponent(goodsDescTxt, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addGap(18))
		);
		getContentPane().setLayout(groupLayout);

	}
	/**
	 * 添加商品事件处理
	 * @param e
	 */
	private  void addGoodsActionPerformed(ActionEvent e) {
		try {
			Connection connection=null;
			String goodsName=goodsNameTxt.getText();
			String model=modelTxt.getText();
			String count=countTxt.getText();
			String price=priceTxt.getText();
			String merchant=merchantTxt.getText();
			String purchaseDate=purchaseDateTxt.getText();
//			String status=statusCmb.get
			String goodsDesc=goodsDescTxt.getText();
			Goods goods=new Goods();
			goods.setGoodsName(goodsName);
			goods.setModel(model);
			goods.setCount(Integer.parseInt(count));
			goods.setPrice(Float.parseFloat(price));
			goods.setMerchant(merchant);
			goods.setPurchaseDate(new Date());
			goods.setGoodsDesc(goodsDesc);
			goods.setStatus(1);
			int addNum=goodsDao.addGoods(connection, goods);
			if(addNum==1){
				JOptionPane.showMessageDialog(null, "添加成功!");
			}else{
				JOptionPane.showMessageDialog(null, "添加失败！");
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "添加失败！");
			e2.printStackTrace();
		}
		
	}
}
