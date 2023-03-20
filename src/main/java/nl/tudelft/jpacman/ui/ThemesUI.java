package nl.tudelft.jpacman.ui;

import javax.swing.*;
import java.awt.*;
import nl.tudelft.jpacman.game.Game;
import java.util.ArrayList;
import java.util.List;

public class ThemesUI extends JPanel {

    private JPanel themeItemJPanel;
    private JButton prevButton;
    private JButton nextButton;

    private JButton confirmButton;
    private JButton backButton;
    private JLabel imageLabel;
    private JLabel ThemeNamePanel;
    // files name
    private final String[] themString = { "Valentine", "China", "Christmas", "Halloween"};
    private List<ImageIcon> imageBg = new ArrayList<>();
    private int indexOfTheme;
    private PacManUI pacManUI;

    public ThemesUI(PacManUI pacManUI, Game game) {
        this.pacManUI = pacManUI;
        this.setBackground(getBackground());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 1.0;
        c.weighty = 1.0;

        indexOfTheme = 0;
        imageBg = new ArrayList<>();

        for (int i = 0; i < themString.length; i++) {
            imageBg.add(createNewImageResizer(i));
        }

        ImageIcon icon = imageBg.get(indexOfTheme);
        imageLabel = new JLabel(icon);
        imageLabel.setPreferredSize(new Dimension(420, 280));

        this.setLayout(new GridBagLayout());
        this.setBackground(Color.BLACK);
        prevButton = new JButton("<");
        nextButton = new JButton(">");
        backButton = new JButton("back");
        confirmButton = new JButton("confirm");
        themeItemJPanel = new JPanel();
        nextButton.addActionListener(e -> {
            nextTheme();
        });
        prevButton.addActionListener(e -> {
            prevTheme();
        });
        confirmButton.addActionListener(e -> {
            game.changeBoard(themString[indexOfTheme]);
            pacManUI.setGameStart();
        });
        backButton.addActionListener(e -> pacManUI.pageSkins());
        themeItemJPanel.setBackground(new java.awt.Color(255, 0, 0));

        c.insets = new Insets(-50, 100, 100, 0);
        ImageIcon icon1 = new ImageIcon("src\\main\\resources\\Lselect.png");
        Image scaledImage = icon1.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        prevButton.setIcon(scaledIcon);
        prevButton.setPreferredSize(new Dimension(65, 60));
        prevButton.setBorderPainted(false);
        prevButton.setContentAreaFilled(false);
        this.add(prevButton, c);

        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(-50, 10, 100, 100);
        icon1 = new ImageIcon("src\\main\\resources\\Rselect.png");
        scaledImage = icon1.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        nextButton.setIcon(scaledIcon);
        nextButton.setPreferredSize(new Dimension(65, 60));
        nextButton.setBorderPainted(false);
        nextButton.setContentAreaFilled(false);
        this.add(nextButton, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(-50, 0, 100, 0);
        imageLabel.setBorder(null);
        imageLabel.setBorder(BorderFactory.createMatteBorder(5, 15, 5, 15, Color.YELLOW));
        this.add(imageLabel, c);

        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.insets = new Insets(0, 30, 20, 0);
        icon1 = new ImageIcon("src\\main\\resources\\btnBack.png");
        scaledImage = icon1.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        backButton.setIcon(scaledIcon);
        backButton.setPreferredSize(new Dimension(85, 30));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        this.add(backButton, c);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets = new Insets(0, 0, 20, 30);
        icon1 = new ImageIcon("src\\main\\resources\\btnSelect.png");
        scaledImage = icon1.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        confirmButton.setIcon(scaledIcon);
        confirmButton.setPreferredSize(new Dimension(95, 30));
        confirmButton.setBorderPainted(false);
        confirmButton.setBorderPainted(false);
        confirmButton.setContentAreaFilled(false);
        this.add(confirmButton, c);

        // Create title panel with title label
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Select Theme");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.YELLOW);
        titlePanel.setBackground(Color.BLACK);
        titlePanel.add(titleLabel);
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(20, 0, 0, 0);
        this.add(titlePanel, c);
        setVisible(true);

        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(20, 0, 0, 0);
        ThemeNamePanel = new JLabel(themString[indexOfTheme]);
        ThemeNamePanel.setFont(new Font("Arial", Font.BOLD, 20));
        ThemeNamePanel.setForeground(Color.YELLOW);
        this.add(ThemeNamePanel, c);
    }

    private void prevTheme() {
        if (indexOfTheme == 0) {
            indexOfTheme = imageBg.size();
        }
        indexOfTheme--;
        imageLabel.setIcon(imageBg.get(indexOfTheme));
        pacManUI.setTheme(themString[indexOfTheme]);
        ThemeNamePanel.setText(themString[indexOfTheme]);
    }

    private void nextTheme() {
        if (indexOfTheme == imageBg.size() - 1) {
            indexOfTheme = -1;
        }
        indexOfTheme++;
        imageLabel.setIcon(imageBg.get(indexOfTheme));
        pacManUI.setTheme(themString[indexOfTheme]);
        ThemeNamePanel.setText(themString[indexOfTheme]);
    }

    private ImageIcon createNewImageResizer(int themIndex) {
        ImageIcon originalImageIcon = new ImageIcon(
                "src\\main\\resources\\sprite\\themes\\" + themString[themIndex] + "\\board.png");
        Image scaledImage = originalImageIcon.getImage().getScaledInstance(420, 280, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
