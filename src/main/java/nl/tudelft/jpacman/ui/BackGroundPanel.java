package nl.tudelft.jpacman.ui;

import javax.swing.*;
import java.awt.*;

public class BackGroundPanel extends JPanel {
    private static ImageIcon background;
    private static JPanel panel;

    @Override
    public void paintComponent(Graphics g) {
        // Call the super method
        super.paintComponent(g);
        // Draw the image icon on the panel
        g.drawImage(background.getImage(), 0, 0, panel.getWidth(), panel.getHeight(), this);
    }

    public void setBackground(String path) {
        background = new ImageIcon(path);

    }

    public void setPanel(JPanel Panel) {
        panel = Panel;
        add(panel);
    }

}
