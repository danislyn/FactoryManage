package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JPanel menuPane;
	private JPanel resultPane;
	
	private StaffPane staffPane;
	private MachinePane machinePane;
	private PlanPane planPane;
	private ProductPane productPane;
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("车间调度管理系统");
		setBounds(100, 100, 850, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		menuPane = new JPanel();
		menuPane.setBounds(27, 10, 721, 54);
		contentPane.add(menuPane);
		menuPane.setLayout(null);
		
		JButton staffBtn = new JButton("\u4EBA\u5458\u7BA1\u7406");
		staffBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(staffPane == null){
					staffPane = new StaffPane();
				}
				updateResultPane(staffPane);
				staffPane.updateTable();
			}
		});
		staffBtn.setBounds(20, 10, 120, 35);
		menuPane.add(staffBtn);
		staffBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JButton machineBtn = new JButton("\u673A\u5E8A\u7BA1\u7406");
		machineBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		machineBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(machinePane == null){
					machinePane = new MachinePane();
				}
				updateResultPane(machinePane);
				machinePane.updateTable();
			}
		});
		machineBtn.setBounds(160, 10, 120, 35);
		menuPane.add(machineBtn);
		
		JButton planBtn = new JButton("\u751F\u4EA7\u8BA1\u5212");
		planBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		planBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(planPane == null){
					planPane = new PlanPane();
				}
				updateResultPane(planPane);
				planPane.updateTable();
			}
		});
		planBtn.setBounds(299, 10, 120, 35);
		menuPane.add(planBtn);
		
		JButton productBtn = new JButton("\u96F6\u4EF6\u5E93\u5B58");
		productBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		productBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(productPane == null){
					productPane = new ProductPane();
				}
				updateResultPane(productPane);
				productPane.updateTable();
			}
		});
		productBtn.setBounds(439, 10, 120, 35);
		menuPane.add(productBtn);
		
		resultPane = new JPanel();
		resultPane.setBounds(27, 76, 770, 450);
		contentPane.add(resultPane);
		resultPane.setLayout(null);
	}
	
	
	private void updateResultPane(JPanel panel){
		panel.setBounds(0, 0, 770, 450);
		panel.setVisible(true);
		resultPane.removeAll();
		resultPane.add(panel, null);
		resultPane.updateUI();
		repaint();
	}
	
	
	//==============================================
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
		            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
