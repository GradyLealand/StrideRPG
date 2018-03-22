package stride.com.striderpg.rpg.models.Enemy;


import stride.com.striderpg.rpg.Enums;

public abstract class Enemy {

    /**
     * Monster name.
     */
    private String name;

    /**
     * Monster type enum.
     */
    private Enums.Enemies type;

    /**
     * Monster current health value.
     */
    private Integer health;

    /**
     * Integer ID for the enemies icon image asset.
     */
    private Integer icon;

    /**
     * The amount of experience that is rewarded to a Player on a
     * successful Monster defeat.
     */
    private Integer experienceReward;

    public Enemy() { }

    public Enemy(String name, Enums.Enemies type, Integer health, Integer icon, Integer experienceReward) {
        this.name = name;
        this.type = type;
        this.health = health;
        this.icon = icon;
        this.experienceReward = experienceReward;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Enums.Enemies getType() {
        return type;
    }
    public void setType(Enums.Enemies type) {
        this.type = type;
    }
    public Integer getHealth() {
        return health;
    }
    public void setHealth(Integer health) {
        this.health = health;
    }
    public Integer getIcon() {
        return icon;
    }
    public void setIcon(Integer icon) {
        this.icon = icon;
    }
    public Integer getExperienceReward() {
        return experienceReward;
    }
    public void setExperienceReward(Integer experienceReward) {
        this.experienceReward = experienceReward;
    }
}
