package electricity.billing.system;

import electricity.billing.system.Conn;
import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import net.proteanit.sql.DbUtils;

public class DepositDetails
extends JInternalFrame
implements ActionListener {
    Choice meternumber;
    Choice cmonth;
    JTable table;
    JButton search;
    JButton print;

    DepositDetails() {
        super("Deposit Details", true, true, true, true);
        this.setSize(700, 700);
        
        this.setLayout(null);
        this.getContentPane().setBackground(Color.WHITE);
        JLabel lblmeternumber = new JLabel("Search By Meter Number");
        lblmeternumber.setBounds(20, 20, 150, 20);
        lblmeternumber.setForeground(Color.black);
        this.add(lblmeternumber);
        this.meternumber = new Choice();
        this.meternumber.setBounds(180, 20, 150, 25);
        this.meternumber.setBackground(Color.WHITE);
        this.meternumber.setForeground(Color.black);
        this.add(this.meternumber);
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
        JLabel lblmonth = new JLabel("Search By Month");
        lblmonth.setBounds(400, 20, 100, 20);
        lblmonth.setForeground(Color.BLACK);
        this.add(lblmonth);
        this.cmonth = new Choice();
        this.cmonth.setBounds(520, 20, 150, 25);
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
        this.cmonth.setBackground(Color.WHITE);
        this.add(this.cmonth);
        this.table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bill");
            this.table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        JScrollPane sp = new JScrollPane(this.table);
        sp.setBounds(0, 100, 700, 600);
        this.add(sp);
        this.search = new JButton("Search");
        this.search.setBounds(20, 70, 80, 25);
        this.search.addActionListener(this);
        this.add(this.search);
        this.print = new JButton("Print");
        this.print.setBounds(120, 70, 80, 25);
        this.print.addActionListener(this);
        this.add(this.print);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.search) {
            String query = "select * from bill where meter_no = '" + this.meternumber.getSelectedItem() + "' and month = '" + this.cmonth.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                this.table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == this.print) {
            try {
                this.table.print();
            }
            catch (Exception e) {
                e.printStackTrace();
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
                    new DepositDetails();
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
