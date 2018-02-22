package stride.com.striderpg.rpg.models.Enemy;


import stride.com.striderpg.rpg.Enums;

/**
 * Enemy class for encounters between an Enemy and a Player. Represents the different information
 * that a Enemy contains.
 */
public class Enemy {

    /**
     * Enemy name.
     */
    private String name;

    /**
     * Enemy type enum.
     */
    private Enums.EnemyType type;

    /**
     * Enemy level.
     */
    private Integer level;

    /**
     * Enemies current health value.
     */
    private Integer health;

    /**
     * Minimum amount of damage an enemy can deliver to a Player.
     */
    private Integer minDamage;

    /**
     * Maximum amount of damage an enemy can deliver to a Player.
     */
    private Integer maxDamage;

    /**
     * Integer ID for the enemies icon image asset.
     */
    private Integer icon;

    /**
     * The amount of experience that is rewarded to a Player on a
     * successful Enemy defeat.
     */
    private Integer experienceReward;

    /**
     * Default constructor required for calls to
     * DataSnapshot.getValue(Enemy.class).
     */
    public Enemy() { }

    /**
     * Construct an enemy with the passed parameters.
     * @param name Enemy name property.
     * @param type Enemy type property.
     * @param health Enemy health property.
     * @param minDamage Enemies minimum damage value.
     * @param maxDamage Enemies maximum damage value.
     * @param icon Path to enemy image asset.
     * @param experienceReward Experience reward for defeating this enemy.
     */
    public Enemy(String name, Enums.EnemyType type,Integer level, Integer health, Integer minDamage, Integer maxDamage, Integer icon, Integer experienceReward) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.health = health;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.icon = icon;
        this.experienceReward = experienceReward;
    }

    /**
     * Implementation of an Enemies toString method to print out the
     * properties of an Enemy.
     * @return Properties of the Enemy object.
     */
    @Override
    public String toString() {
        return "Enemy{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", level =" + level +
                ", health=" + health +
                ", minDamage=" + minDamage +
                ", maxDamage=" + maxDamage +
                ", icon='" + icon + '\'' +
                ", experienceReward=" + experienceReward +
                '}';
    }


    public String getName() {
        return name;
    }

    public Enums.EnemyType getType() {
        return type;
    }

    public Integer getLevel() {
        return  level;
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getMinDamage() {
        return minDamage;
    }

    public Integer getMaxDamage() {
        return maxDamage;
    }

    public Integer getIcon() {
        return icon;
    }

    public Integer getExperienceReward() {
        return experienceReward;
    }
}
