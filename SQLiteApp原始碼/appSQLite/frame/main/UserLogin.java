package frame.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UserLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static UserLogin frame;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new UserLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserLogin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("使用者登入");
		title.setFont(new Font("微軟正黑體", Font.PLAIN, 50));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(80, 22, 285, 55);
		contentPane.add(title);
		
		//使用者
		JLabel userLabel = new JLabel("帳號:");
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userLabel.setFont(new Font("標楷體", Font.PLAIN, 20));
		userLabel.setBounds(106, 101, 60, 40);
		contentPane.add(userLabel);
		
		JTextField user = new JTextField();
		user.setText("root");
		user.setFont(new Font("標楷體", Font.PLAIN, 20));
		user.setBounds(176, 107, 150, 35);
		contentPane.add(user);
		user.setColumns(10);
		
		//密碼
		JLabel passwdLabel = new JLabel("密碼:");
		passwdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwdLabel.setFont(new Font("標楷體", Font.PLAIN, 20));
		passwdLabel.setBounds(106, 165, 60, 40);
		contentPane.add(passwdLabel);
		
		JPasswordField passwd = new JPasswordField();
		passwd.setText("123456789");
		passwd.setEchoChar('*');
		passwd.setFont(new Font("標楷體", Font.PLAIN, 20));
		passwd.setBounds(176, 171, 150, 35);
		contentPane.add(passwd);
		passwd.setColumns(10);
		
		//登入按鈕
		JButton login = new JButton("登入");
		login.setFont(new Font("標楷體", Font.PLAIN, 20));
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.getText().equals("root") && String.valueOf(passwd.getPassword()).equals("123456789")){
					try {
						MainMenu mainMenu = new MainMenu();
						mainMenu.setVisible(true);
						frame.dispose();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(contentPane, "當前目錄找不到SQLiteData(data/world.db)");
					}
				}else {
					JOptionPane.showMessageDialog(contentPane, "帳號或密碼錯誤");
				}
			}
		});
		login.setBounds(337, 225, 87, 28);
		contentPane.add(login);
	}
}
