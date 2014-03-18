package gui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlanPane extends JPanel {
	private JTable contentTable;

	/**
	 * Create the panel.
	 */
	public PlanPane() {
		setLayout(null);
		
		contentTable = new JTable();
		contentTable.setBounds(165, 10, 595, 430);
		add(contentTable);
		
		JButton addBtn = new JButton("\u5236\u5B9A\u751F\u4EA7\u8BA1\u5212");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		addBtn.setBounds(21, 24, 120, 35);
		add(addBtn);
		
		JButton editBtn = new JButton("\u4FEE\u6539\u8BA1\u5212\u4FE1\u606F");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		editBtn.setBounds(21, 84, 120, 35);
		add(editBtn);
		
		JButton deleteBtn = new JButton("\u5220\u9664\u8BA1\u5212");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		deleteBtn.setBounds(21, 143, 120, 35);
		add(deleteBtn);

	}

}
