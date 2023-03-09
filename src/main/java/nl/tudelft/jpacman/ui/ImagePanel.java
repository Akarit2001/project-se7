package nl.tudelft.jpacman.ui;

import javax.swing.*;

import java.awt.*;

class ImagePanel extends JPanel {

    public ImagePanel() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addIMage(ImageIcon img) {
        JLabel logo = new JLabel();
        logo.setSize(new Dimension(200, 200));
        Image image = img.getImage();
        Image dimg = image.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(dimg);
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);
        logo.setIcon(icon);
        add(logo);
    }

}