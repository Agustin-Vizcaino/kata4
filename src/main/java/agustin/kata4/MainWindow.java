package agustin.kata4;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() throws HeadlessException {
        this.setTitle("Population histogram viewer");
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void addBarChart(BasicBarChart barChart) {
        add((Component) barChart);
    }
}
