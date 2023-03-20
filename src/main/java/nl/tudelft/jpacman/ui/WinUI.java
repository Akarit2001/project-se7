package nl.tudelft.jpacman.ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class WinUI extends JPanel {

    JPanel buttonPanel = new JPanel();
    ImageIcon background;
    JPanel bgJPanel = new JPanel();
    JLabel bg = new JLabel();

    public WinUI() {
        this.setBackground("src\\main\\resources\\bgwin2.gif");

    }

    public void addButton(JButton btnhome, JButton btnrestart) {
        buttonPanel.add(btnhome);
        buttonPanel.add(btnrestart);
        // Hide the border of the button
        btnhome.setBorderPainted(false);
        btnhome.setContentAreaFilled(false);
        btnhome.setBackground(Color.BLACK);

        btnrestart.setBorderPainted(false);
        btnrestart.setContentAreaFilled(false);
        btnrestart.setBackground(Color.BLACK);

        buttonPanel.setBackground(new java.awt.Color(255, 255, 255, 0));
        this.setLayout(new GridBagLayout());

        // Set constraints for buttonPanel
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.SOUTHEAST;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(10, 10, 20, 20);
        btnhome.setPreferredSize(new Dimension(120, 35));
        Dimension btnBackMinSize = new Dimension(80, 40);
        btnhome.setMinimumSize(btnBackMinSize);
        this.add(btnhome, c);
        ImageIcon icon = new ImageIcon("src\\main\\resources\\btnHome.png");
        Image scaledImage = icon.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        btnhome.setIcon(scaledIcon);

        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 1;
        c2.anchor = GridBagConstraints.SOUTHWEST;
        c2.weightx = 1.0;
        c2.weighty = 1.0;
        c2.insets = new Insets(10, 20, 20, 10);
        btnrestart.setPreferredSize(new Dimension(120, 35));
        Dimension btnBackMinSize2 = new Dimension(80, 40);
        btnrestart.setMinimumSize(btnBackMinSize2);
        this.add(btnrestart, c2);
        ImageIcon icon2 = new ImageIcon("src\\main\\resources\\btnRestart.png");
        Image scaledImage2 = icon2.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
        btnrestart.setIcon(scaledIcon2);

    }

    public ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
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