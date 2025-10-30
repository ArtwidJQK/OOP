package BillManagement.boundary;

import BillManagement.control.BillManagerControl;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainMenuGUI extends JFrame {
    
    private BillManagerControl control;
    private BillListGUI billListPanel;

    public MainMenuGUI() {
        this.control = new BillManagerControl();

        setTitle("Bill Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        billListPanel = new BillListGUI(control);
        add(billListPanel);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        
        JMenuItem addItem = new JMenuItem("Thêm Hóa Đơn Mới");
        addItem.addActionListener((ActionEvent e) -> {
            BillFormGUI addForm = new BillFormGUI(control, null, billListPanel); 
            addForm.setVisible(true);
        });
        
        JMenuItem statsItem = new JMenuItem("Thống kê (TB Nước Ngoài)");
        statsItem.addActionListener((ActionEvent e) -> {
            double avg = control.getAverageForeignerBill();
            JOptionPane.showMessageDialog(this, 
                String.format("Trung bình thành tiền của KH nước ngoài: %.2f", avg),
                "Thống kê", JOptionPane.INFORMATION_MESSAGE);
        });

        fileMenu.add(addItem);
        fileMenu.add(statsItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
        setLocationRelativeTo(null);
    }
}
