package nl.tudelft.jpacman.ui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class HomeUI extends JPanel {

    ImageIcon background;
    JLabel bg = new JLabel();

    public HomeUI() {
        this.setBackground("src\\main\\resources\\bghome2 .gif");

    }

    public void start() {
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        // Call the super method
        super.paintComponent(g);
        // Draw the image icon on the panel
        g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

    public void setBackground(String path) {
        background = new ImageIcon(path);

    }

}
