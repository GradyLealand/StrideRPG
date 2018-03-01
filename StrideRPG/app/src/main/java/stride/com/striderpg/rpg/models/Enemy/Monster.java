package stride.com.striderpg.rpg.models.Enemy;


import stride.com.striderpg.rpg.Enums;

/**
 * Monster class for encounters between an Monster and a Player. Represents the different information
 * that a Monster contains.
 */
public class Monster extends Enemy {

    private Integer level;

    /**
     * Default constructor required for calls to
     * DataSnapshot.getValue(Monster.class).
     */
    public Monster() { }

    /**
     * Construct an enemy with the passed parameters.
     * @param name Monster name property.
     * @param type Monster type property.
     * @param health Monster health property.
     * @param icon Path to enemy image asset.
     * @param experienceReward Experience reward for defeating this enemy.
     */
    public Monster(String name, Enums.Enemies type, Integer level, Integer health,
                   Integer icon, Integer experienceReward) {
        super(name, type, health, icon, experienceReward);
        this.level = level;
    }

    public Monster(String name, Enums.Enemies type, Integer icon) {
        this.setName(name);
        this.setType(type);
        this.setIcon(icon);
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
