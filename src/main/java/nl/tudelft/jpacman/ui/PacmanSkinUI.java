package nl.tudelft.jpacman.ui;

import javax.swing.*;

import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.sprite.PacManSprites;

import java.awt.*;

//Merge my code with the previous one.

public class PacmanSkinUI extends JPanel {
    JPanel buttonPanel = new JPanel();
    // create two buttons to switch between cards

    ImageIcon background;
    JPanel bgJPanel = new JPanel();
    JLabel bg = new JLabel();

    private JButton selectButton;
    private SkinChangeListener skinChangeListener;
    private final ImageIcon[] skinImages = new ImageIcon[] {
            new ImageIcon("src\\main\\resources\\PacmanSkin\\pacman1.png"),
            new ImageIcon("src\\main\\resources\\PacmanSkin\\cat.png"),
            new ImageIcon("src\\main\\resources\\PacmanSkin\\buble.png"),
            new ImageIcon("src\\main\\resources\\PacmanSkin\\dropy.png"),
            new ImageIcon("src\\main\\resources\\PacmanSkin\\pacman3.png"),
            new ImageIcon("src\\main\\resources\\PacmanSkin\\pacman4.png"),
            new ImageIcon("src\\main\\resources\\PacmanSkin\\pacman5.png")

    };

    private final String[] skinNames = new String[] {
            "Pacman",
            "Tom",
            "Buble",
            "Dropy",
            "Santy",
            "Chloe",
            "Mulan"
    };
    private final String[] pathSkin = new String[] {
            new String("/sprite/pacman.png"),
            new String("/sprite/catskin.png"),
            new String("/sprite/bubleskin.png"),
            new String("/sprite/dropyskin.png"),
            new String("/sprite/pacman3.png"),
            new String("/sprite/pacman4.png"),
            new String("/sprite/pacman5.png")

    };
    private int currentSkinIndex = 0;
    private final JLabel skinLabel = new JLabel(skinImages[currentSkinIndex]);
    private final JLabel skinNameLabel = new JLabel(skinNames[currentSkinIndex]);
    private final JButton leftButton = new JButton("<");
    private final JButton rightButton = new JButton(">");
    private final Game game;

