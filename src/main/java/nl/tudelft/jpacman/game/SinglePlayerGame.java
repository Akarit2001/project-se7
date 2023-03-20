package nl.tudelft.jpacman.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.Luminance;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.MapParser;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;

import com.google.common.collect.ImmutableList;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.points.PointCalculatorLoader;
import nl.tudelft.jpacman.sprite.PacManSprites;

/**
 * A game with one player and a single level.
 *
 * @author Jeroen Roosen
 */
public class SinglePlayerGame extends Game {

    /**
     * The player of this game.
     */
    private final Player player;
    private final PacManSprites sprites = new PacManSprites();
    /**
     * The level of this game.
     */
    private List<Level> listlevel;

    private Level level;
    private int MAP_NUMBER = 0;
    private String theme;

    /**
     * Create a new single player game for the provided level and player.
     *
     * @param player
     *                        The player.
     * @param level
     *                        The level.
     * @param pointCalculator
     *                        The way to calculate points upon collisions.
     */
    protected SinglePlayerGame(Player player, List<Level> level, PointCalculator pointCalculator) {
        super(pointCalculator);

        assert player != null;
        assert level != null;

        this.player = player;
        listlevel = level;

        this.level = listlevel.get(MAP_NUMBER);
        this.level.registerPlayer(player);
    }

    @Override
    public List<Player> getPlayers() {
        return ImmutableList.of(player);
    }

    public void nextState() {
        MAP_NUMBER++;
        if (MAP_NUMBER > 0) {
            MAP_NUMBER = 0;
            setWin(true);
        }
        this.level.removeObserver(this);
        this.level = listlevel.get(MAP_NUMBER);
        this.level.registerPlayer(player);

    }

    @Override
    public void levelWon() {
        this.setWin(false);
        nextState();
        stop();

    }

    public void levelWonAll() {
        this.setWin(true);
        stop();
    }

    @Override
    public void levelLost() {
        this.setLost(true);
        stop();

    }

    @Override
    public Level getLevel() {
        return this.level;
    }

    @Override
    public void reStart() {
        reSetLevel();
        player.setAlive(true);
        MAP_NUMBER = -1;
        reSetScore();
        nextState();

    }

    @Override
    public void reSetScore() {
        player.reSetScore();
    }

    public void reSetLevel() {
        listlevel.set(0, createNewlevel(theme));
    }

    @Override
    public int getScore() {
        return player.getScore();
    }

    public void setSkin(String skin) {
        sprites.setPacmanSkin(skin);
        player.setSprites(sprites.getPacmanSprites());
    }

    @Override
    public void changeBoard(String theme) {
        this.theme = theme;
        this.level.removeObserver(this);
        this.level = createNewlevel(theme);
        this.level.registerPlayer(this.player); 
    }
    public Level createNewlevel(String theme){
        try {
            PacManSprites pacManSprites = new PacManSprites();
            pacManSprites.setTheme(theme);
            BoardFactory boardFactory = new BoardFactory(pacManSprites);
            LevelFactory levelFactory = new LevelFactory(pacManSprites, new GhostFactory(pacManSprites),
                    new PointCalculatorLoader().load());
            MapParser mapParser = new MapParser(levelFactory, boardFactory);
            return mapParser.parseMap("/board0.txt");
        } catch (IOException e) {
            throw new PacmanConfigurationException(
                    "Unable to create level, name = ", e);
        }
    }

}


