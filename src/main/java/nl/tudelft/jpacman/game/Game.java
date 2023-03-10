package nl.tudelft.jpacman.game;

import java.util.List;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Level.LevelObserver;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;

/**
 * A basic implementation of a Pac-Man game.
 *
 * @author Jeroen Roosen
 */
public abstract class Game implements LevelObserver {

    /**
     * <code>true</code> if the game is in progress.
     */
    private boolean inProgress;
    private boolean win;
    private boolean lost;
    private boolean winAll;

    /**
     * Object that locks the start and stop methods.
     */
    private final Object progressLock = new Object();

    /**
     * The algorithm used to calculate the points that
     * they player gets whenever some action happens.
     */
    private PointCalculator pointCalculator;

    /**
     * Creates a new game.
     *
     * @param pointCalculator
     *                        The way to calculate points upon collisions.
     */
    protected Game(PointCalculator pointCalculator) {
        this.pointCalculator = pointCalculator;
        inProgress = false;
        win = false;
        lost = false;

    }

    /**
     * Starts or resumes the game.
     */
    public void start() {
        synchronized (progressLock) {
            if (isInProgress()) {
                return;
            }
            if (getLevel().isAnyPlayerAlive() && getLevel().remainingPellets() > 0) {
                inProgress = true;
                getLevel().addObserver(this);
                getLevel().start();
            }
        }
    }

    /**
     * Pauses the game.
     */
    public void stop() {
        synchronized (progressLock) {
            if (!isInProgress()) {
                return;
            }
            inProgress = false;
            getLevel().stop();
        }
    }

    /**
     * @return <code>true</code> iff the game is started and in progress.
     */
    public boolean isInProgress() {
        return inProgress;
    }

    /**
     * @return An immutable list of the participants of this game.
     */
    public abstract List<Player> getPlayers();

    /**
     * @return The level currently being played.
     */
    public abstract Level getLevel();

    public abstract void reStart();

    public abstract void reSetScore();

    public abstract int getScore();

    /**
     * Moves the specified player one square in the given direction.
     *
     * @param player
     *                  The player to move.
     * @param direction
     *                  The direction to move in.
     */
    public void move(Player player, Direction direction) {
        if (isInProgress()) {
            // execute player move.
            getLevel().move(player, direction);
            pointCalculator.pacmanMoved(player, direction);
        }
    }

    @Override
    public void levelWon() {
        win = true;
        stop();
    }

    @Override
    public void levelLost() {
        lost = true;
        stop();
    }

    public void setIsprogress(Boolean b) {
        inProgress = b;
    }

    public Boolean isWin() {

        return win;
    }

    public Boolean isLost() {
        return lost;
    }

    public void setWin(Boolean bl) {
        win = bl;
    }

    public void setLost(Boolean bl) {
        lost = bl;
    }

}
