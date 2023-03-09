package nl.tudelft.jpacman.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import java.awt.*;

import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.ui.ScorePanel.ScoreFormatter;

public class HomeUI extends JPanel {

    // create a panel to hold the buttons
    JPanel buttonPanel = new JPanel();
    // create two buttons to switch between cards

    JLabel title = new JLabel("PacMan");
    ImageIcon background;
    JPanel logoPanel = new JPanel();
    JPanel miniPanel = new JPanel();
    JLabel logo = new JLabel();
    JLabel miniLogo = new JLabel();

    public HomeUI() {

        logoPanel.setBackground(new Color(0, 0, 0, 0));
        logoPanel.add(logo);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(logoPanel);

    }

    public void start() {
        setVisible(true);
    }

    public void addButton(JButton btn) {
        buttonPanel.add(btn);
        buttonPanel.setBackground(new java.awt.Color(255, 0, 0, 0));
        this.add(buttonPanel);
    }

    public ImageIcon ResizeImage(ImageIcon image, int width, int hight) {

        Image img = image.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH);

        return new ImageIcon(img);

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
