package electricity.billing.system;

import electricity.billing.system.Conn;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class ViewInformation
extends JInternalFrame
implements ActionListener {
    JButton cancel;

    ViewInformation(String meter) {
        super("View Information", true, true, true, true);
        this.setBounds(350, 150, 850, 650);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setLayout(null);
        JLabel heading = new JLabel("VIEW CUSTOMER INFORMATION");
        heading.setBounds(250, 0, 500, 40);
        heading.setFont(new Font("Tahoma", 0, 20));
        heading.setForeground(Color.BLACK);
        this.add(heading);
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(70, 80, 100, 20);
        lblname.setForeground(Color.BLACK);
        this.add(lblname);
        JLabel name = new JLabel("");
        name.setBounds(250, 80, 100, 20);
        name.setForeground(Color.BLACK);
        this.add(name);
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(70, 140, 100, 20);
        lblmeternumber.setForeground(Color.BLACK);
        this.add(lblmeternumber);
        JLabel meternumber = new JLabel("");
        meternumber.setBounds(250, 140, 100, 20);
        meternumber.setForeground(Color.BLACK);
        this.add(meternumber);
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(70, 200, 100, 20);
        lbladdress.setForeground(Color.BLACK);
        this.add(lbladdress);
        JLabel address = new JLabel("");
        address.setBounds(250, 200, 100, 20);
        address.setForeground(Color.BLACK);
        this.add(address);
        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(70, 260, 100, 20);
        lblcity.setForeground(Color.BLACK);
        this.add(lblcity);
        JLabel city = new JLabel("");
        city.setBounds(250, 260, 100, 20);
        city.setForeground(Color.BLACK);
        this.add(city);
        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(500, 80, 100, 20);
        lblstate.setForeground(Color.BLACK);
        this.add(lblstate);
        JLabel state = new JLabel("");
        state.setBounds(650, 80, 100, 20);
        state.setForeground(Color.BLACK);
        this.add(state);
        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(500, 140, 100, 20);
        lblemail.setForeground(Color.BLACK);
        this.add(lblemail);
        JLabel email = new JLabel("");
        email.setBounds(650, 140, 100, 20);
        email.setForeground(Color.BLACK);
        this.add(email);
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(500, 200, 100, 20);
        lblphone.setForeground(Color.BLACK);
        this.add(lblphone);
        JLabel phone = new JLabel("");
        phone.setBounds(650, 200, 100, 20);
        phone.setForeground(Color.BLACK);
        this.add(phone);
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + meter + "'");
            while (rs.next()) {
                name.setText(rs.getString("name"));
                address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("phone"));
                meternumber.setText(rs.getString("meter_no"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.cancel = new JButton("Cancel");
        this.cancel.setBackground(Color.WHITE);
        this.cancel.setForeground(Color.BLACK);
        this.cancel.setBounds(350, 340, 100, 25);
        this.cancel.addActionListener(this);
        this.add(this.cancel);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.cancel) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new ViewInformation("");
    }
}
