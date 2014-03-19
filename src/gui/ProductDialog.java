package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import pojo.Product;

import dao.hibernate.ProductDaoHibernate;
import dao.interfaces.ProductDao;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField specField;
	private JTextField sumField;
	
	private ProductPane parent;
	private ProductDao productDao = new ProductDaoHibernate();
	private Product currentProduct;

	/**
	 * Create the dialog.
	 */
	public ProductDialog(ProductPane current, Product product) {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("\u96F6\u4EF6\u540D\u79F0");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(48, 30, 80, 30);
		contentPanel.add(label);
		
		nameField = new JTextField();
		nameField.setFont(new Font("宋体", Font.PLAIN, 14));
		nameField.setBounds(151, 30, 120, 30);
		contentPanel.add(nameField);
		nameField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u89C4\u683C\u8BF4\u660E");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(48, 92, 80, 30);
		contentPanel.add(label_1);
		
		specField = new JTextField();
		specField.setFont(new Font("宋体", Font.PLAIN, 14));
		specField.setBounds(151, 92, 120, 30);
		contentPanel.add(specField);
		specField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5E93\u5B58");
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		label_2.setBounds(48, 149, 80, 30);
		contentPanel.add(label_2);
		
		sumField = new JTextField();
		sumField.setFont(new Font("宋体", Font.PLAIN, 14));
		sumField.setBounds(151, 149, 66, 30);
		contentPanel.add(sumField);
		sumField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u786E\u5B9A");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//添加新零件
						if(currentProduct == null){
							Product oneProduct = new Product();
							oneProduct.setName(nameField.getText());
							oneProduct.setSpec(specField.getText());
							try {
								oneProduct.setSum(Integer.parseInt(sumField.getText()));
							} catch (NumberFormatException e) {
							}
							
							productDao.save(oneProduct);
						}
						//修改零件信息
						else{
							currentProduct.setName(nameField.getText());
							currentProduct.setSpec(specField.getText());
							try {
								currentProduct.setSum(Integer.parseInt(sumField.getText()));
							} catch (NumberFormatException e) {
							}
							
							productDao.update(currentProduct);
						}
						
						parent.updateTable();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u53D6\u6D88");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		//初始化填写
		customInit(current, product);
	}
	
	private void customInit(ProductPane current, Product product){
		this.parent = current;
		if(product != null){
			currentProduct = product;
			this.setTitle("修改零件信息");
			
			nameField.setText(currentProduct.getName());
			specField.setText(currentProduct.getSpec());
			sumField.setText(String.valueOf(currentProduct.getSum()));
		}
		else{
			this.setTitle("添加零件");
		}
	}
	
}