    public PacmanSkinUI(PacManSprites pacmanSprites, Game game) {
        this.setBackground("src\\main\\resources\\bgSkinUI.png");
        this.game = game;
        setLayout(new BorderLayout());

        // create a new panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.BLACK);
        // create constraints for the centerPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0; // added non-zero weightx
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // create the centerPanel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new java.awt.Color(3, 3, 3));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1.0; // added non-zero weightx
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 10, 0, 10);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));

        // add left button
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(100, 10, 0, 20));
        leftPanel.setBackground(Color.BLACK);
        leftPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftButton.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(leftButton);
        centerPanel.add(leftPanel);
        leftButton.setBorderPainted(false);
        leftButton.setContentAreaFilled(false);
        ImageIcon icon1 = new ImageIcon("src\\main\\resources\\Lselect.png");
        Image scaledImage1 = icon1.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
        leftButton.setIcon(scaledIcon1);

        // add skin label
        centerPanel.add(skinLabel);

        // add right button
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(100, 20, 0, 10));
        rightPanel.setBackground(Color.BLACK);
        rightPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightButton.setHorizontalAlignment(SwingConstants.CENTER);
        rightPanel.add(rightButton);
        centerPanel.add(rightPanel);
        rightButton.setBorderPainted(false);
        rightButton.setContentAreaFilled(false);
        ImageIcon icon2 = new ImageIcon("src\\main\\resources\\Rselect.png");
        Image scaledImage2 = icon2.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
        rightButton.setIcon(scaledIcon2);

        // create a new panel with FlowLayout
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.BLACK);

        // create the title label
        GridBagConstraints titlePanelConstraints = new GridBagConstraints();
        titlePanelConstraints.gridx = 1;
        titlePanelConstraints.gridy = 0;
        titlePanelConstraints.insets = new Insets(20, 0, 0, 0);
        titlePanelConstraints.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("Choose Skins");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.YELLOW);
        titlePanel.add(titleLabel);

        // add an empty border to the title panel
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        mainPanel.add(titlePanel, titlePanelConstraints);

        // add skin name
        GridBagConstraints skinNamePanelConstraints = new GridBagConstraints();
        skinNamePanelConstraints.gridx = 1;
        skinNamePanelConstraints.gridy = 2;
        skinNamePanelConstraints.insets = new Insets(20, 0, 0, 0);
        skinNamePanelConstraints.anchor = GridBagConstraints.CENTER;

        // create the skinNamePanel
        JPanel skinNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        skinNamePanel.setBackground(Color.BLACK);
        skinNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        skinNameLabel.setForeground(Color.YELLOW);
        skinNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        skinNamePanel.add(skinNameLabel);

        mainPanel.add(skinNamePanel, skinNamePanelConstraints);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(centerPanel, BorderLayout.CENTER);

        // add the buttonPanel to the center of the mainPanel
        mainPanel.add(buttonPanel, gbc);
        add(mainPanel, BorderLayout.CENTER);

        leftButton.addActionListener(e -> changeSkin(-1));
        rightButton.addActionListener(e -> changeSkin(1));
        setButtonDimensions(leftButton, 50, 70);
        setButtonDimensions(rightButton, 50, 70);

    }

    private void setButtonDimensions(JButton button, int width, int height) {
        Dimension dimension = new Dimension(width, height);
        button.setPreferredSize(dimension);
        button.setMaximumSize(dimension);
        button.setMinimumSize(dimension);
    }

    public void setSkinChangeListener(SkinChangeListener listener) {
        this.skinChangeListener = listener;
    }

    public void addButton(JButton btnSkin, JButton btnBack) {
        selectButton = btnSkin;

        btnSkin.setBorderPainted(false);
        btnSkin.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);

        this.setLayout(new GridBagLayout());

        // Set constraints for buttonPanel
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.SOUTHEAST;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(20, 10, 20, 20); // add 10-pixel margin
        // Set preferred size of button
        btnSkin.setPreferredSize(new Dimension(120, 35));
        this.add(selectButton, c);
        ImageIcon icon1 = new ImageIcon("src\\main\\resources\\btnSelect.png");
        Image scaledImage1 = icon1.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
        btnSkin.setIcon(scaledIcon1);

        // Add constraints for back button
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 2;
        c2.anchor = GridBagConstraints.SOUTHWEST;
        c2.weightx = 1.0;
        c2.weighty = 1.0;
        c2.insets = new Insets(20, 10, 20, 20); // add 10-pixel margin
        // Set preferred size of back button
        btnBack.setPreferredSize(new Dimension(120, 35));
        this.add(btnBack, c2);
        ImageIcon icon2 = new ImageIcon("src\\main\\resources\\btnBack.png");

        // Set the icon of the button
        Image scaledImage2 = icon2.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);

        // Create a new ImageIcon with the scaled image
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);

        // Set the icon of the button
        btnBack.setIcon(scaledIcon2);

        selectButton.addActionListener(e -> {
            game.setSkin(pathSkin[currentSkinIndex]);
        });

    }

    private void changeSkin(int index) {
        currentSkinIndex += index;
        if (currentSkinIndex < 0) {
            currentSkinIndex = skinImages.length - 1;
        } else if (currentSkinIndex >= skinImages.length) {
            currentSkinIndex = 0;
        }
        currentSkinIndex += index;
        currentSkinIndex = (currentSkinIndex + skinImages.length) % skinImages.length;
        skinLabel.setIcon(skinImages[currentSkinIndex]);
        skinNameLabel.setText(skinNames[currentSkinIndex]);

        ImageIcon newSkin = skinImages[currentSkinIndex];
        skinLabel.setIcon(newSkin);

        if (skinChangeListener != null) {
            skinChangeListener.onSkinChanged(newSkin);
            skinImages[currentSkinIndex] = resizeImageIcon(skinImages[currentSkinIndex], 120, 120);
        }

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