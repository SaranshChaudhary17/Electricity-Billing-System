package electricity.billing.system;

import electricity.billing.system.Conn;
import electricity.billing.system.Project;
import electricity.billing.system.Signup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Login
extends JFrame
implements ActionListener {
    JButton login;
    JButton cancel;
    JButton signup;
    JTextField username;
    JTextField password;
    Choice logginin;

    Login() {
        super("Login Page");
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(300, 20, 100, 20);
        lblusername.setForeground(Color.BLACK);
        this.add(lblusername);
        this.username = new JTextField();
        this.username.putClientProperty("JTextField.placeholderText", "Enter Username");
        this.username.putClientProperty("JComponent.roundRect", true);
        this.username.setBounds(400, 20, 150, 25);
        this.add(this.username);
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(300, 60, 100, 20);
        lblpassword.setForeground(Color.BLACK);
        this.add(lblpassword);
        this.password = new JPasswordField();
        this.password.putClientProperty("JTextField.placeholderText", "Enter Password");
        this.password.putClientProperty("JComponent.roundRect", true);
        this.password.putClientProperty("JPasswordField.showRevealButton", true);
        this.password.setBounds(400, 60, 150, 25);
        this.add(this.password);
        JLabel loggininas = new JLabel("Loggin in as");
        loggininas.setBounds(300, 100, 100, 20);
        loggininas.setForeground(Color.BLACK);
        this.add(loggininas);
        this.logginin = new Choice();
        this.logginin.add("Admin");
        this.logginin.add("Customer");
        this.logginin.setBounds(400, 100, 150, 20);
        this.logginin.setForeground(Color.WHITE);
        this.add(this.logginin);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i2 = i1.getImage().getScaledInstance(16, 16, 1);
        this.login = new JButton("Login", new ImageIcon(i2));
        this.login.putClientProperty("JButton.buttonType", "roundRect");
        this.login.setBounds(330, 160, 100, 25);
        this.login.addActionListener(this);
        this.add(this.login);
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i4 = i3.getImage().getScaledInstance(16, 16, 1);
        this.cancel = new JButton("Cancel", new ImageIcon(i4));
        this.cancel.putClientProperty("JButton.buttonType", "roundRect");
        this.cancel.setBounds(450, 160, 100, 25);
        this.cancel.addActionListener(this);
        this.add(this.cancel);
        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i6 = i5.getImage().getScaledInstance(16, 16, 1);
        this.signup = new JButton("Signup", new ImageIcon(i6));
        this.signup.putClientProperty("JButton.buttonType", "roundRect");
        this.signup.setBounds(380, 200, 100, 25);
        this.signup.addActionListener(this);
        this.add(this.signup);
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i8 = i7.getImage().getScaledInstance(250, 250, 1);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image = new JLabel(i9);
        image.setBounds(0, 0, 250, 250);
        this.add(image);
        this.setSize(640, 300);
        this.setLocation(400, 200);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        block8: {
            if (ae.getSource() == this.login) {
                String susername = this.username.getText();
                String spassword = this.password.getText();
                String user = this.logginin.getSelectedItem();
                try {
                    Conn c = new Conn();
                    String query = "select * from login where username = '" + susername + "' and password = '" + spassword + "' and user = '" + user + "'";
                    ResultSet rs = c.s.executeQuery(query);
                    if (rs.next()) {
                        String meter = rs.getString("meter_no");
                        this.setVisible(false);
                        new Project(user, meter);
                        break block8;
                    }
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    this.username.setText("");
                    this.password.setText("");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (ae.getSource() == this.cancel) {
                this.setVisible(false);
            } else if (ae.getSource() == this.signup) {
                this.setVisible(false);
                new Signup();
            }
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
            UIManager.put("control", new Color(128, 128, 128));
            UIManager.put("nimbusBase", new Color(18, 30, 49));
            UIManager.put("nimbusFocus", new Color(115, 164, 209));
            UIManager.put("nimbusLightBackground", new Color(18, 30, 49));
            UIManager.put("nimbusDarkBackground", new Color(18, 30, 49));
            UIManager.put("nimbusSelectionBackground", new Color(115, 164, 209));
            UIManager.put("text", Color.BLACK);
            UIManager.put("nimbusSelection", new Color(18, 30, 49));
            UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
            SwingUtilities.invokeLater(new Runnable(){

                @Override
                public void run() {
                    new Login();
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
