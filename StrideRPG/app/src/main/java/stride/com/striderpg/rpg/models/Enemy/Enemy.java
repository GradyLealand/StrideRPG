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
     * The amount of experience that is rewarded to a Player on a successful Enemy defeat.
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
    public Enemy(String name, Enums.EnemyType type, Integer health, Integer minDamage, Integer maxDamage, Integer icon, Integer experienceReward) {
        this.name = name;
        this.type = type;
        this.health = health;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.icon = icon;
        this.experienceReward = experienceReward;
    }

    /**
     * Implementation of an Enemies toString method to print out the properties of an Enemy.
     * @return Properties of the Enemy object.
     */
    @Override
    public String toString() {
        return "Enemy{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", health=" + health +
                ", minDamage=" + minDamage +
                ", maxDamage=" + maxDamage +
                ", icon='" + icon + '\'' +
                ", experienceReward=" + experienceReward +
                '}';
    }

    /**
     * Get an enemies name property.
     * @return Enemy name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get an enemies type enum property.
     * @return Enemy type.
     */
    public Enums.EnemyType getType() {
        return type;
    }

    /**
     * Get an enemies health property.
     * @return Enemy health.
     */
    public Integer getHealth() {
        return health;
    }

    /**
     * Get an enemies minimum damage property.
     * @return Enemies minimum damage.
     */
    public Integer getMinDamage() {
        return minDamage;
    }

    /**
     * Get an enemies maximum damage property.
     * @return Enemies maximum damage.
     */
    public Integer getMaxDamage() {
        return maxDamage;
    }

    /**
     * Get an enemies url/path to image asset.
     * @return URI to Enemy image asset icon.
     */
    public Integer getIcon() {
        return icon;
    }

    /**
     * Get an enemies experience reward when defeated.
     * @return Enemy experience reward.
     */
    public Integer getExperienceReward() {
        return experienceReward;
    }
}
