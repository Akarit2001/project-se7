package nl.tudelft.jpacman.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.game.Game;

/**
 * Panel displaying a game.
 *
 * @author Jeroen Roosen
 *
 */
class BoardPanel extends JPanel {

    /**
     * Default serialisation ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The background colour of the board.
     */
    private static final Color BACKGROUND_COLOR = Color.WHITE;

    /**
     * The size (in pixels) of a square on the board. The initial size of this
     * panel will scale to fit a board with square of this size.
     */
    private static final int SQUARE_SIZE = 16;

    /**
     * The game to display.
     */
    private final Game game;
    BufferedImage background;

    /**
     * Creates a new board panel that will display the provided game.
     *
     * @param game
     *             The game to display.
     */
    BoardPanel(Game game) {
        super();
        assert game != null;
        this.game = game;

        Board board = game.getLevel().getBoard();

        int w = board.getWidth() * SQUARE_SIZE;
        int h = board.getHeight() * SQUARE_SIZE;

        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0.5f));

        Dimension size = new Dimension(w, h);
        setMinimumSize(size);
        setPreferredSize(size);
    }

    @Override
    public void paint(Graphics g) {
        assert g != null;
        // Draw the background image first

        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        // Cast your graphics object to a graphics2d object
        Graphics2D g2d = (Graphics2D) g;
        // Create an AlphaComposite with 50% transparency and DST_OVER rule
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.DST_OVER, 0.5f);
        // Set the composite on the graphics2d object
        g2d.setComposite(ac);
        // Draw your sprites with transparency
        render(game.getLevel().getBoard(), g2d, getSize());
    }

    /**
     * Renders the board on the given graphics context to the given dimensions.
     *
     * @param board
     *                 The board to render.
     * @param graphics
     *                 The graphics context to draw on.
     * @param window
     *                 The dimensions to scale the rendered board to.
     */
    private void render(Board board, Graphics graphics, Dimension window) {
        int cellW = window.width / board.getWidth();
        int cellH = window.height / board.getHeight();

        graphics.fillRect(0, 0, window.width, window.height);

        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                int cellX = x * cellW;
                int cellY = y * cellH;
                Square square = board.squareAt(x, y);
                render(square, graphics, cellX, cellY, cellW, cellH);
            }
        }
    }

    /**
     * Renders a single square on the given graphics context on the specified
     * rectangle.
     *
     * @param square
     *                 The square to render.
     * @param graphics
     *                 The graphics context to draw on.
     * @param x
     *                 The x position to start drawing.
     * @param y
     *                 The y position to start drawing.
     * @param width
     *                 The width of this square (in pixels.)
     * @param height
     *                 The height of this square (in pixels.)
     */
    private void render(Square square, Graphics g, int x, int y, int width, int height) {
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(ac);
        square.getSprite().draw(g2d, x, y, width, height);
        for (Unit unit : square.getOccupants()) {
            unit.getSprite().draw(g2d, x, y, width, height);
        }
    }

    public void setBackground(String path) {
        try {
            background = ImageIO.read(new File(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
