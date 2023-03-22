package nl.tudelft.jpacman.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.border.Border;

import nl.tudelft.jpacman.audio.PacManSoundPlayer;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.sprite.PacManSprites;
import nl.tudelft.jpacman.ui.ScorePanel.ScoreFormatter;

/**
 * The default JPacMan UI frame. The PacManUI consists of the following
 * elements:
 *
 * <ul>
 * <li>A score panel at the top, displaying the score of the player(s).
 * <li>A board panel, displaying the current level, i.e. the board and all units
 * on it.
 * <li>A button panel, containing all buttons provided upon creation.
 * </ul>
 *
 * @author Jeroen Roosen
 *
 */
public class PacManUI extends JFrame implements ActionListener {

    /**
     * Default serialisation UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The desired frame rate interval for the graphics in milliseconds, 40
     * being 25 fps.
     */
    private static final int FRAME_INTERVAL = 40;

    /**
     * The panel displaying the player scores.
     */
    private final ScorePanel scorePanel;

    /**
     * The panel displaying the game.
     */
    private final BoardPanel boardPanel;

    /**
     * Creates a new UI for a JPacman game.
     *
     * @param game
     *                       The game to play.
     * @param buttons
     *                       The map of caption-to-action entries that will appear
     *                       as
     *                       buttons on the interface.
     * @param keyMappings
     *                       The map of keyCode-to-action entries that will be added
     *                       as key
     *                       listeners to the interface.
     * @param scoreFormatter
     *                       The formatter used to display the current score.
     * 
     * 
     * 
     */

    // create a card layout
    CardLayout cardLayout = new CardLayout();
    // create a panel to hold the cards
    JPanel cardPanel = new JPanel();
    JPanel homePanel = new JPanel();
    final Game game;
    JPanel GamePlay = new JPanel();
    JPanel buttonPanel = new JPanel();

    JButton btnstop = new JButton("Stop");
    // JButton btnquit = new JButton("quit");
    JButton btnWin = new JButton("Home");
    JButton btnSkin = new JButton("select");
    JButton btnBack = new JButton("back");
    JButton restartButton = new JButton("restart");
    JButton btnrestart = new JButton();
    JButton homeButton = new JButton("home");
    JButton btnStart = new JButton("Start");
    JButton playButton = new JButton("Tap the screen to play");
    WinUI winUI = new WinUI();
    HomeUI homeUI = new HomeUI();
    ThemesUI themesUI;

    private CustomFont customFont = new CustomFont();
    PacManSprites pacmanSprites;
    PacmanSkinUI pacmanSkinUI;
    private String theme;
    PacManSoundPlayer pacManSoundPlayer;

