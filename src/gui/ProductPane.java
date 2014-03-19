package gui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import dao.hibernate.ProductDaoHibernate;
import dao.interfaces.ProductDao;

import pojo.Product;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;

public class ProductPane extends JPanel {
	private JTable contentTable;
	
	private ProductModel tableModel;
	private ProductDao productDao = new ProductDaoHibernate();
	private List<Product> productCache = new ArrayList<Product>();

	/**
	 * Create the panel.
	 */
	public ProductPane() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(165, 10, 595, 430);
		add(scrollPane);
		
		contentTable = new JTable();
		scrollPane.setViewportView(contentTable);
		contentTable.setFont(new Font("宋体", Font.PLAIN, 16));
		contentTable.setRowHeight(24);
		
		JButton addBtn = new JButton("\u6DFB\u52A0\u96F6\u4EF6");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		addBtn.setBounds(21, 24, 120, 35);
		add(addBtn);
		
		JButton editBtn = new JButton("\u7F16\u8F91\u4FE1\u606F");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		editBtn.setBounds(21, 84, 120, 35);
		add(editBtn);
		
		JButton deleteBtn = new JButton("\u5220\u9664\u96F6\u4EF6");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		deleteBtn.setBounds(21, 143, 120, 35);
		add(deleteBtn);

	}
	
	private ProductPane getThis(){
		return this;
	}
	
	public void updateTable(){
		productCache = productDao.findAll();
		
		if(tableModel == null){
			tableModel = new ProductModel(productCache);
		}
		else{
			tableModel.setData(productCache);
		}
		
		contentTable.setModel(tableModel);
		contentTable.updateUI();
		repaint();
	}
	
	
	
	//======================================================
	class ProductModel extends AbstractTableModel{
		String[] columns = {"编号", "零件名称", "规格说明", "库存"};
		List<Product> data = new ArrayList<Product>();
		
		public ProductModel(List<Product> data){
			this.data = data;
		}
		
		public void setData(List<Product> data){
			this.data = data;
		}
		
		@Override
	    public String getColumnName(int column) {
	        return columns[column];
	    }
		
		@Override
		public int getColumnCount() {
			return columns.length;
		}

		@Override
		public int getRowCount() {
			if(data == null){
				return 0;
			}
			return data.size();
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Product product = data.get(rowIndex);
			String val = "";
			
			switch (columnIndex) {
			case 0:
				val = String.valueOf(product.getId());
				break;
			case 1:
				val = product.getName();
				break;
			case 2:
				val = product.getSpec();
				break;
			case 3:
				val = String.valueOf(product.getSum());
				break;
			}
			return val;
		}
		
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
	        return false;
	    }
		
	}

}
