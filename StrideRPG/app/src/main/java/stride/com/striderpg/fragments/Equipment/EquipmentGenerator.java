package stride.com.striderpg.fragments.Equipment;


import java.util.ArrayList;

import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.models.Item.Item;

/**
 * EquipmentGenerator used to retrieve the current Players
 * Equipment to generate an ArrayList of each Item.
 */
public class EquipmentGenerator {

    /**
     * EquipmentGenerator Logging tag.
     */
    private static final String TAG = "EquipmentGenerator";

    /**
     * Item ArrayList to hold each Item that will be inside of
     * the Equipment RecyclerView.
     */
    private ArrayList<EquipmentAdapter.ItemDataHolder> items = new ArrayList<>();

    /**
     * Constructor that calls the buildEquipment() method to
     * generate the Equipment information/data.
     */
    EquipmentGenerator() {
        buildEquipment();
    }

    /**
     * Build out the items ArrayList by adding the active players
     * current Equipment slots values to the items ArrayList.
     */
    private void buildEquipment() {
        for (Item item : G.activePlayer.getEquipment().getSlots().values()) {
            items.add(new EquipmentAdapter.ItemDataHolder(item.getType(), item));
        }
    }

    /**
     * Gets the items ArrayList.
     */
    public ArrayList<EquipmentAdapter.ItemDataHolder> getItems() {
        return items;
    }
}
