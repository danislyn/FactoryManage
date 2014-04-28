package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import dao.hibernate.MachineDaoHibernate;
import dao.hibernate.PlanDaoHibernate;
import dao.hibernate.ProductDaoHibernate;
import dao.interfaces.MachineDao;
import dao.interfaces.PlanDao;
import dao.interfaces.ProductDao;

import pojo.Machine;
import pojo.Plan;
import pojo.Product;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

public class PlanDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField yyField;
	private JTextField mmField;
	private JTextField ddField;
	private JTextField yyField2;
	private JTextField mmField2;
	private JTextField ddField2;
	private JTextField expectionField;
	private JComboBox productBox;
	
	private PlanPane parent;
	private Plan currentPlan;
	private PlanDao planDao = new PlanDaoHibernate();
	private MachineDao machineDao = new MachineDaoHibernate();
	private ProductDao productDao = new ProductDaoHibernate();
	
//	private List<Machine> machineList;
	private List<Product> productList;

	/**
	 * Create the dialog.
	 */
	public PlanDialog(PlanPane current, Plan plan) {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 370);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("\u8BA1\u5212\u540D\u79F0");
			label.setFont(new Font("宋体", Font.PLAIN, 14));
			label.setBounds(48, 32, 80, 30);
			contentPanel.add(label);
		}
		{
			nameField = new JTextField();
			nameField.setFont(new Font("宋体", Font.PLAIN, 14));
			nameField.setBounds(158, 32, 120, 30);
			contentPanel.add(nameField);
			nameField.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u5F00\u59CB\u65E5\u671F");
			label.setFont(new Font("宋体", Font.PLAIN, 14));
			label.setBounds(48, 84, 80, 30);
			contentPanel.add(label);
		}
		{
			yyField = new JTextField();
			yyField.setFont(new Font("宋体", Font.PLAIN, 14));
			yyField.setBounds(158, 84, 50, 30);
			contentPanel.add(yyField);
			yyField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("\u5E74");
			lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
			lblNewLabel.setBounds(207, 84, 23, 30);
			contentPanel.add(lblNewLabel);
		}
		{
			mmField = new JTextField();
			mmField.setFont(new Font("宋体", Font.PLAIN, 14));
			mmField.setBounds(228, 84, 30, 30);
			contentPanel.add(mmField);
			mmField.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u6708");
			label.setFont(new Font("宋体", Font.PLAIN, 14));
			label.setBounds(260, 84, 23, 30);
			contentPanel.add(label);
		}
		{
			ddField = new JTextField();
			ddField.setFont(new Font("宋体", Font.PLAIN, 14));
			ddField.setBounds(288, 84, 30, 30);
			contentPanel.add(ddField);
			ddField.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u65E5");
			label.setFont(new Font("宋体", Font.PLAIN, 14));
			label.setBounds(322, 84, 23, 30);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u5F00\u59CB\u65E5\u671F");
			label.setFont(new Font("宋体", Font.PLAIN, 14));
			label.setBounds(48, 131, 80, 30);
			contentPanel.add(label);
		}
		{
			yyField2 = new JTextField();
			yyField2.setFont(new Font("宋体", Font.PLAIN, 14));
			yyField2.setColumns(10);
			yyField2.setBounds(158, 131, 50, 30);
			contentPanel.add(yyField2);
		}
		{
			JLabel label = new JLabel("\u5E74");
			label.setFont(new Font("宋体", Font.PLAIN, 14));
			label.setBounds(207, 131, 23, 30);
			contentPanel.add(label);
		}
		{
			mmField2 = new JTextField();
			mmField2.setFont(new Font("宋体", Font.PLAIN, 14));
			mmField2.setColumns(10);
			mmField2.setBounds(228, 131, 30, 30);
			contentPanel.add(mmField2);
		}
		{
			JLabel label = new JLabel("\u6708");
			label.setFont(new Font("宋体", Font.PLAIN, 14));
			label.setBounds(260, 131, 23, 30);
			contentPanel.add(label);
		}
		{
			ddField2 = new JTextField();
			ddField2.setFont(new Font("宋体", Font.PLAIN, 14));
			ddField2.setColumns(10);
			ddField2.setBounds(288, 131, 30, 30);
			contentPanel.add(ddField2);
		}
		{
			JLabel label = new JLabel("\u65E5");
			label.setFont(new Font("宋体", Font.PLAIN, 14));
			label.setBounds(322, 131, 23, 30);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u751F\u4EA7\u96F6\u4EF6");
			label.setFont(new Font("宋体", Font.PLAIN, 14));
			label.setBounds(48, 181, 80, 30);
			contentPanel.add(label);
		}
		{
			productBox = new JComboBox();
			productBox.setFont(new Font("宋体", Font.PLAIN, 14));
			productBox.setBounds(158, 181, 120, 30);
			contentPanel.add(productBox);
		}
		{
			JLabel label = new JLabel("\u8BA1\u5212\u4EA7\u91CF");
			label.setFont(new Font("宋体", Font.PLAIN, 14));
			label.setBounds(48, 234, 80, 30);
			contentPanel.add(label);
		}
		{
			expectionField = new JTextField();
			expectionField.setFont(new Font("宋体", Font.PLAIN, 14));
			expectionField.setBounds(158, 234, 120, 30);
			contentPanel.add(expectionField);
			expectionField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u786E\u5B9A");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//添加新计划
						if(currentPlan == null){
							Plan onePlan = new Plan();
							onePlan.setName(nameField.getText());
							
							Calendar c = Calendar.getInstance();
							try {
								c.set(Integer.parseInt(yyField.getText()), 
										Integer.parseInt(mmField.getText()) - 1, 
										Integer.parseInt(ddField.getText()));
								onePlan.setFromTime(c.getTime());
							} catch (NumberFormatException e) {
							}
							
							try {
								c.set(Integer.parseInt(yyField2.getText()), 
										Integer.parseInt(mmField2.getText()) - 1, 
										Integer.parseInt(ddField2.getText()));
								onePlan.setToTime(c.getTime());
							} catch (NumberFormatException e) {
							}
							
//							int index = machineBox.getSelectedIndex();
//							if(index > 0){
//								onePlan.setMachine(machineList.get(index - 1));
//							}
							
							int index2 = productBox.getSelectedIndex();
							if(index2 > 0){
								onePlan.setProduct(productList.get(index2 - 1));
							}
							
							try {
								onePlan.setExpectation(Integer.parseInt(expectionField.getText()));
							} catch (NumberFormatException e) {
							}
							
							planDao.save(onePlan);
						}
						//修改计划信息
						else{
							currentPlan.setName(nameField.getText());
							
							Calendar c = Calendar.getInstance();
							try {
								c.set(Integer.parseInt(yyField.getText()), 
										Integer.parseInt(mmField.getText()) - 1, 
										Integer.parseInt(ddField.getText()));
								currentPlan.setFromTime(c.getTime());
							} catch (NumberFormatException e) {
								currentPlan.setFromTime(null);
							}
							
							try {
								c.set(Integer.parseInt(yyField2.getText()), 
										Integer.parseInt(mmField2.getText()) - 1, 
										Integer.parseInt(ddField2.getText()));
								currentPlan.setToTime(c.getTime());
							} catch (NumberFormatException e) {
								currentPlan.setToTime(null);
							}
							
//							int index = machineBox.getSelectedIndex();
//							if(index > 0){
//								currentPlan.setMachine(machineList.get(index - 1));
//							}
//							else{
//								currentPlan.setMachine(null);
//							}
							
							int index2 = productBox.getSelectedIndex();
							if(index2 > 0){
								currentPlan.setProduct(productList.get(index2 - 1));
							}
							else{
								currentPlan.setProduct(null);
							}
							
							try {
								currentPlan.setExpectation(Integer.parseInt(expectionField.getText()));
							} catch (NumberFormatException e) {
								currentPlan.setExpectation(0);
							}
							
							planDao.update(currentPlan);
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
		customInit(current, plan);
	}
	
	private void customInit(PlanPane current, Plan plan){
		//初始化下拉框
		initCombox();
		
		this.parent = current;
		if(plan != null){
			currentPlan = plan;
			this.setTitle("修改计划信息");
			
			nameField.setText(currentPlan.getName());
			
			Calendar c = Calendar.getInstance();
			if(currentPlan.getFromTime() != null){
				c.setTime(currentPlan.getFromTime());
				yyField.setText(String.valueOf(c.get(Calendar.YEAR)));
				mmField.setText(String.valueOf(c.get(Calendar.MONTH) + 1));
				ddField.setText(String.valueOf(c.get(Calendar.DATE)));
			}
			if(currentPlan.getToTime() != null){
				c.setTime(currentPlan.getToTime());
				yyField2.setText(String.valueOf(c.get(Calendar.YEAR)));
				mmField2.setText(String.valueOf(c.get(Calendar.MONTH) + 1));
				ddField2.setText(String.valueOf(c.get(Calendar.DATE)));
			}
			
//			if(currentPlan.getMachine() != null){
//				int index = findMachineIndex(currentPlan.getMachine());
//				machineBox.setSelectedIndex(index + 1);
//			}
			
			if(currentPlan.getProduct() != null){
				int index = findProductIndex(currentPlan.getProduct());
				productBox.setSelectedIndex(index + 1);
			}
			
			expectionField.setText(String.valueOf(currentPlan.getExpectation()));
		}
		else{
			this.setTitle("添加计划");
		}
	}
	
	private void initCombox(){
//		machineList = machineDao.findAll();
		productList = productDao.findAll();
		
//		Vector<String> machineVector = new Vector<String>();
//		machineVector.add("无");
//		for(Machine machine : machineList){
//			machineVector.add(machine.getAlias());
//		}
		
		Vector<String> productVector = new Vector<String>();
		productVector.add("无");
		for(Product product : productList){
			productVector.add(product.getName());
		}
		productBox.setModel(new DefaultComboBoxModel(productVector));
	}
	
//	private int findMachineIndex(Machine machine){
//		for(int i=0; i<machineList.size(); i++){
//			if(machineList.get(i).getId().equals(machine.getId())){
//				return i;
//			}
//		}
//		return -1;
//	}
	
	private int findProductIndex(Product product){
		for(int i=0; i<productList.size(); i++){
			if(productList.get(i).getId().equals(product.getId())){
				return i;
			}
		}
		return -1;
	}

}