    Font font = customFont.fontFormat();

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
        boardPanel.setBackground("src\\main\\resources\\sprite\\themes\\" + theme + "\\board.png");
    }

    public PacManUI(Game game, final Map<String, Action> buttons,
            final Map<Integer, Action> keyMappings,
            ScoreFormatter scoreFormatter) {

        assert game != null;
        assert buttons != null;
        assert keyMappings != null;
        this.game = game;
        this.theme = "valentine";

        customFont.setSizeFont(18f);
        Font font = customFont.fontFormat();

        themesUI = new ThemesUI(this, game);

        // set size JFrame
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // addCard Layout to Card Panel
        cardPanel.setLayout(cardLayout);

        PacKeyListener keys = new PacKeyListener(keyMappings);
        addKeyListener(keys);

        scorePanel = new ScorePanel(game.getPlayers());
        if (scoreFormatter != null) {
            scorePanel.setScoreFormatter(scoreFormatter);
        }
        pacmanSkinUI = new PacmanSkinUI(pacmanSprites, game);
        boardPanel = new BoardPanel(game);
        GamePlay.setLayout(new BorderLayout());
        boardPanel.setOpaque(false);
        GamePlay.add(scorePanel, BorderLayout.NORTH);
        GamePlay.add(boardPanel, BorderLayout.CENTER);

        // set theme
        boardPanel.setBackground("src\\main\\resources\\sprite\\themes\\" + theme + "\\board.png");

        btnstop.setFont(font);
        btnstop.setText(" Stop ");
        btnstop.setForeground(Color.YELLOW);
        btnstop.setOpaque(false);
        btnstop.setBorderPainted(false);
        btnstop.setFocusPainted(false);
        btnstop.setContentAreaFilled(false);

        /*
         * btnquit.setFont(font);
         * btnquit.setText("Quit  ");
         * btnquit.setForeground(Color.YELLOW);
         * btnquit.setOpaque(false);
         * btnquit.setBorderPainted(false);
         * btnquit.setFocusPainted(false);
         * btnquit.setContentAreaFilled(false);
         */

        btnWin.addActionListener(this);
        btnSkin.addActionListener(this);
        btnBack.addActionListener(this);
        btnrestart.addActionListener(this);
        btnstop.addActionListener(this);
        // btnquit.addActionListener(this);

        scorePanel.addPauseButton(btnstop);
        // scorePanel.addQuitButton(btnquit);

        winUI.addButton(btnWin, btnrestart);
        pacmanSkinUI.addButton(btnSkin, btnBack);
        btnStart.addActionListener(this);

        cardPanel.add(homeUI, "home");
        cardPanel.add(GamePlay, "gameplay");
        cardPanel.add(pacmanSkinUI, "PacmanSkin");
        cardPanel.add(winUI, "Win");
        cardPanel.add(themesUI, "themes");
        add(cardPanel);
        pack();
        pacManSoundPlayer = new PacManSoundPlayer();
        pacManSoundPlayer.playBgSound();

        // tap to start game
        homeUI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    pacManSoundPlayer.StopBgSound();
                    Point p = e.getPoint();
                    SwingUtilities.convertPointToScreen(p, getContentPane());
                    SwingUtilities.convertPointFromScreen(p, btnStart);
                    cardLayout.show(cardPanel, "PacmanSkin");
                    if (btnStart.contains(p)) {
                        btnStart.doClick();

                    }
                }
            }
        });
    }

    /**
     * Starts the "engine", the thread that redraws the interface at set
     * intervals.
     */
    public void start() {
        setVisible(true);
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(this::displayGameOverDialog, 0, FRAME_INTERVAL, TimeUnit.MILLISECONDS);
    }

    /**
     * Draws the next frame, i.e. refreshes the scores and game.
     */

    private void displayGameOverDialog() {
        boardPanel.repaint();
        scorePanel.refresh();

        if (game.isLost()) {
            game.setLost(false);
            JDialog gameOverDialog = createGameOverDialog();
            gameOverDialog.setVisible(true);
            game.setWin(false);
            game.reStart();

        } else if (game.isWin()) {
            cardLayout.show(cardPanel, "Win");
        }
    }

    private JDialog createGameOverDialog() {
        JDialog gameOverDialog = new JDialog(this, "Game Over", true);
        gameOverDialog.setUndecorated(true);
        gameOverDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Border border = BorderFactory.createLineBorder(Color.WHITE, 4);
        gameOverDialog.getRootPane().setBorder(border);
        gameOverDialog.setLayout(new BorderLayout());

        // add mouse listener to content pane
        gameOverDialog.getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameOverDialog.dispose();
            }
        });

        JPanel backgroundPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("src\\main\\resources\\bgOver.png");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

            }
        };

        JLabel scoreLabel = new JLabel("Your Score: " + game.getScore(), SwingConstants.CENTER);
        customFont.setSizeFont(24f);
        Font font1 = customFont.fontFormat();
        scoreLabel.setFont(font1);
        scoreLabel.setForeground(Color.WHITE);
        int topPadding = 20;
        scoreLabel.setBorder(BorderFactory.createCompoundBorder(scoreLabel.getBorder(),
                BorderFactory.createEmptyBorder(topPadding, 0, 0, 0)));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 0, 0));
        JButton restartButton = createRestartButton(gameOverDialog);
        JButton homeButton = createHomeButton(gameOverDialog);

        homeButton.setFont(font);
        homeButton.setText("  HOME  ");
        homeButton.setForeground(Color.YELLOW);
        homeButton.setOpaque(false);
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(false);

        restartButton.setFont(font);
        restartButton.setText(" RESTART ");
        restartButton.setForeground(Color.YELLOW);
        restartButton.setOpaque(false);
        restartButton.setBorderPainted(false);
        restartButton.setFocusPainted(false);
        restartButton.setContentAreaFilled(false);

        gameOverDialog.setContentPane(backgroundPanel);
        buttonPanel.add(restartButton);
        buttonPanel.add(homeButton);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.setOpaque(false);

        southPanel.add(buttonPanel);

        backgroundPanel.setLayout(new BorderLayout());

        backgroundPanel.add(southPanel, BorderLayout.SOUTH);
        backgroundPanel.add(scoreLabel, BorderLayout.NORTH);
        gameOverDialog.setSize(350, 250);
        gameOverDialog.setLocationRelativeTo(this);

        return gameOverDialog;
    }

    private JButton createRestartButton(JDialog dialog) {
        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> {
            dialog.dispose();
            requestFocus();
            game.reStart();
            GameDialog();
        });
        return restartButton;
    }

    private JButton createHomeButton(JDialog dialog) {
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(e -> {
            dialog.dispose();
            requestFocus();
            game.reStart();

            cardLayout.show(cardPanel, "home");
            pacManSoundPlayer.playBgSound();
        });
        return homeButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PacManSoundPlayer.playBtnClick();
        if (e.getSource() == btnSkin) {
            cardLayout.show(cardPanel, "themes");
        } else if (e.getSource() == btnBack) {
            cardLayout.show(cardPanel, "home");
            pacManSoundPlayer.playBgSound();
        } else if (e.getSource() == btnWin) {
            cardLayout.show(cardPanel, "home");
            pacManSoundPlayer.playBgSound();
            game.setWin(false);
            game.reStart();
        } else if (e.getSource() == btnrestart) {
            cardLayout.show(cardPanel, "PacmanSkin");

            game.setWin(false);
            game.reStart();
        } else if (e.getSource() == btnstop) {
            game.stop();
            GameDialog();
        } // else if (e.getSource() == btnquit) {
          // game.stop();
          // QuitDialog();
          // }

    }

    public void setGameStart() {
        cardLayout.show(cardPanel, "gameplay");
        GameDialog();
    }

    private void GameDialog() {
        JDialog frame = new JDialog(this);
        addComponentListener(new ComponentAdapter() {
            public void componentMoved(ComponentEvent e) {
                int x = getLocation().x + (getWidth() - frame.getWidth()) / 2;
                int y = getLocation().y + (getHeight() - frame.getHeight()) / 2;
                frame.setLocation(x, y);

            }
        });
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(GamePlay.getWidth(), GamePlay.getHeight()));
        frame.setUndecorated(true);
        frame.setBackground(new Color(100, 100, 100, 50));
        frame.setLocationRelativeTo(this);
        frame.requestFocusInWindow();

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("Tap the screen to play");
        titleLabel.setFont(font);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.YELLOW);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(3, 3, 3, 200));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        panel.add(titlePanel, BorderLayout.CENTER); // Add titlePanel to the top of the panel

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    Point p = e.getPoint();
                    SwingUtilities.convertPointToScreen(p, getContentPane());
                    SwingUtilities.convertPointFromScreen(p, playButton);

                    frame.setVisible(false);
                    game.start();
                    requestFocus();
                    frame.dispose();
                    if (playButton.contains(p)) {
                        playButton.doClick();

                    }
                }
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    /*
     * private void QuitDialog() {
     * 
     * JDialog dialog = new JDialog(this, "Confirmation", true);
     * JLabel label = new JLabel("Do you want to exit?");
     * JPanel panel = new JPanel();
     * panel.add(label);
     * dialog.add(panel);
     * dialog.setUndecorated(true);
     * JButton yesButton = new JButton("Yes");
     * yesButton.addActionListener(e1 -> {
     * System.exit(0);
     * dialog.dispose();
     * });
     * JButton noButton = new JButton("No");
     * noButton.addActionListener(e1 -> {
     * game.start();
     * dialog.dispose();
     * requestFocus();
     * });
     * JPanel buttonPanel = new JPanel();
     * buttonPanel.add(yesButton);
     * buttonPanel.add(noButton);
     * dialog.add(buttonPanel, "South");
     * dialog.pack();
     * dialog.setLocationRelativeTo(this);
     * dialog.setVisible(true);
     * 
     * }
     */

    public void pageSkins() {
        cardLayout.show(cardPanel, "PacmanSkin");
    }

}
