package stride.com.striderpg.rpg.models.Encounter;


import java.util.ArrayList;

import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Item.Item;

/**
 * A Boss class for storing information about a Boss that a Player
 * will fight during an ActiveEncounter.
 */
public class Boss {

    /**
     * Boss name property.
     */
    private String name;

    /**
     * BossType enumeration property.
     */
    private Enums.BossType type;

    /**
     * BossTier enumeration property.
     */
    private Enums.BossTier tier;

    /**
     * Boss expiration timestamp.
     */
    private String expiration;

    /**
     * Boss health property.
     */
    private Integer health;

    /**
     * Boss maxHealth property.
     */
    private Integer maxHealth;

    /**
     * Boss level property.
     */
    private Integer level;

    /**
     * Boss icon resource id property.
     */
    private Integer icon;

    /**
     * Boss rewards ArrayList property.
     */
    private ArrayList<Item> rewards = new ArrayList<>();

    /**
     * Experience reward for defeating Boss.
     */
    private Integer encounterExperienceReward;

    /**
     * Default constructor required for calls to
     * DataSnapshot.getValue(Boss.class).
     */
    public Boss() { }

    /**
     * Construct a new Boss object using the parameters passed
     * into the Constructor method.
     */
    public Boss(String name, Enums.BossType type, Enums.BossTier tier, String expiration,
                Integer health, Integer level, Integer icon, ArrayList<Item> rewards,
                Integer encounterExperienceReward) {
        this.name = name;
        this.type = type;
        this.tier = tier;
        this.expiration = expiration;
        this.health = health;
        this.maxHealth = health;
        this.level = level;
        this.icon = icon;
        this.rewards = rewards;
        this.encounterExperienceReward = encounterExperienceReward;
    }

    /**
     * Implementation of a Bosses toString method to print out the
     * properties of a Boss object.
     * @return Properties of the Boss object.
     */
    @Override
    public String toString() {
        return "Boss{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", tier=" + tier +
                ", expiration='" + expiration + '\'' +
                ", health=" + health +
                ", level=" + level +
                ", icon=" + icon +
                ", rewards=" + rewards +
                ", encounterExperienceReward=" + encounterExperienceReward +
                '}';
    }

    public String getName() {
        return name;
    }

    public Enums.BossType getType() {
        return type;
    }

    public Enums.BossTier getTier() {
        return tier;
    }

    public String getExpiration() {
        return expiration;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getMaxHealth() {
        return maxHealth;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getIcon() {
        return icon;
    }

    public ArrayList<Item> getRewards() {
        return rewards;
    }

    public Integer getEncounterExperienceReward() {
        return encounterExperienceReward;
    }
}
