package electricity.billing.system;

import electricity.billing.system.Conn;
import electricity.billing.system.MeterInfo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewCustomer
extends JInternalFrame
implements ActionListener {
    JTextField tfname;
    JTextField tfaddress;
    JTextField tfstate;
    JTextField tfcity;
    JTextField tfemail;
    JTextField tfphone;
    JButton next;
    JButton cancel;
    JLabel lblmeter;

    NewCustomer() {
        super("Register a Customer", true, true, true, true);
        this.setSize(700, 500);
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.WHITE);
        this.add(p);
        JLabel heading = new JLabel("New Customer");
        heading.setBounds(180, 10, 200, 25);
        heading.setFont(new Font("Tahoma", 0, 24));
        heading.setForeground(Color.BLACK);
        p.add(heading);
        JLabel lblname = new JLabel("Customer Name");
        lblname.setBounds(100, 80, 100, 20);
        lblname.setForeground(Color.BLACK);
        p.add(lblname);
        this.tfname = new JTextField();
        this.tfname.setBounds(240, 80, 200, 25);
        p.add(this.tfname);
        JLabel lblmeterno = new JLabel("Meter Number");
        lblmeterno.setBounds(100, 120, 100, 25);
        lblmeterno.setForeground(Color.BLACK);
        p.add(lblmeterno);
        this.lblmeter = new JLabel("");
        this.lblmeter.setBounds(240, 120, 100, 25);
        this.lblmeter.setForeground(Color.BLACK);
        p.add(this.lblmeter);
        Random ran = new Random();
        long number = ran.nextLong() % 1000000L;
        this.lblmeter.setText("" + Math.abs(number));
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100, 160, 100, 25);
        lbladdress.setForeground(Color.BLACK);
        p.add(lbladdress);
        this.tfaddress = new JTextField();
        this.tfaddress.setBounds(240, 160, 200, 25);
        p.add(this.tfaddress);
        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(100, 200, 100, 25);
        lblcity.setForeground(Color.BLACK);
        p.add(lblcity);
        this.tfcity = new JTextField();
        this.tfcity.setBounds(240, 200, 200, 25);
        p.add(this.tfcity);
        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(100, 240, 100, 25);
        lblstate.setForeground(Color.BLACK);
        p.add(lblstate);
        this.tfstate = new JTextField();
        this.tfstate.setBounds(240, 240, 200, 25);
        p.add(this.tfstate);
        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(100, 280, 100, 25);
        lblemail.setForeground(Color.BLACK);
        p.add(lblemail);
        this.tfemail = new JTextField();
        this.tfemail.setBounds(240, 280, 200, 25);
        p.add(this.tfemail);
        JLabel lblphone = new JLabel("Phone Number");
        lblphone.setBounds(100, 320, 100, 25);
        lblphone.setForeground(Color.BLACK);
        p.add(lblphone);
        this.tfphone = new JTextField();
        this.tfphone.setBounds(240, 320, 200, 25);
        p.add(this.tfphone);
        this.next = new JButton("Next");
        this.next.setBounds(120, 390, 100, 25);
        this.next.setBackground(Color.WHITE);
        this.next.setForeground(Color.BLACK);
        this.next.addActionListener(this);
        p.add(this.next);
        this.cancel = new JButton("Cancel");
        this.cancel.setBounds(250, 390, 100, 25);
        this.cancel.setBackground(Color.WHITE);
        this.cancel.setForeground(Color.BLACK);
        this.cancel.addActionListener(this);
        p.add(this.cancel);
        this.setLayout(new BorderLayout());
        this.add((Component)p, "Center");
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, 1);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        this.add((Component)image, "West");
        this.getContentPane().setBackground(Color.BLACK);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.next) {
            String name = this.tfname.getText();
            String meter = this.lblmeter.getText();
            String address = this.tfaddress.getText();
            String city = this.tfcity.getText();
            String state = this.tfstate.getText();
            String email = this.tfemail.getText();
            String phone = this.tfphone.getText();
            String query1 = "insert into customer values('" + name + "', '" + meter + "', '" + address + "', '" + city + "', '" + state + "', '" + email + "', '" + phone + "')";
            String query2 = "insert into login values('" + meter + "', '', '" + name + "', '', '')";
            try {
                Conn c = new Conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
                this.setVisible(false);
                MeterInfo mi = new MeterInfo(meter);
                if (this.getDesktopPane() != null) {
                    this.getDesktopPane().add(mi);
                    int tx = (this.getDesktopPane().getWidth() - mi.getWidth()) / 2;
                    int ty = (this.getDesktopPane().getHeight() - mi.getHeight()) / 2;
                    mi.setLocation(tx, ty);
                    mi.setVisible(true);
                    try { mi.setSelected(true); } catch (Exception ex) {}
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewCustomer();
    }
}
