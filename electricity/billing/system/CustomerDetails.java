package electricity.billing.system;

import electricity.billing.system.Conn;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import net.proteanit.sql.DbUtils;

public class CustomerDetails
extends JInternalFrame
implements ActionListener {
    Choice meternumber;
    Choice cmonth;
    JTable table;
    JButton search;
    JButton print;

    CustomerDetails() {
        super("Customer Details", true, true, true, true);
        this.setSize(1200, 650);
        
        this.table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            this.table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        JScrollPane sp = new JScrollPane(this.table);
        this.add(sp);
        this.print = new JButton("Print");
        this.print.addActionListener(this);
        this.add((Component)this.print, "South");
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            this.table.print();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
            UIManager.put("Table.background", Color.black);
            UIManager.put("Table.foreground", Color.white);
            UIManager.put("Table.selectionBackground", Color.white);
            UIManager.put("Table.selectionForeground", Color.black);
            UIManager.put("Table.gridColor", Color.white);
            UIManager.put("TableHeader.background", Color.white);
            UIManager.put("TableHeader.foreground", Color.black);
            UIManager.put("Label.foreground", Color.white);
            UIManager.put("Button.background", Color.black);
            UIManager.put("Button.foreground", Color.white);
            SwingUtilities.invokeLater(new Runnable(){

                @Override
                public void run() {
                    new CustomerDetails();
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
