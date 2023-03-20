package nl.tudelft.jpacman.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
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

    JButton btnWin = new JButton("Home");
    JButton btnSkin = new JButton("select");
    JButton btnBack = new JButton("back");
    JButton restartButton = new JButton("restart");
    JButton btnrestart = new JButton();
    JButton homeButton = new JButton("home");
    JButton btnStart = new JButton("Start");

    WinUI winUI = new WinUI();
    HomeUI homeUI = new HomeUI();
    ThemesUI themesUI;

    PacManSprites pacmanSprites;
    PacmanSkinUI pacmanSkinUI;
    private String theme;

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

        themesUI = new ThemesUI(this, game);
        // set size JFrame
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // addCard Layout to Card Panel
        cardPanel.setLayout(cardLayout);

        PacKeyListener keys = new PacKeyListener(keyMappings);
        addKeyListener(keys);

        JPanel buttonPanel = new ButtonPanel(buttons, this);

        scorePanel = new ScorePanel(game.getPlayers());
        if (scoreFormatter != null) {
            scorePanel.setScoreFormatter(scoreFormatter);
        }
        pacmanSkinUI = new PacmanSkinUI(pacmanSprites, game);
        boardPanel = new BoardPanel(game);
        GamePlay.setLayout(new BorderLayout());
        boardPanel.setOpaque(false);
        GamePlay.add(buttonPanel, BorderLayout.SOUTH);
        GamePlay.add(scorePanel, BorderLayout.NORTH);
        GamePlay.add(boardPanel, BorderLayout.CENTER);

        // set theme
        boardPanel.setBackground("src\\main\\resources\\sprite\\themes\\" + theme + "\\board.png");

        btnWin.addActionListener(this);
        btnSkin.addActionListener(this);
        btnBack.addActionListener(this);
        btnrestart.addActionListener(this);

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

        // tap to start game
        homeUI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
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

        } else if (game.isWin()) {
            cardLayout.show(cardPanel, "Win");
        }
    }

    private JDialog createGameOverDialog() {
        JDialog gameOverDialog = new JDialog(this, "Game Over", true);
        gameOverDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        gameOverDialog.setLayout(new BorderLayout());
        game.setWin(false);
        game.reStart();
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
                ImageIcon imageIcon = new ImageIcon("src\\main\\resources\\sprite\\themes\\" + theme + "\\board.png");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        JLabel gameOverLabel = new JLabel("You Died", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gameOverLabel.setForeground(Color.RED);
        int padding = 20;
        gameOverLabel.setBorder(BorderFactory.createEmptyBorder(padding, 0, padding, 0));

        JLabel scoreLabel = new JLabel("Your Score: " + game.getScore(), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        scoreLabel.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new java.awt.Color(255, 255, 255, 0));
        JButton restartButton = createRestartButton(gameOverDialog);
        JButton homeButton = createHomeButton(gameOverDialog);

        gameOverDialog.setContentPane(backgroundPanel);
        buttonPanel.add(restartButton);
        buttonPanel.add(homeButton);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.setOpaque(false);

        southPanel.add(buttonPanel);

        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(gameOverLabel, BorderLayout.NORTH);
        backgroundPanel.add(southPanel, BorderLayout.SOUTH);
        backgroundPanel.add(scoreLabel, BorderLayout.CENTER);
        gameOverDialog.setSize(350, 250);
        gameOverDialog.setLocationRelativeTo(this);

        return gameOverDialog;
    }

    private JButton createRestartButton(JDialog dialog) {
        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> {
            dialog.dispose();
            game.reStart();
        });
        return restartButton;
    }

    private JButton createHomeButton(JDialog dialog) {
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(e -> {
            dialog.dispose();
            game.reStart();
            cardLayout.show(cardPanel, "home");
        });
        return homeButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSkin) {
            cardLayout.show(cardPanel, "themes");
        } else if (e.getSource() == btnBack) {
            cardLayout.show(cardPanel, "home");
        } else if (e.getSource() == btnWin) {
            cardLayout.show(cardPanel, "home");
            game.setWin(false);
            game.reStart();
        } else if (e.getSource() == btnrestart) {
            cardLayout.show(cardPanel, "PacmanSkin");
            game.setWin(false);
            game.reStart();
        }
    }

    public void setGameStart() {
        cardLayout.show(cardPanel, "gameplay");
    }

    public void pageSkins() {
        cardLayout.show(cardPanel, "PacmanSkin");
    }

}
