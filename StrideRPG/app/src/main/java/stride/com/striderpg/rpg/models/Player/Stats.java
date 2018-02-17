package stride.com.striderpg.rpg.models.Player;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import stride.com.striderpg.rpg.models.Activity.TimestampParser;

/**
 * A Stats class to store information about a Player that's less important that core
 * game-play properties. Things that are useful to store over time can go here.
 */
public class Stats {

    /**
     * Total amount of enemies this player has defeated.
     */
    private Integer enemiesDefeated;

    /**
     * Total amount of items this player has looted.
     */
    private Integer itemsLooted;

    /**
     * Player registration date.
     */
    private String registered;

    /**
     * Default constructor required for calls to
     * DataSnapshot.getValue(Player.class).
     */
    public Stats() {
        this.enemiesDefeated = 0;
        this.itemsLooted = 0;
        this.registered = TimestampParser.makeTimestamp();
    }

    public Integer getEnemiesDefeated() {
        return enemiesDefeated;
    }
    public void setEnemiesDefeated(Integer enemiesDefeated) {
        this.enemiesDefeated = enemiesDefeated;
    }

    public Integer getItemsLooted() {
        return itemsLooted;
    }
    public void setItemsLooted(Integer itemsLooted) {
        this.itemsLooted = itemsLooted;
    }

    public String getRegistered() {
        return this.registered;
    }
}
