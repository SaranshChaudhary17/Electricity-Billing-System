package electricity.billing.system;

import electricity.billing.system.Conn;
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MeterInfo
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
    Choice meterlocation;
    Choice metertype;
    Choice phasecode;
    Choice billtype;
    String meternumber;

    MeterInfo(String meternumber) {
        super("Enter Meter Details", true, true, true, true);
        this.meternumber = meternumber;
        this.setSize(700, 500);
        
        this.setDefaultCloseOperation(2);
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.WHITE);
        this.add(p);
        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180, 10, 200, 25);
        heading.setFont(new Font("Tahoma", 0, 24));
        heading.setForeground(Color.BLACK);
        p.add(heading);
        JLabel lblname = new JLabel("Meter Number");
        lblname.setBounds(100, 80, 100, 20);
        lblname.setForeground(Color.BLACK);
        p.add(lblname);
        JLabel lblmeternumber = new JLabel(meternumber);
        lblmeternumber.setBounds(240, 80, 100, 20);
        lblmeternumber.setForeground(Color.BLACK);
        p.add(lblmeternumber);
        JLabel lblmeterno = new JLabel("Meter Location");
        lblmeterno.setBounds(100, 120, 100, 20);
        lblmeterno.setForeground(Color.BLACK);
        p.add(lblmeterno);
        this.meterlocation = new Choice();
        this.meterlocation.add("Outside");
        this.meterlocation.add("Inside");
        this.meterlocation.setBounds(240, 120, 200, 20);
        p.add(this.meterlocation);
        JLabel lbladdress = new JLabel("Meter Type");
        lbladdress.setBounds(100, 160, 100, 20);
        lbladdress.setForeground(Color.BLACK);
        p.add(lbladdress);
        this.metertype = new Choice();
        this.metertype.add("Electric Meter");
        this.metertype.add("Solar Meter");
        this.metertype.add("Smart Meter");
        this.metertype.setBounds(240, 160, 200, 20);
        p.add(this.metertype);
        JLabel lblcity = new JLabel("Phase Code");
        lblcity.setBounds(100, 200, 100, 20);
        lblcity.setForeground(Color.BLACK);
        p.add(lblcity);
        this.phasecode = new Choice();
        this.phasecode.add("011");
        this.phasecode.add("022");
        this.phasecode.add("033");
        this.phasecode.add("044");
        this.phasecode.add("055");
        this.phasecode.add("066");
        this.phasecode.add("077");
        this.phasecode.add("088");
        this.phasecode.add("099");
        this.phasecode.setBounds(240, 200, 200, 20);
        p.add(this.phasecode);
        JLabel lblstate = new JLabel("Bill Type");
        lblstate.setBounds(100, 240, 100, 20);
        lblstate.setForeground(Color.BLACK);
        p.add(lblstate);
        this.billtype = new Choice();
        this.billtype.add("Normal");
        this.billtype.add("Industrial");
        this.billtype.setBounds(240, 240, 200, 20);
        p.add(this.billtype);
        JLabel lblemail = new JLabel("Days");
        lblemail.setBounds(100, 280, 100, 20);
        lblemail.setForeground(Color.BLACK);
        p.add(lblemail);
        JLabel lblemails = new JLabel("30 Days");
        lblemails.setBounds(240, 280, 100, 20);
        lblemails.setForeground(Color.BLACK);
        p.add(lblemails);
        JLabel lblphone = new JLabel("Note");
        lblphone.setBounds(100, 320, 100, 20);
        lblphone.setForeground(Color.BLACK);
        p.add(lblphone);
        JLabel lblphones = new JLabel("By Default Bill is calculated for 30 days only");
        lblphones.setBounds(240, 320, 500, 20);
        lblphones.setForeground(Color.BLACK);
        p.add(lblphones);
        this.next = new JButton("Submit");
        this.next.setBounds(220, 390, 100, 25);
        this.next.setBackground(Color.WHITE);
        this.next.setForeground(Color.BLACK);
        this.next.addActionListener(this);
        p.add(this.next);
        this.cancel = new JButton("Cancel");
        this.cancel.setBounds(340, 390, 100, 25);
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
        this.getContentPane().setBackground(Color.black);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.next) {
            String meter = this.meternumber;
            String location = this.meterlocation.getSelectedItem();
            String type = this.metertype.getSelectedItem();
            String code = this.phasecode.getSelectedItem();
            String typebill = this.billtype.getSelectedItem();
            String days = "30";
            String query = "insert into meter_info values('" + meter + "', '" + location + "', '" + type + "', '" + code + "', '" + typebill + "', '" + days + "')";
            try {
                Conn c = new Conn();
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Meter Information Added Successfully");
                this.setVisible(false);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == this.cancel) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new MeterInfo("");
    }
}
