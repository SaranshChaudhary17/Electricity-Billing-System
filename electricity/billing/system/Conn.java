package electricity.billing.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    Connection c;
    Statement s;

    Conn() {
        try {
            this.c = DriverManager.getConnection("jdbc:mysql:///ebs", "root", "Jaat@9412");
            this.s = this.c.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
