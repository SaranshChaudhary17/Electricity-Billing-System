package electricity.billing.system;

import electricity.billing.system.Conn;
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GenerateBill
extends JInternalFrame
implements ActionListener {
    String meter;
    JButton bill;
    Choice cmonth;
    JTextArea area;

    GenerateBill(String meter) {
        this.meter = meter;
        this.setSize(500, 800);
        
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.WHITE);
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        JLabel heading = new JLabel("Generate Bill");
        heading.setForeground(Color.BLACK);
        JLabel meternumber = new JLabel(meter);
        meternumber.setForeground(Color.BLACK);
        this.cmonth = new Choice();
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
        this.area = new JTextArea(50, 15);
        this.area.setText("\n\n\t--------Click on the---------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month");
        this.area.setFont(new Font("Senserif", 2, 18));
        this.area.setForeground(Color.BLACK);
        this.area.setBackground(Color.WHITE);
        JScrollPane pane = new JScrollPane(this.area);
        this.bill = new JButton("Generate Bill");
        this.bill.addActionListener(this);
        this.bill.setBackground(Color.BLACK);
        this.bill.setForeground(Color.WHITE);
        panel.add(heading);
        panel.add(meternumber);
        panel.add(this.cmonth);
        panel.setBackground(Color.LIGHT_GRAY);
        this.add((Component)panel, "North");
        this.add((Component)pane, "Center");
        this.add((Component)this.bill, "South");
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            Conn c = new Conn();
            String month = this.cmonth.getSelectedItem();
            this.area.setText("\tChaudhary Power Limited\nELECTRICITY BILL GENERATED FOR THE MONTH\n\tOF " + month + ", 2022\n\n\n");
            this.area.append("\n---------------------------------------------------\n");
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + this.meter + "'");
            if (rs.next()) {
                this.area.append("\n    Customer Name: " + rs.getString("name"));
                this.area.append("\n    Meter Number   : " + rs.getString("meter_no"));
                this.area.append("\n    Address             : " + rs.getString("address"));
                this.area.append("\n    City                 : " + rs.getString("city"));
                this.area.append("\n    State                : " + rs.getString("state"));
                this.area.append("\n    Email                : " + rs.getString("email"));
                this.area.append("\n    Phone                 : " + rs.getString("phone"));
                this.area.append("\n---------------------------------------------------");
                this.area.append("\n");
            }
            if ((rs = c.s.executeQuery("select * from meter_info where meter_no = '" + this.meter + "'")).next()) {
                this.area.append("\n    Meter Location: " + rs.getString("meter_location"));
                this.area.append("\n    Meter Type:     " + rs.getString("meter_type"));
                this.area.append("\n    Phase Code:        " + rs.getString("phase_code"));
                this.area.append("\n    Bill Type:          " + rs.getString("bill_type"));
                this.area.append("\n    Days:                " + rs.getString("days"));
                this.area.append("\n---------------------------------------------------");
                this.area.append("\n");
            }
            if ((rs = c.s.executeQuery("select * from tax")).next()) {
                this.area.append("\n");
                this.area.append("\n    Cost Per Unit: " + rs.getString("cost_per_unit"));
                this.area.append("\n    Meter Rent:     " + rs.getString("meter_rent"));
                this.area.append("\n    Service Charge:        " + rs.getString("service_charge"));
                this.area.append("\n    Service Tax:          " + rs.getString("service_tax"));
                this.area.append("\n    Swacch Bharat Cess:                " + rs.getString("swacch_bharat_cess"));
                this.area.append("\n    Fixed Tax: " + rs.getString("fixed_tax"));
                this.area.append("\n");
            }
            if ((rs = c.s.executeQuery("select * from bill where meter_no = '" + this.meter + "' and month='" + month + "'")).next()) {
                this.area.append("\n");
                this.area.append("\n    Current Month: " + rs.getString("month"));
                this.area.append("\n    Units Consumed:     " + rs.getString("units"));
                this.area.append("\n    Total Charges:        " + rs.getString("totalbill"));
                this.area.append("\n-------------------------------------------------------");
                this.area.append("\n    Total Payable: " + rs.getString("totalbill"));
                this.area.append("\n");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GenerateBill("");
    }
}
