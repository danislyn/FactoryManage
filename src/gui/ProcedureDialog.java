package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import pojo.Plan;
import pojo.Procedure;

import dao.hibernate.ProcedureDaoHibernate;
import dao.interfaces.ProcedureDao;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProcedureDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable contentTable;
	
	private ProcedureModel tableModel;
	private ProcedureDao procedureDao = new ProcedureDaoHibernate();
	private List<Procedure> procedureCache = new ArrayList<Procedure>();

	private Plan plan;
	
	/**
	 * Create the dialog.
	 */
	public ProcedureDialog(Plan currentPlan) {
		this.setTitle(currentPlan.getName() + "-生产工序");
		this.plan = currentPlan;
		
		setBounds(100, 100, 534, 423);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 498, 332);
		contentPanel.add(scrollPane);
		
		contentTable = new JTable();
		contentTable.setFont(new Font("宋体", Font.PLAIN, 15));
		contentTable.setRowHeight(24);
		scrollPane.setViewportView(contentTable);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u786E\u5B9A");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public void updateTable(){
		procedureCache = procedureDao.getProceduresByProduct(plan.getProduct().getId());
		
		if(tableModel == null){
			tableModel = new ProcedureModel(procedureCache);
		}
		else{
			tableModel.setData(procedureCache);
		}
		
		contentTable.setModel(tableModel);
		contentTable.updateUI();
		repaint();
	}
	
	
	
	//======================================================
	class ProcedureModel extends AbstractTableModel{
		String[] columns = {"生产零件", "工序", "工序名称", "需要机床", "预计时间"};
		List<Procedure> data = new ArrayList<Procedure>();
		
		final DecimalFormat decimalFormat = new DecimalFormat(".0");
		
		public ProcedureModel(List<Procedure> data){
			this.data = data;
		}
		
		public void setData(List<Procedure> data){
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
			Procedure procedure = data.get(rowIndex);
			String val = "";
			
			switch (columnIndex) {
			case 0:
				val = procedure.getProduct() != null ? procedure.getProduct().getName() : "";
				break;
			case 1:
				val = "步骤" + procedure.getStepNo();
				break;
			case 2:
				val = procedure.getStepName();
				break;
			case 3:
				val = procedure.getMachine() != null ? procedure.getMachine().getMachineType().getName() : "";
				break;
			case 4:
				val = decimalFormat.format(plan.getExpectation() * procedure.getUnitTime() / 60) + "小时";
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
