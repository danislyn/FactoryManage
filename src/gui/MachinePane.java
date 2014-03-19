package gui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;

import dao.hibernate.MachineDaoHibernate;
import dao.interfaces.MachineDao;

import pojo.Machine;

public class MachinePane extends JPanel {
	private JTable contentTable;
	
	private MachineModel tableModel;
	private MachineDao machineDao = new MachineDaoHibernate();
	private List<Machine> machineCache = new ArrayList<Machine>();

	/**
	 * Create the panel.
	 */
	public MachinePane() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(165, 10, 595, 430);
		add(scrollPane);
		
		contentTable = new JTable();
		scrollPane.setViewportView(contentTable);
		contentTable.setFont(new Font("宋体", Font.PLAIN, 16));
		contentTable.setRowHeight(24);
		
		JButton addBtn = new JButton("\u6DFB\u52A0\u673A\u5E8A");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		addBtn.setBounds(21, 24, 120, 35);
		add(addBtn);
		
		JButton editBtn = new JButton("\u4FEE\u6539\u673A\u5E8A\u4FE1\u606F");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		editBtn.setBounds(21, 84, 120, 35);
		add(editBtn);
		
		JButton deleteBtn = new JButton("\u5220\u9664\u673A\u5E8A");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		deleteBtn.setBounds(21, 143, 120, 35);
		add(deleteBtn);

	}
	
	private MachinePane getThis(){
		return this;
	}
	
	
	public void updateTable(){
		machineCache = machineDao.findAll();
		
		if(tableModel == null){
			tableModel = new MachineModel(machineCache);
		}
		else{
			tableModel.setData(machineCache);
		}
		
		contentTable.setModel(tableModel);
		contentTable.updateUI();
		repaint();
	}
	
	
	
	//======================================================
	class MachineModel extends AbstractTableModel{
		String[] columns = {"编号", "机床类型", "别名", "当前操作人员"};
		List<Machine> data = new ArrayList<Machine>();
		
		public MachineModel(List<Machine> data){
			this.data = data;
		}
		
		public void setData(List<Machine> data){
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
			Machine machine = data.get(rowIndex);
			String val = "";
			
			switch (columnIndex) {
			case 0:
				val = String.valueOf(machine.getId());
				break;
			case 1:
				val = machine.getMachineType() != null ? machine.getMachineType().getName() : "无";
				break;
			case 2:
				val = machine.getAlias();
				break;
			case 3:
				val = machine.getStaff() != null ? machine.getStaff().getName() : "无";
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
