package gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;

import dao.hibernate.PlanDaoHibernate;
import dao.interfaces.PlanDao;

import pojo.Plan;

public class PlanPane extends JPanel {
	private JTable contentTable;
	
	private PlanModel tableModel;
	private PlanDao planDao = new PlanDaoHibernate();
	private List<Plan> planCache = new ArrayList<Plan>();

	/**
	 * Create the panel.
	 */
	public PlanPane() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(165, 10, 595, 430);
		add(scrollPane);
		
		contentTable = new JTable();
		scrollPane.setViewportView(contentTable);
		contentTable.setFont(new Font("宋体", Font.PLAIN, 15));
		contentTable.setRowHeight(24);
		
		JButton addBtn = new JButton("\u5236\u5B9A\u751F\u4EA7\u8BA1\u5212");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PlanDialog dialog = new PlanDialog(getThis(), null);
				dialog.setVisible(true);
			}
		});
		addBtn.setBounds(21, 24, 120, 35);
		add(addBtn);
		
		JButton editBtn = new JButton("\u4FEE\u6539\u8BA1\u5212\u4FE1\u606F");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Plan plan = planCache.get(contentTable.getSelectedRow());
				PlanDialog dialog = new PlanDialog(getThis(), plan);
				dialog.setVisible(true);
			}
		});
		editBtn.setBounds(21, 84, 120, 35);
		add(editBtn);
		
		JButton deleteBtn = new JButton("\u5220\u9664\u8BA1\u5212");
		deleteBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Plan plan = planCache.get(contentTable.getSelectedRow());
				
				int result = JOptionPane.showConfirmDialog(null, "确定要删除 " + plan.getName() + " 吗？");
				if(result == JOptionPane.OK_OPTION){
					planDao.delete(plan);
				}
				updateTable();
			}
		});
		deleteBtn.setBounds(21, 197, 120, 35);
		add(deleteBtn);
		
		JButton procedureBtn = new JButton("\u67E5\u770B\u751F\u4EA7\u5DE5\u5E8F");
		procedureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Plan plan = planCache.get(contentTable.getSelectedRow());
				ProcedureDialog dialog = new ProcedureDialog(plan);
				dialog.setVisible(true);
				dialog.updateTable();
			}
		});
		procedureBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		procedureBtn.setBounds(21, 141, 120, 35);
		add(procedureBtn);

	}
	
	private PlanPane getThis(){
		return this;
	}
	
	
	public void updateTable(){
		planCache = planDao.findAll();
		
		if(tableModel == null){
			tableModel = new PlanModel(planCache);
		}
		else{
			tableModel.setData(planCache);
		}
		
		contentTable.setModel(tableModel);
		contentTable.updateUI();
		repaint();
	}
	
	
	
	//======================================================
	class PlanModel extends AbstractTableModel{
		String[] columns = {"编号", "计划名称", "开始日期", "结束日期", "生产零件", "计划产量"};
		List<Plan> data = new ArrayList<Plan>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		public PlanModel(List<Plan> data){
			this.data = data;
		}
		
		public void setData(List<Plan> data){
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
			Plan plan = data.get(rowIndex);
			String val = "";
			
			switch (columnIndex) {
			case 0:
				val = String.valueOf(plan.getId());
				break;
			case 1:
				val = plan.getName();
				break;
			case 2:
				val = plan.getFromTime() != null ? sdf.format(plan.getFromTime()) : "无";
				break;
			case 3:
				val = plan.getToTime() != null ? sdf.format(plan.getToTime()) : "无";
				break;
			case 4:
				val = plan.getProduct() != null ? plan.getProduct().getName() : "无";
				break;
			case 5:
				val = String.valueOf(plan.getExpectation());
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
