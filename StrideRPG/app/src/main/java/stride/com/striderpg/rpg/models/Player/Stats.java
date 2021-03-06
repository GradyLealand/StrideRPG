package stride.com.striderpg.rpg.models.Player;


import stride.com.striderpg.rpg.utils.TimeParser;

/**
 * A Stats class to store information about a Player that's less
 * important that core game-play properties. Things that are useful
 * to store over time can go here.
 */
public class Stats {

    /**
     * Total amount of enemies this player has defeated.
     */
    private Integer enemiesDefeated;

    /**
     * Total amount of bosses this player has defeated.
     */
    private Integer bossesDefeated;

    /**
     * Total amount of bosses this player has had expired on them.
     */
    private Integer bossesExpired;

    /**
     * Total amount of losses this player has had.
     */
    private Integer losses;

    /**
     * Total amount of items this player has looted.
     */
    private Integer itemsLooted;

    /**
     * Total amount of experience player has earned.
     */
    private Integer totalExperience;

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
        this.bossesDefeated = 0;
        this.bossesExpired = 0;
        this.losses = 0;
        this.itemsLooted = 0;
        this.totalExperience = 0;
        this.registered = TimeParser.makeTimestamp();
    }

    /**
     * Implementation of a Stats toString method to print out the
     * properties of a Stats object.
     */
    @Override
    public String toString() {
        return "Stats{" +
                "enemiesDefeated=" + enemiesDefeated +
                ", bossesDefeated=" + bossesDefeated +
                ", bossesExpired=" + bossesExpired +
                ", losses=" + losses +
                ", itemsLooted=" + itemsLooted +
                ", totalExperience=" + totalExperience +
                ", registered='" + registered + '\'' +
                '}';
    }

    /**
     * Increment a Stats enemies defeated property by 1.
     */
    public void updateEnemiesDefeated() {
        this.setEnemiesDefeated(this.getEnemiesDefeated() + 1);
    }

    /**
     * Increment a Stats bosses defeated property by 1.
     */
    public void updateBossesDefeated() {
        this.setBossesDefeated(this.getBossesDefeated() + 1);
    }

    /**
     * Increment a Stats bosses expired property by 1.
     */
    public void updateBossesExpired() {
        this.setBossesExpired(this.getBossesExpired() + 1);
    }

    /**
     * Increment a Stats losses property by 1.
     */
    public void updateLosses() {
        this.setLosses(this.getLosses() + 1);
    }

    /**
     * Increment a Stats items looted property by 1.
     */
    public void updateItemsLooted() {
        this.setItemsLooted(this.getItemsLooted() + 1);
    }

    /**
     * Increment a Stats totalExperience by a specified amount.
     */
    public void updateTotalExperience(Integer experience) {
        this.setTotalExperience(this.getTotalExperience() + experience);
    }

    public Integer getEnemiesDefeated() {
        return enemiesDefeated;
    }
    public void setEnemiesDefeated(Integer enemiesDefeated) {
        this.enemiesDefeated = enemiesDefeated;
    }
    public Integer getBossesDefeated() {
        return bossesDefeated;
    }
    public void setBossesDefeated(Integer bossesDefeated) {
        this.bossesDefeated = bossesDefeated;
    }
    public Integer getBossesExpired() {
        return bossesExpired;
    }
    public void setBossesExpired(Integer bossesExpired) {
        this.bossesExpired = bossesExpired;
    }
    public Integer getLosses() {
        return losses;
    }
    public void setLosses(Integer losses) {
        this.losses = losses;
    }
    public Integer getItemsLooted() {
        return itemsLooted;
    }
    public void setItemsLooted(Integer itemsLooted) {
        this.itemsLooted = itemsLooted;
    }
    public Integer getTotalExperience() {
        return totalExperience;
    }
    public void setTotalExperience(Integer totalExperience) {
        this.totalExperience = totalExperience;
    }
    public String getRegistered() {
        return this.registered;
    }
}
