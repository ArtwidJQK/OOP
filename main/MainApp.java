package BillManagement.main;

import BillManagement.boundary.MainMenuGUI;
import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenuGUI mainFrame = new MainMenuGUI();
            mainFrame.setVisible(true);
        });
    }
}
