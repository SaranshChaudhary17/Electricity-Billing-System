package electricity.billing.system;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class PaymentSuccess
extends JInternalFrame {
    PaymentSuccess(String meter, String month, String units, String totalbill) {
        super("Payment Successful", true, true, true, true);
        this.setLayout(null);
        this.setBounds(450, 150, 500, 300);
        Color backgroundColor = Color.LIGHT_GRAY;
        Color textColor = Color.BLACK;
        JLabel heading = new JLabel("Payment Successful");
        heading.setFont(new Font("Tahoma", 1, 24));
        heading.setBounds(100, 20, 300, 30);
        heading.setForeground(textColor);
        this.add(heading);
        JLabel lblmeter = new JLabel("Meter Number: ");
        lblmeter.setBounds(50, 80, 200, 20);
        lblmeter.setForeground(textColor);
        this.add(lblmeter);
        JLabel meternumber = new JLabel(meter);
        meternumber.setBounds(200, 80, 200, 20);
        meternumber.setForeground(textColor);
        this.add(meternumber);
        JLabel lblmonth = new JLabel("Month: ");
        lblmonth.setBounds(50, 110, 200, 20);
        lblmonth.setForeground(textColor);
        this.add(lblmonth);
        JLabel monthlabel = new JLabel(month);
        monthlabel.setBounds(200, 110, 200, 20);
        monthlabel.setForeground(textColor);
        this.add(monthlabel);
        JLabel lblunits = new JLabel("Units Consumed: ");
        lblunits.setBounds(50, 140, 200, 20);
        lblunits.setForeground(textColor);
        this.add(lblunits);
        JLabel unitslabel = new JLabel(units);
        unitslabel.setBounds(200, 140, 200, 20);
        unitslabel.setForeground(textColor);
        this.add(unitslabel);
        JLabel lbltotalbill = new JLabel("Total Bill: ");
        lbltotalbill.setBounds(50, 170, 200, 20);
        lbltotalbill.setForeground(textColor);
        this.add(lbltotalbill);
        JLabel totalbilllabel = new JLabel(totalbill);
        totalbilllabel.setBounds(200, 170, 200, 20);
        totalbilllabel.setForeground(textColor);
        this.add(totalbilllabel);
        JButton close = new JButton("Close");
        close.setBounds(200, 220, 100, 25);
        close.setBackground(Color.WHITE);
        close.setForeground(Color.BLACK);
        close.addActionListener(e -> this.setVisible(false));
        this.add(close);
        this.getContentPane().setBackground(backgroundColor);
        
    }

    public static void main(String[] args) {
        new PaymentSuccess("", "", "", "");
    }
}
