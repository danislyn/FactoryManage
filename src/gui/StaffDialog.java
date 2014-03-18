package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import dao.hibernate.StaffDaoHibernate;
import dao.interfaces.StaffDao;

import pojo.Staff;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaffDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField ageField;
	private JRadioButton radioMale;
	private JRadioButton radioFemale;
	
	private ButtonGroup radioGroup = new ButtonGroup();
	
	private StaffPane parent;
	private StaffDao staffDao = new StaffDaoHibernate();
	private Staff currentStaff;

	/**
	 * Create the dialog.
	 */
	public StaffDialog(StaffPane current, Staff staff) {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel nameLabel = new JLabel("\u59D3\u540D");
			nameLabel.setFont(new Font("宋体", Font.PLAIN, 14));
			nameLabel.setBounds(57, 35, 54, 30);
			contentPanel.add(nameLabel);
		}
		
		nameField = new JTextField();
		nameField.setFont(new Font("宋体", Font.PLAIN, 14));
		nameField.setBounds(140, 35, 130, 30);
		contentPanel.add(nameField);
		nameField.setColumns(10);
		
		JLabel genderLabel = new JLabel("\u6027\u522B");
		genderLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		genderLabel.setBounds(57, 87, 54, 30);
		contentPanel.add(genderLabel);
		
		radioMale = new JRadioButton("\u7537");
		radioMale.setFont(new Font("宋体", Font.PLAIN, 14));
		radioMale.setBounds(140, 91, 54, 23);
		contentPanel.add(radioMale);
		radioGroup.add(radioMale);
		
		radioFemale = new JRadioButton("\u5973");
		radioFemale.setFont(new Font("宋体", Font.PLAIN, 14));
		radioFemale.setBounds(205, 91, 54, 23);
		contentPanel.add(radioFemale);
		radioGroup.add(radioFemale);
		
		JLabel ageLabel = new JLabel("\u5E74\u9F84");
		ageLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		ageLabel.setBounds(57, 138, 54, 30);
		contentPanel.add(ageLabel);
		
		ageField = new JTextField();
		ageField.setFont(new Font("宋体", Font.PLAIN, 14));
		ageField.setBounds(140, 138, 54, 30);
		contentPanel.add(ageField);
		ageField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u786E\u5B9A");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//添加新人员
						if(currentStaff == null){
							Staff oneStaff = new Staff();
							oneStaff.setName(nameField.getText());
							oneStaff.setGender(radioMale.isSelected() ? 1 : 0);
							oneStaff.setAge(Integer.parseInt(ageField.getText()));
							
							staffDao.save(oneStaff);
						}
						//修改人员信息
						else{
							currentStaff.setName(nameField.getText());
							currentStaff.setGender(radioMale.isSelected() ? 1 : 0);
							currentStaff.setAge(Integer.parseInt(ageField.getText()));
							
							staffDao.update(currentStaff);
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
		customInit(current, staff);
	}
	
	private void customInit(StaffPane current, Staff staff){
		this.parent = current;
		if(staff != null){
			currentStaff = staff;
			this.setTitle("修改人员信息");
			
			nameField.setText(currentStaff.getName());
			if(currentStaff.getGender() == 1){
				radioMale.setSelected(true);
			}
			else{
				radioFemale.setSelected(true);
			}
			ageField.setText(String.valueOf(currentStaff.getAge()));
		}
		else{
			this.setTitle("添加成员");
		}
	}
}
