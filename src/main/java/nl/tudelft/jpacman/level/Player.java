package nl.tudelft.jpacman.level;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.skills.Skills;
import nl.tudelft.jpacman.sprite.AnimatedSprite;
import nl.tudelft.jpacman.sprite.Sprite;

/**
 * A player operated unit in our game.
 *
 * @author Jeroen Roosen
 */
public class Player extends Unit {

    /**
     * The amount of points accumulated by this player.
     */
    private int score;
    private int seconds;

    private boolean isSkillActive;


    public void setIsSkillActive(boolean isSkillActive) {
        this.isSkillActive = isSkillActive;
    }

    /**
     * The animations for every direction.
     */
    private final Map<Direction, Sprite> sprites;

    /**
     * The animation that is to be played when Pac-Man dies.
     */
    private final AnimatedSprite deathSprite;

    /**
     * <code>true</code> iff this player is alive.
     */
    private boolean alive;

    /**
     * {@link Unit} iff this player died by collision, <code>null</code> otherwise.
     */
    private Unit killer;

    /**
     * Creates a new player with a score of 0 points.
     *
     * @param spriteMap
     *                       A map containing a sprite for this player for every
     *                       direction.
     * @param deathAnimation
     *                       The sprite to be shown when this player dies.
     */
    protected Player(Map<Direction, Sprite> spriteMap, AnimatedSprite deathAnimation) {
        this.score = 0;
        this.alive = true;
        this.sprites = spriteMap;
        this.deathSprite = deathAnimation;
        deathSprite.setAnimating(false);
        isSkillActive = false;
    }

    /**
     * Returns whether this player is alive or not.
     *
     * @return <code>true</code> iff the player is alive.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Sets whether this player is alive or not.
     *
     * If the player comes back alive, the {@link killer} will be reset.
     *
     * @param isAlive
     *                <code>true</code> iff this player is alive.
     */
    public void setAlive(boolean isAlive) {
        if (isAlive) {
            deathSprite.setAnimating(false);
            this.killer = null;
        }
        if (!isAlive) {
            deathSprite.restart();
        }
        this.alive = isAlive;
    }

    /**
     * Returns the unit that caused the death of Pac-Man.
     *
     * @return <code>Unit</code> iff the player died by collision, otherwise
     *         <code>null</code>.
     */
    public Unit getKiller() {
        return killer;
    }

    /**
     * Sets the cause of death.
     *
     * @param killer is set if collision with ghost happens.
     */
    public void setKiller(Unit killer) {
        this.killer = killer;
    }

    /**
     * Returns the amount of points accumulated by this player.
     *
     * @return The amount of points accumulated by this player.
     */
    public int getScore() {
        return score;
    }

    @Override
    public Sprite getSprite() {
        if (isAlive()) {
            return sprites.get(getDirection());
        }
        return deathSprite;
    }

    /**
     * Adds points to the score of this player.
     *
     * @param points
     *               The amount of points to add to the points this player already
     *               has.
     */
    public void addPoints(int points) {
        score += points;
    }

    public void reSetScore() {
        score = 0;
    }

    public void setSkill(Skills skills) {
        if(!isSkillActive){
            Timer timer = new Timer();

            seconds = 0;
            isSkillActive =true;
        
            // Schedule a task to run every 1 second
            System.out.println("player skill executed.");
            timer.schedule(new TimerTask() {
              public void run() {
                
                if (seconds < 5 && isAlive()) {
                  System.out.println("Text printed after " + ++seconds + " seconds");
                  addPoints(15);
                  
                } else {
                  // Stop the timer after 10 seconds
                  System.out.println("player skill canceled.");
                  isSkillActive=false;
                  seconds = 0;
                  timer.cancel();
                }
              }
            }, 0, 1000);
            skills.execute();
        }


    }
}
