package stride.com.striderpg.rpg.models.Enemy;


import java.util.ArrayList;

import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Item.Item;

/**
 * A Boss class for storing information about a Boss that a Player
 * will fight during an ActiveEncounter.
 */
public class Boss extends Enemy {

    /**
     * BossTier enumeration property.
     */
    private Enums.BossTier tier;

    /**
     * Boss expiration timestamp.
     */
    private String expiration;

    /**
     * Boss maxHealth property.
     */
    private Integer maxHealth;

    /**
     * Boss rewards ArrayList property.
     */
    private ArrayList<Item> rewards = new ArrayList<>();

    /**
     * Default constructor required for calls to
     * DataSnapshot.getValue(Boss.class).
     */
    public Boss() { }

    public Boss(String name, Enums.Enemies type, Integer health, Integer icon, Integer experienceReward,
                Enums.BossTier tier, String expiration, ArrayList<Item> rewards) {
        super(name, type, health, icon, experienceReward);
        this.tier = tier;
        this.expiration = expiration;
        this.maxHealth = health;
        this.rewards = rewards;
    }

    public Boss(String name, Enums.Enemies type, Integer icon) {
        this.setName(name);
        this.setType(type);
        this.setIcon(icon);
    }

    public Enums.BossTier getTier() {
        return tier;
    }

    public void setTier(Enums.BossTier tier) {
        this.tier = tier;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public Integer getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(Integer maxHealth) {
        this.maxHealth = maxHealth;
    }

    public ArrayList<Item> getRewards() {
        return rewards;
    }

    public void setRewards(ArrayList<Item> rewards) {
        this.rewards = rewards;
    }
}
