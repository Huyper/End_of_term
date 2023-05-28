package client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {
	Connection conn;

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JLabel label;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
   public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
       public void run(){
                try {
                   Login frame = new Login();
                   frame.setVisible(true);
             } 
                catch (Exception e) {
                	// TODO: handle exception
                   e.printStackTrace();
              
            
            }}});
    }

    /**
     * Create the frame.
     */
       public void ketnoi(){
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	            this.conn = DriverManager.getConnection("jdbc:mysql://"
	            		+ "localhost:3308/"
	            		+ "quanlidoanhthuveso?useUnicode=true&"
	            		+ "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode"
	            		+ "=false&serverTimezone=UTC", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
		
	}
    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(Color.black
        		);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        textField.setBounds(481, 170, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        passwordField.setBounds(481, 286, 281, 68);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLUE);
        lblUsername.setForeground(Color.magenta);
        lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 31));
        lblUsername.setBounds(250, 166, 193, 52);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.magenta);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 31));
        lblPassword.setBounds(250, 286, 193, 52);
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        btnNewButton.setBounds(545, 392, 162, 73);
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String userName = textField.getText();
                String password = passwordField.getText();
                try {
                   
      // kiểm tra xem có tài khoản không
                     PreparedStatement st = (PreparedStatement) conn
                        .prepareStatement("Select name, password from {user???} where name=? and password=?");

                    st.setString(1, userName);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        dispose();
                        
                        
                        // sau khi đăng nhập thì đến màn hình chính
                      //  MainView ah = new MainView (userName);
                      //ah.setTitle("Welcome");
                      //  ah.setVisible(true);
                        
                        
                        
                        
                        
                        JOptionPane.showMessageDialog(btnNewButton, "Đăng nhập tahfnh công");//
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton, "Sai tên đăng nhập haowjc mật khẩu");
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });

        contentPane.add(btnNewButton);

        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }
}