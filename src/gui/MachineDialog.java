package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import pojo.Machine;
import pojo.MachineType;
import pojo.Staff;

import dao.hibernate.MachineDaoHibernate;
import dao.hibernate.MachineTypeDaoHibernate;
import dao.hibernate.StaffDaoHibernate;
import dao.interfaces.MachineDao;
import dao.interfaces.MachineTypeDao;
import dao.interfaces.StaffDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

public class MachineDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField aliasField;
	private JComboBox typeBox;
	private JComboBox staffBox;
	
	private MachinePane parent;
	private Machine currentMachine;
	private MachineDao machineDao = new MachineDaoHibernate();
	private MachineTypeDao typeDao = new MachineTypeDaoHibernate();
	private StaffDao staffDao = new StaffDaoHibernate();
	
	private List<MachineType> typeList;
	private List<Staff> staffList;
	

	/**
	 * Create the dialog.
	 */
	public MachineDialog(MachinePane current, Machine machine) {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("\u673A\u5E8A\u7C7B\u578B");
			label.setFont(new Font("宋体", Font.PLAIN, 14));
			label.setBounds(44, 28, 90, 30);
			contentPanel.add(label);
		}
		
		typeBox = new JComboBox();
		typeBox.setFont(new Font("宋体", Font.PLAIN, 14));
		typeBox.setBounds(154, 28, 120, 30);
		contentPanel.add(typeBox);
		
		JLabel label = new JLabel("\u673A\u5E8A\u522B\u540D");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(44, 96, 90, 30);
		contentPanel.add(label);
		
		aliasField = new JTextField();
		aliasField.setFont(new Font("宋体", Font.PLAIN, 14));
		aliasField.setBounds(154, 96, 120, 30);
		contentPanel.add(aliasField);
		aliasField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u64CD\u4F5C\u4EBA\u5458");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(44, 160, 90, 30);
		contentPanel.add(label_1);
		
		staffBox = new JComboBox();
		staffBox.setFont(new Font("宋体", Font.PLAIN, 14));
		staffBox.setBounds(154, 160, 120, 30);
		contentPanel.add(staffBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u786E\u5B9A");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//添加新机床
						if(currentMachine == null){
							Machine oneMachine = new Machine();
							oneMachine.setAlias(aliasField.getText());
							
							int index = typeBox.getSelectedIndex();
							if(index > 0){
								oneMachine.setMachineType(typeList.get(index - 1));
							}
							
							int index2 = staffBox.getSelectedIndex();
							if(index2 > 0){
								oneMachine.setStaff(staffList.get(index2 - 1));
							}
							
							machineDao.save(oneMachine);
						}
						//修改机床信息
						else{
							currentMachine.setAlias(aliasField.getText());
							
							int index = typeBox.getSelectedIndex();
							if(index > 0){
								currentMachine.setMachineType(typeList.get(index - 1));
							}
							else{
								currentMachine.setMachineType(null);
							}
							
							int index2 = staffBox.getSelectedIndex();
							if(index2 > 0){
								currentMachine.setStaff(staffList.get(index2 - 1));
							}
							else{
								currentMachine.setStaff(null);
							}
							
							machineDao.update(currentMachine);
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
		customInit(current, machine);
	}
	
	private void customInit(MachinePane current, Machine machine){
		//初始化下拉框
		initCombox();
		
		this.parent = current;
		if(machine != null){
			currentMachine = machine;
			this.setTitle("修改机床信息");
			
			aliasField.setText(currentMachine.getAlias());
			
			if(currentMachine.getMachineType() != null){
				int index = findTypeIndex(currentMachine.getMachineType());
				typeBox.setSelectedIndex(index + 1);
			}
			else{
				typeBox.setSelectedIndex(0);
			}
			
			if(currentMachine.getStaff() != null){
				int index = findStaffIndex(currentMachine.getStaff());
				staffBox.setSelectedIndex(index + 1);
			}
			else{
				staffBox.setSelectedIndex(0);
			}
		}
		else{
			this.setTitle("添加机床");
		}
	}
	
	private void initCombox(){
		typeList = typeDao.findAll();
		staffList = staffDao.findAll();
		
		Vector<String> typeVector = new Vector<String>();
		typeVector.add("无");
		for(MachineType type : typeList){
			typeVector.add(type.getName());
		}
		
		Vector<String> staffVector = new Vector<String>();
		staffVector.add("无");
		for(Staff staff : staffList){
			staffVector.add(staff.getName());
		}
		
		typeBox.setModel(new DefaultComboBoxModel(typeVector));
		staffBox.setModel(new DefaultComboBoxModel(staffVector));
	}
	
	private int findTypeIndex(MachineType type){
		for(int i=0; i<typeList.size(); i++){
			if(typeList.get(i).getId().equals(type.getId())){
				return i;
			}
		}
		return -1;
	}
	
	private int findStaffIndex(Staff staff){
		for(int i=0; i<staffList.size(); i++){
			if(staffList.get(i).getId().equals(staff.getId())){
				return i;
			}
		}
		return -1;
	}
	
}
