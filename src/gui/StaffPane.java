package gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
import pojo.Staff;

import dao.hibernate.StaffDaoHibernate;
import dao.interfaces.StaffDao;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;

public class StaffPane extends JPanel {
	private JTable contentTable;
	
	private StaffModel tableModel;
	private StaffDao staffDao = new StaffDaoHibernate();
	private List<Staff> staffCache = new ArrayList<Staff>();
	
	/**
	 * Create the panel.
	 */
	public StaffPane() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(165, 10, 595, 430);
		add(scrollPane);
		
		contentTable = new JTable();
		scrollPane.setViewportView(contentTable);
		contentTable.setFont(new Font("宋体", Font.PLAIN, 20));
		contentTable.setRowHeight(24);
		
		JButton addBtn = new JButton("\u6DFB\u52A0\u4EBA\u5458");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StaffDialog addDialog = new StaffDialog(getThis(), null);
				addDialog.setVisible(true);
			}
		});
		addBtn.setBounds(21, 24, 120, 35);
		add(addBtn);
		
		JButton editBtn = new JButton("\u7F16\u8F91\u4FE1\u606F");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Staff staff = staffCache.get(contentTable.getSelectedRow());
				StaffDialog addDialog = new StaffDialog(getThis(), staff);
				addDialog.setVisible(true);
			}
		});
		editBtn.setBounds(21, 84, 120, 35);
		add(editBtn);
		
		JButton deleteBtn = new JButton("\u5220\u9664\u4EBA\u5458");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Staff staff = staffCache.get(contentTable.getSelectedRow());
				
				int result = JOptionPane.showConfirmDialog(null, "确定要删除 " + staff.getName() + " 吗？");
				if(result == JOptionPane.OK_OPTION){
					staffDao.delete(staff);
				}
				updateTable();
			}
		});
		deleteBtn.setBounds(21, 143, 120, 35);
		add(deleteBtn);

	}
	
	private StaffPane getThis(){
		return this;
	}
	
	
	public void updateTable(){
		staffCache = staffDao.findAll();
		
		if(tableModel == null){
			tableModel = new StaffModel(staffCache);
		}
		else{
			tableModel.setData(staffCache);
		}
		
		contentTable.setModel(tableModel);
		contentTable.updateUI();
		repaint();
	}
	
	
	
	//======================================================
	class StaffModel extends AbstractTableModel{
		String[] columns = {"编号", "姓名", "性别", "年龄"};
		List<Staff> data = new ArrayList<Staff>();
		
		public StaffModel(List<Staff> data){
			this.data = data;
		}
		
		public void setData(List<Staff> data){
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
			Staff staff = data.get(rowIndex);
			String val = "";
			
			switch (columnIndex) {
			case 0:
				val = String.valueOf(staff.getId());
				break;
			case 1:
				val = staff.getName();
				break;
			case 2:
				val = staff.getGender() == 1 ? "男" : "女";
				break;
			case 3:
				val = String.valueOf(staff.getAge());
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
