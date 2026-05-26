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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculateBill
extends JInternalFrame
implements ActionListener {
    JTextField tfname;
    JTextField tfaddress;
    JTextField tfstate;
    JTextField tfunits;
    JTextField tfemail;
    JTextField tfphone;
    JButton next;
    JButton cancel;
    JLabel lblname;
    JLabel labeladdress;
    Choice meternumber;
    Choice cmonth;

    CalculateBill() {
        super("Calculate Bill", true, true, true, true);
        this.setSize(700, 500);
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.LIGHT_GRAY);
        this.add(p);
        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(100, 10, 400, 25);
        heading.setFont(new Font("Tahoma", 0, 24));
        heading.setForeground(Color.BLACK);
        p.add(heading);
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(100, 80, 100, 20);
        lblmeternumber.setForeground(Color.BLACK);
        p.add(lblmeternumber);
        this.meternumber = new Choice();
        this.meternumber.setBackground(Color.LIGHT_GRAY);
        this.meternumber.setForeground(Color.BLACK);
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                this.meternumber.add(rs.getString("meter_no"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.meternumber.setBounds(240, 80, 200, 20);
        p.add(this.meternumber);
        JLabel lblmeterno = new JLabel("Name");
        lblmeterno.setBounds(100, 120, 100, 20);
        lblmeterno.setForeground(Color.BLACK);
        p.add(lblmeterno);
        this.lblname = new JLabel("");
        this.lblname.setBounds(240, 120, 100, 20);
        this.lblname.setForeground(Color.BLACK);
        p.add(this.lblname);
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100, 160, 100, 20);
        lbladdress.setForeground(Color.BLACK);
        p.add(lbladdress);
        this.labeladdress = new JLabel();
        this.labeladdress.setBounds(240, 160, 200, 20);
        this.labeladdress.setForeground(Color.BLACK);
        p.add(this.labeladdress);
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + this.meternumber.getSelectedItem() + "'");
            while (rs.next()) {
                this.lblname.setText(rs.getString("name"));
                this.labeladdress.setText(rs.getString("address"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.meternumber.addItemListener(new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + CalculateBill.this.meternumber.getSelectedItem() + "'");
                    while (rs.next()) {
                        CalculateBill.this.lblname.setText(rs.getString("name"));
                        CalculateBill.this.labeladdress.setText(rs.getString("address"));
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        JLabel lblcity = new JLabel("Units Consumed");
        lblcity.setBounds(100, 200, 100, 20);
        lblcity.setForeground(Color.BLACK);
        p.add(lblcity);
        this.tfunits = new JTextField();
        this.tfunits.setBounds(240, 200, 200, 25);
        this.tfunits.setBackground(Color.LIGHT_GRAY);
        this.tfunits.setForeground(Color.BLACK);
        p.add(this.tfunits);
        JLabel lblstate = new JLabel("Month");
        lblstate.setBounds(100, 240, 100, 20);
        lblstate.setForeground(Color.BLACK);
        p.add(lblstate);
        this.cmonth = new Choice();
        this.cmonth.setBounds(240, 240, 200, 20);
        this.cmonth.setBackground(Color.LIGHT_GRAY);
        this.cmonth.setForeground(Color.BLACK);
        this.cmonth.add("January");
        this.cmonth.add("February");
        this.cmonth.add("March");
        this.cmonth.add("April");
        this.cmonth.add("May");
        this.cmonth.add("June");
        this.cmonth.add("July");
        this.cmonth.add("August");
        this.cmonth.add("September");
        this.cmonth.add("October");
        this.cmonth.add("November");
        this.cmonth.add("December");
        p.add(this.cmonth);
        this.next = new JButton("Submit");
        this.next.setBounds(120, 350, 100, 25);
        this.next.setBackground(Color.WHITE);
        this.next.setForeground(Color.BLACK);
        this.next.addActionListener(this);
        p.add(this.next);
        this.cancel = new JButton("Cancel");
        this.cancel.setBounds(250, 350, 100, 25);
        this.cancel.setBackground(Color.WHITE);
        this.cancel.setForeground(Color.BLACK);
        this.cancel.addActionListener(this);
        p.add(this.cancel);
        this.setLayout(new BorderLayout());
        this.add((Component)p, "Center");
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.png"));
        Image i2 = i1.getImage().getScaledInstance(150, 150, 1);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        this.add((Component)image, "West");
        this.getContentPane().setBackground(Color.BLACK);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.next) {
            String meter = this.meternumber.getSelectedItem();
            String units = this.tfunits.getText();
            String month = this.cmonth.getSelectedItem();
            int totalbill = 0;
            int unit_consumed = Integer.parseInt(units);
            String query = "select * from tax";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                while (rs.next()) {
                    totalbill += unit_consumed * Integer.parseInt(rs.getString("cost_per_unit"));
                    totalbill += Integer.parseInt(rs.getString("meter_rent"));
                    totalbill += Integer.parseInt(rs.getString("service_charge"));
                    totalbill += Integer.parseInt(rs.getString("service_tax"));
                    totalbill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
                    totalbill += Integer.parseInt(rs.getString("fixed_tax"));
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            String query2 = "insert into bill values('" + meter + "', '" + month + "', '" + units + "', '" + totalbill + "','Not Paid')";
            try {
                Conn c = new Conn();
                c.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
                this.setVisible(false);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new CalculateBill();
    }
}
