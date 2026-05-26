package electricity.billing.system;

import electricity.billing.system.Conn;
import electricity.billing.system.PaymentSuccess;
import java.awt.Choice;
import java.awt.Color;
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

public class PayBill
extends JInternalFrame
implements ActionListener {
    Choice cmonth;
    JButton pay;
    JButton back;
    String meter;
    JLabel labelunits;
    JLabel labeltotalbill;
    JLabel labelstatus;

    PayBill(final String meter) {
        super("Pay Bill", true, true, true, true);
        this.meter = meter;
        this.setLayout(null);
        this.setBounds(300, 150, 900, 600);
        Color backgroundColor = Color.LIGHT_GRAY;
        Color textColor = Color.BLACK;
        JLabel heading = new JLabel("Electricity Bill");
        heading.setFont(new Font("Tahoma", 1, 24));
        heading.setBounds(120, 5, 400, 30);
        heading.setForeground(textColor);
        this.add(heading);
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(35, 80, 200, 20);
        lblmeternumber.setForeground(textColor);
        this.add(lblmeternumber);
        JLabel meternumber = new JLabel(meter);
        meternumber.setBounds(300, 80, 200, 20);
        meternumber.setForeground(textColor);
        this.add(meternumber);
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35, 140, 200, 20);
        lblname.setForeground(textColor);
        this.add(lblname);
        JLabel labelname = new JLabel("");
        labelname.setBounds(300, 140, 200, 20);
        labelname.setForeground(textColor);
        this.add(labelname);
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(35, 200, 200, 20);
        lblmonth.setForeground(textColor);
        this.add(lblmonth);
        this.cmonth = new Choice();
        this.cmonth.setBounds(300, 200, 200, 20);
        this.add(this.cmonth);
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
        this.cmonth.setForeground(Color.white);
        JLabel lblunits = new JLabel("Units");
        lblunits.setBounds(35, 260, 200, 20);
        lblunits.setForeground(textColor);
        this.add(lblunits);
        this.labelunits = new JLabel("");
        this.labelunits.setBounds(300, 260, 200, 20);
        this.labelunits.setForeground(textColor);
        this.add(this.labelunits);
        JLabel lbltotalbill = new JLabel("Total Bill");
        lbltotalbill.setBounds(35, 320, 200, 20);
        lbltotalbill.setForeground(textColor);
        this.add(lbltotalbill);
        this.labeltotalbill = new JLabel("");
        this.labeltotalbill.setBounds(300, 320, 200, 20);
        this.labeltotalbill.setForeground(textColor);
        this.add(this.labeltotalbill);
        JLabel lblstatus = new JLabel("Status");
        lblstatus.setBounds(35, 380, 200, 20);
        lblstatus.setForeground(textColor);
        this.add(lblstatus);
        this.labelstatus = new JLabel("");
        this.labelstatus.setBounds(300, 380, 200, 20);
        this.labelstatus.setForeground(Color.RED);
        this.add(this.labelstatus);
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + meter + "'");
            while (rs.next()) {
                meternumber.setText(meter);
                labelname.setText(rs.getString("name"));
            }
            rs = c.s.executeQuery("select * from bill where meter_no = '" + meter + "' AND month = 'January'");
            while (rs.next()) {
                this.labelunits.setText(rs.getString("units"));
                this.labeltotalbill.setText(rs.getString("totalbill"));
                this.labelstatus.setText(rs.getString("status"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.cmonth.addItemListener(new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent ae) {
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from bill where meter_no = '" + meter + "' AND month = '" + PayBill.this.cmonth.getSelectedItem() + "'");
                    while (rs.next()) {
                        PayBill.this.labelunits.setText(rs.getString("units"));
                        PayBill.this.labeltotalbill.setText(rs.getString("totalbill"));
                        PayBill.this.labelstatus.setText(rs.getString("status"));
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.pay = new JButton("Pay");
        this.pay.setBackground(Color.WHITE);
        this.pay.setForeground(Color.BLACK);
        this.pay.setBounds(100, 460, 100, 25);
        this.pay.addActionListener(this);
        this.add(this.pay);
        this.back = new JButton("Back");
        this.back.setBackground(Color.WHITE);
        this.back.setForeground(Color.BLACK);
        this.back.setBounds(230, 460, 100, 25);
        this.back.addActionListener(this);
        this.add(this.back);
        this.getContentPane().setBackground(backgroundColor);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, 1);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 120, 600, 300);
        this.add(image);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.pay) {
            try {
                Conn c = new Conn();
                c.s.executeUpdate("update bill set status = 'Paid' where meter_no = '" + this.meter + "' AND month='" + this.cmonth.getSelectedItem() + "'");
                PaymentSuccess ps = new PaymentSuccess(this.meter, this.cmonth.getSelectedItem(), this.labelunits.getText(), this.labeltotalbill.getText());
                if (this.getDesktopPane() != null) {
                    this.getDesktopPane().add(ps);
                    int tx = (this.getDesktopPane().getWidth() - ps.getWidth()) / 2;
                    int ty = (this.getDesktopPane().getHeight() - ps.getHeight()) / 2;
                    ps.setLocation(tx, ty);
                    ps.setVisible(true);
                    try { ps.setSelected(true); } catch (Exception ex) {}
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            this.setVisible(false);
        } else {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new PayBill("");
    }
}
