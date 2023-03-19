package nl.tudelft.jpacman.ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class WinUI extends JPanel {

    // create a panel to hold the buttons
    JPanel buttonPanel = new JPanel();
    // create two buttons to switch between cards

    JLabel title = new JLabel("PacMan");
    ImageIcon background;
    JPanel bgJPanel = new JPanel();
    JLabel bg = new JLabel();

    public WinUI() {
        this.setBackground("src\\main\\resources\\5.png");

    }

    public void addButton(JButton btn) {
        buttonPanel.add(btn);
        ImageIcon icon = new ImageIcon("src\\main\\resources\\btnHome.png");

        // Set the icon of the button
        Image scaledImage = icon.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);

        // Create a new ImageIcon with the scaled image
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Set the icon of the button
        btn.setIcon(scaledIcon);

        // Hide the border of the button
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBackground(new java.awt.Color(255, 255, 255, 0));

        buttonPanel.setBackground(new java.awt.Color(255, 255, 255, 0));
        this.setLayout(new GridBagLayout());

        // Set constraints for buttonPanel
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.SOUTHEAST;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(10, 10, 20, 20); // add 10-pixel margin
        // Set preferred size of button
        btn.setPreferredSize(new Dimension(120, 35));
        this.add(buttonPanel, c);
    }

    public ImageIcon ResizeImage(ImageIcon image, int width, int hight) {

        Image img = image.getImage().getScaledInstance(bg.getWidth(), bg.getHeight(), Image.SCALE_SMOOTH);

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