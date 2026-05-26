package electricity.billing.system;

import electricity.billing.system.Conn;
import electricity.billing.system.Login;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Signup
extends JFrame
implements ActionListener {
    JButton create;
    JButton back;
    Choice accountType;
    JTextField meter;
    JTextField username;
    JTextField name;
    JTextField password;

    Signup() {
        super("Create an Account");
        this.setBounds(450, 150, 700, 400);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        JPanel panel = new JPanel();
        panel.setBounds(30, 30, 650, 300);
        panel.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 2), "Create Account", 4, 2, null, Color.DARK_GRAY));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);
        panel.setForeground(Color.DARK_GRAY);
        this.add(panel);
        JLabel heading = new JLabel("Create Account As");
        heading.setBounds(100, 50, 140, 20);
        heading.setForeground(Color.DARK_GRAY);
        heading.setFont(new Font("Tahoma", 1, 14));
        panel.add(heading);
        this.accountType = new Choice();
        this.accountType.add("Admin");
        this.accountType.add("Customer");
        this.accountType.setBounds(260, 50, 150, 20);
        panel.add(this.accountType);
        final JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(100, 90, 140, 20);
        lblmeter.setForeground(Color.DARK_GRAY);
        lblmeter.setFont(new Font("Tahoma", 1, 14));
        lblmeter.setVisible(false);
        panel.add(lblmeter);
        this.meter = new JTextField();
        this.meter.setBounds(260, 90, 150, 20);
        this.meter.setVisible(false);
        panel.add(this.meter);
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(100, 130, 140, 20);
        lblusername.setForeground(Color.DARK_GRAY);
        lblusername.setFont(new Font("Tahoma", 1, 14));
        panel.add(lblusername);
        this.username = new JTextField();
        this.username.setBounds(260, 130, 150, 20);
        panel.add(this.username);
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100, 170, 140, 20);
        lblname.setForeground(Color.DARK_GRAY);
        lblname.setFont(new Font("Tahoma", 1, 14));
        panel.add(lblname);
        this.name = new JTextField();
        this.name.setBounds(260, 170, 150, 20);
        panel.add(this.name);
        this.meter.addFocusListener(new FocusListener(){

            @Override
            public void focusGained(FocusEvent fe) {
            }

            @Override
            public void focusLost(FocusEvent fe) {
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from login where meter_no = '" + Signup.this.meter.getText() + "'");
                    while (rs.next()) {
                        Signup.this.name.setText(rs.getString("name"));
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(100, 210, 140, 20);
        lblpassword.setForeground(Color.DARK_GRAY);
        lblpassword.setFont(new Font("Tahoma", 1, 14));
        panel.add(lblpassword);
        this.password = new JTextField();
        this.password.setBounds(260, 210, 150, 20);
        panel.add(this.password);
        this.accountType.addItemListener(new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent ae) {
                String user = Signup.this.accountType.getSelectedItem();
                if (user.equals("Customer")) {
                    lblmeter.setVisible(true);
                    Signup.this.meter.setVisible(true);
                    Signup.this.name.setEditable(false);
                } else {
                    lblmeter.setVisible(false);
                    Signup.this.meter.setVisible(false);
                    Signup.this.name.setEditable(true);
                }
            }
        });
        this.create = new JButton("Create");
        this.create.setBackground(new Color(0, 123, 255));
        this.create.setForeground(Color.BLACK);
        this.create.setBounds(140, 260, 120, 25);
        this.create.addActionListener(this);
        panel.add(this.create);
        this.back = new JButton("Back");
        this.back.setBackground(new Color(0, 123, 255));
        this.back.setForeground(Color.BLACK);
        this.back.setBounds(300, 260, 120, 25);
        this.back.addActionListener(this);
        panel.add(this.back);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, 1);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(415, 30, 250, 250);
        panel.add(image);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.create) {
            String atype = this.accountType.getSelectedItem();
            String susername = this.username.getText();
            String sname = this.name.getText();
            String spassword = this.password.getText();
            String smeter = this.meter.getText();
            try {
                Conn c = new Conn();
                String query = null;
                query = atype.equals("Admin") ? "insert into login values('" + smeter + "', '" + susername + "', '" + sname + "', '" + spassword + "', '" + atype + "')" : "update login set username = '" + susername + "', password = '" + spassword + "', user = '" + atype + "' where meter_no = '" + smeter + "'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                this.setVisible(false);
                new Login();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == this.back) {
            this.setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
