package electricity.billing.system;

import electricity.billing.system.Conn;
import java.awt.Color;
import java.sql.ResultSet;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import net.proteanit.sql.DbUtils;

public class BillDetails
extends JInternalFrame {
    BillDetails(String meter) {
        super("Bill Details", true, true, true, true);
        this.setSize(700, 650);
        
        this.getContentPane().setBackground(Color.WHITE);
        JTable table = new JTable();
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setGridColor(Color.LIGHT_GRAY);
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(Color.LIGHT_GRAY);
        tableHeader.setForeground(Color.BLACK);
        try {
            Conn c = new Conn();
            String query = "select * from bill where meter_no = '" + meter + "'";
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 0, 700, 650);
        sp.getViewport().setBackground(Color.WHITE);
        this.add(sp);
        
    }

    public static void main(String[] args) {
        new BillDetails("");
    }
}
