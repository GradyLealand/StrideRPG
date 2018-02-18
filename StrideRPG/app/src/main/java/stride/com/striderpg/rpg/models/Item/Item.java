package stride.com.striderpg.rpg.models.Item;


import stride.com.striderpg.rpg.Enums;

/**
 * An Item class to represent an Item present in a Players Equipment.
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
     * Items itemRarity level, used to determine stat's on generation.
     */
    private Enums.ItemRarity itemRarity;

    /**
     * Items itemType, randomly selected on item generation.
     */
    private Enums.ItemType itemType;

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
     * @param itemRarity Item itemRarity level.
     * @param itemType Item itemType.
     */
    public Item(String name, Integer powerLevel, Integer vitalityBoost, Integer strengthBoost,
                Integer speedBoost, Enums.ItemRarity itemRarity, Enums.ItemType itemType) {
        this.name = name;
        this.powerLevel = powerLevel;
        this.vitalityBoost = vitalityBoost;
        this.strengthBoost = strengthBoost;
        this.speedBoost = speedBoost;
        this.itemRarity = itemRarity;
        this.itemType = itemType;
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
                ", itemRarity=" + itemRarity +
                ", itemType=" + itemType +
                '}';
    }

    /**
     * Compare two items together by comparing their power level property.
     * @param item Item being compared to.
     * @return Boolean if this item is better than passed item.
     */
    public boolean isBetter(Item item) {
        return this.powerLevel > item.getPowerLevel();
    }

    public String getName() {
        return name;
    }

    public Integer getPowerLevel() {
        return powerLevel;
    }

    public Integer getVitalityBoost() {
        return vitalityBoost;
    }

    public Integer getStrengthBoost() {
        return strengthBoost;
    }

    public Integer getSpeedBoost() {
        return speedBoost;
    }

    public Enums.ItemRarity getItemRarity() {
        return itemRarity;
    }

    public Enums.ItemType getItemType() {
        return itemType;
    }
}
