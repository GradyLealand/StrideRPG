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
     * Description of the Item.
     */
    private String description;

    /**
     * Item Type Enumeration.
     */
    private Enums.ItemType type;

    /**
     * Item Rarity Enumeration.
     */
    private Enums.ItemRarity rarity;

    /**
     * Stat boost this Item gives the Player.
     */
    private Integer statBoost;

    public Item() { }

    /**
     * Constructor to set all Item properties.
     * @param name Item name.
     * @param description Item description.
     * @param type Item Type Enumeration.
     * @param rarity Item Rarity Enumeration.
     * @param statBoost Item stat boost.
     */
    public Item(String name, String description, Enums.ItemType type, Enums.ItemRarity rarity, Integer statBoost) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.rarity = rarity;
        this.statBoost = statBoost;
    }

    /**
     * Compare this Item to another Item.
     * @param item Item being compared to this.
     * @return True or False if this Item's stat boost
     * is less than the item passed as a parameter.
     */
    public boolean compare(Item item) {
        return this.statBoost < item.getStatBoost();
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", rarity=" + rarity +
                ", statBoost=" + statBoost +
                '}';
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Enums.ItemType getType() {
        return type;
    }
    public Enums.ItemRarity getRarity() {
        return rarity;
    }
    public Integer getStatBoost() {
        return statBoost;
    }
}
