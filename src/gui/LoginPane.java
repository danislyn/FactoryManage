package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import pojo.User;

import dao.hibernate.UserDaoHibernate;
import dao.interfaces.UserDao;
import javax.swing.SwingConstants;

public class LoginPane extends JPanel {
	private JTextField accountField;
	private JPasswordField passwordField;
	
	private JFrame currentFrame;
	
	private UserDao userDao = new UserDaoHibernate();

	/**
	 * Create the panel.
	 */
	public LoginPane(JFrame parent) {
		this.currentFrame = parent;
		
		setLayout(null);
		
		JLabel accountLabel = new JLabel("\u5E10\u53F7");
		accountLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		accountLabel.setBounds(94, 106, 54, 26);
		add(accountLabel);
		
		accountField = new JTextField();
		accountField.setBounds(158, 102, 134, 30);
		add(accountField);
		accountField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("\u5BC6\u7801");
		passwordLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		passwordLabel.setBounds(94, 164, 54, 26);
		add(passwordLabel);
		
		JButton loginButton = new JButton("\u767B\u5F55");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String account = accountField.getText();
				String password = passwordField.getText();
				
				User loginUser = userDao.loginCheck(account, password);
				if(loginUser != null){
					
					//关闭当前
					setVisible(false);
					currentFrame.dispose();
					
					//开启主窗口
					MainFrame mainFrame = new MainFrame();
					mainFrame.setVisible(true);
				}
				else{
					JOptionPane.showMessageDialog(null, "用户名密码错误，请重试", "提示", JOptionPane.INFORMATION_MESSAGE);
					accountField.setText("");
					passwordField.setText("");
				}
			}
		});
		loginButton.setFont(new Font("宋体", Font.PLAIN, 16));
		loginButton.setBounds(199, 221, 93, 30);
		add(loginButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(158, 160, 134, 30);
		add(passwordField);
		
		JLabel logoLabel = new JLabel("\u8F66\u95F4\u8C03\u5EA6\u7BA1\u7406\u7CFB\u7EDF");
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setFont(new Font("宋体", Font.PLAIN, 28));
		logoLabel.setBounds(48, 26, 297, 46);
		add(logoLabel);

	}
}
