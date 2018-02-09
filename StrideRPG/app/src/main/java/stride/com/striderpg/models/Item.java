package stride.com.striderpg.models;

/**
 * An Item class to represent an Item present in a Players Inventory.
 */
public class Item {

    /**
     * Name of the Item.
     */
    private String name = "test";

    /**
     * Short description or flavour text to describe this Item.
     */
    private String description = "desc";

    /**
     * The power level this weapon represents. A short handed way to describe
     * how good an item is based on Players level and Skills.
     */
    private Integer powerLevel = 13;

    /**
     * Amount to boost a Players Vitality by when equipped.
     */
    private Integer vitalityBoost = 8;

    /**
     * Amount to boost a Players Strength by when equipped.
     */
    private Integer strengthBoost = 9;

    /**
     * Amount to boost a Players Speed by when equipped.
     */
    private Integer speedBoost = 2;

    /**
     * Default constructor required for calls to
     * DataSnapshot.getValue(Item.class).
     */
    public Item() { }

    /**
     * Implementation of an Items toString method to print out the properties of an Item object.
     *
     * @return Properties of the Item object.
     */
    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", powerLevel=" + powerLevel +
                ", vitalityBoost=" + vitalityBoost +
                ", strengthBoost=" + strengthBoost +
                ", speedBoost=" + speedBoost +
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
     * Get an Items description property.
     * @return Item description.
     */
    public String getDescription() {
        return description;
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
}
