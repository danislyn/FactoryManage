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

import pojo.MachineType;

import dao.hibernate.MachineTypeDaoHibernate;
import dao.interfaces.MachineDao;
import dao.interfaces.MachineTypeDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MachineTypeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	
	private MachineTypeDao machineTypeDao = new MachineTypeDaoHibernate();
	
	/**
	 * Create the dialog.
	 */
	public MachineTypeDialog() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setTitle("机床类型");
		setBounds(100, 100, 350, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("\u673A\u5E8A\u7C7B\u578B\u540D\u79F0");
			label.setFont(new Font("宋体", Font.PLAIN, 14));
			label.setBounds(38, 41, 120, 30);
			contentPanel.add(label);
		}
		{
			nameField = new JTextField();
			nameField.setFont(new Font("宋体", Font.PLAIN, 14));
			nameField.setBounds(168, 41, 120, 30);
			contentPanel.add(nameField);
			nameField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u786E\u5B9A");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						MachineType type = new MachineType();
						type.setName(nameField.getText());
						
						machineTypeDao.save(type);
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
		
	}

}
