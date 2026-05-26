package electricity.billing.system;

import electricity.billing.system.Conn;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UpdateInformation
extends JInternalFrame
implements ActionListener {
    JTextField tfaddress;
    JTextField tfstate;
    JTextField tfcity;
    JTextField tfemail;
    JTextField tfphone;
    JButton update;
    JButton cancel;
    String meter;
    JLabel name;

    UpdateInformation(String meter) {
        super("View Information", true, true, true, true);
        this.meter = meter;
        this.setBounds(300, 150, 1050, 450);
        this.getContentPane().setBackground(new Color(34, 34, 34));
        this.setLayout(null);
        JLabel heading = new JLabel("UPDATE CUSTOMER INFORMATION");
        heading.setBounds(110, 0, 400, 30);
        heading.setFont(new Font("Tahoma", 0, 20));
        heading.setForeground(Color.BLACK);
        this.add(heading);
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(30, 70, 100, 20);
        lblname.setForeground(Color.BLACK);
        this.add(lblname);
        this.name = new JLabel("");
        this.name.setBounds(230, 70, 200, 25);
        this.name.setForeground(Color.BLACK);
        this.add(this.name);
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(30, 110, 100, 25);
        lblmeternumber.setForeground(Color.BLACK);
        this.add(lblmeternumber);
        JLabel meternumber = new JLabel("");
        meternumber.setBounds(230, 110, 200, 25);
        meternumber.setForeground(Color.BLACK);
        this.add(meternumber);
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(30, 150, 100, 20);
        lbladdress.setForeground(Color.BLACK);
        this.add(lbladdress);
        this.tfaddress = new JTextField();
        this.tfaddress.setBounds(230, 150, 200, 25);
        this.tfaddress.setBackground(new Color(50, 50, 50));
        this.tfaddress.setForeground(Color.BLACK);
        this.add(this.tfaddress);
        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(30, 190, 100, 20);
        lblcity.setForeground(Color.BLACK);
        this.add(lblcity);
        this.tfcity = new JTextField();
        this.tfcity.setBounds(230, 190, 200, 25);
        this.tfcity.setBackground(new Color(50, 50, 50));
        this.tfcity.setForeground(Color.BLACK);
        this.add(this.tfcity);
        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(30, 230, 100, 20);
        lblstate.setForeground(Color.BLACK);
        this.add(lblstate);
        this.tfstate = new JTextField();
        this.tfstate.setBounds(230, 230, 200, 25);
        this.tfstate.setBackground(new Color(50, 50, 50));
        this.tfstate.setForeground(Color.BLACK);
        this.add(this.tfstate);
        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(30, 270, 100, 20);
        lblemail.setForeground(Color.BLACK);
        this.add(lblemail);
        this.tfemail = new JTextField();
        this.tfemail.setBounds(230, 270, 200, 25);
        this.tfemail.setBackground(new Color(50, 50, 50));
        this.tfemail.setForeground(Color.BLACK);
        this.add(this.tfemail);
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(30, 310, 100, 20);
        lblphone.setForeground(Color.BLACK);
        this.add(lblphone);
        this.tfphone = new JTextField();
        this.tfphone.setBounds(230, 310, 200, 25);
        this.tfphone.setBackground(new Color(50, 50, 50));
        this.tfphone.setForeground(Color.BLACK);
        this.add(this.tfphone);
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + meter + "'");
            while (rs.next()) {
                this.name.setText(rs.getString("name"));
                this.tfaddress.setText(rs.getString("address"));
                this.tfcity.setText(rs.getString("city"));
                this.tfstate.setText(rs.getString("state"));
                this.tfemail.setText(rs.getString("email"));
                this.tfphone.setText(rs.getString("phone"));
                meternumber.setText(rs.getString("meter_no"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.update = new JButton("Update");
        this.update.setBackground(Color.WHITE);
        this.update.setForeground(Color.BLACK);
        this.update.setBounds(70, 360, 100, 25);
        this.update.addActionListener(this);
        this.add(this.update);
        this.cancel = new JButton("Cancel");
        this.cancel.setBackground(Color.WHITE);
        this.cancel.setForeground(Color.BLACK);
        this.cancel.setBounds(230, 360, 100, 25);
        this.cancel.addActionListener(this);
        this.add(this.cancel);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 300, 1);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(550, 50, 400, 300);
        this.add(image);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.update) {
            String address = this.tfaddress.getText();
            String city = this.tfcity.getText();
            String state = this.tfstate.getText();
            String email = this.tfemail.getText();
            String phone = this.tfphone.getText();
            try {
                Conn c = new Conn();
                c.s.executeUpdate("update customer set address = '" + address + "', city = '" + city + "', state = '" + state + "', email = '" + email + "', phone = '" + phone + "' where meter_no = '" + this.meter + "'");
                JOptionPane.showMessageDialog(null, "User Information Updated Successfully");
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
        new UpdateInformation("");
    }
}
