package electricity.billing.system;

import electricity.billing.system.BillDetails;
import electricity.billing.system.CalculateBill;
import electricity.billing.system.CustomerDetails;
import electricity.billing.system.DepositDetails;
import electricity.billing.system.GenerateBill;
import electricity.billing.system.Login;
import electricity.billing.system.NewCustomer;
import electricity.billing.system.PayBill;
import electricity.billing.system.UpdateInformation;
import electricity.billing.system.ViewInformation;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class Project
extends JFrame
implements ActionListener {
    String atype;
    String meter;
    JDesktopPane dp;

    Project(String atype, String meter) {
        super("Home Page");
        this.atype = atype;
        this.meter = meter;
        this.setExtendedState(6);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect1.jpg"));
        final Image bgImage = i1.getImage();
        this.dp = new JDesktopPane() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        this.setContentPane(this.dp);
        
        JMenuBar mb = new JMenuBar();
        mb.setBackground(Color.LIGHT_GRAY);
        mb.setForeground(Color.BLACK);
        this.setJMenuBar(mb);
        JMenu master = new JMenu("Master");
        master.setForeground(Color.BLACK);
        JMenuItem newcustomer = new JMenuItem("New Customer");
        newcustomer.setFont(new Font("monospaced", 0, 12));
        newcustomer.setBackground(Color.WHITE);
        newcustomer.setForeground(Color.BLACK);
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon1.png"));
        Image image1 = icon1.getImage().getScaledInstance(20, 20, 1);
        newcustomer.setIcon(new ImageIcon(image1));
        newcustomer.setMnemonic('D');
        newcustomer.addActionListener(this);
        newcustomer.setAccelerator(KeyStroke.getKeyStroke(68, 2));
        master.add(newcustomer);
        JMenuItem customerdetails = new JMenuItem("Customer Details");
        customerdetails.setFont(new Font("monospaced", 0, 12));
        customerdetails.setBackground(Color.WHITE);
        customerdetails.setForeground(Color.BLACK);
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("icon/icon2.png"));
        Image image2 = icon2.getImage().getScaledInstance(20, 20, 1);
        customerdetails.setIcon(new ImageIcon(image2));
        customerdetails.setMnemonic('M');
        customerdetails.addActionListener(this);
        customerdetails.setAccelerator(KeyStroke.getKeyStroke(77, 2));
        master.add(customerdetails);
        JMenuItem depositdetails = new JMenuItem("Deposit Details");
        depositdetails.setFont(new Font("monospaced", 0, 12));
        depositdetails.setBackground(Color.WHITE);
        depositdetails.setForeground(Color.BLACK);
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("icon/icon3.png"));
        Image image3 = icon3.getImage().getScaledInstance(20, 20, 1);
        depositdetails.setIcon(new ImageIcon(image3));
        depositdetails.setMnemonic('N');
        depositdetails.addActionListener(this);
        depositdetails.setAccelerator(KeyStroke.getKeyStroke(78, 2));
        master.add(depositdetails);
        JMenuItem calculatebill = new JMenuItem("Calculate Bill");
        calculatebill.setFont(new Font("monospaced", 0, 12));
        calculatebill.setBackground(Color.WHITE);
        calculatebill.setForeground(Color.BLACK);
        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("icon/icon5.png"));
        Image image4 = icon4.getImage().getScaledInstance(20, 20, 1);
        calculatebill.setIcon(new ImageIcon(image4));
        calculatebill.setMnemonic('B');
        calculatebill.addActionListener(this);
        calculatebill.setAccelerator(KeyStroke.getKeyStroke(66, 2));
        master.add(calculatebill);
        JMenu info = new JMenu("Information");
        info.setForeground(Color.BLACK);
        JMenuItem updateinformation = new JMenuItem("Update Information");
        updateinformation.setFont(new Font("monospaced", 0, 12));
        updateinformation.setBackground(Color.WHITE);
        updateinformation.setForeground(Color.BLACK);
        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        Image image5 = icon5.getImage().getScaledInstance(20, 20, 1);
        updateinformation.setIcon(new ImageIcon(image5));
        updateinformation.setMnemonic('P');
        updateinformation.addActionListener(this);
        updateinformation.setAccelerator(KeyStroke.getKeyStroke(80, 2));
        info.add(updateinformation);
        JMenuItem viewinformation = new JMenuItem("View Information");
        viewinformation.setFont(new Font("monospaced", 0, 12));
        viewinformation.setBackground(Color.WHITE);
        viewinformation.setForeground(Color.BLACK);
        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
        Image image6 = icon6.getImage().getScaledInstance(20, 20, 1);
        viewinformation.setIcon(new ImageIcon(image6));
        viewinformation.setMnemonic('L');
        viewinformation.addActionListener(this);
        viewinformation.setAccelerator(KeyStroke.getKeyStroke(76, 2));
        info.add(viewinformation);
        JMenu user = new JMenu("User");
        user.setForeground(Color.BLACK);
        JMenuItem paybill = new JMenuItem("Pay Bill");
        paybill.setFont(new Font("monospaced", 0, 12));
        paybill.setBackground(Color.WHITE);
        paybill.setForeground(Color.BLACK);
        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        Image image7 = icon7.getImage().getScaledInstance(20, 20, 1);
        paybill.setIcon(new ImageIcon(image7));
        paybill.setMnemonic('R');
        paybill.addActionListener(this);
        paybill.setAccelerator(KeyStroke.getKeyStroke(82, 2));
        user.add(paybill);
        JMenuItem billdetails = new JMenuItem("Bill Details");
        billdetails.setFont(new Font("monospaced", 0, 12));
        billdetails.setBackground(Color.WHITE);
        billdetails.setForeground(Color.BLACK);
        ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
        Image image8 = icon8.getImage().getScaledInstance(20, 20, 1);
        billdetails.setIcon(new ImageIcon(image8));
        billdetails.setMnemonic('B');
        billdetails.addActionListener(this);
        billdetails.setAccelerator(KeyStroke.getKeyStroke(66, 2));
        user.add(billdetails);
        JMenu report = new JMenu("Report");
        report.setForeground(Color.BLACK);
        JMenuItem generatebill = new JMenuItem("Generate Bill");
        generatebill.setFont(new Font("monospaced", 0, 12));
        generatebill.setBackground(Color.WHITE);
        generatebill.setForeground(Color.BLACK);
        ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("icon/icon7.png"));
        Image image9 = icon9.getImage().getScaledInstance(20, 20, 1);
        generatebill.setIcon(new ImageIcon(image9));
        generatebill.setMnemonic('G');
        generatebill.addActionListener(this);
        generatebill.setAccelerator(KeyStroke.getKeyStroke(71, 2));
        report.add(generatebill);
        JMenu utility = new JMenu("Utility");
        utility.setForeground(Color.BLACK);
        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setFont(new Font("monospaced", 0, 12));
        notepad.setBackground(Color.WHITE);
        notepad.setForeground(Color.BLACK);
        ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("icon/icon12.png"));
        Image image10 = icon10.getImage().getScaledInstance(20, 20, 1);
        notepad.setIcon(new ImageIcon(image10));
        notepad.setMnemonic('N');
        notepad.addActionListener(this);
        notepad.setAccelerator(KeyStroke.getKeyStroke(78, 2));
        utility.add(notepad);
        JMenuItem calculator = new JMenuItem("Calculator");
        calculator.setFont(new Font("monospaced", 0, 12));
        calculator.setBackground(Color.WHITE);
        calculator.setForeground(Color.BLACK);
        ImageIcon icon11 = new ImageIcon(ClassLoader.getSystemResource("icon/icon9.png"));
        Image image11 = icon11.getImage().getScaledInstance(20, 20, 1);
        calculator.setIcon(new ImageIcon(image11));
        calculator.setMnemonic('C');
        calculator.addActionListener(this);
        calculator.setAccelerator(KeyStroke.getKeyStroke(67, 2));
        utility.add(calculator);
        JMenu mexit = new JMenu("Exit");
        mexit.setForeground(Color.BLACK);
        JMenuItem exit = new JMenuItem("Exit");
        exit.setFont(new Font("monospaced", 0, 12));
        exit.setBackground(Color.WHITE);
        exit.setForeground(Color.BLACK);
        ImageIcon icon12 = new ImageIcon(ClassLoader.getSystemResource("icon/icon11.png"));
        Image image12 = icon12.getImage().getScaledInstance(20, 20, 1);
        exit.setIcon(new ImageIcon(image12));
        exit.setMnemonic('W');
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(87, 2));
        mexit.add(exit);
        if (atype.equals("Admin")) {
            mb.add(master);
        } else {
            mb.add(info);
            mb.add(user);
            mb.add(report);
        }
        mb.add(utility);
        mb.add(mexit);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void addInternalFrame(JInternalFrame frame) {
        this.dp.add(frame);
        int x = (this.dp.getWidth() - frame.getWidth()) / 2;
        int y = (this.dp.getHeight() - frame.getHeight()) / 2;
        frame.setLocation(x, y);
        frame.setVisible(true);
        try {
            frame.setSelected(true);
        }
        catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        if (msg.equals("New Customer")) {
            addInternalFrame(new NewCustomer());
        } else if (msg.equals("Customer Details")) {
            addInternalFrame(new CustomerDetails());
        } else if (msg.equals("Deposit Details")) {
            addInternalFrame(new DepositDetails());
        } else if (msg.equals("Calculate Bill")) {
            addInternalFrame(new CalculateBill());
        } else if (msg.equals("View Information")) {
            addInternalFrame(new ViewInformation(this.meter));
        } else if (msg.equals("Update Information")) {
            addInternalFrame(new UpdateInformation(this.meter));
        } else if (msg.equals("Bill Details")) {
            addInternalFrame(new BillDetails(this.meter));
        } else if (msg.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Exit")) {
            this.setVisible(false);
            new Login();
        } else if (msg.equals("Pay Bill")) {
            addInternalFrame(new PayBill(this.meter));
        } else if (msg.equals("Generate Bill")) {
            addInternalFrame(new GenerateBill(this.meter));
        }
    }

    public static void main(String[] args) {
        new Project("", "");
    }
}