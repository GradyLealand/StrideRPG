package stride.com.striderpg.models;


import stride.com.striderpg.rpg.Enums;

/**
 * An Item class to represent an Item present in a Players Inventory.
 */
public class Item {

    /**
     * Name of the Item.
     */
    private String name;

    /**
     * The power level this weapon represents. A short handed way to describe
     * how good an item is based on Players level and Skills.
     */
    private Integer powerLevel;

    /**
     * Amount to boost a Players Vitality by when equipped.
     */
    private Integer vitalityBoost;

    /**
     * Amount to boost a Players Strength by when equipped.
     */
    private Integer strengthBoost;

    /**
     * Amount to boost a Players Speed by when equipped.
     */
    private Integer speedBoost;

    /**
     * Items rarity level, used to determine stat's on generation.
     */
    private Enums.Rarity rarity;

    /**
     * Items type, randomly selected on item generation.
     */
    private Enums.Type type;

    /**
     * Default constructor required for calls to
     * DataSnapshot.getValue(Item.class).
     */
    public Item() { }

    /**
     * Custom constructor to build an item with specified properties.
     * @param name Item name.
     * @param powerLevel Item power level. Generally used to determine how powerful this item is.
     * @param vitalityBoost Item vitality boost.
     * @param strengthBoost Item strength boost.
     * @param speedBoost Item speed boost.
     * @param rarity Item rarity level.
     * @param type Item type.
     */
    public Item(String name, Integer powerLevel, Integer vitalityBoost, Integer strengthBoost,
                Integer speedBoost, Enums.Rarity rarity, Enums.Type type) {
        this.name = name;
        this.powerLevel = powerLevel;
        this.vitalityBoost = vitalityBoost;
        this.strengthBoost = strengthBoost;
        this.speedBoost = speedBoost;
        this.rarity = rarity;
        this.type = type;
    }

    /**
     * Implementation of an Items toString method to print out the properties of an Item object.
     * @return Properties of the Item object.
     */
    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", powerLevel=" + powerLevel +
                ", vitalityBoost=" + vitalityBoost +
                ", strengthBoost=" + strengthBoost +
                ", speedBoost=" + speedBoost +
                ", rarity=" + rarity +
                ", type=" + type +
                '}';
    }

    /**
     * Get an Items name property.
     * @return Item name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get an Items power level property.
     * @return Item power level.
     */
    public Integer getPowerLevel() {
        return powerLevel;
    }

    /**
     * Get an Items vitality boost.
     * @return Item vitality boost.
     */
    public Integer getVitalityBoost() {
        return vitalityBoost;
    }

    /**
     * Get an Items strength boost.
     * @return Item strength boost.
     */
    public Integer getStrengthBoost() {
        return strengthBoost;
    }

    /**
     * Get an Items speed boost.
     * @return Item speed boost.
     */
    public Integer getSpeedBoost() {
        return speedBoost;
    }

    /**
     * Get an items rarity.
     * @return Item Rarity enum value.
     */
    public Enums.Rarity getRarity() {
        return rarity;
    }

    /**
     * Get an items type.
     * @return Item Type enum value.
     */
    public Enums.Type getType() {
        return type;
    }
}
