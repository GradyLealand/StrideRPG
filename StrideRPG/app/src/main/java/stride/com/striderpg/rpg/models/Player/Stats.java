package stride.com.striderpg.rpg.models.Player;


import stride.com.striderpg.rpg.Util.TimestampParser;

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

    /**
     * Implementation of a Stats toString method to print out the properties of a Stats object.
     * @return Properties of the Stats object.
     */
    @Override
    public String toString() {
        return "Stats{" +
                "enemiesDefeated=" + enemiesDefeated +
                ", itemsLooted=" + itemsLooted +
                ", registered='" + registered + '\'' +
                '}';
    }

    /**
     * Increment a Stats enemies defeated property by 1.
     */
    public void updateEnemiesDefeated() {
        this.setEnemiesDefeated(this.getEnemiesDefeated() + 1);
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
